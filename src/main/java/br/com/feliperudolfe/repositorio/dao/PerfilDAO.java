package br.com.feliperudolfe.repositorio.dao;

import javax.ejb.Local;

import br.com.feliperudolfe.modelo.comum.repositorio.DAO;
import br.com.feliperudolfe.modelo.modelo.entidade.Perfil;

/**
 * @author Felipe Rudolfe
 *
 */
@Local
public interface PerfilDAO extends DAO<Long, Perfil> { }