package userModels;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * UserUtilities is a set of utilities used for user security purposes.
 * There are methods to hash String inputs and generate session tokens
 * from random data.
 * 
 * @author PDM
 * @version 1.0
 */

public class UserUtilities {

	/**
	 * hash a string input, for example to encrypt passwords before writing to DB
	 * @param inputString string to be hashed
	 * @return hashed string
	 */
	public static String hashString(String inputString) {
		
		String hashedString = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(inputString.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for(byte b: bytes) {
				sb.append(String.format("%02x", b));
			}
			hashedString = sb.toString();
		}
		catch (NoSuchAlgorithmException exception) {
			exception.printStackTrace();
		}
		
		return hashedString;
		
	}
	
	/**
	 * Generate a session token of 'tokenLength' random characters
	 * allowing users to securely authorise requests
	 * @return generated token
	 */
	public static String generateToken() {
		
		String token;
		Random random = new Random();
		String charRange = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int tokenLength = 16;
		char[] generated = new char[tokenLength];
		
		for (int i = 0; i < tokenLength; i += 1 ) {
			generated[i] = charRange.charAt(random.nextInt(charRange.length()));
		}
		
		token = new String(generated);
		return token;
	}
	
}
