#version 330

in vec3 textureCoords;
out vec4 outColor;

uniform samplerCube cubeMap;

void main(){
    outColor = texture(cubeMap, textureCoords);
}