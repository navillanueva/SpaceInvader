//Los enemigos del juego y sus acciones. UC3M. Monica Ocania Bastante & Nicolas Arnedo Villanueva
package edu.uc3m.galaga;

/**
 * En esta clase se guardan los datos de cada enemigo
 * y sus metodos particulares.
 * @author Monica Ocania Bastante & Nicolas Arnedo Villanueva
 * @since 2018/12/19
 */

import edu.uc3m.game.GameBoardGUI;

public class Enemigo {
	/**
	 * La cantidad de veces que hace falta que el jugador le de para que desaparezca
	 */
	private int vida;
	/**
	 * Los puntos que da al morir
	 */
	private int puntos;
	/**
	 * Su posicion en el tablero
	 */
	private Posicion coordenadas = new Posicion(-15, -15);
	/**
	 * Su identificador de sprite
	 */
	private int identificador;
	/**
	 * El tipo de enemigo que es. Puede ser: zako, goei o comandante galaga. Si pone
	 * "vacio", el sistema entiende que ese hueco no lo ocupa un enemigo "vivo"
	 */
	private String tipo = "vacio";// Zako, goei, comandante galaga
	/**
	 * El dibujo que tiene
	 */
	private String imagen = "empty.png";
	/**
	 * En que direccion se esta moviendo
	 */
	private int direccion;
	/**
	 * Cuando hace un movimiento secuencial, que parte esta realizando
	 */
	private int contadorMov = 0;
	/**
	 * Enjambre: esta dentro del enjambre Muerto: el jugador lo ha eliminado
	 * Explotando:ha muerto y esta explotando Atacando: ha salido del enjambre
	 * Volviendo: esta retomando su posicion en el enjambre
	 */
	private String estado = "muerto";// enjambre, muerto, explotando, atacando, volviendo
	private int[] aliados = { 0, 0 };
	/**
	 * Posicion que tendria si estuviera dentro del enjambre
	 */
	private Posicion hueco = new Posicion(-15, -15);;
	/**
	 * La interfaz en la que aparece
	 */
	private GameBoardGUI gui;
	/**
	 * El torpedo que puede disparar
	 */
	private Torpedo disparo = new Torpedo(false, coordenadas, -1, false, gui);

	// CONSTRUCTORES
	// Constructor vacio para poder poner el tipo "vacio"
	public Enemigo() {
		setEstado("muerto");
	}

	public Enemigo(String tipo, int identificador, int x, int y, GameBoardGUI gui, int idDisparo, int hx, int hy) {
		this.gui = gui;
		// Rellenar datos especificos de cada tipo
		tipo.toLowerCase();
		switch (tipo) {
		case "zako":
			vida = 1;
			puntos = 100;
			this.tipo = tipo;
			imagen = "enemy3G0.png";
			break;
		case "goei":
			vida = 1;
			puntos = 250;
			imagen = "enemy2G0.png";
			this.tipo = tipo;
			break;
		case "comandante galaga":
			vida = 2;
			puntos = 500;
			this.tipo = tipo;
			imagen = "enemy1G0.png";
			break;
		default:
			vida = 1;
			puntos = 100;
			this.tipo = "zako";
			imagen = "enemy3G0.png";
			break;
		}
		coordenadas = new Posicion(x, y);
		setEstado("entrada");
		setIdentificador(identificador);
		disparo = new Torpedo(false, coordenadas, idDisparo, false, gui);
		this.hueco = new Posicion(hx, hy);
	}

	// GETTERS
	public int getVida() {
		return vida;
	}

	public int getPuntos() {
		return puntos;
	}

	public Posicion getCoordenadas() {
		return coordenadas;
	}

	public int getIdentificador() {
		return identificador;
	}

	public String getTipo() {
		return tipo;
	}

	public String getImagen() {
		return imagen;
	}

	public int getDireccion() {
		return direccion;
	}

	public Torpedo getDisparo() {
		return disparo;
	}

	public String getEstado() {
		return estado;
	}

	public Posicion getHueco() {
		return hueco;
	}

	public int[] getAliados() {
		return aliados;
	}

	// SETTERS
	public void setVida(int vida) {
		this.vida = vida;
	}

	public void setPuntos(int puntosVencer) {
		this.puntos = puntosVencer;
	}

	public void setCoordenadas(Posicion coordenadas) {
		this.coordenadas = coordenadas;
	}

	public void setIdentificador(int nombre) {
		if (nombre <= 1000 && nombre >= 100) {
			identificador = nombre;
		} else {// por si me equivoco para no repetir
			identificador = (int) (Math.random() * 2000 + 200);
		}
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setHueco(Posicion hueco) {
		this.hueco = hueco;
	}

	public void setContadorMov(int contadorMov) {
		this.contadorMov = contadorMov;
	}

	public void setAliados(int[] aliados) {
		this.aliados = aliados;
	}

	// METODOS ESPECIALES
	/**
	 * Cambia la imagen norte del enemigo
	 * 
	 * @param tipo Dependiendo del tipo tiene una imagen
	 */
	public void alternarImagen() {
		tipo.toLowerCase();
		// Cada tipo con su imagen especifica
		switch (tipo) {
		case "zako":
			if (imagen.equals("enemy3G0.png")) {
				imagen = "enemy3G1.png";
			} else {
				imagen = "enemy3G0.png";
			}
			break;
		case "goei":
			if (imagen.equals("enemy2G0.png")) {
				imagen = "enemy2G1.png";
			} else {
				imagen = "enemy2G0.png";
			}
			break;
		/*
		 * El comandante galaga tiene dos versiones. Con una vida y con dos, por lo que
		 * debemos poner una condicion para cada caso
		 */
		case "comandante galaga":
			if (imagen.equals("enemy1G0.png")) {
				imagen = "enemy1G1.png";
			} else {
				if (vida == 2)
					imagen = "enemy1G0.png";
			}

			if (imagen.equals("enemy9G0.png")) {
				imagen = "enemy9G1.png";
			} else {
				if (imagen.equals("enemy9G1.png")) {
					imagen = "enemy9G0.png";
				}
				break;
			}
			// Cambiamos la imagen del sprite a la nueva
			gui.gb_setSpriteImage(identificador, imagen);
		}
	}

	/**
	 * Crear el sprite en la consola
	 */
	public void crearSprite() {
		gui.gb_addSprite(identificador, imagen, true);
		gui.gb_setSpriteVisible(identificador, true);
		gui.gb_moveSpriteCoord(identificador, coordenadas.getX(), coordenadas.getY());
	}

	/**
	 * Borra al enemigo de la pantalla y en su lugar pone una explosion
	 */
	public void destruido() {
		setEstado("explotando");
		gui.gb_println("El enemigo " + tipo + " ha sido eliminido.");
		gui.gb_println("Has ganado " + puntos);
		setImagen("explosion20.png");
		gui.gb_setSpriteImage(identificador, imagen);
		contadorMov = 0;
	}

	// Metodos referentes al MOVIMIENTO
	/**
	 * Mueve al enemigo en la direccion indicada 1 o 2 casillas
	 * 
	 * @param direccion En que direccion se va a mover
	 * @param dos       Si se mueve de dos en dos o de uno en uno(de uno en uno solo
	 *                  puede en las direcciones pares)
	 */
	public void mover(int direccion, boolean dos) {
		switch (direccion) {
		/*
		 * Cambiamos sus coordenadas y luego su imagen
		 * dependiendo del tipo
		 */
		case 0:
			if (dos) {
				coordenadas.setY(coordenadas.getY() - 2);
			} else {
				coordenadas.setY(coordenadas.getY() - 1);
			}
			switch (tipo) {
			case "comandante galaga":
				if (vida == 2) {
					gui.gb_setSpriteImage(identificador, "enemy100.png");
				} else {
					gui.gb_setSpriteImage(identificador, "enemy900.png");
				}
				break;
			case "goei":
				gui.gb_setSpriteImage(identificador, "enemy200.png");
				break;
			case "zako":
				gui.gb_setSpriteImage(identificador, "enemy300.png");
				break;
			}
			break;
		case 1:
			coordenadas.setX(coordenadas.getX() + 1);
			coordenadas.setY(coordenadas.getY() - 2);
			switch (tipo) {
			case "comandante galaga":
				if (vida == 2) {
					gui.gb_setSpriteImage(identificador, "enemy101.png");
				} else {
					gui.gb_setSpriteImage(identificador, "enemy901.png");
				}
				break;
			case "goei":
				gui.gb_setSpriteImage(identificador, "enemy201.png");
				break;
			case "zako":
				gui.gb_setSpriteImage(identificador, "enemy301.png");
				break;
			}
			break;
		case 2:
			if (dos) {
				coordenadas.setX(coordenadas.getX() + 2);
				coordenadas.setY(coordenadas.getY() - 2);
			} else {
				coordenadas.setX(coordenadas.getX() + 1);
				coordenadas.setY(coordenadas.getY() - 1);
			}
			switch (tipo) {
			case "comandante galaga":
				if (vida == 2) {
					gui.gb_setSpriteImage(identificador, "enemy102.png");
				} else {
					gui.gb_setSpriteImage(identificador, "enemy902.png");
				}
				break;
			case "goei":
				gui.gb_setSpriteImage(identificador, "enemy202.png");
				break;
			case "zako":
				gui.gb_setSpriteImage(identificador, "enemy302.png");
				break;
			}
			break;
		case 3:
			coordenadas.setX(coordenadas.getX() + 2);
			coordenadas.setY(coordenadas.getY() - 1);
			switch (tipo) {
			case "comandante galaga":
				if (vida == 2) {
					gui.gb_setSpriteImage(identificador, "enemy103.png");
				} else {
					gui.gb_setSpriteImage(identificador, "enemy903.png");
				}
				break;
			case "goei":
				gui.gb_setSpriteImage(identificador, "enemy203.png");
				break;
			case "zako":
				gui.gb_setSpriteImage(identificador, "enemy303.png");
				break;
			}
			break;
		case 4:
			if (dos) {
				coordenadas.setX(coordenadas.getX() + 2);
			} else {
				coordenadas.setX(coordenadas.getX() + 1);
			}
			switch (tipo) {
			case "comandante galaga":
				if (vida == 2) {
					gui.gb_setSpriteImage(identificador, "enemy104.png");
				} else {
					gui.gb_setSpriteImage(identificador, "enemy904.png");
				}
				break;
			case "goei":
				gui.gb_setSpriteImage(identificador, "enemy204.png");
				break;
			case "zako":
				gui.gb_setSpriteImage(identificador, "enemy304.png");
				break;
			}
			break;
		case 5:
			coordenadas.setX(coordenadas.getX() + 2);
			coordenadas.setY(coordenadas.getY() + 1);
			switch (tipo) {
			case "comandante galaga":
				if (vida == 2) {
					gui.gb_setSpriteImage(identificador, "enemy105.png");
				} else {
					gui.gb_setSpriteImage(identificador, "enemy905.png");
				}
				break;
			case "goei":
				gui.gb_setSpriteImage(identificador, "enemy205.png");
				break;
			case "zako":
				gui.gb_setSpriteImage(identificador, "enemy305.png");
				break;
			}
			break;
		case 6:
			if (dos) {
				coordenadas.setX(coordenadas.getX() + 2);
				coordenadas.setY(coordenadas.getY() + 2);
			} else {
				coordenadas.setX(coordenadas.getX() + 1);
				coordenadas.setY(coordenadas.getY() + 1);
			}
			switch (tipo) {
			case "comandante galaga":
				if (vida == 2) {
					gui.gb_setSpriteImage(identificador, "enemy106.png");
				} else {
					gui.gb_setSpriteImage(identificador, "enemy906.png");
				}
				break;
			case "goei":
				gui.gb_setSpriteImage(identificador, "enemy206.png");
				break;
			case "zako":
				gui.gb_setSpriteImage(identificador, "enemy306.png");
				break;
			}
			break;
		case 7:
			coordenadas.setX(coordenadas.getX() + 1);
			coordenadas.setY(coordenadas.getY() + 2);
			switch (tipo) {
			case "comandante galaga":
				if (vida == 2) {
					gui.gb_setSpriteImage(identificador, "enemy107.png");
				} else {
					gui.gb_setSpriteImage(identificador, "enemy907.png");
				}
				break;
			case "goei":
				gui.gb_setSpriteImage(identificador, "enemy207.png");
				break;
			case "zako":
				gui.gb_setSpriteImage(identificador, "enemy307.png");
				break;
			}
			break;
		case 8:
			if (dos) {
				coordenadas.setY(coordenadas.getY() + 2);
			} else {
				coordenadas.setY(coordenadas.getY() + 1);
			}
			switch (tipo) {
			case "comandante galaga":
				if (vida == 2) {
					gui.gb_setSpriteImage(identificador, "enemy108.png");
				} else {
					gui.gb_setSpriteImage(identificador, "enemy908.png");
				}
				break;
			case "goei":
				gui.gb_setSpriteImage(identificador, "enemy208.png");
				break;
			case "zako":
				gui.gb_setSpriteImage(identificador, "enemy308.png");
				break;
			}
			break;
		case 9:
			coordenadas.setX(coordenadas.getX() - 1);
			coordenadas.setY(coordenadas.getY() + 2);
			switch (tipo) {
			case "comandante galaga":
				if (vida == 2) {
					gui.gb_setSpriteImage(identificador, "enemy109.png");
				} else {
					gui.gb_setSpriteImage(identificador, "enemy909.png");
				}
				break;
			case "goei":
				gui.gb_setSpriteImage(identificador, "enemy209.png");
				break;
			case "zako":
				gui.gb_setSpriteImage(identificador, "enemy309.png");
				break;
			}
			break;
		case 10:
			if (dos) {
				coordenadas.setX(coordenadas.getX() - 2);
				coordenadas.setY(coordenadas.getY() + 2);
			} else {
				coordenadas.setX(coordenadas.getX() - 1);
				coordenadas.setY(coordenadas.getY() + 1);
			}
			switch (tipo) {
			case "comandante galaga":
				if (vida == 2) {
					gui.gb_setSpriteImage(identificador, "enemy110.png");
				} else {
					gui.gb_setSpriteImage(identificador, "enemy910.png");
				}
				break;
			case "goei":
				gui.gb_setSpriteImage(identificador, "enemy210.png");
				break;
			case "zako":
				gui.gb_setSpriteImage(identificador, "enemy310.png");
				break;
			}
			break;
		case 11:
			coordenadas.setX(coordenadas.getX() - 2);
			coordenadas.setY(coordenadas.getY() + 1);
			switch (tipo) {
			case "comandante galaga":
				if (vida == 2) {
					gui.gb_setSpriteImage(identificador, "enemy111.png");
				} else {
					gui.gb_setSpriteImage(identificador, "enemy911.png");
				}
				break;
			case "goei":
				gui.gb_setSpriteImage(identificador, "enemy211.png");
				break;
			case "zako":
				gui.gb_setSpriteImage(identificador, "enemy311.png");
				break;
			}
			break;
		case 12:
			if (dos) {
				coordenadas.setX(coordenadas.getX() - 2);
			} else {
				coordenadas.setX(coordenadas.getX() - 1);
			}
			switch (tipo) {
			case "comandante galaga":
				if (vida == 2) {
					gui.gb_setSpriteImage(identificador, "enemy112.png");
				} else {
					gui.gb_setSpriteImage(identificador, "enemy912.png");
				}
				break;
			case "goei":
				gui.gb_setSpriteImage(identificador, "enemy212.png");
				break;
			case "zako":
				gui.gb_setSpriteImage(identificador, "enemy312.png");
				break;
			}
			break;
		case 13:
			coordenadas.setX(coordenadas.getX() - 2);
			coordenadas.setY(coordenadas.getY() - 1);
			switch (tipo) {
			case "comandante galaga":
				if (vida == 2) {
					gui.gb_setSpriteImage(identificador, "enemy113.png");
				} else {
					gui.gb_setSpriteImage(identificador, "enemy913.png");
				}
				break;
			case "goei":
				gui.gb_setSpriteImage(identificador, "enemy213.png");
				break;
			case "zako":
				gui.gb_setSpriteImage(identificador, "enemy313.png");
				break;
			}
			break;
		case 14:
			if (dos) {
				coordenadas.setX(coordenadas.getX() - 2);
				coordenadas.setY(coordenadas.getY() - 2);
			} else {
				coordenadas.setX(coordenadas.getX() - 1);
				coordenadas.setY(coordenadas.getY() - 1);
			}
			switch (tipo) {
			case "comandante galaga":
				if (vida == 2) {
					gui.gb_setSpriteImage(identificador, "enemy114.png");
				} else {
					gui.gb_setSpriteImage(identificador, "enemy914.png");
				}
				break;
			case "goei":
				gui.gb_setSpriteImage(identificador, "enemy214.png");
				break;
			case "zako":
				gui.gb_setSpriteImage(identificador, "enemy314.png");
				break;
			}
			break;
		case 15:
			coordenadas.setX(coordenadas.getX() - 1);
			coordenadas.setY(coordenadas.getY() - 2);
			switch (tipo) {
			case "comandante galaga":
				if (vida == 2) {
					gui.gb_setSpriteImage(identificador, "enemy115.png");
				} else {
					gui.gb_setSpriteImage(identificador, "enemy915.png");
				}
				break;
			case "goei":
				gui.gb_setSpriteImage(identificador, "enemy215.png");
				break;
			case "zako":
				gui.gb_setSpriteImage(identificador, "enemy315.png");
				break;
			}
			break;
		}
		//Guardar la nueva direccion y moverlo a su nuevo sitio
		this.direccion = direccion;
		gui.gb_moveSpriteCoord(identificador, coordenadas.getX(), coordenadas.getY());
	}

	/**
	 * Hace dar vueltas al enemigo aumentado o disminuyendo en 1 en que direccion se
	 * esta moviendo.
	 * 
	 * @param sentido Horario o antihorario
	 */
	public void vuelta(String sentido) {
		if (contadorMov >= 3) {
			contadorMov = 0;
		}
		contadorMov++;

		if (contadorMov == 1) {
			if (sentido.equals("horario")) {
				direccion++;
				if (direccion > 15) {
					direccion = 0;
				}

			} else {
				direccion--;
				if (direccion < 0) {
					direccion = 15;
				}
			}
			/*
			 * Para obetener mayor radio de vueta, se mueve 2 veces en esa direccion
			 */
			mover(direccion, true);
			mover(direccion, true);

		} else {
			if (contadorMov == 2) {
				contadorMov = 0;
			}
		}

	}

	/**
	 * El enemigo realiza un zigzag. Derecha, abajo, izquierda, abajo y vuelta a
	 * empezar
	 */
	public void zigzag() {
		contadorMov++;
		if (contadorMov <= 21) {
			mover(Global.DIR_SSE, true);
		}
		if (contadorMov > 21 && contadorMov < 31) {
			mover(Global.DIR_S, true);
		}
		if (contadorMov >= 31 && contadorMov < 51) {
			mover(Global.DIR_SSW, true);
		}
		if (contadorMov >= 51 && contadorMov < 61) {
			mover(Global.DIR_S, true);
		}
		if (contadorMov == 61) {
			contadorMov = 0;
		}
	}

	/**
	 * Vuelve a su posicion en el enjambre. Para ello, intenta igualar su posicion
	 * con la del hueco
	 */
	public void volver() {
		if (coordenadas.getX() > hueco.getX() && coordenadas.getY() == hueco.getY()) {
			mover(Global.DIR_W, false);
		} else {
			if (coordenadas.getX() < hueco.getX() && coordenadas.getY() == hueco.getY()) {
				mover(Global.DIR_E, false);
			} else {
				if (coordenadas.getX() > hueco.getX() && coordenadas.getY() > hueco.getY()) {
					mover(Global.DIR_NW, false);
				} else {
					if (coordenadas.getX() > hueco.getX() && coordenadas.getY() < hueco.getY()) {
						mover(Global.DIR_SW, false);
					} else {
						if (coordenadas.getX() < hueco.getX() && coordenadas.getY() < hueco.getY()) {
							mover(Global.DIR_SE, false);
						} else {
							if (coordenadas.getX() < hueco.getX() && coordenadas.getY() > hueco.getY()) {
								mover(Global.DIR_NE, false);
							} else {
								if (coordenadas.getY() > hueco.getY() && coordenadas.getX() == hueco.getX()) {
									mover(Global.DIR_N, false);
								} else {
									if (coordenadas.getY() < hueco.getY() && coordenadas.getX() == hueco.getX()) {
										mover(Global.DIR_S, false);

									}
								}
							}
						}
					}

				}
			}
		}
		/*
		 * Si ha logrado llegar a su posicion, cambiamos su estado
		 */
		if (coordenadas.getX() == hueco.getX() && coordenadas.getY() == hueco.getY()) {
			setEstado("enjambre");
		}
	}

	// metodos referentes a la COLISION

	/**
	 * Se sustituye la imagen por una explosion, creando una animacion progresiva
	 * que se realiza cada 5 ciclos para poder apreciarla bien
	 */
	public void explotar() {
		contadorMov++;
		if (contadorMov % 5 == 0) {
			switch (imagen) {
			case "explosion20.png":
				setImagen("explosion21.png");
				gui.gb_setSpriteImage(identificador, imagen);
				break;
			case "explosion21.png":
				setImagen("explosion22.png");
				gui.gb_setSpriteImage(identificador, imagen);
				break;
			case "explosion22.png":
				setImagen("explosion23.png");
				gui.gb_setSpriteImage(identificador, imagen);
				break;
			case "explosion23.png":
				setImagen("explosion24.png");
				gui.gb_setSpriteImage(identificador, imagen);
				break;
			case "explosion24.png":
				setEstado("muerto");
				gui.gb_setSpriteVisible(identificador, false);
				break;
			default:

				setEstado("muerto");
				gui.gb_setSpriteVisible(identificador, false);
				break;
			}
		}
	}
	// METODOS DE PRUEBA
}