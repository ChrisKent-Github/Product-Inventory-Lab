package io;

import Models.ActionFigure;
import Models.NerfBlaster;
import Services.ActionFigureService;
import Services.NerfBlasterService;
import utils.CSVUtils;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    String figureCSVFile = "/Users/chris/Desktop/Figure.csv";
    FileWriter figureWriter = new FileWriter(figureCSVFile); //(1)
    String blasterCSVFile = "/Users/chris/Desktop/Blaster.csv";
    FileWriter blasterWriter = new FileWriter(blasterCSVFile); //(1)

    public App() throws IOException {
    }

    public static void main(String... args) throws IOException {
        App application = new App(); // (2)
        application.init();  // (3)
        application.menu();
    }

    public void init(){
        // (4)
        // application logic here
        // call methods to take user input and interface with services
        Console.printWelcome();
    }

    public void menu() throws IOException {
        Integer input = 0;
        Boolean returnMenu = true;
        while (returnMenu == true) {
            System.out.println("1.Add Product\n2.Remove Product\n3.Update Product\n4.Product Report");
            Scanner scanMenu = new Scanner(System.in);
            input = Integer.parseInt(scanMenu.nextLine());
            if (input > 0) {
                switch (input) {
                    case 1:
                        addProduct();
                    case 2:
                        removeProduct();
                    case 3:
                        updateProduct();
                    case 4:
                        productReport();
                }
            }
            scanMenu.close();
        }
    }

    public void addProduct() throws IOException {
        Integer input = 0;
        String opt1 = "";
        String opt2 = "";
        String opt3 = "";
        Double opt4 = 0.0;
        Integer opt5 = 0;
        Double opt6 = 0.0;
        Boolean addMore = true;
        String yesOrNo = "";
        ActionFigureService aServ = new ActionFigureService();
        NerfBlasterService nServ = new NerfBlasterService();
        Scanner scanAdd = new Scanner(System.in);
        while (addMore == true) {
            System.out.println("1.Action Figure\n2.Nerf Blaster");
            input = Integer.parseInt(scanAdd.nextLine(), 10);

            if (input > 0) {
                System.out.println("What is the product name?");
                opt1 = String.valueOf(scanAdd.nextLine());
                if (input == 1) {
                    System.out.println("What is the color of the product?");
                } else if (input == 2) {
                    System.out.println("What type is the product?");
                }
                opt2 = String.valueOf(scanAdd.nextLine());
                if (input == 1) {
                    System.out.println("What brand is the product?");
                } else if (input == 2) {
                    System.out.println("What series is the product?");
                }
                opt3 = String.valueOf(scanAdd.nextLine());
                System.out.println("What size is the product?");
                opt4 = Double.parseDouble(scanAdd.nextLine());
                System.out.println("How much of this product would you like to add?");
                opt5 = Integer.parseInt(scanAdd.nextLine());
                System.out.println("How much does the product cost?");
                opt6 = Double.parseDouble(scanAdd.nextLine());
                if (input == 1) {
                    ActionFigureService.create(opt1, opt2, opt3, opt4, opt5, opt6);
                    writeToFile(aServ.getClass());
                } else {
                    NerfBlasterService.create(opt1, opt2, opt3, opt4, opt5, opt6);
                    writeToFile(nServ.getClass());
                }
                System.out.println("Would you like to add another product?\nYes or no?");
                yesOrNo = scanAdd.nextLine().toLowerCase().replaceAll("\\s+", "");
                if (yesOrNo.equals("yes")) {
                    addMore = true;
                } else {
                    addMore = false;
                    menu();
                }
            }

        }
        scanAdd.close();
    }

        public void removeProduct() throws IOException {
            Integer input = -1;
            Integer input2 = -1;
            Integer idRemove = -1;
            Boolean removeMore = true;
            String yesOrNo = "";
            Scanner scanRemove = new Scanner(System.in);
            while (removeMore == true){
            System.out.println("1.Action Figure\n2.Nerf Blaster");
            input = Integer.parseInt(scanRemove.nextLine());
            if (input > 0) {
                System.out.println("Would you like to remove one product in this category or all products?\n1.One\n2.All");
                input2 = Integer.parseInt(scanRemove.nextLine());
                ActionFigureService aServ = new ActionFigureService();
                NerfBlasterService nServ = new NerfBlasterService();
                if(input == 1 && input2 == 2  && aServ.findAll().length > 0){
                    aServ.deleteAll();
                    writeToFile(aServ.getClass());
                    System.out.println("All Action Figure products removed successfully.");
                }
                else if(input == 2 && input2 == 2 && nServ.findAll().length > 0){
                    nServ.deleteAll();
                    writeToFile(nServ.getClass());
                    System.out.println("All Nerf Blaster products removed successfully.");
                }
                System.out.println("Enter Id of product you would like to remove from inventory.");
                idRemove = Integer.parseInt(scanRemove.nextLine());
                if (input == 1 && input2 == 1 && aServ.findAll().length > 0) {
                    if (aServ.findActionFigure(idRemove) != null) {
                        aServ.delete(idRemove);
                        writeToFile(aServ.getClass());
                        System.out.println("Action Figure Product #" + idRemove + ": Removed successfully.");
                    } else {
                        System.out.println("No product with Id:" + idRemove + "exists in inventory.");
                    }
                } else if (input == 2 && input2 == 1 && nServ.findAll().length > 0) {
                    if (nServ.findNerfBlaster(idRemove) != null) {
                        nServ.delete(idRemove);
                        writeToFile(nServ.getClass());
                        System.out.println("Nerf Blaster Product #" + idRemove + ": Removed successfully.");
                    } else {
                        System.out.println("No product with Id:" + idRemove + "exists in inventory.");
                    }
                } else {
                    System.out.println("No products of this category exist.");
                }
                System.out.println("Would you like to remove another product?\nYes or no?");
                yesOrNo = scanRemove.nextLine().toLowerCase().replaceAll("\\s+", "");
                if (yesOrNo.equals("yes")) {
                    removeMore = true;
                } else {
                    removeMore = false;
                    menu();
                    }
                }
            }
            scanRemove.close();
        }

        public void updateProduct() throws IOException {
            Integer input = -1;
            Integer idUpdate = -1;
            Integer idUpdate2 = -1;
            Boolean updateMore = true;
            String yesOrNo = "";
            String newName = "";
            String newColor = "";
            String newBrand = "";
            String newType = "";
            String newSeries = "";
            Double newSize = -1.0;
            Integer newQty = -1;
            Double newPrice = -1.0;
            Scanner scanUpdate = new Scanner(System.in);

            while (updateMore == true) {
                System.out.println("1.Action Figure\n2.Nerf Blaster");
                input = Integer.parseInt(scanUpdate.nextLine());
                if (input > 0) {
                    ActionFigureService aServ = new ActionFigureService();
                    NerfBlasterService nServ = new NerfBlasterService();
                    System.out.println("Enter Id of product you would like to update");
                    idUpdate = Integer.parseInt(scanUpdate.nextLine());
                    if(input == 1 && aServ.findActionFigure(idUpdate) != null){
                        System.out.println("What would you like to update?\n\n1.Name\n2.Color\n3.Brand\n4.Size\n5.Quantity\n6.Price");
                        idUpdate2 = Integer.parseInt(scanUpdate.nextLine());
                        switch(idUpdate2){
                            case 1:
                                System.out.println("Enter new name.");
                                newName = scanUpdate.nextLine();
                                aServ.findActionFigure(idUpdate).setName(newName);
                                writeToFile(aServ.getClass());
                                break;
                            case 2:
                                System.out.println("Enter new color.");
                                newColor = scanUpdate.nextLine();
                                aServ.findActionFigure(idUpdate).setColor(newColor);
                                writeToFile(aServ.getClass());
                                break;
                            case 3:
                                System.out.println("Enter new brand.");
                                newBrand = scanUpdate.nextLine();
                                aServ.findActionFigure(idUpdate).setBrand(newBrand);
                                writeToFile(aServ.getClass());
                                break;
                            case 4:
                                System.out.println("Enter new size.");
                                newSize = Double.parseDouble(scanUpdate.nextLine());
                                aServ.findActionFigure(idUpdate).setSize(newSize);
                                writeToFile(aServ.getClass());
                                break;
                            case 5:
                                System.out.println("Enter new quantity.");
                                newQty = Integer.parseInt(scanUpdate.nextLine());
                                aServ.findActionFigure(idUpdate).setQty(newQty);
                                writeToFile(aServ.getClass());
                                break;
                            case 6:
                                System.out.println("Enter new price.");
                                newPrice = Double.parseDouble(scanUpdate.nextLine());
                                aServ.findActionFigure(idUpdate).setPrice(newPrice);
                                writeToFile(aServ.getClass());
                                break;

                        }
                    }
                    else if(input == 2 && nServ.findNerfBlaster(idUpdate) != null){
                        System.out.println("What would you like to update?\n\n1.Name\n2.Color\n3.Type\n4.Series\n5.Quantity\n6.Price");
                        idUpdate2 = Integer.parseInt(scanUpdate.nextLine());
                        switch(idUpdate2){
                            case 1:
                                System.out.println("Enter new name.");
                                newName = scanUpdate.nextLine();
                                nServ.findNerfBlaster(idUpdate).setName(newName);
                                writeToFile(nServ.getClass());
                                break;
                            case 2:
                                System.out.println("Enter new color.");
                                newType = scanUpdate.nextLine();
                                nServ.findNerfBlaster(idUpdate).setType(newType);
                                writeToFile(nServ.getClass());
                                break;
                            case 3:
                                System.out.println("Enter new brand.");
                                newSeries = scanUpdate.nextLine();
                                nServ.findNerfBlaster(idUpdate).setSeries(newSeries);
                                writeToFile(nServ.getClass());
                                break;
                            case 4:
                                System.out.println("Enter new size.");
                                newSize = Double.parseDouble(scanUpdate.nextLine());
                                nServ.findNerfBlaster(idUpdate).setSize(newSize);
                                writeToFile(nServ.getClass());
                                break;
                            case 5:
                                System.out.println("Enter new quantity.");
                                newQty = Integer.parseInt(scanUpdate.nextLine());
                                nServ.findNerfBlaster(idUpdate).setQty(newQty);
                                writeToFile(nServ.getClass());
                                break;
                            case 6:
                                System.out.println("Enter new price.");
                                newPrice = Double.parseDouble(scanUpdate.nextLine());
                                nServ.findNerfBlaster(idUpdate).setPrice(newPrice);
                                writeToFile(nServ.getClass());
                                break;

                        }

                    }
                    else{
                        System.out.println("No product with that Id exist");
                    }

                }
                System.out.println("Would you like to update another product?\nYes or no?");
                yesOrNo = scanUpdate.nextLine().toLowerCase().replaceAll("\\s+", "");
                if (yesOrNo.equals("yes")) {
                    updateMore = true;
                } else {
                    updateMore = false;
                    menu();
                }
            }
            scanUpdate.close();

        }

        public void productReport() throws IOException {
            String exit = "";
            Scanner scanReport = new Scanner(System.in);
            ActionFigureService aServ = new ActionFigureService();
            NerfBlasterService nServ = new NerfBlasterService();
            System.out.println("Action Figures\n--------------\n" + aServ.display() + "Nerf Blasters\n-------------\n" + nServ.display());
            System.out.println("\n\nType exit to exit.");
            exit = scanReport.nextLine().toLowerCase().replaceAll("\\s+", "");
            if (exit.equals("exit")) {
                menu();
            }
        }

        public void writeToFile(Class service) throws IOException {
        if(service.getClass().isInstance(ActionFigureService.class)) {
            ActionFigureService aServ = new ActionFigureService();
            CSVUtils.writeLine(figureWriter, new ArrayList<String>(Arrays.asList(String.valueOf(aServ.getNextId()))));
            for (ActionFigure f: aServ.findAll()){
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
        }
        else if(service.getClass().isInstance(NerfBlasterService.class)) {
            NerfBlasterService nServ = new NerfBlasterService();
            CSVUtils.writeLine(blasterWriter, new ArrayList<String>(Arrays.asList(String.valueOf(nServ.getNextId()))));
            for (NerfBlaster f: nServ.findAll()){
                List<String> list = new ArrayList<>();
                list.add(String.valueOf(f.getId()));
                list.add(f.getName());
                list.add(f.getType());
                list.add(f.getSeries());
                list.add(String.valueOf(f.getSize()));
                list.add(String.valueOf(f.getQty()));
                list.add(String.valueOf(f.getPrice()));

                CSVUtils.writeLine(blasterWriter, list);
            }
            blasterWriter.flush();
            blasterWriter.close();
        }

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


        }


    }

