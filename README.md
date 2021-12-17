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

GUI Relationship (Design of GUI and operation logic)
1. HomeFrame as the center of operations and user experience. Similar to Home Screen in Apple iOS, the HomeFrame is the home of the bank ATM. 
No matter where you are, you can go back to the HomeFrame easily because the HomeFrame always stays there, never closes, unless you quit the program, 
which offers a huge sense of safety and rely. 

The information of accounts is shown in the HomeFrame, which can be directly accessed as soon as users log in. 
The reason why we put the information of accounts in the HomeFrame rather than conceal it in other window 
is that we think the information of accounts is the most important information for users. So it should be the most conspicuous. 


The menu bar in HomeFrame is the launch hub of all the business and operations, such as transfer, withdraw, loans, stocks, etc, 
which realizes great extensibility of the GUI design. For example, if in the future, we need to add other business to our bank ATM, like funds, exchange, assets management, etc., 
we can simply add these business as menu items in the menu bar. 


2. One business, one window; one operation, one window. When a user is viewing the business of stocks, there is a stand-alone window of ViewStocksFrame for him. When he is transferring, 
he can easily operate in the separate window of TransferFrame. the benefits of these kind of design is it can make user concentrate on the current business and operation, keeping users from distractions. 
Whats more, it allows for loose coupling of GUI windows and realizes great extensibility. 