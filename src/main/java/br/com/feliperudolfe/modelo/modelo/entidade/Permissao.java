package br.com.feliperudolfe.modelo.modelo.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.feliperudolfe.modelo.comum.modelo.entidade.Entidade;

/**
 * @author Felipe Rudolfe
 *
 */
@Entity
@Table(name = "tb_permissoes")
public class Permissao extends Entidade<String> {

	/**
	 *
	 */
	private static final long serialVersionUID = -3745771557909042464L;

	@Id
	private String codigo;

	@Column(nullable = false, length = 255)
	private String descricao;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}