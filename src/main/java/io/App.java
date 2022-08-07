package io;

import Services.ActionFigureService;
import Services.NerfBlasterService;

import java.util.Scanner;

public class App {

    private ActionFigureService actionService = new ActionFigureService(); // (1)


    public static void main(String... args){
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

    public void addProduct() {
        Integer input = 0;
        String opt1 = "";
        String opt2 = "";
        String opt3 = "";
        Double opt4 = 0.0;
        Integer opt5 = 0;
        Double opt6 = 0.0;
        Boolean addMore = true;
        String yesOrNo = "";
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
                } else {
                    NerfBlasterService.create(opt1, opt2, opt3, opt4, opt5, opt6);
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

        public void menu() {
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

        public void removeProduct(){
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
                    System.out.println("All Action Figure products removed successfully.");
                }
                else if(input == 2 && input2 == 2 && nServ.findAll().length > 0){
                    nServ.deleteAll();
                    System.out.println("All Nerf Blaster products removed successfully.");
                }
                System.out.println("Enter Id of product you would like to remove from inventory.");
                idRemove = Integer.parseInt(scanRemove.nextLine());
                if (input == 1 && input2 == 1 && aServ.findAll().length > 0) {
                    if (aServ.findActionFigure(idRemove) != null) {
                        aServ.delete(idRemove);
                        System.out.println("Product Id:" + idRemove + "removed successfully.");
                    } else {
                        System.out.println("No product with Id:" + idRemove + "exists in inventory.");
                    }
                } else if (input == 2 && input2 == 1 && nServ.findAll().length > 0) {
                    if (nServ.findNerfBlaster(idRemove) != null) {
                        nServ.delete(idRemove);
                        System.out.println("Product Id:" + idRemove + "removed successfully.");
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

        public void updateProduct() {
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
                                break;
                            case 2:
                                System.out.println("Enter new color.");
                                newColor = scanUpdate.nextLine();
                                aServ.findActionFigure(idUpdate).setColor(newColor);
                                break;
                            case 3:
                                System.out.println("Enter new brand.");
                                newBrand = scanUpdate.nextLine();
                                aServ.findActionFigure(idUpdate).setBrand(newBrand);
                                break;
                            case 4:
                                System.out.println("Enter new size.");
                                newSize = Double.parseDouble(scanUpdate.nextLine());
                                aServ.findActionFigure(idUpdate).setSize(newSize);
                                break;
                            case 5:
                                System.out.println("Enter new quantity.");
                                newQty = Integer.parseInt(scanUpdate.nextLine());
                                aServ.findActionFigure(idUpdate).setQty(newQty);
                                break;
                            case 6:
                                System.out.println("Enter new price.");
                                newPrice = Double.parseDouble(scanUpdate.nextLine());
                                aServ.findActionFigure(idUpdate).setPrice(newPrice);
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
                                break;
                            case 2:
                                System.out.println("Enter new color.");
                                newType = scanUpdate.nextLine();
                                nServ.findNerfBlaster(idUpdate).setType(newType);
                                break;
                            case 3:
                                System.out.println("Enter new brand.");
                                newSeries = scanUpdate.nextLine();
                                nServ.findNerfBlaster(idUpdate).setSeries(newSeries);
                                break;
                            case 4:
                                System.out.println("Enter new size.");
                                newSize = Double.parseDouble(scanUpdate.nextLine());
                                nServ.findNerfBlaster(idUpdate).setSize(newSize);
                                break;
                            case 5:
                                System.out.println("Enter new quantity.");
                                newQty = Integer.parseInt(scanUpdate.nextLine());
                                nServ.findNerfBlaster(idUpdate).setQty(newQty);
                                break;
                            case 6:
                                System.out.println("Enter new price.");
                                newPrice = Double.parseDouble(scanUpdate.nextLine());
                                nServ.findNerfBlaster(idUpdate).setPrice(newPrice);
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

        public void productReport() {
            String exit = "";
            Scanner scanReport = new Scanner(System.in);
            ActionFigureService aServ = new ActionFigureService();
            NerfBlasterService nServ = new NerfBlasterService();
            System.out.println(aServ.display());
            System.out.println("\n\nType exit to exit.");
            exit = scanReport.nextLine().toLowerCase().replaceAll("\\s+", "");
            if (exit.equals("exit")) {
                menu();
            }
        }


    }

