package com.example.demo.login;

import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.ResponseEntity;


@Service
public class LoginService {
	
	private final LoginRepository repository;
	
	public LoginUsuario salvar(LoginUsuario login) {
        // Aplica criptografia na senha recebida
        String senhaCriptografada = BCrypt.hashpw(login.getSenha(), BCrypt.gensalt());
        login.setSenha(senhaCriptografada);

        return repository.save(login);
    }
	
	public LoginService (LoginRepository repository) {
		this.repository = repository;
	}

}
