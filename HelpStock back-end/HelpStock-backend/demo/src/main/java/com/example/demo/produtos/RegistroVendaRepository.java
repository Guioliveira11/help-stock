package com.example.demo.produtos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroVendaRepository extends JpaRepository<RegistroVendas, Long> {

}
