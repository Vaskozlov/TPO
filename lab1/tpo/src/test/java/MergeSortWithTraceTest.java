import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.vaskozlov.lab1.MergeSortWithTrace;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MergeSortWithTraceTest {

    @ParameterizedTest(name = "Тест: {0}")
    @MethodSource("provideCases")
    void testMergeSortWithTrace(String name,
                                int[] input,
                                List<Integer> expectedSorted,
                                List<String> expectedTrace) {

        List<String> actualTrace = new ArrayList<>();
        List<Integer> result = MergeSortWithTrace.sort(input, actualTrace);

        assertEquals(expectedSorted, result,
                () -> "Неверный результат сортировки для случая: " + name);

        assertEquals(expectedTrace, actualTrace,
                () -> "Трассировка не совпадает с эталонной для случая: " + name +
                        "\n\nПолучено:\n" + String.join("\n", actualTrace) +
                        "\n\nОжидалось:\n" + String.join("\n", expectedTrace));
    }

    private static Stream<Arguments> provideCases() {
        return Stream.of(
                Arguments.of("empty", new int[]{}, List.of(), expectedEmpty()),
                Arguments.of("one", new int[]{7}, List.of(7), expectedOne()),
                Arguments.of("two_desc", new int[]{5, 2}, List.of(2, 5), expectedTwoDesc()),
                Arguments.of("four_mixed", new int[]{4, 1, 3, 2}, List.of(1, 2, 3, 4), expectedFourMixed())
        );
    }

    private static List<String> expectedEmpty() {
        return List.of(
                "ENTER []  (len=0)",
                "BASE_CASE_RETURN []"
        );
    }

    private static List<String> expectedOne() {
        return List.of(
                "ENTER [7]  (len=1)",
                "BASE_CASE_RETURN [7]"
        );
    }

    private static List<String> expectedTwoDesc() {
        return List.of(
                "ENTER [5, 2]  (len=2)",
                "SPLIT mid=1  left_len=1, right_len=1",
                "ENTER   L [5]  (len=1)",
                "BASE_CASE_RETURN   L [5]",
                "LEFT_DONE [5, 2] -> [5]",
                "ENTER   R [2]  (len=1)",
                "BASE_CASE_RETURN   R [2]",
                "RIGHT_DONE [5, 2] -> [2]",
                "START_MERGE  [5] + [2]",
                "TAKE_RIGHT 2  (i=0, j=0)",
                "REMAIN_LEFT 5",
                "MERGE_RESULT [2, 5]"
        );
    }

    private static List<String> expectedFourMixed() {
        return List.of(
                "ENTER [4, 1, 3, 2]  (len=4)",
                "SPLIT mid=2  left_len=2, right_len=2",
                "ENTER   L [4, 1]  (len=2)",
                "SPLIT mid=1  left_len=1, right_len=1",
                "ENTER   L   L [4]  (len=1)",
                "BASE_CASE_RETURN   L   L [4]",
                "LEFT_DONE   L [4, 1] -> [4]",
                "ENTER   L   R [1]  (len=1)",
                "BASE_CASE_RETURN   L   R [1]",
                "RIGHT_DONE   L [4, 1] -> [1]",
                "START_MERGE  [4] + [1]",
                "TAKE_RIGHT 1  (i=0, j=0)",
                "REMAIN_LEFT 4",
                "MERGE_RESULT [1, 4]",
                "LEFT_DONE [4, 1, 3, 2] -> [1, 4]",
                "ENTER   R [3, 2]  (len=2)",
                "SPLIT mid=1  left_len=1, right_len=1",
                "ENTER   R   L [3]  (len=1)",
                "BASE_CASE_RETURN   R   L [3]",
                "LEFT_DONE   R [3, 2] -> [3]",
                "ENTER   R   R [2]  (len=1)",
                "BASE_CASE_RETURN   R   R [2]",
                "RIGHT_DONE   R [3, 2] -> [2]",
                "START_MERGE  [3] + [2]",
                "TAKE_RIGHT 2  (i=0, j=0)",
                "REMAIN_LEFT 3",
                "MERGE_RESULT [2, 3]",
                "RIGHT_DONE [4, 1, 3, 2] -> [2, 3]",
                "START_MERGE  [1, 4] + [2, 3]",
                "TAKE_LEFT  1  (i=0, j=0)",
                "TAKE_RIGHT 2  (i=1, j=0)",
                "TAKE_RIGHT 3  (i=1, j=1)",
                "REMAIN_LEFT 4",
                "MERGE_RESULT [1, 2, 3, 4]"
        );
    }
}