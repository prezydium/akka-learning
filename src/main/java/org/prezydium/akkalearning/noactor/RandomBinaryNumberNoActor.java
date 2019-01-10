package org.prezydium.akkalearning.noactor;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RandomBinaryNumberNoActor {

    private static Logger logger = Logger.getLogger(RandomBinaryNumberNoActor.class.getName());

    public static void generateRandomBinaryNumberAsString() {
        String result = "";
        for (int i = 0; i < 1000; i++) {
            result = Integer.toBinaryString(new Random().nextInt(10000));
        }
        result = ("00000000000000" + result).substring(result.length());
        logger.log(Level.INFO, result);
    }
}