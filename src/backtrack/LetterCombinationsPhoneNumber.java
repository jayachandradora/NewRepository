package backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsPhoneNumber {

	public static void main(String[] args) {
		LetterCombinationsPhoneNumber ph = new LetterCombinationsPhoneNumber();
		String digit = "23";
		System.out.println(ph.letterCombinations(digit));
		System.out.println(digit.substring(1, 1 + 1));
	}

	Map<String, String> phone = new HashMap<String, String>() {
		{
			put("2", "abc");
			put("3", "def");
			put("4", "ghi");
			put("5", "jkl");
			put("6", "mno");
			put("7", "pqrs");
			put("8", "tuv");
			put("9", "wxyz");
		}
	};

	List<String> output = new ArrayList<String>();

	public void backtrack(String combination, String digits) {

		if (digits.length() == 0) {
			output.add(combination);
		} else {

			String digit = digits.substring(0, 1);
			String letters = phone.get(digit);

			for (int i = 0; i < letters.length(); i++) {

				String letter = phone.get(digit).substring(i, i + 1);
				backtrack(combination + letter, digits.substring(1));
			}
		}
	}

	public List<String> letterCombinations(String digits) {
		if (digits.equals("1")) {
			output.add("digits' must consist of values from 2 to 9 only".toString());
			return output;
		}

		if (digits.length() != 0)
			backtrack("", digits);

		return output;
	}

}
