package io;

import Models.ActionFigure;
import Models.NerfBlaster;
import Services.ActionFigureService;
import Services.NerfBlasterService;
import utils.CSVUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Console {


    public static void printWelcome(){
        System.out.println("" +
                "**************************************************" +
                "***           Welcome and Bienvenue            ***" +
                "***                    to                      ***" +
                "***          ZipCo Inventory Manager           ***" +
                "**************************************************");
    }

    public static void loadAllData(){
        ActionFigureService aServ = new ActionFigureService();
        NerfBlasterService nServ = new NerfBlasterService();
        aServ.loadData();
        nServ.loadData();
    }

    public static void writeToFile(Class service) throws IOException {
        String figureCSVFile = "/Users/chris/Desktop/Figure.csv";
        FileWriter figureWriter = new FileWriter(figureCSVFile); //(1)
        String blasterCSVFile = "/Users/chris/Desktop/Blaster.csv";
        FileWriter blasterWriter = new FileWriter(blasterCSVFile); //(1)
        if (service.getClass().isInstance(ActionFigureService.class)) {
            ActionFigureService aServ = new ActionFigureService();
            CSVUtils.writeLine(figureWriter, new ArrayList<String>(Arrays.asList(String.valueOf(aServ.getNextId()))));
            for (ActionFigure f : aServ.findAll()) {
                List<String> list = new ArrayList<>();
                list.add(String.valueOf(f.getId()));
                list.add(f.getName());
                list.add(f.getColor());
                list.add(f.getBrand());
                list.add(String.valueOf(f.getSize()));
                list.add(String.valueOf(f.getQty()));
                list.add(String.valueOf(f.getPrice()));

                CSVUtils.writeLine(figureWriter, list);
            }
            figureWriter.flush();
            figureWriter.close();
        } if (service.getClass().isInstance(NerfBlasterService.class)) {
            NerfBlasterService nServ = new NerfBlasterService();
            CSVUtils.writeLine(blasterWriter, new ArrayList<String>(Arrays.asList(String.valueOf(nServ.getNextId()))));
            for (NerfBlaster b : nServ.findAll()) {
                List<String> list = new ArrayList<>();
                list.add(String.valueOf(b.getId()));
                list.add(b.getName());
                list.add(b.getType());
                list.add(b.getSeries());
                list.add(String.valueOf(b.getSize()));
                list.add(String.valueOf(b.getQty()));
                list.add(String.valueOf(b.getPrice()));

                CSVUtils.writeLine(blasterWriter, list);
            }
            blasterWriter.flush();
            blasterWriter.close();
        }
    }

}

