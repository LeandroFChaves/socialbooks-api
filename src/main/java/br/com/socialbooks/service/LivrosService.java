package br.com.socialbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.socialbooks.domain.Livro;
import br.com.socialbooks.repository.LivrosRepository;
import br.com.socialbooks.services.exceptions.RegistroNaoEncontradoException;

@Service
public class LivrosService {

	@Autowired
	private LivrosRepository livrosRepository;

	public List<Livro> listar() {
		return livrosRepository.findAll();
	}

	public Livro buscar(Long id) {
		Livro livro = livrosRepository.findOne(id);

		if (livro == null) {
			throw new RegistroNaoEncontradoException("O livro não encontrado.");
		}

		return livro;
	}

	public Livro salvar(Livro livro) {
		livro.setId(null);

		return livrosRepository.save(livro);
	}

	public void atualizar(Livro livro) {
		verificarExistencia(livro);
		livrosRepository.save(livro);
	}

	private void verificarExistencia(Livro livro) {
		buscar(livro.getId());
	}

	public void deletar(Long id) {
		try {
			livrosRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RegistroNaoEncontradoException("O livro não encontrado.");
		}
	}
}
