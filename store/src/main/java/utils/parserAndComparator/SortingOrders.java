package utils.parserAndComparator;

public enum SortingOrders {
    ASC("asc"),
    DESC("desc");

    private String value;

    SortingOrders(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
