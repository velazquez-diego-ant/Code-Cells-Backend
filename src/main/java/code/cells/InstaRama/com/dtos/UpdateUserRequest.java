package code.cells.InstaRama.com.dtos;

import jakarta.validation.constraints.Email;

public record UpdateUserRequest (
        String name,
        @Email(message = "El email no tiene un formato válido")
        String email,
        String password,
        Integer accountStatus,
        String role
) {
}
