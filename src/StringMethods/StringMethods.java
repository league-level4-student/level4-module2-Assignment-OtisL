package StringMethods;

import java.util.Arrays;
import java.util.Base64;

/*
Visit the JavaDocs for the String class to view everything you can do with a String.  


HINT:  Here are some String methods you might find useful 
contains
replace
trim
length
getBytes
endsWith
split 
indexOf
lastIndexOf
compareTo(IgnoreCase)
substring


Here are some Character methods you might find useful:
Character.toLowerCase(char c);
Character.isLetter(char c);
Character.isDigit(char c);
Character.getNumericValue(char c);
 */

public class StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		if(s1.length()>s2.length()) {
			return s1;
		}else {
			return s2;
		}
	}

	
	// if String s contains the word "underscores", change all of the spaces to underscores
	public static String formatSpaces(String s) {
		if(s.contains("underscores")) {
			s=s.replace(' ', '_');
		}
		return s;
	}

	
	// Return the name of the person whose LAST name would appear first if they were in alphabetical order
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		s1=s1.trim();
		s2=s2.trim();
		s3=s3.trim();
		String s1s = s1.split(" ")[1];
		String s2s = s2.split(" ")[1];
		String s3s = s3.split(" ")[1];
		if(s1s.compareTo(s2s)<1) {
			if(s1s.compareTo(s3s)<1) {
				return s1;
			}else {
				return s3;
			}
		}else {
			if(s2s.compareTo(s3s)<1) {
				return s2;
			}else {
				return s3;
			}
		}
	}
	
	
	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int total = 0;
		for(int i=0; i<s.length(); i++) {
			if(Character.isDigit(s.charAt(i))) {
				total=total+Character.getNumericValue(s.charAt(i));
			}
		}
		return total;
	}
	
	
	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		int index = 0;
		int times = 0;
		while(s.indexOf(substring, index)>=0) {
			index = s.indexOf(substring, index)+substring.length();
			times++;
		}
		return times;
	}

	// Call Utitilities.encrypt to encrypt String s
	public static String encrypt(String s, char key) {
		byte[] bytes = s.getBytes();
		String newKey = key+"";
		byte keyByte = newKey.getBytes()[0];
		String encrypted = Utilities.encrypt(bytes, keyByte);
		return encrypted;
	}

	// Call Utilities.decrypt to decrypt the cyphertext
	public static String decrypt(String s, char key) {
		String newKey = key+"";
		byte keyByte = newKey.getBytes()[0];
		String decrypted = Utilities.decrypt(s, keyByte);
		return decrypted;
	}


	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		int index = 0;
		int times = 0;
		String[] newS=s.split(" ");
		for(int i=0; i<newS.length; i++) {
			if(newS[i].lastIndexOf(substring)>0) {
				if(newS[i].lastIndexOf(substring)+substring.length()==newS[i].length()) {
					times++;
				}
			}
		}
		return times;
	}
	

	// Given String s, return the number of characters between the first occurrence
	// of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		int first=0;
		int second=0;
		first=s.indexOf(substring)+substring.length();
		second=s.lastIndexOf(substring);
		
		return second-first;
	}


	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		s=s.trim();
		String newS = "";
		s=s.replaceAll("[^a-zA-Z]", "");
		s=s.toLowerCase();
		for(int i=s.length()-1; i>=0; i--) {
			newS+=s.charAt(i);
		}
		if(newS.equals(s)) {
			return true;
		}else {
			return false;
		}
	}
	
}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a single
	// byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
