package fr.zelytra.MapLoader.Utils;

public class Message {
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";

	public static String prefix_console() {
		String prefix = ANSI_BLUE+"["+ANSI_CYAN+"MapLoader"+ANSI_BLUE+"]"+ANSI_RESET;
		return prefix;
	}
	public static String prefix_player() {
		String prefix = "§3[§bMapLoader§3]§r ";
		return prefix;
	}
	public static String error_player() {
		String error = "§cAn arror as occured while attempting this command.";
		return error;
	}
	public static String error_console() {
		String error = ANSI_RED +"An arror as occured while attempting this command."+ANSI_RESET;
		return error;
	}
	

}


