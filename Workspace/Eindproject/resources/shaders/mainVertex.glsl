#version 330 core

layout(location = 0) in vec3 position;
layout(location = 1) in vec3 color;

out vec4 passColor;

uniform vec4 ourColor;
uniform mat4 model;

void main(){
	gl_Position = vec4(position, 1.0) * model;
	passColor = ourColor * vec4(color, 1.0f);
}
