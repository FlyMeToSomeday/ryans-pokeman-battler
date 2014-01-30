package battler;

import pokemon.Pokemon;

public class Player
{
	protected String name;
	protected Pokemon pokemon[];
	protected Pokemon activePokemon;
	
	public Player(String name, Pokemon pokemon[])
	{
		this.name = name;
		this.pokemon = pokemon;
		this.activePokemon = null;
	}
	
	public String showAllPokemonBasic()
	{
		if(pokemon.length <= 0)
		{
			return "No Pokemon!";
		}
		
		String basicPokemonList = "";
		
		for(int i = 0; i < pokemon.length; i++)
		{
			basicPokemonList += "'s";
			basicPokemonList += i+1; // # command to switch to this pokemon
			basicPokemonList += "'";
			
			if(pokemon[i].isActive())
			{
				basicPokemonList += " - ACTIVE";
			}
			
			basicPokemonList += " - " + pokemon[i].showBasicDetails() + "\n";
		}
		
		return basicPokemonList;
	}
	
	public String showAllPokemonDetailed()
	{
		if(pokemon.length <= 0)
		{
			return "No Pokemon!";
		}
		
		String detailedPokemonList = "";
		
		for(int i = 0; i < pokemon.length; i++)
		{
			detailedPokemonList += "'s";
			detailedPokemonList += i+1; // # command to switch to this pokemon
			detailedPokemonList += "'";
			
			if(pokemon[i].isActive())
			{
				detailedPokemonList += " - ACTIVE";
			}
			
			detailedPokemonList += " - " + pokemon[i].showExtenedDetails() + "\n";
		}
		
		return detailedPokemonList;
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

	public Pokemon[] getPokemon()
	{
		return pokemon;
	}

	public void setPokemon(Pokemon[] pokemon)
	{
		this.pokemon = pokemon;
	}

	public Pokemon getActivePokemon()
	{
		return activePokemon;
	}

	public void setActivePokemon(Pokemon activePokemon)
	{
		if(this.activePokemon != null)
		{
			this.activePokemon.setActive(false); // un-set old pokemon
		}
		
		this.activePokemon = activePokemon; // set new pokemon
		this.activePokemon.setActive(true);
	}
}
