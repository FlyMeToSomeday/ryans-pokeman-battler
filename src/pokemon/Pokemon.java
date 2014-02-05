package pokemon;

import pokemon.move.Move;

public abstract class Pokemon
{
	protected String name;
	protected int maxHitPoints;
	protected int hitPoints;
	protected int energyCount;
	protected Type type;
	protected Type weakness;
	protected Type resistance;
	protected int resistanceAmount;
	protected int retreatCost;
	protected Move moves[];
	
	protected boolean isActive = false;
	protected boolean wasParalyzed = false;
	protected boolean isPoisoned = false;
	protected Condition condition = Condition.NONE;
	
	protected int chanceToMiss = 0; // from 0% to 100% chance
	protected boolean canAttack = true;
	
	/*
	 * isSwitchable applies to both being able to be switched to if its on the bench,
	 * or being able to be switched out if it is the current active pokemon.
	 * 
	 * This must be watched very closely to ensure that pokemon don't get 'trapped'
	 * and rendered useless when they otherwise should be able to be switched..
	 * (mostly from conditions..)
	 */
	protected boolean isSwitchable = true;
	
	protected Rarity rarity;
	
	public String showMoves()
	{
		if(moves.length <= 0)
		{
			return "No Moves!";
		}
		
		String moveList = "";
		
		for(int i = 0; i < moves.length; i++)
		{
			moveList += "'m";
			moveList += i+1; // # command to use this attack
			moveList += "'";
			moveList += " - " + moves[i] + "\n";
		}
		
		return moveList;
	}
	
	public String showBasicDetails()
	{
		String basicDetails = "";
		
		basicDetails += (hitPoints <= 0 ? "FNT " : "") + name + "\n";
		
		return basicDetails;
	}
	
	public String showActiveDetails()
	{
		String currentCondition = "";
		
		switch(condition.ordinal())
		{
			case 1: 
				currentCondition = " (SLP)";
				break;
				
			case 2:
				currentCondition = " (???)";
				break;
				
			case 3: 
				currentCondition = " (PAR)";
				break;
			
			default: ;
		}
		
		return (hitPoints <= 0 ? "FNT " : "") + name + currentCondition + (isPoisoned ? " (PSN)" : "") + " (" + hitPoints + "/" + maxHitPoints + " HP/" + energyCount + " PP)";
	}
	
	public String showExtenedDetails()
	{
		String basicDetails = "";
		
		basicDetails += showActiveDetails() + "\n";
		basicDetails += "\tType: " + this.type + "\n";
		basicDetails += "\tWeakness: " + this.weakness + "\n";
		basicDetails += "\tResistance: " + this.resistance + " " + this.resistanceAmount + "\n";
		
		return basicDetails;
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
	public int getMaxHitPoints()
	{
		return maxHitPoints;
	}
	public void setmaxHitPoints(int maxHitPoints)
	{
		this.maxHitPoints = maxHitPoints;
	}
	public int getHitPoints()
	{
		return hitPoints;
	}
	public void setHitPoints(int hitPoints)
	{
		this.hitPoints = hitPoints;
	}
	public int getEnergyCount()
	{
		return energyCount;
	}
	public void setEnergyCount(int energyCount)
	{
		this.energyCount = energyCount;
	}
	public Type getType()
	{
		return type;
	}
	public void setType(Type type)
	{
		this.type = type;
	}
	public Type getWeakness()
	{
		return weakness;
	}
	public void setWeakness(Type weakness)
	{
		this.weakness = weakness;
	}
	public Type getResistance()
	{
		return resistance;
	}
	public void setResistance(Type resistance)
	{
		this.resistance = resistance;
	}
	public int getResistanceAmount()
	{
		return resistanceAmount;
	}
	public void setResistanceAmount(int resistanceAmount)
	{
		this.resistanceAmount = resistanceAmount;
	}
	public int getRetreatCost()
	{
		return retreatCost;
	}
	public void setRetreatCost(int retreatCost)
	{
		this.retreatCost = retreatCost;
	}
	public Move[] getMoves()
	{
		return moves;
	}
	public void setMoves(Move[] moves)
	{
		this.moves = moves;
	}
	public boolean isActive()
	{
		return isActive;
	}
	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}
	public boolean wasParalyzed()
	{
		return wasParalyzed;
	}
	public void setWasParalyzed(boolean wasParalyzed)
	{
		this.wasParalyzed = wasParalyzed;
	}
	public boolean isPoisoned()
	{
		return isPoisoned;
	}
	public void setPoisoned(boolean isPoisoned)
	{
		this.isPoisoned = isPoisoned;
	}
	public Condition getCondition()
	{
		return condition;
	}
	public void setCondition(Condition condition)
	{
		this.condition = condition;
	}
	public int getChanceToMiss()
	{
		return chanceToMiss;
	}

	public void setChanceToMiss(int chanceToMiss)
	{
		this.chanceToMiss = chanceToMiss;
	}
	
	public boolean getCanAttack()
	{
		return canAttack;
	}
	public void setCanAttack(boolean canAttack)
	{
		this.canAttack = canAttack;
	}
	
	public boolean getIsSwitchable()
	{
		if(isSwitchable == false)
		{
			return false;
		}
		
		else // is switchable
		{
			// and alive
			if(this.getHitPoints() > 0)
			{
				return true;
			}
			
			else // not alive
			{
				return false;
			}
		}
	}
	public void setIsSwitchable(boolean isSwitchable)
	{
		this.isSwitchable = isSwitchable;
	}

	public Rarity getRarity()
	{
		return rarity;
	}
	public void setRarity(Rarity rarity)
	{
		this.rarity = rarity;
	}
	
}
