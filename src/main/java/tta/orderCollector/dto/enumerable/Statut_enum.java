package tta.orderCollector.dto.enumerable;

public enum Statut_enum {

	// Orders's different status values this exists
	ANY(0), WAITING(1), TRANSMITTED(2), CANCELED(3), DISMISSED(4), MODIFIED(5), EXECUTED(6), PARTIALLY_EXECUTED(7),
	NON_EXECUTED(8), REJECTED(9), ACCEPTED(10), SUSPENDED(11);

	private final int value;

	Statut_enum(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}

	public static Statut_enum get(final int value) {

		switch (value) {
		case (0):
			return ANY;
		case (1):
			return WAITING;
		case (2):
			return TRANSMITTED;
		case (3):
			return CANCELED;
		case (4):
			return DISMISSED;
		case (5):
			return MODIFIED;
		case (6):
			return EXECUTED;
		case (7):
			return PARTIALLY_EXECUTED;
		case (8):
			return NON_EXECUTED;
		case (9):
			return REJECTED;
		case (10):
			return ACCEPTED;
		case (11):
			return SUSPENDED;

		}

		throw new IllegalArgumentException("Unknown value: " + value);
	}
}
