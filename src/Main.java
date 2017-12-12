/*
*	Main.java
*	Nathan Asdarjian
*
*	Handles starting up LWJGL and running the game loop
*/

import java.io.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;

public class Main
{
	public static final float WIDTH = 1280;
	public static final float HEIGHT = 720;

	private Camera c;
	private Render r;
	
	public Main()
	{
		c =  new Camera(-100f,-15f,-100f);
		r = new Render();
		try
		{
			init();
			while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			{
				c.pollInput();		// reads user input
				r.update();			// changes visual model to match
				Display.update();	// updates screen
				Display.sync(60);	// 60 frames per second
			}
			Display.destroy();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void init() throws Exception
	{
		Display.setFullscreen(false);
		DisplayMode displayMode = null;
		DisplayMode d[] = Display.getAvailableDisplayModes();
		for (DisplayMode d1 : d)
		{
			if (d1.getWidth() == WIDTH && d1.getHeight() == HEIGHT)
			{
				displayMode = d1;
				break;
			}
		}
		Display.setDisplayMode(displayMode);
		Display.setTitle("LWJGL Project Template");
		Display.create();
		Mouse.setGrabbed(true);
		
		glClearColor(0.1f, 0.2f, 0.5f, 0.0f);	// background color
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(90.0f, WIDTH / HEIGHT, 0.1f, 300.0f);
		
		glMatrixMode(GL_MODELVIEW);
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		
		glEnable(GL_DEPTH_TEST);
	}
	
	public static void main(String[] args)
	{
		new Main();
	}
}