//Los torpedos que se disparan por ambas partes.UC3M. Monica Ocania Bastante & Nicolas Arnedo Villanueva
package edu.uc3m.galaga;

/**
 * En esta clase tenemos toda la informacion de los torpedos y sus metodos especiales.
 * @author Monica Ocania Bastante & Nicolas Arnedo Villanueva
 * @since 2018/12/19
 */

import edu.uc3m.game.GameBoardGUI;

public class Torpedo {
	/**
	 * Indica a quien pertenece el torpedo true: jugador false: enemigo
	 */
	private boolean isAmigo;
	/**
	 * Indica la posicion en el tablero
	 */
	private Posicion coordenadas = new Posicion();
	/**
	 * La imagen, que puede ser dos. Amiga o enemiga
	 */
	private String imagen;
	/**
	 * El identificador del spirte, usado tambien para saber si el torpedo ha sido
	 * disparado o no. Si es positivo, esta disparado y moviendose. Si es <=0, esta
	 * listo para ser disparado
	 */
	private int identificador = 0;
	/**
	 * Veces que hemos disparado el torpedo especial
	 */
	private int vecesUsadoEspecial = 0;
	/**
	 * En que fase de la explosion esta el torpedo especial
	 */
	private int faseExplosion = 0;
	/**
	 * Si esta o no explotando el torpedo especial
	 */
	private boolean explotando = false;
	/**
	 * Interfaz donde se va a usar
	 */
	private GameBoardGUI gui;

	public Torpedo(Boolean amigo, Posicion coordenadasLanzador, int id, Boolean especial, GameBoardGUI gui) {
		isAmigo = amigo;
		this.gui = gui;
		if (!especial) {
			if (isAmigo) {
				imagen = "torpedo100.png";
				coordenadas.setX(coordenadasLanzador.getX());
				coordenadas.setY(coordenadasLanzador.getY() - 10);
				especial = false;
			} else {
				imagen = "torpedo200.png";
				coordenadas.setX(coordenadasLanzador.getX());
				coordenadas.setY(coordenadasLanzador.getY() + 10);
				especial = false;
			}
		} else {
			imagen = "torpedoEspecial30.png";
			coordenadas.setX(coordenadasLanzador.getX());
			coordenadas.setY(coordenadasLanzador.getY() - 20);
			especial = true;

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

	public int getVecesUsadoEspecial() {
		return vecesUsadoEspecial;
	}

	public boolean isExplotando() {
		return explotando;
	}

	public int getFaseExplosion() {
		return faseExplosion;
	}

	// SETTERS
	public void setCoordenadas(int altura) {
		coordenadas.setY(altura);
	}

	public void setCoordenadas(int ancho, int altura) {
		coordenadas.setX(ancho);
		coordenadas.setY(altura);
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void setVecesUsadoEspecial(int vecesUsoEspecial) {
		this.vecesUsadoEspecial = vecesUsoEspecial;
	}

	public void setExplotando(boolean explotando) {
		this.explotando = explotando;
	}

	public void setFaseExplosion(int faseExplosion) {
		this.faseExplosion = faseExplosion;
	}

	// METODOS
	/**
	 * Hacer que el torpedo suba o baje
	 */
	public void moverTorpedo() {
		if (identificador > 0) {
			if (isAmigo) {
				coordenadas.setY(coordenadas.getY() - 1);
				gui.gb_moveSpriteCoord(identificador, coordenadas.getX(), coordenadas.getY());
			} else {
				coordenadas.setY(coordenadas.getY() + 1);
				gui.gb_moveSpriteCoord(identificador, coordenadas.getX(), coordenadas.getY());
			}

			// Si se ha salido de la pantalla ponerlo como no disparado
			if (coordenadas.getY() < -5 || coordenadas.getY() > 225) {
				identificador = identificador * -1;
			}
		}
	}

	/**
	 * Borra la imagen del torpedo y lo cambia a no disparado (identificador<0)
	 */
	public void borrarTorpedo() {
		gui.gb_setSpriteVisible(identificador, false);
		setIdentificador(identificador * -1);
	}

	/**
	 * Dispara el torpedo, poniendolo encima o debajo del que lo disparo,
	 * dependiendo de si es amigo o enemigo
	 * @param coordenadasLanzador Posicion de referencia para colocarlo
	 */
	public void lanzar(Posicion coordenadasLanzador) {
		if (identificador < 0) {
			if (isAmigo) {
				setCoordenadas(coordenadasLanzador.getX(), coordenadasLanzador.getY() - 10);
			} else {
				setCoordenadas(coordenadasLanzador.getX(), coordenadasLanzador.getY() + 10);
			}
			setIdentificador(identificador * -1);
			/*
			 * Lo hacemos visible
			 */
			gui.gb_addSprite(identificador, imagen, true);
			gui.gb_setSpriteVisible(identificador, true);
			gui.gb_moveSpriteCoord(identificador, coordenadas.getX(), coordenadas.getY());
		}
	}
}