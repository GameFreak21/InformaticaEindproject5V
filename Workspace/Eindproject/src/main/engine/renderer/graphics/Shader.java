package main.engine.renderer.graphics;

import org.lwjgl.opengl.GL31;

import main.engine.util.FileUtils;

public class Shader {
	public String vertexFile, fragmentFile;
	private int vertexID, fragmentID, programID;
	
	public Shader(String vertexPath, String fragmentPath) {
		vertexFile = FileUtils.loadAsString(vertexPath);
		fragmentFile = FileUtils.loadAsString(fragmentPath);
	}
	
	public void create() {
		programID = GL31.glCreateProgram();
		vertexID = GL31.glCreateShader(GL31.GL_VERTEX_SHADER);
		
		GL31.glShaderSource(vertexID, vertexFile);
		GL31.glCompileShader(vertexID);
		
		if(GL31.glGetShaderi(vertexID, GL31.GL_COMPILE_STATUS) == GL31.GL_FALSE) { //error checking for shader programs
			System.err.println("Vertex Shader: " + GL31.glGetShaderInfoLog(vertexID) + " Gave an error!");
			return;
		}
		
		fragmentID = GL31.glCreateShader(GL31.GL_FRAGMENT_SHADER);
		
		GL31.glShaderSource(fragmentID, fragmentFile);
		GL31.glCompileShader(fragmentID);
		
		if(GL31.glGetShaderi(fragmentID, GL31.GL_COMPILE_STATUS) == GL31.GL_FALSE) { //error checking for shader programs
			System.err.println("Fragment Shader: " + GL31.glGetShaderInfoLog(fragmentID) + " Gave an error!");
			return;
		}
		
		GL31.glAttachShader(programID, vertexID);
		GL31.glAttachShader(programID, fragmentID);
		
		GL31.glLinkProgram(programID); //make sure program is ok
		if(GL31.glGetProgrami(programID, GL31.GL_LINK_STATUS) == GL31.GL_FALSE) {
			System.err.println("Program linking: " + GL31.glGetProgramInfoLog(programID) + " Gave an error!");
		}
		GL31.glValidateProgram(programID);
		if(GL31.glGetProgrami(programID, GL31.GL_VALIDATE_STATUS) == GL31.GL_FALSE) {
			System.err.println("Program validation: " + GL31.glGetProgramInfoLog(programID) + " Gave an error!");
		}
		
		GL31.glDeleteShader(vertexID);//cleanup
		GL31.glDeleteShader(fragmentID);
	}
	
	public void bind() {
		GL31.glUseProgram(programID);
	}
	
	public void unbind() {
		GL31.glUseProgram(0);
	}
	
	public void destroy() {
		GL31.glDeleteProgram(programID);
	}
}
