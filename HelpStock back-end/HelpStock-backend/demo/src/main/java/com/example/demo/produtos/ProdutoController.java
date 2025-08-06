package com.example.demo.produtos;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProdutoController {


    public ProdutoController(ProdutoService service) {
        this.service = service;
    }
    
    @Autowired
    private ProdutoService service;
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Produto> produtos = service.listarTodos();
            return ResponseEntity.ok(produtos);
        } catch (Exception e) {
            e.printStackTrace(); // vai mostrar o erro no console
            return ResponseEntity.status(500).body("Erro ao buscar produtos: " + e.getMessage());
        }
    }
    
    
    
    @PutMapping("/{id}/reabastecer")
    public ResponseEntity<?> reabastecer(@PathVariable Long id, @RequestParam int quantidade) {
    	try {
    		service.reabastecerProduto(id, quantidade);
    		return ResponseEntity.ok().build();
    	} catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao reabastecer produto: " + e.getMessage());
        }
    }


    @PutMapping("/{id}/vendidos")
    public ResponseEntity<?> venderProduto(@PathVariable Long id, @RequestParam int quantidade) {
        try {
            service.venderProduto(id, quantidade);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Quantidade inválida: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao vender produto: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return ResponseEntity.ok("Produto deletado com sucesso.");
        }
        return ResponseEntity.notFound().build();
    } 

    		
    @GetMapping("/buscar")
    public ResponseEntity<List<Produto>> buscar(@RequestParam String termo) {
        List<Produto> resultados = produtoRepository.findByNomeContainingIgnoreCase(termo);
        return ResponseEntity.ok(resultados);
    }
    
    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        System.out.println("Recebido do React:");
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Preço: " + produto.getPreco());
        System.out.println("Quantidade: " + produto.getQuantidade()); 
        return service.salvar(produto);
    }
}

