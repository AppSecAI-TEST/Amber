package au.com.gravitywave.amber.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import au.com.gravitywave.amber.R;
import au.com.gravitywave.amber.fragments.entities.PersonListContent;

import static android.app.Activity.RESULT_OK;

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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static Criteria criteria;

//    ServerMockService serverMockService;
//    boolean isBound = false;
Button drawerToggleButton;

    DrawerLayout drawer;

MapView mMapView;
    TextView timeTextView;
    TextView mFromEditText;
    TextView mToEditText;
    int FROM_PLACE_PICKER_REQUEST = 1 ;
    int TO_PLACE_PICKER_REQUEST = 2 ;
    ArrayList<Marker> mAllMarkers;
    LocationListener locationListener;
    LocationManager locationManager;
    private GoogleMap mMap;
    private View mRootView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public MonitorFragment() {
        // Required empty public constructor
        mAllMarkers = new ArrayList<>();

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MonitorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MonitorFragment newInstance(String param1, String param2) {
        MonitorFragment fragment = new MonitorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);


//        Intent i = new Intent(getContext(), ServerMockService.class);
//        getActivity().bindService(i, serviceConnection, Context.BIND_AUTO_CREATE);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_monitor, container, false);

        drawerToggleButton = (Button) mRootView.findViewById(R.id.buttonToggleDrawer);
        drawer = (DrawerLayout) container.getRootView().findViewById(R.id.drawer_layout);


        drawerToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        handleFromClick(mRootView);
        handleToClick(mRootView);


        return mRootView;
    }



    private void handleFromClick(View view) {
        mFromEditText = (EditText)view.findViewById(R.id.from_location);
        mFromEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                Intent intent = null;
                try {
                    intent = builder.build(getActivity());
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                startActivityForResult(intent, FROM_PLACE_PICKER_REQUEST);

            }
        });
    }

    private void handleToClick(View view) {
        mToEditText = (EditText)view.findViewById(R.id.to_location);
        mToEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                Intent intent = null;
                try {
                    intent = builder.build(getActivity());
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                startActivityForResult(intent, TO_PLACE_PICKER_REQUEST);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FROM_PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(getContext(),data);
                String address = String.format("from: %s, ", place.getName());

                addMarker(place,"from");
                mFromEditText.setText(address);
            }
        }
        if (requestCode == TO_PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(getContext(),data);
                String address = String.format("to: %s, ", place.getName());

                addMarker(place,"to");
                mToEditText.setText(address);
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
//        PersonListFragment personListFragment = new PersonListFragment();
//        fragmentTransaction.replace(R.id.bottom_sheet, personListFragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();

        mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        mMapView.getMapAsync(this);//when you already implement OnMapReadyCallback in your fragment


        //timeTextView = (TextView) view.findViewById(R.id.currentTime);
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

    private void addMarker(Place place, String name){
        Marker marker = mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(name));
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
    public void onListFragmentInteraction(PersonListContent.Person item) {

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


//    private ServiceConnection serviceConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            ServerMockService.ServiceMockBinder binder = (ServerMockService.ServiceMockBinder)service;
//            serverMockService = binder.getService();
//            isBound = true;
//            showTime();
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            isBound = false;
//        }
//    };

//    public void showTime() {
//        String currentTIme = serverMockService.getCurrentTime();
//
//
//        timeTextView.setText(currentTIme);
//    }

}
