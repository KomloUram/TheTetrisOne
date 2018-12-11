package root;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import rendering.Shape;

public class Block {
	char col;
	int r;
	int g;
	int b;
	Block(int r, int g, int b, char col){
		this.r = r;
		this.b = b;
		this.g = g;
		this.col = col;
	}
	public void draw(int px, int py){
		Shape.RectCol(px, py, Mechanics.scale, Mechanics.scale, r, g, b);
	}
	public static Block[] load() throws Exception{
		
		BufferedReader br = new BufferedReader(new FileReader("res/tiles.txt"));
		Block[] blocks = new Block[Integer.parseInt(br.readLine())+1];
		blocks[0] = new Block(224,224,224,'0');
		String line = br.readLine();
		int count = 1;
		while(line != null){
			String[] words = line.split(" ");
			switch(words[0].charAt(0)){
				case 'r':blocks[count] = new Block(192,0,0,words[0].charAt(0));count++;break;
				case 'g':blocks[count] = new Block(0,192,0,words[0].charAt(0));count++;break;
				case 'b':blocks[count] = new Block(0,0,192,words[0].charAt(0));count++;break;
				case 'y':blocks[count] = new Block(192,192,0,words[0].charAt(0));count++;break;
				case 'o':blocks[count] = new Block(192,96,0,words[0].charAt(0));count++;break;
				case 'p':blocks[count] = new Block(192,0,192,words[0].charAt(0));count++;break;
				case 't':blocks[count] = new Block(0,192,255,words[0].charAt(0));count++;break;
				default:break;
			}
			line = br.readLine();
		}
		br.close();
		return blocks;
	}
}
