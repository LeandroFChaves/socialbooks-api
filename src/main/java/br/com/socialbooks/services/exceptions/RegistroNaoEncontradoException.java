package br.com.socialbooks.services.exceptions;

public class RegistroNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RegistroNaoEncontradoException (String mensagem){
		// Chama o construtor da classe pai (RuntimeException).
		super(mensagem);
	}
	
	public RegistroNaoEncontradoException (String mensagem, Throwable causa){
		// Chama o construtor da classe pai (RuntimeException).
		super(mensagem, causa);
	}	
}
