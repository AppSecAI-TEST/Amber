//package au.com.gravitywave.amber.fragments.entities;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * Helper class for providing sample firstName for user interfaces created by
// * Android template wizards.
// * <p>
// * TODO: Replace all uses of this class before publishing your app.
// */
//public class PersonListContent {
//
//    /**
//     * An array of sample (dummy) items.
//     */
//    public static final List<Person> ITEMS = new ArrayList<Person>();
//
//    /**
//     * A map of sample (dummy) items, by ID.
//     */
//    public static final HashMap<Integer, Person> ITEM_MAP = new HashMap<>();
//
//    private static final int COUNT = 25;
//
//    static {
//        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createPerson(i));
//        }
//    }
//
//    private static void addItem(Person item) {
//        ITEMS.add(item);
//        ITEM_MAP.put(item.id, item);
//    }
//
//    private static Person createPerson(int position) {
//        return new Person(position, "first " + position, "last " + position);
//    }
//
//    /**
//     * A dummy item representing a piece of firstName.
//     */
//    public static class Person {
//        public final int id;
//        public final String firstName;
//        public final String lastName;
//
//        public Person(int id, String firstName, String lastName) {
//            this.id = id;
//            this.firstName = firstName;
//            this.lastName = lastName;
//        }
//
//        @Override
//        public String toString() {
//            return firstName + " " + lastName;
//        }
//    }
//}
