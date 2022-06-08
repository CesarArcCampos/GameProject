package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Panel;

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

		try {

			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bush.png"));
			tile[2].collision = true;

			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tile[3].collision = true;

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
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, panel.tileSize, panel.tileSize, null);
			}
			
			worldCol++;

			if (worldCol == panel.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}


	}

}
