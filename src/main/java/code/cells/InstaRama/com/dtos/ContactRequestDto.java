package code.cells.InstaRama.com.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class ContactRequestDto {

    @NotNull(message = "User ID is required")
    private Long userId; // Necesitamos el ID para buscar al usuario

    @NotBlank(message = "Character type is required")
    @Pattern(regexp = "Felicidades|Error|Queja|Sugerencia", 
             message = "Character type must be one of: Felicidades, Error, Queja, Sugerencia")
    private String characterType;

    @NotBlank(message = "Message is required")
    @Size(max = 250, message = "Message cannot exceed 250 characters")
    private String message;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 150)
    private String email;
    
    // El nombre NO se pide aquí porque se obtiene del User
}