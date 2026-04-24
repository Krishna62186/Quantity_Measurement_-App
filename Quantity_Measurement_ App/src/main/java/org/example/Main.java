public final class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 1e-6;

    public QuantityLength(double value, LengthUnit unit) {
        validate(value, unit);
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    // -----------------------------
    // ✅ UC5: Conversion
    // -----------------------------
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        validate(value, source);

        if (target == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        if (source == target) {
            return value;
        }

        double base = source.toBase(value);
        return target.fromBase(base);
    }

    public QuantityLength convertTo(LengthUnit target) {
        double result = convert(this.value, this.unit, target);
        return new QuantityLength(result, target);
    }

    // -----------------------------
    // ✅ UC6: Addition (instance)
    // Result in unit of FIRST operand
    // -----------------------------
    public QuantityLength add(QuantityLength other) {
        validateObject(other);

        double sumBase = this.toBase() + other.toBase();
        double result = this.unit.fromBase(sumBase);

        return new QuantityLength(result, this.unit);
    }

    // -----------------------------
    // ✅ UC6: Addition (static with target unit)
    // -----------------------------
    public static QuantityLength add(QuantityLength a, QuantityLength b, LengthUnit targetUnit) {
        validateObject(a);
        validateObject(b);

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double sumBase = a.toBase() + b.toBase();
        double result = targetUnit.fromBase(sumBase);

        return new QuantityLength(result, targetUnit);
    }

    // -----------------------------
    // ✅ UC6: Overloaded method (raw values)
    // -----------------------------
    public static QuantityLength add(double v1, LengthUnit u1,
                                     double v2, LengthUnit u2,
                                     LengthUnit targetUnit) {

        if (!Double.isFinite(v1) || !Double.isFinite(v2)) {
            throw new IllegalArgumentException("Values must be finite");
        }

        QuantityLength q1 = new QuantityLength(v1, u1);
        QuantityLength q2 = new QuantityLength(v2, u2);

        return add(q1, q2, targetUnit);
    }

    // -----------------------------
    // Private helpers
    // -----------------------------
    private double toBase() {
        return unit.toBase(value);
    }

    private static void validate(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
    }

    private static void validateObject(QuantityLength q) {
        if (q == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
    }

    // -----------------------------
    // equals() using base comparison
    // -----------------------------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        return Math.abs(this.toBase() - other.toBase()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBase());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}