package code.cells.InstaRama.com.dtos;

import jakarta.validation.constraints.NotBlank;

public record CommentRequest(
        @NotBlank(message = "No se puede enviar un comentario vacío")
        String message
) {
}
