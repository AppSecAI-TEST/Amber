package au.com.gravitywave.amber.entities;

import java.util.Calendar;

/**
 * Created by georg on 4/08/2017.
 */

public class Offer extends EntityBase {

    private int offerId;

    private int journeyId;
    private int offeringPersonId;
    private Calendar offerTime;
    private String message;
    private String status;

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(int journeyId) {
        this.journeyId = journeyId;
    }

    public int getOfferingPersonId() {
        return offeringPersonId;
    }

    public void setOfferingPersonId(int offeringPersonId) {
        this.offeringPersonId = offeringPersonId;
    }

    public Calendar getOfferTime() {
        return offerTime;
    }

    public void setOfferTime(Calendar offerTime) {
        this.offerTime = offerTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
