package code.cells.InstaRama.com.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record PostRequest(
        @NotBlank
        String title,

        @NotBlank
        byte[] photo,

        @NotBlank
        String description,

        Date datetime
        @NotNull
        Long kingdomId
) {
}
