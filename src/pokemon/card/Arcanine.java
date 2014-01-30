package pokemon.card;

import pokemon.Pokemon;
import pokemon.Rarity;
import pokemon.Type;
import pokemon.move.*;
import pokemon.move.moveList.Flamethrower;
import pokemon.move.moveList.TakeDown;

public class Arcanine extends Pokemon
{
	public Arcanine(int energyCount)
	{
		this.name = "Arcanine";
		this.maxHitPoints = 100;
		this.hitPoints = maxHitPoints;
		this.energyCount = energyCount;
		this.type = Type.FIRE;
		this.weakness = Type.WATER;
		this.resistance = Type.NONE;
		this.resistanceAmount = 0;
		this.retreatCost = 3;
		
		this.moves = new Move[2];
		this.moves[0] = new Flamethrower();
		this.moves[1] = new TakeDown();
		
		this.rarity = Rarity.RARE;
	}
}
