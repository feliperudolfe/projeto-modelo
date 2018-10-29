package br.com.feliperudolfe.modelo.integracao.ws;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.feliperudolfe.modelo.comum.configuracao.Msg;
import br.com.feliperudolfe.modelo.comum.configuracao.MsgRef;
import br.com.feliperudolfe.modelo.comum.modelo.dto.Mensagem;
import br.com.feliperudolfe.modelo.comum.modelo.dto.Requisicao;
import br.com.feliperudolfe.modelo.comum.tratamento.NegocioException;
import br.com.feliperudolfe.modelo.modelo.entidade.Usuario;
import br.com.feliperudolfe.negocio.service.implementacao.UsuarioService;

/**
 * @author Felipe Rudolfe
 *
 */
@Path(value = "/usuario")
public class UsuarioWS extends WebService {

	@Inject
	private UsuarioService usuarioService;

	@POST
	@Path("/cadastrar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrar(Requisicao<Usuario> requisicao) throws NegocioException {
		Boolean resultado = this.usuarioService.cadastrar(requisicao.getData());
		return criarRespostaSucesso(resultado, Mensagem.SUCESSO, Msg.get(MsgRef.MSG_CADASTRADO_COM_SUCESSO, "Usuário"));
	}// cadastrar()

}