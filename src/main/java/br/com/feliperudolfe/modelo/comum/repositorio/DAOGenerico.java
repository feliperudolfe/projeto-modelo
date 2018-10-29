package br.com.feliperudolfe.modelo.comum.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.jboss.logging.Logger;

import br.com.feliperudolfe.modelo.comum.modelo.dto.Mensagem;
import br.com.feliperudolfe.modelo.comum.modelo.entidade.Entidade;
import br.com.feliperudolfe.modelo.comum.tratamento.DatabaseException;

/**
 * @author Felipe Rudolfe
 *
 * @param <T>
 * @param <E>
 */
public abstract class DAOGenerico<T, E extends Entidade<T>> implements DAO<T, E> {

	private final Logger LOGGER = Logger.getLogger(DAOGenerico.class);

	private static final String PU = "modelo-pu";

	@PersistenceContext(name = PU)
	private EntityManager em;

	private Class<E> classe;

	public DAOGenerico(Class<E> classe) {
		this.classe = classe;
	}// DAOGenerico()

	@Override
	public void inserir(E entidade) throws DatabaseException {
		try {
			this.getEM().persist(entidade);
		} catch (Exception e) {
			LOGGER.info(e);
			throw new DatabaseException(Mensagem.ERRO, "Ocorre um erro durante a executar desta operação.");
		}// try-catch
	}// inserir()

	@Override
	public void atualizar(E entidade) throws DatabaseException {
		try {
			this.getEM().merge(entidade);
		} catch (Exception e) {
			LOGGER.info(e);
			throw new DatabaseException(Mensagem.ERRO, "Ocorre um erro durante a executar desta operação.");
		}// try-catch
	}// atualizar()

	@Override
	public void remover(E entidade) throws DatabaseException {
		try {
			this.getEM().remove(entidade);
		} catch (Throwable e) {
			LOGGER.info(e);
			throw new DatabaseException(Mensagem.ERRO, "Ocorre um erro durante a executar desta operação.");
		}// try-catch
	}// remover()

	@Override
	public List<E> listar() {
		TypedQuery<E> typedQuery = null;
		final CriteriaBuilder builder = this.getEM().getCriteriaBuilder();
		final CriteriaQuery<E> criteriaQuery = builder.createQuery(this.classe);
		criteriaQuery.from(this.classe);
		typedQuery = this.getEM().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}// listar()

	@Override
	public E buscar(T codigo) {
		return this.getEM().find(this.classe, codigo);
	}// buscar()

	protected E executarConsultaSimples(String sql) {
		Query query = this.getEM().createQuery(sql);
		return executarQuerySimples(query);
	}// executarConsultaSimples()

	@SuppressWarnings("unchecked")
	protected E executarQuerySimples(Query query) {
		E retorno = null;
		try {
			retorno = (E) query.getSingleResult();
		} catch (NoResultException e) {
			return retorno;
		} // try-cacth
		return retorno;
	}// executarQuerySimples()

	protected List<E> executarConsulta(String sql) {
		Query query = this.getEM().createQuery(sql);
		return executarQuery(query);
	}// executarConsulta()

	protected List<E> executarConsultaComLimite(String sql, int limite) {
		Query query = this.getEM().createQuery(sql);
		return executarQueryComLimite(query, limite);
	}// executarConsulta()

	protected List<E> executarConsultaComLimite(String sql, int inicio, int limite) {
		Query query = this.getEM().createQuery(sql);
		return executarQueryComLimite(query, inicio, limite);
	}// executarConsulta()

	@SuppressWarnings("unchecked")
	protected List<E> executarQuery(Query query) {
		return query.getResultList();
	}// executarQuery()

	protected int executarQueryUpdate(Query query) {
		return query.executeUpdate();
	}// executarQuery()

	@SuppressWarnings("unchecked")
	protected List<E> executarQueryComLimite(Query query, int limite) {
		return query.setMaxResults(limite).getResultList();
	}// executarQueryComLimite()

	@SuppressWarnings("unchecked")
	protected List<E> executarQueryComLimite(Query query, int inicio, int limite) {
		return query.setFirstResult(inicio).setMaxResults(limite).getResultList();
	}// executarQueryComLimite()

	@Override
	public Long count() {
		CriteriaBuilder cb = this.getEM().getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(this.classe)));
		return this.getEM().createQuery(cq).getSingleResult();
	}// count()

	protected EntityManager getEM() {
		return this.em;
	}// getEM()
}