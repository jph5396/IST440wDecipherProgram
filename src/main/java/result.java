

import java.util.ArrayList;
import java.util.Set;

/**
 * the result class is used to calculate the most fit of the possible permutations and print the final output for the
 * user to see.
 */
public class result {
    private String CipherText;
    private plainTextPerm mostFit;
    private ArrayList<plainTextPerm> nextFiveMostFit;
    private String translatedPlainText;

    /**
     * The constructor for the result class called during the decipher process to represent the output of the program.
     * @param cipherText the original cipher text of the cipher the program is handling.
     * @param sixMostFit the six permutations that are most fit according to their scores.
     */
    public result(String cipherText, ArrayList<plainTextPerm> sixMostFit) {
        CipherText = cipherText;
        mostFit = sixMostFit.get(0);
        nextFiveMostFit = new ArrayList<plainTextPerm>();

        for (int i = 1; i < sixMostFit.size() -1; ++i) {
            nextFiveMostFit.add(sixMostFit.get(i));
        }

    }

    /**
     * A constructor for the result class requiring all instance variables upfront. It is not used in the prototype,
     * but it was created in case it is needed in the future.
     * @param cipherText the original cipher text of the cipher that the program is handling.
     * @param mostFit the plain text permutation that has been identified as the most fit.
     * @param nextFiveMostFit an array list representing the next five most fit permutations.
     * @param translatedPlainText the plain text translated into english if it was in another language.
     */
    public result(String cipherText, plainTextPerm mostFit, ArrayList<plainTextPerm> nextFiveMostFit, String translatedPlainText) {

        CipherText = cipherText;
        this.mostFit = mostFit;
        this.nextFiveMostFit = nextFiveMostFit;
        this.translatedPlainText = translatedPlainText;
    }

    /**
     * the method used to print the final result of the decipher program to the console.
     */
    public void printResult(){
            System.out.println("#### RESULTS ####");
            System.out.println("Cipher Text: " + this.CipherText);
            System.out.println();
            System.out.println("Most Probable Plain Text: " + this.mostFit.getPlainText());
            printHelper(mostFit);

            System.out.println("Next most fit: ");
            for(plainTextPerm perm : this.nextFiveMostFit){
                System.out.println(perm.getPlainText());
                printHelper(perm);
            }


    }

    /**
     * the private print helper method is used to support the printResult() method. It is a reusable method that allows
     * the program to print any the score for any plaintext permutaiton that is passed to it.
     * @param perm the permutation that needs to be printed.
     */
    private void printHelper(plainTextPerm perm){
        Set<String> keyset = perm.getFitnessScores().keySet();
        String mostFitLang = "";
        Double mostFitScore = -999999999999999.99;

        for (String key : keyset) {
            if (perm.getFitnessScores().get(key) > mostFitScore) {
                mostFitLang = key;
                mostFitScore = perm.getFitnessScores().get(key);
            }
        }

        System.out.println("--Language: " + mostFitLang + " Most Fit Score: " + mostFitScore);
    }

    /**
     * get method used to access the ciphertext and ensure proper encapsulation.
     * @return String representing the original cipher text.
     */
    public String getCipherText() {
        return CipherText;
    }

    /**
     * the set method used to set a new cipher text and ensure proper encapsulation.
     * @param cipherText the new string to set the cipher text to.
     */
    public void setCipherText(String cipherText) {
        CipherText = cipherText;
    }

    /**
     *  the get method to access the most fit plaintext permutation and ensure proper encapsulation.
     * @return most fit plainTextPerm object
     */
    public plainTextPerm getMostFit() {
        return mostFit;
    }

    /**
     *  the set method used to set a most fit plain text permutation and ensure proper encapsulation.
     * @param mostFit a plainTextPerm that is considered the most fit.
     */
    public void setMostFit(plainTextPerm mostFit) {
        this.mostFit = mostFit;
    }

    /**
     * the get method used to access the next five most fit permutations and ensure proper encapsulation.
     * @return an array list representing the next 5 most fit permutations.
     */
    public ArrayList<plainTextPerm> getNextFiveMostFit() {
        return nextFiveMostFit;
    }

    /**
     * the set method used to set a new list of the next five most fit permutations and ensure proper encapsulation.
     * @param nextFiveMostFit the arraylist to set the next five most fit to.
     */
    public void setNextFiveMostFit(ArrayList<plainTextPerm> nextFiveMostFit) {
        this.nextFiveMostFit = nextFiveMostFit;
    }

    /**
     * the get method used to access the translated plain text and ensure proper encapsulation.
     * @return a string representing the translated text.
     */
    public String getTranslatedPlainText() {
        return translatedPlainText;
    }

    /**
     * the set method used to set a new translated plain text and ensure proper encapsulation.
     * @param translatedPlainText the new translated plaintext.
     */
    public void setTranslatedPlainText(String translatedPlainText) {
        this.translatedPlainText = translatedPlainText;
    }
}
