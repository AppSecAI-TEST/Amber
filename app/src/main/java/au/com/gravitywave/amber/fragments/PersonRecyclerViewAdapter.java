package au.com.gravitywave.amber.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import au.com.gravitywave.amber.R;
import au.com.gravitywave.amber.fragments.PersonListFragment.OnListFragmentInteractionListener;
import au.com.gravitywave.amber.fragments.entities.PersonListContent.Person;

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
//        holder.mIdView.setText(mPeople.get(position).id);
        holder.mFirstName.setText(mPeople.get(position).firstName);
        holder.mLastName.setText(mPeople.get(position).lastName);

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
        //        public final TextView mIdView;
        public final TextView mFirstName;
        public final TextView mLastName;
        public Person mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
//            mIdView = (TextView) view.findViewById(R.id.id);
            mFirstName = (TextView) view.findViewById(R.id.first_name);
            mLastName = (TextView) view.findViewById(R.id.last_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mFirstName.getText() + "'";
        }
    }
}
