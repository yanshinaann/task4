package task4;

public class Field {
    private double weight;
    private double height;
    private double mu; // коэф трения
    private double g;

    public Field(double weight, double height) {
        this.weight = weight;
        this.height = height;
        mu = 0;
        g = 9.8;
    }

    public double getMu() {
        return mu;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }
}
