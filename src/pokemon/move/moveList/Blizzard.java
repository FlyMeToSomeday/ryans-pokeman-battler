package pokemon.move.moveList;

import java.util.Random;

import battler2.Player;
import pokemon.move.Move;

public class Blizzard extends Move
{
	public Blizzard()
	{
		this.name = "Blizzard";
		this.damage = 50;
		this.energyCost = 0;
		
		this.attackEffectDescription = "Will deal either 10 damage to each of your opponent's benched pokemon or it will deal 10 damage to each of your own benched pokemon.";
	}

	@Override
	public String doAttackEffect(Player attacker, Player defender)
	{
		Random random = new Random();
		boolean goodHit = false;
		
		if(random.nextBoolean())
		{
			goodHit = true;
			
			// hit the defender's bench
			for(int i = 0; i < defender.getPokemon().length; i++)
			{
				// if its not the active pokemon, then smack them
				if(!defender.getPokemon()[i].isActive())
				{
					defender.getPokemon()[i].setHitPoints(defender.getPokemon()[i].getHitPoints() - 10);
				}
			}
		}
		
		else
		{
			// hit the attacker's bench
			for(int i = 0; i < attacker.getPokemon().length; i++)
			{
				// if its not the active pokemon, then smack them
				if(!attacker.getPokemon()[i].isActive())
				{
					attacker.getPokemon()[i].setHitPoints(attacker.getPokemon()[i].getHitPoints() - 10);
				}
			}
		}
		
		return (goodHit ? defender.getName() : attacker.getName()) + "'s benched Pokemon all take 10 damage each from the Blizzard!";
	}
}
