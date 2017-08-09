package au.com.gravitywave.amber.entities;

import java.util.Date;

/**
 * Created by v_bollg on 09/08/2017.
 */

public class Message extends EntityBase {


    private int mJourneyId;
    private int mMessageId;
    private int mFromPersonId;
    private int mToPersonId;
    private Date mSendTime;

    private String mMessage;

    public Message(int mMessageId, int journeyId, int mFromPersonId, int mToPersonId, String message, Date mSendTime) {
        this.mMessageId = mMessageId;
        this.mJourneyId = journeyId;
        this.mFromPersonId = mFromPersonId;
        this.mToPersonId = mToPersonId;
        this.mMessage = message;
        this.mSendTime = mSendTime;
    }

    public int getMessageId() {
        return mMessageId;
    }

    public void setMessageId(int messageId) {
        this.mMessageId = messageId;
    }

    public int getmJourneyId() {
        return mJourneyId;
    }

    public void setmJourneyId(int mJourneyId) {
        this.mJourneyId = mJourneyId;
    }

    public int getFromPersonId() {
        return mFromPersonId;
    }

    public void setFromPersonId(int fromPersonId) {
        this.mFromPersonId = fromPersonId;
    }

    public int getToPersonId() {
        return mToPersonId;
    }

    public void setToPersonId(int mToPersonId) {
        this.mToPersonId = mToPersonId;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public Date getSendTime() {
        return mSendTime;
    }

    public void setSendTime(Date sendTime) {
        this.mSendTime = sendTime;
    }
}
