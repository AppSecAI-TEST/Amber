package au.com.gravitywave.amber.repositories;

import java.util.ArrayList;
import java.util.List;

import au.com.gravitywave.amber.entities.Offer;

/**
 * Created by georg on 6/08/2017.
 */

public class OfferRepository extends RepositoryBase {
    private static final List<Offer> offers = new ArrayList<>();
    private static int nextOfferId = 1;

    static {

    }

    public List<Offer> getOffersForJourney(int journeyId) {
        List<Offer> list = new ArrayList<>();
        for (Offer offer : offers) {
            if (offer.getStatus() == "o" && offer.getJourneyId() == journeyId) {
                list.add(offer);
            }
        }

        return list;
    }

    public List<Offer> getOffersFromPerson(int personId) {
        List<Offer> list = new ArrayList<>();
        for (Offer offer : offers) {
            if (offer.getStatus() == "o" && offer.getOfferingPersonId() == personId) {
                list.add(offer);
            }
        }

        return list;
    }

    public List<Offer> getOffersFromPersonForJourney(int personId, int journeyId) {
        List<Offer> list = new ArrayList<>();
        for (Offer offer : offers) {
            if (offer.getStatus() == "o" && offer.getOfferingPersonId() == personId && offer.getJourneyId() == journeyId) {
                list.add(offer);
            }
        }

        return list;
    }


    public Offer getOfferById(int offerId) {
        for (Offer j : offers) {
            if (j.getOfferId() == offerId) {
                return j;
            }
        }
        return null;
    }

    public int Insert(Offer offer) {
        offer.setOfferId(nextOfferId);
        offers.add(offer);
        nextOfferId++;

        return offer.getOfferId();
    }

}
