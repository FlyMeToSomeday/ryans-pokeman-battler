package pokemon.move;

import battler2.Player;

public abstract class Move
{
	protected String name;
	protected int damage;
	protected int energyCost;
	
	protected String attackEffectDescription = "No Special Effects";
	
	// returns a description of what happened
	public abstract String doAttackEffect(Player attacker, Player defender);
	
	public String toString()
	{
		return energyCost + " PP / " + name + " / " + damage + " DMG\n" +
				"\t- " + attackEffectDescription;
	}

	// GETTERS AND SETTERS
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getDamage()
	{
		return damage;
	}

	public void setDamage(int damage)
	{
		this.damage = damage;
	}

	public int getEnergyCost()
	{
		return energyCost;
	}

	public void setEnergyCost(int energyCost)
	{
		this.energyCost = energyCost;
	}

	public String getAttackEffectDescription()
	{
		return attackEffectDescription;
	}

	public void setAttackEffectDescription(String attackEffectDescription)
	{
		this.attackEffectDescription = attackEffectDescription;
	}
}
