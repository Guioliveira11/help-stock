package com.example.demo.produtos;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }
    
    public void reabastecerProduto(Long id, int quantidade) {
    	 Produto produto = repository.findById(id)
         .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
    	 produto.setQuantidade(produto.getQuantidade() + quantidade);
         repository.save(produto);
    }

    public void venderProduto(Long id, int quantidade) {
        Produto produto = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (quantidade <= 0 || quantidade > produto.getQuantidade()) {
            throw new IllegalArgumentException("Quantidade inválida para venda.");
        }

        produto.setQuantidade(produto.getQuantidade() - quantidade);
        repository.save(produto);
    }
    
    public List<Produto> buscarProdutos(String termo) {
        return repository.findByNomeContainingIgnoreCase(termo);
    }


}
