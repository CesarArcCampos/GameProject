package object;

import entity.Entity;
import main.Panel;

public class MedicKit extends Entity{
	
	Panel panel;
	int value = 5;

	public MedicKit(Panel panel) {
		super(panel);
		
		this.panel = panel;
		
		type = type_consumable;
		name = "Medicine";
		down1 = setup("/object/medickit",panel.tileSize,panel.tileSize);
		description = "(Medicine)\nHeals your life by" + value + ".";
				
	}
	
	public void use(Entity entity) {
		
		panel.gameState = panel.warningState;
		panel.ui.currentDialogue = "You took the " + name +
			"!\n" + "Your life as been recovered\n by" + value + ".";
		entity.life += value;
		
		if (panel.player.life > panel.player.maxLife) {
			panel.player.life = panel.player.maxLife;
		}
		
		panel.playSFX(7);
	}

}
