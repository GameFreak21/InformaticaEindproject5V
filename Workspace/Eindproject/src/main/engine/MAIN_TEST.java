package main.engine;

import org.lwjgl.glfw.GLFW;

import main.engine.io.Input;
import main.engine.io.Window;
import main.engine.math.Matrix;
import main.engine.math.Time;
import main.engine.math.Vector3;
import main.engine.objects.Camera;
import main.engine.objects.GameObject;
import main.engine.renderer.graphics.Renderer;
import main.engine.renderer.graphics.Shader;
import main.engine.renderer.primitives.*;
import main.engine.util.ModelLoader;

public class MAIN_TEST {
	static Window window = new Window("test", 720, 600);
	static Renderer renderer;
	static Shader shader;

	static Matrix matrix = new Matrix();

//	static Box[] boxs = {
//			new Box(new Vector3(), new Vector3(), new Vector3(3,1,1)),
//			new Box(new Vector3(0,0,1), new Vector3(), new Vector3(1,1,1)),
//			new Box(new Vector3(-2,0.5f,0.5f), new Vector3(), new Vector3(1,2,2)),
//	};
//	static Wedge[] wedges = {
//			new Wedge(new Vector3(2,0,0), new Vector3(), new Vector3(1,1,1)),
//			new Wedge(new Vector3(1,0,2f), new Vector3(0,-90,0), new Vector3(1,1,3)),
//			new Wedge(new Vector3(-1,0,1.25f), new Vector3(0,-90,0), new Vector3(1,1,1.5f)),
//			new Wedge(new Vector3(0,0,2), new Vector3(0,-90,0), new Vector3(1,1,1)),
//			new Wedge(new Vector3(0,1,0), new Vector3(), new Vector3(3,1,1)),
//	};
//	static Pyramid[] pyramids = {
//			new Pyramid(new Vector3(0,1,1), new Vector3(), new Vector3(1,1,1)),
//			new Pyramid(new Vector3(-2,1.75f,0.5f), new Vector3(), new Vector3(1,0.5f,2)),
//	};
	static Box box;
//	static GameObject yoda = new GameObject(new Vector3(0,0,0), new Vector3(), new Vector3(0.1f), ModelLoader.LoadModel("resources/Models/Baby_Yoda.obj"));
	static GameObject pika;
	static Quad quad;
	// static Box box = new Box(new Vector3(), new Vector3(), new
	// Vector3(0.5f,0.5f,0.5f));
	// static Pyramid box = new Pyramid(new Vector3(0,0.5f,0), new Vector3(), new
	// Vector3(1.0f,1.0f,1.0f));
	// static Wedge box = new Wedge(new Vector3(), new Vector3(), new
	// Vector3(1.0f,1.0f,1.0f));

	static Camera camera = new Camera(new Vector3(0, 0, 2), new Vector3());

	public static void main(String[] args) {
		window.create();
		box = new Box(new Vector3(0, 0, 0), new Vector3(), new Vector3(0.5f, 0.5f, 0.5f));
		pika = new GameObject(new Vector3(2, 0, 0), new Vector3(), new Vector3(0.01f, 0.01f, 0.01f),
				ModelLoader.LoadModel("resources/Models/Pikachu.stl", "resources/textures/a.jpg"));
		quad = new Quad(new Vector3(0, 0, 0), new Vector3(), new Vector3(1, 1, 1));
		
		
//		for(int i = 0; i < boxs.length; i++) {
//			boxs[i].gameObject.mesh.create();
//		}
//		for(int i = 0; i < wedges.length; i++) {
//			wedges[i].gameObject.mesh.create();
//		}
//		for(int i = 0; i < pyramids.length; i++) {
//			pyramids[i].gameObject.mesh.create();
//		}
		box.gameObject.mesh.create();
//		yoda.mesh.create();
		pika.mesh.create();
		quad.gameObject.mesh.create();
		shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");
		renderer = new Renderer(window, shader);
		shader.create();

		while (!GLFW.glfwWindowShouldClose(window.window)) {
			window.update();

			// System.out.println("FPS : " + (int)(1f/Time.deltaTime));

			if (Input.keyDown(GLFW.GLFW_KEY_F11)) {
				window.lockCursor(true);
				camera.lock(false);
			}
			if (Input.keyDown(GLFW.GLFW_KEY_F10)) {
				window.lockCursor(false);
				camera.lock(true);
			}
			
			if (Input.keyDown(GLFW.GLFW_KEY_F6)) { // Reload shaders
				shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");
				renderer = new Renderer(window, shader);
				shader.create();
			}

			camera.update();
			// box.gameObject.update();
//			for(int i = 0; i < boxs.length; i++) {
//				renderer.renderMesh(boxs[i].gameObject, camera);
//			}
//			for(int i = 0; i < wedges.length; i++) {
//				renderer.renderMesh(wedges[i].gameObject, camera);
//			}
//			for(int i = 0; i < pyramids.length; i++) {
//				renderer.renderMesh(pyramids[i].gameObject, camera);
//			}
			renderer.renderMesh(box.gameObject, camera);
//			renderer.renderMesh(yoda, camera);
			renderer.renderMesh(pika, camera);
//			renderer.renderMesh(quad.gameObject, camera);
			window.swapBuffers();
		}
//		for(int i = 0; i < boxs.length; i++) {
//			boxs[i].gameObject.mesh.destroy();
//		}
//		for(int i = 0; i < wedges.length; i++) {
//			wedges[i].gameObject.mesh.destroy();
//		}
//		for(int i = 0; i < pyramids.length; i++) {
//			pyramids[i].gameObject.mesh.destroy();
//		}
//		yoda.mesh.destroy();
		pika.mesh.destroy();

		shader.destroy();
		window.kill();
	}
}
