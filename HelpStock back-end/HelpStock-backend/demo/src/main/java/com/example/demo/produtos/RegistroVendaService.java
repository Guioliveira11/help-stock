package com.example.demo.produtos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroVendaService {
	
	@Autowired
    private RegistroVendaRepository registrovendarepository;
	
	 @Autowired
	 private ProdutoRepository produtoRepository; 
	 

	public RegistroVendas registrarVenda(Long produtoId, int quantidadeVendida) {
        Produto produto = produtoRepository.findById(produtoId)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (produto.getQuantidade() < quantidadeVendida) {
            throw new RuntimeException("Estoque insuficiente");
        }

        produto.setQuantidade(produto.getQuantidade() - quantidadeVendida);
        produtoRepository.save(produto);

        RegistroVendas venda = new RegistroVendas();
        venda.setProduto(produto);
        venda.setQuantidadeVenda(quantidadeVendida);

        return registrovendarepository.save(venda);
	}


	 public List<RegistroVendas> listarTodos() {
		return registrovendarepository.findAll();
	}

}
