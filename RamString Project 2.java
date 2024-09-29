/*
Logan Miller
CMSC 256
February 18th, 2022
 */


package cmsc256;

import java.util.Locale;

public class RamString implements WackyStringInterface {

    private String input;

    public RamString() {
        this.input = "Rodney, the Ram";
    }

    public RamString(String str){
        if (str == null) {
            throw new IllegalArgumentException();
        }
        this.input = str;
    }

    @Override
    public void setWackyString(String string) {
        if(string == null)
            throw new IllegalArgumentException();
        else
            this.input = string;
    }

    @Override
    public String getWackyString() {
        return this.input;
    }

    @Override
    public String getEveryFifthCharacter() {
        String output = "";
        for (int i = 4; i < input.length(); i += 5) {
            output = output + input.substring(i, i + 1);
        }
        return output;
    }

    @Override
    public String getEvenCharacters() {
        String output = "";
        for (int i = 1; i < input.length(); i += 2) {
            output = output + input.substring(i, i + 1);
        }
        return output;
    }

    @Override
    public String getOddCharacters() {
        String output = "";
        for (int i = 0; i < input.length(); i += 2) {
            output = output + input.substring(i, i + 1);
        }
        return output;
    }

    @Override
    public int countDoubleDigits() {
        String str = input;
        System.out.println(str);
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                if (i < str.length() - 1 && Character.isDigit(str.charAt(i + 1))) {
                    if(i == str.length() - 2 && !(Character.isDigit(str.charAt(i - 1)))) {
                        if (str.substring(i - 1, i).equals(" ")){
                            if(!(Character.isDigit(str.charAt(i - 2)))){
                                count++;
                            }
                        }
                        else
                            count++;
                    }
                    else if(i == 0 && str.substring(i+2, i+3).equals(" ") && !(Character.isDigit(str.charAt(i + 3))))
                        count++;

                    else if(i < str.length() - 2 && !(Character.isDigit(str.charAt(i + 2)))){
                        if(i > 0 && !(Character.isDigit(str.charAt(i - 1)))) {
                            if (str.substring(i - 1, i).equals(" ")){
                                if(!(Character.isDigit(str.charAt(i - 2)))){
                                    count++;
                                }
                            }
                            if (i > 1 && !(Character.isDigit(str.charAt(i - 2))) && !(str.substring(i - 1, i).equals(" ")))
                                count++;
                        }

                    }


                }
            }
        }
        return count;
    }

    @Override
    public int countVowels() {
        String asdf = this.input.toLowerCase(Locale.ENGLISH);
        int count = 0;
        for (int i = 0; i < asdf.length(); i++) {
            if (asdf.substring(i, i + 1).equals("a") || asdf.substring(i, i + 1).equals("e") || asdf.substring(i, i + 1).equals("i") || asdf.substring(i, i + 1).equals("o") || asdf.substring(i, i + 1).equals("u") || asdf.substring(i, i + 1).equals("y")) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String reformatName() {
        if(input.equals(" "))
            throw new UnsupportedOperationException();
        else if(input.isEmpty())
            throw new UnsupportedOperationException();


        String reformatted = "";
        int index = -1;
            for(int i = 0; i < input.length(); i++){
                if (input.substring(i, i + 1).equals(" ")) {
                    index = i;
                    i = input.length();
            }}


         if (index == -1)
            throw new UnsupportedOperationException();
        else {
            reformatted = input.substring(index).trim() + ", " + input.substring(0, index);
        }
        return reformatted;
    }


    @Override
    public void ramifyString() {
        int count = 0;

            for (int i = 0; i < input.length(); i++) {
                if (input.substring(i, i + 1).equals("0")) {
                    if (i < input.length() - 1 && input.substring(i, i + 2).equals("00")) {
                        if (i < input.length() - 2 && input.substring(i, i + 3).equals("000")) {
                            i = i + 2;
                        } else
                            input = input.substring(0, i) + "CS@VCU" + input.substring(i + 2);
                    } else
                        input = input.substring(0, i) + "Go VCU" + input.substring(i + 1);
                }
            }
        }



    @Override
    public void convertDigitsToRomanNumeralsInSubstring(int startPosition, int endPosition) throws IllegalArgumentException {
        if (startPosition > endPosition)
            throw new IllegalArgumentException("Start position cannot be bigger than end position");
        if (startPosition < 1 || endPosition > input.length())
            throw new StringIndexOutOfBoundsException();
        System.out.println(input);
        String newString = "";
        if(input.length() > 0){
        for (int i = 0; i < input.length(); i++) {
            if (i >= startPosition - 1 && i < endPosition) {
                if (Character.isDigit(input.charAt(i))) {
                    int num = Integer.parseInt(input.substring(i, i + 1));
                    if(num == 0)
                        newString = newString + "0";
                    else if (num == 1)
                        newString = newString + "I";
                    else if (num == 2)
                        newString = newString + "II";
                    else if (num == 3)
                        newString = newString + "III";
                    else if (num == 4)
                        newString = newString + "IV";
                    else if (num == 5)
                        newString = newString + "V";
                    else if (num == 6)
                        newString = newString + "VI";
                    else if (num == 7)
                        newString = newString + "VII";
                    else if (num == 8)
                        newString = newString + "VIII";
                    else if (num == 9)
                        newString = newString + "IX";
                } else if (i == input.length() - 1)
                    newString = newString + input.substring(i);
                else
                    newString = newString + input.substring(i, i + 1);
            } else if (i == input.length() - 1)
                newString = newString + input.substring(i);
            else
                newString = newString + input.substring(i, i + 1);
        }
        input = newString;
        System.out.println(input + " " + startPosition  + " " + endPosition);
    }

    }
}