package player;

import java.awt.*;

import engine.*;
import hud.*;
import level.*;
import objects.*;
import utilities.*;

/*
 * THIS CODE WAS MADE BY TREVOR STRICKLAND ON DECEMBER 2016,
 * PLEASE DO NOT COPY OR REDISTRIBUTE ANY OF THE CODE FOUND
 * WITHIN THIS CLASS, OR ANY OTHER CLASS ASSOCIATED WITH IT.
 * USE FOR REVIEWING OR EDUCATIONAL PURPOSES ONLY.
 */

public class Player {
	
	public static float x, y;

	public float velX;

	public float velY;
	
	public float speed = 2f;
	
	public int direction = 0;
	
	public boolean isCollidingBottom, isCollidingTop, isCollidingLeft, isCollidingRight, isFalling, isJumping, debug;
	
	public float gravity;
	
	public float max_downForce = 7f;
	
	public static int gold = 0;
	
	public float walkingFrames = 0;
	public float idleFrames = 0;

	public Player(float x, float y){
		this.x = x;
		this.y = y;
		
		gravity = 0;
		
		isCollidingLeft = false;
		isCollidingRight = false;
		
		debug = false;
		
		direction = 2;
		isFalling = true;
	}
	
	public void update(Graphics2D g){
		if(direction == 0 || direction == 1) g.drawImage(Images.player_walking[(int) walkingFrames], (int) x, (int) y, Engine.GRIDSIZE, Engine.GRIDSIZE, null);
		if(direction == 2 || direction == 3) g.drawImage(Images.player_idle[(int) idleFrames], (int) x, (int) y, Engine.GRIDSIZE, Engine.GRIDSIZE, null);
		
		if(direction == 0 && !isCollidingLeft){
			velX = -speed;
			walkingFrames -= 0.05f;
			if(walkingFrames < 4) walkingFrames = 7;
		}else if(direction == 0 && isCollidingLeft){
			direction = 2;
		}
		
		if(direction == 1 && !isCollidingRight){
			velX = speed;
			walkingFrames += 0.05f;
			if(walkingFrames > 3) walkingFrames = 0;
		}else if(direction == 1 && isCollidingRight){
			direction = 3;
		}
		
		if(direction == 2){
			velX = 0;
			idleFrames -= 0.01f;
			if(idleFrames < 4) idleFrames = 7;
		}
		if(direction == 3){
			velX = 0;
			idleFrames += 0.01f;
			if(idleFrames > 4) idleFrames = 0;
		}
		
		if(isFalling || isJumping){
			y += gravity;
		}
		
		if(debug){
			g.setColor(Color.GREEN);
			g.draw(bottomBox());
			g.draw(topBox());
			g.draw(leftBox());
			g.draw(rightBox());
		}
		
		x += velX;
		y += velY;
		
		collision();
		
		if(topBox().intersects(Level.borders)){
			
		}else{
			Health.hp -= 0.1f;
		}
	}
	
	public void collision(){
		for(int x = 0; x < Level.tiles.length; x++){
			for(int y = 0; y < Level.tiles[0].length; y++){
				if(bottomBox().intersects(Level.tiles[x][y].getBounds()) && Level.tiles[x][y].id != Tile.Air){
					isFalling = false;
					isCollidingBottom = true;
				}else if(bottomBox().intersects(Level.tiles[x][y].getBounds()) && Level.tiles[x][y].id == Tile.Air){
					isFalling = true;
					gravity += 0.1f;
					
					isCollidingBottom = false;
					
					if(gravity >= max_downForce){
						gravity = max_downForce;
					}
				}
				if(leftBox().intersects(Level.tiles[x][y].getBounds()) && Level.tiles[x][y].id != Tile.Air){
					isCollidingLeft = true;
					//System.out.println("Colliding Left!");
				}else if(leftBox().intersects(Level.tiles[x][y].getBounds()) && Level.tiles[x][y].id == Tile.Air){
					isCollidingLeft = false;
				}
				if(rightBox().intersects(Level.tiles[x][y].getBounds()) && Level.tiles[x][y].id != Tile.Air){
					isCollidingRight = true;
					//System.out.println("Colliding Right!");
				}else if(rightBox().intersects(Level.tiles[x][y].getBounds()) && Level.tiles[x][y].id == Tile.Air){
					isCollidingRight = false;
				}
				if(topBox().intersects(Level.tiles[x][y].getBounds()) && Level.tiles[x][y].id != Tile.Air){
					isCollidingTop = true;
					isFalling = true;
				}else if(topBox().intersects(Level.tiles[x][y].getBounds()) && Level.tiles[x][y].id == Tile.Air){
					isCollidingTop = false;
				}
			}
		}
	}
	
	public Rectangle topBox(){
		return new Rectangle((int) x + (Engine.GRIDSIZE / 2), (int) y, 1, 10);
	}
	
	public Rectangle bottomBox(){
		return new Rectangle((int) x + (Engine.GRIDSIZE / 2), (int) y + (Engine.GRIDSIZE - 10), 1, 10);
	}
	
	public Rectangle leftBox(){
		return new Rectangle((int) x - 1, (int) y + (Engine.GRIDSIZE / 2), 1, 1);
	}
	
	public Rectangle rightBox(){
		return new Rectangle((int) x + (Engine.GRIDSIZE + 1), (int) y + (Engine.GRIDSIZE / 2), 1, 1);
	}
	
	public Rectangle hitBox(){
		return new Rectangle((int) x, (int) y, Engine.GRIDSIZE, Engine.GRIDSIZE);
	}
}
