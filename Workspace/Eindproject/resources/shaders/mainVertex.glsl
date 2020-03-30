#version 330 core

layout(location = 0) in vec3 position;
layout(location = 1) in vec3 color;

out vec4 passColor;

uniform vec4 ourColor;
uniform mat4 model;
uniform mat4 projection;
uniform mat4 view;

void main(){
	gl_Position =  projection * view *  model * vec4(position, 1.0);
	passColor = ourColor * vec4(color, 1.0f);
}
