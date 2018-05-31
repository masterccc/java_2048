import java.awt.Color;

/**
 * Représente une tuile
 *
 */
public class Tile {

	private int x;
	private int y;
	private int value;

	private Color color;

	/* taille */
	public static int WIDTH = 70;
	public static int HEIGHT = 70;

	/* couleurs */
	public static Color _0c = new Color(205, 193, 180);
	public static Color _2c = new Color(230, 228, 218);
	public static Color _4c = new Color(237, 224, 200);
	public static Color _8c = new Color(242, 177, 121);
	public static Color _16c = new Color(245, 197, 99);
	public static Color _32c = new Color(246, 124, 95);
	public static Color _64c = new Color(246, 94, 59);
	public static Color _128c = new Color(237, 207, 114);
	public static Color _256c = new Color(237, 204, 97);
	public static Color _512c = new Color(237, 200, 80);
	public static Color _1024c = new Color(237, 197, 63);
	public static Color _2048c = new Color(237, 194, 46);

	/**
	 * Constructeur
	 * @param x coordonée x de départ
	 * @param y coordonée y de départ
	 */
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		this.value = 0;
		this.setColor(getColorFromValue(value));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getValue() {
		return value;
	}

	/**
	 * Récupére la couleur d'une tuile en fonction de sa valeur
	 * 
	 * @param valeur
	 *            de la tuile
	 * @return Couleur de type Color
	 */
	public Color getColorFromValue(int value) {
		switch (value) {
		case 2:
			return _2c;
		case 4:
			return _4c;
		case 8:
			return _8c;
		case 16:
			return _16c;
		case 32:
			return _32c;
		case 64:
			return _64c;
		case 128:
			return _128c;
		case 256:
			return _256c;
		case 512:
			return _512c;
		case 1024:
			return _1024c;
		default:
			return _0c;
		}
	}

	/**
	 * Change la valeur d'une tuile et sa couleur
	 * 
	 * @param value valeur de la tuile
	 */
	public void setValue(int value) {
		this.value = value;
		this.color = getColorFromValue(value);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		this.color = c;
	}
}
