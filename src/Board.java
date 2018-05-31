import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Plateau de jeu
 */

public class Board extends JPanel {

	public static int ROW = 4;
	public static int b = 66 ;
	
	private boolean arret= false;
	private int score;
	
	private ArrayList<ArrayList<Tile>> tiles_lines;
	private ArrayList<ArrayList<Tile>> tiles_col;
	
	JLabel lblScore ;
	
	Font ft = new Font("TimesRoman", Font.BOLD, 25);
	FontMetrics metrics;

	/**
	 * Créer le plateau de jeu avec 2 tuiles au hasard à 2
	 * @param lblScore
	 */
	public Board(JLabel lblScore) {

		this.lblScore = lblScore ;

		this.setBackground(new Color(187, 173, 160));

		arret = false;

		tiles_lines = new ArrayList<ArrayList<Tile>>(ROW);
		tiles_col = new ArrayList<ArrayList<Tile>>(ROW);

		for (int i = 0; i < ROW; i++) {
			tiles_lines.add(new ArrayList<Tile>());
			tiles_col.add(new ArrayList<Tile>());
		}

		int l = 0, c = 0;
		for (int ligne = 0; ligne < ROW; ligne++) {
			for (int col = 0; col < ROW; col++) {
				Tile t = new Tile(col, ligne);
				t.setValue(0);
				tiles_lines.get(ligne).add(t);
				tiles_col.get(col).add(t);
			}
		}

		initBoard();
		this.repaint();
		this.setVisible(true);
	}

	/**
	 * Met à zéro la grille de jeu et choisit deux cases au hasard pour démarrer la partie
	 */
	public void initBoard() {

		score = 0;
		lblScore.setText("Score");
		
		for (ArrayList<Tile> list : tiles_lines) {
			for (Tile t : list) t.setValue(0);
		}
		addRandom();
		addRandom();
	}

	/**
	 * Tire un nombre au hasard entre min et max
	 * @param min
	 * @param max
	 * @return Retourne un nombre au hasard
	 */
	public static int randInt(int min, int max) {

		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	/**
	 * Peint la grille de jeu
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int y = 10, x = 10;

		for (ArrayList<Tile> list : tiles_lines) {
			y += 80;
			x = 7;
			for (Tile t : list) {
				g.setColor(t.getColor());
				g.fillRect(x, y, 70, 70);
				if (t.getValue() > 0) {
					String display = String.valueOf(t.getValue());
					metrics = g.getFontMetrics(ft);
					g.setColor(Color.white);
					g.setFont(ft);
					g.drawString(display, (x + 35) - metrics.stringWidth(display) / 2, y + 43);
				}
				x += 80;
			}
		}

	}

	/**
	 * Ajoute une tuile aléatoire sur la grille
	 * @return
	 */
	public boolean addRandom() {
		ArrayList<Tile> free = new ArrayList<Tile>();

		for (ArrayList<Tile> list : tiles_lines) {
			for (Tile t : list) {
				if (t.getValue() == 0)
					free.add(t);
			}
		}

		if (free.isEmpty()) {
			perdu();
			return false;
		} else {
			free.get(randInt(0, free.size() - 1)).setValue(getFourOrTwo());
			return true ;
		}
	}

	/**
	 * Choisir la valeur 2 ou 4 aléatoirement pour choisir la valeur d'une nouvelle tuile
	 * @return
	 */
	public int getFourOrTwo() {
		int r = randInt(0, 20);
		if (r > 18)
			return 4;
		return 2;
	}


	/**
	 * Tasse une ligne de la grille, en fonction de la direction
	 * @param tiles ensemble des lignes de la grille
	 * @param m Sens du mouvement
	 * @return Retourne si le déplacement est effectif
	 */
	public boolean move( ArrayList<ArrayList<Tile>> tiles, Mouvement m ){
		/* Il faudrait au moins 10 diagrammes UML pour expliquer cette fonction :) */
		boolean moved = false ;
		boolean reverse = false ;
		int somme = 0 ;
		if( m == Mouvement.BAS || m == Mouvement.DROITE ) reverse = true ;
		ArrayList<Tile> tmp;

		for (ArrayList<Tile> line : tiles) {
			if(reverse) Collections.reverse(line);
			if(hole(line)) moved = true;
			tmp = removeZero(line);
			if(tmp.size() > 0){
				int tour = tmp.size() - 1;
					for (int i = 0; i < tour ; i += 1) {
						if(i < tmp.size()){
							Tile t = tmp.get(i);
							if ((i + 1) < tmp.size() && t.getValue() == tmp.get(i + 1).getValue()) {
								moved = true ;
								somme = t.getValue() *2 ;
								t.setValue(somme);
								tmp.get(i + 1).setValue(0);
								tmp = removeZero(tmp);
								score += somme; 
								lblScore.setText("Score :" + score );
								if(somme == 2048){
									
									win();
									return true; 
								}
							}
						}
					}
				
				/* Rangement de la ligne de départ */
				for (int d = 0; d < ROW; d++) {
					if (d < tmp.size()) {
						line.get(d).setValue(tmp.get(d).getValue());
					} else {
						line.get(d).setValue(0);
					}
				}
			}
			if(reverse) Collections.reverse(line);
		}
		
		return moved;
	}
	
	/**
	 * Mouvement des tuiles vers le haut
	 * @return Retourne vrai si le mouvement est effectif
	 */
	public boolean up() {
		return move(tiles_col, Mouvement.HAUT);
	}
	/**
	 * Mouvement des tuiles vers le bas
	 * @return Retourne vrai si le mouvement est effectif
	 */
	public boolean down() {
		return move(tiles_col, Mouvement.BAS);
	}

	/**
	 * Mouvement des tuiles vers lagauche
	 * @return Retourne vrai si le mouvement est effectif
	 */
	public boolean left() {
		return move(tiles_lines, Mouvement.GAUCHE);
	}

	/**
	 * Mouvement des tuiles vers la droite
	 * @return Retourne vrai si le mouvement est effectif
	 */
	public boolean right() {
		return move(tiles_lines, Mouvement.DROITE);
	}

	/**
	 * Detecte si il y a un creux dans la ligne
	 * @param a
	 * @return retourne vrai si il y a un creux
	 */
	public boolean hole(ArrayList<Tile> a){

		boolean gotzero = false ;

		for(Tile t : a ){
			if(gotzero && t.getValue() != 0) return true ;
			if(t.getValue() == 0 ) gotzero = true ;
		}
		
		return false ;
	}

	/**
	 * Renvoie une ligne de la grille sans cases vides
	 * @param a ligne en question
	 * @return retourne la ligne vidée de ses cases vides
	 */
	public ArrayList<Tile> removeZero(ArrayList<Tile> a) {
		ArrayList<Tile> tmp = new ArrayList<Tile>();
		for (Tile t : a) {
			if (t.getValue() != 0)
				tmp.add(t);
		}
		return tmp;
	}

	/**
	 * Déclenche la perte du joueur 
	 */
	public void perdu() {
		lblScore.setText("Perdu ! Score : " + score );
		System.exit(0);
		arret = true; 
	}

	/**
	 * Déclenche la victoire du joueur
	 */
	public void win(){
		lblScore.setText("Gagné ! Score : "+ score );
		arret = true ;
	}

	/**
	 * Retourne l'état de la partie
	 * @return Retourne vrai si la partie est terminée
	 */
	public boolean getState() {
		return arret;
	}

	/**
	 * Set l'etat de la partie
	 * @param b etat désiré
	 */
	public void setState(boolean b) {
		arret = b;

	}

	/**
	 * Modifie le score du joueur
	 * @param i nouveau score
	 */
	public void setScore(int i) {
		score = i ;
	}

}
