package Services;

import Models.ActionFigure;
import io.Console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ActionFigureService {
    private static int nextId = 1;
    private static ArrayList<ActionFigure> figureInventory = new ArrayList<>();


    public ActionFigureService(){
    }


    public static ActionFigure create(String name, String brand, String color, Double size, Integer qty, Double price) {
        ActionFigure createdFigure = new ActionFigure(nextId++, name, brand, color, size, qty, price);
        figureInventory.add(createdFigure);
        return createdFigure;
    }

    public ActionFigure findActionFigure(Integer id) {
        for (int i = 0; i < figureInventory.size(); i++) {
            if (figureInventory.get(i).equals(figureInventory.get(id))) {
                return figureInventory.get(i);
            }
        }
        return null;
    }

        public ActionFigure[] findAll () {
            return figureInventory.toArray(new ActionFigure[figureInventory.size()]);
        }


        public Boolean delete (Integer id ){
        return figureInventory.remove(findActionFigure(id));
        }

        public void deleteAll (){
        figureInventory.clear();
        }


    public String display(){
        String stringInventory = "";
        for (int i = 0; i < figureInventory.size(); i++) {
            stringInventory += figureInventory.get(i).toString() + "\n";
        }
        return stringInventory;
     }

     public Integer getNextId(){
        return nextId;
     }

     public void setNextId(Integer idFromFile){
        nextId = idFromFile;
     }

    public void loadData(){
        // (1)
        String figureCSVFile = "/Users/chris/Desktop/Figure.csv";
        String line = "";
        String csvSplitBy = ",";
        ActionFigureService aServ = new ActionFigureService();

        // (2)
        try (BufferedReader br = new BufferedReader(new FileReader(figureCSVFile))) {
            if((line = br.readLine()) != null) {
                aServ.setNextId(Integer.parseInt(line)); // (3)


                while ((line = br.readLine()) != null) {
                    // split line with comma
                    String[] figureFromFile = line.split(csvSplitBy);

                    // (4)
                    int id = Integer.parseInt(figureFromFile[0]);
                    String name = figureFromFile[1];
                    String color = figureFromFile[2];
                    String brand = figureFromFile[3];
                    double size = Double.parseDouble(figureFromFile[4]);
                    int qty = Integer.parseInt(figureFromFile[5]);
                    double price = Double.parseDouble(figureFromFile[6]);

                    // (5)
                    figureInventory.add(new ActionFigure(id, name, color, brand, size, qty, price));
                }
            }
         } catch (IOException e) {
            e.printStackTrace();
         }
        }
    }