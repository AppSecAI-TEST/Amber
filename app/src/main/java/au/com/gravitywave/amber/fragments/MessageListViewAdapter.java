package au.com.gravitywave.amber.fragments;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import au.com.gravitywave.amber.R;
import au.com.gravitywave.amber.entities.Message;

/**
 * Created by georg on 9/08/2017.
 */

public class MessageListViewAdapter extends ArrayAdapter<Message> {
    Context mContext;
    int mResource;
    int mPersonId;
    ArrayList<Message> mMessages;

    public MessageListViewAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<Message> messages, int personId) {
        super(context, resource);

        mContext = context;
        mResource = resource;
        mMessages = messages;
        mPersonId = personId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        if (convertView == null)
            if (getItemViewType(position) == 1)
                convertView = LayoutInflater.from(mContext).inflate(R.layout.message_left_row, parent, false);
            else
                convertView = LayoutInflater.from(mContext).inflate(R.layout.message_right_row, parent, false);

        Message message = mMessages.get(position);

        TextView mMesssageTextView = (TextView) convertView.findViewById(R.id.message);
        mMesssageTextView.setText(message.getMessage());

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        TextView mWhenTextView = (TextView) convertView.findViewById(R.id.when);
        mWhenTextView.setText(sdf.format(message.getSendTime()));

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (mMessages.get(position).getFromPersonId() == mPersonId)
            return 1;
        else
            return 2;
    }
}
