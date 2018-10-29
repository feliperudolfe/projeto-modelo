package br.com.feliperudolfe.negocio.service.implementacao;

import java.util.List;

import javax.ejb.EJB;

import br.com.feliperudolfe.comum.configuracao.Msg;
import br.com.feliperudolfe.comum.configuracao.MsgRef;
import br.com.feliperudolfe.comum.modelo.dto.Mensagem;
import br.com.feliperudolfe.comum.tratamento.NegocioException;
import br.com.feliperudolfe.modelo.entidade.Perfil;
import br.com.feliperudolfe.modelo.entidade.Usuario;
import br.com.feliperudolfe.negocio.service.UsuarioService;
import br.com.feliperudolfe.repositorio.dao.PerfilDAO;
import br.com.feliperudolfe.repositorio.dao.PermissaoDAO;
import br.com.feliperudolfe.repositorio.dao.UsuarioDAO;

/**
 * @author Felipe Rudolfe
 *
 */
public class UsuarioServiceEJB implements UsuarioService {

	@EJB
	private UsuarioDAO usuarioDAO;

	@EJB
	private PerfilDAO perfilDAO;

	@EJB
	private PermissaoDAO permissaoDAO;

	@Override
	public List<Usuario> listar() {
		return this.usuarioDAO.listar();
	}// listar()

	@Override
	public Boolean cadastrar(Usuario usuario) throws NegocioException {

		Boolean retorno = Boolean.FALSE;
		try {

			this.validar(usuario);

			Perfil perfil = this.perfilDAO.buscar(1L);
			if (perfil == null) {
				throw new NegocioException(Msg.get(MsgRef.MSG_NAO_ENCONTRADO, "Pefil de usuário"));
			}// if

			usuario.setPerfil(perfil);
			this.usuarioDAO.inserir(usuario);

			retorno = Boolean.TRUE;

		} catch (NegocioException e) {
			throw new NegocioException(e);
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}// try-catch

		return retorno;
	}// cadastrar()

	private void validar(Usuario usuario) throws NegocioException {

		if (usuario == null) {
			throw new NegocioException(Mensagem.ALERTA, "Usuário não informado");
		}// if

		if (usuario.getLogin() == null || usuario.getLogin().isEmpty()) {
			throw new NegocioException(Mensagem.ALERTA, Msg.get(MsgRef.CMP_OBG, "Login"));
		} else if (usuario.getLogin().length() < 6) {
			throw new NegocioException(Mensagem.ALERTA, Msg.get(MsgRef.CMP_TAM_MIN, "Login", 6));
		} else if (usuario.getLogin().length() > 20) {
			throw new NegocioException(Mensagem.ALERTA, Msg.get(MsgRef.CMP_TAM_MAX, "Login", 20));
		} else if (this.usuarioDAO.isExisteUsuarioComLogin(usuario.getLogin())) {
			throw new NegocioException(Mensagem.ALERTA, Msg.get(MsgRef.MSG_JA_CADASTRADO, "Login"));
		}// if-else

		if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
			throw new NegocioException(Mensagem.ALERTA, Msg.get(MsgRef.CMP_OBGA, "Senha"));
		} else if (usuario.getSenha().length() < 6) {
			throw new NegocioException(Mensagem.ALERTA, Msg.get(MsgRef.CMP_TAM_MIN, "Senha", 6));
		} else if (usuario.getSenha().length() > 20) {
			throw new NegocioException(Mensagem.ALERTA, Msg.get(MsgRef.CMP_TAM_MAX, "Senha", 20));
		}

	}// validar()

	@Override
	public Usuario buscarPorLoginAndPass(String login, String pass) {
		return this.usuarioDAO.buscarPorLoginAndPass(login, pass);
	}// buscarPorLoginAndPass()

}