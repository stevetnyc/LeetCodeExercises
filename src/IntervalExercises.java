import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IntervalExercises {


    public static List<List<Integer>> employeeFreeTime(List<List<List<Integer>>> schedule) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> sorted = new ArrayList<>();

        for (List<List<Integer>> empSched: schedule) {
            for (List<Integer> slot: empSched) {
                sorted.add(slot);
            }
        }

        sorted.sort(Comparator.comparingInt(a -> a.get(0)));

        List<List<Integer>> merged = new ArrayList<>();

        for (List<Integer> slot: sorted) {
            if (merged.size() == 0 || merged.get(merged.size() - 1).get(1) < slot.get(0)) {
                merged.add(slot);
            } else {
                merged.get(merged.size() - 1).add(1, Math.max(merged.get(merged.size() - 1).get(1), slot.get(1)));
            }
        }

        for (int i = 1; i < merged.size(); i++) {
            int start = merged.get(i - 1).get(1);
            int end = merged.get(i).get(0);

            result.add(new ArrayList<>(List.of(start, end)));
        }

        return result;
    }

    public static void  main (String[] args) {

        //schedule = [[[2,4],[7,10]],[[1,5]],[[6,9]]]
        //[[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
        List<List<List<Integer>>> schedule = new ArrayList<>();
        List<List<Integer>> empSched = new ArrayList<>();
        empSched.add(new ArrayList<>(List.of(1,3)));
        empSched.add(new ArrayList<>(List.of(6,7)));
        schedule.add(empSched);
        empSched = new ArrayList<>();
        empSched.add(new ArrayList<>(List.of(2,4)));
        schedule.add(empSched);
        empSched = new ArrayList<>();
        empSched.add(new ArrayList<>(List.of(2,5)));
        empSched.add(new ArrayList<>(List.of(9,12)));
        schedule.add(empSched);

        Utils.printList(employeeFreeTime(schedule));


    }
}
