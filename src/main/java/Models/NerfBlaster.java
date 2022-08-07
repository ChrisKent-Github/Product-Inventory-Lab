package Models;

import java.util.Arrays;

public class NerfBlaster {
    private Integer id;
    private String name;
    private String type;
    private String series;
    private Double size;
    private Integer qty;
    private Double price;


    public NerfBlaster(Integer id, String name, String type, String series, Double size, Integer qty, Double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.series = series;
        this.size = size;
        this.qty = qty;
        this.price = price;
    }

    public NerfBlaster() {

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
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
        String[] att = {String.valueOf(this.id), this.name, this.type, this.series, String.valueOf(this.size), String.valueOf(this.qty), String.valueOf(this.price)};
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
