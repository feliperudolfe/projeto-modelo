package br.com.feliperudolfe.comum.seguranca;

import java.security.Principal;
import java.util.List;

import javax.ws.rs.core.SecurityContext;

import br.com.feliperudolfe.modelo.entidade.Permissao;
import br.com.feliperudolfe.modelo.entidade.Usuario;

/**
 * @author Felipe Rudolfe
 *
 */
public class ApplicationSecurityContext implements SecurityContext {

	private static final String SCHEMA_HTTPS = "https";

	private Usuario user;
	private String scheme;

	public ApplicationSecurityContext(Usuario user, String scheme) {
        this.user = user;
        this.scheme = scheme;
    }// ApplicationSecurityContext()

	@Override
	public Principal getUserPrincipal() {
		return this.user;
	}// getUserPrincipal()

	@Override
	public boolean isUserInRole(String role) {

		Boolean retorno = Boolean.FALSE;
		List<Permissao> permissoes = getPermissoes();
		if (role != null && !role.isEmpty() && permissoes != null && !permissoes.isEmpty()) {
			for (Permissao permissao : permissoes) {
				if (role.equalsIgnoreCase(permissao.getCodigo())) {
					retorno = Boolean.TRUE;
					break;
				}// if
			}// for
		}// if

		return retorno;
	}// isUserInRole()

	@Override
	public boolean isSecure() {
		return SCHEMA_HTTPS.equals(this.scheme);
	}// isSecure()

	@Override
	public String getAuthenticationScheme() {
		return SecurityContext.BASIC_AUTH;
	}// getAuthenticationScheme()

	private List<Permissao> getPermissoes() {
		return (user.getPerfil() == null) ? null : user.getPerfil().getPermissoes();
	}// getPermissoes()

}