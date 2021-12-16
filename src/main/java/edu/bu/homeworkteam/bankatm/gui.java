import javax.swing.*;
import java.awt.event.*;  
class gui{
    private String username;
    public static void main(String[] args) { 
        login();
    }

    public static void login() {    
        // Creating instance of JFrame
        JFrame frame = new JFrame("Login");
        // Setting the width and height of frame
        frame.setLocation( 1500, 400 );
        frame.setSize(900, 1100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
        JPanel panel = new JPanel();    
        // adding panel to frame
        frame.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
        panel.setLayout(null);

        // Creating JLabel
        JLabel userLabel = new JLabel("User:");
        /* This method specifies the location and size
         * of component. setBounds(x, y, width, height)
         * here (x,y) are cordinates from the top left 
         * corner and remaining two arguments are the width
         * and height of the component.
         */
        userLabel.setBounds(300, 250,80,25);
        panel.add(userLabel);

        /* Creating text field where user is supposed to
         * enter user name.
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(400,250,165,25);
        panel.add(userText);

        // Same process for password label and text field.
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(300,300,80,25);
        panel.add(passwordLabel);

        /*This is similar to text field but it hides the user
         * entered data and displays dots instead to protect
         * the password like we normally see on login screens.
         */
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(400,300,165,25);
        panel.add(passwordText);

        // Creating login button
        JButton loginButton = new JButton("login");
        loginButton.setBounds(300, 350, 80, 25);
        loginButton.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        //verify method rturns bool
                        //0 for admin 1 for user
                        //next window

                        /*
                        boolean a=verify(passwordText, UserText);
                        if (a){
                            username=userText.getText()
                            int b=identify(passwordText.getText(), userText.getText());
                            if (b==0){
                                admin();
                            }
                            else{
                                user()
                            }
                        }
                        else{
                            errorPage()
                        }

                        */
                        admin();  
                    }  
                });  
        panel.add(loginButton);

        JButton resetButton = new JButton("reset");
        resetButton.setBounds(400, 350, 80, 25);
        resetButton.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        login();  
                    }  
                });  
        panel.add(resetButton);

        // Setting the frame visibility to true
        frame.setVisible(true);
    }
    
    //private static void test(String a){
      //  JFrame frame = new JFrame("User");
        // Setting the width and height of frame
        //frame.setLocation( 1500, 400 );
        //frame.setSize(900, 1100);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
        //JPanel panel = new JPanel();    
        // adding panel to frame
        //frame.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
        //panel.setLayout(null);

        
        

        // Creating login button
       // JButton button1 = new JButton(a);
        //panel.add(button1);
        //button1.setBounds(10, 80, 80, 25);
        //frame.setVisible(true);
    //}
    

    private static void user() {
        // Creating instance of JFrame
        JFrame frame = new JFrame("User");
        // Setting the width and height of frame
        frame.setLocation( 1500, 400 );
        frame.setSize(900, 1100);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
        JPanel panel = new JPanel();    
        // adding panel to frame
        frame.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
        panel.setLayout(null);

        
        

        // Creating login button
        JButton button1 = new JButton("Create Account");
        button1.setBounds(250, 250, 400, 100);
        button1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        createAccount();  
                    }  
                });  
        panel.add(button1);

        JButton button2 = new JButton("Request Loan");
        button2.setBounds(250, 375, 400, 100);
        button2.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        requestLoan();  
                    }  
                });  
        panel.add(button2);

        JButton button3 = new JButton("View Transactions");
        button3.setBounds(250, 500, 400, 100);
        button3.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        transactions();  
                    }  
                });  
        panel.add(button3);

        JButton button4 = new JButton("Current Balances");
        button4.setBounds(250, 625, 400, 100);
        button4.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        currentBalances();  
                    }  
                });  
        panel.add(button4);

        // Setting the frame visibility to true
        frame.setVisible(true);
        
    }
    private static void createAccount() {
        // Creating instance of JFrame
        JFrame frame = new JFrame("User");
        // Setting the width and height of frame
        frame.setLocation( 1500, 400 );
        frame.setSize(900, 1100);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
        JPanel panel = new JPanel();    
        // adding panel to frame
        frame.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Deposit:");
        /* This method specifies the location and size
         * of component. setBounds(x, y, width, height)
         * here (x,y) are cordinates from the top left 
         * corner and remaining two arguments are the width
         * and height of the component.
         */
        userLabel.setBounds(300, 250,80,25);
        panel.add(userLabel);

        /* Creating text field where user is supposed to
         * enter user name.
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(400,250,165,25);
        panel.add(userText);
        String s1[]={"Savings", "Checking"};
        JComboBox type=new JComboBox(s1);
        type.setBounds(200, 250, 80, 25);
        type.setVisible(true);
        panel.add(type);

        String s2[]={"USD", "EUR", "JPY", "CNY", "GBP","AUD","CAD", "HKD","SGD"};
        JComboBox currency=new JComboBox(s2);
        currency.setBounds(600, 250, 80, 25);
        currency.setVisible(true);
        panel.add(currency);

        // Creating login button
        JButton button1 = new JButton("Submit");
        
        button1.setBounds(300, 300, 80, 25);
        button1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        //boolean a= validate();  
                    }  
                });  
        panel.add(button1);

        JButton button2 = new JButton("Reset");
        button2.setBounds(400, 300, 80, 25);
        button2.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        createAccount();  
                    }  
                });  
        panel.add(button2);

        

        // Setting the frame visibility to true
        frame.setVisible(true);
        

        // Creating login button
        
        
    }
    private static void requestLoan() {
        // Creating instance of JFrame
        JFrame frame = new JFrame("Loan");
        // Setting the width and height of frame
        frame.setLocation( 1500, 400 );
        frame.setSize(900, 1100);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
        JPanel panel = new JPanel();    
        // adding panel to frame
        frame.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Value:");
        /* This method specifies the location and size
         * of component. setBounds(x, y, width, height)
         * here (x,y) are cordinates from the top left 
         * corner and remaining two arguments are the width
         * and height of the component.
         */
        userLabel.setBounds(300, 250,80,25);
        panel.add(userLabel);

        /* Creating text field where user is supposed to
         * enter user name.
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(400,250,165,25);
        panel.add(userText);
        String s1[]={"USD", "EUR", "JPY", "CNY", "GBP","AUD","CAD", "HKD","SGD"};
        JComboBox currency=new JComboBox(s1);
        currency.setBounds(600, 250, 80, 25);
        currency.setVisible(true);
        panel.add(currency);

        // Creating login button
        JButton button1 = new JButton("Submit");
        
        button1.setBounds(300, 300, 80, 25);
        button1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        //createAccount();  
                    }  
                });  
        panel.add(button1);

        JButton button2 = new JButton("Reset");
        button2.setBounds(400, 300, 80, 25);
        button2.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        //requestLoan();  
                    }  
                });  
        panel.add(button2);

        

        // Setting the frame visibility to true
        frame.setVisible(true);
        
    }

    private static void transactions() {
        // Creating instance of JFrame
        JFrame frame = new JFrame("Transactions");
        // Setting the width and height of frame
        frame.setLocation( 1500, 400 );
        frame.setSize(900, 1100);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
        JPanel panel = new JPanel();    
        // adding panel to frame
        frame.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Value:");
        /* This method specifies the location and size
         * of component. setBounds(x, y, width, height)
         * here (x,y) are cordinates from the top left 
         * corner and remaining two arguments are the width
         * and height of the component.
         */
        userLabel.setBounds(300, 250,80,25);
        panel.add(userLabel);

        /* Creating text field where user is supposed to
         * enter user name.
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(400,250,165,25);
        panel.add(userText);
        String s1[]={"USD", "EUR", "JPY", "CNY", "GBP","AUD","CAD", "HKD","SGD"};
        JComboBox currency=new JComboBox(s1);
        currency.setBounds(600, 250, 80, 25);
        currency.setVisible(true);
        panel.add(currency);

        // Creating login button
        JButton button1 = new JButton("Submit");
        
        button1.setBounds(300, 300, 80, 25);
        button1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        //createAccount();  
                    }  
                });  
        panel.add(button1);

        JButton button2 = new JButton("Reset");
        button2.setBounds(400, 300, 80, 25);
        button2.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        //requestLoan();  
                    }  
                });  
        panel.add(button2);

        

        // Setting the frame visibility to true
        frame.setVisible(true);
        
    }

    private static void currentBalances() {
        // Creating instance of JFrame
        JFrame frame = new JFrame("Balances");
        // Setting the width and height of frame
        frame.setLocation( 1500, 400 );
        frame.setSize(900, 1100);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
        JPanel panel = new JPanel();    
        // adding panel to frame
        frame.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Value:");
        /* This method specifies the location and size
         * of component. setBounds(x, y, width, height)
         * here (x,y) are cordinates from the top left 
         * corner and remaining two arguments are the width
         * and height of the component.
         */
        userLabel.setBounds(300, 250,80,25);
        panel.add(userLabel);

        /* Creating text field where user is supposed to
         * enter user name.
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(400,250,165,25);
        panel.add(userText);
        String s1[]={"USD", "EUR", "JPY", "CNY", "GBP","AUD","CAD", "HKD","SGD"};
        JComboBox currency=new JComboBox(s1);
        currency.setBounds(600, 250, 80, 25);
        currency.setVisible(true);
        panel.add(currency);

        // Creating login button
        JButton button1 = new JButton("Submit");
        
        button1.setBounds(300, 300, 80, 25);
        button1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        //createAccount();  
                    }  
                });  
        panel.add(button1);

        JButton button2 = new JButton("Reset");
        button2.setBounds(400, 300, 80, 25);
        button2.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        //requestLoan();  
                    }  
                });  
        panel.add(button2);

        

        // Setting the frame visibility to true
        frame.setVisible(true);
        
    }

    private static void admin() {
        // Creating instance of JFrame
        JFrame frame = new JFrame("Admin");
        // Setting the width and height of frame
        frame.setLocation( 1500, 400 );
        frame.setSize(900, 1100);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
        JPanel panel = new JPanel();    
        // adding panel to frame
        frame.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
        panel.setLayout(null);

        
        

        // Creating login button
        JButton button1 = new JButton("Check Customers");
        button1.setBounds(250, 250, 400, 100);
        button1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        checkCustomers();  
                    }  
                });  
        panel.add(button1);

        JButton button2 = new JButton("Daily Report");
        button2.setBounds(250, 375, 400, 100);
        button2.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        dailyReport();  
                    }  
                });  
        panel.add(button2);

        JButton button3 = new JButton("Pay Interest");
        button3.setBounds(250, 500, 400, 100);
        button3.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        payInterest();  
                    }  
                });  
        panel.add(button3);

        JButton button4 = new JButton("Maintain Stocks");
        button4.setBounds(250, 625, 400, 100);
        button4.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        maintainStocks();  
                    }  
                });  
        panel.add(button4);

        // Setting the frame visibility to true
        frame.setVisible(true);
        
    }

    private static void checkCustomers() {
        // Creating instance of JFrame
        JFrame frame = new JFrame("Admin");
        // Setting the width and height of frame
        frame.setLocation( 1500, 400 );
        frame.setSize(900, 1100);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
        JPanel panel = new JPanel();    
        // adding panel to frame
        frame.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
        panel.setLayout(null);

        
        

        // Creating login button
        JButton button1 = new JButton("Check Specific Customer");
        button1.setBounds(250, 250, 400, 100);
        button1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        //createAccount();  
                    }  
                });  
        panel.add(button1);

        JButton button2 = new JButton("Check all Customers");
        button2.setBounds(250, 375, 400, 100);
        button2.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        //requestLoan();  
                    }  
                });  
        panel.add(button2);

        

        // Setting the frame visibility to true
        frame.setVisible(true);
        
    }

    private static void dailyReport() {
        // Creating instance of JFrame
        JFrame frame = new JFrame("Admin");
        // Setting the width and height of frame
        frame.setLocation( 1500, 400 );
        frame.setSize(900, 1100);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
        JPanel panel = new JPanel();    
        // adding panel to frame
        frame.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
        panel.setLayout(null);

        
        

        // Creating login button
        JButton button1 = new JButton("Check Specific Customer");
        button1.setBounds(250, 250, 400, 100);
        button1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        //createAccount();  
                    }  
                });  
        panel.add(button1);

        JButton button2 = new JButton("Check all Customers");
        button2.setBounds(250, 375, 400, 100);
        button2.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        //requestLoan();  
                    }  
                });  
        panel.add(button2);

        

        // Setting the frame visibility to true
        frame.setVisible(true);
        
    }

    private static void payInterest() {
        // Creating instance of JFrame
        JFrame frame = new JFrame("Interest");
        // Setting the width and height of frame
        frame.setLocation( 1500, 400 );
        frame.setSize(900, 1100);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
        JPanel panel = new JPanel();    
        // adding panel to frame
        frame.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
        panel.setLayout(null);

        
        
        JLabel userLabel = new JLabel("Interest has been paid");
        /* This method specifies the location and size
         * of component. setBounds(x, y, width, height)
         * here (x,y) are cordinates from the top left 
         * corner and remaining two arguments are the width
         * and height of the component.
         */
        userLabel.setBounds(300, 250,80,25);
        panel.add(userLabel);
        

        //pay interest method

        // Setting the frame visibility to true
        frame.setVisible(true);
        
    }

    private static void maintainStocks() {
        // Creating instance of JFrame
        JFrame frame = new JFrame("Admin");
        // Setting the width and height of frame
        frame.setLocation( 1500, 400 );
        frame.setSize(900, 1100);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
        JPanel panel = new JPanel();    
        // adding panel to frame
        frame.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
        panel.setLayout(null);

        
        

        // Creating login button
        JButton button1 = new JButton("Update Stock Price");
        button1.setBounds(250, 250, 400, 100);
        button1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        //updateStock();  
                    }  
                });  
        panel.add(button1);

        JButton button2 = new JButton("Add to Stock List");
        button2.setBounds(250, 375, 400, 100);
        button2.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        //addStock();  
                    }  
                });  
        panel.add(button2);

        JButton button3 = new JButton("Remove from Stock List");
        button3.setBounds(250, 375, 400, 100);
        button3.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        //removeStock();  
                    }  
                });  
        panel.add(button3);

        

        // Setting the frame visibility to true
        frame.setVisible(true);
        
    }
}
