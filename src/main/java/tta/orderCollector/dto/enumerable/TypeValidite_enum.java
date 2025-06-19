package tta.orderCollector.dto.enumerable;

public enum TypeValidite_enum {

	DAY(1), END_OF_MONTH(2), FIXED_DATE(3), NEXT_YEAR(4),FOK(5);

	private final int value;

	public int value() {
		return value;
	}

	TypeValidite_enum(int value) {
		this.value = value;
	}

	public static TypeValidite_enum get(int value) {

		switch (value) {

		case (1):
			return DAY;
		case (2):
			return END_OF_MONTH;
		case (3):
			return FIXED_DATE;
		case (4):
			return NEXT_YEAR;
		
		case (5):
			return FOK;

		}

		throw new IllegalArgumentException("Unknown value: " + value);
	}
}
