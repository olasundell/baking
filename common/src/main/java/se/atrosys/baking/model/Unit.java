package se.atrosys.baking.model;

import lombok.Getter;

import static se.atrosys.baking.model.UnitType.*;

/**
 * TODO write documentation
 */
@Getter
public enum Unit {
	GRAMMES(MASS),
	LITRES(VOLUME),
	DECILITRES(VOLUME),
	CENTILITRES(VOLUME),
	MILLILITRES(VOLUME),
	TEASPOONS(VOLUME),
	TABLESPOONS(VOLUME),
	PIECES(NUMBER);

	private final UnitType type;

	Unit(UnitType type) {
		this.type = type;
	}
}
