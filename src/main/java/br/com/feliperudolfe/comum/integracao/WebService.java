package br.com.feliperudolfe.comum.integracao;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import br.com.feliperudolfe.comum.integracao.ResponseMapper;
import br.com.feliperudolfe.modelo.entidade.Usuario;

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