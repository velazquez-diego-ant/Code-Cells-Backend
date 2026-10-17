package code.cells.InstaRama.com.services;

import code.cells.InstaRama.com.dtos.ContactRequestDTO;
import code.cells.InstaRama.com.exceptions.ResourceNotFoundException;
import code.cells.InstaRama.com.models.ContactForm;
import code.cells.InstaRama.com.models.User;
import code.cells.InstaRama.com.repository.ContactFormRepository;
import code.cells.InstaRama.com.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactFormService {

    private final ContactFormRepository contactFormRepository;
    private final UserRepository userRepository;

    public List<ContactForm> getAllContacts() {
        return contactFormRepository.findAll();
    }

    public ContactForm getContactById(Integer id) {
        return contactFormRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact message not found with id: " + id));
    }

    public ContactForm sendContactMessage(ContactRequestDTO request) {
        // *Buscamos al usuario usando el Long del DTO /
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not registered in english"));

        ContactForm contactForm = ContactForm.builder()
                .characterType(request.getCharacterType())
                .message(request.getMessage())
                .email(request.getEmail())
                .user(user) 
                .build();

        return contactFormRepository.save(contactForm);
    }

    public void deleteContact(Integer id) {
        if (!contactFormRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contact message not found with id: " + id);
        }
        contactFormRepository.deleteById(id);
    }
}