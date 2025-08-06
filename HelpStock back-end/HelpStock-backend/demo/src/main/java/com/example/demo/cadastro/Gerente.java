package com.example.demo.cadastro;

public class Gerente extends Funcionario {
    private String setor;

    // Construtor privado
    protected Gerente(String nome, String email,String senha) {
        super(nome, email, senha);
        this.setor = setor;
    }

    // Funcionalidades exclusivas do gerente
    public void deletarProduto(int produtoId) {
        System.out.println("Produto " + produtoId + " deletado pelo gerente " + getNome());
    }

    public void gerarRelatorio() {
        System.out.println("Relatório de estoque gerado por " + getNome());
    }
}