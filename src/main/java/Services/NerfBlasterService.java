package Services;

import Models.ActionFigure;
import Models.NerfBlaster;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    public Integer getNextId(){
        return nextId;
    }

    public void setNextId(Integer idFromFile){
        nextId = idFromFile;
    }

    public void loadData(){
        // (1)
        String figureCSVFile = "/Users/chris/Desktop/Blaster.csv";
        String line = "";
        String csvSplitBy = ",";
        NerfBlasterService nServ = new NerfBlasterService();

        // (2)
        try (BufferedReader br = new BufferedReader(new FileReader(figureCSVFile))) {
            if(br.readLine() != null) {
            nServ.setNextId(Integer.parseInt(br.readLine())); // (3)
            }

            while ((line = br.readLine()) != null) {
                // split line with comma
                String[] figureFromFile = line.split(csvSplitBy);

                // (4)
                int id = Integer.parseInt(figureFromFile[0]);
                String name = figureFromFile[1];
                String type = figureFromFile[2];
                String series = figureFromFile[3];
                double size = Double.parseDouble(figureFromFile[4]);
                int qty = Integer.parseInt(figureFromFile[5]);
                double price = Double.parseDouble(figureFromFile[6]);

                // (5)
                nerfInventory.add(new NerfBlaster(id, name, type, series, size, qty, price));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
