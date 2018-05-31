import java.util.Random;

/**
 * Crée l'"""intelligence""" artificielle
 *
 */
public class IA implements Runnable {

	private Board b;
	private Random r ;
	
	/**
	 * Constructeur
	 * @param b grille de jeu
	 */
	public IA(Board b){
		r = new Random();
		this.b = b ;	
	}
	
	/**
	 * Lance l'IA
	 */
	public void play(){
		
		boolean moveOk = true ;
		int rand ;
		int nothing = 0 ;
		boolean cont = true; 
		b.initBoard();
		
		/* Toujours bas, droite, gauche.  Haut uniquement si on reste bloqué */
		do {
			rand = r.nextInt(3);

			if(rand == 0) moveOk = b.left();
			else if(rand == 1) moveOk = b.right();
			else if(rand == 2) moveOk = b.down();
			
			
			if(!moveOk){ /* si ça marche pas -< on va vers le haut */
				if(nothing > 15){
					moveOk = b.up();
					nothing = 0;
					/* si ça marche pas c'est qu'on a perdu */
					if(!moveOk) cont = false ;
				}
				else{
					nothing++;
					continue;
				}
			}
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(moveOk){
				cont= b.addRandom();
				b.repaint();
			}
		} while(cont);
		
		System.out.println("L'ia a fini a la partie");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		play();
	}
	
}
