package pokemon.move.moveList;

import battler.Player;
import pokemon.move.Move;

public class Flamethrower extends Move
{
	public Flamethrower()
	{
		this.name = "Flamethrower";
		this.damage = 50;
		this.energyCost = 1;
		
		this.attackEffectDescription = "Give it to them like it's '68.";
	}

	@Override
	public String doAttackEffect(Player attacker, Player defender)
	{
		// no special effects
		return "";
	}
}
