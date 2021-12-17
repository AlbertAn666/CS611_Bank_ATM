# CS611_Bank_ATM

## compiling and running
1. Please make sure to install the 'lombok' plugin for the IntelliJ IDEA 
https://plugins.jetbrains.com/plugin/6317-lombok
otherwise the code will be shown abnormally. 

2. Before running the project, please setup the your own database connection in file resources/application.properties
for reference, please google "mysql install" and "mysql remote connection".

3. For the entity data structure design and specification, please directly refer to the 
   code in 'entities' package and comments with it.

## classes 
1) Entities:
Account, Customer, Shareholdings, Collateral, Loan, Stock, Transaction

2) PageUIs:
   AccountInfoFrame- frame to show the account information
   AddStockFrame- frame for admin to add stock
   BorrowLoanFrame- frame for customer to borrow loan
   BuyStockFrame- frame for customer to buy stocks
   CheckCustomerFrame- frame for checking on a specific customer
   CustomerAccountsFrame- frame for the admin to check the account of the customer
   CreateAccountFrame- frame for the customer to create account
   DepositFrame- frame for the customer to deposit money
   GuiConfiguration- the entrance of the GUI
   GuiManager- starts the GUI and handles affairs.
   HomeFrame- the main frame of customer and displays options for customer
   LoginFrame- the frame for log in
   ManagerMenuFrame- displays options for manager(admin) input
   ModifyStockFrame- the frame for the admin modifying stocks
   PromptFrame- shows the messages
   RepayLoanFrame- the frame for customer to repay the loan
   SellStockFrame- the frame for customer to sell stocks
   ShowTransactionFrame- shows transactions
   StockMarketFrame- the frame for the admin to operate with stocks
   TransferFrame- the frame for the customer to transfer money
   ViewCollateralFrame- view all the collateral
   ViewStocksFrame- use for customer to view stocks
   WithdrawFrame- use for customer to withdraw money

3) Services
LoginService- the login and register service logic for customer and admin(manager)
CustomerService- the customer bank service logic, including create accounts, delete accounts, deposit, withdraw, transfer money, view information, etc.
CustomerStockService-the customer stock service logic, including creating a stock account, buying/selling stocks, showing stocks information, showing profit, etc.
ManagerService- the manager bank service logic, including pay interests and charge interest, show information of customers, show transactions information, etc.
ManagerStockService- the manager stock service logic, including add stocks, maintain stocks, modify price, etc.
ServiceConfig- configurations of services

4) Repository
- AccountRepository Interface- account repository, deals with the data access and storage of accounts
- CustomerRepository Interface- customer repository, deals with the data access and storage of customers
- StockRepository Interface- stock repository, deals with the data access and storage of stocks
- TransactionRepository Interface- transaction repository, deals with the data access and storage of transactions
- UniversalRepository- handles complicated data queries

## Design Pattern and features
**Layered Architecture**  
The project is split into 3 layers - the presentation layer, the service layer and the data access layer.
Using the layered architecture makes the project simplified and clarified. And also makes it scalable and reliable.

**Singleton Pattern**  
We applied Singleton Pattern to the GuiManager class, which manages and handles GUI affairs, as well as holds the references
 of services and repositories. In this case, only one instance of GuiManager class in needed, hence the application of Singleton Pattern for GuiManager is appropriate.

**Dependency Injection**  
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