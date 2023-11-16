package com.lp.domain.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

@Service
public class QrCodeServiceImpl implements QrCodeService {

    private final QRCodeWriter qrCodeWriter;

    public QrCodeServiceImpl() {
        qrCodeWriter = new QRCodeWriter();
    }

    public BitMatrix generateQrCode(
            String text,
            int width,
            int height
    ) throws WriterException {
        return generateBarCode(
                text,
                width,
                height,
                BarcodeFormat.QR_CODE
        );
    }

    private BitMatrix generateBarCode(
            String text,
            int width,
            int height,
            BarcodeFormat format
    ) throws WriterException {
        return qrCodeWriter.encode(
                text,
                format,
                width,
                height
        );
    }
}
