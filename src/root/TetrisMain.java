package root;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

import rendering.TextureLoad;
import write.Font;

public class TetrisMain {

	public static int ticks = 0;
	public static boolean paused = false;
	
		public static void main(String[] args){
		try {
    	  	Display.setDisplayMode(new DisplayMode(Const.W, Const.H));
            Display.setTitle("Opengl Research Station");

            Display.create();
        } catch (LWJGLException e) {
            System.err.println("Display wasn't initialized correctly.");
            System.exit(1);
        } 	    
		

		preload();
             
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND); 
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDisable(GL_LIGHTING);
        
		while (!Display.isCloseRequested()) {
            Camera.create();
            Mechanics.draw();
            
            if(paused){
            	Font.writeString(Const.W/2, Const.H/2, "paused", 32);
            	Player.control();
            	continue;
            }

            Player.control();
            if(ticks%30 == 0)
            	Mechanics.move('V');
            
    		glEnd();
            ticks++;
            if(ticks == 360)
            	ticks = 0;
		}
 
        Display.destroy();
        System.exit(0);
    }
		
	private static void preload(){

		Mechanics.load();
	    TextureLoad.LoadAll();
	    Font.LoadSymbols();
		
	}
}


