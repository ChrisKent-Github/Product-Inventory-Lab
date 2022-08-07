# Section 5

In this final section we will look at utilizing a common and popular data exchange format called JSON. With JSON we can easily represent an objects data to be passed around to other systems or saved to file. 
The latter is what we will focus on in the this section

### Objectives
* Import a third party library
* Read in JSON data
* Write JSON data

## Part 1 - Setup
Lucky for us JSON is such a common format there are thrid party libraries to help manage this. One such library is called Jackson. First we need to add the Jackson library to our project via the pom.xml.

```
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.10.1</version>
</dependency>
```

With the Jackson dependency add to the pom.xml file we are set to use the Jackson library in our code

## Part 2 - Reading in JSON

We will use objests available to us from Jackson to read a JSON file. Then map the JSON data to the Java objects it represents, in this case the objects in our inventory.

```
ObjectMapper objectMapper = new ObjectMapper();
this.inventory = objectMapper.readValue(new File("sneaker.json"), new TypeReference<List<Sneaker>>(){});
```

Jackson offers us a ObjectMapper class that maps an objects data to JSON data and vise-versa. Next we call the ```.readValue``` method passing in two aurguments. The first is a file to read from. The second is the type of objects we want create 
from the JSON data.

> notice the *new TypeReference* object that is instantiated. This is a Jackson object used to help reference the type of objects we want to create. Read the Jackson api for more

## Part 3 - Writing to JSON

Lets look at saving JSON to file. We will use more objects from the Jackson library to create the JSON to write file.

```
ObjectMapper mapper = new ObjectMapper();
ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
writer.writeValue(new File("sneaker.json"), inventory);
```

Once again we see the usage of the *ObjectMapper* object, this time to convert java objects to JSON. Next, we create a *ObjectWriter* to write the JSON data to file and tell the writer to use *new DefaultPrettyPrinter()* as the strategy to write
the data to file. The is tell the writer to format the JSON across multiple lines so that we can read it much easier (i.e. pretty). Otherwise, the write will write all of the JSON data on one line. 

## Part 4 - Refactor

Now that we know how to read and write our objects to the super convenient JSON format, lets update our app. Change the services class to use JSON to save and load the inventory models.

1. save and load application models with JSON
2. save and load the *nextId* static data with a text file

## Conclusion

And finally we have the application our awesome store needs to keep track of inventory. We made sure our application could create, read, update, and delete data. This will give us all the functionality we need to keep our stores inventory
organised and up to data. We also add the ability to save and load our data with json so that we can be sure data persist.

If you've made it this far, Congratulations! Take a moment and enjoy the win.