import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingAlgorithms {

    public static void mergeSort(List<Integer> listOfInts) {
        mergeSortRecursive(listOfInts, 0, listOfInts.size() - 1);
    }

    private static void mergeSortRecursive(List<Integer> listOfInts, int start, int end) {
        if (start >= end) {
            // No need to sort with length of 0 or 1
            return;
        }
        int mid = (start + end) / 2;
        mergeSortRecursive(listOfInts, start, mid);
        mergeSortRecursive(listOfInts, mid+1, end);
        merge(listOfInts, start, mid, end);
    }

    // Method to merge two sorted list into one, the first list has indices from start to mid, the second list has indices from mid+1 to end
    private static void merge(List<Integer> listOfInts, int start, int mid, int end) {
        List<Integer> mergedList = new ArrayList<>();
        int originalStart = start;
        int start2 = mid+1;
        while (start <= mid && start2 <= end) {
            if (listOfInts.get(start) <= listOfInts.get(start2)) {
                mergedList.add(listOfInts.get(start));
                start += 1;
            } else {
                mergedList.add(listOfInts.get(start2));
                start2 += 1;;
            }
        }
        if (start <= mid) {
            mergedList.addAll(listOfInts.subList(start, mid));
        }
        if (start2 <= end) {
            mergedList.addAll(listOfInts.subList(start2, end));
        }
        for (int i = 0; i < mergedList.size(); i++) {
            listOfInts.set(i + originalStart, mergedList.get(i));
        }
    }

    public static void quickSort(List<Integer> listOfInts) {
        // First, do random shuffle
        Collections.shuffle(listOfInts);
        quickSortRecursive(listOfInts, 0, listOfInts.size() - 1);
    }

    private static void quickSortRecursive(List<Integer> listOfInts, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivotIndex = partition(listOfInts, start, end);
        quickSortRecursive(listOfInts, start, pivotIndex - 1);
        quickSortRecursive(listOfInts, pivotIndex + 1, end);
    }

    // Always using the last index as pivot
    private static int partition(List<Integer> listOfInts, int start, int end) {
        int i = start;
        int j = start;
        int pivot = listOfInts.get(end);
        while (j < end) {
            if (listOfInts.get(j) <= pivot) {
                if (i != j) {
                    // Need to swap i and j
                    swap(listOfInts, i, j);
                }
                i++;
            }
            j++;
        }
        // By the end, need to swap between pivot and i
        swap(listOfInts, i, end);
        return i;
    }

    private static void swap(List<Integer> listOfInts, int i, int j) {
        int temp = listOfInts.get(i);
        listOfInts.set(i, listOfInts.get(j));
        listOfInts.set(j, temp);
    }
    
}
