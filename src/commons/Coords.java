package commons;

import java.io.*;

 
public class Coords implements Serializable {
	
	private int posX, posY;

	public Coords(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	@Override
	public String toString() {
		return "Coords{" + "X:" + posX + ", Y:" + posY + '}';
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 29 * hash + this.posX;
		hash = 29 * hash + this.posY;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coords) {
			Coords other = (Coords) obj;
			return this.posX == other.getPosX() && this.posY == other.getPosY();
		}
		return false;
	}
}
