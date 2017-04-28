package ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;
import model.Friend;
import utility.Validator;

/**
 * A black book rating app. Now using Java API data structures. Now using a hash
 * map implementation.
 *
 * @author 55jphillip
 */
public class BBApp {

    Map<String, Friend> map;

    public BBApp() {
        this.map = new HashMap();

        map.put("Barb", new Friend("Barb", 9));
        map.put("Steve", new Friend("Steve", 5));
        map.put("Tom", new Friend("Tom", 7));
        map.put("Sue", new Friend("Sue", 3));
        map.put("Mark", new Friend("Mark", 9));
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
                map.put(name, new Friend(name, rating));
            } else if (choice.equals("remove")) {
                String name = Validator.getLine(sc, "Enter name to remove: ");
                map.remove(name);
            } else if (choice.equals("list")) {
                System.out.println(map);
            } else if (choice.equals("name")) {
                // TreeMap works well to sort by the key
                Map<String, Friend> m = new TreeMap(map);
                for (Friend friend : m.values()) {
                    System.out.println(friend.getName() + ", " + friend.getRating());
                }

            } else if (choice.equals("reverse")) {
                // Example of using the new Java 8 stream class to sort
                Map<String, Friend> rMap = map.entrySet().stream()
                        .sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
                rMap.forEach((key, value) -> System.out.println(key + " = " + value));

            } else if (choice.equals("rating")) {
                // Example of turing map into a list and then sorting the list
                List<Map.Entry<String, Friend>> rList = new ArrayList<>();
                rList.addAll(map.entrySet());
                Collections.sort(rList, (Entry<String, Friend> o1, Entry<String, Friend> o2) -> {
                    return Integer.compare(o1.getValue().getRating(), o2.getValue().getRating());
                });
                System.out.println(rList.toString());

            } else if (choice.equals("search")) {
                // very fast hash search O(1)
                String name = Validator.getLine(sc, "Who would you like to find: ");
                if (map.containsKey(name)) {
                    System.out.println(map.get(name));
                } else {
                    System.out.println(name + " not found!");
                }
            } else if (choice.equals("range")) {
//                int low = Validator.getInt(sc, "Enter low end rating: ");
//                int high = Validator.getInt(sc, "Enter high end rating: ");
//                for (Friend f : map) {
//                    if (f.getRating() >= low && f.getRating() <= high) {
//                        System.out.println(f.toString());
//                    }
//                }
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