#version 330 core

out vec4 outColor;
in vec4 passColor;

void main(){
	outColor = vec4(passColor);
}
