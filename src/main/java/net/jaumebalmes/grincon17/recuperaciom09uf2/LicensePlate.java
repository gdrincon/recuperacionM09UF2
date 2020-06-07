package net.jaumebalmes.grincon17.recuperaciom09uf2;

public class LicensePlate {

    private final static String ALFABET_LETTERS = "BCDFGHJKLMNPQRSTVXYZ";
    private final static int MOD_ALFABET = ALFABET_LETTERS.length();
    private final static int MOD_NUMBERS = 10000;
    private final static String [] LEADING_ZEROES = {"0000", "0", "00","000"};
    private final static int CONTROL_TENS = 10;
    private final static int CONTROL_HUNDREDS = 100;
    private final static int CONTROL_THOUSANDS = 1000;
    private final static char CONTROL_Z = 'Z';
    private String plate;

    public LicensePlate(String plate) {
        this.plate = plate;
    }

    public synchronized void generator() {
        String numbers = plate.substring(0,4);
        String letters = plate.substring(4);
        String newNumbers = getNumbers(Integer.parseInt(numbers));
        String newLetters = "";
        char nextChar3 = ' ';
        char nextChar2 = ' ';
        char nextChar1 = ' ';
        if (newNumbers.equals(LEADING_ZEROES[0])) {
            if(letters.charAt(2) != CONTROL_Z) {
                nextChar3 = getletters(letters.charAt(2));
                newLetters = letters.replace(letters.charAt(2), nextChar3);
            } else {
                nextChar3 = getletters(letters.charAt(2));
                if(letters.charAt(1) != CONTROL_Z) {
                    nextChar2 = getletters(letters.charAt(1));
                    newLetters = newLetters + letters.charAt(0) + nextChar2 + nextChar3;
                } else {
                    nextChar1 = getletters(letters.charAt(0));
                    nextChar2 = getletters(letters.charAt(1));
                    newLetters = newLetters + nextChar1 + nextChar2 + nextChar3;
                }
            }
            plate = newNumbers + newLetters;
        } else {
            plate = newNumbers + letters;
        }
    }

    private String getNumbers(int number) {
        String s = "";
        int num = number%MOD_NUMBERS+1;
        if(num < CONTROL_TENS) {
            s = LEADING_ZEROES[3] + num;
        } else if (num < CONTROL_HUNDREDS) {
            s = LEADING_ZEROES[2] + num;
        } else if (num < CONTROL_THOUSANDS) {
            s = LEADING_ZEROES[1] +num;
        }  else if (num < MOD_NUMBERS) {
            s = String.valueOf(num);
        } else {
            s = LEADING_ZEROES[0];
        }
        return s;
    }

    private char getletters(char c) {
        char newChar = ' ';
        for (int i = 0; i < ALFABET_LETTERS.length(); i++) {
            if (c == ALFABET_LETTERS.charAt(i)) {
                newChar = ALFABET_LETTERS.charAt((i + 1) % MOD_ALFABET);
            }
        }
        return newChar;
    }

    public String getPlate() {
        return plate;
    }

    @Override
    public String toString() {
        return plate;
    }
}
