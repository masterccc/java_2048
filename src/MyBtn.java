import java.awt.Graphics;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

/**
 * Bouton pour rejouer la partie
 *
 */
public class MyBtn extends JButton {
	
	Font ft = new Font("TimesRoman", Font.BOLD, 25);
	FontMetrics metrics;
	
	String msg = "New Game" ;
	
	public void paintComponent(Graphics g){
		//super.paintComponent(g);
		g.setColor(new Color(143, 122, 102));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		metrics = g.getFontMetrics(ft);
		g.setColor(Color.white);
		g.setFont(ft);
		g.drawString(msg, metrics.stringWidth(msg) / 2, this.getHeight() - 10);
		
	}
}
