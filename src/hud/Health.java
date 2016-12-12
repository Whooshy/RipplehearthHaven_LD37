package hud;

import java.awt.*;

import engine.*;
import level.*;
import utilities.*;

/*
 * THIS CODE WAS MADE BY TREVOR STRICKLAND ON DECEMBER 2016,
 * PLEASE DO NOT COPY OR REDISTRIBUTE ANY OF THE CODE FOUND
 * WITHIN THIS CLASS, OR ANY OTHER CLASS ASSOCIATED WITH IT.
 * USE FOR REVIEWING OR EDUCATIONAL PURPOSES ONLY.
 */

public class Health {
	
	public static float hp = 10;
	
	public static int time = 0;

	public Health(){
		
	}
	
	public void update(Graphics2D g){
		//g.rotate(Math.toRadians(0), 0, 0);
		if(hp <= 0){
			hp = 0;
			die(g);
		}
		
		g.drawImage(Images.health[(int) hp], 20, 20, Engine.GRIDSIZE, Engine.GRIDSIZE, null);
		
		//hp--;
		
	}
	
	public void die(Graphics2D g){
		g.setColor(Color.RED);
		g.setFont(new Font("Courier", Font.PLAIN, 32));
		g.drawString("YOU DIED", Engine.width / 2 - (2 * 32), 150);
		
		time++;
		if(time > 200){
			Engine.STATE = 1;
			hp = 10;
		}
	}
	
}
