public class QuantityMeasurementApp {

    // ----------- Enum for Units -----------
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084); // 1 cm = 0.0328084 feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // ----------- Generic Quantity Class (UNCHANGED) -----------
    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }
    }

    // ----------- Main Method -----------
    public static void main(String[] args) {

        QuantityLength yardToFeet =
                new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength yardToInch =
                new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength inch =
                new QuantityLength(36.0, LengthUnit.INCH);

        QuantityLength cmToInch =
                new QuantityLength(1.0, LengthUnit.CENTIMETER);
        QuantityLength inchEquivalent =
                new QuantityLength(0.393701, LengthUnit.INCH);

        System.out.println("1 yard == 3 feet ? " + yardToFeet.equals(feet));
        System.out.println("1 yard == 36 inch ? " + yardToInch.equals(inch));
        System.out.println("1 cm == 0.393701 inch ? " + cmToInch.equals(inchEquivalent));
    }
}