# MeliTest
Project for process of Mercado Libre

# Instrucciones de ejecución
Para crear la red para comunicar contenedores
docker network create meliproject         			      							

Para bajar la imagen de Mysql
docker pull mysql:latest					      							

Para crear el contenedor de Mysql
docker run -p 3306:3306 --name instance-mysql --network meliproject -e MYSQL_ROOT_PASSWORD=1234 -d mysql:latest    	

Desde la carpeta del proyecto para compilar el .jar
.\gradlew build 						      			    				

Para compilar el dockerfile y crear la imagen
docker build -t meli-application:v1 .				      							

Para crear el contenedor apartir de la imagen
docker run -p 8080:8080 --name meli-application --network meliproject meli-application:v1

Una vez realizado esto y estando arriba la imagen de Mysql podríamos ir a la BD y ejecutar el archivo schema_bd.sql que dejo agregado a este repositorio	
