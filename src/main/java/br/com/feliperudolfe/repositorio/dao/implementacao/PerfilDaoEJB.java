package br.com.feliperudolfe.repositorio.dao.implementacao;

import javax.ejb.Stateless;

import br.com.feliperudolfe.modelo.comum.repositorio.DAOGenerico;
import br.com.feliperudolfe.modelo.modelo.entidade.Perfil;
import br.com.feliperudolfe.repositorio.dao.PerfilDAO;

/**
 * @author Felipe Rudolfe
 *
 */
@Stateless
public class PerfilDaoEJB extends DAOGenerico<Long, Perfil> implements PerfilDAO {

	public PerfilDaoEJB() {
		super(Perfil.class);
	}

}