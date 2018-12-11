package root;

import java.io.BufferedReader;
import java.io.FileReader;

import rendering.Shape;

public class Tile {

	private int w,h;
	private boolean[][] body;
	private char color;
	
	Tile(boolean[][] body,char color, int w, int h){
		this.w = w;
		this.h = h;
		this.body = body;
		this.color = color;
	}
	
	public char getCol(){
		return color;
	}
	public int getw(){
		return w;
	}
	public int geth(){
		return h;
	}
	public boolean[][] getShape(){
		return body;
	}
	
	public void rotate(){
		boolean[][] rotb = new boolean[h][w];
		for(int i = 0;i < h;i++)
			for(int j = 0;j < w;j++)
				rotb[i][j] = body[w-j-1][i];
		int s = h;
		h = w;
		w = s;
		body = rotb;
	}
	public void draw(int x, int y){
		for(int i = 0;i < h;i++)
			for(int j = 0;j < w;j++)
				if(body[j][i])
					for(int k = 0;k <  Mechanics.blockset.length;k++)
						if(Mechanics.blockset[k].col == color)
							Mechanics.blockset[k].draw(x+i*(Mechanics.scale+Mechanics.gap), y+j*(Mechanics.scale+Mechanics.gap));
						
	}
	static Tile[] load() throws Exception{
		
		BufferedReader br = new BufferedReader(new FileReader("res/tiles.txt"));
		Tile[] tiles = new Tile[Integer.parseInt(br.readLine())];
		int w,h,count = 0;
		char c;
		boolean[][] body;
		String line = br.readLine();
		while(line != null){
			String[] words = line.split(" ");
			c = words[0].charAt(0);
			w = Integer.parseInt(words[1]);
			h = Integer.parseInt(words[2]);
			body = new boolean[w][h];
			for(int i = 0;i < h;i++){
				words = br.readLine().split(" ");
				for(int j = 0;j < w;j++)
					if(words[j].equals("1"))
						body[j][i] = true;
					else
						body[j][i] = false;
			}
			tiles[count] = new Tile(body,c,w,h);
			count++;
			line = br.readLine();
		}
		br.close();
		return tiles;
	}
}
