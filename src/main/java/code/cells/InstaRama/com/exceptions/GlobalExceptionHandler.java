package code.cells.InstaRama.com.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //controlador de excepciones
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Recurso no encontrado");
        problem.setType(URI.create("http://localhost:8080/errors/not-found"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ProblemDetail handleInvalidCredentials(InvalidCredentialsException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        problem.setTitle("Credenciales inválidas");
        problem.setType(URI.create("http://localhost:8080/errors/unauthorized"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(AccountDisabledException.class)
    public ProblemDetail handleAccountDisabled(AccountDisabledException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        problem.setTitle("Cuenta desactivada");
        problem.setType(URI.create("http://localhost:8080/errors/forbidden"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ProblemDetail handleEmailAlreadyExists(EmailAlreadyExistsException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problem.setTitle("Email ya registrado");
        problem.setType(URI.create("http://localhost:8080/errors/conflict"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex){
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errores.put(error.getField(), error.getDefaultMessage())
        );
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Hay datos inválidos en la petición");
        problem.setTitle("Error de validación");
        problem.setType(URI.create("http://localhost:8080/errors/bad-request"));
        problem.setProperty("errores", errores);
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    //Cuando un usuario intenta modificar algo que no es suyo
    @ExceptionHandler(ForbiddenActionException.class)
    public ProblemDetail handleForbiddenAction(ForbiddenActionException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        problem.setTitle("Acción no permitida");
        problem.setType(URI.create("http://localhost:8080/errors/forbidden"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    //Datos inválidos detectados en los servicios (por ejemplo, un entityType desconocido)
    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgument(IllegalArgumentException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setTitle("Petición inválida");
        problem.setType(URI.create("http://localhost:8080/errors/bad-request"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    //Errores de la base de datos: llaves foráneas, campos obligatorios vacíos, etc.
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrity(DataIntegrityViolationException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
                "No se pudo completar la operación porque entra en conflicto con otros datos guardados");
        problem.setTitle("Conflicto con los datos");
        problem.setType(URI.create("http://localhost:8080/errors/conflict"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }
}
