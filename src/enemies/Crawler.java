package enemies;

import java.awt.*;
import java.util.*;

import engine.*;
import hud.*;
import level.*;
import objects.*;
import player.*;
import utilities.*;

/*
 * THIS CODE WAS MADE BY TREVOR STRICKLAND ON DECEMBER 2016,
 * PLEASE DO NOT COPY OR REDISTRIBUTE ANY OF THE CODE FOUND
 * WITHIN THIS CLASS, OR ANY OTHER CLASS ASSOCIATED WITH IT.
 * USE FOR REVIEWING OR EDUCATIONAL PURPOSES ONLY.
 */

public class Crawler {
	
	public float x, y;
	
	public float crawlFrames = 0;
	
	public float gravity = 0;
	public float max_downForce = 7;
	
	public float hp = 20;
	public int addHp = 10;
	
	public float speed = 1.5f;
	
	public static float damage = 1f;
	
	public boolean isFalling, isCollidingBottom, isCollidingLeft, isCollidingRight, isCollidingTop;
	
	public Random random = new Random();

	public Crawler(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void update(Graphics2D g){
		if(crawlFrames >= 4) crawlFrames = 0; 
		
		g.drawImage(Images.crawler_crawl[(int) crawlFrames], (int) x, (int) y, Engine.GRIDSIZE, Engine.GRIDSIZE, null);
		
		if(Player.x > x && !isCollidingRight){
			x += speed;
			crawlFrames += 0.1f;
		}
		if(Player.x < x && !isCollidingLeft){
			x -= speed;
			crawlFrames += 0.1f;
		}
		
		if(isCollidingLeft || isCollidingRight){
			gravity = -2f;
		}
		
		if(x < Level.bx) x += 2;
		if(x > Level.bwidth + (Engine.GRIDSIZE * 250)) x -= 2;
		
		if(y < Level.by) y += 2;
		if(y > Level.bheight + (Engine.GRIDSIZE * 75)) y -= 2;
		
		System.out.println(Level.bwidth + ", " + Level.bheight);
		
		y += gravity;
		
		if(hp <= 0){
			x = random.nextInt(Engine.GRIDSIZE * Level.tiles.length);
			y = Engine.GRIDSIZE * Level.tiles[0].length;
			hp = 20 + addHp;
			addHp += 10;
			Level.bx -= Engine.GRIDSIZE * 10;
			Level.by -= Engine.GRIDSIZE * 10;
			Level.bwidth += Engine.GRIDSIZE * 10;
			Level.bheight += Engine.GRIDSIZE * 10;
			Player.gold += 1;
		}
		
		if(Level.ak47.bullets[0].hitbox().intersects(hitBox())){
			hp -= damage;
		}
		
		if(Level.player.hitBox().intersects(hitBox())){
			Health.hp -= 0.1f;
		}
		
		System.out.println(hp);
		
		collision();
	}
	
	public void collision(){
		for(int x = 0; x < Level.tiles.length; x++){
			for(int y = 0; y < Level.tiles[0].length; y++){
				if(bottomBox().intersects(Level.tiles[x][y].getBounds()) && Level.tiles[x][y].id != Tile.Air){
					isFalling = false;
					isCollidingBottom = true;
				}else if(bottomBox().intersects(Level.tiles[x][y].getBounds()) && Level.tiles[x][y].id == Tile.Air){
					isFalling = true;
					gravity += 0.02f;
					
					this.y += 2;
					
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
		return new Rectangle((int) x, (int) y, Engine.GRIDSIZE, (int) Engine.GRIDSIZE);
	}
	
}
