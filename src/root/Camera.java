package root;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.lwjgl.opengl.Display;

import rendering.Shape;



public class Camera {
	static private int W  = Const.W;
	static private int H  = Const.H;
	
	static private int ch = H;
	static private int cw = W;
	static private int cx = 0;
	static private int cy = 0;
	
	public static int getCX(){
		return cx;
	}
	public static int getCY(){
		return cy;
	}
	
	
	public static void create(){
        Display.update();
        Display.sync(60);
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
		glOrtho(cx, cw, ch, cy, 1, -1);
        
        glMatrixMode(GL_MODELVIEW);
	}
	
	public static void set(int x, int y){
		cx = x;
		cy = y;
		cw = x+W;
		ch = y+H;
	}
}