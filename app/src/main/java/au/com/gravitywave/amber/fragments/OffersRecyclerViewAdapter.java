package au.com.gravitywave.amber.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

import java.text.SimpleDateFormat;
import java.util.List;

import au.com.gravitywave.amber.AmberApplication;
import au.com.gravitywave.amber.R;
import au.com.gravitywave.amber.entities.Offer;
import au.com.gravitywave.amber.entities.Person;
import au.com.gravitywave.amber.fragments.OffersFragment.OnListFragmentInteractionListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Offer} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class OffersRecyclerViewAdapter
        extends RecyclerView.Adapter<OffersRecyclerViewAdapter.ViewHolder>
        implements GoogleApiClient.OnConnectionFailedListener

{

    private final List<Offer> mValues;
    private final OnListFragmentInteractionListener mListener;
    GoogleApiClient mGoogleApiClient;

    public OffersRecyclerViewAdapter(List<Offer> items, OnListFragmentInteractionListener listener, Context context) {
        mValues = items;
        mListener = listener;


        mGoogleApiClient = new GoogleApiClient
                .Builder(context)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
//                .enableAutoManage(context, this)
                .build();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_offer, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final OffersRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        Offer offer = mValues.get(position);

        Person requestor = AmberApplication.personRepository.GetById(offer.getOfferingPersonId());
        //Person offerer = AmberApplication.personRepository.GetById(offer.getOfferingPersonId());

        holder.mOffererFirstNameTextView.setText(requestor.getFirstName());

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        holder.mOfferWhenTextView.setText(sdf.format(offer.getOfferTime().getTime()));

        holder.mOfferMessageTextView.setText(offer.getMessage());

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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mOffererFirstNameTextView;
        public final TextView mOfferWhenTextView;
        public final TextView mOfferMessageTextView;

        public Offer mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mOffererFirstNameTextView = (TextView) view.findViewById(R.id.first_name);
            mOfferWhenTextView = (TextView) view.findViewById(R.id.offer_when);
            mOfferMessageTextView = (TextView) view.findViewById(R.id.offer_message);

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        final int position = getAdapterPosition();
                        mListener.onListFragmentInteraction(mValues.get(position));
                    }
                }
            });

        }
    }
}
