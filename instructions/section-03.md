# Section 3

#### The Brief
Now that we have production models and services to manage them we can pull everything together and add a UI to allow a user to interact with the application.

### Objectives
* Create a class responsible for user input and output
* Create a class to initialize the application
* Create a user interface to create, read, update and delete products

## Part 1 - Input Output
We will need to allow user input in order for the application to function. In order to do this we will use the Scanner class to accept input and methods from the System.out package to print to the console. 
In order to keep our code a bit cleaner as well as add a layer of abstraction we will use a Console class. 

```
package io;

public class Console {

}
```

Notice that I've added this to an _io_ package.

Now we can keep repetitious System.out and Scanner calls out of the main application code. We can also use this class to print large output strings that would bloat our code.

```
public class Console {
    public static void printWelcome(){
        System.out.println("" +
                "**************************************************" +
                "***           Welcome and Bienvenue            ***" +
                "***                    to                      ***" +
                "***          ZipCo Inventory Manager           ***" +
                "**************************************************");
    }

}
```

Having this print line string in the code is large and litters up the main code. Having it abstracted away allows us to keep the main code clean and easy to read. We will simply call the _printWelcome()_ behaviour wherever 
we want to call this code.

```
Console.printWelcome()
```

Whenever we want to capture user input or display output we will create methods in the Console class to handle these behaviours.

## Part 2 - Application class
Now it is time to put all of these classes we have created to work. We will begin by creating an App class to initialize the application logic and initialize the services. This is the top most class and will start the program

```
public class App {
	public static void main(String... args){
	
	}
}
```

We will use the special _main()_ method to instantiate the App class and initialize logic. 


## Part 3 - Initialize
We can continue to Initialize objects that the application needs to run.

```
public class App {

	private SneakerService sneakerService = new SneakerService(); // (1)
	
	public static void main(String... args){
		App application = new App(); // (2)
		application.init();  // (3)
	}
	
	public void init(){
		// (4)
		// application logic here 
		// call methods to take user input and interface with services
		Console.printWelcome();
	}
}
```

1. Create the services needed to manage inventory
2. Instantiate the application
3. Call a method to initialize the application
4. Use this method to kick off application logic

Now that we have a way to start the application, create the rest of the code to interact with a user. 
Your application should have a main menu that will allow a user to do the following:

* Create different products to be added to inventory
* Read from existing products
* Update products
* Delete products
* Get different reports about products
* Exit the program

## Conclusion

Creating a Console class allowed us to abstract away user interface components of the code. Now the main code isn't concerned with how UI is done, it is only concerned with what it does. We created an application class to 
house the elements of the program. Finally, creating a user interface to allow a user to interact (Create, Read, Update, and Delete) with items in the inventory.



