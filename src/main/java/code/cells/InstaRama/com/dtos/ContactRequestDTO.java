package code.cells.InstaRama.com.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ContactRequestDTO {

    @NotBlank(message = "El tipo de carácter es obligatorio")
    @Pattern(regexp = "Felicidades|Error|Queja|Sugerencia", message = "El tipo debe ser: Felicidades, Error, Queja o Sugerencia")
    private String characterType;

    @NotBlank(message = "El mensaje no puede estar vacío")
    @Size(max = 250, message = "El mensaje no debe exceder los 250 caracteres")
    private String message;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un correo válido")
    private String email;

    @NotNull(message = "El ID de usuario es obligatorio")
    private Long userId;
}