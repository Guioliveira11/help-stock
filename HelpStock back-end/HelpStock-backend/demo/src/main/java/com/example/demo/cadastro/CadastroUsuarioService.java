package com.example.demo.cadastro;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class CadastroUsuarioService {
	
	private final CadastroUsuarioRepository repository;
	
	public CadastroUsuarioService(CadastroUsuarioRepository repository) {
		this.repository = repository;
	}
	
    public CadastroUsuario salvar(CadastroUsuario cadastro) {
        // Aplica criptografia na senha recebida
        String senhaCriptografada = BCrypt.hashpw(cadastro.getSenha(), BCrypt.gensalt());
        cadastro.setSenha(senhaCriptografada);

        return repository.save(cadastro);
    }
	
	public List<CadastroUsuario> listarTodos() {
        return repository.findAll();
    }



        
}
