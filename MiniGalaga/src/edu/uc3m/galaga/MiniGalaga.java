//Juego de naves espaciales, trabajo fin de asignatura. UC3M. Monica Ocania Bastante & Nicolas Arnedo Villanueva

package edu.uc3m.galaga;
/**
 * Recreacion del Galaga. El jugador se mueve de izquierda a derecha, disparando torpedos
 * para eliminar a alienigenas( enemigos) hasta un maximo del nivel 3.
 * @author Monica Ocania Bastante & Nicolas Arnedo Villanueva
 * @since 2018/11/28
 */
import edu.uc3m.game.GameBoardGUI;

public class MiniGalaga {
	
	private static boolean exit = false;
	
	public static void main(String[] args) throws InterruptedException {
		// CREAR TABLERO
		GameBoardGUI gui = new GameBoardGUI(17, 22);
		Global.crearTablero(gui);
		
		
	
		// imprimir algo en el cuadrado pequenio (consola)
		gui.gb_println("Hola Mundo!");
		
		
		//CREAR ENEMIGO
		Enemigo enemigo = new Enemigo ("comandante galaga", 500, 80, 140 , gui);
		// Ejemplo crear sprite
		gui.gb_addSprite(500, "enemy900.png", true);
		gui.gb_setSpriteVisible(500, true);
		gui.gb_moveSprite(500, 8, 14);
		// para que vaya siempre a la derecha y vuelve a empezar, le sumo 1 a la
		// posiciony hago modulo del maximo (asi va hacia al derecha y vuelve a empezar
		
		// CREAR JUGADOR
		Jugador usuario = new Jugador(gui);
		

		// CREAR ENJAMBRES
		Nivel nivel1 = new Nivel(1, gui);
		Nivel nivel2 = new Nivel(2, gui);
		Nivel nivel3 = new Nivel(3, gui);
		
		/*
		 * Como empezamos en el primer nivel antes de empezar 
		 * lo volvemos visible y lo imprimimos
		 */
		nivel1.enjambreVisible();
		nivel1.alternarImagenEnjambre();
		nivel2.alternarImagenEnjambre();
		nivel3.alternarImagenEnjambre();

		// VARIABLES DE CONTROL DEL MOVIMIENTO
		int contador = 0;
		int contador2 = -1;
		int contador3 = 500;
		int contador4=0;
		String comando;//La tecla que ha pulsado el jugador
		// JUEGO
		while (!exit) {
			comando = gui.gb_getLastAction();
			// MOVER JUGADOR
			usuario.moverJugador(comando);
			
			// MOVER ENJAMBRE
			//Entrada de los enemigos en pantalla
			if(contador4<90) {
				nivel1.entrada();
				}
			contador=Global.vaivenEnjambre(contador, contador2, contador4, nivel1);
			if(contador>=30&& contador>=90) {
				contador2 = 0;
			}
			/*
			 * Cuando llega al borde, baja 1.
			 * Se iguala a contador para seguir teniendo control en el flujo.
			 */
			contador=Global.bajarEnjambre(contador,contador2, contador4, nivel1);
			
			// DISPARAR
			Global.efectuarDisparo(comando, contador3, usuario);
			usuario.moverTorpedos();
			nivel1.destruir(usuario);//Borra a los enemigos que hemos acertado
			
			
			/*
			boolean keepRunning = true;
			boolean keepMoving = false;
			do {
				
				
				if (comando.length() > 0 || keepMoving) {
					// Imprimimos las acciones a modo feedback
					gui.gb_println(comando);
					
					switch (comando) {
					case "right":
						enemigo.mover(Constante.DIR_E, 1);
						break;
					case "down":
						enemigo.mover(Constante.DIR_S, 1);
						break;
					case "up":
						enemigo.mover(Constante.DIR_N, 1);
						break;
					case "tab":
						enemigo.mover(Constante.DIR_W, 1);
						break;
					case "left":
						keepMoving = false;
						break;
					case "space":
						keepMoving = true;
						break;
					}
					if (keepMoving) {
						//Movemos en sentido agujas reloj
						int nextDir = (enemigo.getDireccion() + 1) % 16;
						enemigo.mover(nextDir, 1);
					}
					
					gui.gb_setSpriteImage(enemigo.getIdentificador(), enemigo.getImagen());
					gui.gb_moveSpriteCoord(enemigo.getIdentificador(), enemigo.getCoordenadas().getX(), enemigo.getCoordenadas().getY());

				}
				
				Thread.sleep(50L);

			} while (keepMoving);*/
			//enemigo.mover(Constante.DIR_E);
			
			//Por consola la ultima accion
			if(!comando.isEmpty()) {//No esta vacio
			System.out.println(comando);
			}
			contador4++;
			contador3++;
			Global.actualizarDatos(usuario, gui);
			Thread.sleep(1000 / 60);// esperar
			}
		}
	
	}
