import edu.duke.*;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int theKey;
    
    public CaesarCipher(int key) {
        //constructer
        theKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) +             //shifting alphabets to right by the number of key
                            alphabet.substring(0,key);
        alphabet = alphabet + alphabet.toLowerCase();
        shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
    }
    
    private char transformLetter(char c, String from, String to) {
        //"from" is the the order of alphabets which needs to be converted or changed
        //"to" is the order of alphabets in which the passed string needs to be converted
        int idx = from.indexOf(c);  //finding the index of the character passed from the "from" alphabets
        if (idx != -1) {
            return to.charAt(idx);  //returing the char at the same index from "to" alphabets
        }
        return c;
    }
    
    public char encryptLetter(char c) {
        //in encyption we trasform from original alphabets order to changed order as per the key
        return transformLetter(c, alphabet, shiftedAlphabet);
    }
    
    public char decryptLetter(char c) {
        //in decryption we trasfrom the changed alphabet order as per the key back to the original order of alphabets
        return transformLetter(c, shiftedAlphabet, alphabet);
    }
    
    private String transform(String input, String from, String to){
        StringBuilder sb = new StringBuilder(input);    //sting builder is used from transformable strings
        for (int i = 0; i < sb.length(); i++) {         //for every character in the string passed
            char c = sb.charAt(i);
            c = transformLetter(c, from, to);           //got the new character code of orignal character as per the key and cipher
            sb.setCharAt(i, c);                         //replaced the original to encrypted code
        }
        return sb.toString();                           //string builder converted to normal string
    }
    
    public String encrypt(String input) {
        return transform(input, alphabet, shiftedAlphabet);
    }
    
    public String decrypt(String input) {
        return transform(input, shiftedAlphabet, alphabet);
    }
    
    public String toString() {
        return "" + theKey;
    }
    
    /*private void test(){
        String text = "AnyDemoText";
        String codedText = objectMade.encrypt(text);        //object is already initialized with key
        System.out.println("Coded text:",codedText);        
            
        String original = objectMade.decrypt(codedText);    //object with same key
        System.out.println("Original text:", original):
    }*/
}
