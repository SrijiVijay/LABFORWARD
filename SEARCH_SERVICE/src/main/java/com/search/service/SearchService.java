package com.search.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

/**
 * The Class Search SimilarWords as per levenshtein distance java code 
 */
@Service
public class SearchService {
	
	/**
	 * Min.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return the int
	 */
	static int min(int x, int y, int z) {
		if (x <= y && x <= z)
			return x;
		if (y <= x && y <= z)
			return y;
		else
			return z;
	}

	/**
	 * Edits the dist.
	 *
	 * @param str1 the str 1
	 * @param str2 the str 2
	 * @param m the m
	 * @param n the n
	 * @return the int
	 */
	private static int findDistance(String str1, String str2, int m, int n) {
		// If first string is empty, the only option is to
		// insert all characters of second string into first
		if (m == 0)
			return n;

		// If second string is empty, the only option is to
		// remove all characters of first string
		if (n == 0)
			return m;

		// If last characters of two strings are same,
		// nothing much to do. Ignore last characters and
		// get count for remaining strings.
		if (str1.charAt(m - 1) == str2.charAt(n - 1))
			return findDistance(str1, str2, m - 1, n - 1);

		// If last characters are not same, consider all
		// three operations on last character of first
		// string, recursively compute minimum cost for all
		// three operations and take minimum of three
		// values.
		return 1 + min(findDistance(str1, str2, m, n - 1), // Insert
				findDistance(str1, str2, m - 1, n), // Remove
				findDistance(str1, str2, m - 1, n - 1) // Replace
		);
	}

	// Returns true if edit distance
	/**
	 * Checks if is edits the distance one.
	 *
	 * @param s1 the s 1
	 * @param s2 the s 2
	 * @return true, if is edits the distance one
	 */
	// between s1 and s2 is one, else false
	@SuppressWarnings("unused")
	private boolean isEditDistanceOne(String s1, String s2) {
		// Find lengths of given strings
		int m = s1.length(), n = s2.length();

		// If difference between lengths is
		// more than 1, then strings can't
		// be at one distance
		if (Math.abs(m - n) > 1)
			return false;

		int count = 0; // Count of edits

		int i = 0, j = 0;
		while (i < m && j < n) {
			// If current characters don't match
			if (s1.charAt(i) != s2.charAt(j)) {
				if (count == 1)
					return false;

				// If length of one string is
				// more, then only possible edit
				// is to remove a character
				if (m > n)
					i++;
				else if (m < n)
					j++;
				else // Iflengths of both strings
						// is same
				{
					i++;
					j++;
				}

				// Increment count of edits
				count++;
			}

			else // If current characters match
			{
				i++;
				j++;
			}
		}

		// If last character is extra
		// in any string
		if (i < m || j < n)
			count++;

		return count == 1;
	}
	
	public static int getDistance(String str1, String str2) 
    { 
  
        int l1 = str1.length(); 
        int l2 = str2.length(); 
  
        if (l1 == 0) 
            return l2; 
  
        if (l2 == 0) 
            return l1; 
  
        int arr[][] = new int[l1 + 1][l2 + 1]; 
  
        for (int i = 0; i <= l1; i++) 
            arr[i][0] = i; 
  
        for (int j = 0; j <= l2; j++) 
            arr[0][j] = j; 
  
        for (int i = 1; i <= l1; i++) { 
            char ch1 = str1.charAt(i - 1); 
  
            for (int j = 1; j <= l2; j++) { 
                char ch2 = str2.charAt(j - 1); 
  
                int m = ch1 == ch2 ? 0 : 1; 
  
                arr[i][j] = Math.min( 
                    Math.min((arr[i - 1][j] + 1), 
                             (arr[i][j - 1] + 1)), 
                    arr[i - 1][j - 1] + m); 
            } 
        } 
  
        return arr[l1][l2]; 
    } 
	
	public Map<String, String> searchData(String str, String newWord) {
		String[] splitStr = str.split(" ");
		Map<String, String> outputMap = new HashMap<>();

		Map<String, Integer> wordCount = new TreeMap<String, Integer>();
		List<String> similarWords = new ArrayList<>();
		for (String word : splitStr) {
			if(findDistance(word, newWord, word.length(), newWord.length()) == 1) {
				similarWords.add(word);
			}
			if (wordCount.containsKey(word)) {
				// Map already contains the word key, increment it's count by 1
				wordCount.put(word, wordCount.get(word) + 1);
				
			} else {
				// Map doesn't have mapping for word. Add one with count = 1
				wordCount.put(word, 1);
			}
		}

		boolean matchFound = false;
		for (Entry<String, Integer> entry : wordCount.entrySet()) {
			if (entry.getKey().equals(newWord)) {
				outputMap.put("Frequency Count", "Count of " + "'" + entry.getKey() + "'" +" is " + entry.getValue());
				matchFound = true;
			}
		}
		if (!matchFound) {
			outputMap.put("Frequency Count", "Oops theres no occurance.");
		}
		outputMap.put("SimilarWords", similarWords.toString());
		return outputMap;
	}


}
