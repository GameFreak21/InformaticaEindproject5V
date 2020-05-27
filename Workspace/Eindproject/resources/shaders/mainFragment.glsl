#version 330 core

out vec4 outColor;

in vec3 passColor;
in vec3 Normal;
in vec3 FragPos;
in vec2 texCoords;

in vec3 toCameraVector;
in vec3 toLightVector;

uniform sampler2D ourTexture;
uniform float shineDamper;
uniform float reflectivity;

void main(){
	float ambientStrength = 0.1f;
	vec3 ambient = ambientStrength * vec3(1,1,1);
	
	vec3 norm = normalize(Normal);
	vec3 lightVector = normalize(toLightVector);

	float brightness = max(dot(norm, lightVector), 0.1f);
	vec3 diffuse = brightness * vec3(1,1,1);
	
	vec3 cameraVector = normalize(toCameraVector);
	vec3 lightDir = -lightVector;
	vec3 reflectedLightDir = reflect(lightDir, norm);
	
	float specularFactor = dot(reflectedLightDir, cameraVector);
	specularFactor = max(specularFactor, 0.0);
	float dampedFactor = pow(specularFactor, shineDamper);
	vec3 finalSpecular = dampedFactor * vec3(1,1,1);
	
	
	vec3 result = (ambient + diffuse) * passColor;
	 
	outColor = texture(ourTexture, texCoords) * vec4(result, 1.0) + vec4(finalSpecular,1.0);
}
