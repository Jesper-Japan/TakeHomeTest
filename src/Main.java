
public class Main {

	public static void main(String[] args) {
		tests();
	}
	
	private static void tests() {
		String phoneNumber1 = "+44 123 456 789";
		System.out.println(phoneNumber1);
		System.out.println(PrivateInfoObfuscator.obfuscate(phoneNumber1));
		String phoneNumber2 = "+74 (123) 456 789";
		System.out.println(phoneNumber2);
		System.out.println(PrivateInfoObfuscator.obfuscate(phoneNumber2));
		String phoneNumber3 = "0044-123-(456)-789";
		System.out.println(phoneNumber3);
		System.out.println(PrivateInfoObfuscator.obfuscate(phoneNumber3));
		String phoneNumber4 = "+88123456789";
		System.out.println(phoneNumber4);
		System.out.println(PrivateInfoObfuscator.obfuscate(phoneNumber4));
		String phoneNumber5 = "66278890";
		System.out.println(phoneNumber5);
		System.out.println(PrivateInfoObfuscator.obfuscate(phoneNumber5));
		String mail1 = "local-part@domain-name.com";
		System.out.println(mail1);
		System.out.println(PrivateInfoObfuscator.obfuscate(mail1));
		String mail2 = "lort@test.domain-name.com";
		System.out.println(mail2);
		System.out.println(PrivateInfoObfuscator.obfuscate(mail2));
		String mail3 = "m@name.com";
		System.out.println(mail3);
		System.out.println(PrivateInfoObfuscator.obfuscate(mail3));
		String mail4 = "c-r-a-z-y@domain.com";
		System.out.println(mail4);
		System.out.println(PrivateInfoObfuscator.obfuscate(mail4));
		
		System.out.println(NumberSupport.countSundays("01-05-2021", "30-05-2021")); // Expects 5
		System.out.println(NumberSupport.countSundays("01-05-2021", "01-05-2022")); // Expects 53
		
		System.out.println(NumberSupport.ordinalNumberSuffix(3));
		System.out.println(NumberSupport.ordinalNumberSuffix(11112));
		System.out.println(NumberSupport.ordinalNumberSuffix(345));
		System.out.println(NumberSupport.ordinalNumberSuffix(62341));
		System.out.println(NumberSupport.ordinalNumberSuffix(100));
		System.out.println(NumberSupport.ordinalNumberSuffix(53));
		System.out.println(NumberSupport.ordinalNumberSuffix(22222));
	}
	

	
	
	
	

}
