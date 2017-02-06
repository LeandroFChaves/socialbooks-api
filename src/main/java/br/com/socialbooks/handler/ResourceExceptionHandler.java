package br.com.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.socialbooks.domain.DetalheErro;
import br.com.socialbooks.services.exceptions.RegistroExistenteException;
import br.com.socialbooks.services.exceptions.RegistroNaoEncontradoException;

@ControllerAdvice // Realiza a interceptação das exceções do nosso código.
public class ResourceExceptionHandler {

	// Qualquer lugar do código que retornar essa exception será tratado aqui.
	@ExceptionHandler(RegistroNaoEncontradoException.class)
	public ResponseEntity<DetalheErro> handlerRegistroNaoEncontrado(RegistroNaoEncontradoException e, HttpServletRequest request) {
		
		DetalheErro erro = new DetalheErro();
		
		erro.setStatus(404l);
		erro.setTitulo(e.getMessage());
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404"); // Informar um portal/página web com todas as infomrações que sua api pode ter com mais detalhes sobre ele. TODO
		erro.setTimeStamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	// Qualquer lugar do código que retornar essa exception será tratado aqui.
	@ExceptionHandler(RegistroExistenteException.class)
	public ResponseEntity<DetalheErro> handlerAutorExistenteException(RegistroExistenteException e, HttpServletRequest request) {
		
		DetalheErro erro = new DetalheErro();
		
		erro.setStatus(409l);
		erro.setTitulo(e.getMessage());
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/409"); // Informar um portal/página web com todas as infomrações que sua api pode ter com mais detalhes sobre ele. TODO
		erro.setTimeStamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	// Qualquer lugar do código que retornar essa exception será tratado aqui.
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalheErro> handlerDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
		
		DetalheErro erro = new DetalheErro();
		
		erro.setStatus(409l);
		erro.setTitulo("Requisição inválida.");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/400"); // Informar um portal/página web com todas as infomrações que sua api pode ter com mais detalhes sobre ele. TODO
		erro.setTimeStamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}	
	
}