package pokemon.move.moveList;

import battler.Player;
import pokemon.move.Move;

public class Selfdestruct extends Move
{
	public Selfdestruct()
	{
		this.name = "Selfdestruct";
		this.damage = 100;
		this.energyCost = 0;
		
		this.attackEffectDescription = "Do 100 damage to both active Pokemon and 20 damage to all benched Pokemon.";
	}

	@Override
	public String doAttackEffect(Player attacker, Player defender)
	{
		attacker.getActivePokemon().setHitPoints(attacker.getActivePokemon().getHitPoints() - 100);
		
		// hit the defender's bench
		for(int i = 0; i < defender.getPokemon().length; i++)
		{
			// if its not the active pokemon, then smack them
			if(!defender.getPokemon()[i].isActive())
			{
				defender.getPokemon()[i].setHitPoints(defender.getPokemon()[i].getHitPoints() - 20);
			}
		}
		
		// hit the attacker's bench
		for(int i = 0; i < attacker.getPokemon().length; i++)
		{
			// if its not the active pokemon, then smack them
			if(!attacker.getPokemon()[i].isActive())
			{
				attacker.getPokemon()[i].setHitPoints(attacker.getPokemon()[i].getHitPoints() - 20);
			}
		}
		
		return "You've killed yourself!\nAll benched Pokemon have taken 20 damage!\nDie, Boom, Bang, Burn, Fuck.. that was so Dope..";
	}
}
