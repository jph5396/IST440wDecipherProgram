
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * The langConfig class is responsible for handling the configuration of the system in regards to supported languages.
 * it allows for additional languages to be added past the original two languages supported by the prototype. It relies
 * on the language_Config.json file in order to locate and load the languages that are supported. If this file is not
 * present at the correct file path, the prototype will not be able to function.
 */
public class langConfig {

    private ArrayList<Language> listOfLangs;

    /**
     * A constructor that takes the list Of languages as a parameter. It is not currently used in the prototype, but has
     * been added in case its use is needed down the line.
     * @param listOfLangs an arraylist of representing the languages supported by the system.
     */
    public langConfig(ArrayList<Language> listOfLangs) {
        this.listOfLangs = listOfLangs;
    }

    /**
     * the langConfig constructor called by the controller class after it receives the absolute file path from the user.
     * it reads from the language_config.json file and then calls the language class method to calculate probabilities
     * for each language supported.
     */
    public langConfig() {
        this.listOfLangs = loadConfigFile();
        for(Language language : listOfLangs) {

            language.calcQuadProbability();
        }

    }

    /**
     * private method called in the constructor to load the language_config.json file. It catches the file not found
     * exception if the language_Config.json file cannot be found.
     * @return an array list of language objects that is used to access each individual language during the decipher
     * process.
     */
    private ArrayList<Language> loadConfigFile(){
        ArrayList<Language> returnThis;
        try {
            Gson gson = new Gson();
            returnThis = gson.fromJson(new FileReader("Languages\\language_Config.json"), new TypeToken<ArrayList<Language>>() {}.getType());
        }

        catch (FileNotFoundException E){
            System.out.println("An error occurred when trying to load the language configuration file.");
            returnThis = new ArrayList<Language>();
            E.printStackTrace();
        }
       return returnThis;
    }
    /**
     * The get method used for accessing the list of languages and ensure proper encapsulation.
     * @return the list of languages supported by the system.
     */
    public ArrayList<Language> getListOfLangs() {
        return listOfLangs;
    }

    /**
     * the set method used to set a new list of languages and ensure proper encapsulation.
     * @param listOfLangs an array list of languages.
     */
    public void setListOfLangs(ArrayList<Language> listOfLangs) {
        this.listOfLangs = listOfLangs;
    }
}
