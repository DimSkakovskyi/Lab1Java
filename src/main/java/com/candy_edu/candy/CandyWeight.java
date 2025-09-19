package com.candy_edu.candy;

public class CandyWeight {
    private Integer grams;

    public CandyWeight(Integer grams){
        if(grams >= 0) this.grams = grams;
        else {
            System.out.println("Incorrect weight value.");
            this.grams = 0;
        }
    }

    public Integer getGrams() { return grams; }

    public void setGrams(Integer grams) {
        if(grams >= 0) this.grams = grams;
        else System.out.println("Incorrect weight value (setGrams).");
    }

    public String toString(){
        return grams + "g";
    }
}
