package level;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

import javax.imageio.*;

import enemies.*;
import engine.*;
import objects.*;
import player.*;
import weapons.*;

/*
 * THIS CODE WAS MADE BY TREVOR STRICKLAND ON DECEMBER 2016,
 * PLEASE DO NOT COPY OR REDISTRIBUTE ANY OF THE CODE FOUND
 * WITHIN THIS CLASS, OR ANY OTHER CLASS ASSOCIATED WITH IT.
 * USE FOR REVIEWING OR EDUCATIONAL PURPOSES ONLY.
 */

public class Level {
	
	public static Tile[][] tiles = new Tile[250][75];
	
	public static Player player;
	public static AK47 ak47;
	
	public static Rectangle borders;
	
	public Random random = new Random();
	
	public Crawler[] enemies = new Crawler[10];
	public Crawler test = new Crawler(125 * Engine.GRIDSIZE, 0 * Engine.GRIDSIZE);
	
	public static float bx, by, bwidth, bheight;

	public Level(){
		for(int i = 0; i < 10; i++) enemies[i] = new Crawler(random.nextInt(Engine.GRIDSIZE * Level.tiles.length), Engine.GRIDSIZE * 0);
		createLevel("/maps/lvl_1_dark_forest.png");
	}
	
	public void createLevel(String levelPath){
		ak47 = new AK47();
		bx = 0;
		by = 0;
		bwidth = 0;
		bheight = 0;
		borders = new Rectangle((int) bx, (int) by, (int) bwidth + (Engine.GRIDSIZE * (tiles.length / 2)), (int) bheight + (Engine.GRIDSIZE * (tiles.length / 2)));
		try {
			BufferedImage levelMap = ImageIO.read(getClass().getResource(levelPath));
			
			for(int x = 0; x < levelMap.getWidth(); x++){
				for(int y = 0; y < levelMap.getHeight(); y++){
					int currentPixel = levelMap.getRGB(x, y);
					
					int red = (currentPixel >> 16) & 0xff;
					int green = (currentPixel >> 8) & 0xff;
					int blue = (currentPixel) & 0xff;
					
					if(red == 0 && green == 0 && blue == 0) tiles[x][y] = new Tile(new Rectangle(x * Engine.GRIDSIZE, y * Engine.GRIDSIZE, Engine.GRIDSIZE, Engine.GRIDSIZE), Tile.Air);
					if(red == 255 && green == 255 && blue == 255) tiles[x][y] = new Tile(new Rectangle(x * Engine.GRIDSIZE, y * Engine.GRIDSIZE, Engine.GRIDSIZE, Engine.GRIDSIZE), Tile.RuinsFloor);
					if(red == 0 && green == 0 && blue == 255){ 
						player = new Player(x * Engine.GRIDSIZE, y * Engine.GRIDSIZE);
						tiles[x][y] = new Tile(new Rectangle(x * Engine.GRIDSIZE, y * Engine.GRIDSIZE, Engine.GRIDSIZE, Engine.GRIDSIZE), Tile.Air);
					}
					if(red == 0 && green == 255 && blue == 0) tiles[x][y] = new Tile(new Rectangle(x * Engine.GRIDSIZE, y * Engine.GRIDSIZE, Engine.GRIDSIZE * 3, Engine.GRIDSIZE* 3), Tile.Stargate);
					if(red == 255 && green == 255 && blue == 235) tiles[x][y] = new Tile(new Rectangle(x * Engine.GRIDSIZE, y * Engine.GRIDSIZE, Engine.GRIDSIZE, Engine.GRIDSIZE), Tile.RuinsBase);
					if(red == 255 && green == 255 && blue == 215) tiles[x][y] = new Tile(new Rectangle(x * Engine.GRIDSIZE, y * Engine.GRIDSIZE, Engine.GRIDSIZE, Engine.GRIDSIZE), Tile.Grass);
					if(red == 255 && green == 255 && blue == 195) tiles[x][y] = new Tile(new Rectangle(x * Engine.GRIDSIZE, y * Engine.GRIDSIZE, Engine.GRIDSIZE, Engine.GRIDSIZE), Tile.Dirt);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Graphics2D g){
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[0].length; y++){
				tiles[x][y].update(g);
			}
		}
		
		borders = new Rectangle((int) bx, (int) by, (int) bwidth + (Engine.GRIDSIZE * tiles.length), (int) bheight + (Engine.GRIDSIZE * tiles[0].length));
		
		g.setColor(Color.RED);
		//top
		g.fillRect(0, 0, tiles.length * Engine.GRIDSIZE, (int) by);
		//left
		g.fillRect(0, 0, (int) bx, tiles[0].length * Engine.GRIDSIZE);
		//right
		g.fillRect(Engine.GRIDSIZE * tiles.length, 0, (int) bwidth, Engine.GRIDSIZE * tiles[0].length);
		//bottom
		g.fillRect(0, Engine.GRIDSIZE * tiles[0].length, Engine.GRIDSIZE * tiles.length, (int) bheight);

		g.setColor(Color.BLACK);
		g.draw(borders);
		
		bx++;
		by += 0.3333f;
		bwidth--;
		bheight -= 0.23f;
		
		//System.out.println(bx + " " + by + " " + bwidth + " " + bheight);
		
		//System.out.println((enemies[0].x / Engine.GRIDSIZE) + ", " + (enemies[0].y / Engine.GRIDSIZE));
		
		player.update(g);
		for(int i = 0; i < 10; i++){
			enemies[i].update(g);
		}
		test.update(g);
		ak47.update(g);
	}
}
