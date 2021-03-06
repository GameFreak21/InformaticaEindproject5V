package main.engine.renderer.graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.engine.io.Window;
import main.engine.math.Matrix;
import main.engine.math.Vector3;
import main.engine.objects.Camera;
import main.engine.objects.GameObject;
import main.engine.physics.Collider;
import main.engine.renderer.graphics.skybox.SkyboxRenderer;
import main.engine.renderer.graphics.skybox.SkyboxShader;

public class MasterRenderer {	
	private StaticShader shader;
	private Renderer renderer;
	private Window window;
//	private SkyboxShader skyboxShader;
	private int a = 0;
	
	public MasterRenderer(Window window) {
		this.window = window;
		this.shader = new StaticShader();
		this.renderer = new Renderer(shader);
	}
	
	private Map<Mesh,List<GameObject>> gameObjects = new HashMap<Mesh, List<GameObject>>();
	
	public void render(Camera camera, Vector3 sun) {
		
		shader.bind();
		shader.SetUniform("projection", window.projectionMat);
		shader.SetUniform("view", Matrix.view(camera.position, camera.rotation));
		shader.SetUniform("sunPosition", sun);
		
		renderer.render(gameObjects);
		
		shader.unbind();

		gameObjects.clear();
	}
	
	public void processGameObject(GameObject object) {
		Mesh mesh = object.mesh;
		List<GameObject> batch = gameObjects.get(mesh);
		if(batch!=null) {
			batch.add(object);
		}else {
			List<GameObject> newBatch = new ArrayList<GameObject>();
			newBatch.add(object);
			gameObjects.put(mesh, newBatch);
		}
	}
	
	public void destroy() {
		shader.destroy();
	}
}
