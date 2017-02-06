package br.com.socialbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.socialbooks.domain.Autor;
import br.com.socialbooks.repository.AutoresRepository;
import br.com.socialbooks.services.exceptions.RegistroExistenteException;
import br.com.socialbooks.services.exceptions.RegistroNaoEncontradoException;

@Service
public class AutoresService {
	
	@Autowired
	private AutoresRepository autoresRepository;
	
	public List<Autor> listar(){
		return autoresRepository.findAll(); 
	}
	
	public Autor salvar(Autor autor){
		if (autor.getId() != null) {
			Autor a = autoresRepository.findOne(autor.getId());
			
			if (a != null){
				throw new RegistroExistenteException("O autor já existe.");
			}
		}
		return autoresRepository.save(autor);
	}
	
	public Autor buscar(Long id){
		Autor autor = autoresRepository.findOne(id);
		
		if (autor == null){
			throw new RegistroNaoEncontradoException("Autor não encontrado.");
		}
		return autor;
	}

}
