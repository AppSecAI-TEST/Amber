package au.com.gravitywave.amber.repositories;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import au.com.gravitywave.amber.entities.Message;

/**
 * Created by v_bollg on 09/08/2017.
 */

public class MessageRepository {
    private static final MessageRepository ourInstance = new MessageRepository();

    private int nextMessageId = 1;

    private List<Message> messages;


    private MessageRepository() {
        messages = new ArrayList<>();

        messages.add(new Message(nextMessageId++, 1, 1, 2, "message from 1", Calendar.getInstance().getTime()));
        messages.add(new Message(nextMessageId++, 1, 1, 2, "message from 1", Calendar.getInstance().getTime()));
        messages.add(new Message(nextMessageId++, 1, 2, 1, "message from 2", Calendar.getInstance().getTime()));
        messages.add(new Message(nextMessageId++, 1, 1, 2, "message from 1", Calendar.getInstance().getTime()));
        messages.add(new Message(nextMessageId++, 1, 2, 1, "message from 2", Calendar.getInstance().getTime()));
    }

    public static MessageRepository getInstance() {
        return ourInstance;
    }

    public List<Message> getJourneyConversation(int journeyId) {
        List<Message> messages = new ArrayList<>();
        for (Message m : messages) {
            if (m.getmJourneyId() == journeyId) {
                messages.add(m);
            }
        }

        Collections.sort(messages, new Comparator<Message>() {
            @Override
            public int compare(Message lhs, Message rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getSendTime().compareTo(rhs.getSendTime());
            }
        });

        return messages;
    }

    public List<Message> getConversation(int person1, int person2) {
        List<Message> messages = new ArrayList<>();
        for (Message m: messages) {
            if ((m.getFromPersonId() == person1 && m.getToPersonId() == person2) ||
                    (m.getToPersonId() == person1 && m.getFromPersonId() == person2)) {
                messages.add(m);
            }
        }

        Collections.sort(messages, new Comparator<Message>() {
            @Override
            public int compare(Message lhs, Message rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getSendTime().compareTo(rhs.getSendTime());
            }
        });

        return messages;
    }
}
