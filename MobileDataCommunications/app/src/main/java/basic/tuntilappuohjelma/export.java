package basic.tuntilappuohjelma;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link export.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link export#newInstance} factory method to
 * create an instance of this fragment.
 */
public class export extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters


    private OnFragmentInteractionListener mListener;

    public export() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment export.
     */
    // TODO: Rename and change types and number of parameters
    public static export newInstance(String param1, String param2) {
        export fragment = new export();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_export,container,false);
        Button createcsv = (Button)view.findViewById(R.id.createcsvbtn);
        Button emailbtn = (Button)view.findViewById(R.id.emailbtn);
        final String csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyCsvFileTehtavat.csv");
        createcsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (DataHandler.getInstance().getTaskList() != null) {
                    int length = DataHandler.getInstance().getTaskList().size();

                    CSVWriter writer = null;
                    try {
                        writer = new CSVWriter(new FileWriter(csv));


                        List<String[]> data = DataHandler.getInstance().getExportlist();
                        writer.writeAll(data); // data is adding to csv

                        writer.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        });

        emailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"email@example.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject here");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "body text");

                File file = new File(csv);
                Uri uri = Uri.fromFile(file);
                emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(emailIntent, "Pick an Email provider"));
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

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

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
