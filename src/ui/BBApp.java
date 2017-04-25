package ui;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import model.Friend;
import utility.Validator;

/**
 * A black book rating app. Now using Java API data structures.
 *
 * @author 55jphillip
 */
public class BBApp {

    List<Friend> list;

    public BBApp() {
        this.list = new LinkedList();
        list.add(new Friend("Barb", 9));
        list.add(new Friend("Steve", 5));
        list.add(new Friend("Tom", 7));
        list.add(new Friend("Sue", 3));
        list.add(new Friend("Mark", 9));
        start();
    }

    private void start() {
        Scanner sc = new Scanner(System.in);
        String choice;
        while (true) {
            menu();
            String regex = "^quit|add|remove|list|name|reverse|rating|search|range$";
            choice = Validator.getLine(sc, "Enter your choice: ", regex);
            if (choice.equalsIgnoreCase("quit")) {
                break;
            } else if (choice.equals("add")) {
                String name = Validator.getLine(sc, "Enter friend's name: ");
                int rating = Validator.getInt(sc, "Enter rating: ");
                list.add(new Friend(name, rating));
            } else if (choice.equals("remove")) {
                int index = Validator.getInt(sc, "Enter index to remove: ");
                list.remove(index);
            } else if (choice.equals("list")) {
                System.out.println(list);
            } else if (choice.equals("name")) {
                Collections.sort(list, (a, b) -> a.getName().compareTo(b.getName()));
//                // If we don't want to change the list then use a temporary array:
//                Friend[] fa = list.toArray(new Friend[0]);
//                Arrays.sort(fa, (a, b) -> a.getName().compareTo(b.getName()));
//                for (Friend f : fa) {
//                    System.out.println(f.toString());
//                }
            } else if (choice.equals("reverse")) {
                Collections.sort(list, (a, b) -> b.getName().compareTo(a.getName()));
            } else if (choice.equals("rating")) {
                Collections.sort(list);
            } else if (choice.equals("search")) {
                ;
            } else if (choice.equals("range")) {
                ;
            }
        }
    }

    public void menu() {
        System.out.println("\nJohn's Black Book");
        System.out.println("Add - add a new friend");
        System.out.println("Remove - remove a friend");
        System.out.println("List - display list of friends");
        System.out.println("Name - display list of friends ordered by name");
        System.out.println("Reverse - display list of friends ordered by name descending");
        System.out.println("Rating - display list of friends ordered by rating");
        System.out.println("Search - search for a friend");
        System.out.println("Range - display friends in a range of ratings");
        System.out.println("Quit");
    }

    public static void main(String[] args) {
        BBApp app = new BBApp();
    }
}
