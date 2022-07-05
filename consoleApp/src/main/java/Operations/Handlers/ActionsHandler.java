package Operations.Handlers;

import Operations.Action;
import java.util.Scanner;

public abstract class ActionsHandler {
    static final Scanner scanner = new Scanner(System.in);
    static final Request request = new Request();

    ActionsHandler next;

    public ActionsHandler linkWith(ActionsHandler next){
        this.next = next;
        return next;
    }

    public abstract void executeAction(Action action);
}
