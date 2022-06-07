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
	Tile[] tile;
	int mapTileNum[][];

	public TileManager(Panel panel) {
		this.panel = panel;
		tile = new Tile [10];
		mapTileNum = new int[panel.MaxScreenColumn][panel.MaxScreenRow];

		getTileImage();
		loadMap();
	}

	public void getTileImage() {

		try {

			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap() {

		try {
			InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < panel.MaxScreenColumn && row < panel.MaxScreenRow) {

				String line = br.readLine();

				while (col < panel.MaxScreenColumn) {
					String numbers[] = line.split(" ");
					int number = Integer.parseInt(numbers[col]);

					mapTileNum[col][row] = number;
					col++;
				}
				if (col == panel.MaxScreenColumn) {
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

		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;

		while (col < panel.MaxScreenColumn && row < panel.MaxScreenRow ) {

			int tileNum = mapTileNum[col][row];

			g2.drawImage(tile[tileNum].image, x, y, panel.tileSize, panel.tileSize, null);
			col++;
			x += panel.tileSize;

			if (col == panel.MaxScreenColumn) {
				col = 0;
				x = 0;
				row++;
				y += panel.tileSize;	
			}
		}


	}

}
