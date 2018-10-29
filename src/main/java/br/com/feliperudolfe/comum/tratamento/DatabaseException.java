package br.com.feliperudolfe.comum.tratamento;

import java.util.ArrayList;
import java.util.List;

import br.com.feliperudolfe.comum.modelo.dto.Mensagem;

/**
 * @author Felipe Rudolfe
 *
 */
public class DatabaseException extends NegocioException {

	/**
	 *
	 */
	private static final long serialVersionUID = 7689779789564172513L;

	private List<Mensagem> mensagens;

	public DatabaseException(String tipo, String texto) {
		this.mensagens = new ArrayList<Mensagem>();
		this.mensagens.add(new Mensagem(tipo, texto));
	}// NegocioException()

	public DatabaseException(String tipo, List<String> mensagens) {
		this.mensagens = new ArrayList<Mensagem>();
		for (String texto : mensagens) {
			this.mensagens.add(new Mensagem(tipo, texto));
		} // for
	}// NegocioException()

	public DatabaseException(DatabaseException e) {
		this.mensagens = e.getMensagens();
	}// NegocioException()

	public DatabaseException(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}// NegocioException()

	public DatabaseException(Mensagem Mensagem) {
		this.mensagens = new ArrayList<Mensagem>();
		this.mensagens.add(Mensagem);
	}// NegocioException()

	public DatabaseException(String... mensagens) {
		this.mensagens = new ArrayList<Mensagem>();
		for (String texto : mensagens) {
			this.mensagens.add(new Mensagem(Mensagem.ERRO, texto));
		} // for
	}// NegocioException()

	public List<Mensagem> getMensagens() {
		return mensagens;
	}// getMensagens()

	@Override
	public String getMessage() {
		StringBuilder retorno = new StringBuilder();
		String mensagem = super.getMessage();
		if (mensagem != null && !mensagem.isEmpty()) {
			retorno.append(mensagem);
		} // if
		for (Mensagem Mensagem : this.mensagens) {
			retorno.append((retorno.toString() == null || retorno.toString().isEmpty()) ? "" : "\n").append(Mensagem.toString());
		} // for
		return retorno.toString();
	}// getMessage()

	@Override
	public String toString() {
		return super.toString() + getMessage();
	}// toString()

}