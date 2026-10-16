package code.cells.InstaRama.com.services;

import code.cells.InstaRama.com.dtos.ContactRequestDto;
import code.cells.InstaRama.com.exceptions.ResourceNotFoundException;
import code.cells.InstaRama.com.models.ContactForm;
import code.cells.InstaRama.com.models.User;
import code.cells.InstaRama.com.repository.ContactFormRepository;
import code.cells.InstaRama.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactFormService {

    @Autowired
    private ContactFormRepository contactFormRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ContactForm> getAllContacts() {
        return contactFormRepository.findAll();
    }

public List<ContactForm> getContactsByUserId(Long userId) { 
    if (!userRepository.existsById(userId)) {
        throw new ResourceNotFoundException("User not registered");
    }
    return contactFormRepository.findByUserId(userId);
}

    public ContactForm createContact(ContactRequestDto request) {
        // 1. Buscar Usuario
         User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not registered"));
        // 2. Crear Entidad
        ContactForm contactForm = new ContactForm();
        contactForm.setCharacterType(request.getCharacterType());
        contactForm.setMessage(request.getMessage());
        contactForm.setEmail(request.getEmail());
        contactForm.setUser(user); // Asignamos el objeto User completo

        // 3. Guardar
        return contactFormRepository.save(contactForm);
    }

    public void deleteContact(Integer id) {
        if (!contactFormRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contact form not found with id: " + id);
        }
        contactFormRepository.deleteById(id);
    }
}