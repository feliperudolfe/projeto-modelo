package br.com.feliperudolfe.modelo.integracao.ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import br.com.feliperudolfe.modelo.comum.integracao.ResponseMapper;
import br.com.feliperudolfe.modelo.modelo.entidade.Usuario;

/**
 * @author Felipe Rudolfe
 *
 */
public abstract class WebService extends ResponseMapper {

	@Context
	private SecurityContext context;

	protected Usuario getUsuarioPrincipal() {
		return (Usuario) context.getUserPrincipal();
	}

}