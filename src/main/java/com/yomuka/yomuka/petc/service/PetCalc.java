package com.yomuka.yomuka.petc.service;

import java.lang.Math;

import com.yomuka.yomuka.petc.DTO.Pet;

public class PetCalc {

    public int calcFood(Pet pet){
        int recommendFood = 0;
        //강아지면
        if(pet.getKind().equals("dog")){
            //1살 미만이면
            if(pet.getPetAgeUnit().equals("개월")){
                if(pet.getPetAge()<4) {
                    recommendFood = (int)Math.round((70*Math.pow(pet.getPetWeight(),0.75)*2.5));
                } else if(pet.getPetAge()<12){
                    recommendFood = (int)Math.round((70*Math.pow(pet.getPetWeight(),0.75)*1.75));
                }
            //1살 이상이면
            } else if (pet.getPetAgeUnit().equals("살")){
                if(pet.getPetNeu().equals("O") && pet.getPetAge()<10){
                    recommendFood = (int)Math.round((70*Math.pow(pet.getPetWeight(),0.75)*1.5));
                } else if(pet.getPetNeu().equals("X") && pet.getPetAge()<10){
                    recommendFood = (int)Math.round((70*Math.pow(pet.getPetWeight(),0.75)*1.7));
                } else if(pet.getPetAge()>=10){
                    recommendFood = (int)Math.round((70*Math.pow(pet.getPetWeight(),0.75)*1.1));
                }
            }
        } else if(pet.getKind().equals("cat")){
        	if(pet.getPetAgeUnit().equals("개월")){
                if(pet.getPetAge()<4) {
                    recommendFood = (int)Math.round((pet.getPetWeight() * 30 + 70) * 3);
                } else if(pet.getPetAge()<=6){
                    recommendFood = (int)Math.round((pet.getPetWeight() * 30 + 70) * 2.5);
                } else if(pet.getPetAge()<12){
                    recommendFood = (int)Math.round((pet.getPetWeight() * 30 + 70) * 2.0);
                }
            } else if(pet.getPetAgeUnit().equals("살")){
                if(pet.getPetNeu().equals("O")){
                    recommendFood = (int)Math.round((pet.getPetWeight() * 30 + 70) * 1.2);
                } else if (pet.getPetNeu().equals("X")){
                    recommendFood = (int)Math.round((pet.getPetWeight() * 30 + 70) * 1.4);
                } else if(pet.getPetAge()>=7){
                    recommendFood = (int)Math.round((pet.getPetWeight() * 30 + 70) * 0.7);
                }
            }
        }
        return recommendFood;
    }

    public int calcWater(Pet pet){
	  int recommendWater = 0;
        //강아지면
        if(pet.getKind().equals("dog")){
        	recommendWater = (int)Math.round(pet.getPetWeight()*70);
        } else if(pet.getKind().equals("cat")){
        	recommendWater = (int)Math.round(pet.getPetWeight()*45);
        } 
        return recommendWater;
    }

}

