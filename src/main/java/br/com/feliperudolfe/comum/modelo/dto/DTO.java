package br.com.feliperudolfe.comum.modelo.dto;

import java.io.Serializable;

import br.com.feliperudolfe.comum.configuracao.Msg;
import br.com.feliperudolfe.comum.configuracao.MsgRef;

/**
 * @author Felipe Rudolfe
 *
 */
public abstract class DTO implements Serializable, Cloneable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DTO clone() {
        DTO retorno = null;
        try {
            retorno = (DTO) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(Msg.get(MsgRef.MSG_NAO_FOI_POSSIVEL_CLONAR_ENTIDADE));
        } // try-catch
        return retorno;
    }// clone()

}