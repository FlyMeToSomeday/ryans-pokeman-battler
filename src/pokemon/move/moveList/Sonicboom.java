package pokemon.move.moveList;

import battler.Player;
import pokemon.move.Move;

public class Sonicboom extends Move
{
	public Sonicboom()
	{
		this.name = "Sonicboom";
		this.damage = 0;
		this.energyCost = 0;
		
		this.attackEffectDescription = "Deals 20 damage to the defender. Neither Weakness nor Resistance are applied.";
	}

	@Override
	public String doAttackEffect(Player attacker, Player defender)
	{
		defender.getActivePokemon().setHitPoints(defender.getActivePokemon().getHitPoints() - 20);
		return "20 damage has been awarded to the unfortunite target of this attack!";
	}
}
