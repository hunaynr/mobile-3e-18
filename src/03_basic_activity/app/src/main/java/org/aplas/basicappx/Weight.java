package org.aplas.basicappx;

public class Weight {
    private double gram;

    public Weight() {
        this.gram = 0;
    }

    public void setGram(double gram) {
        this.gram = gram;
    }

    public void setOunce(double ounce) {
        this.gram = ounce*28.3495231;
    }

    public void setPound(double pound) {
        this.gram = pound*453.59237;
    }

    public double getGram() {
        return gram;
    }

    public double getOunce() {
        return gram/28.3495231;
    }

    public double getPound() {
        return gram/453.59237;
    }

    public double convert(String oriUnit, String convUnit, double value) {
        if(oriUnit.equals("Grm")) {
            setGram(value);
        }
        if(oriUnit.equals("Onc")) {
            setOunce(value);
        }
        else {
            setPound(value);
        }
        if(convUnit.equals("Grm")) {
            return getGram();
        }
        if(convUnit.equals("Onc")) {
            return getOunce();
        }
        else {
            return getPound();
        }
    }
}
