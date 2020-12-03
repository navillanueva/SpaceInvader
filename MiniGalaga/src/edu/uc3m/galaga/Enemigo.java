//Los enemigos del juego y sus acciones. UC3M. Monica Ocania Bastante & Nicolas Arnedo Villanueva
package edu.uc3m.galaga;

/**
 * En esta clase se guardan los datos de cada enemigo
 * y sus metodos particulares.
 * @author Monica Ocania Bastante & Nicolas Arnedo Villanueva
 * @since 2018/11/28
 */

import edu.uc3m.game.GameBoardGUI;

public class Enemigo {// dependiendo de como se llame tendra unas caracteristicas u otras.
	private Global cte = new Global();
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
	private Posicion coordenadas;
	/**
	 * Su identificador de sprite
	 */
	private int identificador;// entre 100 y 200;
	/**
	 * El tipo de enemigo que es. Puede ser: zako, goei o comandante galaga. Si pone
	 * "vacio", el sistema entiende que ese hueco no lo ocupa un enemigo "vivo"
	 */
	private String tipo = "vacio";// Zako, goei, comandante galaga
	/**
	 * El dibujo que tiene
	 */
	private int direccion;
	private String estado;//enjambre, muerto, explotando, atacando
	private String imagen;
	private GameBoardGUI gui;
	// crear una variable que controle la rotacion

	// Constructor vacio para poder poner el tipo "vacio"
	public Enemigo() {

	}

	public Enemigo(String tipo, int identificador, int x, int y, GameBoardGUI gui) {
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
			this.tipo = tipo;
			imagen = "enemy3G0.png";
			break;
		}
		// Coordenadas
		//if ((x <= cte.getxMax() && x >= cte.getMin()) && (y <= cte.getyMax() && y >= cte.getMin())) {
			coordenadas = new Posicion(x, y);
		//} else {
		//	coordenadas = new Posicion(cte.getxMax(), cte.getyMax());
		//}
		setIdentificador(identificador);

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

	// SETTERS
	public void setVida(int vida) {
		this.vida = vida;
	}

	public void setPuntos(int puntosVencer) {
		this.puntos = puntosVencer;
	}

	public void setCoordenadas(Posicion coordenadas) {
		
		this.coordenadas=coordenadas;
		//int x = coordenadas.getX();
		//int y = coordenadas.getY();
		// Coordenadas
		// Comprobar esta dentro del tablero
		// x. No puede salirse por los lados
		/*
		if (x <= cte.getxMax() && x >= cte.getMin()) {
			coordenadas.setX(x);
		} else {
			coordenadas.setX(170);
		}
		// y
		if (y <= cte.getyMax() && x >= 0) {
			coordenadas.setY(y);
		} else {
			coordenadas.setY(220);
		}
		*/
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

	// METODOS ESPECIALES
	/**
	 * Cambia la imagen norte del enemigo
	 * 
	 * @param tipo
	 */
	public void alternarImagen(String tipo) {
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
	 * Borra al enemigo de la pantalla
	 */
	public void destruido() {
		setImagen("empty.png");
		gui.gb_setSpriteImage(getIdentificador(), getImagen());
		gui.gb_println("El enemigo " + tipo + " ha sido eliminido.");
		gui.gb_println("Has ganado " + puntos);
		setTipo("vacio");// se pone al final para que no salga "el enemigo vacio ha sido eliminado"
	}

	/*
	 * public void mover(int direccion, int pasos) {
	 * 
	 * if (direccion < 0 || direccion > 16 || pasos < 1) { //return false; } else {
	 * 
	 * this.direccion = direccion; this.coordenadas.setX(coordenadas.getX() +
	 * Constante.MOVES[direccion][0] * pasos);
	 * this.coordenadas.setY(coordenadas.getY() + Constante.MOVES[direccion][1] *
	 * pasos); //return true; gui.gb_moveSpriteCoord(identificador,
	 * coordenadas.getX(), coordenadas.getY()); } }
	 */

	public void mover(int direccion) {
		if (direccion % 2 == 0) {
			switch (direccion) {
			case 0:
				coordenadas.setY(coordenadas.getY() - 1);
				break;
			case 2:
				coordenadas.setX(coordenadas.getX() + 1);
				coordenadas.setY(coordenadas.getY() - 1);
				break;
			case 4:
				coordenadas.setX(coordenadas.getX() + 1);
				break;
			case 6:
				coordenadas.setX(coordenadas.getX() + 1);
				coordenadas.setY(coordenadas.getY() + 1);
				break;
			case 8:
				coordenadas.setY(coordenadas.getY() + 1);
				break;
			case 10:
				coordenadas.setX(coordenadas.getX() - 1);
				coordenadas.setY(coordenadas.getY() + 1);
				break;
			case 12:
				coordenadas.setX(coordenadas.getX() - 1);
				break;
			case 14:
				coordenadas.setX(coordenadas.getX() - 1);
				coordenadas.setY(coordenadas.getY() - 1);
				break;
			}
			gui.gb_moveSpriteCoord(identificador, coordenadas.getX(), coordenadas.getY());
		}
	}
	

}