package com.example.demo.cadastro;

public class Funcionario extends CadastroUsuario {
	
	protected Funcionario(String nome, String email, String senha) {
        super(nome, email, senha);
    }
	
    @Override
    public void acessarSistema() {
        System.out.println("Funcionário acessando funcionalidades básicas do sistema.");
    }

    public void registrarEntrada() {
        System.out.println("Funcionário registrou entrada.");
    }
    




}
