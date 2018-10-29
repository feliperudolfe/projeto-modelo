package br.com.feliperudolfe.modelo.integracao.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.feliperudolfe.modelo.comum.seguranca.anotacao.Autenticacao;
import br.com.feliperudolfe.modelo.comum.seguranca.anotacao.Autorizacao;

/**
 * @author Felipe Rudolfe
 *
 */
@Path(value = "/hello-world")
public class HelloWorldWS extends WebService {

	@GET
	@Autenticacao
	@Autorizacao(permissoes = { "COMUM" })
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String index() {
		String nome = getUsuarioPrincipal().getName();
		if (nome == null) {
			nome = "Anonymus";
		} else {
			nome = nome.trim();
		} // if-else
		return "Hello, " + nome;
	}// nome()

}