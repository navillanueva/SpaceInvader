//Juego de naves espaciales, trabajo fin de asignatura. UC3M. Monica Ocania Bastante & Nicolas Arnedo Villanueva

package edu.uc3m.galaga;

/**
 * Recreacion del Galaga. El jugador se mueve de izquierda a derecha, disparando torpedos
 * para eliminar a alienigenas( enemigos) hasta un maximo del nivel 3.
 * @author Monica Ocania Bastante & Nicolas Arnedo Villanueva
 * @since 2018/12/19
 */
import edu.uc3m.game.GameBoardGUI;

public class MiniGalaga {

	private static boolean exit = false;

	public static void main(String[] args) throws InterruptedException {
		// CREAR TABLERO
		GameBoardGUI gui = new GameBoardGUI(Global.largo / 10, Global.alto / 10);
		Global.crearTablero(gui);

		// Ejemplo crear sprite
		gui.gb_addSprite(500, "enemy900.png", true);
		gui.gb_setSpriteVisible(500, true);
		gui.gb_moveSprite(500, 5, -100);

		// CREAR JUGADOR
		Jugador usuario = new Jugador(gui);

		// CREAR ENJAMBRES
		Nivel nivel1 = new Nivel(1, gui);
		Nivel nivel2 = new Nivel(2, gui);
		Nivel nivel3 = new Nivel(3, gui);
		nivel1.setActual(true);
		Nivel nivelActual = nivel1;// el nivel que se pone en los metodos.
		gui.gb_setValueLevel(1);
		nivel1.enjambreVisible();

		// VARIABLES DE CONTROL
		// Control del movimiento del enjambre
		int contadorMovEnj = 0;
		boolean empezar = false;
		int entradaLimite1 = 180;
		int entradaLimite2 = 622;
		int entradaLimite3 = 1200;
		int contadorIniMovEnj = 0;
		// Control de disparos y explosiones
		int explosion = 0;
		int intervaloDisparo = 15;
		int frecuenciaDisparo = 0;
		boolean heDisparado = false;
		// Control de modos
		boolean gato = false;
		boolean invencible = false;
		// La tecla que ha pulsado el jugador ese ciclo de juego
		String comando;

		// JUEGO
		while (!exit) {

			comando = gui.gb_getLastAction();

			// PASAR DE NIVEL
			if (!nivelActual.isEnjambreVivo() && !nivel3.isActual()) {
				if (!nivel2.isActual()) {
					nivelActual = Global.pasarNivel(usuario, nivelActual, nivel2, 2, gui);
					// Reinicio contadores para los movimientos
					contadorMovEnj = 0;
					empezar = false;
					contadorIniMovEnj = 0;

				} else {
					if (!nivel3.isActual()) {
						// Reinicio contadores para los movimientos
						nivelActual = Global.pasarNivel(usuario, nivelActual, nivel3, 3, gui);
						contadorMovEnj = 0;
						empezar = false;
						contadorIniMovEnj = 0;
					}
				}
			}
			/*
			 * Si estoy en el nivel final y no quedan enemigos vivos, he ganado el juego
			 */
			if (nivel3.isActual() && !nivelActual.isEnjambreVivo()) {
				if (gato)
					Global.crearTablero(gui);
				Global.pintar("ha ganado", gui);
			}

			// Comprobar que el jugador esta VIVO
			usuario.vidasEnPantalla();
			if (usuario.getVidaActual() > 0) {

				// COMANDOS
				if (comando.regionMatches(0, "command", 0, 7)) {
					invencible = Global.accionComando(usuario, comando, invencible, gui);
					gato = Global.accionComandoGato(gato, comando, gui, usuario);
					if (comando.regionMatches(8, "level1", 0, 6)) {
						nivelActual.borrarEnjambre();
						contadorMovEnj = 0;
						empezar = false;
						contadorIniMovEnj = 0;
						gui.gb_println("Transportado a nivel 1");
						nivel1 = new Nivel(1, gui);
						nivel1.alternarImagenEnjambre();
						nivelActual = Global.pasarNivel(usuario, nivelActual, nivel1, 1, gui);
					}
					if (comando.regionMatches(8, "level2", 0, 6)) {
						nivelActual.borrarEnjambre();
						nivel2 = new Nivel(2, gui);
						nivel2.alternarImagenEnjambre();
						nivelActual = Global.pasarNivel(usuario, nivelActual, nivel2, 2, gui);
						contadorMovEnj = 0;
						empezar = false;
						contadorIniMovEnj = 0;
						gui.gb_println("Transportado a nivel 2");
					}
					if (comando.regionMatches(8, "level3", 0, 6)) {
						nivelActual.borrarEnjambre();
						nivel3 = new Nivel(3, gui);
						nivel3.alternarImagenEnjambre();
						nivelActual = Global.pasarNivel(usuario, nivelActual, nivel3, 3, gui);
						contadorMovEnj = 0;
						empezar = false;
						contadorIniMovEnj = 0;
						gui.gb_println("Transportado a nivel 3");
					}
					/*
					 * Borro el comando escrito
					 */
					gui.gb_clearCommandBar();
				}

				// MOVER JUGADOR
				usuario.moverJugador(comando);

				// MOVER ENJAMBRE
				// Entrada de los enemigos en pantalla
				Global.controlEntrada(nivelActual, contadorIniMovEnj, entradaLimite1, entradaLimite2, entradaLimite3);
				// Movimiento de izquierda a derecha
				contadorMovEnj = Global.vaivenEnjambre(contadorMovEnj, empezar, contadorIniMovEnj, nivelActual);
				/*
				 * Solo puedo devolver una variable, asi que empezar lo controlamos a parte.
				 */
				if (contadorMovEnj >= 30 && contadorMovEnj >= 180) {
					empezar = true;
				}

				// DISPARAR
				heDisparado = Global.controlarDisparos(usuario, nivelActual, comando, invencible, contadorIniMovEnj,
						heDisparado);
				contadorIniMovEnj++;

				// Si esta MUERTO
			} else {
				if (comando.regionMatches(0, "command", 0, 7)) {
					gui.gb_println("Estas muerto, no puedes hacer nada");
				}
				explosion = Global.muerte(explosion, usuario, gui, gato);
			}

			/*
			 * Controlar la frecuencia de disparo. Si ya he disparado, aumento en uno la
			 * frecuencia, rebajando en uno lo que queda para poder disparar de nuevo.
			 */
			if (heDisparado) {
				frecuenciaDisparo++;
			}
			if (frecuenciaDisparo == intervaloDisparo) {
				heDisparado = false;
				frecuenciaDisparo = 0;
			}

			/*
			 * Cuando disparo no escribo en consola
			 */
			if (comando.equals("space")) {
				gui.gb_clearCommandBar();
			}

			// Por consola la ultima accion
			if (!comando.isEmpty()) {
				System.out.println(comando);
			}
			// Actualizar datos del juego
			
			usuario.actualizarDatosInterfaz(gui);
			/*
			 * Boton de nuevo juego. Tanto si pone un nombre como si no, el juego reconoce
			 * el comando y lo ejecuta. Borra el enjambre acutal,reinicia los 3 enjambres,
			 * las variables de control y tanto el jugador como el tablero
			 */
			if (comando.regionMatches(0, "new game", 0, 8)) {// Para que si pone nombre, siga funcionando
				nivelActual.borrarEnjambre();
				nivel1 = new Nivel(1, gui);
				nivel2 = new Nivel(2, gui);
				nivel3 = new Nivel(3, gui);
				nivelActual = nivel1;
				contadorMovEnj = 0;
				empezar = false;
				contadorIniMovEnj = 0;
				explosion = 0;
				invencible = Global.reiniciar(nivelActual, gui, usuario, invencible);
			}
			Thread.sleep(1000 / 60);// esperar
		}
	}
}