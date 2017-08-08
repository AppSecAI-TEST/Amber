package au.com.gravitywave.amber.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import au.com.gravitywave.amber.R;
import au.com.gravitywave.amber.entities.Person;
import au.com.gravitywave.amber.fragments.PersonListFragment.OnListFragmentInteractionListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Person} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PersonRecyclerViewAdapter extends RecyclerView.Adapter<PersonRecyclerViewAdapter.ViewHolder> {

    private final List<Person> mPeople;
    //private final OnListFragmentInteractionListener mListener;

    //    public PersonRecyclerViewAdapter(List<Person> items, OnListFragmentInteractionListener listener) {
    public PersonRecyclerViewAdapter(List<Person> items) {
        mPeople = items;
        //mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mPeople.get(position);
//        holder.mOffererFirstNameTextView.setText(mPeople.get(position).offerId);
        holder.mFirstName.setText(mPeople.get(position).getFirstName());
        holder.mLastName.setText(mPeople.get(position).getLastName());

//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mPeople.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        //        public final TextView mOffererFirstNameTextView;
        public final TextView mFirstName;
        public final TextView mLastName;
        public Person mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
//            mOffererFirstNameTextView = (TextView) view.findViewById(R.offerId.offerId);
            mFirstName = (TextView) view.findViewById(R.id.first_name);
            mLastName = (TextView) view.findViewById(R.id.last_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mFirstName.getText() + "'";
        }
    }
}
