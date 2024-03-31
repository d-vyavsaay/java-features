package org.features.java8;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//ref :- https://stackify.com/streams-guide-java-8/
public class StreamsFeatures {

    public static void main(String[] args) {
        List<String> lst = Arrays.asList("deepak", "deepanshu","deepankar","suhasini");

//        foreach - simple iteration , however note that this is a terminal operation, i.e it does not return a stream, it closes the stream
        lst.forEach(System.out::println);

//        filter
        List<String> deep = lst.stream().filter(s -> s.contains("deep")).collect(Collectors.toList());
        System.out.println(deep);

//        using predicates
        Predicate<String> p = s -> s.length() > 6 ;
        List<String> longer = lst.stream().filter(p).collect(Collectors.toList());
        System.out.println(longer);
        
//        Stream.of in place of Arrays.asList
        Stream<String> jingle = Stream.of("jingle", "mingle", "single", "tingle");
//        mapping - modifying the objects in the stream
        List<Character> firstChars = jingle.map(s -> s.charAt(0)).collect(Collectors.toList());
        System.out.println(firstChars);

//        converting a stream to an array
        Object[] array = lst.toArray();//here we might have to use a cast
        String[] stringArray = lst.toArray(String[]::new); // without a cast

//        flatmap - flattens data structures with higher dimensions. ref:- https://www.geeksforgeeks.org/stream-flatmap-java-examples/
        Stream<List<String>> nameStream = Stream.of(
                Arrays.asList("Deepak", "Singh"),
                Arrays.asList("Suhasini", "Singh"),
                Arrays.asList("Deepanshu", "Singh"),
                Arrays.asList("Deepankar", "Singh")
        );
        List<String> collect = nameStream.flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(collect);

//        peek - is just like for each, in that you can perform any kind of operation on each of the elements, the only difference is that it is not a terminal operation
        List<String> peek = lst.stream()
                .peek(str -> System.out.println(str.charAt(0)))//perform an operation on each of the elements in the list
                .toList();//the stream is not closed yet and you can perform any streams operation on it.

        //sorted method to sort a list
        List<String> sorted = lst.stream().sorted((s1, s2) -> s1.compareTo(s2)).collect(Collectors.toList());
        System.out.println("sorted :" + sorted);

        //min - find out the minimum from a list
        List<Integer> intList = Arrays.asList(1, 2, 4, 8, 9, 7, 15, 14, 23, 52, 14);
        int min = intList.stream().min((i1,i2) -> i1 - i2)
                .orElseThrow(NoSuchElementException::new);
        System.out.println("min :- " + min);

//        max - find out the max from a list
        int max = intList.stream().max((i1, i2) -> i1 - i2).orElseThrow(NoSuchElementException::new);
        System.out.println("max: " + max);

//        distinct - irons out duplicates from the list. If the elements are objects then, they should implement the equals method,
//        as distinct uses the equals method for identifying equal objects.
        List<Integer> duplicateList = Arrays.asList(1, 1, 5, 4, 8, 9, 12, 12, 54, 23, 12, 5, 85, 41, 25, 1, 2, 6, 7, 8, 12);
        List<Integer> distinctList = duplicateList.stream().distinct().sorted(Integer::compareTo).toList();
        System.out.println("distinct " + distinctList);


    }
}
