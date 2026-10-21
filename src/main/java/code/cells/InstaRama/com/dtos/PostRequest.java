package code.cells.InstaRama.com.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record PostRequest(
        @NotBlank(message = "El título es obligatorio")
        String title,

        @NotNull(message = "La foto es obligatoria")
        @Size(min = 1, message = "La foto es obligatoria")
        byte[] photo,

        @NotBlank(message = "La descripción es obligatoria")
        String description,

        Date datetime
) {
}
