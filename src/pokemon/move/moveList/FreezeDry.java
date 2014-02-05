package pokemon.move.moveList;

import java.util.Random;

import battler2.Player;
import pokemon.Condition;
import pokemon.move.Move;

public class FreezeDry extends Move
{
	public FreezeDry()
	{
		this.name = "Freeze Dry";
		this.damage = 30;
		this.energyCost = 0;
		
		this.attackEffectDescription = "Has a 50% chance of paralyzing the target!";
	}

	@Override
	public String doAttackEffect(Player attacker, Player defender)
	{
		Random random = new Random();
		
		if(random.nextBoolean())
		{
			defender.getActivePokemon().setCondition(Condition.PARALYZED);
			return "The target, " + defender.getActivePokemon().getName() +  ", is now paralyzed!";
		}
		
		return defender.getActivePokemon().getName() + " has resisted the paralysis!";
	}
}
