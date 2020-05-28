package main.engine.renderer.graphics.skybox;

import org.lwjgl.opengl.GL31;

import main.engine.io.Window;
import main.engine.math.Matrix;
import main.engine.math.Vector3;
import main.engine.objects.Camera;
import main.engine.renderer.graphics.Material;
import main.engine.renderer.primitives.Box;

public class SkyboxRenderer {
	private static final float SIZE = 500f;
	
	private static String[] TEXTURE_FILES = {"right.png","left.png","top.png","bottom.png","back.png","front.png"};
	
	private SkyboxShader shader;
	private int cubemapTexture;
	private Box skybox;
	
	public SkyboxRenderer() {
		shader = new SkyboxShader();
		cubemapTexture = Material.LoadCubeMap();
		skybox = new Box(new Vector3(), new Vector3(), new Vector3(SIZE), 0, new Vector3());
	}
	
	public void render(Camera camera, Window window) {
		shader.bind();
		shader.SetUniform("projection", window.projectionMat);
		shader.SetUniform("view", Matrix.view(camera.position, camera.rotation));
		
		GL31.glBindBuffer(GL31.GL_ELEMENT_ARRAY_BUFFER, skybox.mesh.indicesBufferObject);
		
		GL31.glBindVertexArray(skybox.mesh.vertexArrayObject);
		GL31.glEnableVertexAttribArray(0);
		//GL31.glDepthMask(false);
		//GL31.glDepthRange(1, 1);
//		GL31.glCullFace(GL31.GL_FRONT);
		//GL31.glActiveTexture(GL31.GL_TEXTURE0);
		GL31.glBindTexture(GL31.GL_TEXTURE_CUBE_MAP, cubemapTexture);

		for (int i = 0; i < TEXTURE_FILES.length; i++) {
			Material data = new Material("resources/textures/skybox/" + TEXTURE_FILES[i]);
			GL31.glTexImage2D(GL31.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GL31.GL_RGB, data.width, data.height, 0,
					GL31.GL_RGB, GL31.GL_UNSIGNED_BYTE, data.buffer);
		}
		GL31.glTexParameteri(GL31.GL_TEXTURE_CUBE_MAP, GL31.GL_TEXTURE_MAG_FILTER, GL31.GL_LINEAR);
		GL31.glTexParameteri(GL31.GL_TEXTURE_CUBE_MAP, GL31.GL_TEXTURE_MIN_FILTER, GL31.GL_LINEAR);
		GL31.glTexParameteri(GL31.GL_TEXTURE_CUBE_MAP, GL31.GL_TEXTURE_WRAP_S, GL31.GL_CLAMP_TO_EDGE);
		GL31.glTexParameteri(GL31.GL_TEXTURE_CUBE_MAP, GL31.GL_TEXTURE_WRAP_T, GL31.GL_CLAMP_TO_EDGE);
		GL31.glTexParameteri(GL31.GL_TEXTURE_CUBE_MAP, GL31.GL_TEXTURE_WRAP_R, GL31.GL_CLAMP_TO_EDGE);
		
		GL31.glDrawElements(GL31.GL_TRIANGLES, skybox.mesh.indices.length, GL31.GL_UNSIGNED_INT, 0);
//		GL31.glCullFace(GL31.GL_BACK);
		//GL31.glDepthRange(0, 1);
		//GL31.glDepthMask(true);
		shader.unbind();
		
		GL31.glDisableVertexAttribArray(0); // disable and unbind
		GL31.glBindVertexArray(0);
		GL31.glBindTexture(GL31.GL_TEXTURE_CUBE_MAP, 0);
	}
}
