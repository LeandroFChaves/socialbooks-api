package br.com.socialbooks.services.exceptions;

public class RegistroExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RegistroExistenteException(String mensagem) {
		// Chama o construtor da classe pai (RuntimeException).
		super(mensagem);
	}

	public RegistroExistenteException(String mensagem, Throwable causa) {
		// Chama o construtor da classe pai (RuntimeException).
		super(mensagem, causa);
	}
}