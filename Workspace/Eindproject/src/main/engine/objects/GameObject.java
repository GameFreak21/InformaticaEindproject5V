package main.engine.objects;

import org.lwjgl.glfw.GLFW;

import main.engine.io.Input;
import main.engine.math.Vector3;
import main.engine.renderer.graphics.Mesh;

public class GameObject {
	public Vector3 position, rotation, scale;
	public Mesh mesh;
	
	double time;
	public GameObject(Vector3 position, Vector3 rotation, Vector3 scale, Mesh mesh) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.mesh = mesh;
	}

	// temp voor test
	public void update() {
		time = GLFW.glfwGetTime();
		if(Input.keyDown(GLFW.GLFW_KEY_D)) {
			position.x += 1f/60f;
		}
		if(Input.keyDown(GLFW.GLFW_KEY_A)) {
			position.x -= 1f/60f;
		}
		if(Input.keyDown(GLFW.GLFW_KEY_E)) {
			rotation = new Vector3(rotation.x + 40f/60f, rotation.y + 40f/60f, rotation.z + 40f/60f);
		}
		if(Input.keyDown(GLFW.GLFW_KEY_Q)) {
			rotation = new Vector3(rotation.x - 40f/60f, rotation.y - 40f/60f, rotation.z - 40f/60f);
		}
		if(Input.keyDown(GLFW.GLFW_KEY_W)) {
			scale = new Vector3(scale.x - 0.5f/60f, scale.y - 0.5f/60f, scale.z - 0.5f/60f);
		}
		if(Input.keyDown(GLFW.GLFW_KEY_S)) {
			scale = new Vector3(scale.x + 0.5f/60f, scale.y + 0.5f/60f, scale.z + 0.5f/60f);
		}
		position.y = 0.1f*(float)Input.scrollY;
		position.x = 0.1f*(float)Input.scrollX;
		
		//position.x = (float) Math.sin(time)/2.0f;
		//rotation = new Vector3(0, (float) time * 60, (float) time * 20);
		//scale = new Vector3((float) Math.sin(time), (float) Math.sin(time), (float) Math.sin(time));
	}
	
}
