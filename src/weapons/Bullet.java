package weapons;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

import player.*;
import utilities.*;

/*
 * THIS CODE WAS MADE BY TREVOR STRICKLAND ON DECEMBER 2016,
 * PLEASE DO NOT COPY OR REDISTRIBUTE ANY OF THE CODE FOUND
 * WITHIN THIS CLASS, OR ANY OTHER CLASS ASSOCIATED WITH IT.
 * USE FOR REVIEWING OR EDUCATIONAL PURPOSES ONLY.
 */

public class Bullet {
	
	public float x, y, width, height, speedX, speedY, degrees;

	public Bullet(float speedX, float speedY, float degrees){
		this.speedX = speedX;
		this.speedY = speedY;
		this.degrees = degrees;
		
		width = 9;
		height = 9;
		
		x = Player.x;
		y = Player.y;
	}
	
	public void update(Graphics2D g){
		g.drawImage(Images.fast_bullet, (int) x, (int) y, (int) width, (int) height, null);
		
		if(width <= 0) width = width * -1;

		x += speedX;
		y += speedY;
		
		//System.out.println(x + ", " + y);
	}
	
	public Rectangle hitbox(){
		return new Rectangle((int) x, (int) y, 9, 9);
	}
	
}
