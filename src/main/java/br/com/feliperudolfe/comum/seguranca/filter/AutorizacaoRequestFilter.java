package br.com.feliperudolfe.comum.seguranca.filter;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import br.com.feliperudolfe.comum.seguranca.anotacao.Autorizacao;

/**
 * @author Felipe Rudolfe
 *
 */
@Provider
@Autorizacao(permissoes = {})
@Priority(Priorities.AUTHORIZATION)
public class AutorizacaoRequestFilter extends AbstractFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		Method metodo = getMetodoInvocado(requestContext);
		if (isRequerAutorizacao(metodo)) {

			SecurityContext context = requestContext.getSecurityContext();
			if (context != null && context.getUserPrincipal() != null) {

				Boolean temPermissao = Boolean.FALSE;
				Autorizacao autorizacao = getAutorizacao(metodo);
				for (String role : autorizacao.permissoes()) {
					if (context.isUserInRole(role)) {
						temPermissao = Boolean.TRUE;
						break;
					}// if
				}// for

				if (!temPermissao) {
					abortarRequisicao(requestContext, Status.UNAUTHORIZED, MSG_USUARIO_NAO_AUTORIZADO);
				} // if

			} else {
				abortarRequisicao(requestContext, Status.UNAUTHORIZED, MSG_SERVICO_PROTEGIDO);
			} // if-else

		} // if

	}// filter()

}