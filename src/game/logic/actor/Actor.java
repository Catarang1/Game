
package game.logic.actor;
 
import game.logic.*;
import javafx.scene.image.*;

public abstract class Actor implements Spawnable {
	
	public int posX, posY = 0;
	public Image image;

	public Actor(Image image) {
		this.image = image;
	}

	@Override
	public void spawn() {
		Engine.actors.add(this);
	}

	@Override
	public void spawn(int x, int y) {
		posX = x;
		posY = y;
		spawn();
	}

}
