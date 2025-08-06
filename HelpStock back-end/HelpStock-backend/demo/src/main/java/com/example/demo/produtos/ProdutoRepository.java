package com.example.demo.produtos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, Long> {

  List<Produto> findByNomeContainingIgnoreCase(String nome);
  
  List<Produto> findById (long id);
	

}
