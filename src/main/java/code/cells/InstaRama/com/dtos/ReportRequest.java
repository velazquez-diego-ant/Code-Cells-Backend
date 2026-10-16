package code.cells.InstaRama.com.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ReportRequest(
        @NotBlank
        @Pattern(regexp = "POST|COMMENT", message = "entityType must be POST or COMMENT")
        String entityType,   //? qué se reporta
        @NotNull
        Long entityId,       //? id del post o comentario: sirve para validar y armar el link, no se guarda como columna
        @NotBlank
        String type,         //? motivo
        String status) {     //? solo se usa en el PUT
}