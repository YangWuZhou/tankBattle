package com.sourcey.footBook.utils;


import com.sourcey.footBook.entity.Header;
import com.sourcey.footBook.entity.Info;
import com.sourcey.footBook.entity.Message;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * 将Message中的数据编码，发送到服务器
 */
public class EncodingMessage {
    private Message message;
    private byte[] msgArr;
    private int offset;

    public EncodingMessage(Message message) {
        this.message = message;
        this.msgArr = new byte[4096];
        this.offset = 0;
    }

    public byte[] encode() {

        Header header = message.getHeader();
        Info info = message.getInfo();

        splitHeader(header);
        splitInfo(info);

        return msgArr;
    }

    private void splitHeader(Header header) {
        if(header == null) {
            throw new RuntimeException("编码数据格式错误");
        }
        try {
            //[0, 4)
            offset += toMsgArr(offset, Convert.intToBytes(header.getPayloadSize()));
            //[4, 12)
            offset += toMsgArr(offset, Convert.longToBytes(header.getSender()));
            //[12, 20)
            offset += toMsgArr(offset, Convert.longToBytes(header.getReceiver()));
            //[20, 21)
            offset += toMsgArr(offset, header.getType());
            //[21, 22)
            offset += toMsgArr(offset, header.getStatus());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void splitInfo(Info info) {
        if(info == null) {
            return;
        }
        Set<Map.Entry<String, String>> payload = info.getPayload().entrySet();
        for(Map.Entry<String, String> entry : payload) {
            offset += toMsgArr(offset,
                    sizesToBytes(new short[]{(short) entry.getKey().length(),
                            (short) entry.getValue().length()}));
            byte[] key = entry.getKey() != null ? entry.getKey().getBytes() : new byte[0];
            offset += toMsgArr(offset, key);
            byte[] value = entry.getValue() != null ? entry.getValue().getBytes() : new byte[0];
            toMsgArr(offset, value);
        }
    }

    private int toMsgArr(int start, byte... byteArr) {
        int msgIndex = start;
        int byteIndex = 0;
        while(msgIndex < byteArr.length + start) {
            msgArr[msgIndex] = byteArr[byteIndex];
            msgIndex++;
            byteIndex++;
        }
        return byteArr.length;
    }

    /*
     * 将keySize和valueSize转换成两个字节
     */
    private byte[] sizesToBytes(short[] sizes) {
        byte[] byteArr = new byte[2];
        byteArr[0] = (byte) ((sizes[1] >>> 8 ) + (sizes[0] << 2));
        byteArr[1] = (byte) (sizes[1] & 0xff);
        return byteArr;
    }
}
