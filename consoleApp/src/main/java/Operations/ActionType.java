package Operations;

public enum ActionType {
    SORT ("sort"),
    SORT_BY_EXACT_FIELD("sort by field"),
    TOP("top"),
    QUIT("quit"),
    CART("cart"),
    CREATE_ORDER("order");

    private String value;

    ActionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
