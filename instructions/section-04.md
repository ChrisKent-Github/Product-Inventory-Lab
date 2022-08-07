# Section 4

#### The Brief

At this point, the inventory manager can create, read, update, and delete products. We have implemented a service
to manage products and a console to interact with a user. This is awesome but there are some limitations to the 
application as
it is now. One of these limitations is the lack of persistence, and we will tackle this next.

### Objectives

* Define the importance of persistence
* Create and save to a file
* Read from a saved file

## Part 1 - Persistence

At this moment the application can only save data in memory as it runs. If we stop the program or exit from the 
application, all the inventory data is lost. This is not ideal because we may need to shut the program down 
from time to time.
If there is ever a program crash the data will be lost as well. In order to save our data we will need to store
it in a place that will persist over time. This is known as **persistent data** or **persistence**. There are many 
ways of achieving
this, in this section we will use a Comma Separated Values(CSV) file to store data. CSV files are a common way to
store and represent data. 

```
1968,  86, "Greetings"
1970,  17, "Bloody Mama"
1970,  73, "Hi, Mom!"
1971,  40, "Born to Win"
1973,  98, "Mean Streets"
1973,  88, "Bang the Drum Slowly"
1974,  97, "The Godfather, Part II"
1976,  41, "The Last Tycoon"
```
Above is a CSV of a few Robert DeNiro movies with the year released and the Rotten Tomatoes score. As you can see 
the data is separated by commas.

CSV file are a nice way to store simple data, but as the data becomes more complicated so does the CSV file. 
Because CSV file are plain text, it is easy to create and save them. However, because they are a simple text 
file it can be easy 
for anyone to open and edit these files. One must tread carefully, if the file is edited incorrectly it could
lead to improper parsing of the data. CSV files are not easy to read and can be hard to edit sometimes so be mindful.

CSV are a widely used format for saving data. This means that possibility to share this data with other programs is
high. Many programs and programing languages have the ability to process CSV data.

**Pros**

* Simple format when using simple data
* Easily saved as plain text
* Widely accepted data format

**Cons**

* Not good with complex data structure
* Not easy to read
* Editing data can be tricky

## Part 2 - Writing to File
We will first look at saving the data from the application to a CSV file. 

The data in the Sneaker CSV will be stored in this format:

```
4
1, "Stan Smith", "Adidas", "walking", 5, 25.00
2, "Air Jordan", "Nike", "basketball", 2, 150.00
3, "Chuck Taylor", "Converse", "basketball", 10, 20.00 
```

Notice the integer on the first line of the data above. This integer will represent the ```nextId``` static value 
that is stored in the service class.

To begin we will create a 'CSVUtils' class to help with this functionality.


**SneakerService.java**

```
package utils;

public class CSVUtils {
    private static final char DEFAULT_SEPARATOR = ',';  // (1)
	
	// (2)
    public static void writeLine(Writer w, List<String> values) throws IOException {
        boolean first = true;

        StringBuilder sb = new StringBuilder();
		
		// (3)
        for (String value : values) {
            if (!first) {
                sb.append(DEFAULT_SEPARATOR);
            }
            sb.append(value);
            first = false;
        }
        sb.append("\n");
        
        w.append(sb.toString());  // (4)
    }
}
```

1. Create a final variable to hold the comma separator since we do not need this value to change
2. To make this method reusable we will pass a Writer object representing the file to write to. The next parameter 
3. is a list of String objects that will represent the data of the object to write to CSV format. Now, wherever we 
4. want to write some values to CSV we can call this method and pass the values to the file and the data as a List
5. Now use a StringBuilder to create the CSV string
6. Finally, append the string to the CSV file

Now we have a utility class to help with saving objects to a CSV file whenever we need it. Next, we look at the 
implementation of the CSVUtility within the service

```
String csvFile = "/Users/batman/Desktop/Sneaker.csv";
FileWriter writer = new FileWriter(csvFile); //(1)

CSVUtils.writeLine(writer, new ArrayList<String>(Arrays.asList(String.valueOf(nextId))));  // (2)

for (Sneaker s : inventory) {
    List<String> list = new ArrayList<>(); // (3)
    list.add(String.valueOf(s.getId()));
    list.add(s.getName());
    list.add(s.getBrand());
    list.add(s.getSport());
    list.add(String.valueOf(s.getQty()));
    list.add(String.valueOf(s.getPrice()));

    CSVUtils.writeLine(writer, list);  // (4)
}

// (5)
writer.flush();
writer.close();
```
1. Create a FileWriter object and pass the location of the file to write to
2. First we save the nextId value so, it can be read back in when loading the data
3. Create an ArrayList of string representations of the object data
4. Use the CSVUtils.writeLine to save the data to file
5. Flush and close connection to the file

## Part 3 - Reading from File

Now that the application can write data to a CSV file. It would be nice to read that data in when the application
loads.

**SneakerService.java**

```
private void loadData(){
	// (1)
	String csvFile = "/Users/batman/Desktop/Sneaker.csv";
	String line = "";
	String csvSplitBy = ",";
	
	// (2)
	try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	    nextId = Integer.parseInt(br.readLine());  // (3)
	
	    while ((line = br.readLine()) != null) {
	        // split line with comma
	        String[] beer = line.split(csvSplitBy);
	
			  // (4)
	        int id = Integer.parseInt(beer[0]);
	        String name = beer[1];
	        String brand = beer[2];
	        String sport = beer[3];
	        int qty = Integer.parseInt(beer[4]);
	        float price = Float.parseFloat(beer[5]);
	
			  // (5)
	        inventory.add(new Sneaker(id, name, brand, sport, qty, price));
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
}
```

1. Set up some values to be used later
2. We use a *try with resources* block to create a new BufferedReader and catch any exceptions that can occur. If there are problems retrieving the file, the catch block with handle exception
3. Begin setting the state of the service by reading in the first line. If you remember the first line represents the nextId value.
4. For every line read in from the CSV file, the program with split the string values by a ','. Then parsed into the proper data type if necessary.
5. Finally, create a new item using the CSV data to set the initial state and add it to the inventory.

## Conclusion

In this section we identified the need to be able to persist our data in order to save
meaningful data over
time. We used a utility class to help write and save the data to a CSV file. Because Comma 
Separated Values are a 
common data format we have the ability to use this file in a number of different ways. This 
file a can later be 
imported back into the program as we did earlier, or even used by other programs and systems 
if needed.

This is pretty cool stuff we are embarking on. But can it get better? I think so! CSV files 
are great but have a
few draw backs. One of these draw backs is readability, and being able to directly edit and 
understand larger sets
of data. This is where CSV has some limitations, we are going to look an alternative that helps 
remedy this.