package pokemon.move.moveList;

import battler.Player;
import pokemon.move.Move;

public class Ember extends Move
{
	public Ember()
	{
		this.name = "Ember";
		this.damage = 30;
		this.energyCost = 1;
		
		this.attackEffectDescription = "Burn the other dude!";
	}

	@Override
	public String doAttackEffect(Player attacker, Player defender)
	{
		// no special effects
		return "";
	}
}
