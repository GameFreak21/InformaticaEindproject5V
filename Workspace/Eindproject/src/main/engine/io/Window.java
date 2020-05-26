package main.engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL31;

import main.engine.math.Matrix;
import main.engine.math.Time;
import main.engine.math.Vector3;

public class Window {

	// variables
	public long window;
	public int width, height;
	public float[][] projectionMat;
	String name;

	private Input input;
	private GLFWWindowSizeCallback sizeCallback;
	private boolean resized = false;
	private Vector3 bgColor;
	
	private float fov = 70.0f;
	private float near = 0.01f;
	private float far = 1000.0f;
	
	private Time time;
	
	public Window(String name, int width, int height) { // window function for class
		this.name = name;
		this.width = width;
		this.height = height;
		this.bgColor = new Vector3(1.0f, 0.4f, 0.4f);
		projectionMat = Matrix.projection((float) width / (float) height, fov, near, far);
		this.create();
	}

	public void create() { // function for creating window
		if (!GLFW.glfwInit()) { // error handling
			System.err.println("Can't initialize GLFW!");
			return;
		}
		
		input = new Input();
		time = new Time();
		
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE); // make window resizable
		window = GLFW.glfwCreateWindow(width, height, name, 0, 0); // create the window
		if (window == 0) { // error handling
			System.err.println("Window isn't created");
			return;
		}

		GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor()); // for monitor resolution
		GLFW.glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2); // center
																											// window

		GLFW.glfwMakeContextCurrent(window); // make the current used window
		GL.createCapabilities();

		GL31.glEnable(GL31.GL_BLEND);// enable alfa
		GL31.glBlendFunc(GL31.GL_SRC_ALPHA, GL31.GL_ONE_MINUS_SRC_ALPHA);
		
		GL31.glEnable(GL31.GL_DEPTH_TEST);
		
		GL31.glEnable(GL31.GL_CULL_FACE);
		GL31.glFrontFace(GL31.GL_CCW);
		GL31.glCullFace(GL31.GL_BACK);
		
		
		createCallbacks();

		GLFW.glfwShowWindow(window); // show the window

		GLFW.glfwSwapInterval(0); //remove comment for v-sync off

	}

	private void createCallbacks() {
		sizeCallback = new GLFWWindowSizeCallback() {
			public void invoke(long window, int w, int h) {
				width = w;
				height = h;
				resized = true;
			}
		};
		//input callbacks
		GLFW.glfwSetKeyCallback(window, input.keyboardCallback);
		GLFW.glfwSetMouseButtonCallback(window, input.mouseButtonCallback);
		GLFW.glfwSetCursorPosCallback(window, input.mousePosCallback);
		GLFW.glfwSetScrollCallback(window, input.mouseScrollCallback);
		
		//size callback
		GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
	}

	public void update() { // update window
		if (resized) {
			GL31.glViewport(0, 0, width, height);
			projectionMat = Matrix.projection((float) width / (float) height, fov, near, far);
			resized = false;
		}
		
		time.update();
		
		GL31.glClearColor(bgColor.x, bgColor.y, bgColor.z, 1.0f);
		GL31.glClear(GL31.GL_COLOR_BUFFER_BIT | GL31.GL_DEPTH_BUFFER_BIT);

		GLFW.glfwPollEvents();
	}
	
	public void lockCursor(boolean cursorLock) {
		GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, cursorLock ? GLFW.GLFW_CURSOR_DISABLED : GLFW.GLFW_CURSOR_NORMAL);
	}
	
	public void swapBuffers() { // swap buffers
		GLFW.glfwSwapBuffers(window);
	}

	public void kill() { // kill window
		GLFW.glfwDestroyWindow(window);
		GLFW.glfwTerminate();
	}
}
