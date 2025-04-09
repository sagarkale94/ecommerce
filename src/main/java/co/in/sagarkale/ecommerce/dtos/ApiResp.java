package co.in.sagarkale.ecommerce.dtos;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResp<T> {
    @Schema(description = "Indicates if the request was successful", defaultValue = "false")
    private boolean success;

    @Schema(description = "Message describing the result")
    private String message;

    @Schema(description = "Data returned in the response", defaultValue = "null")
    private T data;
}
