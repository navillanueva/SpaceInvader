//Los torpedos que se disparan por ambas partes.UC3M. Monica Ocania Bastante & Nicolas Arnedo Villanueva
package edu.uc3m.galaga;

/**
 * En esta clase tenemos toda la informacion de los torpedos y sus metodos especiales.
 * @author Monica Ocania Bastante & Nicolas Arnedo Villanueva
 * @since 2018/11/28
 */

import edu.uc3m.game.GameBoardGUI;
public class Torpedo {
	/**
	 * Indica a quien pertenece el torpedo
	 * true: jugador
	 * false: enemigo
	 */
	private boolean isAmigo;
	/**
	 * Indica la posicion en el tablero
	 */
	private Posicion coordenadas = new Posicion();
	/**
	 * La imagen, que puede ser dos. Amiga o
	 * enemiga
	 */
	private String imagen;
	/**
	 * El identificador del spirte, usado tambien
	 * para saber si el torpedo ha sido disparado o no.
	 * Si es positivo, esta disparado y moviendose.
	 * Si es <=0, esta listo para ser disparado
	 */
	private int identificador = 0;
	private  GameBoardGUI gui;

	public Torpedo(Boolean amigo, Posicion coordenadasLanzador, int id, GameBoardGUI gui) {
		isAmigo = amigo;
		this.gui=gui;
		if (isAmigo) {
			imagen = "torpedo100.png";
			coordenadas.setX(coordenadasLanzador.getX());
			coordenadas.setY(coordenadasLanzador.getY() - 10);
		} else {
			imagen = "torpedo200.png";
			coordenadas.setX(coordenadasLanzador.getX());
			coordenadas.setY(coordenadasLanzador.getY() + 10);
		}
		identificador = id;
	}

	// GETTERS
	public boolean isAmigo() {
		return isAmigo;
	}

	public Posicion getCoordenadas() {
		return coordenadas;
	}

	public String getImagen() {
		return imagen;
	}

	public int getIdentificador() {
		return identificador;
	}

	// SETTERS
	public void setCoordenadas(int altura) {
		coordenadas.setY(altura);
	}
	public void setIdentificador(int identificador) {
		this.identificador=identificador;
	}

	// METODOS
	/**
	 * Hacer que el torpedo suba o baje
	 */
	public void moverTorpedo() {
		if (isAmigo) {
			coordenadas.setY(coordenadas.getY() - 1);
			gui.gb_moveSpriteCoord(identificador, coordenadas.getX(), coordenadas.getY());
		} else {
			coordenadas.setY(coordenadas.getY() + 1);
			gui.gb_moveSpriteCoord(identificador, coordenadas.getX(), coordenadas.getY());
		}
		//Si se ha salido de la pantalla ponerlo como no disparado
		if(coordenadas.getY()<-5 ||coordenadas.getY()>225) {
			identificador=identificador*-1;
		}
	}
	
	/**
	 * Borrar la imagen del torpedo y cambiarlo a no disparado
	 * (identificador<0)
	 */
	public void colisionado() {
		gui.gb_setSpriteImage(identificador, "empty.png");
		setIdentificador(identificador * -1);
	}


}