public class BitManipulation {
    private static boolean bitIsOne(int num, int bitIndex) {
        // 1 << bitIndex ==> Logical left shift to bitIndex
        // Logical AND operation to determine if that bit is 0 or not
        // x & 1 => x
        return ((num & (1 << bitIndex)) != 0);
    }

    private static int setBit(int num, int bitIndex) {
        // Logical OR operation used as a tool based on the fact
        // that x | 1 => 1
        return num | (1 << bitIndex);
    }

    private static int clearBitMSBToIndex(int num, int bitIndex) {
        // 00100 - 1 => 00011 if bitIndex = 2
        int mask = ((1 << bitIndex) - 1);
        return num & mask;
    }

    private static int clearBitIndexToLSB(int num, int bitIndex) {
        // (-1) 11111 << bitIndex + 1 => 11000 if bitIndex = 2
        return (num & (-1 << (bitIndex + 1)));
    }

    private static int clearBit(int num, int bitIndex, boolean msbToIndex, boolean IndexToLSB) {
        if (msbToIndex) {
            return clearBitMSBToIndex(num, bitIndex);
        }
        if (IndexToLSB) {
            return clearBitIndexToLSB(num, bitIndex);
        }
        // Logical shift to bitIndex location ==> 0001000 (Example)
        // Invert bits to create mask ==> 1110111
        // x & 0 => 0 therefore:
        // if the bit at the index is 1, we set it to 0
        // if the bit at the index is 0, the bit will remain zero...
        return num & (~(1 << bitIndex));
    }

    private static void print(String statement, boolean value) {
        System.out.println(String.format(statement, value));
    }

    private static void print(String statement, int value) {
        System.out.println(String.format(statement, value));

    }

    private static void print(String statement, String value) {
        System.out.println(String.format(statement, value));
    }

    public static void main(String[] args) {
        print("%s", "Bit Manipulation");
        print("5 (0101): bitIsOne at 3rd LSB: %s", bitIsOne(5, 2));
        print("5 (0101) with 2nd bit index set becomes 7 (0111): %d", setBit(5, 1));
        print("7 (0111) with 2nd bit index cleared becomes 5: %d", clearBit(7, 1, false, false));
        print("7 (0111) with MSB to 2nd bit index cleared becomes 1: %d", clearBit(7, 1, true, false));
        print("15 (1111) with 3rd bit index to LSB cleared becomes 8: %d", clearBit(15, 2, false, true));
    }
}