package main.engine;
import org.lwjgl.glfw.GLFW;

import main.engine.io.Window;
import main.engine.math.Vector3;
import main.engine.renderer.graphics.Mesh;
import main.engine.renderer.graphics.Vertex;
import main.engine.renderer.graphics.Renderer;
import main.engine.renderer.primitives.Box;

public class MAIN_TEST {
	static Window window = new Window("test", 720, 600);
	static Renderer renderer = new Renderer();
	static Box box = new Box(new Vector3(0, 0, 0), 10, window);
	
	static Mesh mesh = new Mesh(new Vertex[] {
			new Vertex(new Vector3(-0.5f,0.5f, 0.0f)),
			new Vertex(new Vector3( 0.5f,0.5f, 0.0f)),
			new Vertex(new Vector3( 0.5f,-0.5f, 0.0f)),
			new Vertex(new Vector3(-0.5f,-0.5f, 0.0f))
	}, new int[] {
			0, 1, 2,  //triangles for rendering , top left, top rigt, bot right
			0, 3, 2  //top left, bot left, bot right
	});
	
	static int frames;
	static long time = System.currentTimeMillis();
	
	public static void main(String[] args) {
		window.create();
		mesh.create();
		
		while(!GLFW.glfwWindowShouldClose(window.window)) {
			window.update();
			
			frames++; //FPS
			if(System.currentTimeMillis() > time + 1000) {
				System.out.println(frames);
				time = System.currentTimeMillis();
				frames = 0;
			}
			
			renderer.renderMesh(mesh);
			window.swapBuffers();
		}
	}
}
