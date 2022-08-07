package Models;

import java.util.Arrays;

public class ActionFigure
{
    private Integer id;
    private String name;
    private String color;
    private String brand;
    private Double size;
    private Integer qty;
    private Double price;


    public ActionFigure(Integer id, String name, String color, String brand, Double size, Integer qty, Double price) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.brand = brand;
        this.size = size;
        this.qty = qty;
        this.price = price;
    }

    public ActionFigure(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String toString(){
        String[] att = {String.valueOf(this.id),this.name,this.color,this.brand, String.valueOf(this.size), String.valueOf(this.qty), String.valueOf(this.price)};
        String finalString = "";
        for(int i = 0; i < att.length; i++) {
            finalString += String.format("%s", att[i]);
            for (int j = 0; j <= 12 - att[i].length(); j++) {
                finalString += " ";
                if(j - (12 - att[i].length()) == 0){
                    finalString += "|";
                }
            }
        }

        return finalString;
    }
}
