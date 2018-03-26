package com.test.convert.Represent.service;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author ritika 
 * Logic to convert numbers to String representation
 */
@Service
public class NumberToString {

	@Value("${stringFormat:unknown}")
	private String stringFormat;

	private static final String[] tensString = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty",
			" seventy", " eighty", " ninety" };

	private static final String[] numberString = { "", " one", " two", " three", " four", " five", " six", " seven",
			" eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen",
			" seventeen", " eighteen", " nineteen" };

	// convert the numbers less than one thousand
	private String convertNumbersLessThanOneThousand(int number) {
		String numStr;

		if (number % 100 < 20) {
			numStr = numberString[number % 100];
			number /= 100;
		} else {
			numStr = numberString[number % 10];
			number /= 10;

			numStr = tensString[number % 10] + numStr;
			number /= 10;
		}
		if (number == 0)
			return numStr;
		return numberString[number] + " hundred" + numStr;
	}

	// check if a string is numberic
	public boolean isNumeric(String string) {
		return string != null && string.matches("[-+]?\\d*\\.?\\d+");
	}

	// covert number to String representation
	public String convertNumberToString(String cmdNum) {
		boolean isNegative = false;
		boolean isNumber = false;

		StringBuffer result = new StringBuffer();
		StringBuffer strThousand = new StringBuffer();

		int number = 0;

		// check if number is negative
		if (isNumeric(cmdNum)) {
			isNumber = true;
			if (cmdNum.contains("-")) {
				isNegative = true;
			}
			try {
				// check if number is out of range
				number = Math.abs(Integer.parseInt(cmdNum));
			} catch (NumberFormatException e) {
				return "Number out of range. Please enter number between -999,999 and 999,999";
			}
		}

		// if not a number or number is out of range
		if (!isNumber) {
			return "Found characters in String. Please enter number between -999,999 and 999,999";
		} else if (number < -999999 || number > 999999) {
			return "Number out of range. Please enter number between -999,999 and 999,999";
		}

		if (number == 0) {
			return "zero";
		}

		String numString = Integer.toString(number);

		// pad with "0"
		String mask = "000000";
		DecimalFormat df = new DecimalFormat(mask);
		numString = df.format(number);

		int hundredThousands = Integer.parseInt(numString.substring(0, 3));
		int thousands = Integer.parseInt(numString.substring(3, 6));

		String strHundredThousands;
		switch (hundredThousands) {
		case 0:
			strHundredThousands = "";
			break;
		case 1:
			strHundredThousands = "one thousand ";
			break;
		default:
			strHundredThousands = convertNumbersLessThanOneThousand(hundredThousands) + " thousand ";
		}

		result.append(strHundredThousands);

		strThousand.append(convertNumbersLessThanOneThousand(thousands));
		result.append(strThousand);

		// remove extra spaces present in the String
		String resultString = result.toString().replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
		StringBuffer finalResult = new StringBuffer();
		if (isNegative) {
			finalResult.append("minus ");
		}
		finalResult.append(resultString);
		return finalResult.toString();
	}

}
