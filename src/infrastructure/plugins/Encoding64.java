package infrastructure.plugins;
import org.apache.commons.codec.binary.Base64;

public class Encoding64 {
	
	// converte um array de bytes em um array de bytes de base64
	public byte[] encode64 (byte[] b) {		
		return Base64.encodeBase64(b);
	}
	
	// pega uma string em base64 e converte para um array de bytes
	public byte[] decode64 (String s) {
		return Base64.decodeBase64(s.getBytes());
	}
	
	// pega um array de bytes e converte para uma string
	public String parsString (byte[] b) {		
		return new String(Base64.encodeBase64(b));
	}

}
