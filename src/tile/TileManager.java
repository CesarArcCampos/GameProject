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
	public int mapTileNum[][];

	public TileManager(Panel panel) {

		this.panel = panel;
		tile = new Tile [10];
		mapTileNum = new int[panel.maxWorldCol][panel.maxWorldRow];

		getTileImage();

		loadMap("/maps/worldMap01.txt");
	}

	public void getTileImage() {

		setup(0,"grass", false);
		setup(1,"wall", true);
		setup(2,"bush", true);
		setup(3,"tree", true);
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

	public void loadMap(String filePath) {

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

					mapTileNum[col][row] = number;
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

			int tileNum = mapTileNum[worldCol][worldRow];

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
