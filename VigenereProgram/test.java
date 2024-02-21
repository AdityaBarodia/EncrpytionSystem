
/**
 * Write a description of test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class test {
   void sliceTest() {
       int a[] = {1,2,3};
       VigenereCipher v = new VigenereCipher(a);
       System.out.println(v.sliceString("abcdefghijklm",1,4));
   }
   void testTryKeyLength() {
       FileResource fr = new FileResource();
       String s = fr.asString();
       int a[] = {1,2,3,4,5};
       VigenereCipher v = new VigenereCipher(a);
       a = v.tryKeyLength(s,4,'e');
       for(int i = 0 ; i<4 ; i++) {
           System.out.print(a[i] + " " );
        }
    }
    
   void testBreakVigenere() {
       int a[] = {1,2,3,4,5};
       VigenereCipher v = new VigenereCipher(a);  
       v.breakVigenere();
    }
    
   void testForLanguage() {
       VigenereBreaker vb = new VigenereBreaker();
       //vb.breakVigenere();
       //FileResource fr = new FileResource();
        //String message = fr.asString();
        //System.out.println(message);
        //for Dictionary
        //FileResource fr2 = new FileResource();
        //HashSet<String> dictionary = vb.readDictionary(fr2);
        //System.out.println(dictionary);
        //String decrypted = vb.breakForLanguage(message,dictionary);
        //System.out.println(decrypted);
       vb.breakVigenere();
    }
    
   void testMostCommonCharIn() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        HashSet<String> dictionary = vb.readDictionary(fr);
        System.out.println(vb.mostCommonCharIn(dictionary));
    }
   
    void testCeaser(){
        
    }
}
