package io;

import Services.ActionFigureService;
import Services.NerfBlasterService;

import java.util.Scanner;

public class App {

    private ActionFigureService actionService = new ActionFigureService(); // (1)


    public static void main(String... args){
        App application = new App(); // (2)
        application.init();  // (3)
        application.addProduct();
    }

    public void init(){
        // (4)
        // application logic here
        // call methods to take user input and interface with services
        Console.printWelcome();
    }

    public void addProduct(){
        Integer input = 0;
        String opt1 = "";
        String opt2 = "";
        String opt3 = "";
        Double opt4 = 0.0;
        Integer opt5 = 0;
        Double opt6 = 0.0;
        Boolean addMore = true;
        String yesOrNo = "";

        while(addMore == true) {
            System.out.println("What would you like to add to the inventory?\n Press 1 for Action Figure or 2 for Nerf Blaster");
            Scanner scanner = new Scanner(System.in);
            input = Integer.parseInt(scanner.nextLine(),10);

            if(input > 0) {
                System.out.println("What is the product name?");
                opt1 = String.valueOf(scanner.nextLine());
                if (input == 1) {
                    System.out.println("What is the brand of the product?");
                } else if (input == 2) {
                    System.out.println("What type is the product?");
                }
                opt2 = String.valueOf(scanner.nextLine());
                if(input == 1) {
                    System.out.println("What color is the product?");
                }
                else if(input == 2){
                    System.out.println("What series is the product?");
                }
                opt3 = String.valueOf(scanner.nextLine());
                System.out.println("What size is the product?");
                opt4 = Double.parseDouble(scanner.nextLine());
                System.out.println("How much of this product would you like to add?");
                opt5 = Integer.parseInt(scanner.nextLine());
                System.out.println("How much does the product cost?");
                opt6 = Double.parseDouble(scanner.nextLine());
                if (input == 1) {
                    ActionFigureService.create(opt1, opt2, opt3, opt4, opt5, opt6);
                } else {
                    NerfBlasterService.create(opt1, opt2, opt3, opt4, opt5, opt6);
                }
                System.out.println("Would you like to add another product?\nYes or no?");
                yesOrNo = scanner.nextLine().toLowerCase().replaceAll("\\s+", "");
                if (yesOrNo.equals("yes")) {
                    addMore = true;
                } else {
                    addMore = false;
                }
            }
            scanner.close();
        }


    }
}
