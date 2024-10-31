# TP DISEÑO DE SOFWARE

## DSDatabasase

### Posgre

1. Tenemos que instalar PosgreSQL para poder crear la base de datos y persistir los archivos, yo estoy usando: **psql (PostgreSQL) 16.2**
[Instalar Posgre+PGAdmin](https://www.postgresql.org/download/)

opcional pero facilita: definir el path en las variables de entorno del sistema haciendo:
buscador windows -> Editar variables de entorno del sistema -> Variables de entorno.. -> Path (doble click) -> Nuevo -> pegan el path de posgre, en mi caso: **C:\Program Files\PostgreSQL\16\bin** (que es al instalacion por defecto)

pueden verificar si se instalo abriendo terminal y haciendo psql --version

2. Entramos a PGAdmin con el usuario posgre que definimos en la instalacion y creamos una base de datos el localhost puerto 5432

3. Creamos una base de datos llamada DSDatabase:

SQL:

CREATE DATABASE DSDatabase;

4.  creamos un usuario admin_ds y contraseña root que por lo menos tenga beneficion para hacer CRUD y siendo dueño con llas sentencias SQL:

SQL:

CREATE USER admin_ds WITH PASSWORD 'root';
ALTER DATABASE DSDatabase OWNER TO admin_ds;
GRANT ALL PRIVILEGES ON DATABASE DSDatabase TO admin_ds;

5. (No hacer por el momento) Dentro de DS-Database esta definida la base de datos con las sentencias para crearla por el momento * NO CARGAR LA BASE* y dejar que la cree sola Spring hasta que definamos como va a ser el mapeo final 

## BACKEND

### Dependencias

2. Instalar maven para correr la aplicacion con SpringBoot, yo estoy usando **Apache Maven 3.9.9**

[Instalar Maven](https://maven.apache.org/download.cgi)

luego agregar el path a las variables de entorno (igual que en posgre)

buscador windows -> Editar variables de entorno del sistema -> Variables de entorno.. -> Path (doble click) -> Nuevo -> pegan el path de maven

pueden verificar si se instalo abriendo terminal y haciendo mvn --version

3. Si vamos a actuar desde visual debemos instalar las extenciones java Extencion pack +Spring Initializr + Spring dev tool + Debugger for java + maven for java + resto for java +(Opcional) Live server [para probar los front sin levantar todo el server]

#### Cursos de springboot

1. [Introduccion spring (Todocode)](https://www.youtube.com/watch?v=8X2acANBuLk&t=2785s&ab_channel=TodoCode)

2. [Ejemplo practico completo](https://www.youtube.com/watch?v=M7lhQMzzHWU&t=923s&ab_channel=DIF%E2%84%A2)

## FRONTEND

#### Cursos de front end web

1. [Curso completo html+css (SoyDalto)](https://www.youtube.com/watch?v=ELSm-G201Ls&t=11648s&ab_channel=SoyDalto)

2. [Curso completo js (SoyDalto)](https://www.youtube.com/watch?v=z95mZVUcJ-E&t=558s&ab_channel=SoyDalto)

