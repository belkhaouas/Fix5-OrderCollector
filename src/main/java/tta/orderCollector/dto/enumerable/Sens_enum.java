package tta.orderCollector.dto.enumerable;

public enum Sens_enum {

	BUY(1), SELL(2);

	private final int value;

	Sens_enum(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}

	public static Sens_enum get(final int value) {

		switch (value) {

		case (1):
			return BUY;
		case (2):
			return SELL;

		}

		throw new IllegalArgumentException("Unknown value: " + value);
	}
}
