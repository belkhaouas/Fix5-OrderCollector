package tta.orderCollector.dto.enumerable;

public enum TypeOperation_enum {

	
	NEW(1), MODIFY(2), CANCEL(3);

	private final int value;

	TypeOperation_enum(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}

	public static TypeOperation_enum get(final int value) {

		switch (value) {

		case (1):
			return NEW;
		case (2):
			return MODIFY;
		case (3):
			return CANCEL;

		}

		throw new IllegalArgumentException("Unknown value: " + value);
	}
}
