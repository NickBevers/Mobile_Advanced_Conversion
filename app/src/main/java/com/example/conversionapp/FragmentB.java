package com.example.conversionapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment {

    public interface FragmentBListener{
        void onInputBSend(String input);
    }

    private EditText et_fahrenheit;
    private FragmentBListener listener;

    public FragmentB() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_b, container, false);

        et_fahrenheit = v.findViewById(R.id.et_fahrenheit);

        v.findViewById(R.id.button_to_celsius).setOnClickListener(bv -> {
            String input = et_fahrenheit.getText().toString();

            //Stuur naar fragment A
            listener.onInputBSend(input);
        });


        return v;
    }

    //ontvangt data van buitenaf (bvb in fragment b wordt op knop gedrukt)
    public void updateFahrenheit(Double input){
        String newInput = input.toString();
        et_fahrenheit.setText(newInput);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentBListener){
            listener = (FragmentBListener)context;
        }
        else{
            throw new RuntimeException(
                    String.format("%s must implement FragmentListener", context.toString())
            );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}