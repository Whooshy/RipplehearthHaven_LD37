package engine;

import java.awt.*;

import java.awt.event.*;
import java.awt.image.*;
import java.net.*;

import javax.sound.sampled.*;
import javax.swing.*;

import enemies.*;
import hud.*;
import level.*;
import player.*;
import utilities.*;

/*
 * THIS CODE WAS MADE BY TREVOR STRICKLAND ON DECEMBER 2016,
 * PLEASE DO NOT COPY OR REDISTRIBUTE ANY OF THE CODE FOUND
 * WITHIN THIS CLASS, OR ANY OTHER CLASS ASSOCIATED WITH IT.
 * USE FOR REVIEWING OR EDUCATIONAL PURPOSES ONLY.
 */
public class Engine extends Canvas implements Runnable, KeyListener, MouseListener{
	
	private Thread thread;
	public boolean isRunning = false;
	
	public static int width = 1000;
	public static int height = 700;
	
	public static int GRIDSIZE = 64;
	public static int STATE = 1;
	
	public static boolean walking = false;
	
	public Images imgs;
	public Camera cam;
	public Health health;
	public UpgradeTab ut;
	public Level lvl;
	
	public Clip walkSound;
	public Clip shotSound;
	public Clip deathSound;
	
	public Rectangle upgradeTab = new Rectangle(0, Engine.GRIDSIZE * 3, Engine.GRIDSIZE, Engine.GRIDSIZE * 3);
	public Rectangle cursorCast;
	
	public Engine(){
		imgs = new Images();
		cam = new Camera();
		lvl = new Level();
		ut = new UpgradeTab();
		health = new Health();
		JFrame frame = new JFrame("Ripplehearth Haven");
		
		setPreferredSize(new Dimension(width, height));
		
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		frame.add(this, BorderLayout.CENTER);
		addKeyListener(this);
		addMouseListener(this);
		
		frame.pack();
		frame.setLocationRelativeTo(null);

		start();
	}

	public static void main(String[] args){
		new Engine();
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		
		isRunning = true;
	}
	
	public void run(){
		while(isRunning){
			try {
				thread.sleep(7);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			update();
		}
	}
	
	public synchronized void stop(){
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void update(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics graphics = bs.getDrawGraphics();
		Graphics2D g = (Graphics2D) graphics;
		
		if(STATE == 0){
			cam.update();
			
			g.setColor(new Color(0, 0, 0));
			g.fillRect(0, 0, Engine.width, Engine.height);
			
			g.drawImage(Images.night_sky, 0, 0, 1000, 750, null);
			
			g.translate(cam.x, cam.y);
			lvl.update(g);
			g.translate(-cam.x, -cam.y);
			
			health.update(g);
			ut.update(g);
			
			g.setColor(Color.YELLOW);
			g.setFont(new Font("Courier", Font.PLAIN, 30));
			g.drawString("Gold: " + Player.gold, 100, 70);
		}
		if(STATE == 1){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, width, height);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier", Font.PLAIN, 50));
			g.drawString("Ripplehearth Haven", 225, 100);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier", Font.PLAIN, 30));
			g.drawString("<press enter to play>", (Engine.width / 2) - 200, Engine.height - 150);
		}
		
		g.dispose();
		bs.show();
	}

	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		
		if(k == e.VK_A){
			lvl.player.direction = 0;
			walking = true;
		}
		if(k == e.VK_D){
			lvl.player.direction = 1;
			walking = true;
		}
		if(k == e.VK_SPACE && lvl.player.isCollidingBottom){
			lvl.player.y -= 5;
			lvl.player.gravity = -5f;
		}
		if(k == e.VK_ENTER && STATE == 1){
			lvl = new Level();
			STATE = 0;
		}
		if(k == e.VK_DOWN && STATE == 0 && ut.open){
			ut.selection++;
		}
		if(k == e.VK_UP && STATE == 0 && ut.open){
			ut.selection--;
		}
		if(k == e.VK_ENTER && STATE == 0 && ut.open && ut.selection == 0 && Level.player.gold >= 1){
			Crawler.damage += 0.5f;
			Level.player.gold -= 1;
			System.out.println("Yoy!");
		}
		if(k == e.VK_ENTER && STATE == 0 && ut.open && ut.selection == 2 && Level.player.gold >= 1){
			lvl.ak47.frmod += 1;
			Level.player.gold -= 1;
		}
		if(k == e.VK_ENTER && STATE == 0 && ut.open && ut.selection == 1 && Level.player.gold >= 1){
			lvl.ak47.speedModifier = lvl.ak47.speedModifier / 2;
			Level.player.gold -= 1;
		}
	}

	public void keyReleased(KeyEvent e) {
		int k = e.getKeyCode();
		
		if(k == e.VK_A){
			lvl.player.direction = 2;
			walking = false;
		}
		if(k == e.VK_D){
			lvl.player.direction = 3;
			walking = false;
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		int worldX = x + (int) lvl.player.x - (Engine.width / 2) + (Engine.GRIDSIZE / 2);
		int worldY = y + (int) lvl.player.y - (Engine.height / 2) + (Engine.GRIDSIZE / 2);
		
		cursorCast = new Rectangle(x, y, 1, 1);
		lvl.ak47.cursorCast = new Rectangle(worldX, worldY, 1, 1);
		
		lvl.ak47.firing = true;
		
		if(cursorCast.intersects(upgradeTab) && !ut.open){
			ut.open = true;
		}else if(cursorCast.intersects(upgradeTab) && ut.open){
			ut.open = false;
		}
		
		//System.out.println(worldX + ", " + worldY);
	}

	public void mouseReleased(MouseEvent e) {
		lvl.ak47.firing = false;
		for(int i = 0; i < lvl.ak47.bullets.length; i++){
			lvl.ak47.bullets[i].x = Player.x;
			lvl.ak47.bullets[i].y = Player.y;
			lvl.ak47.bullets[i].degrees = 0;
		}
		
		lvl.ak47.firingTime = 0;
	}
	
}
