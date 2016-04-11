package shaffer.j;

import java.util.Scanner;

public class MorseCodeConverter {
	public final static String[] morseCode = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
			"-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--",
			"--.." };
	public final static char[] letters = { 'a', 'b', 'c', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public static void main(String[] args) {
		System.out.println("Welcome to translator");

		Scanner input = new Scanner(System.in);
		String text;
		do {
			System.out.println("Enter text to translate (-1 to exit)");
			text = input.nextLine();
			// dash digit regex
			String morseCodeRegex = "[.-]{1,5}(?> [.-]{1,5})*(?>   [.-]{1,5}(?> [.-]{1,5})*)*";
			String nonEngMorRegex = "[^\\p{L}\\p{Nd}]+";
			if (text.matches(morseCodeRegex)) {
				// go to morse -> english
				convertToEnglish(text);
			} else if (text.matches(nonEngMorRegex)) {
				System.out.println("Unable to detect language");
			}else {
				// go to english --> morse
				convertToMorse(text);
			}
		} while (!text.equals("-1"));

		System.out.println("Good bye!");
	}

	public static String convertToMorse(String words) {
		String mString = "";
		System.out.println("Detected language: english");
		// for each character convert to char
		for (char x : words.toLowerCase().toCharArray()) {
			// loop through each char
			mString += xToMorse(x) + " ";
		}
		System.out.println(mString);
		return mString;
	}

	public static String xToMorse(char x) {
		for (int i = 0; i < letters.length; i++) {
			// if char matches with a letter in letters
			if (x == letters[i]) {
				// return the matching value in the morseCode array
				return morseCode[i];
			}
		}
		if (x == ' ') {
			return String.valueOf(" ");
		} else {
			return String.valueOf("?");
		}
	}

	public static String convertToEnglish(String words) {
		String engString = "";
		System.out.println("Detected language: morse");
		// looks for each white space and splits
		for (String e : words.split("\\s")) {
			engString += morseToEnglish(e);
		}
		System.out.println(engString);
		return engString;
	}

	public static String morseToEnglish(String e) {
		for (int i = 0; i < morseCode.length; i++) {
			if (e.equals(morseCode[i])) {
				return String.valueOf(letters[i]);
			}
		}
		if (e == " ") {
			return String.valueOf(" ");
		} else {
			return String.valueOf("?");
		}
	}
}
