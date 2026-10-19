package code.cells.InstaRama.com.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest (
        @NotBlank(message = "El nombre es obligatorio")
        String name,
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email no tiene un formato válido")
        String email,
        @NotBlank(message = "La contraseña es obligatoria")
        String password
) {
}
