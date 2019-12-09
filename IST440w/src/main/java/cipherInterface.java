/**
 *  A java interface that is called by the constructor in order to create a cipher and start the deciphering process.
 *  This interface allows the program to follow the factory design pattern and remove (or mitigate) the need to make changes
 *  to multiple classes when adding support for new ciphers. The interface includes a single method, decipher.
 *
 * @author Joseph Hackett
 */

public interface cipherInterface {

    /**
     * The decipher method should be implemented to handle all logic that the programmer wants to occur when it is time
     * to decipher a cipher. For example: the caesar cipher implementation supported by the prototype calculates all
     * possible permutations, calculates each permutations fitness score, and finally prints the result.
     * @param langConfig A langConfig object is passed into the method to give the cipher access to the necessary language.
     *                   documents.
     */
    public void decipher(langConfig langConfig);
}
