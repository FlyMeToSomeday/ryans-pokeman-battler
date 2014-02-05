package battler2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;

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
		setButtonActionListeners(aiEnabled, me, you);
	}
	
	// TODO finish this method.. make the buttons do things
	// the helper methods DO NO ERROR CHECKING, if a control is enabled, the player can do it un-checked!!!!1!!!11!
	private void setButtonActionListeners(boolean aiEnabled, Player me, Player you)
	{
		// disable all of the buttons, enable them as we need them..
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
		
		// if its the ai's turn, then disable the UI
		if(aiEnabled)
		{
			Battler2UI.viewMoves.setEnabled(false);
			Battler2UI.viewBench.setEnabled(false);
			Battler2UI.forfeitGame.setEnabled(false);
			Battler2UI.passTurn.setEnabled(false);
		}
		
		else // the ai is not enabled for this turn, then build the player controls
		{
			if(me.getActivePokemon() != null)
			{
				// if active pokemon is dead, force switch and don't allow moves
				if(me.getActivePokemon().getHitPoints() < 0)
				{
					Battler2UI.passTurn.setEnabled(false);
				}
				
				else // active is alive and able
				{
					// and can attack.. then set the moves..
					if(me.getActivePokemon().getCanAttack())
					{
						// set moves..
						if(me.getActivePokemon().getMoves() != null)
						{
							for(int i = 0; i < me.getActivePokemon().getMoves().length; i++)
							{
								JButton tmp;
								
								switch(i)
								{
									case 0:
										tmp = Battler2UI.move1;
										break;
										
									case 1:
										tmp = Battler2UI.move2;
										break;
										
									case 2:
										tmp = Battler2UI.move3;
										break;
										
									case 3:
										tmp = Battler2UI.move4;
										break;
										
									default: // something went wrong..
										tmp = new JButton();
										break;
								}
								
								tmp.setToolTipText(me.getActivePokemon().getMoves()[i].getName() + " (" + me.getActivePokemon().getMoves()[i].getEnergyCost() + ")");
								
								// if the pokemon has enough energy to use the move..
								if(me.getActivePokemon().getEnergyCount() >= me.getActivePokemon().getMoves()[i].getEnergyCost())
								{
									tmp.setEnabled(true);
									
									// set the action listener..
									tmp.addActionListener(new ActionListener()
									{
										@Override
										public void actionPerformed(ActionEvent e)
										{
											// TODO useMove(i, me, you);
										}
									});
								}
							}
						}
					}
					
					// set switches..
					for(int i = 0; i < me.getPokemon().length && me.getActivePokemon().getIsSwitchable(); i++)
					{
						JButton tmp;
						
						switch(i)
						{
							case 0:
								tmp = Battler2UI.switch1;
								break;
								
							case 1:
								tmp = Battler2UI.switch2;
								break;
								
							case 2:
								tmp = Battler2UI.switch3;
								break;
								
							case 3:
								tmp = Battler2UI.switch4;
								break;
								
							case 4:
								tmp = Battler2UI.switch5;
								break;
								
							case 5:
								tmp = Battler2UI.switch6;
								break;
								
							default: // something went wrong..
								tmp = new JButton();
								break;
						}
						
						// if it is the active pokemon, then set the tool tip to show that and keep the button disabled..
						if(me.getPokemon()[i].isActive())
						{
							tmp.setToolTipText("Current Active Pokemon");
						}
						
						else // it is some other pokemon..
						{
							tmp.setToolTipText(me.getPokemon()[i].getName() + " (" + me.getPokemon()[i].getEnergyCount() + ")");
							tmp.setEnabled(true);
							
							// set the action listener..
							tmp.addActionListener(new ActionListener()
							{
								@Override
								public void actionPerformed(ActionEvent e)
								{
									// TODO switchTo(i, me);
								}
							});
						}
					}
				}
			}
			
			// setup the rest of the controls..
			Battler2UI.viewMoves.setEnabled(true);
			Battler2UI.viewMoves.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// TODO viewMoves(me);
				}
			});
			
			Battler2UI.viewBench.setEnabled(true);
			Battler2UI.viewBench.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// TODO viewBench(me);
				}
			});
			
			Battler2UI.forfeitGame.setEnabled(true);
			Battler2UI.forfeitGame.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// TODO forfeitGame(me);
				}
			});
			
			Battler2UI.passTurn.setEnabled(true);
			Battler2UI.passTurn.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// TODO passTurn(me);
				}
			});
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
		
		doTurn(false, red, blu); // for testing only
		
//		while(battleStatus == 0)
//		{
//			if(redsTurn)
//			{
//				doTurn(false, red, blu); // change this to true to watch 2 ai's fight..
//			}
//			
//			else // blu's turn
//			{	
//				//doTurn(aiEnabled, blu, red);
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
