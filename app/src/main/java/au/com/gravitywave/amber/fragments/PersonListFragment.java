package au.com.gravitywave.amber.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import au.com.gravitywave.amber.R;
import au.com.gravitywave.amber.fragments.entities.PersonListContent;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PersonListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    List<PersonListContent.Person> people;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PersonListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PersonListFragment newInstance(int columnCount) {
        PersonListFragment fragment = new PersonListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        people = new ArrayList<>(40);

        people.add(new PersonListContent.Person("1", "Tobey", "Hannond"));
        people.add(new PersonListContent.Person("2", "Catie", "Druce"));
        people.add(new PersonListContent.Person("3", "Gussy", "Kingsbury"));
        people.add(new PersonListContent.Person("4", "Maury", "Sheavills"));
        people.add(new PersonListContent.Person("5", "Caritta", "Shoveller"));
        people.add(new PersonListContent.Person("6", "Alissa", "Felstead"));
        people.add(new PersonListContent.Person("7", "Adrian", "Omand"));
        people.add(new PersonListContent.Person("8", "Jedd", "Birrell"));
        people.add(new PersonListContent.Person("9", "Hurleigh", "Crown"));
        people.add(new PersonListContent.Person("10", "Buckie", "Kerrigan"));
        people.add(new PersonListContent.Person("11", "Shurlocke", "Gorick"));
        people.add(new PersonListContent.Person("12", "Adriaens", "Tombs"));
        people.add(new PersonListContent.Person("13", "Millisent", "McFeat"));
        people.add(new PersonListContent.Person("14", "Cecil", "Tirrell"));
        people.add(new PersonListContent.Person("15", "Tildi", "Kerswill"));
        people.add(new PersonListContent.Person("16", "Edita", "Piser"));
        people.add(new PersonListContent.Person("17", "Ezekiel", "Erbe"));
        people.add(new PersonListContent.Person("18", "Christoph", "Domenico"));
        people.add(new PersonListContent.Person("19", "Elysia", "Sherland"));
        people.add(new PersonListContent.Person("20", "Rozalie", "Ohms"));
        people.add(new PersonListContent.Person("21", "Antony", "McAughtrie"));
        people.add(new PersonListContent.Person("22", "Gabrielle", "Passey"));
        people.add(new PersonListContent.Person("23", "Xenia", "Laxton"));
        people.add(new PersonListContent.Person("24", "Abbot", "Olohan"));
        people.add(new PersonListContent.Person("25", "Karleen", "Letty"));
        people.add(new PersonListContent.Person("26", "Ivor", "Hasluck"));
        people.add(new PersonListContent.Person("27", "Cornie", "Punchard"));
        people.add(new PersonListContent.Person("28", "Andriana", "Bryenton"));
        people.add(new PersonListContent.Person("29", "Othilia", "Dabes"));
        people.add(new PersonListContent.Person("30", "Riki", "Harden"));
        people.add(new PersonListContent.Person("31", "Tripp", "Dumbare"));
        people.add(new PersonListContent.Person("32", "Rebekah", "Ivanyushin"));
        people.add(new PersonListContent.Person("33", "Lettie", "Sutlieff"));
        people.add(new PersonListContent.Person("34", "Giselle", "Freeborne"));
        people.add(new PersonListContent.Person("35", "Eddy", "Cottier"));
        people.add(new PersonListContent.Person("36", "Christian", "Williment"));
        people.add(new PersonListContent.Person("37", "Krishnah", "Olyet"));
        people.add(new PersonListContent.Person("38", "Janenna", "Swine"));
        people.add(new PersonListContent.Person("39", "Paton", "Fagence"));
        people.add(new PersonListContent.Person("40", "Hartwell", "Matthewson"));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
//            recyclerView.setAdapter(new PersonRecyclerViewAdapter(PersonListContent.ITEMS, mListener));
            recyclerView.setAdapter(new PersonRecyclerViewAdapter(people));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(PersonListContent.Person item);
    }
}
