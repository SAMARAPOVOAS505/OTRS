package com.samara.otrs.service;

import com.samara.otrs.model.User;
import com.samara.otrs.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Garante que a senha será salva criptografada

    public User createUser(User user) {
        // Criptografar a senha antes de salvar
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
    }

    public Optional<User> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);

        existingUser.setName(updatedUser.getName());
        existingUser.setLogin(updatedUser.getLogin());
        existingUser.setRole(updatedUser.getRole());

        // Atualiza a senha, se fornecida
        if (updatedUser.getSenha() != null && !updatedUser.getSenha().isBlank()) {
            existingUser.setSenha(passwordEncoder.encode(updatedUser.getSenha()));
        }

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado para exclusão");
        }
        userRepository.deleteById(id);
    }
}
