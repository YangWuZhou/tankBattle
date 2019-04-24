package com.sourcey.footBook.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Convert {
    public static final int SHORT_SIZE = 2;
    public static final int INT_SIZE = 4;
    public static final int LONG_SIZE = 8;

    public static int bytesToInt(byte[] bytes, int start) throws IOException {
        bytes = Arrays.copyOfRange(bytes, start, start + INT_SIZE);
        ByteArrayInputStream byteArray = new ByteArrayInputStream(bytes);
        DataInputStream data = new DataInputStream(byteArray);
        return data.readInt();
    }

    public static long bytesToLong(byte[] bytes, int start) throws IOException {
        bytes = Arrays.copyOfRange(bytes, start, start + LONG_SIZE);
        ByteArrayInputStream byteArray = new ByteArrayInputStream(bytes);
        DataInputStream data = new DataInputStream(byteArray);
        return data.readLong();
    }

    public static String bytesToString(byte[] bytes, int start, int end) throws IOException {
        if(start == end) {
            return null;
        }
        bytes = Arrays.copyOfRange(bytes, start, end);
        ByteArrayInputStream byteArray = new ByteArrayInputStream(bytes);
        DataInputStream data = new DataInputStream(byteArray);
        return data.readUTF();
    }

    public static byte[] intToBytes(int srcInt) throws IOException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream(INT_SIZE);
        DataOutputStream data = new DataOutputStream(byteArray);
        data.writeInt(srcInt);
        data.flush();
        return byteArray.toByteArray();
    }

    public static byte[] longToBytes(long srcLong) throws IOException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream(LONG_SIZE);
        DataOutputStream data = new DataOutputStream(byteArray);
        data.writeLong(srcLong);
        data.flush();
        return byteArray.toByteArray();
    }
}
