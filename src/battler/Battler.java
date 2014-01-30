package battler;

import java.util.Random;
import java.util.Scanner;

import pokemon.Condition;
import pokemon.Pokemon;
import pokemon.card.*;

public class Battler
{
	protected static Player red;
	protected static Player blu;
	
	protected static boolean isBattling;
	protected static boolean isBlusTurn;
	
	protected static Scanner sc;
	protected static Random random;
	
	public Battler()
	{

	}
	
	public static void main(String[] args)
	{
		// Begin setup stuffs
		sc = new Scanner(System.in);
		random = new Random();
		
		// TODO MAKE A GUI
		
		// TODO MAKE OPTION FOR BATTLING A WILD POKEMON
		
		// TODO EACH PLAYER PICKS THEIR POKEMON IN PRIVATE
		
		// red is top
		Pokemon redPokemon[] = new Pokemon[4];
		redPokemon[0] = new Abra(0);
		redPokemon[1] = new Aerodactyl(0);
		redPokemon[2] = new Arcanine(0);
		redPokemon[3] = new Articuno(0);
		red = new Player("Some Scrub", redPokemon);
		red.setActivePokemon(redPokemon[0]);
		
		// blu is bottom
		Pokemon bluPokemon[] = new Pokemon[4];
		bluPokemon[0] = new Charmander(2);
		bluPokemon[1] = new Growlithe(0);
		bluPokemon[2] = new Magneton(0);
		bluPokemon[3] = new Voltorb(0);
		blu = new Player("Ryan", bluPokemon);
		blu.setActivePokemon(bluPokemon[0]);
		
		// Begin battle stuffs
		isBattling = true;
		isBlusTurn = false;
		
		while(isBattling)
		{
			Player attackingPlayer;
			Player defendingPlayer;
			
			// whose who?
			if(isBlusTurn)
			{
				attackingPlayer = blu;
				defendingPlayer = red;
			}
			
			else
			{
				attackingPlayer = red;
				defendingPlayer = blu;
			}

			System.out.print(printHint(""));
			System.out.print(printHint("~~~~~~~~~~~~"));
			System.out.print(printHint(attackingPlayer.getName() + "'s Turn! (Team " + (isBlusTurn ? "Blu" : "Red") + ")"));
			System.out.print(printHint("~~~~~~~~~~~~"));

			boolean endTurn = false;
			int outcome = 0; // 0 is reserved for default outcome (don't end the turn, if 0)
			
			defendingPlayer.getActivePokemon().setChanceToMiss(0); // reset defender's chance to miss..
			
			if(!attackingPlayer.getActivePokemon().gotKill())
			{	
				// check if asleep, if asleep, then notify the attacking player and check if their pokemon wakes up
				if(attackingPlayer.getActivePokemon().getCondition().equals(Condition.ASLEEP))
				{
					System.out.print(printHint("Shhh... " + attackingPlayer.getActivePokemon().getName() + " is sound asleep!"));
					
					// if true, wake up
					if(random.nextBoolean())
					{
						System.out.print(printHint(attackingPlayer.getActivePokemon().getName() + " has finally woken up!"));
					}
				}
				
				// check if paralyzed, if paralyzed, then notify the attacking player
				if(attackingPlayer.getActivePokemon().getCondition().equals(Condition.PARALYZED))
				{
					// was already paralyzed last turn? (and should ware off this turn..)
					if(attackingPlayer.getActivePokemon().wasParalyzed())
					{
						attackingPlayer.getActivePokemon().setWasParalyzed(false);
						attackingPlayer.getActivePokemon().setCondition(Condition.NONE);
						System.out.print(printHint(attackingPlayer.getActivePokemon().getName() + " is no longer paralyzed!"));
					}
					
					// first logical turn being paralyzed?
					else
					{
						attackingPlayer.getActivePokemon().setWasParalyzed(true);
						System.out.print(printHint(attackingPlayer.getActivePokemon().getName() + " is doing it's best Stephen Hawking impersonation!"));
						System.out.print(printHint(attackingPlayer.getActivePokemon().getName() + " should be ready to go next turn!\n(Remember, you may 'pass' your turn if you have no moves to take!)"));
					}
				}
			}
			
			else // they did get a kill last turn
			{
				System.out.print(printHint("You just got a KO! But remember, you may 'switch' or 'pass', but not use a 'move' for this turn only!"));
				attackingPlayer.getActivePokemon().setGotKill(false);
				attackingPlayer.getActivePokemon().setChanceToMiss(100);
			}
			
			while(!endTurn)
			{
				System.out.print(printPrompt(attackingPlayer));
				String input = sc.nextLine();
				
				outcome = 0;
				
				// TODO implement pokemon powers (useable from the bench..)
				
				switch (input)
				{
					// Information Gathering
					case "pass":
							if(attackingPlayer.getActivePokemon().getHitPoints() <= 0)
							{
								outcome = -30;
							}
							
							else // allowed to 'pass'
							{
								endTurn = true;
							}

							break;
					
					case "moves": 
							System.out.print(printMoveList(attackingPlayer));
							break;
							
					case "switch":
							System.out.print(printSwitchMenu(attackingPlayer));
							break;
							
					case "switch -detailed":
							System.out.print(printDetailedSwitchMenu(attackingPlayer));
							break;
							
					case "bench":
							System.out.print(printBenchCount());
							break;
							
					case "target":
							System.out.print(printTargetDetails());
							break;
							
					case "examine":
							System.out.print(printExamineDetails());
							break;
					
					case "m1":
							outcome = useMove(0, attackingPlayer, defendingPlayer);
							break;
							
					case "m2":
							outcome = useMove(1, attackingPlayer, defendingPlayer);
							break;
						
					case "m3":
							outcome = useMove(2, attackingPlayer, defendingPlayer);
							break;
						
					case "m4":
							outcome = useMove(3, attackingPlayer, defendingPlayer);
							break;
							
					case "s1":
							outcome = switchPokemon(0, attackingPlayer);
							break;
							
					case "s2":
							outcome = switchPokemon(1, attackingPlayer);
							break;
						
					case "s3":
							outcome = switchPokemon(2, attackingPlayer);
							break;
						
					case "s4":
							outcome = switchPokemon(3, attackingPlayer);
							break;
						
					case "s5":
							outcome = switchPokemon(4, attackingPlayer);
							break;
						
					case "s6":
							outcome = switchPokemon(5, attackingPlayer);
							break;
							
					case "flee":
					case "gg":
							System.out.print(forfeit(attackingPlayer));
							endTurn = true;
							isBattling = false;
							break;
					
					default: System.out.print(printHelpMenu());
				}
				
				// Check if the turn should end or not..
				// If it shouldn't, then print a hint as to why not.
				
				// outcome key
				
				/* 
				 * outcome - MOVES 
				 * 
				 * return -11 if the pokemon is fainted (non-turn ending)
				 * return -12 if the pokemon is asleep (non-turn ending)
				 * return -13 if the pokemon is paralyzed (non-turn ending)
				 * return -14 if invalid move number (non-turn ending)
				 * return -15 if not enough energy to perform the move (non-turn ending)
				 * 
				 * *use energies*
				 * 
				 * return -16 if the pokemon is confused and attacks itself instead (turn ending)
				 * return -17 if the pokemon misses due to some effect (turn ending)
				 * 
				 * return  10 if the move was successful (turn ending)
				 * return  11 if the move was not very effective... (turn ending)
				 * return  12 if the move was super-effective! (turn ending)
				 * 
				 */
				
				/*
				 * outcome - SWITCHES
				 * 
				 * -21 - invalid slot number (no pokemon there) (non-turn ending)
				 * -22 - trying to switch active pokemon with itself (non-turn ending)
				 * -23 - active Pokemon is asleep (non-turn ending)
				 * -24 - active Pokemon is paralyzed (non-turn ending)
				 * -25 - selected benched Pokemon is fainted (non-turn ending)
				 * 
				 * *clear active pokemon's conditions*
				 * 
				 * 20 - all okay, do the switch (turn ending)
				 */
				
				/*
				 * outcome - OTHER
				 * 
				 * -30 - tried to pass when a KO'd pokemon was active (non-turn ending)
				 */
				
				if(endTurn == false)
				{
					switch(outcome)
					{
						// Turn should end..
						case -16:
						case -17:
						case  10:
						case  11:
						case  12:
						case  20:
								endTurn = true;
								break;
						
						// Turn should not end..
						case -11:
								System.out.print(printHint("Your active Pokemon isn't a zombie!  Please switch it out to keep battling!"));
								break;
						case -12:
								System.out.print(printHint("Uh.. Now of all times..?  Your active Pokemon is taking a snooze!  Maybe it'll wake up next turn.."));
								break;
						case -13:
								System.out.print(printHint("Oh no!  Your active Pokemon is paralyzed!  It will be ready to go next turn!"));
								break;
						case -14:
								System.out.print(printHint("Your active Pokemon doesn't know the selected move!  Please review the command 'moves' if you need a refresher!"));
								break;
						case -15:
								System.out.print(printHint("Your active Pokemon has no more PP! (teh-heh-heh)  Please choose a different move or switch it out! (don't forget about the 'pass' command either!)"));
								break;
						case -21:
								System.out.print(printHint("You do not have a Pokemon assigned to that bench slot!  Please review the command 'switch' if you need a refresher!"));
								break;
						case -22:
								System.out.print(printHint("That Pokemon is already active in battle!"));
								break;
						case -23:
								System.out.print(printHint("Pokemon that are sleeping can not be switched!"));
								break;
						case -24:
								System.out.print(printHint("Pokemon that are paralyzed can not be switched! (Thank Ryan for that..)"));
								break;
						case -25:
								System.out.print(printHint("Fainted Pokemon tell no tales.. please select a conscious Pokemon!"));
								break;
						case -30:
								System.out.print(printHint("You must switch-out your fainted active Pokemon!"));
								break;
								
							
						default: ; // do nothing (leave endTurn false, assume outcome == 0, give this player another prompt, turn not over)
					}
				}
			}
			
			// deal poison damage, if poisoned
			if(attackingPlayer.getActivePokemon().isPoisoned())
			{
				System.out.print(printHint(attackingPlayer.getActivePokemon().getName() + " is poisoned and has taken 10 damage!"));
				System.out.print(printHint(dealRawDamage(10, attackingPlayer.getActivePokemon())));
			}
			
			System.out.print(printEndOfTurnResults(outcome, attackingPlayer, defendingPlayer));
			
			// TODO update both benches and show who is fainted and alive (GUI specific, so do the GUI first)
			// getListOfAllAlivePokemon();
			
			/*
			 * checkForLoss key
			 * 0 - no one, game on
			 * 1 - blu won
			 * 2 - red won
			 * 3 - draw game
			 */
			switch(checkForLoss())
			{
				case 1:
					System.out.print(printWinString(blu, red, false));
					isBattling = false;
					break;
					
				case 2:
					System.out.print(printWinString(red, blu, false));
					isBattling = false;
					break;
					
				case 3:
					System.out.print(printWinString(blu, blu, true));
					isBattling = false;
					break;
					
				default: ; // do nothing, battle on..
			}
			
			isBlusTurn = !isBlusTurn;
		}
	}

	// Display active pokemon's name and current HP with a prompt..
	private static String printPrompt(Player currentPlayer)
	{
		return currentPlayer.getActivePokemon().showActiveDetails() + " (type '?' for help) > ";
	}
	
	// Print a hint..
	private static String printHint(String printMe)
	{
		return printMe + "\n";
	}
	
	// COMMAND METHODS

	private static String printHelpMenu()
	{	
		String helpMenu = "";
		
		helpMenu += "'?' or 'help'      - Show this help menu\n";
		helpMenu += "'pass'             - End your current turn\n";
		helpMenu += "'moves'            - Show your moves\n";
		helpMenu += "'m1' though 'm4'   - Use your active Pokemon's attack as shown from the 'moves' command\n";
		helpMenu += "'switch'           - See all of your Pokemon (names only)\n";
		helpMenu += "'switch -detailed' - See all of your Pokemon with extended details (name, hp, pp, type, weakness, resistance, and status effects)\n";
		helpMenu += "'s1' though 's6'   - Switch your active Pokemon with one of your benched Pokemon as shown from the 'switch' and 'switch -extended' commands\n";
		helpMenu += "'bench'            - See how many useable Pokemon each trainer has\n";
		helpMenu += "'target'           - Show your opponent's active Pokemon's details\n";
		helpMenu += "'examine'          - Show your active Pokemon's details\n";
		helpMenu += "'flee' or 'gg'     - Forfeit this battle\n";
		
		helpMenu += "\n";
		return helpMenu;
	}
	
	private static String printMoveList(Player currentPlayer)
	{
		return currentPlayer.activePokemon.showMoves() + "\n";
	}
	
	private static String printSwitchMenu(Player currentPlayer)
	{
		return currentPlayer.showAllPokemonBasic() + "\n";
	}
	
	private static String printDetailedSwitchMenu(Player currentPlayer)
	{
		return currentPlayer.showAllPokemonDetailed() + "\n";
	}
	
	private static String printBenchCount()
	{
		String benchCount = "";
		
		benchCount += red.getName() + " has " + countPokemon(red) + " Pokemon remaining!" + "\n";
		benchCount += blu.getName() + " has " + countPokemon(blu) + " Pokemon remaining!" + "\n";
		
		return benchCount;
	}
	
	private static int countPokemon(Player player)
	{
		int useablePokemon = 0;
		
		for(int i = 0; i < player.getPokemon().length; i++)
		{
			if(player.getPokemon()[i].getHitPoints() > 0)
			{
				useablePokemon = useablePokemon + 1;
			}
		}
		
		return useablePokemon;
	}
	
	private static Pokemon[] getListOfAllAlivePokemon()
	{
		Pokemon listOfAlive[] = new Pokemon[12];
		
		// red is in slots 0 though 5
		for(int i = 0; i < red.getPokemon().length; i++)
		{
			if(red.getPokemon()[i].getHitPoints() > 0)
			{
				listOfAlive[i] = red.getPokemon()[i];
			}
		}
		
		// blu is in slots 6 though 11
		for(int i = 0; i < blu.getPokemon().length; i++)
		{
			if(blu.getPokemon()[i].getHitPoints() > 0)
			{
				listOfAlive[i + 6] = blu.getPokemon()[i];
			}
		}
		
		return listOfAlive;
	}
	
	/*
	 * returns who lost
	 * 
	 * key
	 * 0 - no one, game on
	 * 1 - blu won
	 * 2 - red won
	 * 3 - draw game
	 */
	private static int checkForLoss()
	{
		if(countPokemon(red) <= 0 && countPokemon(blu) <= 0)
		{
			return 3;
		}
		
		if(countPokemon(blu) <= 0)
		{
			return 2;
		}
		
		if(countPokemon(red) <= 0)
		{
			return 1;
		}
		
		return 0;
	}
	
	private static String printTargetDetails()
	{
		if(isBlusTurn)
		{
			return red.getActivePokemon().showExtenedDetails() + "\n";
		}
		
		else
		{
			return blu.getActivePokemon().showExtenedDetails() + "\n";
		}
	}
	
	private static String printExamineDetails()
	{
		if(isBlusTurn)
		{
			return blu.getActivePokemon().showExtenedDetails() + "\n";
		}
		
		else
		{
			return red.getActivePokemon().showExtenedDetails() + "\n";
		}
	}
	
	// outcome key (only using turn ending events.. -- non-turn ending events are handled above..)
	
	/* MOVES 
	 * 
	 * return -11 if the pokemon is fainted (non-turn ending)
	 * return -12 if the pokemon is asleep (non-turn ending)
	 * return -13 if the pokemon is paralyzed (non-turn ending)
	 * return -14 if invalid move number (non-turn ending)
	 * return -15 if not enough energy to perform the move (non-turn ending)
	 * 
	 * *use energies*
	 * 
	 * return -16 if the pokemon is confused and attacks itself instead (turn ending)
	 * return -17 if the pokemon misses due to some effect (turn ending)
	 * 
	 * return  10 if the move was successful (turn ending)
	 * return  11 if the move was not very effective... (turn ending)
	 * return  12 if the move was super-effective! (turn ending)
	 * 
	 */
	private static String printEndOfTurnResults(int outcome, Player attackingPlayer, Player defendingPlayer)
	{
		switch(outcome)
		{
			case -16:
					return "self out" + attackingPlayer.getActivePokemon().getName() + " attacked has it's ???! of\n";
					
			case -17:
					return attackingPlayer.getActivePokemon().getName() + " has missed " + defendingPlayer.getActivePokemon().getName() + "!\n";
					
			case 11:
					return attackingPlayer.getActivePokemon().getName() + "'s attack was not very effective..\n";
					
			case 12:
					return attackingPlayer.getActivePokemon().getName() + "'s attack was super-effective!!\n";
				
			default:
					return "";
		}
	}
	
	private static String forfeit(Player currentPlayer)
	{
		String forfeitString = "";
		
		if(isBlusTurn)
		{
			forfeitString += blu.getName() + " has forfeited!" + "\n";
			forfeitString += red.getName() + " is victorious!" + "\n";
		}
		
		else
		{
			forfeitString += red.getName() + " has forfeited!" + "\n";
			forfeitString += blu.getName() + " is victorious!" + "\n";
		}
		
		return forfeitString;
	}
	
	/* MOVES 
	 * 
	 * return -11 if the pokemon is fainted (non-turn ending)
	 * return -12 if the pokemon is asleep (non-turn ending)
	 * return -13 if the pokemon is paralyzed (non-turn ending)
	 * return -14 if invalid move number (non-turn ending)
	 * return -15 if not enough energy to perform the move (non-turn ending)
	 * 
	 * *use energies*
	 * 
	 * return -16 if the pokemon is confused and attacks itself instead (turn ending)
	 * return -17 if the pokemon misses due to some effect (turn ending)
	 * 
	 * return  10 if the move was successful (turn ending)
	 * return  11 if the move was not very effective... (turn ending)
	 * return  12 if the move was super-effective! (turn ending)
	 * 
	 */
	private static int useMove(int moveNumber, Player attacker, Player defender)
	{	
		// check if the attacking pokemon is fainted
		if(attacker.getActivePokemon().getHitPoints() <= 0)
		{
			return -11;
		}
		
		// check if the attacking pokemon is asleep
		if(attacker.getActivePokemon().getCondition().equals(Condition.ASLEEP))
		{
			return -12;
		}
		
		// check if the attacking pokemon is paralyzed
		if(attacker.getActivePokemon().getCondition().equals(Condition.PARALYZED))
		{
			return -13;
		}
		
		// check if the attacking pokemon has the selected moveNumber
		if(attacker.getActivePokemon().getMoves().length <= moveNumber)
		{
			return -14;
		}
		
		// check if the attacking pokemon has the energy to use the selected move
		if(attacker.getActivePokemon().getEnergyCount() < attacker.getActivePokemon().getMoves()[moveNumber].getEnergyCost())
		{
			return -15;
		}
		
		// burn the attacking pokemon's attack's energy cost
		attacker.getActivePokemon().setEnergyCount(attacker.getActivePokemon().getEnergyCount() - attacker.getActivePokemon().getMoves()[moveNumber].getEnergyCost());
		
		// check if the attacking pokemon is confused, and attacks themselves as a result of being confused
		if(attacker.getActivePokemon().getCondition().equals(Condition.CONFUSED))
		{
			// flip a coin, if true, the pokemon hit themselves out of confusion
			if(random.nextBoolean())
			{
				System.out.print(printHint(dealRawDamage(10, attacker.getActivePokemon())));
				return -16;
			}
		}
		
		// check if the attacking pokemon misses for some other reason (and then reset their miss chance for their next turn)
		// range of 0 to 100 (inclusive)
		if(random.nextInt(101) <= attacker.getActivePokemon().getChanceToMiss())
		{
			attacker.getActivePokemon().setChanceToMiss(0);
			return -17;
		}
		
		// default to a standard hit
		int howEffective = 10;
		
		// check if the attacking pokemon scored a not very effective.. hit (due do weakness)
		if(hasResistance(attacker.getActivePokemon(), defender.getActivePokemon()))
		{
			howEffective = 11;
		}
		
		// check if the attacking pokemon scored a super-effective hit (due do weakness)
		if(hasWeakness(attacker.getActivePokemon(), defender.getActivePokemon()))
		{
			howEffective = 12;
		}
		
		// do the damage and effects..
		System.out.print(printHint(doFullAttack(attacker.getActivePokemon().getMoves()[moveNumber].getDamage(), moveNumber, attacker, defender)));
		
		return howEffective;
	}
	
	private static String doFullAttack(int damageAmount, int moveNumber, Player attacker, Player defender)
	{
		String attackDescription = "";
		
		attackDescription += calcAndDoDamage(damageAmount, attacker.getActivePokemon(), defender.getActivePokemon());
		attackDescription += doAttackEffect(moveNumber, attacker, defender);
		
		if(isFainted(defender.getActivePokemon()))
		{
			attackDescription += defender.getActivePokemon().getName() + " has been taken down!\n";
			defender.getActivePokemon().setCondition(Condition.NONE);
			defender.getActivePokemon().setPoisoned(false);
			attackDescription += attacker.getActivePokemon().getName() + " may 'switch' or 'pass' but not attack during their next turn!\n";
			attacker.getActivePokemon().setGotKill(true);
		}
		
		return attackDescription;
	}
	
	private static String calcAndDoDamage(int damageAmount, Pokemon attacker, Pokemon defender)
	{
		int damageToDo = damageAmount;
		
		// check for, and apply, weakness
		if(hasWeakness(attacker, defender))
		{
			damageToDo = damageToDo * 2;
		}
		
		// check for, and apply, resistance
		if(hasResistance(attacker, defender))
		{
			damageToDo = damageToDo - defender.getResistanceAmount();
		}
		
		// make sure damage isn't below 0..
		if(damageToDo < 0)
		{
			damageToDo = 0;
		}
		
		// do the actual damage
		defender.setHitPoints(defender.getHitPoints() - damageToDo);
		
		String returnString = "";
		
		// show nothing if 0 damage is done (for attacks that do effects..)
		if(damageToDo > 0)
		{
			returnString += attacker.getName() + " has done " + damageToDo + " damage!\n";
		}
		
		return returnString;
	}
	
	private static boolean hasWeakness(Pokemon attacker, Pokemon defender)
	{
		if(attacker.getType().equals(defender.getWeakness()))
		{
			return true;
		}
		
		return false;
	}
	
	private static boolean hasResistance(Pokemon attacker, Pokemon defender)
	{
		if(attacker.getType().equals(defender.getResistance()))
		{
			return true;
		}
		
		return false;
	}
	
	// returns description of what happened
	private static String doAttackEffect(int attackerMoveNumber, Player attacker, Player defender)
	{
		return attacker.getActivePokemon().getMoves()[attackerMoveNumber].doAttackEffect(attacker, defender);
	}
	
	// deals raw, unmodified, damage to the target
	private static String dealRawDamage(int damageAmount, Pokemon target)
	{
		target.setHitPoints(target.getHitPoints() - damageAmount);
		
		String returnString = target.getName() + " has taken " + damageAmount + " damage!\n";
		
		if(isFainted(target))
		{
			returnString += target.getName() + " has gone down!\n";
		}
		
		return returnString;
	}
	
	// check if the selected Pokemon is fainted
	private static boolean isFainted(Pokemon pokemon)
	{
		if(pokemon.getHitPoints() <= 0)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	/*
	 * switching return key
	 * 
	 * -21 - invalid slot number (no pokemon there) (non-turn ending)
	 * -22 - trying to switch active pokemon with itself (non-turn ending)
	 * -23 - active Pokemon is asleep (non-turn ending)
	 * -24 - active Pokemon is paralyzed (non-turn ending)
	 * -25 - selected benched Pokemon is fainted (non-turn ending)
	 * 
	 * *clear active pokemon's conditions*
	 * 
	 * 20 - all okay, do the switch (turn ending)
	 */
	private static int switchPokemon(int pokemonSlotNumber, Player currentPlayer)
	{
		// check if the attacking pokemon has the selected moveNumber
		if(currentPlayer.getPokemon().length <= pokemonSlotNumber)
		{
			return -21;
		}
		
		// check if the current Pokemon is the Pokemon that we are trying to switch to
		if(currentPlayer.getActivePokemon().equals(currentPlayer.getPokemon()[pokemonSlotNumber]))
		{
			return -22;
		}
		
		// check if the active Pokemon is asleep
		if(currentPlayer.getActivePokemon().getCondition().equals(Condition.ASLEEP))
		{
			return -23;
		}
		
		// check if the active Pokemon is paralyzed
		if(currentPlayer.getActivePokemon().getCondition().equals(Condition.PARALYZED))
		{
			return -24;
		}
		
		// check if the benched Pokemon is actually alive
		if(currentPlayer.getPokemon()[pokemonSlotNumber].getHitPoints() <= 0)
		{
			return -25;
		}
		
		// if the active Pokemon is confused, then clear it
		if(currentPlayer.getActivePokemon().getCondition().equals(Condition.CONFUSED))
		{
			currentPlayer.getActivePokemon().setCondition(Condition.NONE);
		}
		
		// if the active Pokemon is poisoned, then clear it
		if(currentPlayer.getActivePokemon().isPoisoned())
		{
			currentPlayer.getActivePokemon().setPoisoned(false);
		}
		
		// actually do the switch -- and print some stuff of wtf is actually going on..
		System.out.print(printHint(currentPlayer.getActivePokemon().getName() + "! That's enough, come back!"));
		currentPlayer.getActivePokemon().setActive(false);
		currentPlayer.setActivePokemon(currentPlayer.pokemon[pokemonSlotNumber]);
		
		// random speech for no reason
		switch(random.nextInt(4)) // a number between 0 and 3 (inclusive)
		{
			case 0:
					System.out.print(printHint("Go get 'em " + currentPlayer.getActivePokemon().getName() + "!"));
					break;
					
			case 1:
					System.out.print(printHint(currentPlayer.getActivePokemon().getName() + "! Lets show them who the real daddy is!"));
					break;
			
			case 2:
					System.out.print(printHint(currentPlayer.getActivePokemon().getName() + "! Go fuck their day up!"));
					break;
					
			case 3:
					System.out.print(printHint(currentPlayer.getActivePokemon().getName() + "! " + currentPlayer.getActivePokemon().getName() + "! He's our man!  If he can't do it, then he's banished to box 20 with the other losers!"));
					break;
			
			default: System.out.print(printHint("Goooo " + currentPlayer.getActivePokemon().getName() + "!"));
		}
		
		return 20;
	}
	
	private static String printWinString(Player winner, Player loser, boolean isDraw)
	{
		if(isDraw)
		{
			return "WOW!!  They're down!  They're both down!!\n" + 
					"This battle is a draw!\n";
		}
		
		return "And there goes the battle!\n" + 
				winner.getName() + " has defeated " + loser.getName() + "!\n";
	}
}
