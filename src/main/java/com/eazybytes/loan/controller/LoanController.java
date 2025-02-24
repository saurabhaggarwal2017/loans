package com.eazybytes.loan.controller;

import com.eazybytes.loan.custom.annotation.ValidLoanId;
import com.eazybytes.loan.dto.*;
import com.eazybytes.loan.service.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Loan Rest APis",
        description = "Curd API for loan service like generate loan, fetch all loan details, delete loan."
)
@RestController
@RequestMapping(path = "/api/v1/loans", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RefreshScope
public class LoanController {
    private final ILoanService loanService;
    @Value("${build.version}")
    private String version;
    private final Logger logger = LoggerFactory.getLogger(LoanController.class);
    @Autowired
    private LoansContactInfoDto loansContactInfoDto;
    public LoanController(ILoanService loanService) {
        this.loanService = loanService;
    }

    @Operation(
            method = "POST",
            description = "Generate Loan for customers.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Http Status OK"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Http Status Bad Request",
                            content = @Content(
                                    schema = @Schema(implementation = ValidationErrorResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Http Status Internal Server Error.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }

    )
    @PostMapping
    public ResponseEntity<ResponseDto> createLoan(@Valid @RequestBody LoanDto loanDto) {
        loanService.createLoan(loanDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.toString(), "Customer applied for a loan successfully created."));
    }

    @Operation(
            method = "GET",
            description = "Get Loan details.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Http Status OK"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Http Status Bad Request",
                            content = @Content(
                                    schema = @Schema(implementation = ValidationErrorResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Http Status Internal Server Error.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }

    )
    @GetMapping
    public ResponseEntity<LoanResponseDto> getLoanDetails(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
            @RequestParam("mobileNumber") String mobileNumber,
            @ValidLoanId
            @RequestParam("loanId") long loanId
    ) {
        LoanResponseDto loanResponseDto = loanService.getLoanDetails(mobileNumber, loanId);
        return ResponseEntity.status(HttpStatus.OK).body(loanResponseDto);
    }

    @Operation(
            method = "GET",
            description = "Get all Loan details.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Http Status OK"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Http Status Bad Request",
                            content = @Content(
                                    schema = @Schema(implementation = ValidationErrorResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Http Status Internal Server Error.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }

    )
    @GetMapping("/all")
    public ResponseEntity<List<LoanResponseDto>> getAllLoanDetails(
            @RequestHeader("eazybank-correlation-id") String correlationId,
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
            @RequestParam("mobileNumber") String mobileNumber
    ) {
        logger.debug("eazyBank-correlation-id found: {} ", correlationId);
        List<LoanResponseDto> loanResponseDtoList = loanService.getAllLoanDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loanResponseDtoList);
    }

    @Operation(
            method = "GET",
            description = "Delete Loan details.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Http Status OK"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Http Status Bad Request",
                            content = @Content(
                                    schema = @Schema(implementation = ValidationErrorResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Http Status Internal Server Error.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }

    )
    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteLoan(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
            @RequestParam("mobileNumber") String mobileNumber,
            @ValidLoanId
            @RequestParam("loanId") long loanId
    ) {
        loanService.deleteLoan(mobileNumber, loanId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(), "Loan deleted successfully."));
    }
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(version);
    }

    @GetMapping("/contact-info")
    public ResponseEntity<LoansContactInfoDto> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(loansContactInfoDto);
    }

}
