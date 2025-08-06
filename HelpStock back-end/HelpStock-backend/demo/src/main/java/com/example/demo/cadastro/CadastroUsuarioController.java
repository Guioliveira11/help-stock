package com.example.demo.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
@CrossOrigin(origins = "http://localhost:3000")
public class CadastroUsuarioController {

    
    @Autowired
    private  CadastroUsuarioService service;
    
    
    @Autowired
    private CadastroUsuarioRepository cadastrousuarioRepository;
    
    

    @Autowired
    public CadastroUsuarioController(CadastroUsuarioService service) {
        this.service = service;
    }
    
    @PostMapping
    public CadastroUsuario salvar(@RequestBody CadastroUsuario cadastro) {
        System.out.println("Recebido do React:");
        System.out.println("Nome: " + cadastro.getNome());
        System.out.println("Login: " + cadastro.getLogin());
        System.out.println("Senha: " + cadastro.getSenha()); 
        return service.salvar(cadastro);
    }
	
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<CadastroUsuario> usuarios = service.listarTodos();
            System.out.println(usuarios);
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(500).body("Erro ao buscar usuarios: " + e.getMessage());
        }
    }
    

    
	
}
