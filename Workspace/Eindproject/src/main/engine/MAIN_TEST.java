package main.engine;
import java.util.Arrays;

import org.lwjgl.glfw.GLFW;

import main.engine.io.Window;
import main.engine.math.Matrix;
import main.engine.math.Vector3;
import main.engine.renderer.graphics.Mesh;
import main.engine.renderer.graphics.Vertex;
import main.engine.renderer.graphics.Renderer;
import main.engine.renderer.graphics.Shader;

public class MAIN_TEST {
	static Window window = new Window("test", 720, 600);
	static Renderer renderer;
	static Shader shader;
	static float [][] no1 = {
			{1, 2}, 
			{3, 4}
	};
	static float [][] no2 = {
			{5,6},
			{7,8}
	};
	static Matrix matrix = new Matrix();
	
	static Mesh mesh = new Mesh(new Vertex[] {
			new Vertex(new Vector3(-0.5f,0.5f, 0.0f), new Vector3(0.0f,1.0f,0.0f)),
			new Vertex(new Vector3( 0.5f,0.5f, 0.0f), new Vector3(0.0f,0.0f,0.0f)),
			new Vertex(new Vector3( 0.5f,-0.5f, 0.0f), new Vector3(0.0f,0.0f,0.0f)),
			new Vertex(new Vector3(-0.5f,-0.5f,0.0f), new Vector3(0.0f,0.0f,.0f))
	}, new int[] {
			0, 1, 2,  //triangles for rendering , top left, top right, bot right
			0, 3, 2
	});
	
	static int frames;
	static long time = System.currentTimeMillis();
	
	public static void main(String[] args) {
		window.create();
		mesh.create();
		shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");
		renderer = new Renderer(shader);
		shader.create();
		float [][] mul = matrix.matmul(no1, no2);
		
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
		mesh.destroy();
		shader.destroy();
		System.out.println(Arrays.deepToString(mul));
	}
}
