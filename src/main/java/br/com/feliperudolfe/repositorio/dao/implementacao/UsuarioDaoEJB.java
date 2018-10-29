package br.com.feliperudolfe.repositorio.dao.implementacao;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.feliperudolfe.comum.repositorio.DAOGenerico;
import br.com.feliperudolfe.modelo.entidade.Usuario;
import br.com.feliperudolfe.repositorio.dao.UsuarioDAO;
import br.com.feliperudolfe.repositorio.query.UsuarioQuery;

/**
 * @author Felipe Rudolfe
 *
 */
@Stateless
public class UsuarioDaoEJB extends DAOGenerico<Long, Usuario> implements UsuarioDAO {

	public UsuarioDaoEJB() {
		super(Usuario.class);
	}

	@Override
	public Usuario buscarPorLoginAndPass(String login, String pass) {

		Query query = this.getEM().createQuery(UsuarioQuery.BUSCAR_POR_LOGIN_AND_PASS);
		query.setParameter("login", login);
		query.setParameter("senha", pass);

		return this.executarQuerySimples(query);
	}// buscarPorLoginAndPass()

	@Override
	public Usuario buscarPorLogin(String login) {

		Query query = this.getEM().createQuery(UsuarioQuery.BUSCAR_POR_LOGIN);
		query.setParameter("login", login);

		return this.executarQuerySimples(query);
	}// buscarPorLogin()


	@Override
	public boolean isExisteUsuarioComLogin(String login) {
		return buscarPorLogin(login) != null ? Boolean.TRUE : Boolean.FALSE;
	}

}