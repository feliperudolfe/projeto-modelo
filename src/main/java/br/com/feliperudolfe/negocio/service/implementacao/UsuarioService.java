package br.com.feliperudolfe.negocio.service.implementacao;

import java.util.List;

import br.com.feliperudolfe.modelo.comum.tratamento.NegocioException;
import br.com.feliperudolfe.modelo.modelo.entidade.Usuario;

public interface UsuarioService {

	public List<Usuario> listar();

	public Boolean cadastrar(Usuario usuario) throws NegocioException;

	public Usuario buscarPorLoginAndPass(String login, String pass);

}