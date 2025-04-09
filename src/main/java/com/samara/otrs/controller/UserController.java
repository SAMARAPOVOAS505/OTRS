package com.samara.otrs.controller;

import java.util.List;
import java.util.Optional;

import com.samara.otrs.dto.LoginDTO;
import com.samara.otrs.model.User;
import com.samara.otrs.repository.UserRepository;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        // Criptografa antes de salvar (só se não estiver fazendo isso no service)
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        Optional<User> userOpt = userRepository.findByLogin(loginDTO.getLogin());

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (passwordEncoder.matches(loginDTO.getSenha(), user.getSenha())) {
                return ResponseEntity.ok("Login realizado com sucesso! Bem-vindo, " + user.getName());
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou senha inválidos");
    }

    
    @PutMapping("/{id}")
public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
    Optional<User> optionalUser = userRepository.findById(id);

    if (optionalUser.isPresent()) {
        User existingUser = optionalUser.get();
        existingUser.setName(updatedUser.getName());
        existingUser.setLogin(updatedUser.getLogin());

        // Só atualiza a senha se for diferente (pra não recriptografar igual)
        if (!passwordEncoder.matches(updatedUser.getSenha(), existingUser.getSenha())) {
            existingUser.setSenha(passwordEncoder.encode(updatedUser.getSenha()));
        }

        return ResponseEntity.ok(userRepository.save(existingUser));
    }

    return ResponseEntity.notFound().build();
}
}