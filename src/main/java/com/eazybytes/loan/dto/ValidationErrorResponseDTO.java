package com.eazybytes.loan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
@Schema
@Data
@AllArgsConstructor
public class ValidationErrorResponseDTO {
    private String apiPath;
    private String statusCode;
    private String errorMessage;
    private int numberOfErrors;
    private Map<String, String> errors;
}
