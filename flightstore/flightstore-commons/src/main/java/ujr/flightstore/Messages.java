package ujr.flightstore;

public class Messages {
	
	public static String message404(String resource, Long id) {
		return String.format("%s with code \"%d\" was not found", resource, id);
	}

}
