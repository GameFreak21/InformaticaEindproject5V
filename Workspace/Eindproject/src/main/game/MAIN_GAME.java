package main.game;

import org.lwjgl.glfw.GLFW;

import main.engine.io.Input;
import main.engine.io.Window;
import main.engine.math.Vector2;
import main.engine.math.Vector3;
import main.engine.objects.GameObject;
import main.engine.objects.PlayerCamera;
import main.engine.physics.Collider;
import main.engine.renderer.graphics.MasterRenderer;
import main.engine.renderer.graphics.Material;
import main.engine.renderer.graphics.Mesh;
import main.engine.renderer.graphics.Vertex;
import main.engine.renderer.primitives.Box;

public class MAIN_GAME {
	static Window window = new Window("parcourGame", 1920, 1080);
	
	public static Mesh box_mesh = new Mesh(new Vertex[] {
			// front
			new Vertex(new Vector3(-0.5f, 1, -0.5f), new Vector3(-0.5f, 0, 0.5f), new Vector3(1), new Vector2(1, 1)), // rechtsboven
			new Vertex(new Vector3(0.5f, 1, -0.5f), new Vector3(-0.5f, 0, 0.5f), new Vector3(1), new Vector2(1, 0)), // linksboven
			new Vertex(new Vector3(0.5f, 0f, -0.5f), new Vector3(-0.5f, 0, 0.5f), new Vector3(1), new Vector2(0, 0)), // linksonder
			new Vertex(new Vector3(-0.5f, 0f, -0.5f), new Vector3(-0.5f, 0, 0.5f), new Vector3(1), new Vector2(0, 1)), // rechtsonder

			// right
			new Vertex(new Vector3(0.5f, 1, -0.5f), new Vector3(), new Vector3(1), new Vector2(1, 1)), // rechtsbovenachter
			new Vertex(new Vector3(0.5f, 0, -0.5f), new Vector3(), new Vector3(1), new Vector2(1, 0)), // rechtsonderachter
			new Vertex(new Vector3(0.5f, 1, 0.5f), new Vector3(), new Vector3(1), new Vector2(0, 0)), // rechtsbovenvoor
			new Vertex(new Vector3(0.5f, 0f, 0.5f), new Vector3(), new Vector3(1), new Vector2(0, 1)), // rechtsondervoor

			// back
			new Vertex(new Vector3(-0.5f, 1, 0.5f), new Vector3(), new Vector3(1), new Vector2(1, 1)), // linksboven
			new Vertex(new Vector3(0.5f, 1, 0.5f), new Vector3(), new Vector3(1), new Vector2(1, 0)), // rechtsboven
			new Vertex(new Vector3(0.5f, 0, 0.5f), new Vector3(), new Vector3(1), new Vector2(0, 0)), // rechtsonder
			new Vertex(new Vector3(-0.5f, 0, 0.5f), new Vector3(), new Vector3(1), new Vector2(0, 1)), // linksonder

			// left
			new Vertex(new Vector3(-0.5f, 1, -0.5f), new Vector3(), new Vector3(1), new Vector2(1, 1)), // linksbovenachter
			new Vertex(new Vector3(-0.5f, 0, -0.5f), new Vector3(), new Vector3(1), new Vector2(1, 0)), // linksonderachter
			new Vertex(new Vector3(-0.5f, 1, 0.5f), new Vector3(), new Vector3(1), new Vector2(0, 0)), // linksbovenvoor
			new Vertex(new Vector3(-0.5f, 0, 0.5f), new Vector3(), new Vector3(1), new Vector2(0, 1)), // linksondervoor

			// bottom
			new Vertex(new Vector3(-0.5f, 0, -0.5f), new Vector3(), new Vector3(1), new Vector2(1, 1)), // rechtsachter
			new Vertex(new Vector3(-0.5f, 0, 0.5f), new Vector3(), new Vector3(1), new Vector2(1, 0)), // rechtsvoor
			new Vertex(new Vector3(0.5f, 0, -0.5f), new Vector3(), new Vector3(1), new Vector2(0, 0)), // linksachter
			new Vertex(new Vector3(0.5f, 0, 0.5f), new Vector3(), new Vector3(1), new Vector2(0, 1)), // linksvoor

			// top
			new Vertex(new Vector3(-0.5f, 1, -0.5f), new Vector3(), new Vector3(1), new Vector2(1, 1)), // linksachter
			new Vertex(new Vector3(-0.5f, 1, 0.5f), new Vector3(), new Vector3(1), new Vector2(1, 0)), // linksvoor
			new Vertex(new Vector3(0.5f, 1, -0.5f), new Vector3(), new Vector3(1), new Vector2(0, 0)), // rechtsachter
			new Vertex(new Vector3(0.5f, 1, 0.5f), new Vector3(), new Vector3(1), new Vector2(0, 1)) // rechtsvoor
	}, new int[] {
			// front
			0, 1, 3, 1, 2, 3,
			// right
			4, 6, 5, 6, 7, 5,

			// back
			9, 8, 11, 11, 10, 9,

			// left
			14, 12, 13, 14, 13, 15,

			// bottom
			16, 18, 19, 16, 19, 17,

			// top
			22, 20, 21, 22, 21, 23, }, new Material("resources/textures/box.jpg"));
	
	public static Mesh planeMesh = new Mesh(new Vertex[] {
			// top
			new Vertex(new Vector3(-0.5f, 0, -0.5f), new Vector3(), new Vector3(1, 1, 1), new Vector2(0, 0)), // linksachter
			new Vertex(new Vector3(-0.5f, 0, 0.5f), new Vector3(), new Vector3(1, 1, 1), new Vector2(0, 1)), // linksvoor
			new Vertex(new Vector3(0.5f, 0, -0.5f), new Vector3(), new Vector3(1, 1, 1), new Vector2(1, 0)), // rechtsachter
			new Vertex(new Vector3(0.5f, 0, 0.5f), new Vector3(), new Vector3(1, 1, 1), new Vector2(1, 1)) // rechtsvoor
	}, new int[] {
			// top
			0, 1, 3, 0, 3, 2 }, new Material("resources/textures/grass.png"));
	
	static int y = -100, x = -100, b = 0, c = 0;
	
	public static Box player;
	
	static Vector3 sunPos = new Vector3(0, 10, 15);
	
	public static Collider playerCollider;
	
	public static PlayerCamera camera = new PlayerCamera(new Vector3(0.5f, 4, 0), new Vector3());
	
	public static void main(String[] args) {
		MasterRenderer renderer = new MasterRenderer(window);
		
		Box lightBox = new Box(sunPos, new Vector3(), new Vector3(0.3f), 1.0f, new Vector3(), false,box_mesh);
		player = new Box(new Vector3(10, 11, 12), new Vector3(), new Vector3(0.5f, 2, 0.5f), 0, new Vector3(), false,box_mesh);
	
		GameObject[] planes = new GameObject[1600];
		for (int i = -1000; i < 1000; i = i + 50) {
			for (int a = -1000; a < 1000; a = a + 50) {
				planes[c] = new GameObject(new Vector3(i, 0, a), new Vector3(), new Vector3(50, 1, 50), planeMesh, 1.0f,
						new Vector3(), false);
				c++;
			}
		}
		// 4 levels, elke kant op 1 level?
		GameObject[] boxes = {
				new Box(new Vector3(0, .5f, 0), new Vector3(), new Vector3(10, 1, 10), 1, new Vector3(), true, box_mesh),		//spawnbox
				new Box(new Vector3(5.5f, .5f, 0), new Vector3(), new Vector3(1, 2, 10), 1, new Vector3(), true,box_mesh),	//spawnbox
				new Box(new Vector3(-5.5f, .5f, 0), new Vector3(), new Vector3(1, 2, 10), 1, new Vector3(), true,box_mesh),	//spawnbox
				new Box(new Vector3(0, .5f, 5.5f), new Vector3(), new Vector3(10, 2, 1), 1, new Vector3(), true,box_mesh),	//spawnbox
				new Box(new Vector3(0, .5f, -5.5f), new Vector3(), new Vector3(10, 2, 1), 1, new Vector3(), true,box_mesh), 	//spawnbox
				
				new Box(new Vector3(7.5f, -6.5f, -1), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh),//level 1 box 1
				new Box(new Vector3(14f, -5f, 1), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh),	// level 1 box 2
				new Box(new Vector3(18f, -5f, 6), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh), 	// level 1 box 3
				new Box(new Vector3(23f, -5f, 1), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh), 	// level 1 box 4
				new Box(new Vector3(27f, -5f, -5), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh), 	// level 1 box 5
				
				new Box(new Vector3(0, -6.5f, 7.5f), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh), //level 2 box 1
				new Box(new Vector3(-5, -5f, 10), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh), 	// level 2 box 2
				new Box(new Vector3(-10, -5f, 15), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh), 	// level 2 box 3
				new Box(new Vector3(-3, -5f, 20), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh), 	// level 2 box 4
				new Box(new Vector3(-10, -8f, 25), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh), 	// level 2 box 5
				
				new Box(new Vector3(-7.5f, -6.5f, 1), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh), // level 3 box 1
				new Box(new Vector3(-12, -5, 3), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh), 	// level 3 box 2
				new Box(new Vector3(-17, -4, -3), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh), 	// level 3 box 3
				new Box(new Vector3(-23, -4, 0), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh), 	// level 3 box 4
				new Box(new Vector3(-30, -7, 3), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh), 	// level 3 box 5
				
				new Box(new Vector3(0, -6.5f, -7.5f), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh), //level 4 box 1
				new Box(new Vector3(5, -5f, -12.5f), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh), 	// level 4 box 2
				new Box(new Vector3(-2, -6, -17.5f), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh), 	//level 4 box 3
				new Box(new Vector3(5, -5.5f, -22.5f), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh), 		// level 4 box 4
				new Box(new Vector3(2, -7, -30), new Vector3(), new Vector3(3, 10, 3), 0, new Vector3(), true,box_mesh), 		//level 4 box 5
		};
		
		playerCollider = new Collider(player.mesh.positionData, new Vector3(0.5f, 2, 0.5f));
		
		while (!GLFW.glfwWindowShouldClose(window.window)) {
			window.update();
			
			if (Input.keyDown(GLFW.GLFW_KEY_F11)) {
				window.lockCursor(true);
				camera.lock(false);
			}
			if (Input.keyDown(GLFW.GLFW_KEY_F10)) {
				window.lockCursor(false);
				camera.lock(true);
			}
			
			camera.update();
			
			for (GameObject plane : planes) {
				renderer.processGameObject(plane);
			}
			for (GameObject box : boxes) {
				renderer.processGameObject(box);
			}
			renderer.render(camera, sunPos);
			window.swapBuffers();
			
		}
		renderer.destroy();
		window.kill();
	}		
}
