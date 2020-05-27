package main.engine.objects;

import org.lwjgl.glfw.GLFW;

import main.engine.MAIN_TEST;
import main.engine.io.Input;
import main.engine.math.Time;
import main.engine.math.Vector3;
import main.engine.physics.Collider;
import main.engine.renderer.graphics.MasterRenderer;

public class PlayerCamera extends Camera {
	private float oldMouseX, oldMouseY;
	private float moveSpeed = 10f, sensitivity = 0.15f;
	private Vector3 oldPosition, Old2position;

	public PlayerCamera(Vector3 position, Vector3 rotation) {
		super(position, rotation);
		this.position = position;
		this.rotation = rotation;
	}

	@Override
	public void update() {
		float dx = (float) (Input.mouseX - oldMouseX);
		float dy = (float) (Input.mouseY - oldMouseY);

		oldMouseX = (float) Input.mouseX;
		oldMouseY = (float) Input.mouseY;
		
		
		if (!lockCam) {
			oldPosition = Old2position;
			Old2position = position;
			boolean running = Input.keyDown(GLFW.GLFW_KEY_LEFT_CONTROL);
			if (running) {
				moveSpeed = 25f;
			} else {
				moveSpeed = 10f;
			}

			if (rotation.x <= (90f - dy) && rotation.x >= (-90f - dy))
				rotation = Vector3.add(rotation, new Vector3(dy * sensitivity, 0, 0));
			else if (rotation.x > (90f - dy))
				rotation.x = 90f;
			else
				rotation.x = -90f;
			rotation = Vector3.add(rotation, new Vector3(0, dx * sensitivity, 0));

			float x = (float) (Math.cos(Math.toRadians(rotation.y)) * moveSpeed * Time.deltaTime);
			float z = (float) (Math.sin(Math.toRadians(rotation.y)) * moveSpeed * Time.deltaTime);

			if (Input.keyDown(GLFW.GLFW_KEY_A)) {
				if (!Collider.CheckCollision(MAIN_TEST.playerCollider, MAIN_TEST.allCollider, this.position, MAIN_TEST.allGameObjectpositions)) {			
					position = Vector3.add(position, new Vector3(-x, 0, -z));
					System.out.println("geen collision!");}
				else 
					position = oldPosition;}
			if (Input.keyDown(GLFW.GLFW_KEY_D)) {
				if (!Collider.CheckCollision(MAIN_TEST.playerCollider, MAIN_TEST.allCollider, this.position, MAIN_TEST.allGameObjectpositions)) {			
					position = Vector3.add(position, new Vector3(x, 0, z));
					System.out.println("geen collision!");}
			else
				position = oldPosition;}
			if (Input.keyDown(GLFW.GLFW_KEY_S)) {
				if (!Collider.CheckCollision(MAIN_TEST.playerCollider, MAIN_TEST.allCollider, this.position, MAIN_TEST.allGameObjectpositions)) {			
					position = Vector3.add(position, new Vector3(-z, 0, x));
					System.out.println("geen collision!");}
			else
				position = oldPosition;}

			if (Input.keyDown(GLFW.GLFW_KEY_W)) {
				if (!Collider.CheckCollision(MAIN_TEST.playerCollider, MAIN_TEST.allCollider, this.position, MAIN_TEST.allGameObjectpositions)) {			
					position = Vector3.add(position, new Vector3(z, 0, -x));
					System.out.println("geen collision!");}
				else {
					position = oldPosition;
					System.out.println("collision!");}}
			System.out.println(this.position.z);
			

			
			//JUMPING @ GRAVITY ETC
			
			if (Input.keyDown(GLFW.GLFW_KEY_SPACE)) {
				if (!Collider.CheckCollision(MAIN_TEST.playerCollider, MAIN_TEST.allCollider, this.position, MAIN_TEST.allGameObjectpositions)) {			
					position = Vector3.add(position, new Vector3(0, (float) (moveSpeed * Time.deltaTime), 0));
					System.out.println("geen collision!");}
				else 
					position = oldPosition;}
				
			if (Input.keyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) {
				if (!Collider.CheckCollision(MAIN_TEST.playerCollider, MAIN_TEST.allCollider, this.position, MAIN_TEST.allGameObjectpositions)) {			
					position = Vector3.add(position, new Vector3(0, (float) (-moveSpeed * Time.deltaTime), 0));
					System.out.println("geen collision!");}
				else 
					position = oldPosition;}
			
			
		}	
		}
	
	
	@Override
	public void lock(Boolean lock) {
		this.lockCam = (lock ? true : false);
	}
}

