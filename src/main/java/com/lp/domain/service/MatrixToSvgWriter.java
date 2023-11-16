package com.lp.domain.service;

import com.google.zxing.common.BitMatrix;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.Writer;

public class MatrixToSvgWriter {

    public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
        try (Writer writer = new OutputStreamWriter(stream)) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.write("<svg xmlns=\"http://www.w3.org/2000/svg\" height=\"" + matrix.getHeight() + "\" width=\"" + matrix.getWidth() + "\">");
            writer.write("<rect width=\"100%\" height=\"100%\" fill=\"#FFFFFF\"/>");
            for (int x = 0; x < matrix.getWidth(); x++) {
                for (int y = 0; y < matrix.getHeight(); y++) {
                    if (matrix.get(x, y)) {
                        writer.write("<rect x=\"" + x + "\" y=\"" + y + "\" width=\"1\" height=\"1\" fill=\"#000000\"/>");
                    }
                }
            }
            writer.write("</svg>");
        }
    }
}