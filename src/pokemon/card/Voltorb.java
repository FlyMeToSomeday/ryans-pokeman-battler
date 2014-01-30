package pokemon.card;

import pokemon.Pokemon;
import pokemon.Rarity;
import pokemon.Type;
import pokemon.move.*;
import pokemon.move.moveList.Tackle;

public class Voltorb extends Pokemon
{
	public Voltorb(int energyCount)
	{
		this.name = "Voltorb";
		this.maxHitPoints = 40;
		this.hitPoints = maxHitPoints;
		this.energyCount = energyCount;
		this.type = Type.LIGHTNING;
		this.weakness = Type.FIGHTING;
		this.resistance = Type.NONE;
		this.resistanceAmount = 0;
		this.retreatCost = 1;
		
		this.moves = new Move[1];
		this.moves[0] = new Tackle();
		
		this.rarity = Rarity.UNCOMMON;
	}
}
