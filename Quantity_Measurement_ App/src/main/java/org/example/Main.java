public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double toBaseFactor; // base = kilogram

    WeightUnit(double toBaseFactor) {
        this.toBaseFactor = toBaseFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * toBaseFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toBaseFactor;
    }

    public double getConversionFactor() {
        return toBaseFactor;
    }
}