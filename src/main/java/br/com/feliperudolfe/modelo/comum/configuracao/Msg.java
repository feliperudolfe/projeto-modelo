package br.com.feliperudolfe.modelo.comum.configuracao;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Properties;

public class Msg {

	private static Properties props;
	private static String arquivoPropriedade = "/mensagem.properties";

	private Msg() {
		carrregarMensagens();
	}// Mensagem()

	public static String get(MsgRef chave) {
		return get(chave.toString());
	}// get()

	public static String get(MsgRef chave, Object ... parametros) {
		return get(chave.toString(), parametros);
	}// get()

	public static String get(String chave) {
		return get(chave, new Object [] {});
	}// get()

	public static String get(String chave, Object ... parametros) {
		String retorno = null;
		if (props == null) {
			carrregarMensagens();
		} // if

		retorno = props.getProperty(chave);
		if (retorno == null || retorno == "") {
			retorno = "Mensagem não encontrada";
		} else {
			if (parametros != null && parametros.length > 0) {
				retorno = MessageFormat.format(retorno, parametros);
			}// if
		}// if-eslse

		return retorno;
	}// get()

	private static void carrregarMensagens() {
		try {
			props = new Properties();
			InputStream in = Msg.class.getResourceAsStream(arquivoPropriedade);
			InputStreamReader isr = new InputStreamReader(in, "UTF-8");
			props.load(isr);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}// try-catch
	}// carrregarMensagens()

}