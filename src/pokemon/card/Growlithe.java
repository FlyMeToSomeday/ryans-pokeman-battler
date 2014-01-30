package pokemon.card;

import pokemon.Pokemon;
import pokemon.Rarity;
import pokemon.Type;
import pokemon.move.*;
import pokemon.move.moveList.Flare;

public class Growlithe extends Pokemon
{
	public Growlithe(int energyCount)
	{
		this.name = "Growlithe";
		this.maxHitPoints = 60;
		this.hitPoints = maxHitPoints;
		this.energyCount = energyCount;
		this.type = Type.FIRE;
		this.weakness = Type.WATER;
		this.resistance = Type.NONE;
		this.resistanceAmount = 0;
		this.retreatCost = 1;
		
		this.moves = new Move[1];
		this.moves[0] = new Flare();
		
		this.rarity = Rarity.UNCOMMON;
	}
}
