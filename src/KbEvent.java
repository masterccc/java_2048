import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;

import com.sun.glass.events.KeyEvent;

/**
 * Intercepte les appuis sur les touches
 *
 */
public class KbEvent implements KeyListener, ActionListener {
	
	Board b;
	JButton btn ;
	
	/**
	 * Constructeur
	 * @param b grille de jeu
	 * @param btn Bouton pour rejouer
	 */
	public KbEvent(Board b, JButton btn){
		this.b = b ;
		this.btn = btn ;
	}
	
	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
		
	}

	/**
	 * Récupérer la touche pressée
	 */
	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
	
		int code = e.getKeyCode() ;
		boolean refresh = true ;
		boolean hazard = true;
		/* Partie en cours */
		
		if(!b.getState()){
			if(code == KeyEvent.VK_UP ){
				System.out.println("bas");
				if(!b.up()) hazard = false ; 
			}
			else if(code == KeyEvent.VK_DOWN ){
				if(!b.down()) hazard = false ; 
					}
			else if(code == KeyEvent.VK_LEFT ){
				if(!b.left()) hazard = false ; 
			}
			else if(code == KeyEvent.VK_RIGHT ){
				if(!b.right()) hazard = false ; 
			}
			else if(code == KeyEvent.VK_I){
				IA dumb = new IA(b);
				new Thread(dumb).start();
			}
			else {
				refresh = false ;
			}
		}else {
			/* Perdu ou gagné - pas de refresh */
			refresh = false ;
			hazard = false ;
		}
		/* Partie interompue */
		if(code == KeyEvent.VK_J ){
				b.initBoard();
				b.setState(false);
				if(!refresh) refresh = true ;
				hazard = false; 
		}
		else if(code == KeyEvent.VK_ESCAPE ){
			System.exit(0);
		}
		
		if(refresh) b.repaint();
		if(hazard) b.addRandom();
		
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Hello");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btn ){
			b.initBoard();
			b.setState(false);
			b.setScore(0);
			b.grabFocus();
			b.repaint();
		}
		
	}

}
