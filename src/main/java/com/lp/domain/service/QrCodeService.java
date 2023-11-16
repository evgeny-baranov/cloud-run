package com.lp.domain.service;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

@Service
public interface QrCodeService {

    BitMatrix generateQrCode(
            String text,
            int width,
            int height
    ) throws WriterException;
}
