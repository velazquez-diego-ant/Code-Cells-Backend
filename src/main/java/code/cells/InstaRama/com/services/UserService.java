package code.cells.InstaRama.com.services;

import lombok.AllArgsConstructor;
import code.cells.InstaRama.com.dtos.LoginRequest;
import code.cells.InstaRama.com.dtos.RegisterRequest;
import code.cells.InstaRama.com.dtos.UpdateUserRequest;
import code.cells.InstaRama.com.exceptions.AccountDisabledException;
import code.cells.InstaRama.com.exceptions.EmailAlreadyExistsException;
import code.cells.InstaRama.com.exceptions.InvalidCredentialsException;
import code.cells.InstaRama.com.exceptions.ResourceNotFoundException;
import code.cells.InstaRama.com.repository.UserRepository;
import org.springframework.stereotype.Service;
import code.cells.InstaRama.com.models.User;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario con el id " + id + " no encontrado")
        );
    }

    public User registerUser(RegisterRequest registerRequest){
        if (userRepository.findByEmail(registerRequest.email()).isPresent()) {
            throw new EmailAlreadyExistsException("Ya existe un usuario con el email " + registerRequest.email());
        }
        User user = new User();
        user.setEmail(registerRequest.email());
        user.setName(registerRequest.name());
        user.setPassword(registerRequest.password());
        user.setAccountStatus(1);
        user.setRole("USER"); 
        
        return userRepository.save(user);
    }

    public User loginUser(LoginRequest loginRequest){
        User user = userRepository.findByEmail(loginRequest.email())
                .filter(u -> u.getPassword().equals(loginRequest.password()))
                .orElseThrow(
                        () -> new InvalidCredentialsException("Email o contraseña incorrectos")
                );
        if (user.getAccountStatus() == 0) {
            throw new AccountDisabledException("La cuenta está desactivada");
        }
        return user;
    }

    public User updateUser(Long id, UpdateUserRequest updateUser){
        User oldUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario con el id " + id + " no encontrado")
        );
        if (updateUser.email() != null && !updateUser.email().equals(oldUser.getEmail())
                && userRepository.findByEmail(updateUser.email()).isPresent()) {
            throw new EmailAlreadyExistsException("Ya existe un usuario con el email " + updateUser.email());
        }
        if (updateUser.name() != null) oldUser.setName(updateUser.name());
        if (updateUser.email() != null) oldUser.setEmail(updateUser.email());
        if (updateUser.password() != null) oldUser.setPassword(updateUser.password());
        if (updateUser.accountStatus() != null) oldUser.setAccountStatus(updateUser.accountStatus());
        if (updateUser.role() != null) oldUser.setRole(updateUser.role());

        return userRepository.save(oldUser);
    }

    public User deleteUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario con el id " + id + " no encontrado")
        );
        userRepository.delete(user);

        return user;
    }
}