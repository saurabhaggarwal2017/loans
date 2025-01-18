package com.eazybytes.loan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Schema
@Data
@AllArgsConstructor
public class ErrorResponseDto {
    private String apiPath;
    private String statusCode;
    private String statusMessage;
    private LocalDateTime errorTime;
}
