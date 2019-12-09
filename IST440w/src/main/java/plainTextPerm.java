

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigDecimal;
import java.util.Set;

/**
 * The plain test perm class represents a possible plain text outcome calculated during the decipher process. It
 * includes a string representing the possible plaintext out put, and a hashmap where the key is the language name, and
 * the value is the fitness score for the permutation in the specific language.
 */
public class plainTextPerm implements Comparable {
    private String plainText;
    private HashMap<String, Double> fitnessScores;

    /**
     * the constructor called during the decipher process to represent a plain text permutation. the only parameter
     * needed is the plain text as fitness scores will be calculated later.
     *
     * @param plainText a string representing the plaintext output.
     *
     */
    public plainTextPerm(String plainText) {
        this.plainText = plainText;
        this.fitnessScores = new HashMap<String,Double>();
    }

    /**
     * The calcfitnessScores method is responsible for calculating the fitness scores of the permutation for each
     * language supported by the system. It begins by creating a representation of the plain text without whitespace.
     * Then it proceeds to get the score of each 4 character sequence in the permutation and adds it to the overall
     * score for the plain text option. If a sequence does not exist in a language, it adds -100 to the score. This is
     * because the permutation is effectively ruled out for that language when the sequence does not exist.
     *
     * The highest (or lowest if it makes more sense to look at it as an absolute value) will be the correct plain text
     * output for the specific cipher. It is important to note that as a cipher grows in length, the fitness score will
     * get will grow as well, so it is not possible predict if a permutation is correct without comparing scores to
     * other permutations.
     * @param config the language configuration used to access supported languages.
     */
    public void calcFitnessScores(langConfig config){
        ArrayList<Language> LangList = config.getListOfLangs();
        String plainTextNoSpaces = this.plainText.replace(" ", "");

        for (Language lang : LangList){

            BigDecimal fitnessScore = BigDecimal.valueOf(0.0);
            HashMap<String, BigDecimal> quadProbability = lang.getQuadProbability();

            for( int i = 0;  i<= plainTextNoSpaces.length() - 4; ++i){
                String quadGram = plainTextNoSpaces.substring(i,i+4);
                if(quadProbability.containsKey(quadGram)){
                    fitnessScore = fitnessScore.add(quadProbability.get(quadGram));
                }
                else {
                    fitnessScore = fitnessScore.add(BigDecimal.valueOf(-100));
                }

            }
            this.fitnessScores.put(lang.getLangName(),fitnessScore.doubleValue());

        }
    }

    /**
     * the implementation of the compareTo method required by the comparable interface. This is must be implemented in
     * order to sort the arraylist of plaintext permutations.
     * @param o an object to compare to. It is cast to a plaintext permutation object during the method.
     * @return an int value used by the comparable interface to identify which object is greater.
     */
    public int compareTo(Object o) {

        Set<String> keySet = this.fitnessScores.keySet();
        HashMap<String, Double> oHashMap = ((plainTextPerm) o).getFitnessScores();

        Double highestScore = -99999999999999999999.99;
        Double oHighestScore = -99999999999999999999.99;

        for ( String key : keySet) {
            if(this.fitnessScores.get(key) > highestScore) {
                highestScore = this.fitnessScores.get(key);
            }
            if(oHashMap.get(key) > oHighestScore){
                oHighestScore = oHashMap.get(key);
            }

        }

        if(highestScore > oHighestScore) {
            return -1;
        }
        else {
            return 1;
        }
    }

    /**
     * A print function used to print out the fitness score of the permutation. It will need to be edited if the
     * prototype is to be extended to support more than the two original languages.
     */
    public void printPermutation(){
        System.out.println(this.plainText);
        System.out.println("English: " + this.fitnessScores.get("English"));
        System.out.println("Spanish: " + this.fitnessScores.get("Spanish"));
    }

    /**
     * the get plaintext method used to access the plaintext of the permutation and ensure proper encapsulation.
     * @return the plaintext string.
     */
    public String getPlainText() {
        return plainText;
    }

    /**
     * the set method used to set a new plaintext and ensure proper encapsulation.
     * @param plainText the new plaintext for the instance.
     */
    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    /**
     * the get method used to access the hashmap of fitness scores and ensure proper encapsulation.
     * @return a hash map representing the fitness scores for the instance in each language.
     */
    public HashMap<String, Double> getFitnessScores() {
        return fitnessScores;
    }

    /**
     * the set method used to set a new hashmap of fitness scores for the current instance and ensure proper
     * encapsulation.
     * @param fitnessScores the new String, double hash map used to represent the fitness scores of the permutation.
     */
    public void setFitnessScores(HashMap<String, Double> fitnessScores) {
        this.fitnessScores = fitnessScores;
    }


}
