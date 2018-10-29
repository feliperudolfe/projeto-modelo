package br.com.feliperudolfe.modelo.modelo.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.feliperudolfe.modelo.comum.modelo.entidade.Entidade;

/**
 * @author Felipe Rudolfe
 *
 */
@Entity
@Table(name = "tb_perfis")
public class Perfil extends Entidade<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = 5924775865038879655L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(nullable = false, unique = true, length = 30)
	private String nome;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_permissoes_perfil", joinColumns = {@JoinColumn(name = "perfil") }, inverseJoinColumns = { @JoinColumn(name = "permissao") })
	private List<Permissao> permissoes;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	public void addPermissao(Permissao permissao) {
		if (this.permissoes == null) {
			this.permissoes = new ArrayList<>();
		}
		this.permissoes.add(permissao);
	}

}