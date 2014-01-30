package battler2;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.swing.AutoCompleteSupport;

public class Battler2UI extends JFrame
{
	private static final long	serialVersionUID	= -2608356796626531611L;
	private static final String VERSION_NUMBER = "0.0.3.0 ALPHA BUILD v1.0";
	
	private Container container;
	
	private Object[] pokemonListFirstSlot = new Object[] {
			"Abra",
			"Aerodactyl",
			"Arcanine",
			"Articuno",
			"Charmander",
			"Growlithe",
			"Magneton",
			"Voltorb"
	};
	
	private Object[] pokemonList = new Object[] {
			"NONE",
			"Abra",
			"Aerodactyl",
			"Arcanine",
			"Articuno",
			"Charmander",
			"Growlithe",
			"Magneton",
			"Voltorb"
	};
	
	/*
	 * GUI VARIABLES - WildBattleSetupUI
	 */
	private JComboBox<?>  pokemon1,
					   pokemon2,
					   pokemon3,
					   pokemon4,
					   pokemon5,
					   pokemon6;
	
	private JSpinner   energy1,
					   energy2,
					   energy3,
					   energy4,
					   energy5,
					   energy6;
	
	private CheckboxGroup rarity;
	
	private Checkbox common,
					  uncommon,
					  rare,
					  legendary;
	
	private JButton beginWildBattle;
	
	/*
	 * GUI VARIABLES - BattleUI
	 */
	private InfoBox red;
	private InfoBox blu;
	
	private JButton move1,
				   move2,
				   move3,
				   move4,
				   switch1,
				   switch2,
				   switch3,
				   switch4,
				   switch5,
				   switch6,
				   viewMoves,
				   viewBench,
				   forfeitGame,
				   passTurn;
	
	private JTextArea console;
	
	public Battler2UI()
	{
		initDefaultUI();
		// initSplashScreenUI(); // TODO show the splash screen when the program is loaded..
	}
	
	/**
	 * TODO LIST OF SORTS
	 * 
	 * - Finish Wild Battle setup (have it transition right into a battle (send params of what's picked to the battler)
	 * - Finish Battle setup (needs to take a boatload of information and translate it to it's UI)
	 * - Redo Battle logic............. fuck.. (so that actual battles can take place)
	 * 
	 * - Implement the trainer battle side of things (should be easy if everything else is working well)
	 * - Implement a splash screen of sorts for kicks (again, should be trivial; even on a bad day...) // when program is first loaded (show buttons for new wild battle or new trainer battle)
	 */
	
	private void initDefaultUI()
	{
		setTitle("Ryan's Pokeman Battler - v" + VERSION_NUMBER);
		setSize(612, 640);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		createMenuBar();
		
		container = getContentPane();
	}
	
	private void createMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		
		JMenu startNew = new JMenu("New..");
		
		JMenuItem wildBattle = new JMenuItem("Wild Battle");
		wildBattle.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				initWildBattleSetupUI();
			}

		});
		
		JMenuItem trainerBattle = new JMenuItem("Trainer Battle");
		
		startNew.add(wildBattle);
		startNew.add(trainerBattle);
		
		menuBar.add(startNew);
		
		setJMenuBar(menuBar);
	}
	
	/*
	 * initWildBattleSetupUI - START
	 */
	private void initWildBattleSetupUI()
	{
		container.removeAll();
		
		container = getContentPane();
		container.setLayout(new FlowLayout());
		
		createPokemonList(container);
		createTierSelection(container);
		createBeginWildBattleButton(container);
		
		repaint();
		revalidate();
	}
	
	private void createPokemonList(Container container)
	{
		JPanel panel = new JPanel(new GridLayout(-1, 2, 5, 5));
		
		JLabel pokemon = new JLabel("Your Pokemon");
		panel.add(pokemon);
		
		JLabel energy = new JLabel("Attached Energies");
		panel.add(energy);
		
		pokemon1 = new JComboBox<Object>();
		AutoCompleteSupport<Object> support = AutoCompleteSupport.install(pokemon1, GlazedLists.eventListOf(pokemonListFirstSlot));
		support.setStrict(true);
		panel.add(pokemon1);
		
		SpinnerModel spinnerModel1 = new SpinnerNumberModel(0, 0, 100, 1);
		energy1 = new JSpinner(spinnerModel1);
		panel.add(energy1);
		
		pokemon2 = new JComboBox<Object>();
		support = AutoCompleteSupport.install(pokemon2, GlazedLists.eventListOf(pokemonList));
		support.setStrict(true);
		panel.add(pokemon2);
		
		SpinnerModel spinnerModel2 = new SpinnerNumberModel(0, 0, 100, 1);
		energy2 = new JSpinner(spinnerModel2);
		panel.add(energy2);
		
		pokemon3 = new JComboBox<Object>();
		support = AutoCompleteSupport.install(pokemon3, GlazedLists.eventListOf(pokemonList));
		support.setStrict(true);
		panel.add(pokemon3);
		
		SpinnerModel spinnerModel3 = new SpinnerNumberModel(0, 0, 100, 1);
		energy3 = new JSpinner(spinnerModel3);
		panel.add(energy3);
		
		pokemon4 = new JComboBox<Object>();
		support = AutoCompleteSupport.install(pokemon4, GlazedLists.eventListOf(pokemonList));
		support.setStrict(true);
		panel.add(pokemon4);
		
		SpinnerModel spinnerModel4 = new SpinnerNumberModel(0, 0, 100, 1);
		energy4 = new JSpinner(spinnerModel4);
		panel.add(energy4);
		
		pokemon5 = new JComboBox<Object>();
		support = AutoCompleteSupport.install(pokemon5, GlazedLists.eventListOf(pokemonList));
		support.setStrict(true);
		panel.add(pokemon5);
		
		SpinnerModel spinnerModel5 = new SpinnerNumberModel(0, 0, 100, 1);
		energy5 = new JSpinner(spinnerModel5);
		panel.add(energy5);
		
		pokemon6 = new JComboBox<Object>();
		support = AutoCompleteSupport.install(pokemon6, GlazedLists.eventListOf(pokemonList));
		support.setStrict(true);
		panel.add(pokemon6);
		
		SpinnerModel spinnerModel6 = new SpinnerNumberModel(0, 0, 100, 1);
		energy6 = new JSpinner(spinnerModel6);
		panel.add(energy6);
		
		container.add(panel);
	}
	
	private void createTierSelection(Container container)
	{
		JPanel panel = new JPanel(new GridLayout(2, 8, 5, 5));
		
		rarity = new CheckboxGroup();
		
		common = new Checkbox("Hunt Beginner Wild");
		common.setCheckboxGroup(rarity);
		panel.add(common);
		
		uncommon = new Checkbox("Hunt Intermediate Wild");
		uncommon.setCheckboxGroup(rarity);
		panel.add(uncommon);
		
		rare = new Checkbox("Hunt Advanced Wild");
		rare.setCheckboxGroup(rarity);
		panel.add(rare);
		
		legendary = new Checkbox("Hunt Ultimate Wild");
		legendary.setCheckboxGroup(rarity);
		panel.add(legendary);
		
		rarity.setSelectedCheckbox(common);
		
		container.add(panel);
	}
	
	private void createBeginWildBattleButton(Container container)
	{
		JPanel panel = new JPanel();
		
		beginWildBattle = new JButton("Begin the Hunt!");
		beginWildBattle.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// initBattleUI(); // TODO feed the UI information to some function that starts up the battle logic..
			}

		});
		panel.add(beginWildBattle);
		
		container.add(panel);
	}
	/*
	 * initWildBattleSetupUI - END
	 */
	
	/*
	 * initBattleUI - START
	 */
	private void initBattleUI()
	{	
		container.removeAll();
		
		container = getContentPane();
		container.setLayout(new FlowLayout());
		
		createInfoBoxes(container);
		createMainControls(container);
		createConsole(container);
		createEndControls(container);
		
		repaint();
		revalidate();
	}
	
	private void createInfoBoxes(Container container)
	{
		JPanel panel = new JPanel();
		
		red = new InfoBox(Color.RED);
		red.setPreferredSize(new Dimension(250, 200));
		panel.add(red);
		
		blu = new InfoBox(Color.BLUE);
		blu.setPreferredSize(new Dimension(250, 200));
		panel.add(blu);
		
		container.add(panel);
	}
	
	private void createMainControls(Container container)
	{
		createMoveControls(container);
		createGap(container);
		createGap(container);
		createSwitchControls(container);
		createGap(container);
		createGap(container);
		createViewControls(container);
	}
	
	private void createMoveControls(Container container)
	{
		JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
		
		move1 = new JButton("Move 1");
		move2 = new JButton("Move 2");
		move3 = new JButton("Move 3");
		move4 = new JButton("Move 4");
		
		panel.add(move1);
		panel.add(move2);
		panel.add(move3);
		panel.add(move4);
		
		container.add(panel);
	}
	
	private void createGap(Container container)
	{
		JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
		
		JLabel blank = new JLabel();
		panel.add(blank);
		panel.add(blank);
		
		container.add(panel);
	}
	
	private void createSwitchControls(Container container)
	{
		JPanel panel = new JPanel(new GridLayout(2, 3, 5, 5));
		
		switch1 = new JButton("Switch 1");
		switch2 = new JButton("Switch 2");
		switch3 = new JButton("Switch 3");
		switch4 = new JButton("Switch 4");
		switch5 = new JButton("Switch 5");
		switch6 = new JButton("Switch 6");
		
		panel.add(switch1);
		panel.add(switch2);
		panel.add(switch3);
		panel.add(switch4);
		panel.add(switch5);
		panel.add(switch6);
		
		container.add(panel);
	}
	
	private void createViewControls(Container container)
	{
		JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
		
		viewMoves = new JButton("View Moves");
		viewBench = new JButton("View Bench");
		
		panel.add(viewMoves);
		panel.add(viewBench);
		
		container.add(panel);
	}
	
	private void createConsole(Container container)
	{
		JPanel panel = new JPanel();
		
		console = new JTextArea(15, 45);
		console.setLineWrap(true);
		console.setWrapStyleWord(true);
		console.setEditable(false);
		
		
		JScrollPane scroll = new JScrollPane(console);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		panel.add(scroll);
		
		container.add(panel);
	}
	
	private void createEndControls(Container container)
	{
		JPanel panel = new JPanel();
		
		forfeitGame = new JButton("Forfeit Game");
		passTurn = new JButton("Pass Turn");
		
		panel.add(forfeitGame);
		panel.add(passTurn);
		
		container.add(panel);
	}
	/*
	 * initBattleUI - END
	 */

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				Battler2UI b2 = new Battler2UI();
				b2.setVisible(true);
			}
		});
	}
}
