package code.cells.InstaRama.com.controllers;

import code.cells.InstaRama.com.dtos.ContactRequestDto;
import code.cells.InstaRama.com.models.ContactForm;
import code.cells.InstaRama.com.services.ContactFormService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactFormController {

    @Autowired
    private ContactFormService contactFormService;

    // GET /api/contact/
    @GetMapping("/contact/")
    public ResponseEntity<List<ContactForm>> getAllContacts() {
        return ResponseEntity.ok(contactFormService.getAllContacts());
    }

    // GET /api/users/contact/{id} -> Mostrar mensajes de un usuario
@GetMapping("/users/contact/{id}")
public ResponseEntity<List<ContactForm>> getContactsByUser(@PathVariable Long id) { // Cambiado a Long
    List<ContactForm> contacts = contactFormService.getContactsByUserId(id);
    return ResponseEntity.ok(contacts);
}

    // POST /api/users/contact/send
    @PostMapping("/users/contact/send")
    public ResponseEntity<ContactForm> sendContact(@Valid @RequestBody ContactRequestDto request) {
        ContactForm newContact = contactFormService.createContact(request);
        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    // DELETE /api/users/contact/{id}
    @DeleteMapping("/users/contact/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Integer id) {
        contactFormService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}