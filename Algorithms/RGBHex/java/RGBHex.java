package rgbhex;

import java.lang.Math;

/**
 * RBGHex: Class to encapsulate the logic for translating rgb tuple values to a
 * hexadecimal string
 */
public class RGBHex {
    RGBHex() {
    }

    /**
     * Takes in three one-byte values representing the different channels and
     * returns a six character hexadecimal string
     *
     * @param red   The red channel value
     * @param green The green channel value
     * @param blue  The blue channel value
     *
     * @return Six character hexadecimal string of the rgb tuple
     */
    public static String rgbToHex(int red, int green, int blue) {
        return intHexValue(red) + intHexValue(green) + intHexValue(blue);
    }

    private static String intHexValue(int b) {
        String hexStr = Integer.toHexString(b);
        return hexStr.length() == 1 ? "0" + hexStr : hexStr;
    }

    private static int genByteValue() {
        return (int) Math.floor(Math.random() * 257);
    }

    public static void main(String[] args) {
        int totalPairs = 11;
        int[] redValues = new int[totalPairs];
        int[] blueValues = new int[totalPairs];
        int[] greenValues = new int[totalPairs];

        while (totalPairs > 0) {
            redValues[totalPairs - 1] = genByteValue();
            blueValues[totalPairs - 1] = genByteValue();
            greenValues[totalPairs - 1] = genByteValue();
            totalPairs--;
        }

        for (int i = 0; i < redValues.length; i++) {
            int red = redValues[i];
            int green = greenValues[i];
            int blue = blueValues[i];

            System.out.println(String.format("(R,G,B): (%d,%d,%d)", red, green, blue));
            System.out.println(String.format("Hex: %s", rgbToHex(red, green, blue)));
        }

    }
}
