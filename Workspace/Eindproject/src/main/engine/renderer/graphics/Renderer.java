package main.engine.renderer.graphics;

import org.lwjgl.opengl.GL31;

public class Renderer {
	public void renderMesh(Mesh mesh) {
		GL31.glBindVertexArray(mesh.vertexArrayObject);
		GL31.glEnableVertexAttribArray(0);
		
		GL31.glBindBuffer(GL31.GL_ELEMENT_ARRAY_BUFFER, mesh.indicesBufferObject);
		GL31.glDrawElements(GL31.GL_TRIANGLES, mesh.indices.length, GL31.GL_UNSIGNED_INT, 0);
		GL31.glBindBuffer(GL31.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		GL31.glDisableVertexAttribArray(0); //disable and unbind
		GL31.glBindVertexArray(0);
	}
}
