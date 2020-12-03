package edu.uc3m.galaga;

import edu.uc3m.game.GameBoardGUI;

//clse de prueba en caso de que queramos meter herencia dentro de la practica
public abstract class SpritePrueba {
	protected int identificador;
	protected String imagen;
	protected Posicion coordenadas;	
	protected GameBoardGUI gui;
	
	//CONSTRUCTOR
	
	public SpritePrueba (GameBoardGUI gui, int identificador, String imagen, Posicion coordenadas) {
		this.identificador=identificador;
		this.imagen=imagen;
		this.coordenadas=coordenadas;
		this.gui=gui;
	}
	
	
	//GETTRRS
	public int getIdentificador() {
			return identificador;
		}
	public String getImagen() {
		return imagen;
	}
	public Posicion getCoordenadas() {
			return coordenadas;
		}
	
	
	
	
	//SETTERS
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public void setCoordenadas(Posicion coordenadas) {
		this.coordenadas = coordenadas;
	}

	//METODOS
	
	public abstract void mover(int direccion);
	//mover(x + Global.MOVE_DELTAS[direccion][0], y+Global.MOVE_DELTAS[direccion][1]);
	
	

	
}
