package com.sourcey.footBook.utils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Convert {
    public static final int BYTE_SIZE = 1;
    public static final int SHORT_SIZE = 2;
    public static final int INT_SIZE = 4;

    public static byte bytesToByte(byte[] bytes, int start) throws IOException {
        bytes = Arrays.copyOfRange(bytes, start, start + BYTE_SIZE);
        ByteArrayInputStream byteArray = new ByteArrayInputStream(bytes);
        DataInputStream data = new DataInputStream(byteArray);
        return data.readByte();
    }

    public static int bytesToInt(byte[] bytes, int start) throws IOException {
        bytes = Arrays.copyOfRange(bytes, start, start + INT_SIZE);
        ByteArrayInputStream byteArray = new ByteArrayInputStream(bytes);
        DataInputStream data = new DataInputStream(byteArray);
        return data.readInt();
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
}
