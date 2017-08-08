package au.com.gravitywave.amber.entities;

import java.util.Calendar;

/**
 * Created by georg on 4/08/2017.
 */

public class Journey extends EntityBase {

    private int journeyId;

    private int requestorId;

    private String startPlaceId;

    private Calendar startTime;

    private String destinationPlaceId;

    private String message;

    private int walkBuddyId;

    private String journeyStatus;

    public Journey(int journeyId, int requestorId, String startPlaceId, Calendar startTime, String destinationPlaceId, String message, int walkBuddyId, String journeyStatus) {
        this.journeyId = journeyId;
        this.requestorId = requestorId;
        this.startPlaceId = startPlaceId;
        this.startTime = startTime;
        this.destinationPlaceId = destinationPlaceId;
        this.message = message;
        this.walkBuddyId = walkBuddyId;
        this.journeyStatus = journeyStatus;
    }

    public int getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(int journeyId) {
        this.journeyId = journeyId;
    }

    public int getRequestorId() {
        return requestorId;
    }

    public void setRequestorId(int requestorId) {
        this.requestorId = requestorId;
    }

    public String getStartPlaceId() {
        return startPlaceId;
    }

    public void setStartPlaceId(String startPlaceId) {
        this.startPlaceId = startPlaceId;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public String getDestinationPlaceId() {
        return destinationPlaceId;
    }

    public void setDestinationPlaceId(String destinationPlaceId) {
        this.destinationPlaceId = destinationPlaceId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getWalkBuddyId() {
        return walkBuddyId;
    }

    public void setWalkBuddyId(int walkBuddyId) {
        this.walkBuddyId = walkBuddyId;
    }


    public String getJourneyStatus() {
        return journeyStatus;
    }

    public void setJourneyStatus(String journeyStatus) {
        this.journeyStatus = journeyStatus;
    }
}
