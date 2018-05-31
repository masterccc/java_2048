import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Fenêtre dans laquelle le jeu apparait
 *
 */
public class Frame extends JFrame {
	
	JPanel mainPanel ;
	JPanel options ;
	JPanel scorePan ;
	JLabel lblScore ;
	MyBtn btnReplay ;
	
	/**
	 * Crée la fenêtre
	 */
	public Frame(){
		
		this.setTitle("2048");
		this.setSize(new Dimension(330,500));
		this.setResizable(false);
		
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		options = new JPanel();
		options.setLayout(new BoxLayout(options, BoxLayout.LINE_AXIS));
		
		
		lblScore = new JLabel("Score");
		lblScore.setPreferredSize(new Dimension(300,30));
		lblScore.setBackground(new Color(0, 0, 255));;
		scorePan = new JPanel();

		scorePan.setPreferredSize(new Dimension(300,50));
		scorePan.setLayout(new BorderLayout());
		scorePan.add(lblScore, BorderLayout.CENTER);
		
		
		btnReplay = new MyBtn();
		
		options.setBackground(new Color(187, 173, 160));
		options.setPreferredSize(new Dimension(330,50));
		options.setSize(new Dimension(330,50));
		options.setMaximumSize(options.getSize());
		
		btnReplay.setSize(new Dimension(330,40));
		btnReplay.setMaximumSize(btnReplay.getSize());
		btnReplay.setPreferredSize(new Dimension(330,100));
		//options.add(lblScore);
		options.add(btnReplay);
		options.setVisible(true);

		mainPanel.setVisible(true);
		
		Board b = new Board(lblScore) ;

		KbEvent kb = new KbEvent(b, btnReplay);
		
		this.setFocusable(true);
		b.setFocusable(true);
		options.setFocusable(true);
		mainPanel.setFocusable(true);
		
		b.addKeyListener(kb);
		btnReplay.addActionListener(kb);

		mainPanel.add(lblScore, BorderLayout.NORTH);
		mainPanel.add(b, BorderLayout.CENTER);
		mainPanel.add(options, BorderLayout.SOUTH);

		this.setContentPane(mainPanel);
		this.setVisible(true);
		b.grabFocus();
		
	}
	
	public static void main(String[] args){
		Frame jeu = new Frame();
		
	}

}
