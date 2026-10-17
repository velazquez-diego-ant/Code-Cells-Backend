package code.cells.InstaRama.com.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record KingdomRequest(

        @NotBlank(message = "Kingdom is required")
        @Size(max = 50, message = "Kingdom must not exceed 50 characters")
        String kingdom,

        @NotBlank(message = "SubKingdom is required")
        @Size(max = 100, message = "SubKingdom must not exceed 100 characters")
        String subKingdom

) {
}