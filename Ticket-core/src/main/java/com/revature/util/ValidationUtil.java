package com.revature.util;

import com.revature.exception.ValidationException;

public class ValidationUtil {
	private ValidationUtil() {

	}

	public static void rejectIfNullOrEmpty(String input, String message) throws ValidationException {
		if (input == null || "".equals(input.trim()))
			throw new ValidationException("Invalid" + message);
	}

	public static void rejectIfNullOrNegative(Integer no, String message) throws ValidationException {
		if (no == null || no <= 0)
			throw new ValidationException("Invalid" + message);

	}
}
