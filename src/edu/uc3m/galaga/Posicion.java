//Donde se encuentra cada sprite. UC3M. Monica Ocania Bastante & Nicolas Arnedo Villanueva
package edu.uc3m.galaga;

/**
 * En esta clase tenemos todos los metodos correspondientes a la posicion del
 * sprite. Como todos los sprites tienen posicion, agrupamos su imformacion
 * aqui.
 * 
 * @author Monica Ocania Bastante & Nicolas Arnedo Villanueva
 * @since 2018/12/19
 */

public class Posicion {
	/**
	 * Posicion lateral
	 */
	private int x = 0;
	/**
	 * Altura
	 */
	private int y = 200;

	//CONSTRUCTORES
	public Posicion() {

	}

	public Posicion(int x, int y) {
		this.x = x;
		this.y = y;
	}

	//GETTERS
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	//SETTERS
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;

	}
	
}