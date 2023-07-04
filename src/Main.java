import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = generateNumbersList();

        Comparator<Integer> integerComparator = Comparator.naturalOrder();

        BiConsumer<Integer, Integer> minMaxConsumer = (min, max) -> {
            System.out.println("Min: " + min);
            System.out.println("Max: " + max);
        };

        System.out.println(numbers);
        countAndPrintEvenNumbers(numbers);
        findMinMax(numbers.stream(), integerComparator, minMaxConsumer);
    }

    public static <T> void findMinMax(Stream<? extends T> stream, Comparator<? super T> order, BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<? extends T> list = stream.toList();
        Optional<? extends T> minOptional = list.stream().min(order);
        Optional<? extends T> maxOptional = list.stream().max(order);
        T min = minOptional.orElse(null);
        T max = maxOptional.orElse(null);
        minMaxConsumer.accept(min, max);
    }

    private static List<Integer> generateNumbersList() {
        return Stream.generate(Main::generateRandomNumber)
                .limit(10)
                .collect(Collectors.toList());
    }

    public static void countAndPrintEvenNumbers(List<Integer> numbers) {
        long evenCount = numbers.stream()
                .filter(n -> n % 2 == 0)
                .peek(System.out::println)
                .count();
        System.out.println("Кол-во четных чисел: " + evenCount);
    }


    private static int generateRandomNumber() {
        return (int) (Math.random() * 100);
    }
}