package br.com.feliperudolfe.repositorio.query;

/**
 * @author Felipe Rudolfe
 *
 */
public class UsuarioQuery {

	public static final String BUSCAR_POR_LOGIN_AND_PASS = "SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha";

	public static final String BUSCAR_POR_LOGIN = "SELECT u FROM Usuario u WHERE u.login = :login";

}