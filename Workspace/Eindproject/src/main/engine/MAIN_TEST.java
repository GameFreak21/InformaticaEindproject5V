package main.engine;

import org.lwjgl.glfw.GLFW;

import main.engine.io.Input;
import main.engine.io.Window;
import main.engine.math.Matrix;
import main.engine.math.Time;
import main.engine.math.Vector3;
import main.engine.objects.Camera;
import main.engine.renderer.graphics.Renderer;
import main.engine.renderer.graphics.Shader;
import main.engine.renderer.primitives.*;


public class MAIN_TEST {
	static Window window = new Window("test", 720, 600);
	static Renderer renderer;
	static Shader shader;

	static Matrix matrix = new Matrix();
	
	static Box[] boxs = {
			new Box(new Vector3(), new Vector3(), new Vector3(3,1,1)),
			new Box(new Vector3(0,0,1), new Vector3(), new Vector3(1,1,1)),
			new Box(new Vector3(-2,0.5f,0.5f), new Vector3(), new Vector3(1,2,2)),
	};
	static Wedge[] wedges = {
			new Wedge(new Vector3(2,0,0), new Vector3(), new Vector3(1,1,1)),
			new Wedge(new Vector3(1,0,2f), new Vector3(0,-90,0), new Vector3(1,1,3)),
			new Wedge(new Vector3(-1,0,1.25f), new Vector3(0,-90,0), new Vector3(1,1,1.5f)),
			new Wedge(new Vector3(0,0,2), new Vector3(0,-90,0), new Vector3(1,1,1)),
			new Wedge(new Vector3(0,1,0), new Vector3(), new Vector3(3,1,1)),
	};
	static Pyramid[] pyramids = {
			new Pyramid(new Vector3(0,1,1), new Vector3(), new Vector3(1,1,1)),
			new Pyramid(new Vector3(-2,1.75f,0.5f), new Vector3(), new Vector3(1,0.5f,2)),
	};
	
	//static Box box = new Box(new Vector3(), new Vector3(), new Vector3(0.5f,0.5f,0.5f));
	//static Pyramid box = new Pyramid(new Vector3(0,0.5f,0), new Vector3(), new Vector3(1.0f,1.0f,1.0f));
	//static Wedge box = new Wedge(new Vector3(), new Vector3(), new Vector3(1.0f,1.0f,1.0f));

	static Camera camera = new Camera(new Vector3(0,0,2), new Vector3());
	
	public static void main(String[] args) {
		window.create();
		for(int i = 0; i < boxs.length; i++) {
			boxs[i].gameObject.mesh.create();
		}
		for(int i = 0; i < wedges.length; i++) {
			wedges[i].gameObject.mesh.create();
		}
		for(int i = 0; i < pyramids.length; i++) {
			pyramids[i].gameObject.mesh.create();
		}
		
		shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");
		renderer = new Renderer(window, shader);
		shader.create();

		while (!GLFW.glfwWindowShouldClose(window.window)) {
			window.update();

			System.out.println("FPS : " + (int)(1f/Time.deltaTime));
			
			if(Input.keyDown(GLFW.GLFW_KEY_F11)) window.lockCursor(true);
			if(Input.keyDown(GLFW.GLFW_KEY_F10)) window.lockCursor(false);
			
			camera.update();
			//box.gameObject.update();
			for(int i = 0; i < boxs.length; i++) {
				renderer.renderMesh(boxs[i].gameObject, camera);
			}
			for(int i = 0; i < wedges.length; i++) {
				renderer.renderMesh(wedges[i].gameObject, camera);
			}
			for(int i = 0; i < pyramids.length; i++) {
				renderer.renderMesh(pyramids[i].gameObject, camera);
			}
			
			window.swapBuffers();
		}
		for(int i = 0; i < boxs.length; i++) {
			boxs[i].gameObject.mesh.destroy();
		}
		for(int i = 0; i < wedges.length; i++) {
			wedges[i].gameObject.mesh.destroy();
		}
		for(int i = 0; i < pyramids.length; i++) {
			pyramids[i].gameObject.mesh.destroy();
		}
		shader.destroy();
		window.kill();
	}
}
