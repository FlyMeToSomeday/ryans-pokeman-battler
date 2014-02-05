package pokemon.move.moveList;

import battler2.Player;
import pokemon.move.Move;

public class TakeDown extends Move
{
	public TakeDown()
	{
		this.name = "Take Down";
		this.damage = 80;
		this.energyCost = 0;
		
		this.attackEffectDescription = "Take 30 damage to yourself for using this move.";
	}

	@Override
	public String doAttackEffect(Player attacker, Player defender)
	{
		attacker.getActivePokemon().setHitPoints(attacker.getActivePokemon().getHitPoints() - 30);
		return attacker.getActivePokemon().getName() + " takes 30 damage in recoil damage!";
	}
}
