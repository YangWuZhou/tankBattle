package com.sourcey.footBook.utils;

import com.sourcey.footBook.entity.Header;
import com.sourcey.footBook.entity.Info;
import com.sourcey.footBook.entity.Message;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * 解码数据，保存到Message中
 */
public class DecodingMessage {
    private InputStream bytes;
    private byte[] msgArr;
    private static final int ZERO = 0;
    private static final int FOUR = 4;
    private static final int TWELVE = 12;
    private static final int TWENTY = 20;
    private static final int TWENTY_ONE = 21;


    public DecodingMessage(InputStream bytes) {
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
        rangeCheck(byteArr, Header.HEADER_SIZE, true);

        Header header = new Header();

        try {
            //[0, 4)
            header.setPayloadSize(Convert.bytesToInt(byteArr, ZERO));
            //[4, 12)
            header.setSender(Convert.bytesToLong(byteArr, FOUR));
            //[12, 20)
            header.setReceiver(Convert.bytesToLong(byteArr, TWELVE));
            //[20, 21)
            header.setType(byteArr[TWENTY]);
            //[21, 22)
            //与Code中的枚举进行比较
            header.setStatus(byteArr[TWENTY_ONE]);

            return header;
        } catch (IOException e) {
            System.out.println("创建Header对象错误");
            e.printStackTrace();
        }
        return null;

    }

    private Info createInfo(byte[] byteArr) {
        int offset = 0;
        Info info = new Info();
        try {
            while(byteArr.length > 0) {
                short[] sizes = rangeCheck(byteArr, Convert.SHORT_SIZE, false);
                if(sizes == null || sizes.length == 0) {
                    return null;
                }
                offset += Convert.SHORT_SIZE;
                String keyStr = Convert.bytesToString(byteArr,
                        offset, offset + sizes[0]);
                offset += sizes[0];
                String valueStr = Convert.bytesToString(byteArr, offset, offset + sizes[1]);
                offset += sizes[1];
                byteArr = Arrays.copyOfRange(byteArr, offset, byteArr.length);
                info.add(keyStr, valueStr);
            }
            return info;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private short[] rangeCheck(byte[] byteArr, int size, boolean isHeader) {
        if(byteArr.length < size) {
            throw new RuntimeException("解码数据格式错误");
        } else if(isHeader) {
            return null;
        }
        short[] sizes = getSize(byteArr);
        int length = sizes[0] + sizes[1];
        if(sizes[0] > 64 || sizes[1] > 1024 || byteArr.length - 2 < length) {
            throw new RuntimeException("解码数据格式错误");
        }

        return sizes;
    }

    /**
     * 获取keySize和valueSize的值
     * keySize：6位
     * valueSize：10位
     */
    private short[] getSize(byte[] byteArr) {
        byteArr = Arrays.copyOf(byteArr, Convert.SHORT_SIZE);
        short[] sizes = new short[2];
        sizes[0] = (short) (byteArr[0] >>> 2 & 0x3f);
        sizes[1] = (short) ((byteArr[1] & 0xff) + ((byteArr[0] & 0x03) << 8));
        return sizes;
    }

    private void close() {
        IOutils.close(bytes);
    }
}
