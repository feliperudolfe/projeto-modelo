package br.com.feliperudolfe.comum.seguranca.filter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.StringTokenizer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.DatatypeConverter;

import org.jboss.resteasy.core.ResourceMethodInvoker;

import br.com.feliperudolfe.comum.configuracao.Msg;
import br.com.feliperudolfe.comum.configuracao.MsgRef;
import br.com.feliperudolfe.comum.integracao.ResponseMapper;
import br.com.feliperudolfe.comum.modelo.dto.Mensagem;
import br.com.feliperudolfe.comum.seguranca.anotacao.Autenticacao;
import br.com.feliperudolfe.comum.seguranca.anotacao.Autorizacao;
import br.com.feliperudolfe.comum.tratamento.NegocioException;
import br.com.feliperudolfe.modelo.entidade.Usuario;
import br.com.feliperudolfe.negocio.service.implementacao.UsuarioServiceEJB;

/**
 * @author Felipe Rudolfe
 *
 */
@RequestScoped
public abstract class AbstractFilter extends ResponseMapper {

	public static final String AUTHORRIZATION_HEADER_KEY = "Authorization";
	private static final String AUTH_BASIC_PREFIX = "Basic ";
	private static final String AUTH_SPLIT = ":";
	public static final String METODO_INVOCADO = "org.jboss.resteasy.core.ResourceMethodInvoker";

	protected static final String MSG_SERVICO_PROTEGIDO = Msg.get(MsgRef.MSG_SERVICO_PROTEGIDO);
	protected static final String MSG_INEXISTENTE_INVALIDO = Msg.get(MsgRef.MSG_USUARIO_INEXISTENTE_INVALIDO);
	protected static final String MSG_USUARIO_NAO_AUTORIZADO = Msg.get(MsgRef.MSG_USUARIO_NAO_AUTORIZADO);

	@Inject
	private UsuarioServiceEJB usuarioService;

	protected String getAuthToken(ContainerRequestContext requestContext) {

		String retorno = null;
		if (requestContext.getHeaderString(AUTHORRIZATION_HEADER_KEY) != null
				&& !requestContext.getHeaderString(AUTHORRIZATION_HEADER_KEY).isEmpty()) {
			retorno = requestContext.getHeaderString(AUTHORRIZATION_HEADER_KEY);
		} // if

		return retorno;
	}// getAuthToken()

	protected Usuario obterUsuarioAutenticado(String authToken) throws IOException {

		Usuario usuario = null;
		if (authToken.startsWith(AUTH_BASIC_PREFIX)) {

			authToken = removerPrefixoToken(authToken);
			byte[] decoded = DatatypeConverter.parseBase64Binary(authToken);
			String usernameAndPassword = new String(decoded);

			final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, AUTH_SPLIT);
			final String login = tokenizer.nextToken();
			final String senha = tokenizer.nextToken();

			usuario = this.usuarioService.buscarPorLoginAndPass(login, senha);
		} // if

		return usuario;
	}// obterUsuarioAutenticado()

	protected String removerPrefixoToken(String authToken) {
		return authToken.substring(AUTH_BASIC_PREFIX.length());
	}// removerPrefixoToken()

	protected void abortarRequisicao(ContainerRequestContext requestContext, Status status, String mensagem) {
		Response response = criarRespostaErro(status, new NegocioException(new Mensagem(Mensagem.ALERTA, mensagem)));
		requestContext.abortWith(response);
	}// abortarRequest()

	protected Boolean isRequerAutenticacao(Method metodo) {
		return isMetodoRequerAutenticacao(metodo) | isClasseMetodoRequerAutenticacao(metodo);
	}// isMetodoRequerAutenticacao()

	protected Boolean isMetodoRequerAutenticacao(Method metodo) {
		return metodo.isAnnotationPresent(Autenticacao.class);
	}// isMetodoRequerAutenticacao()

	protected Boolean isClasseMetodoRequerAutenticacao(Method metodo) {
		return metodo.getDeclaringClass().isAnnotationPresent(Autenticacao.class);
	}// isClasseMetodoRequerAutenticacao()

	protected Autorizacao getAutorizacao(Method metodo) {
		Autorizacao retorno = metodo.getAnnotation(Autorizacao.class);
		if (retorno == null) {
			retorno = getAutorizacao(metodo.getDeclaringClass());
		}// if

		return retorno;
	}// getAutorizacao()

	protected Autorizacao getAutorizacao(Class<?> classe) {
		return (Autorizacao) classe.getAnnotation(Autorizacao.class);
	}// getAutorizacao()

	protected Method getMetodoInvocado(ContainerRequestContext requestContext) {
		return ((ResourceMethodInvoker) requestContext.getProperty(METODO_INVOCADO)).getMethod();
	}// getMetodoInvocado()

}