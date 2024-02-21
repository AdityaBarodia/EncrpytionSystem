import java.util.*;
import edu.duke.*;
import java.io.File;
public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
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
           key[i]=cc.getKey(slice);
        }
        return key;
    }  

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        HashMap<String,HashSet<String>> languages = new HashMap();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            HashSet<String> dictionary = readDictionary(new FileResource(f));
            languages.put(f.getName(),dictionary);
        }
        //for message
        FileResource fr = new FileResource();
        String message = fr.asString();
        //for Dictionary
        //FileResource fr2 = new FileResource();
        //HashSet<String> dictionary = readDictionary(fr2);
        //String decrypted = breakForLanguage(message,dictionary);
        //System.out.println(decrypted);
        breakForAllLangs(message, languages);
     }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> wordDictionary = new HashSet();
        for(String wordLine : fr.lines()) {
            wordDictionary.add(wordLine.toLowerCase());
            }
        return wordDictionary;
    }
    
    /*public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        for(String word : message.split("//W")) {
            if(dictionary.contains(word)) {
               count++ ;
            }
        }
        return count;
    }*/
    
    public int countWords(String message, HashSet<String> dictionary) {
        int wordCount = 0;
        List<String> splitMessage = new ArrayList<String>(Arrays.asList(message.split("\\W")));
        for (int index=0;index < splitMessage.size();index++) {
            if (dictionary.contains(splitMessage.get(index).toLowerCase())) {
                wordCount++;
            }
        }
        return wordCount;
    }
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        String decrypted = "";
        int highestWordCount = 0;
        int correctKeyLength = 0;
        for (int index=1; index < 10; index++) {
            char commonChar = mostCommonCharIn(dictionary);
            int[] currentKeys = tryKeyLength(encrypted, index, commonChar);
            VigenereCipher vigCipher = new VigenereCipher(currentKeys);
            String message = vigCipher.decrypt(encrypted);
            int currentWordCount = countWords(message, dictionary);
            if (currentWordCount > highestWordCount) {
                highestWordCount = currentWordCount;
                correctKeyLength = index;
                decrypted = message;
            }
            //System.out.println("Common Word: "+commonChar); 
        }
        System.out.println("Key length: "+correctKeyLength);
        System.out.println("Word count: "+highestWordCount);
        return decrypted;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character,Integer> map = new HashMap();
        for(String words : dictionary) {   
          StringBuilder sb= new StringBuilder(words);
          for(int i =0; i<sb.length() ; i++) {
              char tmp = sb.charAt(i);
              if(!map.keySet().contains(tmp)) {
                 map.put(tmp,1); 
                }
              else {
                 map.put(tmp,map.get(tmp)+1);
                }
            }
        }
        char mostCommonChar = ' ' ; 
        int count = 0;
        for(char ch : map.keySet()) {
           if(map.get(ch) > count) {
               count = map.get(ch);
               mostCommonChar = ch;
            }
        }
        return mostCommonChar;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages) {
        int tmpCount = 0;
        HashSet<String> realDictionary;
        String finalDecrypted ="";
        String flanguage ="";
        for(String language : languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            String decrypted = breakForLanguage(encrypted,dictionary);
            int count = countWords(decrypted,dictionary);
            if(count>tmpCount) {
                count = tmpCount;
                realDictionary = dictionary;
                finalDecrypted = decrypted;
                flanguage = language;
            }
        }
        System.out.println(finalDecrypted);
        System.out.println("The lang is " +flanguage);
    }
  }

