package pokemon.move.moveList;

import battler2.Player;
import pokemon.move.Move;

public class Tackle extends Move
{
	public Tackle()
	{
		this.name = "Tackle";
		this.damage = 10;
		this.energyCost = 0;
		
		this.attackEffectDescription = "Tackle a Dude!";
	}

	@Override
	public String doAttackEffect(Player attacker, Player defender)
	{
		// no special effects
		return "";
	}
}
