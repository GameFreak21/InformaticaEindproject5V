#version 330 core

layout(location = 0) in vec3 position;
layout(location = 1) in vec3 normals;
layout(location = 2) in vec3 color;
layout(location = 3) in vec2 texturecoords;

out vec3 passColor;
out vec3 Normal;
out vec3 FragPos;
out vec2 texcoords;

uniform mat4 model;
uniform mat4 projection;
uniform mat4 view;

void main(){
	gl_Position = projection * view * model * vec4(position, 1.0);
	
	FragPos = vec3(model * vec4(position, 1.0));
	texcoords = texturecoords;
	passColor = color;
	Normal = mat3(transpose(inverse(model))) * normals;
}
