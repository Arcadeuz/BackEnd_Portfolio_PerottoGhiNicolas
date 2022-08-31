# BackEnd_Portfolio_PerottoGhiNicolas
BackEnd del Proyecto Integrador para Argentina Programa

Una aplicación Spring Boot para actuar como Backend para Mi sitio web. El Frontend también es un proyecto git disponible en la siguiente ubicación: -
https://github.com/Arcadeuz/FrontEnd_Portfolio_PerottoGhiNicolas

# Características importantes:-

1. Estructura adecuada del proyecto con modelos, repositorio, servicios y dtos.
2. Los Modelos están estructurados de acuerdo con el objeto de colección MYSQL.
3. Los DTO se han escrito como réplicas de las clases de modelo que interactúan entre la interfaz de usuario (aplicación) y la capa de servicio, mientras que los modelos interactúan entre la capa de servicio y base de datos.
4. Lombok se ha utilizado para generar Getter Setters y Constructors donde sea necesario para las diversas clases, lo que reduce la cantidad de código repetitivo que se debe escribir.
5.  Spring Data JPA se ha utilizado para conectarse con la configuración de la base de datos MYSQL en el servicio clevercloud.
6.  Autenticación de APIs basada en tokens con Spring y JWT .
7. La aplicación está alojada en Heroku en la siguiente ubicación:-
https://backendportfoliomnpg.herokuapp.com
10. Y la aplicación de interfaz de usuario general también se aloja a través de Heroku y se puede acceder a ella en:-

# Funcionalidad del Proyecto:-
Soporta Multiples Usuario, los cuales pueden tener una o mas personas
Cada persona tiene:
-Acerca De Mi
-Educacion
-Experiencia Laboral
-Habildades
-Proyectos


Como implementar el proyecto en CleverCloud, Heroku y Firebase:
https://drive.google.com/file/d/1EfybrW7PNoh8FzrPlxqxe31jB3_jSJXJ/view?usp=sharing
(Extracto del resemun de clases de Argentina Programa)


# Importante:-

Es necesario que una vez se creada la base de datos, se carguen en la BD de Rol lo siguiente:
id| Rol
1 | ROL_USER
2 | ROL_ADMIN
