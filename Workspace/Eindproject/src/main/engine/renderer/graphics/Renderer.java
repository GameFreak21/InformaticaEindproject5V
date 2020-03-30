package main.engine.renderer.graphics;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL31;

import main.engine.io.Window;
import main.engine.math.Matrix;
import main.engine.math.Vector4;
import main.engine.objects.Camera;
import main.engine.objects.GameObject;

public class Renderer {
	Shader shader;
	Window window;
	
	public Renderer(Window window, Shader shader) {
		this.shader = shader;
		this.window = window;
	}

	public void renderMesh(GameObject object, Camera camera) {
		GL31.glBindVertexArray(object.mesh.vertexArrayObject);
		GL31.glEnableVertexAttribArray(0);
		GL31.glEnableVertexAttribArray(1); // enable location for shader

		GL31.glBindBuffer(GL31.GL_ELEMENT_ARRAY_BUFFER, object.mesh.indicesBufferObject);
		//GL31.glPolygonMode(GL31.GL_FRONT_AND_BACK, GL31.GL_LINE); //turn on wireframe
		shader.bind();
		float timeVal = (float) GLFW.glfwGetTime();
		Vector4 colorVal = new Vector4((float) (Math.sin(timeVal) / 2.0f) + 0.8f,
				(float) (Math.sin(timeVal) / 1.21f) + 0.6f, (float) (Math.sin(timeVal) / 1.5f) + 0.4f, 1.0f);
		shader.SetUniform("ourColor", colorVal);
		shader.SetUniform("model", Matrix.transform(object.position, object.rotation, object.scale));
		shader.SetUniform("projection", window.projectionMat);
		shader.SetUniform("view", Matrix.view(camera.position,  camera.rotation));
		GL31.glDrawElements(GL31.GL_TRIANGLES, object.mesh.indices.length, GL31.GL_UNSIGNED_INT, 0);
		shader.unbind();
		// GL31.glPolygonMode(GL31.GL_FRONT_AND_BACK, GL31.GL_FILL); //turn off wireframe
		GL31.glBindBuffer(GL31.GL_ELEMENT_ARRAY_BUFFER, 0);

		GL31.glDisableVertexAttribArray(0); // disable and unbind
		GL31.glDisableVertexAttribArray(1);
		GL31.glBindVertexArray(0);
	}
}
