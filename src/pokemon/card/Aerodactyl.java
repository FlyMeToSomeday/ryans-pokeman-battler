package pokemon.card;

import pokemon.Pokemon;
import pokemon.Rarity;
import pokemon.Type;
import pokemon.move.*;
import pokemon.move.moveList.WingAttack;

public class Aerodactyl extends Pokemon
{
	public Aerodactyl(int energyCount)
	{
		this.name = "Aerodactyl";
		this.maxHitPoints = 60;
		this.hitPoints = maxHitPoints;
		this.energyCount = energyCount;
		this.type = Type.FIGHTING;
		this.weakness = Type.GRASS;
		this.resistance = Type.FIGHTING;
		this.resistanceAmount = 30;
		this.retreatCost = 2;
		
		this.moves = new Move[1];
		this.moves[0] = new WingAttack();
		
		this.rarity = Rarity.LEGENDARY;
	}
}
