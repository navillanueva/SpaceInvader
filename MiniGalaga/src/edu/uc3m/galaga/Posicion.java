//Donde se encuentra cada sprite. UC3M. Monica Ocania Bastante & Nicolas Arnedo Villanueva
package edu.uc3m.galaga;

/**
 * En esta clase tenemos todos los metodos correspondientes a la 
 * posicion del sprite. Como todos los sprites tienen posicion,
 * agrupamos su imformacion aqui.
 * @author Monica Ocania Bastante & Nicolas Arnedo Villanueva
 * @since 2018/11/28
 */

public class Posicion {
	private Global cte=new Global();
	/**
	 * Posicion lateral
	 */
	private int x=0;
	/**
	 * Altura
	 */
	private int y=200;
	
	public Posicion() {
		
	}
	
	public Posicion(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		//No nos podemos salir por los lados
		//if (x <= cte.getxMax() && x >= cte.getMin()) {
			this.x=x;
		//} 
		
	}
	public void setY(int y) {
		//Si nos podemos salir por arriba o por abajo
			this.y=y;
		 
	}
	
	
}