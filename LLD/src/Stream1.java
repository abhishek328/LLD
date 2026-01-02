import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stream1 {
    public static void main(String[] args) {
        // square of number
        List<Integer> num = new ArrayList<>();
        num.add(1);
        num.add(2);
        num.add(3);
        num.add(4);

        num.stream().map(a -> a*a ).forEach(System.out::println);

        // filter start with "A" and convert them lowercase
        List<String> names = Arrays.asList("ABHISHEek", "Ram", "Abhi", "Sh9yam");
        System.out.println(names.stream().filter(name -> name.startsWith("A")).map(String::toLowerCase).collect(Collectors.toSet()));

        // Count how many strings have length greater than 5.
        System.out.println(names.stream().filter(name -> name.length()> 5).collect(Collectors.toSet()).size());

        //Given List<Integer>, find the maximum value using streams.
        System.out.println(num.stream().mapToInt(a-> a).max().getAsInt());

        System.out.println(num.stream().max(Integer::compareTo).get());

        //Convert List<String> to a comma-separated single string.

        System.out.println(names.stream().collect(Collectors.joining(",")));

        //Remove duplicates from a list without using distinct().
        System.out.println(names.stream().collect(Collectors.toSet()));

        //Check if all elements in a list are positive.
        System.out.println(num.stream().allMatch(a-> a>0));

        //Check if any string contains a digit.
        System.out.println(names.stream().anyMatch(a-> a.chars().anyMatch(Character::isDigit)));

        //Convert a List<Integer> into an IntStream and calculate the sum.
        System.out.println(num.stream().mapToInt(a-> a).sum());






    }
}
