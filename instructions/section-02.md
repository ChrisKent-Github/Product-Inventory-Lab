# Section 2

#### The Brief
Continuing from Section 1, we will begin to code the models(classes that represent the objects in the application). 
Using TDD we will consider our implementation first in JUnit Tests and then write the logic make our test pass. 
This will insure the objects behave as intended.

### Objectives
* Continue using git version control
* Create test and implementations of data models
* Create test and implementations of data services

## Part 1 - Properties
As of right now our models are empty and we need to add some properties. We need to think about what is needed to properly represent 
an item in our inventory. 

For example:

**Sneaker**

A sneaker item from inventory

**Properties**

- id: int
- name: String
- brand: String
- sport: String
- size: float
- qty: int
- price: float


These will be enough properties for now. If I ever need to add or remove I can come back and make a change. 

Take a moment and think about the properties that you want to represent the items from your store.

Now it's is time to update our models to reflect these attributes. Use git to create a new branch to work on.

```
git checkout -b feature-sneaker-properties
```

Open up one of your models and add create class members to match the properties you used earlier. 

**main/java/models/Sneaker.java**

```java
package models;

public class Sneaker {
    private int id;
    private String name;
    private String brand;
    private String sport;
    private int size;
    private int qty;
    private float price;
}
```

I make sure to keep my objects properties private so that I can mantain control of how other objects access this object.

Now we can add, commit and merge the changes back to 'dev' branch. And do the same for the remaining models in the inventory. 


## Part 2 - Accessors and Mutators
We have a pretty nice class with properties that we can use to create lots of objects with. But unfortunately we can't access or
change any of the properties. Let's make some changes so we allow access to these object fields.

Because we are Test Driven Developers we will define how we expect the object to behave through tests first. Then create the logic 
to execute this behavior.

As always, create a new branch to develop on and open a models test file. I will be continuing with a SneakerTest class for the 
Sneaker object.

I will start with testing the behavior of setting the 'name' property. So I will use the pattern of create a getter and setter 
for public access to this property.

**test/java/models/SneakerTest.java**


```java
public class SneakerTest {
    
	@Test
	public void setNameTest() {
	    // given (1)
	    String expected = "OZWEEGO";
	    
	    // when (2)
	    testSneaker = new Sneaker()
	    testSneaker.setName(expected);
	    
	    // then (3)
	    Assertions.assertEquals(expected, testSneaker.getName());
	}
}
```

1. This is our test data that we will compare to the actual values returned from the object.
2. We create a new Sneaker object for testing. Then we invoke *setName()* passing in the test data. 
3. Retrieve the objects 'name' value with ```getName()``` and compare with the value that is expected.

The IDE may be upset because ```setName()``` and ```getName()``` don't exist. Thats ok, we will fix this next.

Back in the model we can continue adding ```setName()``` and ```getName()```

**main/java/models/Sneaker.java**

```java
public void setName(String name) {
    this.name = name;
}
```
After the logic is complete, test to make sure everything is working as expected. Once the test is passing continue with the rest 
of the object properties.

1. Create the test
2. Implement the logic
3. Test logic

Once you complete this model, commit your changes to git and continue testing your other models.


## Part 3 - Contructors

Now is a good time to think about how these objects will be created. If we create an object from one of the models like so:
```
Sneaker sweetAdidas = new Sneaker();
```

We will get back a Sneaker object, but none of the properties will be set. In some situations this maybe how you want your object to behave. But I want to use the constructor to initialize the objects properties.

```java
Sneaker sweetAdidas = new Sneaker(6, "Stan Smith", "Adidas", "Tennis", 10.5f, 10, 80.00f);
```

To begin create a new git branch to work on. We will open the test for this object and start with the above implementation.

**test/java/models/SneakerTest.java**

```java
public class SneakerTest {
		
    @Test // (1)
    public void constructorTest(){
    	  
        // (2)
        int expectedId = 6;
        String expectedName = "Stan Smith";
        String expectedBrand = "Adidas";
        String expectedSport = "Tennnis";
        int expectedQty = 10;
        float expectedPrice = 80.00f;

        // (3)
        Sneaker testSneaker = new Sneaker(expectedId, expectedName, expectedBrand,
                expectedSport, expectedQty,expectedPrice);

        // (4)
        Assertions.assertEquals(expectedId, testSneaker.getId());
        Assertions.assertEquals(expectedName, testSneaker.getName());
        Assertions.assertEquals(expectedBrand, testSneaker.getBrand());
        Assertions.assertEquals(expectedSport, testSneaker.getSport());
        Assertions.assertEquals(expectedQty, testSneaker.getQty());
        Assertions.assertEquals(expectedPrice, testSneaker.getPrice());
    }
}
```
1. The test annotation in needed to notify JUnit to treat this method as a test.
2. We create data to use during the test. We will pass these values into the constructor and expect the same values upon retrieval. 
3. Create a new object from the test data created above.
4. Use accessor methods to retrieve the object's data and assert that it is equal to what was passed into the constructor.

Remember to go to git and add/commit/merge your code

Now complete the constructors for the rest of your models.

## Part 4 - Managing the Data with a Service

At this point we have some really good objects with some fantastic test. Now we need to think about how we would manage these objects. 

If I wanted to create a few objects it would look a little like this:

```
Sneaker s1 = new Sneaker(arg1, ag2, arg3...);
Sneaker s2 = new Sneaker(arg1, ag2, arg3...);
Sneaker s3 = new Sneaker(arg1, ag2, arg3...);
```

But the store is doing so well we will need to keep track of hundreds maybe thousands of sneakers. I could use an ArrayList to
hold all of the sneakers I make.

```
List<Sneaker> sneakerList = new ArrayList<Sneaker>();

Sneaker s1 = new Sneaker(arg1, ag2, arg3...);
Sneaker s2 = new Sneaker(arg1, ag2, arg3...);
Sneaker s3 = new Sneaker(arg1, ag2, arg3...);

sneakerList.add(s1);
sneakerList.add(s2);
sneakerList.add(s3);
```

or

```
List<Sneaker> sneakerList = new ArrayList<Sneaker>();

sneakerList.add(new Sneaker(arg1, ag2, arg3...));
sneakerList.add(new Sneaker(arg1, ag2, arg3...));
sneakerList.add(new Sneaker(arg1, ag2, arg3...));
```

This looks good, but we can take this a little further. The problem here is that if someone forgets to add a sneaker to the array it 
will not be managed and possible forgotten :( This will not be good, especially if we start doing batch processing on items in the 
array. If an item wasn't added then it will not be apart of the batch.

Another problem is when we look to manipulate the collection of objects. Task such as finding objects and removing objects may need a
few lines of code to accomplish. This can start to add up if we are creating, reading, updating and deleting objects from the array.

A solution these problems is to abstract away these processes into a class that will manage the objects. A class that will proved the 
service of maintaining these objects.

This is where Service classes comes handy. It will have methods for creating, finding and deleting objects from the list.

As always start with a new branch

**main/java/services/SneakerService.java**

```java
public class SneakerService {

    private static int nextId = 1;  // (1)

    private AList<Sneaker> inventory = new ArrayList<>();  // (2)
}
``` 

1. This is a static member that I will use to create id numbers for new objects. We will see more of this later.
2. This is the collection that will hold all of the objects.

As stated earlier, we are going to lean on this class to create, read, update, and delete. Lets go to the service test class and 
start with the sneaker creation behaviour.

**/test/java/services/SneakerServiceTest.java**

```
public class SneakerServiceTest {
    @Test
    public void createTest(){

        // (1)
        String expectedName = "Stan Smith";
        String expectedBrand = "Adidas";
        String expectedSport = "Tennis";
        int expectedSize = 10.5;
        int expectedQty = 10;
        float expectedPrice = 80.00f;
        
        // (2)
        SneakerService sneakerService = new SneakerService();
        Sneaker testSneaker = SneakerService.create(expectedName, expectedBrand,
         expectedSport, expectedSize, expectedQty, expectedPrice);

        // (3)
        int actualId = testSneaker.getId();
        String actualName = testSneaker.getName();
        String actualBrand = testSneaker.getBrand();
        String actualSport = testSneaker.getSport();
        int actualSize = testSneaker.getSize();
        int actualQty = testSneaker.getQuantity
        float actualPrice = testSneaker.getPrice();

        // (4)
        Assertions.assertEquals(Integer.class.getName(), new Integer(actualId).getClass().getName());
        Assertions.assertEquals(expectedName, actualName);
        Assertions.assertEquals(expectedBrand, actualBrand);
        Assertions.assertEquals(expectedSport, actualSport);
        Assertions.assertEquals(expectedSize, actualSize);
        Assertions.assertEquals(expectedQty, actualQty);
        Assertions.assertEquals(expectedPrice, actualPrice);

    }
}
```

1. We create some test data that we will use to create a test Sneaker.
2. First I instantiate a SneakerService object. Then I will use ```create(arg1, arg2, arg3...)``` to have the service create and 
3. return a new sneaker object.
4. Using accessor methods to capture the data from the newly created sneaker.
5. Check/Assert that the data passed into the SneakerService was properly assigned to the new sneaker object returned.

Now it's time to implement the create sneaker logic:

**/main/java/services/SneakerService.java**

```java
// (1)
public Sneaker create(String name, String brand, String sport, int size, int quantity, float price) {
    
    // (2)
    Sneaker createdSneaker = new Sneaker(nextId++, name, brand, sport, size, quantity, price);
    
    // (3)
    inventory.add(createdSneaker);
    
    // (4)
    return createdSneaker;
}
```

1. A create method that will accept arguments and return a new Sneaker instance.
2. Make a new Sneaker instance and pass the values receive from the line above into the constructor.
3. Add the new sneaker to the ArrayList to be managed.
4. Return the new object to the caller.

Notice the ```nextId++``` in the second line. I am using the static member *nextId* to assign id numbers to new objects and incrementing it by 1. This will then be assigned to the next object created.

Check to make sure the test passed and continue adding the following behaviours: find/findAll/delete

Using my example the following would be my method signatures for these operations.

```
//read
public Sneaker findSneaker(int id) {
	// should take an int and return an object with that id, if exists
}

//read all
public Sneaker[] findAll() {
    // should return a basic array copy of the ArrayList
}

//delete
public boolean delete(int id) {
    // should remove the object with this id from the ArrayList if exits and return true.
    // Otherwise return false
}
```
Dont forget to write your tests first and commit along the way!

Once you have completed this service, move on to the other services you will need to manage the other models.

Now if we ever need to change the way we create, read, update, delete these objects we can do it here in this service object. 
It will then be reflected everywhere the service is used to do these operations. 



## Conclusion

We continued using git to make incremental changes to our program to ensure we have a recovery route if necessary. We added properties, getters and setters to the models and tested them as proof of implementation. 
Finally we created services for the models to help manage and centralize code.