package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {

	Panel panel;
	
	public Config(Panel panel) {
		
		this.panel = panel;
	}
	
	public void saveConfig() {
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
			
			//Music volume
			bw.write(String.valueOf(panel.music.volumeScale));
			bw.newLine();
			
			//SFX volume
			bw.write(String.valueOf(panel.sfx.volumeScale));
			bw.newLine();
			
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadConfig() {
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("config.txt"));
			
			String s = br.readLine();
			
			//Music volume
			panel.music.volumeScale = Integer.parseInt(s);
			
			//SFX volume
			s = br.readLine();
			panel.sfx.volumeScale = Integer.parseInt(s);
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
