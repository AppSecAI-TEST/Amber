package au.com.gravitywave.amber.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import au.com.gravitywave.amber.AmberApplication;
import au.com.gravitywave.amber.R;
import au.com.gravitywave.amber.entities.Journey;
import au.com.gravitywave.amber.entities.Offer;
import au.com.gravitywave.amber.entities.Person;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MonitorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MonitorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonitorFragment extends Fragment
        implements OnMapReadyCallback,
        PersonListFragment.OnListFragmentInteractionListener

{
    public static Criteria criteria;

//    ServerMockService serverMockService;
//    boolean isBound = false;


//    @BindView(R.id.buttonToggleDrawer)
//    Button drawerToggleButton;
//
//    @BindView(R.id.drawer_layout)
//    DrawerLayout drawer;

    MapView mMapView;


    List<Journey> mRequests;

    ArrayList<Marker> mAllMarkers;
    LocationListener locationListener;
    LocationManager locationManager;
    GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    private View mRootView;
    private OnFragmentInteractionListener mListener;


    public MonitorFragment() {
        // Required empty public constructor
        mAllMarkers = new ArrayList<>();

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MonitorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MonitorFragment newInstance() {
        MonitorFragment fragment = new MonitorFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        mGoogleApiClient = new GoogleApiClient
                .Builder(getActivity())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_monitor, container, false);

        ButterKnife.bind(getActivity());


        ShowRequests();


//        drawerToggleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawer.openDrawer(Gravity.LEFT);
//            }
//        });


        return mRootView;
    }

    private void ShowRequests() {

        mRequests = AmberApplication.journeyRepository.getCurrentRequests();

        //loop through journeys
        //add (green) marker for each
        for (final Journey j : mRequests) {
            Places.GeoDataApi.getPlaceById(mGoogleApiClient, j.getStartPlaceId())
                    .setResultCallback(new ResultCallback<PlaceBuffer>() {
                        @Override
                        public void onResult(PlaceBuffer places) {
                            if (places.getStatus().isSuccess() && places.getCount() > 0) {
                                final Place place = places.get(0);
                                Log.i(TAG, "Place found: " + place.getName());

                                Offer offer = AmberApplication.offerRepository.getOfferById(AmberApplication.currentPerson.getPersonId());

                                String status = offer == null ? "request" : "offered";

                                if (offer != null)

                                    MonitorFragment.this.addMarker(
                                            place,
                                            AmberApplication.personRepository.GetById(j.getRequestorId()).getFirstName(),
                                            status);


                            } else {
                                Log.e(TAG, "Place not found");
                            }
                            places.release();
                        }
                    });
        }

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        mMapView.getMapAsync(this);//offerTime you already implement OnMapReadyCallback in your fragment


        //timeTextView = (TextView) view.findViewById(R.offerId.currentTime);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        addCurrentLocationMarker(googleMap);
    }

    private void addMarker(Place place, String name, String type) {


        MarkerOptions options;

        switch (type) {
            case "offered":
                options = new MarkerOptions()
                        .position(place.getLatLng())
                        .title(name)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                break;
            case "request":
                options = new MarkerOptions()
                        .position(place.getLatLng())
                        .title(name)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                break;
            default:
                options = new MarkerOptions()
                        .position(place.getLatLng())
                        .title(name);
                break;

        }

        Marker marker = mMap.addMarker(options);

        mAllMarkers.add(marker);
        ZoomAllMarkers();
    }

    private void addCurrentLocationMarker(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        // setup bestProvider
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        String bestProvider = locationManager.getBestProvider(criteria, true);

        double lo = locationManager.getLastKnownLocation(bestProvider).getLongitude();
        double la = locationManager.getLastKnownLocation(bestProvider).getLatitude();

        // Add a marker in Sydney and move the camera
        LatLng currentLatLng = new LatLng(la, lo);
        Marker marker = mMap.addMarker(new MarkerOptions().position(currentLatLng).title("Marker in Sydney"));
        mAllMarkers.add(marker);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18));

        if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        ZoomAllMarkers();
    }

    public void ZoomAllMarkers() {

        if (mAllMarkers.size() > 1) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (Marker marker : mAllMarkers) {
                builder.include(marker.getPosition());
            }
            LatLngBounds bounds = builder.build();

            int padding = 10; // offset from edges of the map in pixels
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);

            mMap.moveCamera(cu);

            mMap.animateCamera(cu);
        }
    }

    @Override
    public void onListFragmentInteraction(Person item) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
