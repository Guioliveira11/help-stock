package com.example.demo.produtos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendas")
public class RegistroVendasController {
	
	@Autowired
	private RegistroVendaService registrovendasservice;

	
    @PostMapping
    public ResponseEntity<?> registrarVenda(@RequestBody VendaDTO dto) {
        try {
        	registrovendasservice.registrarVenda(dto.getProdutoId(), dto.getQuantidadeVendida());
            return ResponseEntity.ok("Venda registrada com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }
    


    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            List<RegistroVendas> vendas = registrovendasservice.listarTodos();
            return ResponseEntity.ok(vendas);
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(500).body("Erro ao buscar vendas: " + e.getMessage());
        }
    }
    
    
    
    
	

}
