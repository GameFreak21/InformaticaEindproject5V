package main.engine.renderer.graphics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL31;
import org.lwjgl.system.MemoryUtil;

public class Mesh {
	public Vertex[] vertices;
	public int[] indices;
	public int vertexArrayObject, positionBufferObject, indicesBufferObject, colorBufferObject;
	
	
	public Mesh(Vertex[] vertices, int[] indices) {
		this.vertices = vertices;
		this.indices = indices;
	}
	
	public void create() {
		vertexArrayObject = GL31.glGenVertexArrays();
		GL31.glBindVertexArray(vertexArrayObject);
		
		//position buffer
		FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
		float[] positionData = new float[vertices.length * 3];
		
		for (int i = 0; i < vertices.length; i++) {
			positionData[i*3] = vertices[i].position.x;
			positionData[i*3 + 1] = vertices[i].position.y;
			positionData[i*3 + 2] = vertices[i].position.z;
		}
		positionBuffer.put(positionData).flip();
		
		positionBufferObject = storeData(positionBuffer, 0, 3);
		
		//color buffer
		FloatBuffer colorBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
		float[] colorData = new float[vertices.length * 3];
		
		for (int i = 0; i < vertices.length; i++) {
			colorData[i*3] = vertices[i].color.x;
			colorData[i*3 + 1] = vertices[i].color.y;
			colorData[i*3 + 2] = vertices[i].color.z;
		}
		colorBuffer.put(colorData).flip();
		
		colorBufferObject = storeData(colorBuffer, 1, 3);
		
		//indicesbuffer
		IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
		indicesBuffer.put(indices).flip();
		
		indicesBufferObject = GL31.glGenBuffers();
		GL31.glBindBuffer(GL31.GL_ELEMENT_ARRAY_BUFFER, indicesBufferObject);
		GL31.glBufferData(GL31.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL31.GL_STATIC_DRAW);
		GL31.glBindBuffer(GL31.GL_ELEMENT_ARRAY_BUFFER, 0); //unbind buffer
	}
	
	private int storeData(FloatBuffer buffer, int index, int size) {
		int bufferID = GL31.glGenBuffers();
		GL31.glBindBuffer(GL31.GL_ARRAY_BUFFER, bufferID);
		GL31.glBufferData(GL31.GL_ARRAY_BUFFER, buffer, GL31.GL_STATIC_DRAW);
		GL31.glVertexAttribPointer(index, size, GL31.GL_FLOAT, false, 0, 0);
		GL31.glBindBuffer(GL31.GL_ARRAY_BUFFER, 0); //unbind buffer
		return bufferID;
	}
	
	public void destroy() {
		GL31.glDeleteBuffers(positionBufferObject);
		GL31.glDeleteBuffers(colorBufferObject);
		GL31.glDeleteBuffers(indicesBufferObject);
		
		GL31.glDeleteVertexArrays(vertexArrayObject);
	}
}