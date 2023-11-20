package com.lp.web;

import com.google.zxing.WriterException;
import com.lp.domain.model.*;
import com.lp.domain.service.QrCodeService;
import com.lp.domain.service.UserService;
import com.lp.web.dto.*;
import com.lp.web.dto.mappers.UserDtoMapper;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@Log
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

    @GetMapping("/roles")
    Iterable<Role> getUserRolesResponse() {
        return userService.getAllRoles();
    }

    @GetMapping("/statuses")
    Iterable<Status> getUserStatusesResponse() {
        return userService.getAllStatuses();
    }

    @GetMapping("/providers")
    Iterable<SocialProviderEnum> getUserLoginProvidersResponse() {
        return userService.getAllProviders();
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
        Optional<User> optionalUser = userService.findById(uuid);

        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User [" + uuid + "] not found");
        }

        return userDtoMapper.mapUserToDto(
                optionalUser.get()
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
        Optional<User> optionalUser = userService.findById(uuid);

        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User [" + uuid + "] not found");
        }

        return userDtoMapper.mapUserToDto(
                userService.saveUser(
                        userDtoMapper.mapUpdateDtoToUser(
                                optionalUser.get(),
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
        Optional<User> optionalUser = userService.findById(uuid);

        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User [" + uuid + "] not found");
        }

        String text = "uuid=" + optionalUser.get().getUuid() + "&"
                + "name=" + URLEncoder.encode(
                optionalUser.get().getName(),
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
