//Los enjambres de cada nivel, como se mueven y se borran. UC3M. Monica Ocania Bastante & Nicolas Arnedo Villanueva
package edu.uc3m.galaga;

/**
 * Esta clase guarda los enjambres de cada nivel y las
 * acciones que pueden realizar.
 * @author Monica Ocania Bastante & Nicolas Arnedo Villanueva
 * @since 2018/12/19
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
	/**
	 * Si es el nivel actual o no
	 */
	private boolean actual = false;
	/**
	 * Interfaz donde aparecen
	 */
	private GameBoardGUI gui;

	// CONSTRUCTOR
	public Nivel(int nivel, GameBoardGUI gui) {
		this.gui = gui;
		if (nivel > 0 && nivel < 4) {
			setNivel(nivel);
		}
		/*
		 * Cada nivel tiene formaciones distintas en el enjambre
		 */
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
					enjambre[1][ii].setEstado("muerto");
				}
			}
			enjambre[2][0] = new Enemigo();
			enjambre[2][9] = new Enemigo();
			for (int ii = 0; ii < enjambre[3].length; ii++) {
				if (ii == 0 || ii == 1 || ii == 8 || ii == 9) {
					enjambre[3][ii] = new Enemigo();
				}
			}

			// Espacios ocupados
			// Comandantes Galaga
			/*
			 * Establecer los goei que saldran con el comandante a atacar.
			 * Esta es su posicion respecto a su fila
			 */
			int[] a = { 2, 3 }, b = { 6, 7 };
			enjambre[0][4] = new Enemigo("comandante galaga", 100, 75, -10, this.gui, 0, 75, 10);
			enjambre[0][4].setAliados(a);
			enjambre[0][5] = new Enemigo("comandante galaga", 101, 85, -10, this.gui, -1, 85, 10);
			enjambre[0][5].setAliados(b);
			// Goei
			enjambre[1][0] = new Enemigo("goei", 102, -55, 20, this.gui, -2, 35, 20);
			enjambre[1][2] = new Enemigo("goei", 103, -35, 20, this.gui, -3, 55, 20);
			enjambre[1][3] = new Enemigo("goei", 104, -25, 20, this.gui, -4, 65, 20);
			enjambre[1][6] = new Enemigo("goei", 105, 185, 20, this.gui, -5, 95, 20);
			enjambre[1][7] = new Enemigo("goei", 106, 195, 20, this.gui, -6, 105, 20);
			enjambre[1][9] = new Enemigo("goei", 107, 215, 20, this.gui, -7, 125, 20);
			// zakos
			enjambre[2][1] = new Enemigo("zako", 108, -45, 120, this.gui, -8, 45, 30);
			enjambre[2][2] = new Enemigo("zako", 109, -35, 120, this.gui, -9, 55, 30);
			enjambre[2][3] = new Enemigo("zako", 110, -25, 120, this.gui, -10, 65, 30);
			enjambre[2][4] = new Enemigo("zako", 111, -15, 120, this.gui, -11, 75, 30);

			enjambre[2][5] = new Enemigo("zako", 112, 185, 120, this.gui, -12, 85, 30);
			enjambre[2][6] = new Enemigo("zako", 113, 195, 120, this.gui, -13, 95, 30);
			enjambre[2][7] = new Enemigo("zako", 114, 205, 120, this.gui, -14, 105, 30);
			enjambre[2][8] = new Enemigo("zako", 115, 215, 120, this.gui, -15, 115, 30);

			enjambre[3][2] = new Enemigo("zako", 116, -35, 130, this.gui, -16, 55, 40);
			enjambre[3][3] = new Enemigo("zako", 117, -25, 130, this.gui, -17, 65, 40);
			enjambre[3][4] = new Enemigo("zako", 118, -15, 130, this.gui, -18, 75, 40);

			enjambre[3][5] = new Enemigo("zako", 119, 175, 130, this.gui, -19, 85, 40);
			enjambre[3][6] = new Enemigo("zako", 120, 185, 130, this.gui, -20, 95, 40);
			enjambre[3][7] = new Enemigo("zako", 121, 195, 130, this.gui, -21, 105, 40);

			break;
		case 2:
			// Enjambre NIVEL2
			// Comandantes Galaga
			for (int ii = 0; ii < enjambre[0].length; ii++) {
				// Espacios vacios del enjambre
				if (ii != 2 && ii != 3 && ii != 6 && ii != 7) {
					enjambre[0][ii] = new Enemigo();

				} else {// Los espacios rellenos del enjambre
					enjambre[0][ii] = new Enemigo("comandante galaga", (200 + ii), (55 + (ii - 2) * 10), -5, this.gui,
							-20 - ii, (55 + (ii - 2) * 10), 10);
				}
			}
			/*
			 * Establecer los goei que saldran con el comandante a atacar.
			 * Esta es su posicion respecto a su fila
			 */
			int[] c = { 1, 2 },d = { 3, 4 },e = { 5, 6 },f = { 7, 8 };
			enjambre[0][2].setAliados(c);
			enjambre[0][3].setAliados(d);
			enjambre[0][6].setAliados(e);
			enjambre[0][7].setAliados(f);
			// Los espacios rellenos del enjambre
			// Goei
			for (int ii = 0; ii < enjambre[1].length; ii++) {
				if (ii <= 4) {
					enjambre[1][ii] = new Enemigo("goei", (224 + ii), -5, (20 + (ii % 5) * 10), this.gui, -30 - ii,
							(35 + ii * 10), 20);
				} else
					enjambre[1][ii] = new Enemigo("goei", (224 + ii), 175, (20 + (ii % 5) * 10), this.gui, -30 - ii,
							(35 + ii * 10), 20);
			}
			// Zako
			for (int ii = 0; ii < enjambre[2].length; ii++) {
				if (ii <= 4) {
					enjambre[2][ii] = new Enemigo("zako", (254 + ii), (-5 - ii * 10), 170, this.gui, -41 - ii,
							(35 + ii * 10), 30);
				} else
					enjambre[2][ii] = new Enemigo("zako", (254 + ii), (175 + (ii % 5) * 10), 170, this.gui, -41 - ii,
							(35 + ii * 10), 30);
			}
			for (int ii = 0; ii < enjambre[3].length; ii++) {
				if (ii <= 4) {
					enjambre[3][ii] = new Enemigo("zako", (274 + ii), 5, (-5 - ii * 10), this.gui, -51 - ii,
							(35 + ii * 10), 40);
				} else
					enjambre[3][ii] = new Enemigo("zako", (274 + ii), 165, (-5 - (ii % 5) * 10), this.gui, -51 - ii,
							(35 + ii * 10), 40);
			}
			break;

		case 3:
			// Enjambre NIVEL3
			for (int ii = 0; ii < enjambre[0].length; ii++) {
				// Los espacios rellenos del enjambre
				// Comandantes Galga
				if (ii != 1 && ii != 4 && ii < 5) {
					enjambre[0][ii] = new Enemigo("comandante galaga", (460 + ii), 5, (-5 - ii * 10), this.gui,
							-60 - ii, (35 + ii * 10), 10);
				} else if (ii != 5 && ii != 8 && ii >= 5) {
					enjambre[0][ii] = new Enemigo("comandante galaga", (460 + ii), 165, (-5 - (ii % 5) * 10), this.gui,
							-60 - ii, (35 + ii * 10), 10);
				} else {// Espacios vacios del enjambre
					enjambre[0][ii] = new Enemigo();
				}
			}
			/*
			 * Establecer los goei que saldran con el comandante a atacar.
			 * Esta es su posicion respecto a su fila
			 */
			int[] cc = { 1, 2 }, dd = { 3, 4 },ee = { 5, 6 },ff = { 7, 8 },gg = { 0, 0 }, hh = { 9, 9 };
			enjambre[0][0].setAliados(gg);
			enjambre[0][2].setAliados(cc);
			enjambre[0][3].setAliados(dd);
			enjambre[0][6].setAliados(ee);
			enjambre[0][7].setAliados(ff);
			enjambre[0][9].setAliados(hh);
			// Los espacios rellenos del enjambre
			// Goei
			for (int ii = 0; ii < enjambre[1].length; ii++) {
				enjambre[1][ii] = new Enemigo("goei", (406 + ii), (35 + ii * 10), -5, this.gui, -70 - ii,
						(35 + ii * 10), 20);
			}
			// Zako
			for (int ii = 0; ii < enjambre[2].length; ii++) {
				// Los espacios rellenos del enjambre
				if (ii == 0) {
					enjambre[2][ii] = new Enemigo("zako", (480 + ii), -10, 60, this.gui, -80 - ii, (35 + ii * 10), 30);
				} else if (ii != 1 && ii != 4 && ii < 4) {
					enjambre[2][ii] = new Enemigo("zako", (480 + ii), -10, (50 + ii * 10), this.gui, -80 - ii,
							(35 + ii * 10), 30);
				} else if (ii > 4 && ii != 5 && ii != 8 && ii < 8) {
					enjambre[2][ii] = new Enemigo("zako", (480 + ii), 180, (55 + (ii % 5) * 10), this.gui, -80 - ii,
							(35 + ii * 10), 30);
				} else if (ii == 9) {
					enjambre[2][ii] = new Enemigo("zako", (480 + ii), 180, 55, this.gui, -80 - ii, (35 + ii * 10), 30);
				} else {// Espacios vacios del enjambre
					enjambre[2][ii] = new Enemigo();
				}
			}
			// Los espacios rellenos del enjambre
			for (int ii = 0; ii < enjambre[3].length; ii++) {
				if (ii < 5) {
					enjambre[3][ii] = new Enemigo("zako", (416 + ii), -10, (90 + ii * 10), this.gui, -90 - ii,
							(35 + ii * 10), 40);
				} else {
					enjambre[3][ii] = new Enemigo("zako", (416 + ii), 180, (85 + (ii % 5) * 10), this.gui, -90 - ii,
							(35 + ii * 10), 40);
				}
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

	public boolean isActual() {
		return actual;
	}

	// SETTERS
	public void setNivel(int nivel) {
		this.numero = nivel;
	}

	public void setActual(boolean actual) {
		this.actual = actual;
	}

	// METODOS
	// referentes a su IMAGEN
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
						enjambre[ii][jj].alternarImagen();
					}

				}
			}
		}
	}

	/**
	 * Comprueba si queda algun enemigo vivo en el enjambre
	 * 
	 * @return true si hay 1 o mas enemigos vivos, false si estan todos muertos
	 */
	public boolean isEnjambreVivo() {
		boolean vivo = false;
		for (int ii = 0; ii < enjambre.length; ii++) {
			for (int jj = 0; jj < enjambre[ii].length; jj++) {
				/*
				 * Si ya ha encontrado a uno vivo, no necesito seguir comprobando
				 */
				if (!vivo) {
					if (!enjambre[ii][jj].getTipo().equals("vacio")) {
						if (!enjambre[ii][jj].getEstado().equals("muerto")) {
							vivo = true;
						}
					}
				}
			}
		}
		return vivo;
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
	 * Borra al enjambre de la pantalla
	 */
	private void enjambreInvisible() {
		for (int ii = 0; ii < enjambre.length; ii++) {
			for (int jj = 0; jj < enjambre[ii].length; jj++) {
				/*
				 * Borramos aquellos que existan y esten vivos
				 */
				if (!enjambre[ii][jj].getTipo().equals("vacio") && !enjambre[ii][jj].getEstado().equals("muerto")
						&& !enjambre[ii][jj].getEstado().equals("explotando")) {
					gui.gb_setSpriteVisible(enjambre[ii][jj].getIdentificador(), false);
				}

			}
		}

	}

	/**
	 * Borra al enjambre y sus dispoaros
	 */
	public void borrarEnjambre() {
		borrarDisparos();
		enjambreInvisible();
	}

	// referentes al MOVIMIENTO
	/**
	 * Cambiar coordenadas al enjambre. Dependiendo del estado del enemigo hara
	 * cosas distintas. Si esta en el enjambre, actualizara su posicion y el hueco y
	 * aleqatoriamente podra disparar o salir a atacar. Si esta realizando la
	 * entrada, se actualiza su hueco. Si esta vovliendo, se actualiza el hueco y se
	 * le aplica el metodo volver. Si esta atacando, se actualiza el hueco y se le
	 * aplica el metodo zigzag. Si esta explotando, se le aplica el metodo explotar.
	 * 
	 * @param direccion En que direccion se va a mover el enjambre.
	 * @throws InterruptedException
	 */
	public void moverEnjambre(String direccion) {
		// Numero aleatorio
		int disparar;
		int atacar;
		// Por que se multiplica el Math.random()
		int probabilidadDisparo = 300;
		int probabilidadAtaque = 700;
		if (direccion.equals("izquierda")) {
			for (int ii = 0; ii < enjambre.length; ii++) {
				for (int jj = 0; jj < enjambre[ii].length; jj++) {
					/*
					 * Si ese elemento es un enemigo vivo, realiza una accion segun su estado
					 */
					if (!enjambre[ii][jj].getTipo().equals("vacio")) {

						if (enjambre[ii][jj].getEstado().equals("enjambre")) {
							enjambre[ii][jj].getCoordenadas().setX((enjambre[ii][jj].getCoordenadas().getX() - 1));
							enjambre[ii][jj].getHueco().setX((enjambre[ii][jj].getHueco().getX() - 1));
							/*
							 * Cuando cambia de cuadrado (cada 10 casillas), le alternamos la imagen para
							 * dar mayor sensacion de dinamismo
							 */
							if ((enjambre[ii][jj].getCoordenadas().getX() % 10) == 0) {
								enjambre[ii][jj].alternarImagen();
								gui.gb_setSpriteImage(enjambre[ii][jj].getIdentificador(),
										enjambre[ii][jj].getImagen());
							}
							/*
							 * Posibiblidad de que salga del enjambre a por el jugador Solo los zakos salen
							 * solos a atacar. Los goei solo salen para cubrir a los comandantes, por lo que
							 * no deben adelantarse
							 */
							if (!enjambre[ii][jj].getTipo().equals("goei")) {
								atacar = (int) (Math.random() * probabilidadAtaque);
								if (atacar == 7) {
									if (enjambre[ii][jj].getTipo().equals("zako")) {
										enjambre[ii][jj].setEstado("atacando");
										enjambre[ii][jj].getHueco().setX(enjambre[ii][jj].getCoordenadas().getX());
										enjambre[ii][jj].getHueco().setY(enjambre[ii][jj].getCoordenadas().getY());
									} else {
										/*
										 * Si es un comandante, el y sus dos escoltas deben salir a atacar
										 */
										enjambre[ii][jj].setEstado("atacando");
										enjambre[ii][jj].getHueco().setX(enjambre[ii][jj].getCoordenadas().getX());
										enjambre[ii][jj].getHueco().setY(enjambre[ii][jj].getCoordenadas().getY());
										// Escolta 1 (si esta vivo)
										if (enjambre[1][enjambre[ii][jj].getAliados()[0]].getEstado()
												.equals("enjambre")) {
											enjambre[1][enjambre[ii][jj].getAliados()[0]].setEstado("atacando");
											enjambre[1][enjambre[ii][jj].getAliados()[0]].getHueco()
													.setX(enjambre[1][enjambre[ii][jj].getAliados()[0]].getCoordenadas()
															.getX());
											enjambre[1][enjambre[ii][jj].getAliados()[0]].getHueco()
													.setY(enjambre[1][enjambre[ii][jj].getAliados()[0]].getCoordenadas()
															.getY());
											/*
											 * Hacemos que se ponga delante del comandante un poco a la izquierda para
											 * asegurarnos de que le cubre
											 */
											enjambre[1][enjambre[ii][jj].getAliados()[0]].getCoordenadas()
													.setX(enjambre[ii][jj].getCoordenadas().getX() - 5);
										}
										// Escolta 2 (si esta vivo)
										if (enjambre[1][enjambre[ii][jj].getAliados()[1]].getEstado()
												.equals("enjambre")) {
											enjambre[1][enjambre[ii][jj].getAliados()[1]].setEstado("atacando");
											enjambre[1][enjambre[ii][jj].getAliados()[1]].getHueco()
													.setX(enjambre[1][enjambre[ii][jj].getAliados()[1]].getCoordenadas()
															.getX());
											enjambre[1][enjambre[ii][jj].getAliados()[1]].getHueco()
													.setY(enjambre[1][enjambre[ii][jj].getAliados()[1]].getCoordenadas()
															.getY());
											/*
											 * Hacemos que se ponga delante del comandante un poco a la derecha para
											 * asegurarnos de que le cubre
											 */
											enjambre[1][enjambre[ii][jj].getAliados()[1]].getCoordenadas()
													.setX(enjambre[ii][jj].getCoordenadas().getX() + 5);
										}
									}
								}
							}
							disparar = (int) (Math.random() * probabilidadDisparo);
							if (disparar == 7) {
								enjambre[ii][jj].getDisparo().lanzar(enjambre[ii][jj].getCoordenadas());
							}
						}
						/*
						 * Si el enemigo esta muerto y explotando su imagen, entonces le cambiamos su
						 * imagen a la siguiente fase de la explosion
						 */
						if (enjambre[ii][jj].getEstado().equals("explotando")) {
							enjambre[ii][jj].explotar();
						}
						if (enjambre[ii][jj].getEstado().equals("volviendo")) {
							enjambre[ii][jj].volver();
							// Asegurarnos de que esta volviendo y no en el enjambre
							if (enjambre[ii][jj].getEstado().equals("volviendo")) 
								enjambre[ii][jj].getHueco().setX((enjambre[ii][jj].getHueco().getX() - 1));
							

						}
						if (enjambre[ii][jj].getEstado().equals("atacando")) {
							enjambre[ii][jj].zigzag();
							enjambre[ii][jj].getHueco().setX((enjambre[ii][jj].getHueco().getX() - 1));
							// Si se ha salido de la pantalla, ponerlo arriba del todo
							if (enjambre[ii][jj].getCoordenadas().getY() > 225) {
								enjambre[ii][jj].setEstado("volviendo");
								enjambre[ii][jj].getCoordenadas().setY(-5);
							}

						}
						if (enjambre[ii][jj].getEstado().equals("entrada")) {
							enjambre[ii][jj].getHueco().setX((enjambre[ii][jj].getHueco().getX() - 1));
						}
					}
				}
			}
		}

		if (direccion.equals("derecha")) {
			for (int ii = 0; ii < enjambre.length; ii++) {
				for (int jj = 0; jj < enjambre[ii].length; jj++) {
					/*
					 * Si ese elemento es un enemigo vivo, realiza una accion segun su estado
					 */
					if (!enjambre[ii][jj].getTipo().equals("vacio")) {

						if (enjambre[ii][jj].getEstado().equals("enjambre")) {
							enjambre[ii][jj].getCoordenadas().setX((enjambre[ii][jj].getCoordenadas().getX() + 1));
							enjambre[ii][jj].getHueco().setX(enjambre[ii][jj].getCoordenadas().getX());
							/*
							 * Cuando cambia de cuadrado (cada 10 casillas), le alternamos la imagen para
							 * dar mayor sensacion de dinamismo
							 */
							if ((enjambre[ii][jj].getCoordenadas().getX() % 10) == 0) {
								enjambre[ii][jj].alternarImagen(); // Le ponemos su nueva
																	// imagen
								gui.gb_setSpriteImage(enjambre[ii][jj].getIdentificador(),
										enjambre[ii][jj].getImagen());
								/*
								 * Posibiblidad de que salga del enjambre a por el jugador Solo los zakos salen
								 * solos a atacar. Los goei solo salen para cubrir a los comandantes, por lo que
								 * no deben adelantarse
								 */
								if (!enjambre[ii][jj].getTipo().equals("goei")) {
									atacar = (int) (Math.random() * probabilidadAtaque);
									if (atacar == 7) {
										if (enjambre[ii][jj].getTipo().equals("zako")) {
											enjambre[ii][jj].setEstado("atacando");
											enjambre[ii][jj].getHueco().setX(enjambre[ii][jj].getCoordenadas().getX());
											enjambre[ii][jj].getHueco().setY(enjambre[ii][jj].getCoordenadas().getY());
										} else {
											enjambre[ii][jj].setEstado("atacando");
											enjambre[ii][jj].getHueco().setX(enjambre[ii][jj].getCoordenadas().getX());
											enjambre[ii][jj].getHueco().setY(enjambre[ii][jj].getCoordenadas().getY());
											if (enjambre[1][enjambre[ii][jj].getAliados()[0]].getEstado()
													.equals("enjambre")) {
												enjambre[1][enjambre[ii][jj].getAliados()[0]].setEstado("atacando");
												enjambre[1][enjambre[ii][jj].getAliados()[0]].getHueco()
														.setX(enjambre[1][enjambre[ii][jj].getAliados()[0]]
																.getCoordenadas().getX());
												enjambre[1][enjambre[ii][jj].getAliados()[0]].getHueco()
														.setY(enjambre[1][enjambre[ii][jj].getAliados()[0]]
																.getCoordenadas().getY());
												/*
												 * Hacemos que se ponga delante del comandante un poco a la izquierda
												 * para asegurarnos de que le cubre
												 */
												enjambre[1][enjambre[ii][jj].getAliados()[0]].getCoordenadas()
														.setX(enjambre[ii][jj].getCoordenadas().getX() - 5);
											}
											if (enjambre[1][enjambre[ii][jj].getAliados()[1]].getEstado()
													.equals("enjambre")) {
												enjambre[1][enjambre[ii][jj].getAliados()[1]].setEstado("atacando");
												enjambre[1][enjambre[ii][jj].getAliados()[1]].getHueco()
														.setX(enjambre[1][enjambre[ii][jj].getAliados()[1]]
																.getCoordenadas().getX());
												enjambre[1][enjambre[ii][jj].getAliados()[1]].getHueco()
														.setY(enjambre[1][enjambre[ii][jj].getAliados()[1]]
																.getCoordenadas().getY());
												/*
												 * Hacemos que se ponga delante del comandante un poco a la derecha para
												 * asegurarnos de que le cubre
												 */
												enjambre[1][enjambre[ii][jj].getAliados()[1]].getCoordenadas()
														.setX(enjambre[ii][jj].getCoordenadas().getX() + 5);
											}
										}
									}
								}

							}
							disparar = (int) (Math.random() * probabilidadDisparo);
							if (disparar == 7) {
								enjambre[ii][jj].getDisparo().lanzar(enjambre[ii][jj].getCoordenadas());
							}

						}
						/*
						 * Si el enemigo esta muerto y explotando su imagen, entonces le cambiamos su
						 * imagen a la siguiente fase de la explosion
						 */
						if (enjambre[ii][jj].getEstado().equals("explotando")) {
							enjambre[ii][jj].explotar();
						}
						if (enjambre[ii][jj].getEstado().equals("volviendo")) {
							enjambre[ii][jj].volver();
							if (enjambre[ii][jj].getEstado().equals("volviendo")) 
								enjambre[ii][jj].getHueco().setX((enjambre[ii][jj].getHueco().getX() + 1));
							

						}
						if (enjambre[ii][jj].getEstado().equals("atacando")) {
							enjambre[ii][jj].zigzag();
							enjambre[ii][jj].getHueco().setX((enjambre[ii][jj].getHueco().getX() + 1));
							if (enjambre[ii][jj].getCoordenadas().getY() > 225) {
								enjambre[ii][jj].setEstado("volviendo");
								enjambre[ii][jj].getCoordenadas().setY(-5);
							}

						}
						if (enjambre[ii][jj].getEstado().equals("entrada")) {
							enjambre[ii][jj].getHueco().setX((enjambre[ii][jj].getHueco().getX() + 1));
						}
					}
				}
			}
		}

		if (direccion.equals("abajo")) {
			for (int ii = 0; ii < enjambre.length; ii++) {
				for (int jj = 0; jj < enjambre[ii].length; jj++) {
					/*
					 * Si ese elemento es un enemigo vivo, realiza una accion segun su estado
					 */
					if (!enjambre[ii][jj].getTipo().equals("vacio")) {
						/*
						 * Si esta dentro de enjambre, se mueve 1 hacia arriba
						 */
						if (enjambre[ii][jj].getEstado().equals("enjambre")) {
							enjambre[ii][jj].getCoordenadas().setY((enjambre[ii][jj].getCoordenadas().getY() + 1));
							enjambre[ii][jj].getHueco().setY(enjambre[ii][jj].getCoordenadas().getY());
							/*
							 * Posibiblidad de que salga del enjambre a por el jugador
							 */
							if (!enjambre[ii][jj].getTipo().equals("goei")) {
								atacar = (int) (Math.random() * probabilidadAtaque);
								if (atacar == 7) {
									if (enjambre[ii][jj].getTipo().equals("zako")) {
										enjambre[ii][jj].setEstado("atacando");
										enjambre[ii][jj].getHueco().setX(enjambre[ii][jj].getCoordenadas().getX());
										enjambre[ii][jj].getHueco().setY(enjambre[ii][jj].getCoordenadas().getY());
									} else {
										enjambre[ii][jj].setEstado("atacando");
										enjambre[ii][jj].getHueco().setX(enjambre[ii][jj].getCoordenadas().getX());
										enjambre[ii][jj].getHueco().setY(enjambre[ii][jj].getCoordenadas().getY());
										if (enjambre[1][enjambre[ii][jj].getAliados()[0]].getEstado()
												.equals("enjambre")) {
											enjambre[1][enjambre[ii][jj].getAliados()[0]].setEstado("atacando");
											enjambre[1][enjambre[ii][jj].getAliados()[0]].getHueco()
													.setX(enjambre[1][enjambre[ii][jj].getAliados()[0]].getCoordenadas()
															.getX());
											enjambre[1][enjambre[ii][jj].getAliados()[0]].getHueco()
													.setY(enjambre[1][enjambre[ii][jj].getAliados()[0]].getCoordenadas()
															.getY());
											enjambre[1][enjambre[ii][jj].getAliados()[0]].getCoordenadas()
													.setX(enjambre[ii][jj].getCoordenadas().getX() - 5);
										}
										if (enjambre[1][enjambre[ii][jj].getAliados()[1]].getEstado()
												.equals("enjambre")) {
											enjambre[1][enjambre[ii][jj].getAliados()[1]].setEstado("atacando");
											enjambre[1][enjambre[ii][jj].getAliados()[1]].getHueco()
													.setX(enjambre[1][enjambre[ii][jj].getAliados()[1]].getCoordenadas()
															.getX());
											enjambre[1][enjambre[ii][jj].getAliados()[1]].getHueco()
													.setY(enjambre[1][enjambre[ii][jj].getAliados()[1]].getCoordenadas()
															.getY());
											enjambre[1][enjambre[ii][jj].getAliados()[1]].getCoordenadas()
													.setX(enjambre[ii][jj].getCoordenadas().getX() - 5);
										}
									}
								}
							}
						}
						/*
						 * Si el enemigo esta muerto y explotando su imagen, entonces le cambiamos su
						 * imagen a la siguiente fase de la explosion
						 */
						if (enjambre[ii][jj].getEstado().equals("explotando")) {
							enjambre[ii][jj].explotar();
						}
						if (enjambre[ii][jj].getEstado().equals("volviendo")) {
							enjambre[ii][jj].volver();
							if (enjambre[ii][jj].getEstado().equals("volviendo")) 
								enjambre[ii][jj].getHueco().setY((enjambre[ii][jj].getHueco().getY() + 1));
					
						}
						if (enjambre[ii][jj].getEstado().equals("atacando")) {
							enjambre[ii][jj].zigzag();
							enjambre[ii][jj].getHueco().setY((enjambre[ii][jj].getHueco().getY() + 1));
							if (enjambre[ii][jj].getCoordenadas().getY() > 225) {
								enjambre[ii][jj].setEstado("volviendo");
								enjambre[ii][jj].getCoordenadas().setY(-5);
							}

						}
						if (enjambre[ii][jj].getEstado().equals("entrada")) {
							enjambre[ii][jj].getHueco().setY((enjambre[ii][jj].getHueco().getY() + 1));
						}
					}
				}
			}
		}

		if (direccion.equals("arriba")) {
			for (int ii = 0; ii < enjambre.length; ii++) {
				for (int jj = 0; jj < enjambre[ii].length; jj++) {
					/*
					 * Si ese elemento es un enemigo vivo, realiza una accion segun su estado
					 */
					if (!enjambre[ii][jj].getTipo().equals("vacio")) {
						/*
						 * Si esta dentro de enjambre, se mueve 1 hacia arriba
						 */
						if (enjambre[ii][jj].getEstado().equals("enjambre")) {
							enjambre[ii][jj].getCoordenadas().setY((enjambre[ii][jj].getCoordenadas().getY() - 1));
							enjambre[ii][jj].getHueco().setY(enjambre[ii][jj].getCoordenadas().getY());
							/*
							 * Posibiblidad de que salga del enjambre a por el jugador
							 */
							atacar = (int) (Math.random() * probabilidadAtaque);
							if (atacar == 7) {
								enjambre[ii][jj].setEstado("atacando");
							}
						}
						/*
						 * Si el enemigo esta muerto y explotando su imagen, entonces le cambiamos su
						 * imagen a la siguiente fase de la explosion
						 */
						if (enjambre[ii][jj].getEstado().equals("explotando")) {
							enjambre[ii][jj].explotar();
						}
						if (enjambre[ii][jj].getEstado().equals("volviendo")) {
							enjambre[ii][jj].volver();
							if (enjambre[ii][jj].getEstado().equals("volviendo")) {
								enjambre[ii][jj].getHueco().setY((enjambre[ii][jj].getHueco().getY() - 1));
							} else {
								enjambre[ii][jj].getHueco().setX(enjambre[ii][jj].getCoordenadas().getX());
								enjambre[ii][jj].getHueco().setY(enjambre[ii][jj].getCoordenadas().getY());
							}
						}
						if (enjambre[ii][jj].getEstado().equals("atacando")) {
							enjambre[ii][jj].zigzag();
							enjambre[ii][jj].getHueco().setY((enjambre[ii][jj].getHueco().getY() - 1));
							if (enjambre[ii][jj].getCoordenadas().getY() > 225) {
								enjambre[ii][jj].setEstado("volviendo");
								enjambre[ii][jj].getCoordenadas().setY(-5);
							}

						}
						if (enjambre[ii][jj].getEstado().equals("entrada")) {
							enjambre[ii][jj].getHueco().setY((enjambre[ii][jj].getHueco().getY() - 1));
						}
					}
				}
			}
		}
		// Imprimimos en la nueva posicion
		imprimirEnjambre();
	}

	/**
	 * Aqui guardamos la entrada de cada nivel;
	 */
	public void entrada(int cont) {
		switch (numero) {
		case 1:
			/**
			 * Para el nivel 1 tenemos la entrada del CG, puestas individuales y luego 3
			 * bucles for para las entradas de los goei y los zako. Dentro de cada bucle
			 * "for" asignamos la direccion de los sprite y la imagen que deben de tomar en
			 * esa direccion hasta llegar a su posicion.
			 */
			// GALAGA
			if (cont < 30) {
				enjambre[0][4].mover(Global.DIR_S, true);
				enjambre[0][5].mover(Global.DIR_S, true);
			} else {
				if (cont == 30) {
					enjambre[0][4].mover(Global.DIR_NNW, true);
					enjambre[0][5].mover(Global.DIR_NNW, true);
				} else {
					if (cont > 30) {
						enjambre[0][4].vuelta("horario");
						enjambre[0][5].vuelta("antihorario");
					}
				}
			}

			// GOIE

			if (cont > 30 && cont < 90) {
				for (int i = 0; i < enjambre[1].length; i++) {
					if (i != 1 && i != 4 && i != 5 && i != 8) { // condicion para seleccionarlos dentro de la matriz
						if (i < 4) { // condicion de los enemigos que salen de la parte izquierda del tablero
							enjambre[1][i].mover(Global.DIR_SE, true);
						} else { // condicion de los enemgios que salen de la parte derecha del tablero
							enjambre[1][i].mover(Global.DIR_SW, true);

						}
					}
				}

			} else {
				if (cont > 90 && cont < 120) {
					for (int i = 0; i < enjambre[1].length; i++) {
						if (i != 1 && i != 4 && i != 5 && i != 8) { // condicion para seleccionarlos dentro de la matriz
							if (i < 4) { // condicion de los enemgios que salen de la parte izquierda del tablero
								enjambre[1][i].mover(Global.DIR_NE, true);
							} else { // condicion de los enemgios que salen de la parte derecha del tablero
								enjambre[1][i].mover(Global.DIR_NW, true);
							}
						}
					}
				} else {
					if (cont >= 120) {
						for (int i = 0; i < enjambre[1].length; i++) {
							if (i != 1 && i != 4 && i != 5 && i != 8) { // condicion para seleccionarlos dentro de la
																		// matriz
								if (i < 4) { // condicion de los enemgios que salen de la parte izquierda del tablero
									enjambre[1][i].vuelta("horario");
								} else { // condicion de los enemgios que salen de la parte derecha del tablero
									enjambre[1][i].vuelta("antihorario");
								}
							}
						}
					}
				}
			}

			// ZAKO
			if (cont > 40 && cont < 90) {
				for (int i = 0; i < enjambre[2].length; i++) {
					if (i != 0 && i != 9) {
						if (i < 5) {
							enjambre[2][i].mover(Global.DIR_E, true);
						} else {
							enjambre[2][i].mover(Global.DIR_W, true);

						}
					}
				}
				for (int i = 0; i < enjambre[3].length; i++) {
					if (i != 0 && i != 1 && i != 8 && i != 9) {
						if (i < 5) {
							enjambre[3][i].mover(Global.DIR_NE, true);
						} else {
							enjambre[3][i].mover(Global.DIR_NW, true);
						}
					}
				}
			} else {
				if (cont >= 90 && cont < 130) {
					for (int i = 0; i < enjambre[2].length; i++) {
						if (i != 0 && i != 9) {
							if (i < 5) {
								enjambre[2][i].mover(Global.DIR_NNE, true);
							} else {
								enjambre[2][i].mover(Global.DIR_NNW, true);
							}
						}
					}
					for (int i = 0; i < enjambre[3].length; i++) {
						if (i != 0 && i != 1 && i != 8 && i != 9) {
							if (i < 5) {
								enjambre[3][i].mover(Global.DIR_NNE, true);
							} else {
								enjambre[3][i].mover(Global.DIR_NNW, true);
							}
						}
					}

				} else {
					if (cont >= 130) {
						for (int i = 0; i < enjambre[2].length; i++) {
							if (i != 0 && i != 9) {
								if (i < 5) {
									enjambre[2][i].zigzag();
								} else {
									enjambre[2][i].vuelta("antihorario");

								}
							}
						}
						for (int i = 0; i < enjambre[3].length; i++) {
							if (i != 0 && i != 1 && i != 8 && i != 9) {
								if (i < 5) {
									enjambre[3][i].vuelta("horario");
								} else {
									enjambre[3][i].zigzag();
								}
							}
						}
					}
				}
				if (cont == 179) {
					for (int ii = 0; ii < enjambre.length; ii++) {
						for (int jj = 0; jj < enjambre[ii].length; jj++) {
							if (!enjambre[ii][jj].getTipo().equals("vacio")) {
								enjambre[ii][jj].setEstado("volviendo");
							}
						}
					}

				}

			}
			break;
		case 2:
			// COMANDANTE
			if (cont < 10) {
				for (int ii = 0; ii < enjambre[0].length; ii++) {

					if (ii == 2 || ii == 3 && enjambre[0][ii].getEstado().equals("entrada")) {
						enjambre[0][ii].setContadorMov(31);

					}

				}
			} else {
				if (cont >= 190 && cont < 370) {
					for (int ii = 0; ii < enjambre[0].length; ii++) {
						if (!enjambre[0][ii].getTipo().equals("vacio")
								&& enjambre[0][ii].getEstado().equals("entrada")) {
							enjambre[0][ii].zigzag();
						}
					}
				} else {
					if (cont >= 370 && cont < 530) {
						for (int ii = 0; ii < enjambre[0].length; ii++) {
							if (!enjambre[0][ii].getTipo().equals("vacio")
									&& enjambre[0][ii].getEstado().equals("entrada")) {
								if (ii == 2 || ii == 3) {
									enjambre[0][ii].vuelta("antihorario");
								} else {
									enjambre[0][ii].vuelta("horario");
								}
							}
						}
					} else {
						if (cont >= 530 && cont < 620) {
							for (int ii = 0; ii < enjambre[0].length; ii++) {
								if (!enjambre[0][ii].getTipo().equals("vacio")
										&& enjambre[0][ii].getEstado().equals("entrada")) {
									if (ii == 2 || ii == 3) {
										enjambre[0][ii].mover(Global.DIR_NNE, true);
									} else {
										enjambre[0][ii].mover(Global.DIR_NNW, true);
									}
								}
							}
						}
					}
				}
			}
			// GOEI
			if (cont < 190) {
				for (int ii = 0; ii < enjambre[1].length; ii++) {
					if (ii <= 4 && enjambre[1][ii].getEstado().equals("entrada")) {
						enjambre[1][ii].mover(Global.DIR_ESE, true);
					} else {
						if (enjambre[1][ii].getEstado().equals("entrada"))
							enjambre[1][ii].mover(Global.DIR_WSW, true);
					}
				}
			} else {
				if (cont >= 190 && cont < 310) {
					for (int ii = 0; ii < enjambre[1].length; ii++) {
						if (enjambre[1][ii].getEstado().equals("entrada"))
							enjambre[1][ii].mover(Global.DIR_S, true);
					}
				} else {
					if (cont >= 310 && cont < 520) {
						for (int ii = 0; ii < enjambre[1].length; ii++) {
							if (ii <= 4 && enjambre[1][ii].getEstado().equals("entrada")) {
								enjambre[1][ii].mover(Global.DIR_W, true);
							} else {
								if (enjambre[1][ii].getEstado().equals("entrada"))
									enjambre[1][ii].mover(Global.DIR_E, true);
							}
						}
					} else {
						if (cont >= 520 && cont < 610) {
							for (int ii = 0; ii < enjambre[1].length; ii++) {
								if (enjambre[1][ii].getEstado().equals("entrada"))
									enjambre[1][ii].mover(Global.DIR_N, true);
							}
						} else {
							if (cont >= 610 && cont < 620) {
								for (int ii = 0; ii < enjambre[1].length; ii++) {
									if (enjambre[1][ii].getEstado().equals("entrada"))
										enjambre[1][ii].setEstado("volviendo");
								}
							}
						}
					}
				}
			}

			// ZAKO
			if (cont < 70) {
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (ii <= 4 && enjambre[2][ii].getEstado().equals("entrada")) {
						enjambre[2][ii].mover(Global.DIR_E, true);
					}
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (ii > 4 && enjambre[3][ii].getEstado().equals("entrada")) {
						enjambre[3][ii].mover(Global.DIR_S, true);
					}
				}
			} else {
				if (cont >= 70 && cont < 140) {
					for (int ii = 0; ii < enjambre[2].length; ii++) {
						if (ii <= 4 && enjambre[2][ii].getEstado().equals("entrada")) {
							enjambre[2][ii].vuelta("antihorario");
						}
					}
					for (int ii = 0; ii < enjambre[3].length; ii++) {
						if (ii > 4 && enjambre[3][ii].getEstado().equals("entrada")) {
							enjambre[3][ii].mover(Global.DIR_S, true);
						}
					}
				} else {
					if (cont >= 140 && cont < 180) {
						for (int ii = 0; ii < enjambre[2].length; ii++) {
							if (ii <= 4 && enjambre[2][ii].getEstado().equals("entrada")) {
								enjambre[2][ii].mover(Global.DIR_N, true);
							}
						}
						for (int ii = 0; ii < enjambre[3].length; ii++) {
							if (ii > 4 && enjambre[3][ii].getEstado().equals("entrada")) {
								enjambre[3][ii].mover(Global.DIR_SW, true);
							}
						}
					} else {
						if (cont >= 180 && cont < 200) {
							for (int ii = 0; ii < enjambre[2].length; ii++) {
								if (ii <= 4 && enjambre[2][ii].getEstado().equals("entrada")) {
									enjambre[2][ii].setEstado("volviendo");
								}
							}
							for (int ii = 0; ii < enjambre[3].length; ii++) {
								if (ii > 4 && enjambre[3][ii].getEstado().equals("entrada")) {
									enjambre[3][ii].setEstado("volviendo");
								}
							}
						}
					}
				}
			}
			if (cont >= 180 && cont < 250) {
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (ii > 4 && enjambre[2][ii].getEstado().equals("entrada")) {
						enjambre[2][ii].mover(Global.DIR_W, true);
					}
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (ii <= 4 && enjambre[3][ii].getEstado().equals("entrada")) {
						enjambre[3][ii].mover(Global.DIR_S, true);
					}
				}
			} else {
				if (cont >= 250 && cont < 340) {
					for (int ii = 0; ii < enjambre[2].length; ii++) {
						if (ii > 4 && enjambre[2][ii].getEstado().equals("entrada")) {
							enjambre[2][ii].vuelta("horario");
						}
					}
					for (int ii = 0; ii < enjambre[3].length; ii++) {
						if (ii <= 4 && enjambre[3][ii].getEstado().equals("entrada")) {
							enjambre[3][ii].mover(Global.DIR_S, true);
						}
					}
				} else {
					if (cont >= 340 && cont < 380) {
						for (int ii = 0; ii < enjambre[2].length; ii++) {
							if (ii > 4 && enjambre[2][ii].getEstado().equals("entrada")) {
								enjambre[2][ii].mover(Global.DIR_N, true);
							}
						}
						for (int ii = 0; ii < enjambre[3].length; ii++) {
							if (ii <= 4 && enjambre[3][ii].getEstado().equals("entrada")) {
								enjambre[3][ii].mover(Global.DIR_NE, true);
							}
						}
					} else {
						if (cont >= 380 && cont < 400) {
							for (int ii = 0; ii < enjambre[2].length; ii++) {
								if (ii > 4 && enjambre[2][ii].getEstado().equals("entrada")) {
									enjambre[2][ii].setEstado("volviendo");
								}
							}
							for (int ii = 0; ii < enjambre[3].length; ii++) {
								if (ii <= 4 && enjambre[3][ii].getEstado().equals("entrada")) {
									enjambre[3][ii].setEstado("volviendo");
								}
							}
						}
					}
				}

			}
			if (cont == 621) {
				for (int ii = 0; ii < enjambre.length; ii++) {
					for (int jj = 0; jj < enjambre[ii].length; jj++) {
						if (!enjambre[ii][jj].getTipo().equals("vacio")) {
							if (enjambre[ii][jj].getEstado().equals("entrada")) {
								enjambre[ii][jj].setEstado("volviendo");
							}
						}
					}
				}
			}
			break;
		case 3:
			// ZAKO
			if (cont < 100) {
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (ii <= 4 && enjambre[2][ii].getEstado().equals("entrada")) {
						enjambre[2][ii].mover(Global.DIR_E, true);
					}
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (ii <= 4 && enjambre[3][ii].getEstado().equals("entrada")) {
						enjambre[3][ii].mover(Global.DIR_E, true);
					}
				}
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (ii > 4 && enjambre[2][ii].getEstado().equals("entrada")) {
						enjambre[2][ii].mover(Global.DIR_W, true);
					}
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (ii > 4 && enjambre[3][ii].getEstado().equals("entrada")) {
						enjambre[3][ii].mover(Global.DIR_W, true);
					}
				}
			} else if (cont >= 100 && cont < 150) {
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (ii <= 4 && enjambre[2][ii].getEstado().equals("entrada")) {
						enjambre[2][ii].mover(Global.DIR_SE, true);
					}
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (ii <= 4 && enjambre[3][ii].getEstado().equals("entrada")) {
						enjambre[3][ii].mover(Global.DIR_SE, true);
					}
				}
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (ii > 4 && enjambre[2][ii].getEstado().equals("entrada")) {
						enjambre[2][ii].mover(Global.DIR_SW, true);
					}
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (ii > 4 && enjambre[3][ii].getEstado().equals("entrada")) {
						enjambre[3][ii].mover(Global.DIR_SW, true);
					}
				}
			} else if (cont >= 150 && cont < 190) {
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (ii <= 4 && enjambre[2][ii].getEstado().equals("entrada")) {
						enjambre[2][ii].vuelta("horario");
					}
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (ii <= 4 && enjambre[3][ii].getEstado().equals("entrada")) {
						enjambre[3][ii].vuelta("horario");
					}
				}
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (ii > 4 && enjambre[2][ii].getEstado().equals("entrada")) {
						enjambre[2][ii].vuelta("antihoraria");
					}
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (ii > 4 && enjambre[3][ii].getEstado().equals("entrada")) {
						enjambre[3][ii].vuelta("antihoraria");
					}
				}
			} else if (cont >= 190 && cont < 230) {
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (ii <= 4 && enjambre[2][ii].getEstado().equals("entrada")) {
						enjambre[2][ii].mover(Global.DIR_N, true);
					}
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (ii <= 4 && enjambre[3][ii].getEstado().equals("entrada")) {
						enjambre[3][ii].mover(Global.DIR_N, true);
					}
				}
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (ii > 4 && enjambre[2][ii].getEstado().equals("entrada")) {
						enjambre[2][ii].mover(Global.DIR_N, true);
					}
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (ii > 4 && enjambre[3][ii].getEstado().equals("entrada")) {
						enjambre[3][ii].mover(Global.DIR_N, true);
					}
				}
			} else if (cont >= 230 && cont < 285) {
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (ii <= 4 && enjambre[2][ii].getEstado().equals("entrada")) {
						enjambre[2][ii].vuelta("horario");
					}
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (ii <= 4 && enjambre[3][ii].getEstado().equals("entrada")) {
						enjambre[3][ii].vuelta("antihorario");
					}
				}
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (ii > 4 && enjambre[2][ii].getEstado().equals("entrada")) {
						enjambre[2][ii].vuelta("horario");
					}
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (ii > 4 && enjambre[3][ii].getEstado().equals("entrada")) {
						enjambre[3][ii].vuelta("antihorario");
					}
				}
			} else if (cont >= 285 && cont < 330) {
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (ii <= 4 && enjambre[2][ii].getEstado().equals("entrada")) {
						enjambre[2][ii].mover(Global.DIR_W, true);
					}
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (ii <= 4 && enjambre[3][ii].getEstado().equals("entrada")) {
						enjambre[3][ii].mover(Global.DIR_E, true);
					}
				}
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (ii > 4 && enjambre[2][ii].getEstado().equals("entrada")) {
						enjambre[2][ii].mover(Global.DIR_W, true);
					}
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (ii > 4 && enjambre[3][ii].getEstado().equals("entrada")) {
						enjambre[3][ii].mover(Global.DIR_E, true);
					}
				}
			} else if (cont >= 330 && cont < 390) {
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (ii <= 4 && enjambre[2][ii].getEstado().equals("entrada")) {
						enjambre[2][ii].mover(Global.DIR_ENE, true);
					}
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (ii <= 4 && enjambre[3][ii].getEstado().equals("entrada")) {
						enjambre[3][ii].mover(Global.DIR_WNW, true);
					}
				}
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (ii > 4 && enjambre[2][ii].getEstado().equals("entrada")) {
						enjambre[2][ii].mover(Global.DIR_ENE, true);
					}
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (ii > 4 && enjambre[3][ii].getEstado().equals("entrada")) {
						enjambre[3][ii].mover(Global.DIR_WNW, true);
					}
				}
			} else if (cont >= 390 && cont < 540) {
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (ii <= 4 && enjambre[2][ii].getEstado().equals("entrada")) {
						enjambre[2][ii].mover(Global.DIR_WNW, true);
					}
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (ii <= 4 && enjambre[3][ii].getEstado().equals("entrada")) {
						enjambre[3][ii].mover(Global.DIR_ENE, true);
					}
				}
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (ii > 4 && enjambre[2][ii].getEstado().equals("entrada")) {
						enjambre[2][ii].mover(Global.DIR_WNW, true);
					}
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (ii > 4 && enjambre[3][ii].getEstado().equals("entrada")) {
						enjambre[3][ii].mover(Global.DIR_ENE, true);
					}
				}
			} else if (cont >= 810 && cont < 1000) {
				for (int ii = 0; ii < enjambre[2].length; ii++) {
					if (enjambre[2][ii].getEstado().equals("entrada"))
						enjambre[2][ii].setEstado("volviendo");
				}
				for (int ii = 0; ii < enjambre[3].length; ii++) {
					if (enjambre[3][ii].getEstado().equals("entrada"))
						enjambre[3][ii].setEstado("volviendo");
				}
			}

			// GOEI
			if (cont >= 540 && cont < 700) {
				for (int ii = 0; ii < enjambre[1].length; ii++) {
					enjambre[1][ii].zigzag();
				}
			} else if (cont >= 700 && cont < 800) {
				for (int ii = 0; ii < enjambre[1].length; ii++) {
					enjambre[1][ii].vuelta("horario");
				}
			} else if (cont >= 800 && cont < 860) {
				for (int ii = 0; ii < enjambre[1].length; ii++) {
					if (ii < 5) {
						enjambre[1][ii].mover(Global.DIR_NE, true);
					} else {
						enjambre[1][ii].mover(Global.DIR_NW, true);
					}
				}
			} else if (cont >= 860 && cont < 960) {
				for (int ii = 0; ii < enjambre[1].length; ii++) {
					if (ii < 5) {
						enjambre[1][ii].mover(Global.DIR_E, true);
					} else {
						enjambre[1][ii].mover(Global.DIR_W, true);
					}
				}
			} else if (cont >= 1010 && cont < 1020) {
				for (int ii = 0; ii < enjambre[1].length; ii++) {
					if (enjambre[1][ii].getEstado().equals("entrada")) {
						enjambre[1][ii].setEstado("volviendo");
					}
				}
			}
			// COMANDANTE GALAGA
			if (cont >= 550 && cont < 650) {
				for (int ii = 0; ii < enjambre[0].length; ii++) {
					if (ii != 1 && ii != 4 && ii < 5) {
						enjambre[0][ii].mover(Global.DIR_SE, true);
					} else {
						if (ii == 6 || ii == 7 || ii == 9)
							enjambre[0][ii].mover(Global.DIR_SW, true);
					}
				}
			} else if (cont >= 650 && cont < 750) {
				for (int ii = 0; ii < enjambre[0].length; ii++) {
					if (ii != 1 && ii != 4 && ii < 5) {
						enjambre[0][ii].vuelta("horario");
					} else {
						if (ii == 6 || ii == 7 || ii == 9)
							enjambre[0][ii].vuelta("antihorario");
					}
				}
			} else if (cont >= 750 && cont < 870) {
				for (int ii = 0; ii < enjambre[0].length; ii++) {
					if (ii != 1 && ii != 4 && ii < 5) {
						enjambre[0][ii].mover(Global.DIR_NNE, true);
					} else {
						if (ii == 6 || ii == 7 || ii == 9)
							enjambre[0][ii].mover(Global.DIR_NNW, true);
					}
				}
			}
			if (cont >= 1100 && cont < 1200) {
				for (int ii = 0; ii < enjambre.length; ii++) {
					for (int jj = 0; jj < enjambre[ii].length; jj++) {
						if (!enjambre[ii][jj].getTipo().equals("vacio")) {
							if (enjambre[ii][jj].getEstado().equals("entrada")) {
								enjambre[ii][jj].setEstado("volviendo");
							}
						}
					}
				}
			}
			break;
		}
		imprimirEnjambre();

	}

	// referentes a los TORPEDOS
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
				 * existe
				 */
				if (!enjambre[ii][jj].getTipo().equals("vacio")) {
					/*
					 * Si el enemigo existe, comprobar que no esta muerto
					 */

					if (!enjambre[ii][jj].getEstado().equals("muerto")
							&& (!enjambre[ii][jj].getEstado().equals("explotando"))) {

						/*
						 * Ahora recorremos los torpedos del jugador y si estan disparados, comprobamos
						 * si han colisionado
						 */
						for (int kk = 0; kk < usuario.getDisparos().length; kk++) {
							/*
							 * Antes de ver si coinciden las posiciones, debemos asegurarnos que el torpedo
							 * ha sido disparado.
							 */
							if (usuario.getDisparos()[kk].getIdentificador() > 0) {
								//Comprobar colision
								if ((((enjambre[ii][jj].getCoordenadas().getX()
										+ Global.min) >= (usuario.getDisparos()[kk].getCoordenadas().getX()))
										&& ((enjambre[ii][jj].getCoordenadas().getX()
												- Global.min) <= (usuario.getDisparos()[kk].getCoordenadas().getX())))
										&& (((enjambre[ii][jj].getCoordenadas().getY()
												+ Global.min) >= (usuario.getDisparos()[kk].getCoordenadas().getY())))
										&& ((enjambre[ii][jj].getCoordenadas().getY()
												- Global.min) <= (usuario.getDisparos()[kk].getCoordenadas().getY()))) {
									/*
									 * Como el comandante galaga tiene dos vidas, debemos ver dos cosas. Si es el
									 * comandante galaga y si todavia no le habiamos dado.
									 */
									if (enjambre[ii][jj].getTipo().equals("comandante galaga")
											&& enjambre[ii][jj].getVida() == 2) {
										if(enjambre[ii][jj].getImagen().equals("enemy9G0.png")){    
										enjambre[ii][jj].setImagen("enemy9G1.png");
										}else {
											enjambre[ii][jj].setImagen("enemy9G0.png");
										}
											enjambre[ii][jj].setVida(1);
											usuario.getDisparos()[kk].borrarTorpedo();
										}else {
										// Borrar el enemigo.
										enjambre[ii][jj].destruido();
										// Borrar el torpedo, sumar los puntos y mostrarlos por pantalla
										usuario.getDisparos()[kk].borrarTorpedo();
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

	}

	/**
	 * Con este metodo movemos todos los torpedos disparados por el enjambre y vemos
	 * si alguno ha dado al jugador
	 */
	public void moverTorpedos(Jugador jugador, boolean invencible) {
		boolean acertado = false;

		for (int ii = 0; ii < enjambre.length; ii++) {
			for (int jj = 0; jj < enjambre[ii].length; jj++) {
				/*
				 * Si el torpedo esta disparado, lo movemos, aunque el que lo disparo este
				 * muerto
				 */
				if (!enjambre[ii][jj].getTipo().equals("vacio")
						&& (enjambre[ii][jj].getDisparo().getIdentificador() > 0)) {
					enjambre[ii][jj].getDisparo().moverTorpedo();

					/*
					 * si esta activado el modo invencible obviamos las colisiones con el jugador
					 */
					if (!invencible) {
						// Si al moverse le ha dado al jugador, le quita 1 vida
						if (enjambre[ii][jj].getDisparo().getIdentificador() > 0)
							acertado = jugador.tocado(enjambre[ii][jj]);
						// Si le ha acertado, el torpedo enemigo desaparece
						if (acertado) {
							enjambre[ii][jj].getDisparo().borrarTorpedo();
						}
					}
				}
			}
		}
	}

	/**
	 *  Comprobrar si los enemigos que han salido a atacar
	 *  han colisionado contra el jugador
	 * @param usuario Jugador contra el que podrían haber impactado
	 */
	public void colisionJugador(Jugador usuario) {
		for (int ii = 0; ii < enjambre.length; ii++) {
			for (int jj = 0; jj < enjambre[ii].length; jj++) {
				
					if (!enjambre[ii][jj].getTipo().equals("vacio") && !enjambre[ii][jj].getEstado().equals("muerto")
							&& !enjambre[ii][jj].getEstado().equals("explotando")) {
						usuario.colision(enjambre[ii][jj]);
					
				}
			}
		}
	}

	/**
	 * Borra de la pantalla los torpedos enemigos disparados
	 */
	public void borrarDisparos() {
		for (int ii = 0; ii < enjambre.length; ii++) {
			for (int jj = 0; jj < enjambre[ii].length; jj++) {
				if ((!enjambre[ii][jj].getTipo().equals("vacio"))
						&& (enjambre[ii][jj].getDisparo().getIdentificador() > 0)) {
					enjambre[ii][jj].getDisparo().borrarTorpedo();
				}
			}
		}
	}
}