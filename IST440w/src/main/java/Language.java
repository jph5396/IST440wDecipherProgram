
import com.google.gson.annotations.SerializedName;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.math.BigInteger;

/**
 * The language class represents an individual language supported by the prototype decipher program. At this time, only
 * English and Spanish are supported.
 */
public class Language {

    @SerializedName("Language Name")
    private String langName;
    @SerializedName("Dictionary File")
    private String dictFile;
    @SerializedName("Quadgram File")
    private String QuadFile;
    private Set<String> dictionary;
    private HashMap<String, BigDecimal> quadProbability;


    /**
     * The constructor that requires each instance variable to be set when the constructor is called. This constructor
     * is not used because each language is loaded using GSON in the langConfig class. It was created for future use.
     * @param langName the name of the language that the instance represents.
     * @param dictFile the local path to the dictionary for the language the instance represents.
     * @param quadFile the local path to the quadgram count file for the language the instance represents.
     * @param dictionary a set that represents the dictionary loaded from the dictFile.
     * @param quadProbability a Hashmap with each quadgram and its probability in the language the instance represents.
     */
    public Language(String langName, String dictFile, String quadFile, Set<String> dictionary, HashMap<String, BigDecimal> quadProbability) {
        this.langName = langName;
        this.dictFile = dictFile;
        QuadFile = quadFile;
        this.dictionary = dictionary;
        this.quadProbability = quadProbability;
    }


    /**
     * the calcQuadProbability method is responsible for calculating the probability of each quadgram in a given
     * language. quadgram probability is used as a fitness measure when attempting to locate the most fit plain text
     * solution for the cipher text. this method makes use of the big decimal and big integer classes offered in the
     * java math package because our quadgram counts are certain to cause numeric overflow, and the probabilities are
     * certain to cause numeric underflow. To further avoid the issue of numeric underflow, the method takes the log
     * function of each probability.
     */
    public void calcQuadProbability() {

        this.quadProbability = new HashMap<String, BigDecimal>();
        String line;
        HashMap<String, Integer> quadCount = new HashMap<String, Integer>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.QuadFile));
            while ((line = br.readLine()) != null){
                String[] keyVal = line.split(" ");
                quadCount.put(keyVal[0], Integer.valueOf(keyVal[1]));
            }

            Set<String> keySet = quadCount.keySet();
            Iterator<String> itr = keySet.iterator();
            BigInteger sumOfValues = BigInteger.valueOf(0);

            while (itr.hasNext()) {
                BigInteger addVal = BigInteger.valueOf(quadCount.get(itr.next()));
                sumOfValues = sumOfValues.add(addVal);

            }

            BigDecimal sumOfVal = new BigDecimal(sumOfValues);
            Iterator<String> itr2 = keySet.iterator();
            while (itr2.hasNext()) {

                String currentQuad = itr2.next();
                BigDecimal count = new BigDecimal(BigInteger.valueOf(quadCount.get(currentQuad)));

                BigDecimal fitnessScore = BigDecimal.valueOf(Math.log(count.divide(sumOfVal, 20, RoundingMode.HALF_EVEN).doubleValue()));
                this.quadProbability.put(currentQuad,fitnessScore);
            }



        }
        catch (FileNotFoundException E) {

        } catch (IOException e) {
            System.out.println("The quadgram file for " + this.getLangName() + " is missing.");
        }

    }

    /**
     * the get method used to retrieve the language name and ensure encapsulation.
     * @return a String of the language name for the language the instance represents.
     */
    public String getLangName() {
        return langName;
    }

    /**
     * the set method used to set a new language name and ensure proper encapsulation.
     * @param langName a string representing the new language name.
     */
    public void setLangName(String langName) {
        this.langName = langName;
    }

    /**
     * the get method used to get the path to the dictionary file and ensure proper encapsulation.
     * @return a string representing the path to the dictionary file.
     */
    public String getDictFile() {
        return dictFile;
    }

    /**
     * the set method used to set a new path to the dictionary file and ensure proper encapsulation.
     * @param dictFile a string that represents the new path to the dictionary file.
     */
    public void setDictFile(String dictFile) {
        this.dictFile = dictFile;
    }

    /**
     * the get method used to get the path to the quadgram count file and ensure proper encapsulation.
     * @return a string representing the path to the quadgram count file.
     */
    public String getQuadFile() {
        return QuadFile;
    }

    /**
     * the set method used to set a new path to the quadgram count file and ensure proper encapsulation.
     * @param quadFile the new file path to the quadgram count file.
     */
    public void setQuadFile(String quadFile) {
        QuadFile = quadFile;
    }

    /**
     * the get method used to get the dictionary for the language and ensure proper encapsulation.
     * @return a set representing a dictionary for the language.
     */
    public Set<String> getDictionary() {
        return dictionary;
    }

    /**
     *  the set method used to set a new language dictionary and ensure proper encapsulation.
     * @param dictionary a set representing the new dictionary for the language instance.
     */
    public void setDictionary(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * the get method used to access the quad probability of the language and ensure proper encapsulation.
     * @return a hashmap containing each quadgram's probability for the language instance.
     */
    public HashMap<String, BigDecimal> getQuadProbability() {
        return quadProbability;
    }

    /**
     * the set method used to set a new quad probability hashmap and ensure proper encapsulation.
     * @param quadProbability a Hash map with a String key and a BigDecimal value to replace the current hashmap in the
     *                        language instance.
     */
    public void setQuadProbability(HashMap<String, BigDecimal> quadProbability) {
        this.quadProbability = quadProbability;
    }
}
