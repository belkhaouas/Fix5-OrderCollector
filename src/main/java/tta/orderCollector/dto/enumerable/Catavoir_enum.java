package tta.orderCollector.dto.enumerable;

public enum Catavoir_enum {

	COMPTE_CLIENT_LIBRE("021"), COMPTE_CLIENT_GERE("022"), COMPTE_PROPRE("001"), COMPTE_ANIMATEUR("006"),
	COMPTE_ETRANGER_LIBRE("031"), COMPTE_ETRANGER_GERE("032"), COMPTE_OPCVM("004");

	private final String value;

	Catavoir_enum(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public static Catavoir_enum get(final String value) {

		switch (value) {

		case ("021"):
			return COMPTE_CLIENT_LIBRE;
		case ("022"):
			return COMPTE_CLIENT_GERE;
		case ("031"):
			return COMPTE_ETRANGER_LIBRE;
		case ("032"):
			return COMPTE_ETRANGER_GERE;
		case ("001"):
			return COMPTE_PROPRE;
		case ("006"):
			return COMPTE_ANIMATEUR;
		case ("004"):
			return COMPTE_OPCVM;

		}

		throw new IllegalArgumentException("Unknown value: " + value);
	}
}
