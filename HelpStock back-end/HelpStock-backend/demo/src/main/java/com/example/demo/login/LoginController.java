package com.example.demo.login;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
	
    @Autowired
    private  LoginService service;
    
    @Autowired
    private LoginRepository loginRepository;
    
    @Autowired
    public LoginController(LoginService service) {
        this.service = service;
    }
   

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginUsuario login) {
    	Optional<LoginUsuario> usuarioOpt = loginRepository.findByLogin(login.getLogin()); 
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
        }
        LoginUsuario usuario = usuarioOpt.get();
        boolean senhaCorreta = BCrypt.checkpw(login.getSenha(), usuario.getSenha());
        if (!senhaCorreta) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
        }
        return ResponseEntity.ok("Login bem-sucedido");
    }
    
    
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
       return ResponseEntity.ok("API no ar!");
    }
   

	
}
