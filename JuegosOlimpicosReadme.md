# ESTRUCTURA DE LA APLICACIÓN 
La aplicación se divide en 3 capas principales:
 - Capa Controlador: Clase que cuenta con la definición de los servicios REST disponibles para utilizar. Estas clases se encapsulan en el paquete com.alberto.jjoo.controllers.
 - Capa Servicios: Clase que cuentan con la lógica de negocio. Se utilza el patrón Fachada para separar la definición de los métodos públicos, disponibles para las clases que inyecten los servicios,
en una interfaz y la implementación donde se desarrollan los métodos. Las interfaces se encapsulane en el paquete com.alberto.jjoo.services y las implementaciones en el paquete com.alberto.jjoo.services.impl.
 - Capa Acceso a Datos: Clase que cuenta con las operaciones de acceso y conexión con la base de datos. De igual manera que con los servicios se utiliza el patrón Fachada para separar la definición de los métodos de
su implementación. Las interfaces de los DAO se encapsulan en el paquete com.alberto.jjoo.dao y las implementaciones en el paquete com.alberto.jjoo.dao.impl.

El resto de la aplicación se divide en los siguientes paquetes:
 - com.alberto.jjoo.entidades: Contiene las entidades del modelo de datos.
 - com.alberto.jjoo.dto: Clase para mapear los resultados de la consulta del punto 2.
 - com.alberto.jjoo.mappers: Clases para mapear las búsquedas que se realicen contra la base de datos.
 - com.alberto.jjoo.exceptions: Clases de error para controlar distintos errores en la aplicación.
 
# GUÍA DE USO
A continuación se detallan los pasos para poder arrancar la aplicación:
 - Descargar la aplicación desde el repositorio de Github: https://github.com/AlbertAMD/pruebaTecnicaQindel
 - Para utilizar la aplicación se va a utilizar Docker para levantar en un contenedor una imagen de la mysql con la base de datos y otro contenedor con la aplicación java.
 - Primero se creará una subred para conectar los contenedores, para ello se lanzará el siguiente comando: 
 '''bash
 "docker network create --subnet=172.18.0.0/16 network_name"
 '''
, donde indicaremos una red en la que podremos dar hasta 16 direcciones ip distintas.
 - A continuación levantaremos una imagen de mysql llamada testsql en la ip 172.18.0.5 con el siguiente comando:
'''bash
 "docker run --name=testsql -e MYSQL_ROOT_PASSWORD=root -d --net network_name --ip=172.18.0.5 mysql"
'''
 - Nos tendremos que conectar a la base de datos levantada para poder crear el esquema de base de datos. Para conectarse a la terminal del docker con la imagen de mysql se puede utilizar el siguiente comando 
'''bash
 "docker exec -it testsql bash"
'''
, y a continuación 
'''bash 
 "mysql -p"
'''
y poniendo como contraseña "root", como se indico en el momento de levantar la imagen.
 - Una vez conectados en la base de datos crearemos el esquema de base de datos a través del scrip facilitado para la prueba: https://github.com/AlbertAMD/pruebaTecnicaQindel/blob/master/juegosolimpicos_ddl.sql
 - Con la base de datos ya creada, y el proyecto descargado pasamos a compilar el proyecto a través de maven con el comando 
'''bash
 "mvn clean package"
'''
 - Con el proyecto compilado podemos crear la imagen en Docker de la aplicación con el comando 
'''bash 
 "docker build --tag=jjoo_qindel ."
'''
 - Con la imagen creada se pasa a arrancar la aplicación y se conecta con la base de datos que se encuentra levantada en otro contenedor: 
'''bash
 "docker run --name jjoo_qindel -d --link testsql:db -p 8080:8080 --net network_name --ip=172.18.0.6 jjoo_qindel"
''' 
especificando que se arranque en la ip 172.18.0.6
 - Con la aplicación levantada y la base de datos levantada podemos probar los servicios REST utilizando para ello la herramienta postman. En el repositorio se incluye un archivo con peticiones para importarlo en postman:
