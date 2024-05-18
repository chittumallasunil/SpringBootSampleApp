package com.pwsunil.com.SpringSecurityEx.Controller;

import com.pwsunil.com.SpringSecurityEx.Models.Department;
import com.pwsunil.com.SpringSecurityEx.Models.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class SecurityController {

    @GetMapping("getAllEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employeeList = new ArrayList<>();
        Department dept1 = new Department(1,"Finance");
        Department dept2 = new Department(2,"HR");
        employeeList.add(new Employee(1,"Sunil",dept1,10000.00));
        employeeList.add(new Employee(2,"Varma",dept2, 15000.00));
        return ResponseEntity.ok(employeeList);
    }

    @PostMapping("reverseString")
    public ResponseEntity<String> reverseString(@RequestBody String s){
        //1. Convert String to Character Array
        //2. Take two integers for start with zero and end to string length
        //3. use while loop with condition start<end
        //4. If conditions to check each start and end letter whether those are chars or not, if chars then swap them
        //5. If start not a char then increase start value and same condtion for end value
        //6. Finally collect the swapped char array and convert to string
            //!cd#ab-cd$ab#
        StringBuilder sb = new StringBuilder();
        char[] strArr = s.toCharArray();
        int start =0,end=strArr.length-1;
        while (start<end){
            if(Character.isLetter(strArr[start]) && Character.isLetter(strArr[end])){
                char temp = strArr[start];
                strArr[start]=strArr[end];
                strArr[end]=temp;
                start++;
                end--;
            }
            else if(!Character.isLetter(strArr[start])){
                start++;
            }
            else if(!Character.isLetter(strArr[end])){
                end--;
            }
        }
        return ResponseEntity.ok(new String(strArr));
    }

    @PostMapping("findIntersection")
    public ResponseEntity<String> findIntersection(@RequestBody String[] strArr){
        //  String[] strArr = new String[]{"1, 3, 4, 7, 13", "1, 2, 4, 13, 15"};
        String[] str1 = strArr[0].split(", ");
        String[] str2 = strArr[1].split(", ");

        int i=0, j=0;
        StringBuilder result = new StringBuilder();
        while (i<str1.length && j<str2.length){
            int num1 = Integer.parseInt(str1[i]);
            int num2 = Integer.parseInt(str2[j]);

            if(num1 == num2){
                result.append(num1).append(", ");
                i++;
                j++;
            } else if(num1<num2){
                i++;
            } else {
                j++;
            }
        }
        return ResponseEntity.ok(result.toString());
    }

    @PostMapping("FindIntersectionUsingStreams")
    public ResponseEntity<List<Integer>> FindIntersectionUsingStreams(@RequestBody String[] strArr){
        int[] str1 = Arrays.stream(strArr[0].split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] str2 = Arrays.stream(strArr[1].split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<Integer> result = Arrays.stream(str1).filter(num1-> Arrays.stream(str2).anyMatch(num2->num2==num1))
                .boxed().collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }
    @PostMapping("longestWord")
    public ResponseEntity<String> longestWord(@RequestBody String s){
        String result = Arrays.stream(s.split(" ")).max(Comparator.comparing(String::length)).orElse(null).toString();
        return ResponseEntity.ok(result);
    }

    @PostMapping("mostRepeatingWord")
    public ResponseEntity<Map.Entry<String, Long>> mostRepeatingWord(@RequestBody String[] strArr){
        //String[] strArr = new String[] {"Angular", "React","Dotnet", "Java", "Java","Oracle","Dotnet","Java","Angular"};
        Map.Entry<String, Long> stringLongEntry = Arrays.stream(strArr).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter((x -> x.getValue() > 1)).findFirst().get();
        return ResponseEntity.ok(stringLongEntry);
    }

    @PostMapping("mostRepeatingChar")
    public ResponseEntity<List<Map.Entry<String, Long>>> mostRepeatingChar(@RequestBody String s){
        //"sssfotwaresunilengineeeer"
        List<Map.Entry<String, Long>> collect = Arrays.stream(s.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).stream().collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }

    @PostMapping("mostRepeatingCharWithoutStreams")
    public ResponseEntity<String> mostRepeatingCharWithoutStreams(@RequestBody String s){
        //"sssfotwaresunilengineeeer"
        char[] chArray = s.toCharArray();
        Map<Character,Integer> map = new HashMap<>();

        for(char c : chArray){
            map.put(c,map.getOrDefault(c,0)+1);
        }

        int maxCount =0;
        char mostrepeatedChar = '\0';
        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            if(entry.getValue()>maxCount){
                maxCount=entry.getValue();
                mostrepeatedChar = entry.getKey();
            }
        }
        String result = mostrepeatedChar + ": "+maxCount;
        return ResponseEntity.ok(result);
    }

    @PostMapping("reverseEachWordInString")
    public ResponseEntity<String> reverseEachWordInString(@RequestBody String s){
        //welcome to programmingwithsunil
        //1. Split the words
        //2. convert it to stream
        //3. apply map operator on each word and use the string builder to reverse each word
        //3. collect the reversed words by joining with " "
        String collect = Arrays.stream(s.split(" ")).map(word -> new StringBuilder(word).reverse()).collect(Collectors.joining(" "));
        return ResponseEntity.ok(collect);
    }

    @PostMapping("getNthHighestSalary")
    public ResponseEntity<Optional<Employee>> getNthHighestSalary(@RequestBody String n){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"Jiya Brein",null, 25000.0));
        employees.add(new Employee(2,"Paul Niksui",null, 13500.0));
        employees.add(new Employee(3,"Martin Theron",null, 18000.0));
        employees.add(new Employee(4,"Murali Gowda",null, 32500.0));
        employees.add(new Employee(5,"Nima Roy",null, 22700.0));
        int nValue = Integer.parseInt(n);
        Optional<Employee> first = employees.stream().sorted(Comparator.comparing(Employee::salary).reversed())
                .skip(nValue)
                .findFirst();
        return ResponseEntity.ok(first);
    }

    @PostMapping("groupAnagramsinArray")
    public ResponseEntity<Set<Set<String>>> groupAnagramsinArray(@RequestBody String[] strArr){
        //["eat","tea","tan","ate","nat","bat"]

        Set<Set<String>> finalResult = new HashSet<>();

        for(int i=0;i<strArr.length;i++){
            Set<String> result = new HashSet<>();
            result.add(strArr[i]);
            char[] word1 = strArr[i].toCharArray();
            Arrays.sort(word1);

            for(int j=0;j< strArr.length;j++){
                char[] word2 = strArr[j].toCharArray();
                Arrays.sort(word2);
                if(new String(word1).equals(new String(word2))){
                    result.add(strArr[j]);
                }
            }
            finalResult.add(result);
        }
        return ResponseEntity.ok(finalResult);
    }
}
