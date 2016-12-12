package hud;

import java.awt.*;

import engine.*;
import utilities.*;

/*
 * THIS CODE WAS MADE BY TREVOR STRICKLAND ON DECEMBER 2016,
 * PLEASE DO NOT COPY OR REDISTRIBUTE ANY OF THE CODE FOUND
 * WITHIN THIS CLASS, OR ANY OTHER CLASS ASSOCIATED WITH IT.
 * USE FOR REVIEWING OR EDUCATIONAL PURPOSES ONLY.
 */

public class UpgradeTab {
	
	public boolean open = false;
	
	public int selection;
	
	public UpgradeTab(){
		selection = 0;
	}
	
	public void update(Graphics2D g){
		if(!open) g.drawImage(Images.upgradeTab[0], 0, Engine.GRIDSIZE * 3, Engine.GRIDSIZE, Engine.GRIDSIZE * 3, null);
		if(open) g.drawImage(Images.upgradeTab[1], 0, Engine.GRIDSIZE * 3, Engine.GRIDSIZE, Engine.GRIDSIZE * 3, null);
		
		g.setColor(Color.WHITE);
		if(selection <= -1) selection = 3;
		if(selection == 0 && open) g.drawRect(11, 228, 36, 33);
		if(selection == 1 && open) g.drawRect(11, 261, 36, 33);
		if(selection == 2 && open) g.drawRect(11, 294, 36, 33);
		if(selection == 3 && open) g.drawRect(11, 325, 36, 22);
		if(selection >= 4 && open) selection = 0;
	}

}
