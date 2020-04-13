import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Controller {

    /**
     *  The controller class contains the main method to run the decipher program, and generally controls the flow of the program.
     *  The class does NOT need to be changed to support new languages or ciphers. New languages should be added via the Language_Config.JSON
     *  file. If the given filepath does not exist, it will inform the user of the error and prompt the user to recheck the file path and try again.
     * @param args arguments received from the command line. None are required at this point. the system will
     *             prompt the user directly.
     */
    public static void main(String args[]) {

        try{
            System.out.println("Please provide the absolute path to the text file you would like deciphered: ");
            Scanner in = new Scanner(System.in);
            String filePath = in.nextLine();
            langConfig langConfig = new langConfig();

            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String str = br.readLine();

            if(str == null){
                System.out.println("file provided does not have any contents. Please double check the file.");
            }
            else{
                cipherInterface cipher = cipherFactory.createCipher(str);
                cipher.decipher(langConfig);

            }
        }
        catch(IOException E) {
            System.out.println("an error occurred when attempting to access the file at the given path. Please double check the path and try again.");
        }

    }


}
