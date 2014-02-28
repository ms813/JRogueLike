package Generic;

import java.util.Random;

/**
 * Created by Matthew on 28/02/14.
 */
public class Dice {

    public strictfp static int roll(String xdy) {

        if (!xdy.contains("d")) {
            throw new IllegalArgumentException("Dice rolls must be in the format int 'd' int");
        } else {

            String[] parts = xdy.split("d");

            int dice = Integer.parseInt(parts[0]);
            int sides = Integer.parseInt(parts[1]);

            Random rnd = new Random();

            int roll = 0;

            for (int i = 0; i < dice; i++) {

                roll += rnd.nextInt(sides) + 1;

            }
            return roll;
        }
    }
}
