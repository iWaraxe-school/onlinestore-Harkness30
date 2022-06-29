package Operations;

public class Action {
    private String orderName;

    public Action(String type) {
        this.orderName = type;
    }

    public String getType() {
        return orderName;
    }

}
