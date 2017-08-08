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
import au.com.gravitywave.amber.activities.ShellActivity;
import au.com.gravitywave.amber.entities.Journey;
import au.com.gravitywave.amber.entities.Offer;
import au.com.gravitywave.amber.entities.Person;
import au.com.gravitywave.amber.fragments.RequestsFragment.OnListFragmentInteractionListener;

import static android.content.ContentValues.TAG;

/**
 * {@link RecyclerView.Adapter} that can display a {@link au.com.gravitywave.amber.entities.Journey} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class RequestsRecyclerViewAdapter
        extends RecyclerView.Adapter<RequestsRecyclerViewAdapter.ViewHolder>
        implements GoogleApiClient.OnConnectionFailedListener {

    private final List<Journey> mValues;
    private final OnListFragmentInteractionListener mListener;

    private Context mContext;
//    GoogleApiClient mGoogleApiClient;

    public RequestsRecyclerViewAdapter(List<Journey> journeys, OnListFragmentInteractionListener listener, Context context) {
        mValues = journeys;
        mListener = listener;
        mContext = context;

//        if (mGoogleApiClient == null) {
//            mGoogleApiClient = new GoogleApiClient
//                    .Builder(context)
//                    .addApi(Places.GEO_DATA_API)
//                    .addApi(Places.PLACE_DETECTION_API)
////                .enableAutoManage(context, this)
//                    .build();
//        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        Journey j = mValues.get(position);

        Person requestor = AmberApplication.personRepository.GetById(j.getRequestorId());

        List<Offer> myOffers = AmberApplication.offerRepository.getOffersFromPersonForJourney(AmberApplication.currentPerson.getPersonId(), j.getJourneyId());

        if (myOffers != null)
            holder.mOfferedTextView.setVisibility(View.VISIBLE);
        else
            holder.mOfferedTextView.setVisibility(View.GONE);

        holder.mFirstNameView.setText(requestor.getFirstName());
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        holder.mWhenView.setText(sdf.format(j.getStartTime().getTime()));

        holder.mMessageView.setText(j.getMessage());

        GoogleApiClient client = ((ShellActivity) mContext).getGoogleApiClient();

        Places.GeoDataApi.getPlaceById(client, j.getStartPlaceId())
                .setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(PlaceBuffer places) {
                        if (places.getStatus().isSuccess() && places.getCount() > 0) {
                            final Place place = places.get(0);
                            Log.i(TAG, "Place found: " + place.getName());
                            holder.mFromView.setText(place.getName());
                        } else {
                            Log.e(TAG, "Place not found");
                        }
                        places.release();
                    }
                });

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
        public final TextView mOfferedTextView;
        public final TextView mFirstNameView;
        public final TextView mFromView;
        public final TextView mWhenView;
        public final TextView mMessageView;

        public Journey mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mOfferedTextView = (TextView) view.findViewById(R.id.offered);
            mFirstNameView = (TextView) view.findViewById(R.id.first_name);
            mFromView = (TextView) view.findViewById(R.id.from);
            mWhenView = (TextView) view.findViewById(R.id.when);
            mMessageView = (TextView) view.findViewById(R.id.message);

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
            return super.toString() + " '" + mFromView.getText() + "'";
        }
    }
}
