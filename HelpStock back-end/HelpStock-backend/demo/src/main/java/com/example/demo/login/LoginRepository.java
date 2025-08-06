package com.example.demo.login;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginUsuario, Long> {

	Optional<LoginUsuario> findByLogin(String login);

}
