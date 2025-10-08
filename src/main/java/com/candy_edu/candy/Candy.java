package com.candy_edu.candy;

@SuppressWarnings("unused")
public class Candy {
    private Integer id;
    private String name;
    private final CandyWeight weight;
    private Double price;
    private CandyType type;
    private Integer sugar;

    public Candy(Integer id, String name, Integer grams, Double price, Integer sugar, CandyType type){
        this.id = id;
        this.name = name;
        this.weight = new CandyWeight(grams);
        this.price = price;
        this.sugar = sugar;
        this.type = type;
    }

    public Candy(String name, Integer grams, Double price, Integer sugar, CandyType type){
        this(null, name, grams, price, sugar, type);
    }

    // гетери/сетери
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName(){ return name; }
    public CandyWeight getWeight(){ return weight; }
    public Double getPrice(){ return price; }
    public CandyType getType(){ return type; }
    public Integer getSugar() { return sugar;}

    public void setName(String name){ this.name = name; }
    public void setWeight(Integer grams){ weight.setGrams(grams); }
    public void setPrice(Double price){ this.price = price; }
    public void setType(CandyType type){ this.type = type; }
    public void setSugar(Integer sugar) {this.sugar = sugar;}

    @Override
    public String toString(){
        return "Candy: " + name +
                ", Weight: " + weight +
                ", Sugar: " + sugar + "g" +
                ", Price: " + price + " UAH" +
                ", Type: " + type;
    }
}
