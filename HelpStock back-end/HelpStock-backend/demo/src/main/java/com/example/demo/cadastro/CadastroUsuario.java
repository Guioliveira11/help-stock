package com.example.demo.cadastro;

import org.mindrot.jbcrypt.BCrypt;

import com.example.demo.CriptografiaUtil;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class CadastroUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private String nome, login,senha,tipo;


    public CadastroUsuario() {} 

    public CadastroUsuario(String nome, String login, String senha) {
    	CadastroUsuario usuario;
    	
    	if (tipo.equalsIgnoreCase("gerente")) {
            usuario = new Gerente(nome, login, senha);
        } else {
            usuario = new Funcionario(nome, nome, senha);
        }
    	
    	
        this.nome = nome;
        this.login = login;
        this.senha = BCrypt.hashpw(senha, BCrypt.gensalt());
    }
    
    public boolean verificarSenha(String senhaDigitada) {
        return CriptografiaUtil.verificarSenha(senhaDigitada, this.senha);
    }
    

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

	public void acessarSistema() {
		
	}
}
