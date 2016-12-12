package utilities;

import java.awt.image.*;
import java.io.*;

import javax.imageio.*;

/*
 * THIS CODE WAS MADE BY TREVOR STRICKLAND ON DECEMBER 2016,
 * PLEASE DO NOT COPY OR REDISTRIBUTE ANY OF THE CODE FOUND
 * WITHIN THIS CLASS, OR ANY OTHER CLASS ASSOCIATED WITH IT.
 * USE FOR REVIEWING OR EDUCATIONAL PURPOSES ONLY.
 */

public class Images {
	
	public static BufferedImage playerWalkSheet;
	public static BufferedImage playerIdleSheet;
	
	public static BufferedImage tilesheet_ruins;
	
	public static BufferedImage ak47;
	public static BufferedImage fast_bullet;
	
	public static BufferedImage crawler;
	
	public static BufferedImage night_sky;
	
	public static BufferedImage[] player_walking = new BufferedImage[8];
	public static BufferedImage[] player_idle = new BufferedImage[8];
	
	public static BufferedImage[] tiles = new BufferedImage[6];
	
	public static BufferedImage[] health = new BufferedImage[11];
	
	public static BufferedImage[] crawler_crawl = new BufferedImage[4];
	
	public static BufferedImage[] upgradeTab = new BufferedImage[2];

	public Images(){
		try {
			playerWalkSheet = ImageIO.read(getClass().getResource("/player/walking.png"));
			playerIdleSheet = ImageIO.read(getClass().getResource("/player/idle.png"));
			
			tilesheet_ruins = ImageIO.read(getClass().getResource("/tiles/tilesheet_ruins.png"));
			
			night_sky = ImageIO.read(getClass().getResource("/backgrounds/night_sky.png"));
			
			player_walking[0] = getSprite(playerWalkSheet, 1, 1, 32, 32);
			player_walking[1] = getSprite(playerWalkSheet, 2, 1, 32, 32);
			player_walking[2] = getSprite(playerWalkSheet, 3, 1, 32, 32);
			player_walking[3] = getSprite(playerWalkSheet, 4, 1, 32, 32);
			player_walking[4] = getSprite(playerWalkSheet, 5, 1, 32, 32);
			player_walking[5] = getSprite(playerWalkSheet, 6, 1, 32, 32);
			player_walking[6] = getSprite(playerWalkSheet, 7, 1, 32, 32);
			player_walking[7] = getSprite(playerWalkSheet, 8, 1, 32, 32);
			
			player_idle[0] = getSprite(playerIdleSheet, 1, 1, 32, 32);
			player_idle[1] = getSprite(playerIdleSheet, 2, 1, 32, 32);
			player_idle[2] = getSprite(playerIdleSheet, 3, 1, 32, 32);
			player_idle[3] = getSprite(playerIdleSheet, 4, 1, 32, 32);
			player_idle[4] = getSprite(playerIdleSheet, 5, 1, 32, 32);
			player_idle[5] = getSprite(playerIdleSheet, 6, 1, 32, 32);
			player_idle[6] = getSprite(playerIdleSheet, 7, 1, 32, 32);
			player_idle[7] = getSprite(playerIdleSheet, 8, 1, 32, 32);
			
			tiles[0] = null;
			tiles[1] = getSprite(tilesheet_ruins, 1, 1, 8, 8);
			tiles[2] = getSprite(tilesheet_ruins, 2, 1, 8, 8);
			tiles[3] = tilesheet_ruins.getSubimage(16, 0, 24, 24);
			tiles[4] = getSprite(tilesheet_ruins, 6, 1, 8, 8);
			tiles[5] = getSprite(tilesheet_ruins, 7, 1, 8, 8);
			
			ak47 = ImageIO.read(getClass().getResource("/weapons/ak47.png"));
			fast_bullet = ImageIO.read(getClass().getResource("/weapons/fast_bullet.png"));
			
			health[0] = ImageIO.read(getClass().getResource("/hud/health_0.png"));
			health[1] = ImageIO.read(getClass().getResource("/hud/health_1.png"));
			health[2] = ImageIO.read(getClass().getResource("/hud/health_2.png"));
			health[3] = ImageIO.read(getClass().getResource("/hud/health_3.png"));
			health[4] = ImageIO.read(getClass().getResource("/hud/health_4.png"));
			health[5] = ImageIO.read(getClass().getResource("/hud/health_5.png"));
			health[6] = ImageIO.read(getClass().getResource("/hud/health_6.png"));
			health[7] = ImageIO.read(getClass().getResource("/hud/health_7.png"));
			health[8] = ImageIO.read(getClass().getResource("/hud/health_8.png"));
			health[9] = ImageIO.read(getClass().getResource("/hud/health_9.png"));
			health[10] = ImageIO.read(getClass().getResource("/hud/health_10.png"));
			
			crawler = ImageIO.read(getClass().getResource("/enemies/crawler.png"));
			
			crawler_crawl[0] = getSprite(crawler, 1, 1, 64, 64);
			crawler_crawl[1] = getSprite(crawler, 2, 1, 64, 64);
			crawler_crawl[2] = getSprite(crawler, 3, 1, 64, 64);
			crawler_crawl[3] = getSprite(crawler, 4, 1, 64, 64);
			
			upgradeTab[0] = ImageIO.read(getClass().getResource("/hud/upgrade_bar_closed.png"));
			upgradeTab[1] = ImageIO.read(getClass().getResource("/hud/upgrade_bar_open.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(BufferedImage sheet, int column, int row, int width, int height){
		sheet = sheet.getSubimage((column * width) - width, (row * height) - height, width, height);
		return sheet;
	}
}
