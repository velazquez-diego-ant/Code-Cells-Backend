package code.cells.InstaRama.com.controllers;

import code.cells.InstaRama.com.dtos.ContactRequestDTO;
import code.cells.InstaRama.com.models.ContactForm;
import code.cells.InstaRama.com.services.ContactFormService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ContactFormController {

    private final ContactFormService contactFormService;

    //* Obtener todos los contactos */
    @GetMapping("/contact")
    public ResponseEntity<List<ContactForm>> getAllContacts() {
        return ResponseEntity.ok(contactFormService.getAllContacts());
    }

    //* Obtener un Contacto en especifico con su ID */
    @GetMapping("/users/contact/{id}")
    public ResponseEntity<ContactForm> getContactById(@PathVariable Integer id) {
        return ResponseEntity.ok(contactFormService.getContactById(id));
    }

    //* Crear un Contacto */
    @PostMapping("/users/contact/send")
    public ResponseEntity<ContactForm> sendContact(@Valid @RequestBody ContactRequestDTO request) {
        ContactForm response = contactFormService.sendContactMessage(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //* Eliminar un Contacto con su ID */
    @DeleteMapping("/users/contact/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Integer id) {
        contactFormService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}