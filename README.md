# CS611_Bank_ATM

Hey guys,

1. Please make sure to install the 'lombok' plugin for the IntelliJ IDEA 
https://plugins.jetbrains.com/plugin/6317-lombok
otherwise the code will be shown abnormally. 

2. Before running the project, please setup the your own database connection in file resources/application.properties
for reference, please google "mysql install" and "mysql remote connection".

3. For the entity data structure design and specification, please directly refer to the 
   code in 'entities' package and comments with it.

Gung (Peng Huang)




Singleton Pattern
We applied Singleton Pattern to the GuiManager class, which manages and handles GUI affairs, as well as holds the references
 of services and repositories. In this case, only one instance of GuiManager class in needed, hence the application of Singleton Pattern for GuiManager is appropriate.


Dependency Injection
In software engineering, dependency injection is a technique in which an object receives other objects that it depends on,
called dependencies (referring to Wikipedia). In our project, we used Dependency Injection powered by Spring Framework, 
through which the Spring container injects objects of Services and Repositories into other objects. 
As we can see, all the member variables as type of Service and Repository were not assigned with new() object explicitly but annotated with @Autowired, 
which indicated the injection of dependency to get free from manual instantiation. The application of Dependency Injection 
allows for loose coupling of components and moves the responsibility of managing components onto the container.


