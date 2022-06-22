package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Panel;
import main.UtilityTool;

public class TileManager {

	Panel panel;
	public Tile[] tile;
	public int mapTileNum[][][];

	public TileManager(Panel panel) {

		this.panel = panel;
		tile = new Tile [50];
		mapTileNum = new int[panel.maxMap][panel.maxWorldCol][panel.maxWorldRow];

		getTileImage();

		loadMap("/maps/worldMap01.txt",0);
		loadMap("/maps/worldMap02.txt",1);
	}

	public void getTileImage() {

		setup(0,"pav1", false);
		setup(1,"pav2", false);
		setup(2,"pav3", false);
		setup(3,"pav4", false);
		setup(4,"pav5", false);
		setup(5,"pav6", false);
		setup(6,"pav7", false);

		setup(7,"wall1", true);
		setup(8,"wall2", true);
		setup(9,"wall3", true);
		setup(10,"wall4", true);
		setup(11,"wall5", true);
		setup(12,"wall6", true);
		setup(13,"wall7", true);
		setup(14,"wall8", true);

		setup(15,"int1", true);
		setup(16,"int3", true);
		setup(17,"int5", true);
		setup(18,"int6", true);
		setup(19,"int2", true);
		setup(20,"int4", true);

		setup(21,"inter1", true);
		setup(22,"inter2", true);
		setup(23,"pav8", false);
		setup(24,"inter3", true);
		setup(25,"inter4", true);

		setup(26,"gate_right", true); //gate right
		setup(27,"gate_left", true); //gate left

		setup(28,"portal", false);
	}

	public void setup(int index, String imageName, boolean collision) {

		UtilityTool uTool = new UtilityTool();

		try {

			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, panel.tileSize, panel.tileSize);
			tile[index].collision = collision;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String filePath, int map) {

		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < panel.maxWorldCol && row < panel.maxWorldRow) {

				String line = br.readLine();

				while (col < panel.maxWorldCol) {
					String numbers[] = line.split(" ");
					int number = Integer.parseInt(numbers[col]);

					mapTileNum[map][col][row] = number;
					col++;
				}
				if (col == panel.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2) {

		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < panel.maxWorldCol && worldRow < panel.maxWorldRow ) {

			int tileNum = mapTileNum[panel.currentMap][worldCol][worldRow];

			int worldX = worldCol * panel.tileSize;
			int worldY = worldRow * panel.tileSize;
			int screenX = worldX - panel.player.worldX + panel.player.screenX;
			int screenY = worldY - panel.player.worldY + panel.player.screenY;

			if (worldX + panel.tileSize > panel.player.worldX - panel.player.screenX &&
					worldX - panel.tileSize < panel.player.worldX + panel.player.screenX &&
					worldY + panel.tileSize > panel.player.worldY - panel.player.screenY &&
					worldY - panel.tileSize < panel.player.worldY + panel.player.screenY) {

				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}

			worldCol++;

			if (worldCol == panel.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}

}
