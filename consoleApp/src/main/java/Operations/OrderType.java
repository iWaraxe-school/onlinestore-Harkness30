package Operations;

public enum OrderType {
    SORT ("sort"),
    SORT_BY_EXACT_FIELD("sort by field"),
    TOP("top"),
    QUIT("quit");

    private String value;

    OrderType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
