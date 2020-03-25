package main.engine;

import org.lwjgl.glfw.GLFW;

import main.engine.io.Window;
import main.engine.math.Matrix;
import main.engine.math.Vector3;
import main.engine.objects.GameObject;
import main.engine.renderer.graphics.Mesh;
import main.engine.renderer.graphics.Vertex;
import main.engine.renderer.graphics.Renderer;
import main.engine.renderer.graphics.Shader;

public class MAIN_TEST {
	static Window window = new Window("test", 720, 600);
	static Renderer renderer;
	static Shader shader;

	static Matrix matrix = new Matrix();

	static Mesh mesh = new Mesh(
			new Vertex[] { 
					//front
					new Vertex(new Vector3(-0.5f, 0.5f, 0.0f), new Vector3(1.0f, 0.0f, 0.0f)),
					new Vertex(new Vector3(0.5f, 0.5f, 0.0f), new Vector3(0.0f, 1.0f, 0.0f)),
					new Vertex(new Vector3(0.5f, -0.5f, 0.0f), new Vector3(0.0f, 0.0f, 1.0f)),
					new Vertex(new Vector3(-0.5f, -0.5f, 0.0f), new Vector3(0.5f, 0.0f, 0.5f)),
					
					//right
					new Vertex(new Vector3(0.5f,0.5f,0.0f), new Vector3(1.0f,0.0f,0.0f)),
					new Vertex(new Vector3(0.5f,-0.5f,0.0f), new Vector3(0.0f,1.0f,0.0f)),
					new Vertex(new Vector3(0.5f,0.5f,1.0f), new Vector3(0.0f,0.0f,1.0f)),
					new Vertex(new Vector3(0.5f,-0.5f,1.0f), new Vector3(0.5f,0.0f,0.5f)),
					
					//back
					new Vertex(new Vector3(-0.5f, 0.5f, 1.0f), new Vector3(1.0f, 0.0f, 0.0f)),
					new Vertex(new Vector3(0.5f, 0.5f, 1.0f), new Vector3(0.0f, 1.0f, 0.0f)),
					new Vertex(new Vector3(0.5f, -0.5f, 1.0f), new Vector3(0.0f, 0.0f, 1.0f)),
					new Vertex(new Vector3(-0.5f, -0.5f, 1.0f), new Vector3(0.5f, 0.0f, 0.5f)),
					
					//left
					new Vertex(new Vector3(-0.5f,0.5f,0.0f), new Vector3(1.0f,0.0f,0.0f)),
					new Vertex(new Vector3(-0.5f,-0.5f,0.0f), new Vector3(0.0f,1.0f,0.0f)),
					new Vertex(new Vector3(-0.5f,0.5f,1.0f), new Vector3(0.0f,0.0f,1.0f)),
					new Vertex(new Vector3(-0.5f,-0.5f,1.0f), new Vector3(0.5f,0.0f,0.5f))
				},
			new int[] { 
					//front
					0, 1, 2, // triangles for rendering , top left, top right, bot right
					0, 3, 2,
					
					//right
					4, 5, 6,
					6, 7, 5,
					
					//back
					8, 9, 10,
					8, 11, 10,
					
					//left
					12, 13, 14,
					14, 15, 13
				});

	static int frames;
	static long time = System.currentTimeMillis();

	static GameObject object = new GameObject(new Vector3(), new Vector3(), new Vector3(1, 1, 1), mesh);

	public static void main(String[] args) {
		window.create();
		object.mesh.create();
		shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");
		renderer = new Renderer(shader);
		shader.create();

		while (!GLFW.glfwWindowShouldClose(window.window)) {
			window.update();

			frames++; // FPS
			if (System.currentTimeMillis() > time + 1000) {
				System.out.println(frames);
				time = System.currentTimeMillis();
				frames = 0;
			}
			object.update();

			renderer.renderMesh(object);
			window.swapBuffers();
		}
		object.mesh.destroy();
		shader.destroy();
	}
}
