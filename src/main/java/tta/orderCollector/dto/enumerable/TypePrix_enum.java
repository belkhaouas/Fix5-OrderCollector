package tta.orderCollector.dto.enumerable;

public enum TypePrix_enum {

	OPEN_PRICE(1), FIXED_PRICE(2), ATP(3), BEST_LIMIT(4), STOP_LOSS(5), STOP_LIMIT(6), APPLICATION(7);
	//, LAST_PRICE(8);

	private final int value;

	TypePrix_enum(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}

	public static TypePrix_enum get(final int value) {

		switch (value) {

		case (1):
			return OPEN_PRICE;
		case (2):
			return FIXED_PRICE;
		case (3):
			return ATP;
		case (4):
			return BEST_LIMIT;
		case (5):
			return STOP_LOSS;
		case (6):
			return STOP_LIMIT;
		case (7):
			return APPLICATION;
//		case (8):
//			return LAST_PRICE;

		}

		throw new IllegalArgumentException("Unknown value: " + value);
	}
}
