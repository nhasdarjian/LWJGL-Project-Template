/*
*	Camera.java
*	Nathan Asdarjian
*	
*	Handles reading user input and transforming user visuals based on input
*/

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Vector3f;
import static org.lwjgl.opengl.GL11.*;

public class Camera
{
	private Vector3f position = null;
	
	private float yaw = 0.0f; //45.0f; // camera starting horizontal angle
	private float pitch = 0.0f; //70.0f; // camera starting vertical angle
	
	float dx, dy;
	float mouseSensitivity = 0.09f;
	float moveSpeed = 0.4f;
	
	public Camera(float x, float y, float z)
	{
		this.position = new Vector3f(x, y, z);
	}
	
	public void yaw(float a)
	{
		this.yaw += a;
	}
	
	public void pitch(float a)
	{
		this.pitch -= a;
	}
	
	public void moveForwards(float distance)
	{
		float xOff = distance * (float) Math.sin(Math.toRadians(yaw));
		float zOff = distance * (float) Math.cos(Math.toRadians(yaw));
		
		position.x -= xOff;
		position.z += zOff;
	}
	
	public void moveBackwards(float distance)
	{
		float xOff = distance * (float) Math.sin(Math.toRadians(yaw));
		float zOff = distance * (float) Math.cos(Math.toRadians(yaw));
		
		position.x += xOff;
		position.z -= zOff;
	}
	
	public void moveLeft(float distance)
	{
		float xOff = distance * (float) Math.sin(Math.toRadians(yaw - 90));
        float zOff = distance * (float) Math.cos(Math.toRadians(yaw - 90));

        position.x -= xOff;
        position.z += zOff;
	}
	
	public void moveRight(float distance)
	{
		float xOff = distance * (float) Math.sin(Math.toRadians(yaw + 90));
        float zOff = distance * (float) Math.cos(Math.toRadians(yaw + 90));

        position.x -= xOff;
        position.z += zOff;
	}
	
	public void moveUp(float distance)
	{
		position.y -= distance;
	}
	
	public void moveDown(float distance)
	{
		position.y += distance;
	}
	
	public void lookThru()
	{
		glLoadIdentity();
		glRotatef(pitch, 1.0f, 0.0f, 0.0f);
		glRotatef(yaw, 0.0f, 1.0f, 0.0f);
		glTranslatef(position.x, position.y, position.z);
	}
	
	public void pollInput()
	{
		// FIRST PERSON
		dx = Mouse.getDX();
		dy = Mouse.getDY();
		this.yaw(dx * mouseSensitivity);
		this.pitch(dy * mouseSensitivity);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) this.moveForwards(moveSpeed);
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) this.moveBackwards(moveSpeed);
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) this.moveLeft(moveSpeed);
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) this.moveRight(moveSpeed);
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) this.moveUp(moveSpeed);
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) this.moveDown(moveSpeed);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))  this.yaw(-mouseSensitivity * 5);
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) this.yaw(mouseSensitivity * 5);
		if (Keyboard.isKeyDown(Keyboard.KEY_UP) && pitch > 40) this.pitch(mouseSensitivity * 5);
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) && pitch < 80) this.pitch(-mouseSensitivity * 5);
		
		this.lookThru();
	}
	
	public Vector3f getPos()
	{
		return this.position;
	}
}