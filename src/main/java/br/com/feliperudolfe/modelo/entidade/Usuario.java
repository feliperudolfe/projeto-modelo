package br.com.feliperudolfe.modelo.entidade;

import java.security.Principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.feliperudolfe.comum.modelo.entidade.Entidade;

/**
 * @author Felipe Rudolfe
 *
 */
@Entity
@Table(name = "tb_usuarios")
public class Usuario extends Entidade<Long> implements Principal {

	/**
	 *
	 */
	private static final long serialVersionUID = 1228185368455756835L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(nullable = false, length = 60, unique = true)
	private String login;

	@Column(nullable = false, length = 255)
	private String senha;

	@ManyToOne
	@JoinColumn(name = "perfil", nullable = false)
	private Perfil perfil;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@Override
	public String getName() {
		return this.login;
	}

}