package br.com.socialbooks.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.socialbooks.domain.Comentario;
import br.com.socialbooks.service.ComentariosService;

@RestController
@RequestMapping(value = "/comentarios")
public class ComentariosResource {

	@Autowired
	private ComentariosService comentariosService;

	@RequestMapping(value = "/{id}/comentarios", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionarComentario(@Valid @PathVariable("id") Long idLivro,
			@RequestBody Comentario comentario) {
		
		// Captura o usuário logado na seção.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		comentario.setUsuario(auth.getName());
		comentariosService.salvarComentario(idLivro, comentario);

		// Retorna após o post como o cliente pode acessar esse recurso que ele
		// acabou de criar. Essa informação vai no Header da resposta. Acessa
		// todos os comentários.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}/comentarios", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable("id") Long idLivro) {
		List<Comentario> comentarios = comentariosService.listarComentariosPorLivro(idLivro);

		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}
}