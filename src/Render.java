/*
*	Render.java
*	Nathan Asdarjian
*	
*	Draws three-dimensional terrain using OpenSimplexNoise and a grid
*/

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;

public class Render
{
	float a;
	OpenSimplexNoise osn;
	
	public Render()
	{
		long l = System.currentTimeMillis();
		System.out.println("Seed: " + l);
		
		osn = new OpenSimplexNoise(l);
		a = 0; // motion variable
	}
	
	public void update()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		for (int i = 0; i < 100; i++)
		{
			glColor3f(0.3f,0.8f,1.0f);
			glBegin(GL_LINE_STRIP);
			for (int j = 0; j < 100; j++) 
				glVertex3f(i * 2f, (float) osn.eval(i/16f + a,j/16f,0f) * 16f, j * 2f);
			glEnd();
			glColor3f(1.0f,0.3f,0.8f);
			glBegin(GL_LINE_STRIP);
			for (int k = 0; k < 100; k++)
				glVertex3f(k * 2f, (float) osn.eval(k/16f + a,i/16f,0f) * 16f, i * 2f);
			glEnd();
		}	
		a += 0.005; // move noise over time; lower value to decrease speed
	}
}