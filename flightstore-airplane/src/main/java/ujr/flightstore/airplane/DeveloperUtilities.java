package ujr.flightstore.airplane;

import java.util.Scanner;

import org.jasypt.util.text.BasicTextEncryptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ujr.flightstore.airplane.model.Airplane;
import ujr.flightstore.airplane.model.Manufacturer;

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

	@SuppressWarnings("unused")
	private static void generateJsonFromPojo() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		
		Manufacturer boeing = Manufacturer.builder().id(1L).name("Boeing").build();
		System.out.println("====> Manufacturer");
		System.out.println(mapper.writeValueAsString(boeing));
		
		
		Airplane airplane = Airplane.builder()
				.id(33L)
				.model("B737")
				.rangeKm(15000)
				.seats(180)
				.manufacturer(boeing)
				.build();
		System.out.println("\n====> Airplane");
		System.out.println(mapper.writeValueAsString(airplane));
	}

}
