import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrivateInfoObfuscator {

	/**
	 * Takes an email address or a phone number and returns it obfuscated to prevent external readers from being able to read it.
	 * 
	 * @param info, Expects: Correctly formatted email address or phone number.
	 * In email address, the local only permits '.', '-' and '_' as special characters. The domain only permits '.' and '-'.
	 * The phone number is expected to contain at least 8 digits and be of valid format.
	 * The phone number is permitted to contain ' ', '-' and parenthesis.
	 * 
	 * @return the email address or phone number obfuscated.
	 * In case of email address, the local is replaced with the first and last letter + *****.
	 * In case of phone number, all but the last 4 digits are replaced with *s and any spaces will have been replaced with '-'.
	 */
	public static String obfuscate(String info) {
		if(info == null) throw new NullPointerException("Info string is null");
		
		Pattern emailRegex = Pattern.compile("^[a-zA-Z0-9]+((\\.|\\-|_)[a-zA-Z0-9]+)*@[a-zA-Z0-9]+((\\.|\\-)[a-zA-Z0-9]+)*(\\.[a-zA-Z]+)$");
		Matcher emailMatcher = emailRegex.matcher(info);
		if(emailMatcher.find()){
			return obfuscateEmail(info);
		} else {
			Pattern phoneRegex = Pattern.compile("^\\+?\\d+(( |\\-)?\\d+)+((( |\\-)?\\(\\d+\\))(( |\\-)?\\d+)+)?$");
			Matcher phoneMatcher = phoneRegex.matcher(info);
			if(phoneMatcher.find()) {
				return obfuscatePhoneNumber(info);
			}
		}
		
		throw new IllegalArgumentException("Info string is neither a correctly formatted email address nor phone number. Provided info string: " + info);
	}

	
	private static String obfuscateEmail(String mailAddress) {
		String[] split = mailAddress.split("@");
		return split[0].substring(0, 1) + "*****" + split[0].substring(split[0].length()-1) + "@" + split[1];
		// I case of 1 letter locals, the method will print that letter twice + *****, but that still obfuscates the mail address.
	}

	private static String obfuscatePhoneNumber(String phoneNumber) {
		char[] result = new char[phoneNumber.length()];
		
		// Count number of digits while obfuscating, as we don't accept phone numbers with less than 8 digits.
		int count=0;
	    for(int i = phoneNumber.length()-1; i >= 0 ; i--)
	    {
	      if(Character.isDigit(phoneNumber.charAt(i))) {
	    	  count++;
	    	  if(count > 4) {
	    		  result[i] = '*';
	    	  } else {
	    		  result[i] = phoneNumber.charAt(i);
	    	  }
	      } else if(phoneNumber.charAt(i) == ' ') {
	    	  result[i] = '-';
	      } else {
	    	  result[i] = phoneNumber.charAt(i);
	      }
	    }
	    if(count<8) {
	    	throw new IllegalArgumentException("Please provide a phonenumber with at least 8 digits");
	    }
		return new String(result);
	}

}
