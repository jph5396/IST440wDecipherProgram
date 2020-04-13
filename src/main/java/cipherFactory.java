/**
 *  The cipher factory will hold the logic that will allow the system to identify which cipher is being used.
 *  because this prototype only supports the caesar cipher, it will automatically return that cipher as an object.
 *  To add support for additional ciphers, add the logic to choose between the ciphers and add the proper return statement.
 *  then create a cipher class that implements the cipherInterface.
 */


public class cipherFactory {

    public static cipherInterface createCipher(String cipherText){

        caesarCipher caesarCipher = new caesarCipher(cipherText);
        return caesarCipher;
    }
}
