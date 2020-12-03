//Propiedades del jugador, como se mueve y dispara.UC3M. Monica Ocania Bastante & Nicolas Arnedo Villanueva
package edu.uc3m.galaga;

/**
 * Esta clase controla las propiedades del jugador y lo que puede hacer
 * @author Monica Ocania Bastante & Nicolas Arnedo Villanueva
 * @since 2018/11/28
  
 */

import edu.uc3m.game.GameBoardGUI;


public class Jugador {
	private Global cte = new Global();
	/**
	 * Su identificador como sprite
	 */
	private int identificador = 40;
	/**
	 * Su posicion en el tablero
	 */
	private Posicion coordenadas = new Posicion(80, 210);
	/**
	 * Los puntos que ha conseguido
	 */
	private int puntos = 0;
	/**
	 * La vida maxima que puede tener
	 */
	private int vidaTotal = 3;
	/**
	 * De la vida maxima, cuanta tiene en toal
	 */
	private int vidaActual = 3;
	/**
	 * La cantidad de veces que ha disparado
	 */
	private int disparosRealizados = 0;
	/**
	 * De los disparos realizados, los que han
	 * impactado en un enemigo
	 */
	private int aciertos = 0;
	/**
	 * La cantidad de torpedos disponibles para
	 * ser disparados
	 */
	private Torpedo[] disparos = new Torpedo[200];
	private GameBoardGUI gui;

	//CONSTRUCTOR
	public Jugador(GameBoardGUI gui) {
		this.gui = gui;
		//Crear los cohetes
		for (int ii = 0; ii < disparos.length; ii++) {
			disparos[ii] = new Torpedo(true, coordenadas, (-700 + ii), gui);
		}

		// Aniadir el sprite a la consola
		gui.gb_addSprite(identificador, "player.png", true);
		gui.gb_setSpriteVisible(identificador, true);
		gui.gb_moveSpriteCoord(identificador, coordenadas.getX(), coordenadas.getY());

		// Imprimir habilidades del jugador
		gui.gb_setValueHealthCurrent(vidaActual);
		gui.gb_setValueHealthMax(vidaTotal);
		gui.gb_setTextAbility1("Disparos");
		gui.gb_setTextAbility2("Aciertos");
		gui.gb_setValueAbility1(disparosRealizados);
		gui.gb_setValueAbility2(aciertos);
		gui.gb_setTextPointsDown("Puntos");
		gui.gb_setTextPointsUp("Velocidad");
		gui.gb_setPortraitPlayer("portrait.png");
	}

	// GETTERS
	public int getIdentificador() {
		return identificador;
	}

	public Posicion getCoordenadas() {
		return coordenadas;
	}

	public int getPuntos() {
		return puntos;
	}

	public int getVidaTotal() {
		return vidaTotal;
	}

	public int getVidaActual() {
		return vidaActual;
	}

	public int getDisparosRealizados() {
		return disparosRealizados;
	}

	public int getAciertos() {
		return aciertos;
	}

	public Torpedo[] getDisparos() {
		return disparos;
	}

	// SETTERS
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public void setCoordenadas(Posicion coordenadas) {
		this.coordenadas = coordenadas;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public void setVidaTotal(int vidaTotal) {
		this.vidaTotal = vidaTotal;
	}

	public void setVidaActual(int vidaActual) {
		this.vidaActual = vidaActual;
	}

	public void setDisparosRealizados(int disparosRealizados) {
		this.disparosRealizados = disparosRealizados;
	}

	public void setAciertos(int aciertos) {
		this.aciertos = aciertos;
	}

	// METODOS ESPECIFICOS QUE FUNCIONAN
	/**
	 * El jugador se mueve en la direccion que indica el comando, que pude ser "left" o "right"
	 * (izquierda o derecha).
	 * Se mueve de 3 en 3.
	 * @param comando
	 */
	public void moverJugador(String comando) {
		if (coordenadas.getX() < cte.getxMax() && coordenadas.getX() > cte.getMin()) {
			switch (comando) {
			case "left":
				coordenadas.setX((coordenadas.getX() - 3));
				break;
			case "right":
				coordenadas.setX((coordenadas.getX() + 3));
				break;
			}
		/*
		 * Para evitar que salga del tablero, al llegar al borde solo puede
		 * ir en direccion contraria a la que ha venido
		 */
		} else {
			if (coordenadas.getX() >= cte.getxMax()) {
				switch (comando) {
				case "left":
					coordenadas.setX((coordenadas.getX() - 3));
					break;
				}
			} else {
				if (coordenadas.getX() <= cte.getMin()) {
					switch (comando) {
					case "right":
						coordenadas.setX((coordenadas.getX() + 3));
						break;
					}
				}
			}
		}
		//Imprimir el sprite en el nuevo sitio
		gui.gb_moveSpriteCoord(identificador, coordenadas.getX(), coordenadas.getY());
	}

	/**
	 * Con este metodo disparamos los torpedos, haciendo que aparezcan encima del jugador, 
	 * compartiendo su posicion x.
	 * @param identificador
	 */
	public void disparar(int identificador) {
		// Buscar sitio donde guardar el torpedo
		boolean buscando = true;
		int contador = 0;
		do {
			/*
			 * Si el identificador es <0, consideramos que el misil esta listo
			 * para ser disparado
			 */
			if (disparos[contador].getIdentificador() <= 0) {
				buscando = false;
				//Poner el misil en pantalla
				disparos[contador] = new Torpedo(true, coordenadas, identificador, gui);
				gui.gb_addSprite(disparos[contador].getIdentificador(), disparos[contador].getImagen(), true);
				gui.gb_setSpriteVisible(disparos[contador].getIdentificador(), true);
				gui.gb_moveSpriteCoord(disparos[contador].getIdentificador(),
						disparos[contador].getCoordenadas().getX(), disparos[contador].getCoordenadas().getY());

			} else {
				contador++;
			}
			/*
			 * El bucle debe funcionar hasta que encuentre un hueco libre
			 * o haya comporbado que todos están disparados
			 */
		} while (buscando && contador < disparos.length);

	}

	/**
	 * Movemos los torpedos que hayan sido disparados, es decir;
	 * los que tienen identificador>0.
	 */
	public void moverTorpedos() {
		for (int ii = 0; ii < disparos.length; ii++) {
			if (disparos[ii].getIdentificador() > 0) {
				disparos[ii].moverTorpedo();
			}
		}
	}
	// METODOS ESPECIFICOS EN PRUEBA
	
}