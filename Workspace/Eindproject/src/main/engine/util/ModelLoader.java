package main.engine.util;

import org.lwjgl.assimp.AIFace;
import org.lwjgl.assimp.AIMesh;
import org.lwjgl.assimp.AIScene;
import org.lwjgl.assimp.Assimp;

import main.engine.math.Vector3;
import main.engine.renderer.graphics.Mesh;
import main.engine.renderer.graphics.Vertex;

public class ModelLoader {

	public static Mesh LoadModel(String filename) {
		AIScene scene = Assimp.aiImportFile(filename, Assimp.aiProcess_JoinIdenticalVertices | Assimp.aiProcess_Triangulate);
		
		if (scene == null) {
			System.err.println("couldn't import scene!");
		}
		
		AIMesh mesh = AIMesh.create(scene.mMeshes().get(0));
		return ProcessMesh(mesh);
		
	}

	private static Mesh ProcessMesh(AIMesh mesh) {
		Vertex[] vertices = new Vertex[mesh.mNumVertices()];
		int[] indices = new int[mesh.mNumFaces()*3];
		for(int i = 0; i < mesh.mNumVertices(); i++) {
			Vertex vertex = new Vertex(new Vector3(), new Vector3(0,1,0));
			vertex.position.x = mesh.mVertices().get(i).x();
			vertex.position.y = mesh.mVertices().get(i).y();
			vertex.position.z = mesh.mVertices().get(i).z();
			vertices[i] = vertex;
		}
		int faceCount = mesh.mNumFaces();
		for(int i = 0; i < faceCount; i++) {
			AIFace face = mesh.mFaces().get(i);
			indices[i*3] = face.mIndices().get(0);
			indices[i*3 + 1] = face.mIndices().get(1);
			indices[i*3 + 2] = face.mIndices().get(2);
		}
		return new Mesh(vertices, indices);
	}
}
