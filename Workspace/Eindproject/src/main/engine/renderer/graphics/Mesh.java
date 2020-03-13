package main.engine.renderer.graphics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL31;
import org.lwjgl.system.MemoryUtil;

public class Mesh {
	public Vertex[] vertices;
	public int[] indices;
	public int vertexArrayObject, positionBufferObject, indicesBufferObject;
	
	
	public Mesh(Vertex[] vertices, int[] indices) {
		this.vertices = vertices;
		this.indices = indices;
	}
	
	public void create() {
		vertexArrayObject = GL31.glGenVertexArrays();
		GL31.glBindVertexArray(vertexArrayObject);
		
		FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
		float[] positionData = new float[vertices.length * 3];
		
		for (int i = 0; i < vertices.length; i++) {
			positionData[i*3] = vertices[i].position.x;
			positionData[i*3 + 1] = vertices[i].position.y;
			positionData[i*3 + 2] = vertices[i].position.z;
		}
		positionBuffer.put(positionData).flip();
		
		positionBufferObject = GL31.glGenBuffers();
		GL31.glBindBuffer(GL31.GL_ARRAY_BUFFER, positionBufferObject);
		GL31.glBufferData(GL31.GL_ARRAY_BUFFER, positionBuffer, GL31.GL_STATIC_DRAW);
		GL31.glVertexAttribPointer(0, 3, GL31.GL_FLOAT, false, 0, 0);
		GL31.glBindBuffer(GL31.GL_ARRAY_BUFFER, 0); //unbind buffer
		
		IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
		indicesBuffer.put(indices).flip();
		
		indicesBufferObject = GL31.glGenBuffers();
		GL31.glBindBuffer(GL31.GL_ELEMENT_ARRAY_BUFFER, indicesBufferObject);
		GL31.glBufferData(GL31.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL31.GL_STATIC_DRAW);
		GL31.glBindBuffer(GL31.GL_ELEMENT_ARRAY_BUFFER, 0); //unbind buffer
	}
}
