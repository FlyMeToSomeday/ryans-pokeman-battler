package pokemon.move.moveList;

import battler.Player;
import pokemon.move.Move;

public class Flare extends Move
{
	public Flare()
	{
		this.name = "Flare";
		this.damage = 20;
		this.energyCost = 0;
		
		this.attackEffectDescription = "Burn a Dude! Sorta..";
	}

	@Override
	public String doAttackEffect(Player attacker, Player defender)
	{
		// no special effects
		return "";
	}
}
