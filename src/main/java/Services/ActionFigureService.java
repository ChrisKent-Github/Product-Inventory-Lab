package Services;

import Models.ActionFigure;

import java.util.ArrayList;

public class ActionFigureService {
    private static int nextId = 1;
    private static ArrayList<ActionFigure> inventory = new ArrayList<>();


    public static ActionFigure create(String name, String color, String brand, Double size, Integer qty, Double price) {
        ActionFigure createdFigure = new ActionFigure(nextId++, name, color, brand, size, qty, price);
        inventory.add(createdFigure);
        return createdFigure;
    }

    public ActionFigure findActionFigure(Integer id) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).equals(id)) {
                return inventory.get(i);
            } else {
                return null;
            }
        }
        return null;
    }


        public ActionFigure[] findAll () {
            return (ActionFigure[]) inventory.toArray();
        }


        public Boolean delete (Integer id ){
        return inventory.remove(findActionFigure(id));
        }
    }