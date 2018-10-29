package br.com.feliperudolfe.comum.validacao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintDefinitionException;
import javax.validation.ConstraintViolation;
import javax.validation.GroupDefinitionException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.api.validation.ResteasyViolationException;
import org.jboss.resteasy.api.validation.Validation;

import br.com.feliperudolfe.comum.integracao.ResponseMapper;
import br.com.feliperudolfe.comum.modelo.dto.Mensagem;

/**
 * @author Felipe Rudolfe
 *
 */
@Provider
public class ValidationExceptionMapper extends ResponseMapper implements ExceptionMapper<ValidationException> {

	// TODO Refatorar tratamento de exceções para retornar com resposta em estrutura padrão

	@Override
	public Response toResponse(ValidationException exception) {
		if (exception instanceof ConstraintDefinitionException) {
			return buildResponse(unwrapException(exception), MediaType.TEXT_PLAIN, Status.INTERNAL_SERVER_ERROR);
		}
		if (exception instanceof ConstraintDeclarationException) {
			return buildResponse(unwrapException(exception), MediaType.TEXT_PLAIN, Status.INTERNAL_SERVER_ERROR);
		}
		if (exception instanceof GroupDefinitionException) {
			return buildResponse(unwrapException(exception), MediaType.TEXT_PLAIN, Status.INTERNAL_SERVER_ERROR);
		}
		if (exception instanceof ResteasyViolationException) {
			ResteasyViolationException resteasyViolationException = ResteasyViolationException.class.cast(exception);
			Exception e = resteasyViolationException.getException();
			if (e != null) {
//				return criarRespostaErro(unwrapException(e));
				return buildResponse(unwrapException(e), MediaType.TEXT_PLAIN, Status.INTERNAL_SERVER_ERROR);
			} else if (resteasyViolationException.getReturnValueViolations().size() == 0) {
//				return criarRespostaErro(unwrapException(e));
				return buildViolationReportResponse(resteasyViolationException, Status.BAD_REQUEST);
			} else {
//				return criarRespostaErro(unwrapException(e));
				return buildViolationReportResponse(resteasyViolationException, Status.INTERNAL_SERVER_ERROR);
			}
		}
		return buildResponse(unwrapException(exception), MediaType.TEXT_PLAIN, Status.INTERNAL_SERVER_ERROR);
	}

	protected Response buildResponse(Object entity, String mediaType, Status status) {
		ResponseBuilder builder = Response.status(status).entity(entity);
		builder.type(MediaType.TEXT_PLAIN);
		builder.header(Validation.VALIDATION_HEADER, "true");
		return builder.build();
	}

	protected Response buildViolationReportResponse(ResteasyViolationException exception, Status status) {
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		Iterator<ConstraintViolation<?>> iterator = exception.getConstraintViolations().iterator();
		while (iterator.hasNext()) {
			ConstraintViolation<?> constraintViolation = (ConstraintViolation<?>) iterator.next();
			mensagens.add(new Mensagem(Mensagem.ALERTA, constraintViolation.getMessage()));
		}// while

		return criarResposta(status, mensagens.toArray(new Mensagem[mensagens.size()]));//builder.build();
	}

	protected String unwrapException(Throwable t) {
		StringBuffer sb = new StringBuffer();
		doUnwrapException(sb, t);
		return sb.toString();
	}

	private void doUnwrapException(StringBuffer sb, Throwable t) {
		if (t == null) {
			return;
		}
		sb.append(t.toString());
		if (t.getCause() != null && t != t.getCause()) {
			sb.append('[');
			doUnwrapException(sb, t.getCause());
			sb.append(']');
		}
	}

}