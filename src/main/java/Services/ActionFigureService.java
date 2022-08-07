package Services;

import Models.ActionFigure;

import java.util.ArrayList;
import java.util.Arrays;

public class ActionFigureService {
    private static int nextId = 1;
    private static ArrayList<ActionFigure> figureInventory = new ArrayList<>();


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
    }