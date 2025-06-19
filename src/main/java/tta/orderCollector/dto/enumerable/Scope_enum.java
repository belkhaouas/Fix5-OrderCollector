package tta.orderCollector.dto.enumerable;

public enum Scope_enum {

	// 0 default config 1 order not in the scope of cancel on disconnect
	CancelConfig("0"), NormalConfig("1");

	private final String value;

	Scope_enum(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public static Scope_enum get(final String value) {

		switch (value) {

		case ("0"):
			return CancelConfig;
		case ("1"):
			return NormalConfig;

		}

		throw new IllegalArgumentException("Unknown value: " + value);
	}
}
