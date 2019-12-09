
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *  The caesarCipher Class represents the solution to the caesar cipher, the main cipher supported by the prototype.
 *
 */
public class caesarCipher implements cipherInterface{

    private String cipherText;
    private ArrayList<plainTextPerm> possibleOutcomes;

    /**
     * The constructor called in the cipherFactory class. It is used to set the initial variables, the cipher text and
     * an array list of plainTextPerm objects. The array list is initialized as an empty list and will be added to when
     * the program calls the decipher method.
     * @param cipherText the cipher text of the cipher the program is deciphering.
     *
     */
    public caesarCipher(String cipherText) {
        this.cipherText = cipherText.toUpperCase();
        this.possibleOutcomes = new ArrayList<plainTextPerm>();
    }

    /**
     * this method is the implementation of the decipher method required by the cipherInterface. it handles the deciphering
     * process for the caesar cipher. It first lets the user know that the cipher has been identified as a caesar cipher.
     * then calls the proper methods to calculate fitness scores, sort the possible outcomes, and then creates a
     * result object.
     * @param langConfig the language configuration used to access supported languages.
     */
    public void decipher(langConfig langConfig) {
        System.out.println("The cipher has been identified as a caesar cipher. Beginning deciphering process");
        System.out.println("the cipher text is: " + this.cipherText);
        calcPerms();
        for (plainTextPerm perm : this.possibleOutcomes){
            perm.calcFitnessScores(langConfig);
        }

        Collections.sort(this.possibleOutcomes);

        result res = new result(this.cipherText, new ArrayList<plainTextPerm>(this.possibleOutcomes.subList(0, 6)));
        res.printResult();

    }

    /**
     *  This private method is used to support the decipher method implementation used by the caesar cipher. It is
     *  responsible for performing the character shifts of the caesar cipher implementation. It does this by creating
     *  two Hash maps: a number to alphabet map, and a alphabet to number map. If the index of the letter in the alphabet
     *  + the current shift is too high, it will change the calculation to (index + shift) - 26 to represent the shift
     *  back to the beginning of the alphabet. as it finishes creating a permutation, it adds the new permutation to the
     *  list of permutations to return.
     * @return An array list of plainTextPerm objects that represent each possible permutation of the cipher.
     */
    private ArrayList<plainTextPerm> calcPerms() {

        ArrayList<plainTextPerm> returnThis = new ArrayList<plainTextPerm>();

        HashMap<Integer,Character> numToAlpha = new HashMap<Integer, Character>();
        HashMap<Character,Integer> alphaToNum = new HashMap<Character, Integer>();

        int num = 1;

        for( char alphabet = 'A'; alphabet <= 'Z'; ++alphabet){
            numToAlpha.put(num, alphabet);
            alphaToNum.put(alphabet, num);
            ++num;
        }

        // i represents the number of shifts to make in the cipher.
        for ( int i = 1; i <=25; ++i){
            String plainText = "";
            // j represents the the index of the character in the cipher.
            for ( int j = 0; j < this.cipherText.length(); ++j) {
                int sum;
                if (this.cipherText.charAt(j) == ' '){
                    plainText += ' ';
                }
                else{
                    if (alphaToNum.get(this.cipherText.charAt(j)) + i > 26){
                        sum = alphaToNum.get(this.cipherText.charAt(j)) + i - 26;
                    }
                    else {
                        sum = alphaToNum.get(this.cipherText.charAt(j)) + i;
                    }

                    plainText += numToAlpha.get(sum);
                }
            }

            System.out.println("plaintext for a shift of " + i + ": " + plainText);
            this.possibleOutcomes.add(new plainTextPerm(plainText));

        }
        return returnThis;
    }

    /**
     *  The get method used to access the cipher text variable and ensure proper encapsulation.
     * @return the current cipher text.
     */
    public String getCipherText() {
        return cipherText;
    }

    /**
     * The set method used to set a new cipher text and ensure proper encapsulation.
     * @param cipherText A string that represents the cipher text.
     */
    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    /**
     * the get method used to access the current list of possible outcomes and ensure proper encapsulation.
     * @return an ArrayList of plainTextPerm Objects representing the possible outcomes of the cipher.
     */
    public ArrayList<plainTextPerm> getPossibleOutcomes() {
        return possibleOutcomes;
    }

    /**
     * the set method used to set a new list of possible outcomes and ensure proper encapsulation.
     * @param possibleOutcomes the new list of possible outcomes.
     */
    public void setPossibleOutcomes(ArrayList<plainTextPerm> possibleOutcomes) {
        this.possibleOutcomes = possibleOutcomes;
    }


}
