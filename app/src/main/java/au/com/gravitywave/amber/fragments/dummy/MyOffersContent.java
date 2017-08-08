//package au.com.gravitywave.amber.fragments.dummy;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import au.com.gravitywave.amber.AmberApplication;
//import au.com.gravitywave.amber.entities.Offer;
//
///**
// * Helper class for providing sample content for user interfaces created by
// * Android template wizards.
// * <p>
// * TODO: Replace all uses of this class before publishing your app.
// */
//public class MyOffersContent {
//
//    /**
//     * An array of sample (dummy) items.
//     */
//    public static final List<Offer> ITEMS = new ArrayList<>();
//
//    /**
//     * A map of sample (dummy) items, by ID.
//     */
//    public static final Map<String, Offer> ITEM_MAP = new HashMap<>();
//
//
//    static {
//        // Add some sample items.
//        for (Offer j: AmberApplication.offerRepository.getOffersFromPerson(AmberApplication.currentPerson.id)) {
//            addItem(new MyOfferViewModel(j));
//        }
//    }
//
//    private static void addItem(MyOfferViewModel item) {
//        ITEMS.add(item);
//        ITEM_MAP.put(item.offerId, item);
//    }
//
//
//}
