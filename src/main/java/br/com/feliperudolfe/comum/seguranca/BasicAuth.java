package br.com.feliperudolfe.comum.seguranca;

import javax.xml.bind.DatatypeConverter;

/**
 * @author Felipe Rudolfe
 *
 */
public class BasicAuth {

	public static String[] decode(String auth) {
		String[] retorno = null;
		if (auth != null && !auth.isEmpty()) {
			auth = auth.replaceFirst("[B|b]asic ", "");
			byte[] decodedBytes = DatatypeConverter.parseBase64Binary(auth);
			if (decodedBytes != null && decodedBytes.length > 0) {
				retorno = new String(decodedBytes).split(":", 2);
			} // if
		} // if

		return retorno;
	}// decode()

}