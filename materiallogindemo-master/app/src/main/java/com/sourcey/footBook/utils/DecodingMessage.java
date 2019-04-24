package com.sourcey.footBook.utils;

import com.sourcey.footBook.entity.Header;
import com.sourcey.footBook.entity.Info;
import com.sourcey.footBook.entity.Message;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 解码数据，保存到Message中
 */
public class DecodingMessage {
    private BufferedInputStream bytes;
    private byte[] msgArr;
    private static final int ZERO = 0;
    private static final int FOUR = 4;
    private static final int EIGHT = 8;
    private static final int TWELVE = 12;
    private static final int SIXTEEN = 16;


    public DecodingMessage(BufferedInputStream bytes) {
        this.bytes = bytes;
        this.msgArr = new byte[4096];
    }

    public Message decode() {
        int temp = -1;
        int size = 0;
        try {
            while ((temp = bytes.read(msgArr)) != -1) {
                size += temp;
            }
            //删除message后剩余(未使用)部分
            msgArr = Arrays.copyOf(msgArr, size);

            Header header = createHeader(msgArr);

            msgArr = Arrays.copyOfRange(msgArr,
                    Header.HEADER_SIZE, msgArr.length);
            Info info = createInfo(msgArr);

            return new Message(header, info);
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    private Header createHeader(byte[] byteArr) {
        rangeCheck(Header.HEADER_SIZE);
        Header header = new Header();

        try {
            header.setPayloadSize(Convert.bytesToInt(byteArr, ZERO));
            header.setType(Convert.bytesToInt(byteArr, FOUR));
            header.setSender(Convert.bytesToInt(byteArr, EIGHT));
            header.setReceiver(Convert.bytesToInt(byteArr, TWELVE));
            //与Code中的枚举进行比较
            header.setStatus(byteArr[SIXTEEN]);

            return header;
        } catch (IOException e) {
            System.out.println("创建Header对象错误");
            e.printStackTrace();
        }
        return null;

    }

    private Info createInfo(byte[] byteArr) {
        rangeCheck(Convert.SHORT_SIZE);
        Info info = new Info();
        short[] sizes = getSize(byteArr);
        if(sizes[0] > 64 || sizes[1] > 1024) {
            throw new RuntimeException("解码数据格式错误");
        }
        int length = sizes[0] + sizes[1];

        if(byteArr.length - 2 != length) {
            throw new RuntimeException("解码数据格式错误");
        }
        int offset = 2;
        try {
            String keyStr = Convert.bytesToString(byteArr,
                    offset, offset + sizes[0]);
            offset += sizes[0];
            String valueStr = Convert.bytesToString(byteArr, offset, byteArr.length);

            info.setKeySize(sizes[0]);
            info.setValueSize(sizes[1]);
            info.setKey(keyStr);
            info.setValue(valueStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取keySize和valueSize的值
     * keySize：6位
     * valueSize：10位
     */
    private short[] getSize(byte[] byteArr) {
        byteArr = Arrays.copyOfRange(byteArr, ZERO, Convert.SHORT_SIZE);
        short[] sizes = new short[2];
        sizes[0] = (short) (byteArr[0] >>> 2 & 0x3f);
        sizes[1] = (short) ((byteArr[1] & 0xff) + ((byteArr[0] & 0x03) << 8));
        return sizes;
    }

    private void rangeCheck(int size) {
        if(msgArr.length < size) {
            throw new RuntimeException("解码数据格式错误");
        }
    }

    private void close() {
        IOutils.close(bytes);
    }
}
