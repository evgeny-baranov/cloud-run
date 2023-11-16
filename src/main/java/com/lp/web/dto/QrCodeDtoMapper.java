package com.lp.web.dto;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.lp.domain.service.MatrixToSvgWriter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class QrCodeDtoMapper {

    public byte[] mapMatrixToByte(BitMatrix bitMatrix, ImageFormatDto format) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        if ("svg".equalsIgnoreCase(format.name())) {
            MatrixToSvgWriter.writeToStream(bitMatrix, "SVG", outputStream);

        } else {
            MatrixToImageWriter.writeToStream(bitMatrix, format.name(), outputStream);
        }

        return outputStream.toByteArray();
    }

    public MediaType mapFormatToMediaType(ImageFormatDto format) {
        if (ImageFormatDto.svg == format) {
            return MediaType.valueOf("image/svg+xml");
        } else {
            return MediaType.valueOf("image/" + format);
        }
    }
}
