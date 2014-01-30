package pokemon.card;

import pokemon.Pokemon;
import pokemon.Rarity;
import pokemon.Type;
import pokemon.move.*;
import pokemon.move.moveList.Ember;
import pokemon.move.moveList.Scratch;

public class Charmander extends Pokemon
{
	public Charmander(int energyCount)
	{
		this.name = "Charmander";
		this.maxHitPoints = 50;
		this.hitPoints = maxHitPoints;
		this.energyCount = energyCount;
		this.type = Type.FIRE;
		this.weakness = Type.WATER;
		this.resistance = Type.NONE;
		this.resistanceAmount = 0;
		this.retreatCost = 1;
		
		this.moves = new Move[2];
		this.moves[0] = new Scratch();
		this.moves[1] = new Ember();
		
		this.rarity = Rarity.UNCOMMON;
	}
}
