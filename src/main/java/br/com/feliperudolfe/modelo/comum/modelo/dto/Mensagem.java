package br.com.feliperudolfe.modelo.comum.modelo.dto;

/**
 * @author Felipe Rudolfe
 *
 */
public class Mensagem {

	public static final String ALERTA = "warning";
	public static final String ERRO = "error";
	public static final String INFORMACAO = "info";
	public static final String SUCESSO = "success";
	public static final String QUESTAO = "question";

	private String tipo;
	private String texto;

	public Mensagem(String tipo, String texto) {
		this.tipo = tipo;
		this.texto = texto;
	}// Mensagem()

	public String getTipo() {
		return tipo;
	}// getTipo()

	public String getTexto() {
		return texto;
	}// getTexto()

	@Override
	public String toString() {
		return "mensagem : {tipo : '" + this.tipo + "', texto : '" + this.texto + "'}";
	}// toString()
}