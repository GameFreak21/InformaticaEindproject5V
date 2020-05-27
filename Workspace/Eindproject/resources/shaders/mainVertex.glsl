#version 330 core

layout(location = 0) in vec3 position;
layout(location = 1) in vec3 normals;
layout(location = 2) in vec3 color;
layout(location = 3) in vec2 textureCoords;

out vec3 passColor;
out vec3 Normal;
out vec2 texCoords;

out vec3 toCameraVector;
out vec3 toLightVector;

uniform vec3 sunPosition;
uniform mat4 model;
uniform mat4 projection;
uniform mat4 view;

void main(){
	vec4 worldPosition = model * vec4(position, 1.0);
	gl_Position = projection * view * model * vec4(position, 1.0);
	
	texCoords = textureCoords;
	passColor = color;
	toLightVector = sunPosition - worldPosition.xyz;
	toCameraVector = (inverse(view) * vec4(0.0,0.0,0.0,1.0)).xyz - worldPosition.xyz;
	
	Normal = mat3(transpose(inverse(model))) * normals;
}
