package br.com.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.socialbooks.domain.Comentario;

public interface ComentariosRepository extends JpaRepository<Comentario, Long> {

}