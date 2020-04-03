#version 330 core

out vec4 outColor;

in vec3 passColor;
in vec3 Normal;
in vec3 FragPos;
in vec2 texCoords;

uniform sampler2D ourTexture;

void main(){
	float ambientStrength = 1f;
	vec3 ambient = ambientStrength * vec3(1,1,1);
	
	vec3 norm = normalize(Normal);
	vec3 lightDir = normalize(vec3(0,50,-20) - FragPos);
	
	float diff = max(dot(norm, lightDir), 0.0);
	vec3 diffuse = diff * vec3(1,1,1);
	
	vec3 result = (ambient + diffuse) * passColor;
	
	outColor = texture(ourTexture, texCoords) * vec4(result, 1.0);
	
}
