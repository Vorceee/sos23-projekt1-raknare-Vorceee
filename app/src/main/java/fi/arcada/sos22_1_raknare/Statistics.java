package fi.arcada.sos22_1_raknare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Statistics {



    public static ArrayList<Double> sortValues(ArrayList<Double> values) {

        ArrayList<Double> sorted = new ArrayList<>(values);
        Collections.sort(sorted);
        return sorted;
    }
    public static double min(ArrayList<Double> values) {
        ArrayList<Double> sorted = sortValues(values);

        double result = sorted.get(0);

        return result;
    }
    public static double max(ArrayList<Double> values) {
        ArrayList<Double> sorted = new ArrayList<>(values);
        Collections.sort(sorted, Collections.reverseOrder());

        double result = sorted.get(0);

        return result;
    }



    public static double calcMedian(ArrayList<Double> values) {
        ArrayList<Double> sorted = sortValues(values);
        int middle = sorted.size()/2;
        double median = sorted.get(middle);

        // Om datamängden har jämnt antal
        if (sorted.size() % 2 == 0) {
            median = (sorted.get(middle) + sorted.get(middle-1)) / 2;
        }

        return median;
    }

    public static double calcMean(ArrayList<Double> values) {
        System.out.println(values.size());

        double total = 0;
        for (int i = 0; i < values.size(); i++) {
            total += values.get(i);
        }
        return total / values.size();

    }

    public static double calcLQ(ArrayList<Double> values) {
        ArrayList<Double> sorted = sortValues(values);
        int lq = sorted.size()/4;
        double result = sorted.get(lq);

        return result;

    }
    public static double calcUQ(ArrayList<Double> values) {
        ArrayList<Double> sorted = new ArrayList<>(values);
        Collections.sort(sorted, Collections.reverseOrder());


        int lq = sorted.size()/4;
        double result = sorted.get(lq);

        return result;

    }

    public static double calcQR(ArrayList<Double> values) {
        double LQ = calcLQ(values);
        double UQ = calcUQ(values);

        double result = UQ - LQ;

        return result;

    }

    public static double calcSD(ArrayList<Double> values) {
        double mean = calcMean(values);
        double sumOfDiff = 0;
        for (int i = 0; i < values.size(); i++) {
            sumOfDiff += Math.pow(values.get(i)-mean, 2);
        }
        double variance = sumOfDiff / values.size();
        return Math.sqrt(variance);
    }

    public static double calcMode(ArrayList<Double> dataset) {
        HashMap<Double, Integer> valueCount = new HashMap<>();

        for (double dataValue: dataset) {
            Integer count = valueCount.get(dataValue);
            if (count == null)  count = 0;
            valueCount.put(dataValue, count+1);

        }
        int maxCount = 0;
        double modeValue = 0.0;


        for (Double dataValue: valueCount.keySet()) {
            Integer curCount = valueCount.get(dataValue);


            if (curCount > maxCount) {
                maxCount = curCount;
                modeValue = dataValue;
            }
        }
        return modeValue;

    }

}
