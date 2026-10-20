package code.cells.InstaRama.com.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record PostRequest(
        @NotBlank
        String title,

        @NotNull
        @Size(min = 1)
        byte[] photo,

        @NotBlank
        String description,

        Date datetime
) {
}
