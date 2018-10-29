package br.com.feliperudolfe.comum.integracao;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.feliperudolfe.comum.modelo.dto.Mensagem;
import br.com.feliperudolfe.comum.modelo.dto.Resposta;
import br.com.feliperudolfe.comum.tratamento.NegocioException;

/**
 * @author Felipe Rudolfe
 *
 */
public abstract class ResponseMapper {

	protected Response criarRespostaSucesso(Resposta resposta) {
		return criarResponse(Status.OK, resposta);
	}

	protected Response criarRespostaSucesso(Object data) {
		return criarRespostaSucesso(new Resposta(data));
	}

	protected Response criarRespostaSucesso(Mensagem mensagem) {
		return criarRespostaSucesso(new Resposta(mensagem));
	}

	protected Response criarRespostaSucesso(Object data, Mensagem mensagem) {
		return criarRespostaSucesso(new Resposta(data, mensagem));
	}

	protected Response criarRespostaSucesso(Object data, String tipo, String texto) {
		return criarRespostaSucesso(new Resposta(data, tipo, texto));
	}

	protected Response criarRespostaErro(NegocioException e) {
		return criarResponse(Status.BAD_REQUEST, new Resposta(e));
	}

	protected Response criarRespostaErro(Status status, NegocioException e) {
		return criarResponse(status, new Resposta(e));
	}

	protected Response criarRespostaErro(String msg) {
		return criarRespostaErro(new NegocioException(Mensagem.ERRO, msg));
	}

	protected Response criarRespostaAlerta(NegocioException e) {
		return criarResponse(Status.BAD_REQUEST, new Resposta(e));
	}

	protected Response criarRespostaAlerta(Mensagem ... mensagem) {
		return criarResponse(Status.BAD_REQUEST, new Resposta(mensagem));
	}

	protected Response criarResposta(Status status,  Resposta resposta) {
		return criarResponse(status, resposta);
	}

	protected Response criarResposta(Status status,  Mensagem ... mensagem) {
		return criarResponse(status, new Resposta(mensagem));
	}

	protected Response criarResponse(Status status, Resposta resposta) {
		return Response.status(status)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.header("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia")
				.entity(resposta).build();
	}// criarResponse()

}