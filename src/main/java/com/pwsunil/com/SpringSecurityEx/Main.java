package com.pwsunil.com.SpringSecurityEx;


import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(QuestionsMarks("arrb6???4xxbl5???eee5"));
        int[] arr = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println(maxSubArray(arr));
        String[] strArr = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(strArr));
        Map<String, List<String>> anagrams = Arrays.stream(strArr)
                .collect(Collectors.groupingBy(Main::sortChars));
        System.out.println(anagrams.values());
    }

    public static String QuestionsMarks(String str) {
        int lastNum = -1; // Initialize with an invalid value
        int questionCount = 0;
        boolean foundPair = false;

        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                int num = Character.getNumericValue(c);

                if (lastNum != -1 && lastNum + num == 10) {
                    if (questionCount == 3) {
                        foundPair = true;
                    } else {
                        return "false";
                    }
                    questionCount = 0; // Reset question count after a valid pair is found
                }
                lastNum = num;
            } else if (c == '?') {
                questionCount++;
            }
        }

        return foundPair ? "true" : "false";
    }

    public static int maxSubArray(int[] arr){
        int currentSubArray = arr[0];
        int maxSubArray = arr[0];
        for(int i=1;i<arr.length;i++){
            currentSubArray = Math.max(arr[i], currentSubArray+arr[i]);
            maxSubArray = Math.max(maxSubArray,currentSubArray);
        }
        return maxSubArray;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagrams = new HashMap<>();

        for (String str : strs) {
            char[] word = str.toCharArray();
            Arrays.sort(word); // Sort the characters in the word

            String sortedWord = new String(word);
            if (anagrams.containsKey(sortedWord)) {
                anagrams.get(sortedWord).add(str); // Add to existing anagram group
            } else {
                anagrams.put(sortedWord, new ArrayList<>(Collections.singletonList(str))); // Create a new anagram group
            }
        }

        return new ArrayList<>(anagrams.values());
    }

    private static String sortChars(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}