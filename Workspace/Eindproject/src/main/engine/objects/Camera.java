package main.engine.objects;

import org.lwjgl.glfw.GLFW;

import main.engine.io.Input;
import main.engine.math.Time;
import main.engine.math.Vector3;

public class Camera {
	public Vector3 position, rotation;

	private float oldMouseX, oldMouseY;
	private float moveSpeed = 1f, sensitivity = 0.15f;

	public Camera(Vector3 position, Vector3 rotation) {
		this.position = position;
		this.rotation = rotation;
	}

	public void update() {
		float dx = (float) (Input.mouseX - oldMouseX);
		float dy = (float) (Input.mouseY - oldMouseY);

		oldMouseX = (float) Input.mouseX;
		oldMouseY = (float) Input.mouseY;

		boolean running = Input.keyDown(GLFW.GLFW_KEY_LEFT_CONTROL);
		if (running) {
			moveSpeed = 2.5f;
		} else {
			moveSpeed = 1f;
		}

		if (rotation.x <= (90f - dy) && rotation.x >= (-90f - dy))
			rotation = Vector3.add(rotation, new Vector3(dy * sensitivity, 0, 0));
		else if(rotation.x > (90f - dy))
			rotation.x = 90f;
		else
			rotation.x = -90f;
		rotation = Vector3.add(rotation, new Vector3(0, dx * sensitivity, 0));
		System.out.println(rotation.x);

		float y = (float) (Math.sin(Math.toRadians(rotation.x)) * moveSpeed * Time.deltaTime);
		float sY = (float) (Math.cos(Math.toRadians(rotation.x)) * moveSpeed);

		float x = (float) (Math.cos(Math.toRadians(rotation.y)) * sY * Time.deltaTime);
		float z = (float) (Math.sin(Math.toRadians(rotation.y)) * sY * Time.deltaTime);

		if (Input.keyDown(GLFW.GLFW_KEY_A))
			position = Vector3.add(position, new Vector3(-x, 0, -z));
		if (Input.keyDown(GLFW.GLFW_KEY_D))
			position = Vector3.add(position, new Vector3(x, 0, z));
		if (Input.keyDown(GLFW.GLFW_KEY_S))
			position = Vector3.add(position, new Vector3(-z, y, x));
		if (Input.keyDown(GLFW.GLFW_KEY_W))
			position = Vector3.add(position, new Vector3(z, -y, -x));
		if (Input.keyDown(GLFW.GLFW_KEY_SPACE))
			position = Vector3.add(position, new Vector3(0, (float) (moveSpeed * Time.deltaTime), 0));
		if (Input.keyDown(GLFW.GLFW_KEY_LEFT_SHIFT))
			position = Vector3.add(position, new Vector3(0, (float) (-moveSpeed * Time.deltaTime), 0));
	}
}
