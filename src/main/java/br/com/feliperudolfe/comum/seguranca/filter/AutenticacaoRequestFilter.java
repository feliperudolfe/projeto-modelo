package br.com.feliperudolfe.comum.seguranca.filter;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import br.com.feliperudolfe.comum.seguranca.ApplicationSecurityContext;
import br.com.feliperudolfe.comum.seguranca.anotacao.Autenticacao;
import br.com.feliperudolfe.modelo.entidade.Usuario;

/**
 * @author Felipe Rudolfe
 *
 */
@Provider
@Autenticacao
@Priority(Priorities.AUTHENTICATION)
public class AutenticacaoRequestFilter extends AbstractFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		Method metodo = getMetodoInvocado(requestContext);
		if (isRequerAutenticacao(metodo)) {

			String authToken = getAuthToken(requestContext);
			if (authToken != null && !authToken.isEmpty()) {

				Usuario usuario = obterUsuarioAutenticado(authToken);
				if (usuario != null) {

					String scheme = requestContext.getUriInfo().getRequestUri().getScheme();
					requestContext.setSecurityContext(new ApplicationSecurityContext(usuario, scheme));

				} else {
					abortarRequisicao(requestContext, Status.UNAUTHORIZED, MSG_INEXISTENTE_INVALIDO);
				}// if-else

			} else {
				abortarRequisicao(requestContext, Status.UNAUTHORIZED, MSG_SERVICO_PROTEGIDO);
			} // if-else

		} // if

	}// filter()

}