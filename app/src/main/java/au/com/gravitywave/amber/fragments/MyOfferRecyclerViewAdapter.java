package au.com.gravitywave.amber.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;

import java.text.SimpleDateFormat;
import java.util.List;

import au.com.gravitywave.amber.AmberApplication;
import au.com.gravitywave.amber.R;
import au.com.gravitywave.amber.entities.Journey;
import au.com.gravitywave.amber.entities.Offer;
import au.com.gravitywave.amber.entities.Person;
import au.com.gravitywave.amber.fragments.MyOffersFragment.OnListFragmentInteractionListener;

import static android.content.ContentValues.TAG;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Offer} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyOfferRecyclerViewAdapter
        extends RecyclerView.Adapter<MyOfferRecyclerViewAdapter.ViewHolder>
        implements GoogleApiClient.OnConnectionFailedListener

{

    private final List<Offer> mValues;
    private final OnListFragmentInteractionListener mListener;
    GoogleApiClient mGoogleApiClient;

    public MyOfferRecyclerViewAdapter(List<Offer> items, OnListFragmentInteractionListener listener, Context context) {
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
    public void onBindViewHolder(final MyOfferRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        Offer offer = mValues.get(position);

        Journey j = AmberApplication.journeyRepository.getJourneyById(offer.getJourneyId());
        Person requestor = AmberApplication.personRepository.GetById(j.getRequestorId());
        //Person offerer = AmberApplication.personRepository.GetById(offer.getOfferingPersonId());

        holder.mRequesterFirstNameTextView.setText(requestor.getFirstName());

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        holder.mWhenTextView.setText(sdf.format(j.getStartTime().getTime()));
        holder.mOfferWhenTextView.setText(sdf.format(offer.getOfferTime().getTime()));

        holder.mMessageTextView.setText(offer.getMessage());

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


        Places.GeoDataApi.getPlaceById(mGoogleApiClient, j.getStartPlaceId())
                .setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(PlaceBuffer places) {
                        if (places.getStatus().isSuccess() && places.getCount() > 0) {
                            final Place place = places.get(0);
                            Log.i(TAG, "Place found: " + place.getName());
                            holder.mFromTextView.setText(place.getName());
                        } else {
                            Log.e(TAG, "Place not found");
                        }
                        places.release();
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
        public final TextView mRequesterFirstNameTextView;
        public final TextView mFromTextView;
        public final TextView mWhenTextView;
        public final TextView mMessageTextView;
        public final TextView mOfferWhenTextView;
        public final TextView mOfferMessageTextView;

        public Offer mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mRequesterFirstNameTextView = (TextView) view.findViewById(R.id.first_name);
            mFromTextView = (TextView) view.findViewById(R.id.from);
            mWhenTextView = (TextView) view.findViewById(R.id.when);
            mMessageTextView = (TextView) view.findViewById(R.id.message);
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

        @Override
        public String toString() {
            return super.toString() + " '" + mFromTextView.getText() + "'";
        }
    }
}
