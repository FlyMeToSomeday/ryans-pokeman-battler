package battler2;

import java.util.Random;

import battler2.Battler2UI;
import battler2.Player;

public class Battler2
{
	private Player red,
				   blu;
	
	public Battler2(Player player, Player wildPokemon)
	{
		this.red = player;
		this.blu = wildPokemon;
	}
	
	// its "me's" turn and we're attacking "you"
	private void doTurn(boolean aiEnabled, Player me, Player you)
	{
		// make our controls control the right person..
		setButtonActionListeners(me);
	}
	
	// TODO finish this method.. make the buttons do things
	private void setButtonActionListeners(Player forWho)
	{
		// disable the buttons, enable them as we need them..
		Battler2UI.move1.setEnabled(false);
		Battler2UI.move2.setEnabled(false);
		Battler2UI.move3.setEnabled(false);
		Battler2UI.move4.setEnabled(false);
		
		Battler2UI.switch1.setEnabled(false);
		Battler2UI.switch2.setEnabled(false);
		Battler2UI.switch3.setEnabled(false);
		Battler2UI.switch4.setEnabled(false);
		Battler2UI.switch5.setEnabled(false);
		Battler2UI.switch6.setEnabled(false);
		
		if(forWho.getActivePokemon() != null)
		{
			// set moves tool tips..
			if(forWho.getActivePokemon().getMoves() != null)
			{
				for(int i = 0; i < forWho.getActivePokemon().getMoves().length; i++)
				{
					switch(i)
					{
						case 0:
							Battler2UI.move1.setToolTipText(forWho.getActivePokemon().getMoves()[0].getName() + " (" + forWho.getActivePokemon().getMoves()[0].getEnergyCost() + ")");
							Battler2UI.move1.setEnabled(true);
							break;
							
						case 1:
							Battler2UI.move2.setToolTipText(forWho.getActivePokemon().getMoves()[1].getName() + " (" + forWho.getActivePokemon().getMoves()[1].getEnergyCost() + ")");
							Battler2UI.move2.setEnabled(true);
							break;
							
						case 2:
							Battler2UI.move3.setToolTipText(forWho.getActivePokemon().getMoves()[2].getName() + " (" + forWho.getActivePokemon().getMoves()[2].getEnergyCost() + ")");
							Battler2UI.move3.setEnabled(true);
							break;
							
						case 3:
							Battler2UI.move4.setToolTipText(forWho.getActivePokemon().getMoves()[3].getName() + " (" + forWho.getActivePokemon().getMoves()[3].getEnergyCost() + ")");
							Battler2UI.move4.setEnabled(true);
							break;
							
						default: break;
					}
				}
			}
			
			// set switch tool tips..
			for(int i = 0; i < forWho.getPokemon().length; i++)
			{
				switch(i)
				{
					case 0:
						// if not active and switchable..
						if(!(forWho.getPokemon()[0].isActive()) && forWho.getActivePokemon().getIsSwitchable())
						{
							Battler2UI.switch1.setToolTipText(forWho.getPokemon()[0].getName() + " (" + forWho.getPokemon()[0].getEnergyCount() + ")");
							Battler2UI.switch1.setEnabled(true);
						}
						break;
						
					case 1:
						if(!(forWho.getPokemon()[1].isActive()) && forWho.getActivePokemon().getIsSwitchable())
						{
							Battler2UI.switch2.setToolTipText(forWho.getPokemon()[1].getName() + " (" + forWho.getPokemon()[1].getEnergyCount() + ")");
							Battler2UI.switch2.setEnabled(true);
						}
						break;
						
					case 2:
						if(!(forWho.getPokemon()[2].isActive()) && forWho.getActivePokemon().getIsSwitchable())
						{
							Battler2UI.switch3.setToolTipText(forWho.getPokemon()[2].getName() + " (" + forWho.getPokemon()[2].getEnergyCount() + ")");
							Battler2UI.switch3.setEnabled(true);
						}
						break;
						
					case 3:
						if(!(forWho.getPokemon()[3].isActive()) && forWho.getActivePokemon().getIsSwitchable())
						{
							Battler2UI.switch4.setToolTipText(forWho.getPokemon()[3].getName() + " (" + forWho.getPokemon()[3].getEnergyCount() + ")");
							Battler2UI.switch4.setEnabled(true);
						}
						break;
						
					case 4:
						if(!(forWho.getPokemon()[4].isActive()) && forWho.getActivePokemon().getIsSwitchable())
						{
							Battler2UI.switch5.setToolTipText(forWho.getPokemon()[4].getName() + " (" + forWho.getPokemon()[4].getEnergyCount() + ")");
							Battler2UI.switch5.setEnabled(true);
						}
						break;
						
					case 5:
						if(!(forWho.getPokemon()[5].isActive()) && forWho.getActivePokemon().getIsSwitchable())
						{
							Battler2UI.switch6.setToolTipText(forWho.getPokemon()[5].getName() + " (" + forWho.getPokemon()[5].getEnergyCount() + ")");
							Battler2UI.switch6.setEnabled(true);
						}
						break;
						
					default: break;
				}
			}
		}
	}
	
	// starts a battle between the red and blu player
	// aiEnabled allows ai to act for the blu player, red is always human
	public void doBattle(boolean aiEnabled)
	{
		// can we even battle..?
		if(red == null || blu == null)
		{
			return;
		}
		
		Random random = new Random();
		
		// Should we keep battling?
		/* 
		 * 0 is battle on
		 * 1 is a loss for red
		 * 2 is a loss for blu
		 * 3 is a tie
		 */
		int battleStatus = 0;
		
		// Who goes first?  Coin flip..
		boolean redsTurn = random.nextBoolean();
		
		doTurn(false, red, blu);
		
//		while(battleStatus == 0)
//		{
//			if(redsTurn)
//			{
//				doTurn(false, red, blu); // change this to true to watch 2 ai's fight..
//			}
//			
//			else // blu's turn
//			{
//				if(aiEnabled)
//				{
//					// don't let the player act for the ai..
//					//disableAllUI();
//				}
//				
//				//doTurn(aiEnabled, blu, red);
//				
//				if(aiEnabled)
//				{
//					// let the player fuck with things again..
//					//enableAllUI();
//				}
//			}
//			
//			//battleStatus = checkBattleStatus(red, blu); // see if this is over yet
//			
//			redsTurn = !redsTurn; // switch the turn over
//		}
		
		// Hooray the battle is over!  Now tell the players..
		//displayResults(battleStatus, red, blu);
	}
}
