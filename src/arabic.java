public enum arabic {
    I(1), IV(4), V(5), IX(9), X(10), XL(40), L(50), XC(90), C(100);

    private final int value;

    arabic(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getByValue(int value) {
        for (arabic numeral : arabic.values()) {
            if (numeral.value == value) {
                return numeral.name();
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }

    public static int getBySymbol(String symbol) {
        for (arabic numeral : arabic.values()) {
            if (numeral.name().equals(symbol)) {
                return numeral.value;
            }
        }
        throw new IllegalArgumentException("Invalid symbol: " + symbol);
    }
    public static boolean contains(String test) {

        for (arabic c : arabic.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }

        return false;
    }
}
