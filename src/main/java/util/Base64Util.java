package util;
import java.io.*;
import org.apache.commons.codec.binary.Base64;

public class Base64Util{


	//converte array de bytes em base 64 string
	public static String ToBase64(byte[] file) throws IOException{
        byte[] encoded = Base64.encodeBase64(file);     

		return  new String(encoded);
	}

	//converte uma base64 string em array de bytes
	public static byte[] ToFile(String encoded){
		 byte[] decoded = Base64.decodeBase64(encoded.getBytes());
		 // System.out.println(decoded);      
		 return decoded;
	}

}