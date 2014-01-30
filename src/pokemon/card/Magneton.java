package pokemon.card;

import pokemon.Pokemon;
import pokemon.Rarity;
import pokemon.Type;
import pokemon.move.*;
import pokemon.move.moveList.Selfdestruct;
import pokemon.move.moveList.Sonicboom;

public class Magneton extends Pokemon
{
	public Magneton(int energyCount)
	{
		this.name = "Magneton";
		this.maxHitPoints = 80;
		this.hitPoints = maxHitPoints;
		this.energyCount = energyCount;
		this.type = Type.LIGHTNING;
		this.weakness = Type.FIGHTING;
		this.resistance = Type.NONE;
		this.resistanceAmount = 0;
		this.retreatCost = 2;
		
		this.moves = new Move[2];
		this.moves[0] = new Sonicboom();
		this.moves[1] = new Selfdestruct();
		
		this.rarity = Rarity.RARE;
	}
}
