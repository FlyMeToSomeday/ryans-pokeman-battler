package battler2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class InfoBox extends JPanel
{
	private static final long	serialVersionUID	= 8969740174580268530L;
	
	private Color mainColor;
	
	public InfoBox(Color mainColor)
	{
		this.mainColor = mainColor;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		drawBackground(g2d, mainColor);
	}
	
	private void drawBackground(Graphics2D g2d, Color backgroundColor)
	{
		g2d.setColor(backgroundColor);
		g2d.fillRect(0, 0, 300, 500);
	}
}
