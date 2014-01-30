package pokemon.card;

import pokemon.Pokemon;
import pokemon.Rarity;
import pokemon.Type;
import pokemon.move.*;
import pokemon.move.moveList.Blizzard;
import pokemon.move.moveList.FreezeDry;

public class Articuno extends Pokemon
{
	public Articuno(int energyCount)
	{
		this.name = "Articuno";
		this.maxHitPoints = 70;
		this.hitPoints = maxHitPoints;
		this.energyCount = energyCount;
		this.type = Type.WATER;
		this.weakness = Type.NONE;
		this.resistance = Type.FIGHTING;
		this.resistanceAmount = 30;
		this.retreatCost = 2;
		
		this.moves = new Move[2];
		this.moves[0] = new FreezeDry();
		this.moves[1] = new Blizzard();
		
		this.rarity = Rarity.LEGENDARY;
	}
}
