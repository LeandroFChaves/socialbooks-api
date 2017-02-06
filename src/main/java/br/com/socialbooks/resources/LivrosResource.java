package br.com.socialbooks.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.socialbooks.domain.Livro;
import br.com.socialbooks.service.LivrosService;

@RestController
@RequestMapping(value = "/livros")
public class LivrosResource {

	@Autowired
	private LivrosService livrosService;

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Livro>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro) {
		livrosService.salvar(livro);

		// Retorna após o post como o cliente pode acessar esse recurso que ele
		// acabou de criar. Essa informação vai no Header da resposta.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		Livro livro = livrosService.buscar(id);
		
		// Determina o tempo que essa informação ficará disponível no cache (tempo de expiração).
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);

		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livro);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		livrosService.deletar(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody Livro livro) {
		// Não é obrigatório, mas é feito para garantir que o objeto atualizado
		// realmente é o que vem na URI.
		livro.setId(id);
		livrosService.atualizar(livro);

		return ResponseEntity.noContent().build();
	}
}