package pokemon.card;

import pokemon.Pokemon;
import pokemon.Rarity;
import pokemon.Type;
import pokemon.move.*;
import pokemon.move.moveList.Psyshock;

public class Abra extends Pokemon
{
	public Abra(int energyCount)
	{
		this.name = "Abra";
		this.maxHitPoints = 30;
		this.hitPoints = maxHitPoints;
		this.energyCount = energyCount;
		this.type = Type.PSYCHIC;
		this.weakness = Type.PSYCHIC;
		this.resistance = Type.NONE;
		this.resistanceAmount = 0;
		this.retreatCost = 0;
		
		this.moves = new Move[1];
		this.moves[0] = new Psyshock();
		
		this.rarity = Rarity.COMMON;
	}
}
