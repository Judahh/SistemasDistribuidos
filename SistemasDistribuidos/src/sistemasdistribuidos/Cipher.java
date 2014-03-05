/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

/**
 *
 * @subAuthor Name <e-mail>
 * @author Judah Holanda Correia Lima <judahholanda7@gmail.com>
 */
public class Cipher {

   public static boolean isYVowel() {
      return false;
   }
   
   public static boolean isVowel(char vowel) {
      vowel = (vowel + "").toLowerCase().charAt(0);
      return ((vowel == 'a')
              || (vowel == 'e')
              || (vowel == 'i')
              || (vowel == 'o')
              || (vowel == 'u')
              || (isYVowel() && vowel == 'y'));
   }

   public static char getNextVowel(char vowel) {
      boolean isLowerCase = Character.isLowerCase(vowel);
      int intVowel = (vowel + "").toLowerCase().charAt(0) - 97;
      intVowel = intVowel + 4;
      if (intVowel == 12 || intVowel == 18) {
         intVowel = intVowel + 2;
      } else if (isYVowel() && intVowel == 28) {
         intVowel = 0;
      } else if (!isYVowel() && intVowel == 24) {
         intVowel = 0;
      }
      if (isLowerCase) {
         return (char) (intVowel + 97);
      }
      return (char) (intVowel + 65);
   }

   public static char getPreviousVowel(char vowel) {
      boolean isLowerCase = Character.isLowerCase(vowel);
      int intVowel = (vowel + "").toLowerCase().charAt(0) - 97;
      intVowel = intVowel - 4;
      if (intVowel == 10 || intVowel == 16) {
         intVowel = intVowel - 2;
      } else if (isYVowel() && intVowel == -4) {
         intVowel = 24;
      } else if (!isYVowel() && intVowel == -4) {
         intVowel = 20;
      }
      if (isLowerCase) {
         return (char) (intVowel + 97);
      }
      return (char) (intVowel + 65);
   }

   public static char getNextConsonant(char consonant) {
      boolean isLowerCase = Character.isLowerCase(consonant);
      consonant++;

      if (isVowel(consonant)) {
         consonant++;
      }
      
      if(!Character.isLetter(consonant)){
         if(isLowerCase){
            consonant='b';
         }else{
            consonant='B';
         }
      }
      return consonant;
   }

   public static char getPreviousConsonant(char consonant) {
      boolean isLowerCase = Character.isLowerCase(consonant);
      consonant--;

      if (isVowel(consonant)) {
         consonant--;
      }
      
      if(!Character.isLetter(consonant)){
         if(isLowerCase){
            consonant='z';
         }else{
            consonant='Z';
         }
      }
      return consonant;
   }

   public static char getCipheredCharacter(char character) {
      if (Character.isLetter(character)) {
         if (isVowel(character)) {
            character = getNextVowel(character);
         } else {
            character = getNextConsonant(character);
         }
      }
      return character;
   }

   public static char getDecipheredCharacter(char character) {
      if (Character.isLetter(character)) {
         if (isVowel(character)) {
            character = getPreviousVowel(character);
         } else {
            character = getPreviousConsonant(character);
         }
      }
      return character;
   }
   
}
