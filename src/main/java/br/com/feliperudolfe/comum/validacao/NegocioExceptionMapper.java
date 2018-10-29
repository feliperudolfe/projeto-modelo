package br.com.feliperudolfe.comum.validacao;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.feliperudolfe.comum.integracao.ResponseMapper;
import br.com.feliperudolfe.comum.tratamento.NegocioException;

/**
 * @author Felipe Rudolfe
 *
 */
@Provider
public class NegocioExceptionMapper extends ResponseMapper implements ExceptionMapper<NegocioException> {

	@Override
	public Response toResponse(NegocioException exception) {
		return criarRespostaErro(exception);
	}

}