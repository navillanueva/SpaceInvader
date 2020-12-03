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
 * @since 2018/12/19
 */

public class Global {
	/**
	 * Largo de la pantalla
	 */
	public static final int largo = 170;
	/**
	 * Alto de la pantalla
	 */
	public static final int alto = 220;
	/**
	 * Maxima anchura para que el sprite se vea
	 */
	public static final int xMax = 165;
	/**
	 * Maxima altura para que el sprite se vea
	 */
	public static final int yMax = 215;
	/**
	 * Minimo en ambos casos para que se muestre en pantalla
	 */
	public static final int min = 5;

	/**
	 * Las direcciones en las que se puede mover el sprite
	 */
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

	// METODOS DEL PROGRAMA PRINCIPAL

	// METODOS DE LA INTERFAZ
	/**
	 * Dibujar la interfaz (el tablero de juego)
	 * 
	 * @param gui Interfaz
	 */
	public static void crearTablero(GameBoardGUI gui) {
		// para que se vea el tablero
		gui.setVisible(true);
		// pintar pantalla de negro
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 22; j++) {
				gui.gb_setSquareColor(i, j, 0, 0, 0);
			}
		}
		gui.gb_setTextPlayerName("Galaga");
	}

	/**
	 * Vuelve a iniciar al jugador, el nivel y la interfaz
	 * 
	 * @param nivelActual El nivel en el que estamos
	 * @param gui         La interfaz que se esta usando
	 * @param usuario     El jugador
	 */
	public static boolean reiniciar(Nivel nivelActual, GameBoardGUI gui, Jugador usuario, Boolean invencible) {
		nivelActual.enjambreVisible();
		usuario.reiniciarDatos();
		gui.gb_clearConsole();
		Global.crearTablero(gui);
		usuario.actualizarDatosInterfaz(gui);
		invencible = false;
		gui.gb_setValueLevel(1);
		return invencible;
	}

	/**
	 * Pone en pantalla el nivel indicado
	 * 
	 * @param usuario        El jugador
	 * @param nivelActual    El nivel en el que estamos
	 * @param siguienteNivel Nivel al que queremos ir
	 * @param nivel          Numero del nivel al que vamos
	 * @param gui            Interfaz del juego
	 * @return El nivel al que hemos ido
	 */
	public static Nivel pasarNivel(Jugador usuario, Nivel nivelActual, Nivel siguienteNivel, int nivel,
			GameBoardGUI gui) {
		usuario.borrarTorpedos();
		usuario.getEspecial().setVecesUsadoEspecial(0);
		nivelActual.borrarDisparos();
		nivelActual.setActual(false);
		siguienteNivel.setActual(true);
		siguienteNivel.enjambreVisible();
		nivelActual = siguienteNivel;
		gui.gb_setValueLevel(nivel);
		return nivelActual;

	}

	/**
	 * Cuando el jugador muere, su imagen explota y se pinta en el fondo "you die"
	 * 
	 * @param explosion En que fase de la explosion esta
	 * @param usuario   jugador que ha muerto
	 * @param gui       interfaz
	 * @param gato      si el modo gato esta activado
	 * @return explosion, en que fase esta.
	 * @throws InterruptedException
	 */
	public static int muerte(int explosion, Jugador usuario, GameBoardGUI gui, boolean gato)
			throws InterruptedException {
		gui.gb_setSpriteVisible(501, false);
		if (explosion == 0) {
			if (gato)
				Global.crearTablero(gui);
			Global.pintar("muerto", gui);
		}
		if (explosion <= 3) {
			usuario.explotar(explosion);
			explosion++;
		}
		return explosion;
	}

	/**
	 * Lo que acciona el comando god, fast o slow
	 * 
	 * @param usuario    Jugador que sufre la accion
	 * @param comando    Comando introducido por teclado
	 * @param invencible Si esta activo o no el modo invencible
	 * @param gui        interfaz donde ocurre
	 * @return Devuelve el nuevo estado del modo invencible
	 */
	public static boolean accionComando(Jugador usuario, String comando, boolean invencible, GameBoardGUI gui) {
		accionComandoVelocidad(usuario, comando, gui);
		return accionComandoGod(comando, invencible, gui);
	}

	/**
	 * Lo que acciona el comando gato. Sustituye al jugador por un gato y pinta la
	 * cara de uno en el fondo de la pantalla
	 * 
	 * @param gato    Si esta activo o no el modo gato
	 * @param comando El comando introducido por teclado
	 * @param gui     Interfaz donde sucede
	 * @param usuario Jugador que sufre la accion
	 * @return Devuelve el nuevo estado del modo gato
	 */
	public static boolean accionComandoGato(Boolean gato, String comando, GameBoardGUI gui, Jugador usuario) {
		if (comando.regionMatches(8, "gato", 0, 4)) {
			if (!gato) {
				Global.pintar("gato", gui);
				gui.gb_println("Miau");
				gui.gb_setSpriteImage(usuario.getIdentificador(), "gato100.png");
				gato = true;
			} else {
				Global.crearTablero(gui);
				gui.gb_println("FUUUUF");
				gui.gb_setSpriteImage(usuario.getIdentificador(), "player.png");
				gato = false;
			}
		}
		return gato;
	}

	/**
	 * Cambia en 1 la velocidad del jugador
	 * 
	 * @param usuario Jugador que sufre la accion
	 * @param comando Fast, sube en 1. Slow, baja en 1
	 * @param gui     Interfaz donde se actualiza el dato
	 */
	private static void accionComandoVelocidad(Jugador usuario, String comando, GameBoardGUI gui) {
		if (comando.regionMatches(8, "fast", 0, 4)) {
			usuario.setVelocidad(usuario.getVelocidad() + 1);
			gui.gb_println("Velocidad aumentada");
			gui.gb_println("Velocidad actual: " + usuario.getVelocidad());
		}
		if (comando.regionMatches(8, "slow", 0, 4)) {
			usuario.setVelocidad(usuario.getVelocidad() - 1);
			gui.gb_println("Velocidad bajada");
			gui.gb_println("Velocidad actual: " + usuario.getVelocidad());
		}
	}

	/**
	 * Establece el modo invencible (no se hacen comrpueban las colisiones con el
	 * jugador) o lo quita.
	 * 
	 * @param comando    Que se ha introducido en consola
	 * @param invencible Si estaba o no en modo invencible
	 * @param gui        La interfaz usada
	 * @return invencible. True si es invencible, false si no
	 */
	private static boolean accionComandoGod(String comando, boolean invencible, GameBoardGUI gui) {
		if (comando.regionMatches(8, "god", 0, 3)) {
			if (invencible) {
				invencible = false;
				gui.gb_println("modo invencible desactivado");
			} else {
				invencible = true;
				gui.gb_println("modo invencible activado");
			}
		}
		return invencible;
	}

	/**
	 *
	 * @param quePintar Que es lo que queremos pintar en la pantalla
	 * @param gui       La interfaz usada
	 */
	public static void pintar(String quePintar, GameBoardGUI gui) {
		switch (quePintar) {
		case "muerto":
			pintarYouDied(gui);
			break;
		case "ha ganado":
			pintarYouWin(gui);
			break;
		case "gato":
			pintarGato(gui);
			break;
		}
	}

	/**
	 * Pinta en la pantalla "YOU DIED"
	 * 
	 * @param gui
	 */
	private static void pintarYouDied(GameBoardGUI gui) {
		// Y
		gui.gb_setSquareColor(4, 6, 255, 0, 0);
		gui.gb_setSquareColor(6, 6, 255, 0, 0);
		gui.gb_setSquareColor(5, 7, 255, 0, 0);
		gui.gb_setSquareColor(5, 8, 255, 0, 0);
		gui.gb_setSquareColor(5, 9, 255, 0, 0);
		// O
		gui.gb_setSquareColor(8, 6, 0, 255, 0);
		gui.gb_setSquareColor(7, 7, 0, 255, 0);
		gui.gb_setSquareColor(9, 7, 0, 255, 0);
		gui.gb_setSquareColor(7, 8, 0, 255, 0);
		gui.gb_setSquareColor(9, 8, 0, 255, 0);
		gui.gb_setSquareColor(8, 9, 0, 255, 0);
		// U
		gui.gb_setSquareColor(10, 6, 0, 0, 255);
		gui.gb_setSquareColor(12, 6, 0, 0, 255);
		gui.gb_setSquareColor(10, 7, 0, 0, 255);
		gui.gb_setSquareColor(12, 7, 0, 0, 255);
		gui.gb_setSquareColor(10, 8, 0, 0, 255);
		gui.gb_setSquareColor(12, 8, 0, 0, 255);
		gui.gb_setSquareColor(11, 9, 0, 0, 255);
		// D
		gui.gb_setSquareColor(3, 11, 255, 255, 0);
		gui.gb_setSquareColor(4, 11, 255, 255, 0);
		gui.gb_setSquareColor(3, 12, 255, 255, 0);
		gui.gb_setSquareColor(5, 12, 255, 255, 0);
		gui.gb_setSquareColor(3, 13, 255, 255, 0);
		gui.gb_setSquareColor(5, 13, 255, 255, 0);
		gui.gb_setSquareColor(3, 14, 255, 255, 0);
		gui.gb_setSquareColor(5, 14, 255, 255, 0);
		gui.gb_setSquareColor(3, 15, 255, 255, 0);
		gui.gb_setSquareColor(4, 15, 255, 255, 0);
		// I
		gui.gb_setSquareColor(6, 11, 255, 0, 255);
		gui.gb_setSquareColor(7, 11, 255, 0, 255);
		gui.gb_setSquareColor(8, 11, 255, 0, 255);
		gui.gb_setSquareColor(7, 12, 255, 0, 255);
		gui.gb_setSquareColor(7, 13, 255, 0, 255);
		gui.gb_setSquareColor(7, 14, 255, 0, 255);
		gui.gb_setSquareColor(6, 15, 255, 0, 255);
		gui.gb_setSquareColor(7, 15, 255, 0, 255);
		gui.gb_setSquareColor(8, 15, 255, 0, 255);
		// E
		gui.gb_setSquareColor(9, 11, 0, 255, 255);
		gui.gb_setSquareColor(10, 11, 0, 255, 255);
		gui.gb_setSquareColor(11, 11, 0, 255, 255);
		gui.gb_setSquareColor(9, 12, 0, 255, 255);
		gui.gb_setSquareColor(9, 13, 0, 255, 255);
		gui.gb_setSquareColor(10, 13, 0, 255, 255);
		gui.gb_setSquareColor(11, 13, 0, 255, 255);
		gui.gb_setSquareColor(9, 14, 0, 255, 255);
		gui.gb_setSquareColor(9, 15, 0, 255, 255);
		gui.gb_setSquareColor(10, 15, 0, 255, 255);
		gui.gb_setSquareColor(11, 15, 0, 255, 255);
		// D
		gui.gb_setSquareColor(12, 11, 255, 255, 0);
		gui.gb_setSquareColor(13, 11, 255, 255, 0);
		gui.gb_setSquareColor(12, 12, 255, 255, 0);
		gui.gb_setSquareColor(14, 12, 255, 255, 0);
		gui.gb_setSquareColor(12, 13, 255, 255, 0);
		gui.gb_setSquareColor(14, 13, 255, 255, 0);
		gui.gb_setSquareColor(12, 14, 255, 255, 0);
		gui.gb_setSquareColor(14, 14, 255, 255, 0);
		gui.gb_setSquareColor(12, 15, 255, 255, 0);
		gui.gb_setSquareColor(13, 15, 255, 255, 0);
	}

	/**
	 * Pinta en la pantalla "YOU WIN"
	 * 
	 * @param gui
	 */
	private static void pintarYouWin(GameBoardGUI gui) {
		// Y
		gui.gb_setSquareColor(4, 6, 255, 0, 0);
		gui.gb_setSquareColor(6, 6, 255, 0, 0);
		gui.gb_setSquareColor(5, 7, 255, 0, 0);
		gui.gb_setSquareColor(5, 8, 255, 0, 0);
		gui.gb_setSquareColor(5, 9, 255, 0, 0);
		// O
		gui.gb_setSquareColor(8, 6, 0, 255, 0);
		gui.gb_setSquareColor(7, 7, 0, 255, 0);
		gui.gb_setSquareColor(9, 7, 0, 255, 0);
		gui.gb_setSquareColor(7, 8, 0, 255, 0);
		gui.gb_setSquareColor(9, 8, 0, 255, 0);
		gui.gb_setSquareColor(8, 9, 0, 255, 0);
		// U
		gui.gb_setSquareColor(10, 6, 0, 0, 255);
		gui.gb_setSquareColor(12, 6, 0, 0, 255);
		gui.gb_setSquareColor(10, 7, 0, 0, 255);
		gui.gb_setSquareColor(12, 7, 0, 0, 255);
		gui.gb_setSquareColor(10, 8, 0, 0, 255);
		gui.gb_setSquareColor(12, 8, 0, 0, 255);
		gui.gb_setSquareColor(11, 9, 0, 0, 255);
		// W
		gui.gb_setSquareColor(1, 11, 255, 255, 0);
		gui.gb_setSquareColor(5, 11, 255, 255, 0);
		gui.gb_setSquareColor(1, 12, 255, 255, 0);
		gui.gb_setSquareColor(5, 12, 255, 255, 0);
		gui.gb_setSquareColor(1, 13, 255, 255, 0);
		gui.gb_setSquareColor(3, 13, 255, 255, 0);
		gui.gb_setSquareColor(5, 13, 255, 255, 0);
		gui.gb_setSquareColor(1, 14, 255, 255, 0);
		gui.gb_setSquareColor(2, 14, 255, 255, 0);
		gui.gb_setSquareColor(4, 14, 255, 255, 0);
		gui.gb_setSquareColor(5, 14, 255, 255, 0);
		gui.gb_setSquareColor(1, 15, 255, 255, 0);
		gui.gb_setSquareColor(5, 15, 255, 255, 0);

		// I
		gui.gb_setSquareColor(6, 11, 255, 0, 255);
		gui.gb_setSquareColor(7, 11, 255, 0, 255);
		gui.gb_setSquareColor(8, 11, 255, 0, 255);
		gui.gb_setSquareColor(9, 11, 255, 0, 255);
		gui.gb_setSquareColor(10, 11, 255, 0, 255);
		gui.gb_setSquareColor(8, 12, 255, 0, 255);
		gui.gb_setSquareColor(8, 13, 255, 0, 255);
		gui.gb_setSquareColor(8, 14, 255, 0, 255);
		gui.gb_setSquareColor(6, 15, 255, 0, 255);
		gui.gb_setSquareColor(7, 15, 255, 0, 255);
		gui.gb_setSquareColor(8, 15, 255, 0, 255);
		gui.gb_setSquareColor(9, 15, 255, 0, 255);
		gui.gb_setSquareColor(10, 15, 255, 0, 255);

		// N
		gui.gb_setSquareColor(11, 11, 0, 255, 255);
		gui.gb_setSquareColor(15, 11, 0, 255, 255);
		gui.gb_setSquareColor(11, 12, 0, 255, 255);
		gui.gb_setSquareColor(12, 12, 0, 255, 255);
		gui.gb_setSquareColor(15, 12, 0, 255, 255);
		gui.gb_setSquareColor(11, 13, 0, 255, 255);
		gui.gb_setSquareColor(13, 13, 0, 255, 255);
		gui.gb_setSquareColor(15, 13, 0, 255, 255);
		gui.gb_setSquareColor(11, 14, 0, 255, 255);
		gui.gb_setSquareColor(14, 14, 0, 255, 255);
		gui.gb_setSquareColor(15, 14, 0, 255, 255);
		gui.gb_setSquareColor(11, 15, 0, 255, 255);
		gui.gb_setSquareColor(15, 15, 0, 255, 255);
	}

	/**
	 * Pinta un gato en el fondo de la pantalla
	 * 
	 * @param gui La interfaz donde se pinta
	 */
	private static void pintarGato(GameBoardGUI gui) {
		gui.gb_setSquareColor(4, 5, 255, 255, 255);
		gui.gb_setSquareColor(4, 5, 255, 255, 255);
		gui.gb_setSquareColor(12, 5, 255, 255, 255);

		gui.gb_setSquareColor(4, 6, 255, 255, 255);
		gui.gb_setSquareColor(5, 6, 255, 255, 255);
		gui.gb_setSquareColor(11, 6, 255, 255, 255);
		gui.gb_setSquareColor(12, 6, 255, 255, 255);

		gui.gb_setSquareColor(4, 7, 255, 255, 255);
		gui.gb_setSquareColor(6, 7, 255, 255, 255);
		gui.gb_setSquareColor(7, 7, 255, 255, 255);
		gui.gb_setSquareColor(8, 7, 255, 255, 255);
		gui.gb_setSquareColor(9, 7, 255, 255, 255);
		gui.gb_setSquareColor(10, 7, 255, 255, 255);
		gui.gb_setSquareColor(12, 7, 255, 255, 255);

		gui.gb_setSquareColor(4, 8, 255, 255, 255);
		gui.gb_setSquareColor(12, 8, 255, 255, 255);

		gui.gb_setSquareColor(4, 9, 255, 255, 255);
		gui.gb_setSquareColor(12, 9, 255, 255, 255);

		gui.gb_setSquareColor(4, 10, 255, 255, 255);
		gui.gb_setSquareColor(6, 10, 255, 255, 255);
		gui.gb_setSquareColor(7, 10, 255, 255, 255);
		gui.gb_setSquareColor(9, 10, 255, 255, 255);
		gui.gb_setSquareColor(10, 10, 255, 255, 255);
		gui.gb_setSquareColor(12, 10, 255, 255, 255);

		gui.gb_setSquareColor(3, 11, 255, 255, 255);
		gui.gb_setSquareColor(13, 11, 255, 255, 255);

		gui.gb_setSquareColor(4, 12, 255, 255, 255);
		gui.gb_setSquareColor(8, 12, 255, 255, 255);
		gui.gb_setSquareColor(12, 12, 255, 255, 255);

		gui.gb_setSquareColor(5, 13, 255, 255, 255);
		gui.gb_setSquareColor(11, 13, 255, 255, 255);

		gui.gb_setSquareColor(6, 14, 255, 255, 255);
		gui.gb_setSquareColor(7, 14, 255, 255, 255);
		gui.gb_setSquareColor(8, 14, 255, 255, 255);
		gui.gb_setSquareColor(9, 14, 255, 255, 255);
		gui.gb_setSquareColor(10, 14, 255, 255, 255);
	}

	// ENJAMBRES

	/**
	 * Mueve el enjambre de derecha a izquierda y de izquierda a derecha. Cuando
	 * llega al borde, baja 1.
	 * 
	 * @param contador    Hacia donde debe moverse
	 * @param contador2   Si ya ha realizado el primer movimiento
	 * @param contEntrada Si se esta haciendo la entrada
	 * @param nivelActual El enjambre del nivel actual
	 * @return Contador (le suma 1 o lo establece en 30)
	 * @throws InterruptedException
	 */
	public static int vaivenEnjambre(int contador, boolean empezar, int contEntrada, Nivel nivelActual)
			throws InterruptedException {
		/*
		 * El margen que se deja meitnras se hace la entrada antes de empezar a moverse
		 */
		int entrada = 90;
		// Primer movimiento a la izquierde desde el centro

		String direccion = "nada";
		if (contador < 30 && contEntrada >= entrada) {
			direccion = "izquierda";
			contador++;
		} else {
			if (contEntrada >= entrada / 2) {
				direccion = "nada";
				empezar = true;
			}
		}

		// vaiven de izquierda a derecha
		if ((contador % 200) <= 100 && empezar && contEntrada >= entrada) {
			direccion = "derecha";
			contador++;
		} else if ((contador % 170) > 100 && empezar && contEntrada >= entrada) {
			direccion = "izquierda";
			contador++;

		} else if (empezar && contEntrada >= entrada) {
			contador = 29;
			direccion = "abajo";
			nivelActual.moverEnjambre(direccion);
		}
		nivelActual.moverEnjambre(direccion);
		contador = bajarEnjambre(contador, empezar, contEntrada, nivelActual);
		return contador;
	}

	/**
	 * Mueve el enjambre hacia abajo
	 * 
	 * @param contador  Hacia donde debe moverse
	 * @param contador2 Si ya ha realizado el primer movimiento
	 * @param contador4 Si se esta haciendo la entrada
	 * @param nivel     El enjambre del nivel actual
	 * @return contador(le suma 1)
	 */
	private static int bajarEnjambre(int contador, boolean empezar, int contador4, Nivel nivel) {
		if ((contador % 170) == 100 && empezar && contador4 >= 90) {
			nivel.moverEnjambre("abajo");
			contador++;
		}
		return contador;
	}

	/**
	 * Controla cuanto dura la entrada de cada nivel. Como cada entrada tiene una
	 * duracion distinta, necesitamos distintos contadores
	 * 
	 * @param nivelActual       Enjambre que ejecuta la entrada
	 * @param contadorIniMovEnj Cuando empieza a moverse el enjambre(la entrada
	 *                          puede seguir haciendose, pero el enjambre ya aplica
	 *                          el metodo mover)
	 * @param entradaLimite1    Cuanto dura la entrada en el nivel 1
	 * @param entradaLimite2    Cuanto dura la entrada en el nivel 2
	 * @param entradaLimite3    Cuanto dura la entrada en el nivel 3
	 */
	public static void controlEntrada(Nivel nivelActual, int contadorIniMovEnj, int entradaLimite1, int entradaLimite2,
			int entradaLimite3) {
		if (nivelActual.getNivel() == 1) {
			if (contadorIniMovEnj < entradaLimite1 && contadorIniMovEnj % 2 != 0) {
				nivelActual.entrada(contadorIniMovEnj);
			}
		}

		if (nivelActual.getNivel() == 2) {
			if (contadorIniMovEnj < entradaLimite2 && contadorIniMovEnj % 2 != 0) {
				nivelActual.entrada(contadorIniMovEnj);
			}
		}

		if (nivelActual.getNivel() == 3) {
			if (contadorIniMovEnj < entradaLimite3 && contadorIniMovEnj % 2 != 0) {
				nivelActual.entrada(contadorIniMovEnj);
			}
		}
	}

	// METODOS PARA LOS DISPAROS
	/**
	 * Metodo que engobla todos los metodos que controlan torpedos, tanto amigos
	 * como enemigos, ademas de las colisiones por ambas partes
	 * 
	 * @param usuario     El jugador
	 * @param nivelActual El enjambre del nivel actual
	 * @param comando     Que tecla se ha pulsado
	 * @param contador3   Para identificar los cohetes que se disparan
	 * @param contEntrada Para saber si se esta haciendo la entrada
	 */
	public static boolean controlarDisparos(Jugador usuario, Nivel nivelActual, String comando, boolean invencible,
			int contEntrada, boolean heDisparado) {
		// para que el jugador pueda disparar
		heDisparado = efectuarDisparo(comando, usuario, heDisparado);
		// mover los torpedos disparados del jugador
		usuario.moverTorpedos();
		/*
		 * Inicia la explosion de los enemigos acertados por los torpedos del jugador
		 */
		nivelActual.destruir(usuario);
		/*
		 * Mueve los torpedos disparados por el enjambre y tras moverlos, comprueba si
		 * alguno ha acertado al jugador
		 */
		nivelActual.moverTorpedos(usuario, invencible);
		usuario.colisionEspecial(nivelActual);
		/*
		 * Comprueba si algun enemigo ha acertado al jugador
		 */
		if (!invencible)
			nivelActual.colisionJugador(usuario);
		return heDisparado;
	}

	/**
	 * Para que el jugador disparo sus torpedos
	 * 
	 * @param comando   La tecla que se ha pulsado
	 * @param contador3 Para poner identificador al cohete
	 * @param usuario   El jugador
	 */
	private static boolean efectuarDisparo(String comando, Jugador usuario, boolean heDisparado) {
		if (comando.equals("space")) {
			if (!heDisparado) {
				usuario.disparar();
				usuario.setDisparosRealizados(usuario.getDisparosRealizados() + 1);
				heDisparado = true;
			}
		}
		if (comando.equals("tab")) {
			if (usuario.getEspecial().getIdentificador() < 0) {
				usuario.dispararEspecial();
			}
		}
		return heDisparado;
	}
}