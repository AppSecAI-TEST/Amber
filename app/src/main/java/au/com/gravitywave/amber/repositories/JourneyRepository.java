package au.com.gravitywave.amber.repositories;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import au.com.gravitywave.amber.entities.Journey;

/**
 * Created by georg on 5/08/2017.
 */

public class JourneyRepository extends RepositoryBase {

    private static final List<Journey> journeys = new ArrayList<>();
    private static int nextJourneyId = 1;

    static {
        journeys.add(new Journey(nextJourneyId++, 1, "ChIJRxccGxu5EmsRnqGNPHcI7zc", Calendar.getInstance(), "ChIJm9GTcxq5EmsR4aLUocq1I5k", "females only please", 0, "R"));
        journeys.add(new Journey(nextJourneyId++, 1, "ChIJewHfqXDHEmsR2ZP_GqYHNWw", Calendar.getInstance(), "ChIJm9GTcxq5EmsR4aLUocq1I5k", null, 0, "R"));
        journeys.add(new Journey(nextJourneyId++, 1, "ChIJM2OL2xq5EmsRQWnQBiqkiBM", Calendar.getInstance(), "ChIJm9GTcxq5EmsR4aLUocq1I5k", null, 0, "R"));
    }

    public List<Journey> getCurrentRequests() {
        List<Journey> list = new ArrayList<>();
        for (Journey j : journeys) {
            if (j.getJourneyStatus() == "R") {
                list.add(j);
            }
        }

        return list;
    }

    public Journey getJourneyById(int journeyId) {
        for (Journey j : journeys) {
            if (j.getJourneyId() == journeyId) {
                return j;
            }
        }
        return null;
    }
}
