import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println(output());

    }

    private static String output() throws IOException {
        final int amountOfSculptures;
        final int perfectAmountOfIce;
        int timeToCelebration;

        List<Integer> amountOfIceInEachSculpture = new ArrayList<>();

        int amountOfPerfectSculptures = 0;
        List<Integer> numbersOfPerfectSculptures = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            String[] firstLine = bufferedReader.readLine().split(" ");
            String[] secondLine = bufferedReader.readLine().split(" ");

            amountOfSculptures = Integer.parseInt(firstLine[0]);
            perfectAmountOfIce = Integer.parseInt(firstLine[1]);
            timeToCelebration = Integer.parseInt(firstLine[2]);

            for (int i = 0; i < amountOfSculptures; i++) {
                amountOfIceInEachSculpture.add(i, Integer.parseInt(secondLine[i]));
            }
        }

        int previousValue = -1;
        int minValue = 0;
        int minIndex = 0;

        int counter = 0;

        while (true) {
            //поиск минимального значения
            boolean isFirst = true;

            for (int i = 0; i < amountOfIceInEachSculpture.size(); i++) {
                int currentValue = amountOfIceInEachSculpture.get(i);

                if (currentValue <= previousValue) continue;

                if (isFirst) {
                    minValue = currentValue;
                    minIndex = i;
                    isFirst = false;
                }

                if (minValue > currentValue) {
                    minValue = currentValue;
                    minIndex = i;
                }
            }

            int timeForCurrentSculpture = Math.abs(minValue - perfectAmountOfIce);

            if (timeToCelebration < Math.abs(timeForCurrentSculpture)) {
                break;
            } else {
                timeToCelebration -= timeForCurrentSculpture;
                amountOfPerfectSculptures++;
                numbersOfPerfectSculptures.add(minIndex + 1);
                previousValue = minValue;

            }
            counter++;
            if (counter == amountOfSculptures) break;
        }

        StringBuilder sb = new StringBuilder();
        numbersOfPerfectSculptures.sort(Comparator.naturalOrder());
        for (int number : numbersOfPerfectSculptures) {
            sb.append(number + " ");
        }
        return amountOfPerfectSculptures + "\n" + sb;
    }
}