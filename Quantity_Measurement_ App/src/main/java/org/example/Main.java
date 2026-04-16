public class QuantityMeasurementApp {

    // Inner class representing Feet measurement
    static class Feet {
        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        // Overriding equals method
        @Override
        public boolean equals(Object obj) {
            // Same reference check (Reflexive)
            if (this == obj) return true;

            // Null and type check
            if (obj == null || getClass() != obj.getClass()) return false;

            // Type casting
            Feet other = (Feet) obj;

            // Compare double values safely
            return Double.compare(this.value, other.value) == 0;
        }

        // (Optional but recommended) Override hashCode when equals is overridden
        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // Main method to test functionality
    public static void main(String[] args) {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);

        boolean result = feet1.equals(feet2);

        System.out.println("Are the two measurements equal? " + result);
    }
}