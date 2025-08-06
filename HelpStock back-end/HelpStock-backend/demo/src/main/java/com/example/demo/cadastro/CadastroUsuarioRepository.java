package com.example.demo.cadastro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroUsuarioRepository extends JpaRepository<CadastroUsuario, Long>  {

	List<CadastroUsuario> findByNomeContainingIgnoreCase(String nome);
}
