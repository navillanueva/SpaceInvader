//Propiedades del jugador, como se mueve y dispara.UC3M. Monica Ocania Bastante & Nicolas Arnedo Villanueva
package edu.uc3m.galaga;

/**
 * Esta clase controla las propiedades del jugador y lo que puede hacer
 * @author Monica Ocania Bastante & Nicolas Arnedo Villanueva
 * @since 2018/12/19
 */

import edu.uc3m.game.GameBoardGUI;

public class Jugador {
	/**
	 * Su identificador como sprite
	 */
	private int identificador = 40;
	/**
	 * Su posicion en el tablero
	 */
	private Posicion coordenadas = new Posicion(80, 205);
	/**
	 * Los puntos que ha conseguido
	 */
	private int puntos = 0;
	/**
	 * La vida maxima que puede tener
	 */
	private int vidaTotal = 3;
	/**
	 * De la vida maxima, cuanta tiene en toTal
	 */
	private int vidaActual = 3;
	/**
	 * La cantidad de veces que ha disparado
	 */
	private int disparosRealizados = 0;
	/**
	 * De los disparos realizados, los que han impactado en un enemigo
	 */
	private int aciertos = 0;
	/**
	 * La cantidad de torpedos disponibles para ser disparados
	 */
	private Torpedo[] disparos = new Torpedo[200];
	/**
	 * Velocidad a la que se mueve
	 */
	private int velocidad = 3;
	/**
	 * Interfaz en la que esta
	 */
	private GameBoardGUI gui;
	/**
	 * Torpedo del ataque especial. Tiene solo uno y puede dispararlo dos veces en
	 * cada nivel.
	 */
	private Torpedo especial = new Torpedo(true, coordenadas, (-90), true, gui);

	// CONSTRUCTOR
	public Jugador(GameBoardGUI gui) {
		this.gui = gui;
		// Crear los cohetes
		for (int ii = 0; ii < disparos.length; ii++) {
			disparos[ii] = new Torpedo(true, coordenadas, (-900 - ii), false, gui);
		}

		//Poner el sprite en la interfaz
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

		// Imprimir vidas del jugador
		gui.gb_addSprite(501, "player.png", true);
		gui.gb_setSpriteVisible(501, true);
		gui.gb_moveSpriteCoord(501, 7, 215);
		gui.gb_setSpriteImage(501, "player.png", 20, 20);

		gui.gb_addSprite(502, "player.png", true);
		gui.gb_setSpriteVisible(502, true);
		gui.gb_moveSpriteCoord(502, 17, 215);
		gui.gb_setSpriteImage(502, "player.png", 20, 20);

		gui.gb_addSprite(503, "player.png", true);
		gui.gb_setSpriteVisible(503, true);
		gui.gb_setSpriteImage(503, "player.png", 20, 20);
		gui.gb_moveSpriteCoord(503, 26, 214);
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

	public int getVelocidad() {
		return velocidad;
	}

	public Torpedo getEspecial() {
		return especial;
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

	public void setVelocidad(int velocidad) {
		if (velocidad <= 20 && velocidad > 0)
			this.velocidad = velocidad;
	}

	// METODOS ESPECIFICOS
	/**
	 * El jugador se mueve en la direccion que indica el comando, que pude ser
	 * "left" o "right" (izquierda o derecha). Se mueve de 3 en 3.
	 * 
	 * @param comando
	 */
	public void moverJugador(String comando) {
		if (coordenadas.getX() < Global.xMax && coordenadas.getX() > Global.min) {
			switch (comando) {
			case "left":
				coordenadas.setX((coordenadas.getX() - velocidad));
				break;
			case "right":
				coordenadas.setX((coordenadas.getX() + velocidad));
				break;
			}
			/*
			 * Para evitar que salga del tablero, al llegar al borde solo puede ir en
			 * direccion contraria a la que ha venido
			 */
		} else {
			if (coordenadas.getX() >= Global.xMax) {
				switch (comando) {
				case "left":
					coordenadas.setX((coordenadas.getX() - velocidad));
					break;
				}
			} else {
				if (coordenadas.getX() <= Global.min) {
					switch (comando) {
					case "right":
						coordenadas.setX((coordenadas.getX() + velocidad));
						break;
					}
				}
			}
		}
		// Imprimir el sprite en el nuevo sitio
		gui.gb_moveSpriteCoord(identificador, coordenadas.getX(), coordenadas.getY());
	}

	/**
	 * Reinicia los datos del jugador
	 */
	public void reiniciarDatos() {
		setVelocidad(3);
		setVidaActual(3);
		setPuntos(0);
		coordenadas.setX(80);
		coordenadas.setY(205);
		setDisparosRealizados(0);
		setAciertos(0);
		especial.setVecesUsadoEspecial(0);
		gui.gb_setSpriteVisible(501, true);
		gui.gb_setSpriteVisible(502, true);
		gui.gb_setSpriteVisible(503, true);
		gui.gb_setSpriteImage(identificador, "player.png");
		borrarTorpedos();
	}
	
	/**
	 * Actualiza en la pantalla sus datos
	 * @param gui
	 */
	public void actualizarDatosInterfaz(GameBoardGUI gui) {
		gui.gb_setValueHealthCurrent(vidaActual);
		gui.gb_setValuePointsDown(puntos);
		gui.gb_setValueAbility1(disparosRealizados);
		gui.gb_setValueAbility2(aciertos);
		gui.gb_setValuePointsUp(velocidad);
	}

	// Vidas del jugador

	/**
	 * Saber si nos ha dado un torpedo enemigo, en cuyo caso perdemos una vida.
	 * 
	 * @param enemigo El enemigo cuyo torpedo queremos comprobar
	 * @return True si ha sido alcanzado, false si no le han dado
	 */
	public boolean tocado(Enemigo enemigo) {
		if ((((coordenadas.getX() + Global.min) >= (enemigo.getDisparo().getCoordenadas().getX()))
				&& ((coordenadas.getX() - Global.min) <= (enemigo.getDisparo().getCoordenadas().getX())))
				&& (((coordenadas.getY() + Global.min) >= (enemigo.getDisparo().getCoordenadas().getY())))
				&& ((coordenadas.getY() - Global.min) <= (enemigo.getDisparo().getCoordenadas().getY()))) {

			gui.gb_println("Te han dado. Una vida menos.");
			vidaActual = vidaActual - 1;
			if (vidaActual == 0) {
				gui.gb_println("Has muerto.");
			}
			return true;
		}
		return false;
	}

	/**
	 * Comprobamos si un enemigo concreto a impactado contra el jugador. En caso de
	 * impacto, se ponen las vidas a cero.
	 * 
	 * @param enemigo El enemigo a comprobar
	 */
	public void colision(Enemigo enemigo) {
		if ((((coordenadas.getX() + Global.min) >= (enemigo.getCoordenadas().getX()))
				&& ((coordenadas.getX() - Global.min) <= (enemigo.getCoordenadas().getX())))
				&& (((coordenadas.getY() + Global.min) >= (enemigo.getCoordenadas().getY())))
				&& ((coordenadas.getY() - Global.min) <= (enemigo.getCoordenadas().getY()))) {
			vidaActual = 0;
			gui.gb_println("El enemigo " + enemigo.getTipo() + " ha impactado contra ti.");
			gui.gb_println("Has muerto.");
		}
	}

	/**
	 * Cuando el jugador muere, explota. Aqui esta la animacion.
	 * Como no puede hacer ninguna accion al estar muerto, dejamos que
	 * el ordenador pare un momento para apreciar bien la explosion
	 * 
	 * @param explosion En que fase de la explosion se encuentra
	 * @throws InterruptedException
	 */
	public void explotar(int explosion) throws InterruptedException {
		switch (explosion) {
		case 0:
			gui.gb_setSpriteImage(identificador, "explosion11.png");
			break;

		case 1:
			gui.gb_setSpriteImage(identificador, "explosion12.png");
			break;
		case 2:
			gui.gb_setSpriteImage(identificador, "explosion13.png");
			break;
		case 3:
			gui.gb_setSpriteImage(identificador, "explosion14.png");
			break;
		}
		Thread.sleep(120L);
	}

	/**
	 * Controla las tres naves que simulan sus vidas en la pantalla
	 */
	public void vidasEnPantalla() {
		if (vidaActual != 3) {
			gui.gb_setSpriteVisible(503, false);
		}
		if (vidaActual < 2) {
			gui.gb_setSpriteVisible(502, false);
		}
		if(vidaActual<1) {
			gui.gb_setSpriteVisible(501, false);
		}
	}

	// Control de los torpedos
	/**
	 * Con este metodo disparamos los torpedos, haciendo que aparezcan encima del
	 * jugador, compartiendo su posicion x.
	 * 
	 * @param identificador Identificador del torpedo que vamos a disparar
	 */
	public void disparar() {//int identificador
		// Buscar sitio donde guardar el torpedo
		boolean buscando = true;
		int contador = 0;
		do {
			/*
			 * Si el identificador es <0, consideramos que el misil esta listo para ser
			 * disparado
			 */
			if (disparos[contador].getIdentificador() <= 0) {
				buscando = false;
				disparos[contador].lanzar(coordenadas);
			} else {
				contador++;
			}
			/*
			 * El bucle debe funcionar hasta que encuentre un hueco libre o haya comprobado
			 * que todos estan disparados
			 */
		} while (buscando && contador < disparos.length);

	}

	public void dispararEspecial() {

		if (especial.getVecesUsadoEspecial() < 2) {
			especial.setVecesUsadoEspecial(especial.getVecesUsadoEspecial() + 1);
			especial.setIdentificador(especial.getIdentificador() * -1);
			especial.setCoordenadas(coordenadas.getX(), coordenadas.getY() - 20);
			gui.gb_addSprite(especial.getIdentificador(), especial.getImagen(), true);
			gui.gb_setSpriteVisible(especial.getIdentificador(), true);
			gui.gb_setSpriteImage(especial.getIdentificador(), especial.getImagen(), 40, 100);
			gui.gb_moveSpriteCoord(especial.getIdentificador(), especial.getCoordenadas().getX(),
					especial.getCoordenadas().getY());
		} else {
			gui.gb_println("Ya has gastado todos tus cohetes \n especiales de este nivel");
		}
	}

	/**
	 * Movemos los torpedos que hayan sido disparados, es decir; los que tienen
	 * identificador>0.
	 */
	public void moverTorpedos() {
		for (int ii = 0; ii < disparos.length; ii++) {
			if (disparos[ii].getIdentificador() > 0) {
				disparos[ii].moverTorpedo();
			}
		}
		if (!especial.isExplotando()) {
			if (especial.getIdentificador() > 0) {
				especial.setCoordenadas(especial.getCoordenadas().getY() - 1);
				gui.gb_moveSpriteCoord(especial.getIdentificador(), especial.getCoordenadas().getX(),
						especial.getCoordenadas().getY());
				if (especial.getCoordenadas().getY() == -15) {
					especial.setIdentificador(especial.getIdentificador() * -1);
				}
			}
		} else {
			explotarEspecial();
		}
	}

	/**
	 * Borra de la pantalla los torpedos y los pone como no disparados
	 */
	public void borrarTorpedos() {
		for (int ii = 0; ii < disparos.length; ii++) {
			if (disparos[ii].getIdentificador() > 0) {
				disparos[ii].borrarTorpedo();
			}
		}
		if (especial.getIdentificador() > 0) {
			gui.gb_setSpriteVisible(especial.getIdentificador(), false);
			especial.setIdentificador(especial.getIdentificador() * -1);
		}
	}

	/**
	 * El cohete especial es mas grande y ademas, explota, pro lo que se necesita un
	 * metodo aparte para controlar su colision. Cuando impacte contra un enemigo
	 * explotara y puede matar enemigos tanto tocandolos el mismo o su explosion
	 * postuma.
	 */
	public void colisionEspecial(Nivel nivel) {
		// Altura del cohete
		int altura = 15;
		int anchura = 5;
		/*
		 * Durante la explosion va cambiando de tamanio, por lo que cada vez tiene un
		 * ancho y alto distino
		 */
		if (especial.isExplotando()) {
			if (especial.getFaseExplosion() < 5) {
				altura = 5;
				anchura = 5;
			} else {
				if (especial.getFaseExplosion() >= 5 && especial.getFaseExplosion() < 10) {
					altura = 5;
					anchura = 5;
				} else {
					if (especial.getFaseExplosion() >= 10 && especial.getFaseExplosion() < 15) {
						altura = 10;
						anchura = 10;
					} else {
						if (especial.getFaseExplosion() >= 15 && especial.getFaseExplosion() < 20) {
							altura = 10;
							anchura = 10;
						}
					}
				}

			}
		}

		// COMPROBACION DE COLISIONES
		/*
		 * Tanto si esta explotando como visible, puede eliminar enemigos
		 */
		for (int ii = 0; ii < nivel.getEnjambre().length; ii++) {
			for (int jj = 0; jj < nivel.getEnjambre()[ii].length; jj++) {
				// Asegurarnos esta disparado
				if (especial.getIdentificador() > 0) {
					/*
					 * Asegurarnos que el enemigo existe y no esta muerto
					 */
					if (!nivel.getEnjambre()[ii][jj].getTipo().equals("vacio")
							&& !nivel.getEnjambre()[ii][jj].getEstado().equals("muerto")
							&& !nivel.getEnjambre()[ii][jj].getEstado().equals("explotando")) {

						if ((((nivel.getEnjambre()[ii][jj].getCoordenadas().getX() + anchura) >= (especial
								.getCoordenadas().getX()))
								&& ((nivel.getEnjambre()[ii][jj].getCoordenadas().getX() - anchura) <= (especial
										.getCoordenadas().getX())))
								&& (((nivel.getEnjambre()[ii][jj].getCoordenadas().getY() + altura) >= (especial
										.getCoordenadas().getY())))
								&& ((nivel.getEnjambre()[ii][jj].getCoordenadas().getY() - altura) <= (especial
										.getCoordenadas().getY()))) {
							/*
							 * Si es el comandante galaga con 2 vidas
							 */
							if (nivel.getEnjambre()[ii][jj].getTipo().equals("comandante galaga")
									&& nivel.getEnjambre()[ii][jj].getVida() == 2) {
								nivel.getEnjambre()[ii][jj].setImagen("enemy9G0.png");
								nivel.getEnjambre()[ii][jj].setVida(1);
							} else {
								// Borrar el enemigo.
								nivel.getEnjambre()[ii][jj].destruido();
								// Sumar los puntos y aciertos
								setAciertos(aciertos + 1);
								setPuntos(puntos + nivel.getEnjambre()[ii][jj].getPuntos());
							}
							/*
							 * Si no estaba explotando, que lo haga. Si ya lo estaba, que siga haciendolo
							 */
							if (!especial.isExplotando()) {
								especial.setExplotando(true);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Controlar la explosion del torpedo especial. Explota cada cinco frames. No
	 * tiene varias imagenes, tiene solo una cuyo tamanio vamos aumentando.
	 */
	public void explotarEspecial() {
		switch (especial.getFaseExplosion()) {
		/*
		 * En cada caso, la imagen de la explosion crece
		 */
		case 0:
			
			gui.gb_setSpriteImage(especial.getIdentificador(), "explosion30.png", 20, 20);
			gui.gb_moveSpriteCoord(especial.getIdentificador(), especial.getCoordenadas().getX(),
					especial.getCoordenadas().getY());
			especial.setFaseExplosion(especial.getFaseExplosion() + 1);
			break;
		case 5:
			gui.gb_setSpriteImage(especial.getIdentificador(), "explosion30.png", 40, 40);

			gui.gb_moveSpriteCoord(especial.getIdentificador(), especial.getCoordenadas().getX(),
					especial.getCoordenadas().getY());
			especial.setFaseExplosion(especial.getFaseExplosion() + 1);
			break;
		case 10:
			gui.gb_setSpriteImage(especial.getIdentificador(), "explosion30.png", 60, 60);

			gui.gb_moveSpriteCoord(especial.getIdentificador(), especial.getCoordenadas().getX(),
					especial.getCoordenadas().getY());
			especial.setFaseExplosion(especial.getFaseExplosion() + 1);
			break;
		case 15:
			gui.gb_setSpriteImage(especial.getIdentificador(), "explosion30.png", 80, 80);

			gui.gb_moveSpriteCoord(especial.getIdentificador(), especial.getCoordenadas().getX(),
					especial.getCoordenadas().getY());
			especial.setFaseExplosion(especial.getFaseExplosion() + 1);
			break;
		case 20:
			gui.gb_setSpriteVisible(especial.getIdentificador(), false);
			especial.setIdentificador(especial.getIdentificador() * -1);
			especial.setExplotando(false);
			especial.setFaseExplosion(0);
			break;
		default:
			especial.setFaseExplosion(especial.getFaseExplosion() + 1);
		}
	}

	// METODOS ESPECIFICOS EN PRUEBA
}
