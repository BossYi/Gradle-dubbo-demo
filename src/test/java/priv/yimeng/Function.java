package priv.yimeng;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2018-02-22
 *
 * @author yimeng
 * @version 1.0
 */
public class Function {

    @Test
    public void testFunctionInterface() {
        Consumer<Integer> c = System.out::println;
        c.accept(10);
    }

    @Test
    public void testM0() {
        BiConsumer<Integer, Integer> x = (a, b) -> System.out.println(a + b);
        x.accept(100, 20);
    }

    @Test
    public void testForEach() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        integers.forEach(System.out::println);
    }

    @Test
    public void testStream() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        integers.stream().map(n -> n * n).forEach(Function::print);
    }

    private static void print(Integer x) {
        System.out.println(x);
    }

    @Test
    public void testReduce() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(integers.stream().map(n -> n * n).reduce((x, y) -> x + y).get());
    }

    @Test
    public void testStreamApi() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        integers.stream().filter(n -> n % 2 == 0).sorted((x, y) -> {
            if (x < y) {
                return 1;
            } else if (x.equals(y)) {
                return 0;
            } else {
                return -1;
            }
        }).forEach(System.out::println);
    }
}
