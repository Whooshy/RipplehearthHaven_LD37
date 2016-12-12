package objects;

import java.awt.*;

import utilities.*;

/*
 * THIS CODE WAS MADE BY TREVOR STRICKLAND ON DECEMBER 2016,
 * PLEASE DO NOT COPY OR REDISTRIBUTE ANY OF THE CODE FOUND
 * WITHIN THIS CLASS, OR ANY OTHER CLASS ASSOCIATED WITH IT.
 * USE FOR REVIEWING OR EDUCATIONAL PURPOSES ONLY.
 */

public class Tile extends Rectangle{
	
	public static int Air = 0;
	public static int RuinsBase = 1;
	public static int RuinsFloor = 2;
	public static int Stargate = 3;
	public static int Grass = 4;
	public static int Dirt = 5;
	
	public int id;

	public Tile(Rectangle size, int id){
		setBounds(size);
		this.id = id;
	}
	
	public void update(Graphics2D g){
		g.drawImage(Images.tiles[id], x, y, width, height, null);
		
	}
	
}
