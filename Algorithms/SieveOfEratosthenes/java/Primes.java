import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Primes {
    private static void print(String statement, boolean value) {
        System.out.println(String.format(statement, value));
    }

    private static void print(String statement, int value) {
        System.out.println(String.format(statement, value));

    }

    private static void print(String statement, String value) {
        System.out.println(String.format(statement, value));
    }

    private static void initPrimeFlagsList(boolean[] flags) {
        // set all prime flags to true besides 0 and 1 (not primes...)
        if (flags.length > 2) {
            for (int i = 2; i < flags.length; i++) {
                flags[i] = true;
            }
        }
    }

    private static void crossOffMultiples(boolean[] flags, int currentPrime) {
        // currentPrime * k where k < currentPrime will have been crossed off already in
        // prior iteration...
        for (int i = currentPrime * currentPrime; i < flags.length; i += currentPrime) {
            flags[i] = false;
        }
    }

    private static int getNextPrimeNumber(boolean[] flags, int currentPrime) {
        int next = currentPrime + 1;
        while (next < flags.length && !flags[next]) {
            next++;
        }
        return next;
    }

    private static List<Integer> generateListOfPrimes(int maxBound) {
        // Using the algorithm: The Sieve of Eratosthenes
        boolean[] flags = new boolean[maxBound + 1];
        int count = 0;

        initPrimeFlagsList(flags);

        int prime = 2;
        while (prime <= Math.sqrt(maxBound)) {
            // Cross of the remaining multiples of prime numbers
            crossOffMultiples(flags, prime);
            prime = getNextPrimeNumber(flags, prime);
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 0; i < flags.length; i++) {
            if (flags[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    private static boolean isPrime(int num) {
        // By definition: A prime number x is prime in if and only if
        // x > 1 && is divisible by 1 and itself
        if (num < 3) {
            return false;
        }

        int sqrt = (int) Math.sqrt(num);
        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static int countTotalPrimesTo(int max) {
        // Max is exclusive ==> [0,max)
        if (max < 3) {
            return 0;
        }
        if (max == 3) {
            return 1; // 2 is a prime...
        }

        int total = 1; // 2 is a prime...
        for (int i = 3; i < max; i++) {
            if (isPrime(i)) {
                total++;
            }
        }

        return total;
    }

    public static void main(String[] args) {
        print("%s", "Math and Logic");
        print("Total primes between the interval [0,10): %d", countTotalPrimesTo(10));
        print("List of primes from [0,30]: %s", generateListOfPrimes(30).toString());

    }
}