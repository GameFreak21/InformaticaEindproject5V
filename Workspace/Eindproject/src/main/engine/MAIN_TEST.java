package main.engine;

import java.util.Arrays;

import org.lwjgl.glfw.GLFW;


import main.engine.io.Input;
import main.engine.io.Window;
import main.engine.math.Matrix;
import main.engine.math.Time;
import main.engine.math.Vector2;
import main.engine.math.Vector3;
import main.engine.objects.Camera;
import main.engine.objects.GameObject;
import main.engine.objects.PlayerCamera;
import main.engine.physics.Collider;
import main.engine.physics.Rigidbody;
import main.engine.renderer.primitives.*;
import main.engine.util.ModelLoader;
import main.engine.renderer.graphics.MasterRenderer;
import main.engine.renderer.graphics.Material;
import main.engine.renderer.graphics.Mesh;
import main.engine.renderer.graphics.Vertex;
import main.engine.renderer.graphics.skybox.SkyboxRenderer;

public class MAIN_TEST {
	static Window window = new Window("test", 720, 600);
	// public static Renderer renderer;

	public static Mesh planeMesh = new Mesh(new Vertex[] {
			// top
			new Vertex(new Vector3(-0.5f, 0, -0.5f), new Vector3(), new Vector3(1, 1, 1), new Vector2(0, 0)), // linksachter
			new Vertex(new Vector3(-0.5f, 0, 0.5f), new Vector3(), new Vector3(1, 1, 1), new Vector2(0, 1)), // linksvoor
			new Vertex(new Vector3(0.5f, 0, -0.5f), new Vector3(), new Vector3(1, 1, 1), new Vector2(1, 0)), // rechtsachter
			new Vertex(new Vector3(0.5f, 0, 0.5f), new Vector3(), new Vector3(1, 1, 1), new Vector2(1, 1)) // rechtsvoor
	}, new int[] {
			// top
			0, 1, 3, 0, 3, 2 }, new Material("resources/textures/grass.png"));

	public static float[] positiondata1;
	public static float[] positiondata2;
//	static boolean PlayerLock;
//	static boolean first = true;
	static int y = -100, x = -100, b = 0, c = 0;
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
	// static GameObject yoda;
	static Box box;
	public static Box player;
//	static GameObject yoda = new GameObject(new Vector3(0,0,0), new Vector3(), new Vector3(0.1f), ModelLoader.LoadModel("resources/Models/Baby_Yoda.obj"));
	//static GameObject pika;
	//static Plane plane;
	static Vector3 sunPos = new Vector3(0, 10, 5);
	public static Collider playerCollider;
	public static Collider boxCollider;

//	public static Vector3[] allGameObjectpositions = new Vector3[1603];
	// static Quad quad;
	// static Box box = new Box(new Vector3(), new Vector3(), new
	// Vector3(0.5f,0.5f,0.5f));
	// static Pyramid box = new Pyramid(new Vector3(0,0.5f,0), new Vector3(), new
	// Vector3(1.0f,1.0f,1.0f));
	// static Wedge box = new Wedge(new Vector3(), new Vector3(), new
	// Vector3(1.0f,1.0f,1.0f));
 
	// public static PlayerCamera PlayerCamera = new PlayerCamera(new Vector3(0, 0,
	// -5), new Vector3());
	public static PlayerCamera camera = new PlayerCamera(new Vector3(0, 3, 5), new Vector3());
	public static SkyboxRenderer skyboxRenderer;
	
	public static void main(String[] args) {
		//float schaal1 = 1;
		//float schaal2 = 0.01f;
		//float schaal0 = 0.01f;
		skyboxRenderer = new SkyboxRenderer();
//		Rigidbody body = new Rigidbody();
		MasterRenderer renderer = new MasterRenderer(window);
		//player = new GameObject(new Vector3(-2f, 0, 0), new Vector3(0, 0, 1), new
		// Vector3(schaal0), ModelLoader.LoadModel("resources/Models/IronMan.obj",
		// "resources/textures/p.png"));
		Box lightBox = new Box(sunPos, new Vector3(), new Vector3(0.3f), 1.0f, new Vector3(), false);
		box = new Box(new Vector3(0.5f, 1, 0), new Vector3(), new Vector3(0.5f, 0.5f, 0.5f), 1.0f, new Vector3(), true);
		player = new Box(new Vector3(10, 11, 12), new Vector3(), new Vector3(0.5f, 2, 0.5f), 0.0f, new Vector3(), false);
//		pika = new GameObject(new Vector3(0.5f, 0.5f, 0.5f), new Vector3(0, 0, 1), new Vector3(schaal1),
//				ModelLoader.LoadModel("resources/Models/PenguinBaseMesh.obj", "resources/textures/p.png"), 1.0f, new Vector3());
		// quad = new Quad(new Vector3(0, 0, 0), new Vector3(), new Vector3(1, 1, 1));
		// yoda = new GameObject(new Vector3(), new Vector3(), new Vector3(schaal2),
		// ModelLoader.LoadModel("resources/Models/Baby_Yoda.obj",
		// "resources/textures/creeperhead.png"));

//		for(int i = 0; i < boxs.length; i++) {
//			boxs[i].gameObject.mesh.create();
//		}
//		for(int i = 0; i < wedges.length; i++) {
//			wedges[i].gameObject.mesh.create();
//		}
//		for(int i = 0; i < pyramids.length; i++) {
//			pyramids[i].gameObject.mesh.create();
//		}
		GameObject[] planes = new GameObject[1600];
		for (int i = -1000; i < 1000; i = i + 50) {
			for (int a = -1000; a < 1000; a = a + 50) {
				planes[c] = new GameObject(new Vector3(i, 0, a), new Vector3(), new Vector3(50, 1, 50), planeMesh, 1.0f,
						new Vector3(), false);
				// (y > 100) {
				// = 0;
				// else {
				// = y + 10;
				//
				c++;
			}
			// x = x + 10;

		}
		// plane = new Plane(new Vector3(5, 5, 5), new Vector3(), new Vector3());
		// plane.gameObject.mesh.create();
		// positiondata1 = yoda.mesh.positionData;
		// player.mesh.create();
//		positiondata2 = pika.mesh.positionData;
		// quad.gameObject.mesh.create();
		// shader = new StaticShader();
		// renderer = new Renderer(window, shader);

//		Collider pikaCollider = new Collider(pika.mesh.positionData, schaal1);
		// Collider yodaCollider = new Collider(yoda.mesh.positionData, schaal2);
		playerCollider = new Collider(player.mesh.positionData, new Vector3(0.5f, 2, 0.5f));
		boxCollider = new Collider(box.mesh.positionData, new Vector3(0.5f, 0.5f, 0.5f));
		System.out.println(Collider.CheckCollision(playerCollider, boxCollider, player.position, box.position));
		
		while (!GLFW.glfwWindowShouldClose(window.window)) {
			window.update();
//			System.out.println("FPS : " + (int) (1f / Time.deltaTime));

			if (Input.keyDown(GLFW.GLFW_KEY_F11)) {
				window.lockCursor(true);
				camera.lock(false);
			}
			if (Input.keyDown(GLFW.GLFW_KEY_F10)) {
				window.lockCursor(false);
				camera.lock(true);
			}
//			if (Input.keyDown(GLFW.GLFW_KEY_F8)) {
//				PlayerLock = true;
//			}
			if (Input.keyDown(GLFW.GLFW_KEY_F7)) {
		//		lightBox.update();
		//		sunPos = lightBox.position;
			}

//			if (Input.keyDown(GLFW.GLFW_KEY_F6)) { // Reload shaders
//				shader = new StaticShader();
//				//renderer = new Renderer(window, shader);
//			}
//			if (PlayerLock) {
//				//PlayerCamera.update();
//			}
//			else {
			camera.update();
			// }
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
			//System.out.println(Collider.CheckCollision(playerCollider, boxCollider, player.position, box.position));
//			body.applyGravity(box.gameObject);
		//	box.body.applyGravity(box);

			for (GameObject plane : planes) {
				renderer.processGameObject(plane);
			}

//			renderer.processGameObject(pika);
			renderer.processGameObject(box);
			renderer.processGameObject(player);
		
			renderer.render(camera, sunPos);
			window.swapBuffers();
//			for (int b = 0; b < allCollider.length; b++)
//				System.out.println(allCollider[b].getClass());
			
			// if (Collider.CheckCollision(yodaCollider, pikaCollider, yoda.position,
			// pika.position)) {
			// yoda.position = Vector3.add(yoda.position, new Vector3(1, 0, 0));
			// }
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

		renderer.destroy();

		// shader.destroy();
		window.kill();
	}
}
