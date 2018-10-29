package br.com.feliperudolfe.comum.modelo.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.feliperudolfe.comum.modelo.dto.DTO;

/**
 * @author Felipe Rudolfe
 *
 * @param <T>
 */
@MappedSuperclass
public abstract class Entidade<T> extends DTO {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date registro;

	@Column(nullable = false)
	private Boolean situacao;

	public abstract T getCodigo();

	public abstract void setCodigo(T t);

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	@PrePersist
	public void prePersist() {
		this.registro = new Date();
		if (this.situacao == null) {
			this.situacao = Boolean.TRUE;
		}// if
	}// prePersist()

	@PreUpdate
	public void preUpdate() {
		this.registro = new Date();
	}// preUpdate()

}