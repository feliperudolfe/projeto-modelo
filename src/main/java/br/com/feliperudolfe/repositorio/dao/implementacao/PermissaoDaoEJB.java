package br.com.feliperudolfe.repositorio.dao.implementacao;

import javax.ejb.Stateless;

import br.com.feliperudolfe.modelo.comum.repositorio.DAOGenerico;
import br.com.feliperudolfe.modelo.modelo.entidade.Permissao;
import br.com.feliperudolfe.repositorio.dao.PermissaoDAO;

/**
 * @author Felipe Rudolfe
 *
 */
@Stateless
public class PermissaoDaoEJB extends DAOGenerico<String, Permissao> implements PermissaoDAO {

	public PermissaoDaoEJB() {
		super(Permissao.class);
	}

}