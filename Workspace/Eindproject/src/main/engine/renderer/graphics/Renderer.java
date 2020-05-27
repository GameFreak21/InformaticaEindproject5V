package main.engine.renderer.graphics;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL31;

import main.engine.math.Matrix;
import main.engine.objects.GameObject;

public class Renderer {
	StaticShader shader;

	public Renderer(StaticShader shader) {
		this.shader = shader;
	}
	
	public void render(Map<Mesh, List<GameObject>> gameObjects) {
		for(Mesh mesh:gameObjects.keySet()) {
			prepMesh(mesh);
			List<GameObject> batch = gameObjects.get(mesh);
			for(GameObject obj:batch) {
				prepInstance(obj);
				GL31.glDrawElements(GL31.GL_TRIANGLES, mesh.indices.length, GL31.GL_UNSIGNED_INT, 0);
			}
			unbindMesh();
		}
	}
	
	private void prepMesh(Mesh mesh) {
		GL31.glBindVertexArray(mesh.vertexArrayObject);
		GL31.glEnableVertexAttribArray(0);
		GL31.glEnableVertexAttribArray(1); // enable location for shader
		GL31.glEnableVertexAttribArray(2);
		GL31.glEnableVertexAttribArray(3);

		GL31.glBindBuffer(GL31.GL_ELEMENT_ARRAY_BUFFER, mesh.indicesBufferObject);

		GL31.glBindTexture(GL31.GL_TEXTURE_2D, mesh.texture.textureID);
        GL31.glGenerateMipmap(GL31.GL_TEXTURE_2D);
        GL31.glTexParameteri(GL31.GL_TEXTURE_2D, GL31.GL_TEXTURE_MIN_FILTER, GL31.GL_LINEAR_MIPMAP_LINEAR);
        GL31.glTexParameterf(GL31.GL_TEXTURE_2D, GL31.GL_TEXTURE_LOD_BIAS, -0.4f);
		GL31.glTexImage2D(GL31.GL_TEXTURE_2D, 0, GL31.GL_RGB, mesh.texture.width, mesh.texture.height, 0,
				GL31.GL_RGB, GL31.GL_UNSIGNED_BYTE, mesh.texture.buffer);
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
