package Services;

import Models.NerfBlaster;

import java.util.ArrayList;

public class NerfBlasterService {
    private static int nextId = 1;
    private static ArrayList<NerfBlaster> nerfInventory = new ArrayList<>();

    public static NerfBlaster create(String name, String type, String series, Double size, Integer qty, Double price) {
        NerfBlaster createdFigure = new NerfBlaster(nextId++, name, type, series, size, qty, price);
        nerfInventory.add(createdFigure);
        return createdFigure;
    }

    public NerfBlaster findActionFigure(Integer id) {
        for (int i = 0; i < nerfInventory.size(); i++) {
            if (nerfInventory.get(i).equals(id)) {
                return nerfInventory.get(i);
            } else {
                return null;
            }
        }
        return null;
    }


    public NerfBlaster[] findAll () {
        return (NerfBlaster[]) nerfInventory.toArray();
    }


    public Boolean delete (Integer id ){
        return nerfInventory.remove(findActionFigure(id));
    }
}
