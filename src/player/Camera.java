package player;

import engine.*;
import level.*;

/*
 * THIS CODE WAS MADE BY TREVOR STRICKLAND ON DECEMBER 2016,
 * PLEASE DO NOT COPY OR REDISTRIBUTE ANY OF THE CODE FOUND
 * WITHIN THIS CLASS, OR ANY OTHER CLASS ASSOCIATED WITH IT.
 * USE FOR REVIEWING OR EDUCATIONAL PURPOSES ONLY.
 */

public class Camera {
	
	public float x, y;

	public Camera(){
		x = 0;
		y = 0;
	}
	
	public void update(){
		x = -Level.player.x + (Engine.width / 2) - (Engine.GRIDSIZE / 2);
		y = -Level.player.y + (Engine.height / 2) - (Engine.GRIDSIZE / 2);
	}
}
