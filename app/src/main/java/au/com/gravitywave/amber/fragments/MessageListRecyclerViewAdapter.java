package au.com.gravitywave.amber.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import au.com.gravitywave.amber.R;
import au.com.gravitywave.amber.entities.Message;
import au.com.gravitywave.amber.fragments.MessagesFragment.OnListFragmentInteractionListener;
import au.com.gravitywave.amber.fragments.dummy.DummyContent.DummyItem;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MessageListRecyclerViewAdapter extends RecyclerView.Adapter<MessageListRecyclerViewAdapter.ViewHolder> {

    private static final int MESSAGE_FOR_PERSON = 1;
    private static final int MESSAGE_FOR_OTHER_PERSON = 2;

    private final List<Message> mValues;
    private final OnListFragmentInteractionListener mListener;

    private int mPersonID;

    public MessageListRecyclerViewAdapter(List<Message> items, OnListFragmentInteractionListener listener, int personID) {
        mValues = items;
        mListener = listener;
        mPersonID = personID;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        if (viewType == MESSAGE_FOR_PERSON) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_left_row, parent, false);
            return new ViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_right_row, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Message message = mValues.get(position);

        if (message.getFromPersonId() == mPersonID)
            return MESSAGE_FOR_PERSON;
        else
            return MESSAGE_FOR_OTHER_PERSON;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        View view = holder.mView;

        TextView mMessageTextView = (TextView) view.findViewById(R.id.message);
        mMessageTextView.setText(holder.mItem.getMessage());

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        TextView mWhenTextView = (TextView) view.findViewById(R.id.when);
        mWhenTextView.setText(sdf.format(holder.mItem.getSendTime()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mWhen;
        public final TextView mPersonName;
        public final TextView mMessage;
        public Message mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mWhen = (TextView) view.findViewById(R.id.when);
            mPersonName = (TextView) view.findViewById(R.id.who);
            mMessage = (TextView) view.findViewById(R.id.message);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mMessage.getText() + "'";
        }
    }
}
