package com.example.noob_coders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;


public class CommunityFragment extends Fragment {

    FirebaseFirestore db;


    public CommunityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);


        db = FirebaseFirestore.getInstance();


        fetchFields();


        return rootView;
    }

    private void fetchFields() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            db.collection("user")
                    .whereEqualTo("E-Mail", email)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()) {

                                Toast.makeText(getActivity(), "Successful", Toast.LENGTH_LONG).show();
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    String fields_chosen = document.get("Fields").toString();
                                    convertFieldsToList(fields_chosen);
                                    Log.d("TAG", document.getId() + " => " + document.getData());
                                }


                            } else {

                                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }
    }

    private void convertFieldsToList (String x)
    {
        String field ="";
        for(int i = 0;i<x.length();i++)
        {
            char c = x.charAt(i);
            if(c!=',')
            {
                field = field +c;
            }
            else
            {
                fetchUsers(field);
                field = "";
            }
        }


    }

    //Fetches the Email of Users for a Particular Field and stores them in an ArrayList.
    private void fetchUsers(String field)
    {
         Log.v("DataCheck",field);

         ArrayList<String> users_field = new ArrayList<>(); //To store the list of the users.

        db.collection(field)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull  Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                users_field.add(document.get("E-Mail").toString());
                            }
                            //Logging the Data to check if received properly.
                            Set<String> s = new LinkedHashSet<String>(users_field);

                            Log.v("ListOfUsers",  " The Field is "+field+" The List Of Users are " + s);
                            //Logging the Data to check if received properly.

                        }else {

                            Toast.makeText(getActivity(),"Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    }
