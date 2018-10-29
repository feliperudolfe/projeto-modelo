package br.com.feliperudolfe.negocio.service;

import java.util.List;

import br.com.feliperudolfe.comum.tratamento.NegocioException;
import br.com.feliperudolfe.modelo.entidade.Usuario;

public interface UsuarioService {

	public List<Usuario> listar();

	public Boolean cadastrar(Usuario usuario) throws NegocioException;

	public Usuario buscarPorLoginAndPass(String login, String pass);

}