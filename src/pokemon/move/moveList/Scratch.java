package pokemon.move.moveList;

import battler2.Player;
import pokemon.move.Move;

public class Scratch extends Move
{
	public Scratch()
	{
		this.name = "Scratch";
		this.damage = 10;
		this.energyCost = 0;
		
		this.attackEffectDescription = "I'll scratch your back if you scratch mine.. (;";
	}

	@Override
	public String doAttackEffect(Player attacker, Player defender)
	{
		// no special effects
		return "";
	}
}
