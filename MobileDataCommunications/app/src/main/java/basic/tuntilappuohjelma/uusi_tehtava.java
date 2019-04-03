package basic.tuntilappuohjelma;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link uusi_tehtava.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link uusi_tehtava#newInstance} factory method to
 * create an instance of this fragment.
 */
public class uusi_tehtava extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private Button tallennabtn,tyhjennabtn;
   private EditText alkuaika, loppuaika,tyonkuvaus;

    // TODO: Rename and change types of parameters


    private OnFragmentInteractionListener mListener;

    public uusi_tehtava() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment uusi_tehtava.
     */
    // TODO: Rename and change types and number of parameters
    public static uusi_tehtava newInstance(String param1, String param2) {
        uusi_tehtava fragment = new uusi_tehtava();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_uusi_tehtava,container,false);
        alkuaika = (EditText) view.findViewById(R.id.aloitus_edit);
        loppuaika = (EditText)view.findViewById(R.id.lopetus_edit);
        tyonkuvaus = (EditText)view.findViewById(R.id.tyon_kuvaus_edit);
        tyhjennabtn = (Button)view.findViewById(R.id.tyhjenna);
        tallennabtn = (Button)view.findViewById(R.id.tallenna_button);

        tyhjennabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alkuaika.setText("");
                loppuaika.setText("-");
                tyonkuvaus.setText("");
            }
        });

        tallennabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (alkuaika.getText().length() > 1) {
                    DataHandler.getInstance().getExportlist().add(new String[]{alkuaika.getText().toString(),loppuaika.getText().toString(),tyonkuvaus.getText().toString()});
                    DataHandler.getInstance().getTaskList().add(alkuaika.getText().toString()+","+ loppuaika.getText().toString() + "," + tyonkuvaus.getText().toString() );

                }
                alkuaika.setText("");
                loppuaika.setText("");
                tyonkuvaus.setText("");

            }
        });

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
/*
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
    */

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
