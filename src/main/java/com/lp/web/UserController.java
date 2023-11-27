package com.lp.web;

import com.google.zxing.WriterException;
import com.lp.domain.model.*;
import com.lp.domain.service.QrCodeService;
import com.lp.domain.service.UserService;
import com.lp.web.dto.*;
import com.lp.web.dto.mappers.UserDtoMapper;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final UserDtoMapper userDtoMapper;

    private final QrCodeService qrCodeService;

    private final QrCodeDtoMapper qrCodeDtoMapper;

    public UserController(
            UserService userService,
            QrCodeService qrCodeService,
            UserDtoMapper dtoMapper,
            QrCodeDtoMapper qrCodeDtoMapper
    ) {
        this.userService = userService;
        this.userDtoMapper = dtoMapper;
        this.qrCodeService = qrCodeService;
        this.qrCodeDtoMapper = qrCodeDtoMapper;
    }

    @GetMapping("/list")
    PageDto<ResponseUserDto> getUserListResponse(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") SortDirectionEnum sortDirection) {

        return userDtoMapper.mapPageToDto(
                userService.getPagedUsers(
                        pageNumber,
                        pageSize,
                        sortBy,
                        sortDirection.name()
                )
        );
    }

    @GetMapping("/{uuid}")
    ResponseUserDto getUserResponse(
            @PathVariable("uuid") UUID uuid
    ) {

        return userDtoMapper.mapUserToDto(
                userService.findById(uuid)
                        .orElseThrow(() -> new IllegalArgumentException("User [" + uuid + "] not found"))
        );
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseUserDto postUserResponse(
            @Valid @RequestBody RequestCreateUserDto userDto
    ) {
        return userDtoMapper.mapUserToDto(
                userService.saveUser(
                        userDtoMapper.mapCreateDtoToUser(
                                new User(),
                                userDto
                        )
                )
        );
    }

    @PutMapping("{uuid}")
    ResponseUserDto putUserResponse(
            @PathVariable("uuid") UUID uuid,
            @Valid @RequestBody RequestUpdateUserDto userDto
    ) {

        return userDtoMapper.mapUserToDto(
                userService.saveUser(
                        userDtoMapper.mapUpdateDtoToUser(
                                userService.findById(uuid)
                                        .orElseThrow(() -> new IllegalArgumentException("User [" + uuid + "] not found")),
                                userDto
                        )
                )
        );
    }

    @GetMapping("{uuid}/qrcode/")
    public ResponseEntity<byte[]> getQrCode(
            @PathVariable("uuid") UUID uuid,
            @RequestParam(defaultValue = "250", required = false) int width,
            @RequestParam(defaultValue = "250", required = false) int height,
//            @RequestParam(defaultValue = "QR_CODE", required = false) BarcodeFormat barcodeFormat,
            @RequestParam(defaultValue = "png", required = false) ImageFormatDto imageFormat
    ) throws WriterException, IOException {
        User user = userService.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("User [" + uuid + "] not found"));

        String text = "uuid=" + user.getUuid() + "&"
                + "name=" + URLEncoder.encode(
                user.getName(),
                StandardCharsets.UTF_8
        );

        return ResponseEntity.ok()
                .contentType(qrCodeDtoMapper.mapFormatToMediaType(imageFormat))
                .body(
                        qrCodeDtoMapper.mapMatrixToByte(
                                qrCodeService.generateQrCode(text, width, height),
                                imageFormat
                        )
                );
    }
}
