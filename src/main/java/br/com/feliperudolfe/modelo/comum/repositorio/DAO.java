package br.com.feliperudolfe.modelo.comum.repositorio;

import java.util.List;

import br.com.feliperudolfe.modelo.comum.modelo.entidade.Entidade;
import br.com.feliperudolfe.modelo.comum.tratamento.DatabaseException;

/**
 * @author Felipe Rudolfe
 *
 * @param <T>
 * @param <E>
 */
public interface DAO<T, E extends Entidade<T>> {

	void inserir(E e) throws DatabaseException;

	void atualizar(E e) throws DatabaseException;

	void remover(E e) throws DatabaseException;

	List<E> listar();

	E buscar(T codigo);

	Long count();

}