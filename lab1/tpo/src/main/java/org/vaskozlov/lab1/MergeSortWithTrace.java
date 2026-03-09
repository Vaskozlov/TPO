package org.vaskozlov.lab1;

import java.util.*;
import java.util.stream.Collectors;

public class MergeSortWithTrace {
    public static List<Integer> sort(int[] arr, List<String> trace) {
        if (trace == null) {
            trace = new ArrayList<>();
        } else {
            trace.clear();
        }

        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        return mergeSortRecursive(list, trace, "");
    }

    private static List<Integer> mergeSortRecursive(List<Integer> arr,
                                                    List<String> trace,
                                                    String prefix) {
        int n = arr.size();
        String current = buildCurrent(arr, prefix);

        trace.add("ENTER " + current + "  (len=" + n + ")");

        if (n <= 1) {
            trace.add("BASE_CASE_RETURN " + current);
            return new ArrayList<>(arr);
        }

        int mid = n / 2;
        trace.add(String.format("SPLIT mid=%d  left_len=%d, right_len=%d", mid, mid, n - mid));

        List<Integer> left = mergeSortRecursive(
                new ArrayList<>(arr.subList(0, mid)), trace, prefix + "  L ");
        trace.add("LEFT_DONE " + current + " -> " + left);

        List<Integer> right = mergeSortRecursive(
                new ArrayList<>(arr.subList(mid, n)), trace, prefix + "  R ");
        trace.add("RIGHT_DONE " + current + " -> " + right);

        trace.add("START_MERGE  " + left + " + " + right);

        List<Integer> merged = merge(left, right, trace);
        trace.add("MERGE_RESULT " + merged);

        return merged;
    }

    private static List<Integer> merge(List<Integer> left,
                                       List<Integer> right,
                                       List<String> trace) {
        List<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                merged.add(left.get(i));
                trace.add(String.format("TAKE_LEFT  %d  (i=%d, j=%d)", left.get(i), i, j));
                i++;
            } else {
                merged.add(right.get(j));
                trace.add(String.format("TAKE_RIGHT %d  (i=%d, j=%d)", right.get(j), i, j));
                j++;
            }
        }

        while (i < left.size()) {
            merged.add(left.get(i));
            trace.add("REMAIN_LEFT " + left.get(i));
            i++;
        }

        while (j < right.size()) {
            merged.add(right.get(j));
            trace.add("REMAIN_RIGHT " + right.get(j));
            j++;
        }

        return merged;
    }

    private static String buildCurrent(List<Integer> arr, String prefix) {
        if (arr.isEmpty()) {
            return prefix + "[]";
        }
        return prefix + "[" +
                arr.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")) + "]";
    }
}