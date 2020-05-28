package main.engine.renderer.graphics.skybox;

import main.engine.renderer.graphics.Shader;

public class SkyboxShader extends Shader{
	public static final String VERTEX_FILE= "/shaders/skybox/skyboxVertexShader.glsl";
	public static final String FRAGMENT_FILE = "/shaders/skybox/skyboxFragmentShader.glsl";
	
	public SkyboxShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

}
