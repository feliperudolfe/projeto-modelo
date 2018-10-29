package br.com.feliperudolfe.modelo.comum.modelo.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.feliperudolfe.modelo.comum.tratamento.NegocioException;

/**
 * @author Felipe Rudolfe
 *
 */
public class Resposta {

	private Object data;
	private List<Mensagem> mensagens;

	public Resposta(Object data) {
		this.data = data;
	}// Resposta()

	public Resposta(String tipo, String mensagem) {
		this.mensagens = new ArrayList<Mensagem>();
		this.mensagens.add(new Mensagem(tipo, mensagem));
	}// Resposta()

	public Resposta(Mensagem mensagem) {
		this.mensagens = new ArrayList<Mensagem>();
		this.mensagens.add(mensagem);
	}// Resposta()

	public Resposta(Object data, Mensagem mensagem) {
		this.data = data;
		this.mensagens = new ArrayList<Mensagem>();
		this.mensagens.add(mensagem);
	}// Resposta()

	public Resposta(Mensagem ... mensagem) {
		this.mensagens = new ArrayList<Mensagem>();
		for (Mensagem msg : mensagem) {
			this.mensagens.add(msg);
		}// for
	}// Resposta()

	public Resposta(Object data, Mensagem ... mensagem) {
		this.data = data;
		this.mensagens = new ArrayList<Mensagem>();
		for (Mensagem msg : mensagem) {
			this.mensagens.add(msg);
		}// for
	}// Resposta()

	public Resposta(String tipo, List<String> mensagens) {
		this.mensagens = new ArrayList<Mensagem>();
		for (String mensagem : mensagens) {
			this.mensagens.add(new Mensagem(tipo, mensagem));
		} // for
	}// Resposta()

	public Resposta(Object data, String tipo, List<String> mensagens) {
		this.data = data;
		this.mensagens = new ArrayList<Mensagem>();
		for (String mensagem : mensagens) {
			this.mensagens.add(new Mensagem(tipo, mensagem));
		} // for
	}// Resposta()

	public Resposta(Object data, String tipo, String mensagem) {
		this.data = data;
		this.mensagens = new ArrayList<Mensagem>();
		this.mensagens.add(new Mensagem(tipo, mensagem));
	}// Resposta()

	public Resposta(NegocioException e) {
		this.mensagens = e.getMensagens();
	}// Resposta()

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

}