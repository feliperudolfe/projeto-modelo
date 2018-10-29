package br.com.feliperudolfe.repositorio.dao;

import javax.ejb.Local;

import br.com.feliperudolfe.comum.repositorio.DAO;
import br.com.feliperudolfe.modelo.entidade.Usuario;

/**
 * @author Felipe Rudolfe
 *
 */
@Local
public interface UsuarioDAO extends DAO<Long, Usuario> {

	public Usuario buscarPorLoginAndPass(String login, String pass);

	public Usuario buscarPorLogin(String login);

	public boolean isExisteUsuarioComLogin(String login);

}