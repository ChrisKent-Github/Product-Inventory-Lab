# Section 1

#### The brief
Imagine you have a fantastic store where you buy/sell/trade a great product. Business if good and the inventory has grown alot. 
It's time to find a better way to manage the inventory.
So you decide to put some shiny new Java skills to work.

In this section we start with an empty Java project and start to setup our environment. Getting our testing framework in place and 
thinking about the objects we will create to compose the application.

### Objectives
* Use version control to create and track a repository of code
* Import a testing framework to support Test Driven Development(TDD)
* Creating classes stubs for our products

## Part 1 - Repository

1. Log into github.com
2. Fork github project
3. Clone you version of the project to your machine
4. Create development(dev) branch

There is a start project waiting for you on Git Hub. Fork this repo, and clone your copy to a folder on your local machine.

```
git clone https://github.com/Zipcoder/Product-Inventory-Lab
```

Now that we have a forked copy on our local machine we can create a development branch(aka dev branch.) Rememeber to also change
directories into the newly cloned repo.

```
cd ./Product-Inventory-Lab
```

We want to create a branch to house all of our development work. This way we save the master branch for working code releases. 

```
git branch dev
git checkout dev
or
git checkout -b dev  //branch and chekout in one command
```
Now we will always use the 'dev' branch as a starting piont to build new features and bug fixes.

## Part 2 - Testing

1. Switch to development branch
2. Create feature branch
3. Add Testing framework to pom.xml file
4. Update git and push to github

Now it's time to get our testing environment set up. In terminal, navigate to your project directory.

```
cd /path/to/project/
```

A _feature branch_ is a branch that we create to develop a feature on. When we have completed the code for the feature we then merge 
it into the 'dev' branch code. And repeat this cycle for all features we develop in the future.

To reiterate the cycle:

1. Create a feature branch from dev branch
2. Work on feature branch until complete	
3. Merge feature branch into dev branch
4. Push work to github

In the last chapter we created a 'dev' branch to use as a starting point for developing the application features. Lets checkout out 
that branch so we can create a _feature branch_ from it.

```
git checkout dev
git branch feature-test-config
git checkout feature-test-config
```

First we checkout dev to move into that branch. Then we create a new branch for the feature we want to add to the application. 
In this case, since we are adding the configuration for the testing environment, the name feature-test-config. But a branch can be
name anything you want...use this power wisely.

> It is common to have feature branch name use the prefix "feature"
> and bug fix branches prefixed "bug". this makes it easy to see what branches are for what purpose

It is time to get the testing suite imported. Open IntelliJ and choose to **import project**. When the file finder window opens,
Navigate to the **pom.xml** file located in the root of your project repository.

> IntelliJ may prompt you to auto import dependencies, choose yes.

Once the project is loaded into your IDE, open pom.xml. Add the following code, nested inside of the *project* xml tag.

```
    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.4.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.4.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

Java core does not come with a testing suite but many 3rd party testing options are available to us. One of these third party testing 
suites is JUnit. JUnit is very popular for testing Java and it will be what we use here. 

The pom.xml file is a way for a developer to define configuration options for a Java application. It also makes it easy to import and 
manage libraries and frameworks that are needed in the project.

To confirm that the JUnit testing suite was imported, you can look in the 'external libraries' folder inside the project and find the 
JUnint libraries listed.

That is all we have to do to import JUnit. Now that our feature is done we can commit our changes and merge them back into our dev 
branch.

```
git status
git add .
git commit -m 'add junit testing suite'
git status

git checkout dev
git merge feature-test-config
git status
```

At this point is up to you what you want to do with the feature-test-config branch. It can be deleted or you can keep it around.
I am choosing to delete it. Also this is a good place to push your changes up to Github.

```
git branch -d feature-test-config  // delete branch
```

```
git push  // push changes to github
```

## Part 3 - Classes

Now it's time to thing about the Objects that will be apart of our inventory application. We will definately need a product to inventory.
Come up with 2 products for your store, and thing about how you would represent them as Java objects.

As an example I will be using a store that sells Sneakers and Whiskey. So, I know I will definately have a Sneaker class and a Whiskey
class. We will see later on that we will create a few more classes to help manage our inventory.

1. Create feature branch
2. Create packages for models, services and test 
3. Create models, service and test stubs
4. Update git and push to github

First, we will checkout the dev branch and create a new feature branch. Now switch to the newly created branch.

```
git checkout dev
git branch feature-create-classes
git checkout feature-create-classes
```

Back in your Java IDE...

Because creating objects are common and frequent, we need to keep things organized. We will do this by using packages. These packages 
help us organized things very similarly as we would files on our operating system. 

Inside the *Project* tab, located in top right of IDE

The **src** package is where we will put most of our files. Inside you will find a **main** and a **test** package. 

* **main** - This is where the main application code is located
* **test** - This is where test classes are located

And inside both packages you will find a **java** folder. It is inside these "java" folders you will put you work. We also want 
to keep the folder structures between "main" and "test" packages the same. Let look at what this means.

Lets create two packages inside main->java and lets call them "models" and "services"

Many times developers will call the classes that represent and object as a "model". This package will contain the different products 
that are going to be inventoried. 

```
main
├── java
│   ├── models
│   └── services
└── resources
```

As was stated earlier the "test" and "main" packages need to mirror each outher. So lets create "models" and "services" packages 
inside the test->java package.

The final package structure should look like the following.

```
├── main
│   ├── java
│   │   ├── models
│   │   └── services
│   └── resources
└── test
    └── java
        ├── models
        └── service
```

Let create the class files for the products in our store. For my example store I will make a "Sneaker" class and a "Whiskey" class.

```
src/main
├── java
│   ├── models
│   │   ├── Sneaker.java
│   │   └── Whiskey.java
│   └── services
│       ├── SneakerService.java
│       └── WhiskeyService.java
└── resources
```
Because our store is so successful, we are really moving through inventory. So I would be nice to have a class that would help 
maintain all the items. A class that provided the service of creating and managing the items. For my example I will create a 
"SneakerService" class and a "WhiskeyService" class inside the services package.

Because we are Doing Test Driven Development (TDD) we need to also have test classes for the models and the services.

For my example store I will have the follwing test classes.

```
src
├── main
│   ├── java
│   │   ├── models
│   │   │   ├── Sneaker.java
│   │   │   └── Whiskey.java
│   │   └── services
│   │       ├── SneakerService.java
│   │       └── WhiskeyService.java
│   └── resources
└── test
    └── java
        ├── models
        │   ├── SneakerTest.java
        │   └── WhiskeyTest.java
        └── services
            ├── SneakerServiceTest.java
            └── WhiskeyServiceTest.java
```

This is enough for our feature. We can *add* and *commit* our changes. Then merge them back into the dev branch and push to github.

```
git add .
git commit -m 'add sneaker/whiskey models and services'
git checkout dev
git merge feature-create-classes
git branch -d feature-create-classes
```

## Conclusion

In this section we used git inside the terminal to connect with guthub to handle our version control. We used the pom.xml file to 
import JUnit so that we can test our code as we develop. Using packages to organize our code, we created some classes to represent 
products in the application.

Now it is time to add some functionality to the classes, we will do this in the next section
