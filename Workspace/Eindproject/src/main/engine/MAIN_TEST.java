package main.engine;

import org.lwjgl.glfw.GLFW;

import main.engine.io.Input;
import main.engine.io.Window;
import main.engine.math.Matrix;
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
	
	static Box[] scene = {
			new Box(new Vector3(), new Vector3(), new Vector3(3,1,1)),
			new Box(new Vector3(2,0,0), new Vector3(), new Vector3(1,1,1)),
			//new Box(new Vector3(), new Vector3(), new Vector3()),
	};
	
	//static Box box = new Box(new Vector3(), new Vector3(), new Vector3(0.5f,0.5f,0.5f));
	static Pyramid box = new Pyramid(new Vector3(0,0.5f,0), new Vector3(), new Vector3(1.0f,1.0f,1.0f));
	//static Wedge box = new Wedge(new Vector3(), new Vector3(), new Vector3(1.0f,1.0f,1.0f));

	static Camera camera = new Camera(new Vector3(0,0,2), new Vector3());
	
	static int frames;
	static long time = System.currentTimeMillis();
	
	public static void main(String[] args) {
		window.create();
		for(int i = 0; i < scene.length; i++) {
			scene[i].gameObject.mesh.create();
		}
		box.gameObject.mesh.create();
		
		shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");
		renderer = new Renderer(window, shader);
		shader.create();

		while (!GLFW.glfwWindowShouldClose(window.window)) {
			window.update();

			frames++; // FPS
			if (System.currentTimeMillis() > time + 1000) {
				System.out.println(frames);
				time = System.currentTimeMillis();
				frames = 0;
			}
			
			if(Input.keyDown(GLFW.GLFW_KEY_F11)) window.lockCursor(true);
			if(Input.keyDown(GLFW.GLFW_KEY_F10)) window.lockCursor(false);
			
			camera.update();
			//box.gameObject.update();
			for(int i = 0; i < scene.length; i++) {
				renderer.renderMesh(scene[i].gameObject, camera);
			}
			renderer.renderMesh(box.gameObject, camera);
			window.swapBuffers();
		}
		for(int i = 0; i < scene.length; i++) {
			scene[i].gameObject.mesh.destroy();
		}
		box.gameObject.mesh.destroy();
		shader.destroy();
	}
}
