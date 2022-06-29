package Operations.Handlers;

import Operations.Action;
import utils.parserAndComparator.XMLParser;

import java.util.Map;
import java.util.Scanner;

public abstract class ActionsHandler {
    static final XMLParser PARSER = new XMLParser();
    static final Scanner scanner = new Scanner(System.in);

    ActionsHandler next;

    public ActionsHandler linkWith(ActionsHandler next){
        this.next = next;
        return next;
    }

    public abstract void executeAction(Action action);
}
