package br.com.socialbooks.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.socialbooks.domain.Comentario;
import br.com.socialbooks.domain.Livro;
import br.com.socialbooks.repository.ComentariosRepository;

@Service
public class ComentariosService {
	
	@Autowired
	private LivrosService livrosService;
	
	@Autowired
	private ComentariosRepository comentariosRepository;	
	
	public Comentario salvarComentario(Long idLivro, Comentario comentario){
		Livro livro = livrosService.buscar(idLivro);
		
		comentario.setLivro(livro);
		comentario.setData(new Date());
		
		return comentariosRepository.save(comentario);
	}
	
	public List<Comentario> listarComentariosPorLivro(Long idLivro){
		Livro livro = livrosService.buscar(idLivro);
		
		return livro.getComentarios();
	}

}
