package home.LinearRegression;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Implement {

    private final ReadCSV readcsv = new ReadCSV();
    private ArrayList<Double> nums_customer;
    private ArrayList<Double> prices;

    public Implement() throws FileNotFoundException { }

    private static ArrayList<Double> multiVector(ArrayList<Double> a, double m) {
        ArrayList<Double> res = new ArrayList<>(a.size());
        for (int i=0; i<a.size(); i++) res.add(0.0);
        for (int i=0; i<a.size(); i++) {
            res.set(i, a.get(i)*m);
        }
        return res;
    }
    private static void addVectorize(ArrayList<Double> a, double m) {
        for (int i=0; i<a.size(); i++) {
            a.set(i, a.get(i)+m);
        }
    }

    private static ArrayList<Double> minusVector(final ArrayList<Double> a, final ArrayList<Double> b) {
        ArrayList<Double> res = new ArrayList<>(a.size());
        for (int i=0; i<a.size(); i++) res.add(0.0);
        for (int i=0; i<a.size(); i++) {
            res.set(i, a.get(i) - b.get(i));
        }
        return res;
    }

    private static double sumVectorize(final ArrayList<Double> a) {
        double sum = 0;
        for (double d:a) { sum += d; }
        return sum;
    }

    private static ArrayList<Double> multiVectorize(final ArrayList<Double> a, final ArrayList<Double> b) {
        ArrayList<Double> res = new ArrayList<>(a.size());
        for (int i=0; i<a.size(); i++) res.add(0.0);
        for (int i=0; i<a.size(); i++) { res.set(i, a.get(i)*b.get(i)); }
        return res;
    }

    private double calculateStandardDeviation(ArrayList<Double> csv) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = csv.size();
        for (double num: csv) sum += num;
        double mean = sum/length;
        for (double num: csv) {
            standardDeviation += Math.pow(num-mean, 2);
        }
        return Math.sqrt(standardDeviation/length);
    }

    private double calculateMean(ArrayList<Double> csv) {
        double sum = 0.0;
        for (int i=0; i<csv.size(); i++) sum += csv.get(i);
        return sum/csv.size();
    }

    private void FeatureScaling (ArrayList<Double> csv1, ArrayList<Double> csv2) {
        double sd1 = this.calculateStandardDeviation(csv1);
        double sd2 = this.calculateStandardDeviation(csv2);
        double mean1 = this.calculateMean(csv1);
        double mean2 = this.calculateMean(csv2);
        for (int i=0; i<csv1.size(); i++) {
            double temp1 = (csv1.get(i) - mean1)/sd1;
            double temp2 = (csv2.get(i) - mean2)/sd2;
            csv1.set(i, temp1);
            csv2.set(i, temp2);
        }
    }

    public static double predictProfit(Double cus, ArrayList<Double> csv1, ArrayList<Double> csv2) {
        double ax = 0.0;
        double ay = 0.0;
        double n = csv1.size();
        double learning_rate = 0.0001;
        int loops = 1000;
        ArrayList<Double> Y_predict = new ArrayList<>(csv1.size());
        for (int i=0; i<csv2.size(); i++) Y_predict.add(0.0);
        for (int i=0; i<loops; i++) {
            Y_predict = multiVector(csv1, ax);
            addVectorize(Y_predict, ay);
            double dx = (-2.0/n) * sumVectorize(multiVectorize(csv1, minusVector(csv2,Y_predict)));
            double dy = (-2.0/n) * sumVectorize(minusVector(csv2, Y_predict));
            ax = ax - dx*learning_rate;
            ay = ay - dy*learning_rate;
        }
        double result = ax*cus + ay;
        return result;
    }

}
