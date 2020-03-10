package main.engine.io;

import org.lwjgl.glfw.GLFW;

public class Window {
	int width, height;
	String name;
	public Window(String name ,int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
	}
	
	public void create() {
		if(!GLFW.glfwInit()) {
			System.err.println("Can't initialize GLFW!");
		}
	}
}
