//Los enjambres de cada nivel, como se mueven y se borrar. UC3M. Monica Ocania Bastante & Nicolas Arnedo Villanueva
package edu.uc3m.galaga;

/**
 * Esta clase guarda los enjambres de cada nivel y las
 * acciones que pueden realizar.
 * @author Monica Ocania Bastante & Nicolas Arnedo Villanueva
 * @since 2018/11/28
 */

import edu.uc3m.game.GameBoardGUI;

public class Nivel {
	/**
	 * El enjambre es una matriz compuesta por los enemigos a vencer en ese nivel.
	 * Para representar los huecos en ella, se usan enemigos de tipo "vacio"
	 */
	private Enemigo[][] enjambre = new Enemigo[4][10];
	/**
	 * Que nivel del juego es
	 */
	private int numero;
	private GameBoardGUI gui;
	private Global cte = new Global();

	public Nivel(int nivel, GameBoardGUI gui) {
		this.gui = gui;
		if (nivel > 0 && nivel < 4) {
			setNivel(nivel);
		}
		switch (numero) {
		case 1:
			// Enjambre NIVEL1
			// Espacios vacios del enjambre
			for (int ii = 0; ii < enjambre[0].length; ii++) {
				if (ii != 4 && ii != 5) {
					enjambre[0][ii] = new Enemigo();
				}
			}
			for (int ii = 0; ii < enjambre[1].length; ii++) {
				if (ii == 1 || ii == 4 || ii == 5 || ii == 8) {
					enjambre[1][ii] = new Enemigo();
				}
			}
			enjambre[2][0] = new Enemigo();
			enjambre[2][9] = new Enemigo();
			for (int ii = 0; ii < enjambre[3].length; ii++) {
				if (ii == 0 || ii == 1 || ii == 8 || ii == 9) {
					enjambre[3][ii] = new Enemigo();
				}
			}
			enjambre[0][4] = new Enemigo("comandante galaga", 100, 75, -80, this.gui);
			enjambre[0][5] = new Enemigo("comandante galaga", 101, 85, -80, this.gui);
			enjambre[1][0] = new Enemigo("goei", 102, -55, 20, this.gui);
			enjambre[1][2] = new Enemigo("goei", 103, -35, 20, this.gui);
			enjambre[1][3] = new Enemigo("goei", 104, -25, 20, this.gui);
			enjambre[1][6] = new Enemigo("goei", 105, 185, 20, this.gui);
			enjambre[1][7] = new Enemigo("goei", 106, 195, 20, this.gui);
			enjambre[1][9] = new Enemigo("goei", 107, 215, 20, this.gui);

			enjambre[2][1] = new Enemigo("zako", 108, -45, 120, this.gui);
			enjambre[2][2] = new Enemigo("zako", 109, -35, 120, this.gui);
			enjambre[2][3] = new Enemigo("zako", 110, -25, 120, this.gui);
			enjambre[2][4] = new Enemigo("zako", 111, -15, 120, this.gui);

			enjambre[2][5] = new Enemigo("zako", 112, 175, 120, this.gui);
			enjambre[2][6] = new Enemigo("zako", 113, 185, 120, this.gui);
			enjambre[2][7] = new Enemigo("zako", 114, 195, 120, this.gui);
			enjambre[2][8] = new Enemigo("zako", 115, 205, 120, this.gui);

			enjambre[3][2] = new Enemigo("zako", 116, -35, 130, this.gui);
			enjambre[3][3] = new Enemigo("zako", 117, -25, 130, this.gui);
			enjambre[3][4] = new Enemigo("zako", 118, -15, 130, this.gui);

			enjambre[3][5] = new Enemigo("zako", 119, 175, 130, this.gui);
			enjambre[3][6] = new Enemigo("zako", 120, 185, 130, this.gui);
			enjambre[3][7] = new Enemigo("zako", 121, 195, 130, this.gui);

			/*
			 * // Los espacios rellenos del enjambre
			 * 
			 * enjambre[0][4] = new Enemigo("comandante galaga", 100, 75, 10, this.gui);
			 * enjambre[0][5] = new Enemigo("comandante galaga", 101, 85, 10, this.gui);
			 * enjambre[1][0] = new Enemigo("goei", 102, 35, 20, this.gui); enjambre[1][2] =
			 * new Enemigo("goei", 103, 55, 20, this.gui); enjambre[1][3] = new
			 * Enemigo("goei", 104, 65, 20, this.gui); enjambre[1][6] = new Enemigo("goei",
			 * 105, 95, 20, this.gui); enjambre[1][7] = new Enemigo("goei", 106, 105, 20,
			 * this.gui); enjambre[1][9] = new Enemigo("goei", 107, 125, 20, this.gui);
			 * enjambre[2][1] = new Enemigo("zako", 108, 45, 30, this.gui); enjambre[2][2] =
			 * new Enemigo("zako", 109, 55, 30, this.gui); enjambre[2][3] = new
			 * Enemigo("zako", 110, 65, 30, this.gui); enjambre[2][4] = new Enemigo("zako",
			 * 111, 75, 30, this.gui); enjambre[2][5] = new Enemigo("zako", 112, 85, 30,
			 * this.gui); enjambre[2][6] = new Enemigo("zako", 113, 95, 30, this.gui);
			 * enjambre[2][7] = new Enemigo("zako", 114, 105, 30, this.gui); enjambre[2][8]
			 * = new Enemigo("zako", 115, 115, 30, this.gui); enjambre[3][2] = new
			 * Enemigo("zako", 116, 55, 40, this.gui); enjambre[3][3] = new Enemigo("zako",
			 * 117, 65, 40, this.gui); enjambre[3][4] = new Enemigo("zako", 118, 75, 40,
			 * this.gui); enjambre[3][5] = new Enemigo("zako", 119, 85, 40, this.gui);
			 * enjambre[3][6] = new Enemigo("zako", 120, 95, 40, this.gui); enjambre[3][7] =
			 * new Enemigo("zako", 121, 105, 40, this.gui);
			 */
			break;
		case 2:
			// Enjambre NIVEL2
			for (int ii = 0; ii < enjambre[0].length; ii++) {
				// Espacios vacios del enjambre
				if (ii != 2 && ii != 3 && ii != 6 && ii != 7) {
					enjambre[0][ii] = new Enemigo();
				} else {// Los espacios rellenos del enjambre
					enjambre[0][ii] = new Enemigo("comandante galaga", (200 + ii), (55 + (ii - 2) * 10), 10, this.gui);
				}
			}

			// Los espacios rellenos del enjambre
			for (int ii = 0; ii < enjambre[1].length; ii++) {
				enjambre[1][ii] = new Enemigo("goei", (224 + ii), (35 + ii * 10), 20, this.gui);
			}
			for (int ii = 0; ii < enjambre[2].length; ii++) {
				enjambre[2][ii] = new Enemigo("zako", (234 + ii), (35 + ii * 10), 30, this.gui);
			}
			for (int ii = 0; ii < enjambre[3].length; ii++) {
				enjambre[3][ii] = new Enemigo("zako", (244 + ii), (35 + ii * 10), 40, this.gui);
			}
			break;
		case 3:
			// Enjambre NIVEL3
			for (int ii = 0; ii < enjambre[0].length; ii++) {
				// Los espacios rellenos del enjambre
				if (ii != 1 && ii != 4 && ii != 5 && ii != 8) {
					enjambre[0][ii] = new Enemigo("comandante galaga", (260 + ii), (35 + ii * 10), 10, this.gui);
				} else {// Espacios vacios del enjambre
					enjambre[0][ii] = new Enemigo();
				}
			}
			// Los espacios rellenos del enjambre
			for (int ii = 0; ii < enjambre[1].length; ii++) {
				enjambre[1][ii] = new Enemigo("goei", (306 + ii), (35 + ii * 10), 20, this.gui);
			}
			for (int ii = 0; ii < enjambre[2].length; ii++) {
				// Los espacios rellenos del enjambre
				if (ii != 1 && ii != 4 && ii != 5 && ii != 8) {
					enjambre[2][ii] = new Enemigo("zako", (280 + ii), (35 + ii * 10), 30, this.gui);
				} else {// Espacios vacios del enjambre
					enjambre[2][ii] = new Enemigo();
				}
			}
			// Los espacios rellenos del enjambre
			for (int ii = 0; ii < enjambre[3].length; ii++) {
				enjambre[3][ii] = new Enemigo("zako", (316 + ii), (35 + ii * 10), 40, this.gui);
			}
			break;
		}

	}

	// GETTERS
	public Enemigo[][] getEnjambre() {
		return enjambre;
	}

	public int getNivel() {
		return numero;
	}

	// SETTERS
	public void setNivel(int nivel) {
		this.numero = nivel;
	}

	// METODOS
	/**
	 * Alternamos las imagenes del enjambre para que sea mas vistoso y se distiga
	 * mejor a los enemigos.
	 */
	public void alternarImagenEnjambre() {
		for (int ii = 0; ii < enjambre.length; ii++) {
			for (int jj = 0; jj < enjambre[ii].length; jj++) {
				// Asegurarnos el enemigo existe
				if (!enjambre[ii][jj].getTipo().equals("vacio")) {
					/*
					 * Para saltarnos uno y hacer la alternacion, lo hacemos solo en los pares
					 */
					if (jj % 2 == 0) {
						enjambre[ii][jj].alternarImagen(enjambre[ii][jj].getTipo());
					}

				}
			}
		}
	}

	/**
	 * Cambiar coordenadas al enjambre. "Direccion" indica hacia donde se ha movido
	 * el enjambre, pudiendo ser "arriba", "abajo", "izquierda" o "derecha"
	 * 
	 * @param direccion
	 * @throws InterruptedException
	 */
	public void moverEnjambre(String direccion) throws InterruptedException {

		if (direccion.equals("izquierda")) {
			for (int ii = 0; ii < enjambre.length; ii++) {
				for (int jj = 0; jj < enjambre[ii].length; jj++) {
					/*
					 * Si ese elemento es un enemigo vivo, lo mueve en el eje x
					 */
					if (!enjambre[ii][jj].getTipo().equals("vacio")) {
						enjambre[ii][jj].getCoordenadas().setX((enjambre[ii][jj].getCoordenadas().getX() - 1));
						/*
						 * Cuando cambia de cuadrado (cada 10 casillas), le alternamos la imagen para
						 * dar mayor sensacion de dinamismo
						 */
						if ((enjambre[ii][jj].getCoordenadas().getX() % 10) == 0) {
							enjambre[ii][jj].alternarImagen(enjambre[ii][jj].getTipo());
							// Le ponemos su nueva imagen
							gui.gb_setSpriteImage(enjambre[ii][jj].getIdentificador(), enjambre[ii][jj].getImagen());
							// Thread.sleep(5L);
						}
					}
				}
			}
		}

		if (direccion.equals("derecha")) {
			for (int ii = 0; ii < enjambre.length; ii++) {
				for (int jj = 0; jj < enjambre[ii].length; jj++) {
					/*
					 * Si ese elemento es un enemigo vivo, lo mueve en el eje x
					 */
					if (!enjambre[ii][jj].getTipo().equals("vacio")) {
						enjambre[ii][jj].getCoordenadas().setX((enjambre[ii][jj].getCoordenadas().getX() + 1));
						/*
						 * Cuando cambia de cuadrado (cada 10 casillas), le alternamos la imagen para
						 * dar mayor sensacion de dinamismo
						 */
						if ((enjambre[ii][jj].getCoordenadas().getX() % 10) == 0) {
							enjambre[ii][jj].alternarImagen(enjambre[ii][jj].getTipo());
							// Le ponemos su nueva imagen
							gui.gb_setSpriteImage(enjambre[ii][jj].getIdentificador(), enjambre[ii][jj].getImagen());
							// Thread.sleep(5L);
						}
					}
				}
			}
		}

		if (direccion.equals("abajo")) {
			for (int ii = 0; ii < enjambre.length; ii++) {
				for (int jj = 0; jj < enjambre[ii].length; jj++) {
					/*
					 * Si ese elemento es un enemigo vivo, cambia su altura
					 */
					if (!enjambre[ii][jj].getTipo().equals("vacio")) {
						enjambre[ii][jj].getCoordenadas().setY((enjambre[ii][jj].getCoordenadas().getY() + 1));
					}
				}
			}
		}

		if (direccion.equals("arriba")) {
			for (int ii = 0; ii < enjambre.length; ii++) {
				for (int jj = 0; jj < enjambre[ii].length; jj++) {
					/*
					 * Si ese elemento es un enemigo vivo, cambia su altura
					 */
					if (!enjambre[ii][jj].getTipo().equals("vacio")) {
						enjambre[ii][jj].getCoordenadas().setY((enjambre[ii][jj].getCoordenadas().getY() - 1));
					}
				}
			}
		}
		// Imprimimos en la nueva posicion
		imprimirEnjambre();
	}

	/**
	 * Generar enjambre en pantalla, imprimiendo los enemigos en la posicion
	 * adecuada.
	 */
	public void imprimirEnjambre() {
		for (int ii = 0; ii < enjambre.length; ii++) {
			for (int jj = 0; jj < enjambre[ii].length; jj++) {
				// Hacer solo si ese elemento es de verdad un enemigo
				if (!enjambre[ii][jj].getTipo().equals("vacio")) {
					gui.gb_moveSpriteCoord(enjambre[ii][jj].getIdentificador(),
							enjambre[ii][jj].getCoordenadas().getX(), enjambre[ii][jj].getCoordenadas().getY());
				}
			}
		}
	}

	/**
	 * Vuelve el enjambre visible, haciendo que cada enemigo aparezca en la consola
	 */
	public void enjambreVisible() {
		for (int ii = 0; ii < enjambre.length; ii++) {
			for (int jj = 0; jj < enjambre[ii].length; jj++) {
				// Hacer solo si ese elemento es de verdad un enemigo
				if (!enjambre[ii][jj].getTipo().equals("vacio")) {
					enjambre[ii][jj].crearSprite();
				}
			}
		}
	}

	/**
	 * Colision. Si las casillas coinciden, los sprites se borran de la pantalla.
	 * Para ello recorremos el enjambre, recorriendo en cada posicion del enjambre
	 * los torpedos para ver si algun torpedo ha impactado contra uno de los
	 * enemigos.
	 * 
	 * @param usuario
	 */
	public void destruir(Jugador usuario) {
		for (int ii = 0; ii < enjambre.length; ii++) {
			for (int jj = 0; jj < enjambre[ii].length; jj++) {
				/*
				 * Antes de ver si un torpedo ha impactado, debemos asegurarnos que este enemigo
				 * existe.
				 */
				if (!enjambre[ii][jj].getTipo().equals("vacio")) {
					for (int kk = 0; kk < usuario.getDisparos().length; kk++) {
						/*
						 * Antes de ver si coinciden las posiciones, debemos asegurarnos que el torpedo
						 * ha sido disparado.
						 */
						if (usuario.getDisparos()[kk].getIdentificador() > 0) {
							/*
							 * Como el comandante galaga tiene dos vidas, debemos ver dos cosas. Si es el
							 * comandante galaga y si todavia no le habiamos dado. El primer if comprueba si
							 * es un comandante galaga al que todavia no hayamos dado antes de ver si le ha
							 * impactado un torpedo. El segundo tan solo comprueba si le ha impactado un
							 * torpedo indistintivamente del tipo del enemigo.
							 */
							if (enjambre[ii][jj].getTipo().equals("comandante galaga")
									&& enjambre[ii][jj].getVida() == 2) {
								if (enjambre[ii][jj].getCoordenadas().getX()
										/ (cte.getMin() + 15) == usuario.getDisparos()[kk].getCoordenadas().getX()
												/ (cte.getMin() + 15)
										&& enjambre[ii][jj].getCoordenadas().getY()
												/ (cte.getMin() + 15) == usuario.getDisparos()[kk].getCoordenadas()
														.getY() / (cte.getMin() + 15)) {
									enjambre[ii][jj].setImagen("enemy9G0.png");
									enjambre[ii][jj].setVida(1);
									usuario.getDisparos()[kk].colisionado();
								}
							} else {
								if (enjambre[ii][jj].getCoordenadas().getX()
										/ cte.getMin() == usuario.getDisparos()[kk].getCoordenadas().getX()
												/ cte.getMin()
										&& enjambre[ii][jj].getCoordenadas().getY()
												/ cte.getMin() == usuario.getDisparos()[kk].getCoordenadas().getY()
														/ cte.getMin()) {
									// Borrar el enemigo.
									enjambre[ii][jj].destruido();
									// Borrar el torpedo, sumar los puntos y mostrarlos por pantalla
									usuario.getDisparos()[kk].colisionado();
									usuario.setAciertos(usuario.getAciertos() + 1);
									usuario.setPuntos(usuario.getPuntos() + enjambre[ii][jj].getPuntos());
								}
							}
						}
					}
				}
			}

		}

	}

	/**
	 *  Aqui guardamos la entrada de cada nivel;
	 */
	public void entrada() {
		switch (numero) {
		case 1: // 22 enemigos / 2 CG / 6 G / 14 Z
			/**
			 *  Para el nivel 1 tenemos la entrada del CG, puestas individuales y luego 3 bucles for para las entradas de los goei y los zako. 
			 *  Dentro de cada bucle "for" asignamos la direccion de los sprite y la imagen que deben de tomar en esa direccion hasta llegar a su posicion.
			 */
			
			enjambre[0][4].mover(Global.DIR_S);
			gui.gb_setSpriteImage(enjambre[0][4].getIdentificador(), "enemy108.png");
			enjambre[0][5].mover(Global.DIR_S);
			gui.gb_setSpriteImage(enjambre[0][5].getIdentificador(), "enemy108.png");

			for (int i = 0; i < enjambre[1].length; i++) {
				if (i != 1 && i != 4 && i != 5 && i != 8) { //condicion para seleccionarlos dentro de la matriz
					if (i < 4) { //condicion de los enemgios que salen de la parte izquierda del tablero
						enjambre[1][i].mover(Global.DIR_E);
						gui.gb_setSpriteImage(enjambre[1][i].getIdentificador(), "enemy204.png");
					} else { //condicion de los enemgios que salen de la parte derecha del tablero
						enjambre[1][i].mover(Global.DIR_W);
						gui.gb_setSpriteImage(enjambre[1][i].getIdentificador(), "enemy212.png");
					}
				}
			}
			for (int i = 0; i < enjambre[2].length; i++) {
				if (i != 0 && i != 9) {
					if (i < 5) {
						enjambre[2][i].mover(Global.DIR_NE);
						gui.gb_setSpriteImage(enjambre[2][i].getIdentificador(), "enemy302.png");
					} else {
						enjambre[2][i].mover(Global.DIR_NW);
						gui.gb_setSpriteImage(enjambre[2][i].getIdentificador(), "enemy314.png");
					}
				}
			}
			for (int i = 0; i < enjambre[3].length; i++) {
				if (i!=0 && i!=1 && i!=8 && i!=9) {
					if (i < 5) {
						enjambre[3][i].mover(Global.DIR_NE);
						gui.gb_setSpriteImage(enjambre[3][i].getIdentificador(), "enemy302.png");
					} else {
						enjambre[3][i].mover(Global.DIR_NW);
						gui.gb_setSpriteImage(enjambre[3][i].getIdentificador(), "enemy314.png");
					}
				}
			}
			imprimirEnjambre();
		case 2:
		case 3:

		}

	}

}