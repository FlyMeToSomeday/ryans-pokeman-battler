package pokemon.move.moveList;

import battler.Player;
import pokemon.move.Move;

public class WingAttack extends Move
{
	public WingAttack()
	{
		this.name = "Wing Attack";
		this.damage = 30;
		this.energyCost = 0;
		
		this.attackEffectDescription = "Hit 'em with a wing.. I think..";
	}

	@Override
	public String doAttackEffect(Player attacker, Player defender)
	{
		// no special effects
		return "";
	}
}
