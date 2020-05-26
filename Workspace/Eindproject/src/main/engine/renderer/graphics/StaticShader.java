package main.engine.renderer.graphics;

public class StaticShader extends Shader {

	private static final String VERTEX_FILE = "/shaders/mainVertex.glsl";
	private static final String FRAGMENT_FILE = "/shaders/mainFragment.glsl";

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	public StaticShader(String vertexFile, String fragmentFile) {
		super(vertexFile, fragmentFile);
	}

}
