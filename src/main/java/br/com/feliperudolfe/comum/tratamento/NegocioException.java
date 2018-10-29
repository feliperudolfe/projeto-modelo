package br.com.feliperudolfe.comum.tratamento;

import java.util.ArrayList;
import java.util.List;

import br.com.feliperudolfe.comum.modelo.dto.Mensagem;

/**
 * @author Felipe Rudolfe
 *
 */
public class NegocioException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -8801966228389990582L;

	private List<Mensagem> mensagens;

	public NegocioException(String tipo, String texto) {
		this.mensagens = new ArrayList<Mensagem>();
		this.mensagens.add(new Mensagem(tipo, texto));
	}// NegocioException()

	public NegocioException(String tipo, List<String> mensagens) {
		this.mensagens = new ArrayList<Mensagem>();
		for (String texto : mensagens) {
			this.mensagens.add(new Mensagem(tipo, texto));
		} // for
	}// NegocioException()

	public NegocioException(NegocioException e) {
		this.mensagens = e.getMensagens();
	}// NegocioException()

	public NegocioException(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}// NegocioException()

	public NegocioException(Mensagem Mensagem) {
		this.mensagens = new ArrayList<Mensagem>();
		this.mensagens.add(Mensagem);
	}// NegocioException()

	public NegocioException(String... mensagens) {
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