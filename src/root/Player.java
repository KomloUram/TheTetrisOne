package root;

import org.lwjgl.input.Keyboard;

public class Player {
	
	static boolean isheld = false;
	static int score = 0;
	public static void incScore(int lines){
		score += (Mechanics.y - Mechanics.cypos) + Mechanics.x*lines + Mechanics.x*lines/2 ;
	}
	public static int getScore(){
		return score;
	}
	public static void control(){
		
		Keyboard.next();
		if(isheld && Keyboard.getEventKeyState())
			return;
		else if(isheld && !Keyboard.getEventKeyState()){
			isheld = false;
			return;
		}
		else if(!isheld && Keyboard.getEventKeyState()){
			isheld = true;
			System.out.println("--- Player.control");
			System.out.println(Keyboard.getKeyName(Keyboard.getEventKey()));
			if(!TetrisMain.paused)
				keypress(Keyboard.getEventKey());
			else
				TetrisMain.paused = false;
		}
	}
	
	static void keypress(int key){

		if(key == Keyboard.KEY_R)
			Mechanics.load();
		
		if(key == Keyboard.KEY_W){
			Mechanics.current.rotate();
			if(Mechanics.current.getw()+Mechanics.cxpos > Mechanics.x)
				Mechanics.move('<');
			else if(Mechanics.cxpos < 0)
				Mechanics.move('>');
			if(Mechanics.current.geth()+Mechanics.cypos > Mechanics.y)
				Mechanics.move('^');
		}
		if(key == Keyboard.KEY_A)
			Mechanics.move('<');
		else if(key == Keyboard.KEY_D)
			Mechanics.move('>');
		if(key == Keyboard.KEY_S) 
    		Mechanics.move('V');

		if(key == Keyboard.KEY_SPACE) 
    		Mechanics.move('_');

		if(key == Keyboard.KEY_P)
			TetrisMain.paused = true;
		
		if(key == Keyboard.KEY_ADD){
			Mechanics.scale++;
			Mechanics.gap = Mechanics.scale/7;
		}
		else if(key == Keyboard.KEY_SUBTRACT){
			Mechanics.scale--;
			Mechanics.gap = Mechanics.scale/7;
		}
	}
	
}