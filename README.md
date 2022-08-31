# BackEnd_Portfolio_PerottoGhiNicolas
BackEnd del Proyecto Integrador para Argentina Programa

A Spring Boot applicaiton to act as a backend for My Website. The front end is also a git project available at the following location :-

https://github.com/subhamsaha-manu/personal-porfolioUI.

Kindly, go through the Readme.md file to understand how to deploy or view the app.

As mentioned this project is a backend written in Spring Boot. Few salient features are :-

Salient Features :-
Proper project structure with models, repository,services and dtos.
The Models are structured in accordance with the MongoDB collection object.
DTOs have been written as replicas of the Model classes which interact between the UI(Application) and Service Layer, whereas the Models interact between the Service and Database layer.
To seamlessly map between the Model and DTO I have used MapStruct api's, and also introduced some custom mapping rules.
Lombok has been used to generate Getter Setters and Constructors where ever required for the various classes, which reduce the amount of boilerplate code to be written.
Spring Data has been used to connect with the Mongo database setup in mLab service of Heroku.
Spring Email has been used to send email notification when any user wants to connect via the Contact Me form in the UI app.
Application wide logging has also been introduced with the help of Lombok and relevant annotations.
The individual app is hosted in Heroku in the following location :- https://dashboard.heroku.com/apps/my-personal-porfolio-app
And the overall UI app is also hosted via Heroku and is accessible at :- https://subham-sportfolio.herokuapp.com/
The master branch of this project also acts as a template for future Spring Boot apps.
For any queries or issues feel free to contact at :- subhamsaha90@gmail.com
