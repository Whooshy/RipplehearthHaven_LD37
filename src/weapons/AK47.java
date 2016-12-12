package weapons;

import java.awt.*;

import engine.*;
import player.*;
import utilities.*;

/*
 * THIS CODE WAS MADE BY TREVOR STRICKLAND ON DECEMBER 2016,
 * PLEASE DO NOT COPY OR REDISTRIBUTE ANY OF THE CODE FOUND
 * WITHIN THIS CLASS, OR ANY OTHER CLASS ASSOCIATED WITH IT.
 * USE FOR REVIEWING OR EDUCATIONAL PURPOSES ONLY.
 */

public class AK47 {
	
	public static Rectangle cursorCast;
	
	public static boolean firing = false;
	
	public float bulletSpeedX, bulletSpeedY;
	public float speedModifier = 10;
	
	public Bullet[] bullets = new Bullet[10];
	
	public int firingTime = 0;
	public float firingRate = 25;
	
	public static int frmod = 1;
	
	public AK47(){
		cursorCast = new Rectangle(0, 0, 1, 1);
		
		for(int i = 0; i < bullets.length; i++) bullets[i] = new Bullet(bulletSpeedX, bulletSpeedY, 0);
	}
	
	public void update(Graphics2D g){
		g.setColor(Color.ORANGE);
		g.draw(cursorCast);
		
		int distanceX = cursorCast.x - (int) (Player.x + (Engine.GRIDSIZE / 2));
		int distanceY = cursorCast.y - (int) (Player.y + (Engine.GRIDSIZE / 2));
		
		bulletSpeedX = (distanceX) / speedModifier;
		bulletSpeedY = (distanceY) / speedModifier;
		
		if(firing && (cursorCast.x < Player.x - 100)){
			firingTime += 1;
				g.drawImage(Images.ak47, (int) Player.x + 48, (int) Player.y + 32, -Engine.GRIDSIZE, 32, null);
				bullets[0].update(g);
				bullets[0].x += bulletSpeedX;
				bullets[0].y += bulletSpeedY;
				bullets[0].degrees = (90);
				if(firingTime > firingRate / frmod){
					bullets[0].x = Player.x + (Engine.GRIDSIZE / 2);
					bullets[0].y = Player.y + (Engine.GRIDSIZE / 2);
					firingTime = 0;
				}
			System.out.println(bullets[0].degrees);
		}
		if(firing && (cursorCast.x > Player.x + 100)){
			firingTime += 1;
				g.drawImage(Images.ak47, (int) Player.x + 16, (int) Player.y + 32, Engine.GRIDSIZE, 32, null);
				bullets[0].update(g);
				bullets[0].x += bulletSpeedX;
				bullets[0].y += bulletSpeedY;
				bullets[0].degrees = (((bulletSpeedY / bulletSpeedX) * 40) - 0);
				if(firingTime > firingRate * 1){
					bullets[0].x = Player.x + (Engine.GRIDSIZE / 2);
					bullets[0].y = Player.y + (Engine.GRIDSIZE / 2);
					firingTime = 0;
				}
			System.out.println(bullets[0].degrees);
		}
	}

}
