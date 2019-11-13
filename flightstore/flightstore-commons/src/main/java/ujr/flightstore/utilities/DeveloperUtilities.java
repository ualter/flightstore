package ujr.flightstore.utilities;

import java.util.Scanner;

import org.jasypt.util.text.BasicTextEncryptor;

public class DeveloperUtilities {
	
	public static void main(String[] args) throws Exception {
		//generateJsonFromPojo();
		encryptPasswordForProperties();
	}

	private static void encryptPasswordForProperties() {
		@SuppressWarnings("resource")
		Scanner scanner   = new Scanner(System.in);
		System.out.print("Enter the Secret Key.............: ");
		String  secretKey = scanner.nextLine();
		System.out.print("Enter the Password to Encrypt....: ");
		String password   = scanner.nextLine();
		
		
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(secretKey);
		String encrypted = encryptor.encrypt(password);
		System.out.println("Encrypted Password...............: ENC(" + encrypted + ")");
		
		/* Only for use outside Eclipse (real console)
		Console console  = System.console();
		System.out.println(console);
		String secretKey = console.readLine("Enter the Secret Key.............:");
		String password  = console.readLine("Enter the Password to Encrypt....:");
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(secretKey);
		String encrypted = encryptor.encrypt(password);
		System.out.println("Encrypted Password...............:" + encrypted);
		*/
	}

}
