package br.com.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.socialbooks.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long> {

}
