package Services;

import Models.NerfBlaster;

import java.util.ArrayList;
import java.util.Arrays;

public class NerfBlasterService {
    private static int nextId = 1;
    private static ArrayList<NerfBlaster> nerfInventory = new ArrayList<>();

    public static NerfBlaster create(String name, String type, String series, Double size, Integer qty, Double price) {
        NerfBlaster createdFigure = new NerfBlaster(nextId++, name, type, series, size, qty, price);
        nerfInventory.add(createdFigure);
        return createdFigure;
    }

    public NerfBlaster findNerfBlaster(Integer id) {
        for (int i = 0; i < nerfInventory.size(); i++) {
            if (nerfInventory.get(i).equals(nerfInventory.get(id))) {
                return nerfInventory.get(i);
            }
        }
        return null;
    }


    public NerfBlaster[] findAll () {
        return nerfInventory.toArray(new NerfBlaster[nerfInventory.size()]);
    }


    public Boolean delete (Integer id ){
        return nerfInventory.remove(findNerfBlaster(id));
    }

    public void deleteAll (){
        nerfInventory.clear();
    }

    public String display(){
        String stringInventory = "";
        for (int i = 0; i < nerfInventory.size(); i++) {
            stringInventory += nerfInventory.get(i).toString() + "\n";
        }
        return stringInventory;
    }
}
