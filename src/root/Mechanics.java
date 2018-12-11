package root;

import rendering.Shape;
import write.Font;

public class Mechanics {

		static int scale = 25;
		static int gap = scale/7;
		static int x = 10;
		static int y = 18;
		static int stage = 0;
		static Tile next, current;
		static int cxpos, cypos;
		static Block[] blockset;
		static Tile[] tileset;
		static char[][] board;
		
		public static void draw(){
			//bg
			Shape.RectCol(0, 0, Const.W, Const.H, 255, 255, 255);
			Font.writeString(600, 80, "Score: "+Integer.toString(Player.getScore()), 32);
			//board
			for(int i = 0;i < y;i++)
				for(int j = 0;j < x;j++)
					for(int k = 0;k < blockset.length;k++)
						if(blockset[k].col == board[i][j]) 
							blockset[k].draw(i*(scale+gap)+5, j*(scale+gap)+5);
							
			//next
			next.draw(5, 65+x*(scale+gap)+5);
			Font.writeString(600, 16, "width: "+Integer.toString(next.getw()), 32);
			Font.writeString(600, 48, "height: "+Integer.toString(next.geth()), 32);
			//current
			current.draw(cypos*(scale+gap)+5, cxpos*(scale+gap)+5);
			
		}
		public static void newBlock(){
			for(int i = cypos;i < cypos+current.geth();i++)
				for(int j = cxpos;j < cxpos+current.getw();j++)
					if(current.getShape()[j-cxpos][i-cypos])
						board[i][j] = current.getCol();
			
			current = next;
			cxpos = (int)(x/2-current.getw()/2);
			cypos = 0;
			next = tileset[(int)Math.floor(Math.random()*tileset.length)];
			chkLines();
		}
		
		public static void move(char dir){
			switch(dir){
				case 'V':
					if(chkTouch(dir))newBlock();
					else cypos++;
					
					break;
				case '<': 
					if(!chkTouch(dir))cxpos--;
					break;
				case '>':
					if(!chkTouch(dir))cxpos++;
					break;
				case '_':
					while(!chkTouch('V'))
						move('V');
					newBlock();
					break;
				case '^':
					cypos--;
					break;
			}
		}

		private static boolean chkTouch(char dir){
			switch(dir){
				case 'V':
					if(current.geth()+cypos == y)
						return true;
					for(int i = 0;i < current.getw();i++){
						int j = current.geth()-1;
						while(!current.getShape()[i][j])
							j--;
						if(board[cypos+j+1][cxpos+i] != '0')
							return true;
					}
					break;
				case '<':
					if(cxpos == 0)
						return true;
					for(int i = 0;i<current.geth();i++){
						int j = 0;
						while(!current.getShape()[j][i])
							j++;
						if(board[cypos+i][cxpos+j-1] != '0')
							return true;
					}
					break;
				case '>':
					if(current.getw()+cxpos == x)
						return true;
					for(int i = 0;i<current.geth();i++){
						int j = current.getw()-1;
						while(!current.getShape()[j][i])
							j--;
						if(board[cypos+i][cxpos+j+1] != '0')
							
							return true;
					}
					break;
			
			}
			return false;
		}
		private static void chkLines(){
			int count = 0;
			int sum = 0;
			for(int i = 0;i < y;i++){
				for(int j = 0;j < x;j++)
					if(board[i][j] != '0')
						sum++;
				if(sum == x){
					count++;
					for(int j = i;j >= 0;j--)
						for(int k = 0;k < x;k++)
							if(j!= 0)
								board[j][k] = board[j-1][k];
							else
								board[j][k] = '0';
				}
				sum = 0;
			}
			if(count > 0){
				Player.incScore(count);
				stageProg();
			}
		}
		public static void stageProg(){
			if(Player.getScore() > 50 + 50*stage){
				stage++;
				if(stage%4 == 0){
					y++;
					expandBoard(true);
				}
				if(stage%2 == 0){
					x++;
					expandBoard(false);
				}
				
			}
			
		}
		static void expandBoard(boolean dir){
			char b[][] = new char[y][x];
		
			for(int i = 0;i < y;i++)
				for(int j = 0;j < x;j++)
					b[i][j] = '0';
			
			for(int i = 0; i < board.length; i++)
				for(int j = 0;j < board[i].length;j++){
					if(dir)
						b[i+1][j] = board[i][j];
					else
						b[i][j] = board[i][j];
				} 
			board = b;
		}
		public static void load(){
			
			board = new char[y][x];
			for(int i = 0;i < y;i++)
				for(int j = 0;j < x;j++)
					board[i][j] = '0';
			
			try{
				tileset = Tile.load();

				blockset = Block.load();
			}
			catch(Exception e){
				System.out.println("Mechanics.load"+e);
			}
			current = tileset[(int)Math.floor(Math.random()*tileset.length)];
			cxpos = (int)(x/2-current.getw()/2);
			cypos = 0;
			next = tileset[(int)Math.floor(Math.random()*tileset.length)];
			
		}
}
