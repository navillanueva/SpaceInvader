//Numeros clave del juego y constantes.UC3M. Monica Ocania Bastante & Nicolas Arnedo Villanueva
package edu.uc3m.galaga;

import edu.uc3m.game.GameBoardGUI;

/**
 * Clase de apoyo para guardar todas las constantes del programa junto a numeros
 * muy usados.
 * 
 * Tambien incluye los metodos usados en la clase principal que no corresponden 
 * a objetos en concreto
 * 
 * @author Monica Ocania Bastante & Nicolas Arnedo Villanueva
 * @since 2018/11/28
 */

public class Global {
	/**
	 * Maxima anchura para que el sprite se vea
	 */
	private int xMax = 165;
	/**
	 * Maxima altura para que el sprite se vea
	 */
	private int yMax = 215;
	/**
	 * Minimo en ambos casos para que se muestre en pantalla
	 */
	private int min = 5;

	public int getxMax() {
		return xMax;
	}

	public int getyMax() {
		return yMax;
	}

	public int getMin() {
		return min;
	}

	public static final int DIR_N = 0;
	public static final int DIR_NNE = 1;
	public static final int DIR_NE = 2;
	public static final int DIR_ENE = 3;
	public static final int DIR_E = 4;
	public static final int DIR_ESE = 5;
	public static final int DIR_SE = 6;
	public static final int DIR_SSE = 7;
	public static final int DIR_S = 8;
	public static final int DIR_SSW = 9;
	public static final int DIR_SW = 10;
	public static final int DIR_WSW = 11;
	public static final int DIR_W = 12;
	public static final int DIR_WNW = 13;
	public static final int DIR_NW = 14;
	public static final int DIR_NNW = 15;

	public static final int[][] MOVES = { 
			{0, -4}, // DIR_N
			{1, -4}, // DIR_NNE
			{3, -3}, // DIR_NE
			{4, -1}, // DIR_ENE
			{4, 0 }, // DIR_E
			{4, 1 }, // DIR_ESE
			{3, 3 }, // DIR_SE
			{1, 4 }, // DIR_SSE
			{0, 4 }, // DIR_S
			{-1, 4}, // DIR_SSW
			{-3, 3}, // DIR_SW
			{-4, 1}, // DIR_WSW
			{-4, 0}, // DIR_W
			{-4,-1}, // DIR_WNW
			{-3,-3}, // DIR_NW
			{-1,-4}, // DIR_NNW
	};
	public static void efectuarDisparo(String comando, int contador3, Jugador usuario) {
		if (comando.equals("space")) {
			usuario.disparar(contador3);
			usuario.setDisparosRealizados(usuario.getDisparosRealizados() + 1);
			
		}
	}
	public static void actualizarDatos(Jugador usuario, GameBoardGUI gui) {
		//Actualizar los contadores
		gui.gb_setValueHealthCurrent(usuario.getVidaActual());
		gui.gb_setValuePointsDown(usuario.getPuntos());
		gui.gb_setValueAbility1(usuario.getDisparosRealizados());
		gui.gb_setValueAbility2(usuario.getAciertos());
	}

	public static int bajarEnjambre(int contador, int contador2, int contador4, Nivel nivel1) throws InterruptedException {
		String direccion;
		if ((contador % 170) == 100 && contador2 >= 0&& contador4>=90) {
			direccion = "abajo";
			nivel1.moverEnjambre(direccion);
			contador++;
		}
		
		return contador;
	}
	public static int vaivenEnjambre(int contador, int contador2, int contador4, Nivel nivel1 ) throws InterruptedException {
		// Mover enjambre
		//Primer moviento a la izquierde desde el centro
		
		String direccion="nada";
		if (contador < 30 && contador4>=90) {
			direccion = "izquierda";
			contador++;

		} else {if( contador4>=90) {
			direccion = "nada";
			contador2 = 0;
		}
		
		}
		
		//vaiven de izquierda a derecha
		if ((contador % 200) < 100 && contador2 >= 0&& contador4>=90) {
			direccion = "derecha";
			contador++;
		} else if ((contador % 170) > 100 && contador2 >= 0&& contador4>=90) {
			direccion = "izquierda";
			contador++;

		} else if (contador2 >= 0&& contador4>=90) {
		contador = 29;
			direccion = "abajo";
			nivel1.moverEnjambre(direccion);
		}
		nivel1.moverEnjambre(direccion);
		
		return contador;
	}
	
	public static void crearTablero(GameBoardGUI gui) {
		// para que se vea el tablero
		gui.setVisible(true);
		// pintar pantalla de negro
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 22; j++) {
				gui.gb_setSquareColor(i, j, 0, 0, 0);
			}
		}
	}

}