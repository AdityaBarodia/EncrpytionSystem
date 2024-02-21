import edu.duke.*;
import java.util.*;

public class VigenereCipher {
    CaesarCipher[] ciphers;
    
    public VigenereCipher(int[] key) {
        ciphers = new CaesarCipher[key.length];
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }
    
    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.encryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String toString() {
        return Arrays.toString(ciphers);
    }
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder(message); 
        String slice ="";
        for(int i = whichSlice; i<message.length(); i+=totalSlices) {
            slice = slice + sb.charAt(i);
        }
        return slice;
    }
    
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int []key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i =0 ; i<klength; i++) {
           String slice = sliceString(encrypted,i,klength);
           //cc.getKey(slice);
           key[i]=cc.getKey(slice);
        }
        return key;
    }
    
    public void breakVigenere() {
        FileResource fr = new FileResource();   
        VigenereCipher vc = new VigenereCipher(tryKeyLength(fr.asString(), 4, 'e'));
        System.out.println(vc. decrypt(fr.asString()));
    }
}
