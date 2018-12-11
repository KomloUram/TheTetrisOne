package write;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;


public class Font {
	private static int length = 200;
	private static String path ="d:/workspace/opengl research station/res/abc.fnt";
	private static String lines[] = new String[length];
	public static Symbol symb[] = new Symbol[length];	
	public static int scale = 64;
	public static int txSize = 512;//(scale*scale)/(scale/8);

	
	public static void LoadSymbols(){
		
		for(int i = 0;i<length;i++)
			symb[i]= new Symbol();
		
		BufferedReader br;
		int k=0;		
		try {
			String 	s= new String();						
			br = new BufferedReader(new FileReader(path));			
			while((s = br.readLine())!=null){
				lines[k]=s;
				k++;	
			}
			br.close();
		}
		catch (Exception e) {e.printStackTrace();}
		   	StringTokenizer st;
		   	for(int i = 4;i<k;i++){
			st = new StringTokenizer(lines[i]);
	   	   	int l = 0;
		   		while (st.hasMoreTokens()){
		   			if(l == 2)
	   					symb[i-3].id=Integer.parseInt(st.nextToken("="+" "));		   			
		   			else if(l == 4)
			   			symb[i-3].px=Integer.parseInt(st.nextToken("="+" "));
		   			else if(l == 6)
				   		symb[i-3].py=Integer.parseInt(st.nextToken("="+" "));
		   			else if(l == 8)
					   	symb[i-3].width=Integer.parseInt(st.nextToken("="+" "));
		   			else
		   				st.nextToken("="+" ");
//		   				System.out.println(st.nextToken("="+" "));
		   			l++;
		   		}
		   }
	}	
	
	public static void writeString(int x,int y, String s,int size){

		int width = 0;
		for(int i = 0;i<s.length();i++){
			int j = 0;
			while(s.charAt(i)!=symb[j].id){
				j++;	
				if(j>=95){
					j=0;
					break;
				}
			}
			drawSymbol(i*2+x+width/(scale/size),y,symb[j],size);
			width+=symb[j].width;
		}
	}
	
	public static void drawSymbol(int posX,int posY,Symbol symb, int desFontSize){
	
		glBindTexture(GL_TEXTURE_2D,1);
		glBegin(GL_QUADS);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_TEXTURE_2D);
		 
		float i=symb.px;
		float j=symb.py;
		
		glTexCoord2f(i/txSize, j/txSize);
		glVertex2i(posX, posY);
        
		glTexCoord2f(i/txSize, (j+scale)/txSize);
		glVertex2i(posX, posY+desFontSize);
        
		glTexCoord2f((i+symb.width)/txSize, (j+scale)/txSize);
		glVertex2i(posX+symb.width/(scale/desFontSize), posY+desFontSize);
        		
		glTexCoord2f((i+symb.width)/txSize, j/txSize);
		glVertex2i(posX+symb.width/(scale/desFontSize), posY);

        glEnd();
		
	}
	
	
}
