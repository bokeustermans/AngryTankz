package model;

import java.util.Random;

public class RandomGenerator {
    public static int getRandomNumber(){
        Random rnd = new Random();
        return rnd.nextInt(3);
    }
}
