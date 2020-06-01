package main.engine.renderer.graphics;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL31;

import main.engine.math.Matrix;
import main.engine.objects.GameObject;
import main.engine.renderer.graphics.skybox.SkyboxRenderer;
import main.engine.renderer.graphics.skybox.SkyboxShader;

public class Renderer {
	StaticShader shader;
//	SkyboxShader skyboxShader;
	
	public Renderer(StaticShader shader) {
		this.shader = shader;
//		this.skyboxShader = skyboxShader;
//		setup();
	}
	
	public void render(Map<Mesh, List<GameObject>> gameObjects) {
		for(Mesh mesh:gameObjects.keySet()) {
			prepMesh(mesh);
			List<GameObject> batch = gameObjects.get(mesh);
			for(GameObject obj:batch) {
				obj.applyGravity(obj);
				prepInstance(obj);
				GL31.glDrawElements(GL31.GL_TRIANGLES, mesh.indices.length, GL31.GL_UNSIGNED_INT, 0);
			}
			unbindMesh();
		}
	}
	
//	private int cubemapTexture;
//	
//	public void setup() {
//		cubemapTexture = Material.LoadCubeMap();
//		final String[] TEXTURE_FILES = {"right.png","left.png","top.png","bottom.png","back.png","front.png"};
//
//		GL31.glBindTexture(GL31.GL_TEXTURE_CUBE_MAP, cubemapTexture);
//
//		for (int i = 0; i < TEXTURE_FILES.length; i++) {
//			Material data = new Material("resources/textures/skybox/" + TEXTURE_FILES[i]);
//			GL31.glTexImage2D(GL31.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GL31.GL_RGB, data.width, data.height, 0,
//					GL31.GL_RGB, GL31.GL_UNSIGNED_BYTE, data.buffer);
//		}
//		GL31.glTexParameteri(GL31.GL_TEXTURE_CUBE_MAP, GL31.GL_TEXTURE_MAG_FILTER, GL31.GL_LINEAR);
//		GL31.glTexParameteri(GL31.GL_TEXTURE_CUBE_MAP, GL31.GL_TEXTURE_MIN_FILTER, GL31.GL_LINEAR);
//		GL31.glTexParameteri(GL31.GL_TEXTURE_CUBE_MAP, GL31.GL_TEXTURE_WRAP_S, GL31.GL_CLAMP_TO_EDGE);
//		GL31.glTexParameteri(GL31.GL_TEXTURE_CUBE_MAP, GL31.GL_TEXTURE_WRAP_T, GL31.GL_CLAMP_TO_EDGE);
//		GL31.glTexParameteri(GL31.GL_TEXTURE_CUBE_MAP, GL31.GL_TEXTURE_WRAP_R, GL31.GL_CLAMP_TO_EDGE);
//		
//		GL31.glBindTexture(GL31.GL_TEXTURE_CUBE_MAP, 0);
//	}
	
//	public void renderSkybox(GameObject skybox) {
//		prepSkyboxMesh(skybox.mesh);
//		
//		GL31.glDepthMask(false);
//		GL31.glDepthRange(1, 1);
//		//GL31.glCullFace(GL31.GL_FRONT);
//		
//		prepInstance(skybox);
//		GL31.glDrawElements(GL31.GL_TRIANGLES, skybox.mesh.indices.length, GL31.GL_UNSIGNED_INT, 0);
//		
//		//GL31.glCullFace(GL31.GL_BACK);
//		GL31.glDepthRange(0, 1);
//		GL31.glDepthMask(true);
//		unbindSkyboxMesh();
//	}
//
//	private void prepSkyboxMesh(Mesh mesh) {
//		GL31.glBindVertexArray(mesh.vertexArrayObject);
//		GL31.glEnableVertexAttribArray(0);
//
//		GL31.glBindBuffer(GL31.GL_ELEMENT_ARRAY_BUFFER, mesh.indicesBufferObject);
//		
//		GL31.glBindTexture(GL31.GL_TEXTURE_CUBE_MAP, cubemapTexture);
//	}
//	
//	private void unbindSkyboxMesh() {
//		GL31.glDisableVertexAttribArray(0); // disable and unbind
//		GL31.glBindVertexArray(0);
//		GL31.glBindTexture(GL31.GL_TEXTURE_CUBE_MAP, 0);
//	}
	
	private void prepMesh(Mesh mesh) {
		GL31.glBindVertexArray(mesh.vertexArrayObject);
		GL31.glEnableVertexAttribArray(0);
		GL31.glEnableVertexAttribArray(1); // enable location for shader
		GL31.glEnableVertexAttribArray(2);
		GL31.glEnableVertexAttribArray(3);

		GL31.glBindBuffer(GL31.GL_ELEMENT_ARRAY_BUFFER, mesh.indicesBufferObject);

		GL31.glBindTexture(GL31.GL_TEXTURE_2D, mesh.texture.textureID);
        GL31.glGenerateMipmap(GL31.GL_TEXTURE_2D);
    	GL31.glTexParameteri(GL31.GL_TEXTURE_2D, GL31.GL_TEXTURE_MAG_FILTER, GL31.GL_LINEAR);
		GL31.glTexParameteri(GL31.GL_TEXTURE_2D, GL31.GL_TEXTURE_MIN_FILTER, GL31.GL_LINEAR);
		GL31.glTexImage2D(GL31.GL_TEXTURE_2D, 0, GL31.GL_RGB, mesh.texture.width, mesh.texture.height, 0,
				GL31.GL_RGB, GL31.GL_UNSIGNED_BYTE, mesh.texture.buffer);
        GL31.glTexParameteri(GL31.GL_TEXTURE_2D, GL31.GL_TEXTURE_MIN_FILTER, GL31.GL_LINEAR_MIPMAP_LINEAR);
        GL31.glTexParameterf(GL31.GL_TEXTURE_2D, GL31.GL_TEXTURE_LOD_BIAS, -0.4f);
        
		shader.SetUniform("shineDamper", mesh.texture.shineDamper);
		shader.SetUniform("reflectivity", mesh.texture.reflectivity);
	}
	
	private void unbindMesh() {
		GL31.glDisableVertexAttribArray(0); // disable and unbind
		GL31.glDisableVertexAttribArray(1);
		GL31.glDisableVertexAttribArray(2);
		GL31.glDisableVertexAttribArray(3);
		GL31.glBindVertexArray(0);
		GL31.glBindTexture(GL31.GL_TEXTURE_2D, 0);
	}
	
	private void prepInstance(GameObject object) {
		shader.SetUniform("model", Matrix.transform(object.position, object.rotation, object.scale));
	}
}
