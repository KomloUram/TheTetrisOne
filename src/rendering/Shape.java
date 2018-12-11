package rendering;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

public class Shape {
	
	private static void SetTexture(float fx,float fy,int fcu){
	
		fx=((fcu%((float)TextureLoad.getIW()/(float)TextureLoad.getTW())+fx)*(1/((float)TextureLoad.getIW()/(float)TextureLoad.getTW())));		
		fy=((fcu/(TextureLoad.getIH()/TextureLoad.getTH())+fy)*(1/((float)TextureLoad.getIH()/(float)TextureLoad.getTH())));
		
//		System.out.println(fx+" "+fy);
		glTexCoord2f(fx, fy);
	}
	private static void setCol(float r, float g, float b){
		
		if(r != 0)
			r /= 255;
		if(g != 0)
			g /= 255;
		if(b != 0)
			b /= 255;
		glColor3f(r, g, b);
	}	
	public static void RectTex (int px, int py, int w, int h, int fcu ,int fid){
		glColor3f(1,1,1);

		glBindTexture(GL_TEXTURE_2D,fid);

		glBegin(GL_QUADS);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_TEXTURE_2D);		

			SetTexture(0f,0f,fcu);
			glVertex2i(px, py);
	        
			SetTexture(0f,1f,fcu);
			glVertex2i(px, py+h);
	        
			SetTexture(1f,1f,fcu);
			glVertex2i(px+w, py+h);
	        
			SetTexture(1f,0f,fcu);
			glVertex2i(px+w, py);

		glEnd();

	}
	
	public static void RectCol (int py, int px, int w, int h, int r, int g, int b){
		glBindTexture(GL_TEXTURE_2D,0);
		setCol(r,g,b);
		glBegin(GL_QUADS);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_TEXTURE_2D);
		
			glVertex2i(px, py);	        
			glVertex2i(px+w, py);	        
			glVertex2i(px+w, py+h);
			glVertex2i(px, py+h);

		glEnd();
	}
	public static void LineCol (int px, int py, int w, int h, int r, int g, int b){
		glBindTexture(GL_TEXTURE_2D,0);
		setCol(r,g,b);
		glBegin(GL_LINES);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_TEXTURE_2D);
					
			glVertex2i(px, py);
			glVertex2i(px+w, py+h);

		glEnd();
	}
	
}
