1.- Crear arquetipo web
mvn archetype:generate 
	-DgroupId=gz.nozing.library.web 
	-DartifactId=library-web 
	-DarchetypeArtifactId=maven-archetype-webapp 
	-DinteractiveMode=false

2.- A�adir el facet web para eclipse 
mvn eclipse:eclipse -Dwtpversion=2.0

3.- Modificar el pom.xml del proyecto
	- Plugin 'maven-eclipse-plugin': a�adir elemento 'wtpversion' para que cada 
	  vez que se regeneren los ficheros de configuraci�n del eclipse, se genere
	  con el facet correspondiente

4.- Arrancar el proyecto en el tomcat: 
mvn tomcat:run