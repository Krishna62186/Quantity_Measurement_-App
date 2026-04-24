public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double toBaseFactor;

    LengthUnit(double toBaseFactor) {
        this.toBaseFactor = toBaseFactor;
    }

    /**
     * Converts a value in this unit to base unit (feet)
     */
    public double convertToBaseUnit(double value) {
        return value * toBaseFactor;
    }

    /**
     * Converts a value from base unit (feet) to this unit
     */
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toBaseFactor;
    }

    public double getConversionFactor() {
        return toBaseFactor;
    }
}