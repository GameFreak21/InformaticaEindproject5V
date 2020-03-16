package main.engine.renderer.graphics;

import org.lwjgl.opengl.GL31;

public class Renderer {
	Shader shader;
	public Renderer(Shader shader) {
		this.shader = shader;
	}
	
	public void renderMesh(Mesh mesh) {
		GL31.glBindVertexArray(mesh.vertexArrayObject);
		GL31.glEnableVertexAttribArray(0);
		GL31.glEnableVertexAttribArray(1); //enable location for shader
		
		GL31.glBindBuffer(GL31.GL_ELEMENT_ARRAY_BUFFER, mesh.indicesBufferObject);
		//GL31.glPolygonMode(GL31.GL_FRONT_AND_BACK, GL31.GL_LINE); //turn on wireframe
		shader.bind();
		GL31.glDrawElements(GL31.GL_TRIANGLES, mesh.indices.length, GL31.GL_UNSIGNED_INT, 0);
		shader.unbind();
		//GL31.glPolygonMode(GL31.GL_FRONT_AND_BACK, GL31.GL_FILL); //turn off wireframe
		GL31.glBindBuffer(GL31.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		GL31.glDisableVertexAttribArray(0); //disable and unbind
		GL31.glDisableVertexAttribArray(1);
		GL31.glBindVertexArray(0);
	}
}
