package eu.poliks.pspigot.util;

public class IntelligentAverage
{
    public double total;
    public int count;
    public double base;

    public IntelligentAverage(final double base) {
        this.total = 0.0;
        this.count = 0;
        this.base = base;
    }

    public void add(final double num) {
        this.total += num;
        ++this.count;
        if (this.count > 50) {
            this.total -= this.getAverage();
            --this.count;
        }
    }

    public void reset() {
        this.total = 0.0;
        this.count = 0;
    }
    //

    public double getAverage() {
        if (this.count < 20) {
            return this.base;
        }
        return this.total / this.count;
    }
}
