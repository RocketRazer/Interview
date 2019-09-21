import java.util.*;

public class Test {

    private static int test() {
        int[] a = new int[]{1, 2, 3};
        System.out.println(a[1] = 88);
        return a[0] = 99;
    }


    private static int[] removeDuplicates(int[] candidates) {
        Arrays.sort(candidates);

        int index = 0;
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] != candidates[index]) {
                candidates[++index] = candidates[i];
            }
        }

        int[] nums = new int[index + 1];
        for (int i = 0; i < index + 1; i++) {
            nums[i] = candidates[i];
        }

        return nums;

    }


    public static void main(String[] args) {
//        // creating Arrays of String type
//        String[] a = new String[] { "A", "B", "C", "D" };
//        // getting the list view of Array
//        List<String> list = Arrays.asList(a);
//        // printing the list
//        System.out.println("The list is: " + list);
//
//
//
//
//        int[] b = new int[]{1, 2, 3};
//
//        Integer[] b2 = new Integer[3];
//        b2[0] = 1;
//        b2[1] = 2;
//        b2[2] = 3;
//        // getting the list view of Array
//        List<Integer> list2 = Arrays.asList(b2);
//
//        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
//
//        List<Integer> list3 = new ArrayList<>();
//
//        list.toArray();
//


        List<Integer> intergerList =new ArrayList<>();
        intergerList.add(1);
        intergerList.add(2);
        intergerList.add(3);

        int[] array = intergerList.stream().mapToInt(i->i).toArray();
        System.out.println(Arrays.toString(array));
    }
}
