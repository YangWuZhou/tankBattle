package com.sourcey.footBook.utils;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class IOutils {

    public static void close(Closeable... streams) {
        for(Closeable stream : streams) {
            if(stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
