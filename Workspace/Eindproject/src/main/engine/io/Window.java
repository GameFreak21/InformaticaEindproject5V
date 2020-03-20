package main.engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL31;

import main.engine.math.Vector3;

public class Window {
	
	//variables
	public long window;
	public int width, height;
	String name;
	
	private Vector3 bgColor;
	
	public Window(String name ,int width, int height) { //window function for class
		this.name = name;
		this.width = width;
		this.height = height;
		this.bgColor = new Vector3(1.0f, 0.4f, 0.4f);
	}
	
	public void create() { //function for creating window
		if(!GLFW.glfwInit()) { //error handling
			System.err.println("Can't initialize GLFW!");
			return;
		}
		
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE); //make window resizable
		window = GLFW.glfwCreateWindow(width, height, name, 0, 0); //create the window
		if(window == 0) { //error handling
			System.err.println("Window isn't created");
			return;
		}
		
		GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor()); //for monitor resolution
		GLFW.glfwSetWindowPos(window, (videoMode.width() - width)/2, (videoMode.height() - height)/2); //center window
		
		GLFW.glfwMakeContextCurrent(window); //make the current used window
		GL.createCapabilities();
		
		GL31.glEnable(GL31.GL_BLEND);//enable alfa
		GL31.glBlendFunc(GL31.GL_SRC_ALPHA, GL31.GL_ONE_MINUS_SRC_ALPHA);
		
		GLFW.glfwShowWindow(window); //show the window
		
	//	GLFW.glfwSwapInterval(0); //remove comment for v-sync off
		
	}
	
	public void update() { //update window
		GL31.glClearColor(bgColor.x, bgColor.y, bgColor.z, 1.0f);
		GL31.glClear(GL31.GL_COLOR_BUFFER_BIT);
		
		GLFW.glfwPollEvents();
	}
	
	public void swapBuffers() { //swap buffers 
		GLFW.glfwSwapBuffers(window);
	}
	
	public void kill() { //kill window
		GLFW.glfwDestroyWindow(window); 
		GLFW.glfwTerminate();
	}
}
