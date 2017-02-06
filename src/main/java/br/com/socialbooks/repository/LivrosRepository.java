package br.com.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.socialbooks.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{

}
