package com.example.noob_coders;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class CounsellingFragment extends Fragment {

    // on below line we are creating variable
    // for our array list and swipe deck.
    private SwipeDeck cardStack;
    private ArrayList<CourseModal> courseModalArrayList;
    private ArrayList<String> fields;
    FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_counselling, container, false);

        db = FirebaseFirestore.getInstance();

        // on below line we are initializing our array list and swipe deck.
        courseModalArrayList = new ArrayList<>();
        fields = new ArrayList<>();
        cardStack = (SwipeDeck) rootView.findViewById(R.id.swipe_deck);

        // on below line we are adding data to our array list.
        courseModalArrayList.add(new CourseModal("Web Development", "30 days", "20 Tracks", "WebD Self Paced Course", R.mipmap.ic_launcher));
        courseModalArrayList.add(new CourseModal("Android Development", "30 days", "20 Tracks", "Android Self Paced Course", R.mipmap.ic_launcher));
        courseModalArrayList.add(new CourseModal("Machine Learning", "30 days", "20 Tracks", "Python Self Paced Course", R.mipmap.ic_launcher));
        courseModalArrayList.add(new CourseModal("InfoSec", "30 days", "20 Tracks", "InfoSec Self Paced Course", R.mipmap.ic_launcher));
        courseModalArrayList.add(new CourseModal("Finance", "30 days", "20 Tracks", "Finance Self Paced Course", R.mipmap.ic_launcher));

        // on below line we are creating a variable for our adapter class and passing array list to it.
        final DeckAdapter adapter = new DeckAdapter(courseModalArrayList, getActivity());

        // on below line we are setting adapter to our card stack.
        cardStack.setAdapter(adapter);

        // on below line we are setting event callback to our card stack.
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                // on card swipe left we are displaying a toast message.
                Toast.makeText(getActivity(), "Card Swiped Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardSwipedRight(int position) {
                // on card swipe right we are displaying a toast message and add coursename to array list.
                CourseModal currentCourseModel = (CourseModal) adapter.getItem(position);
                fields.add(currentCourseModel.getCourseName());
                Toast.makeText(getActivity(), "Card Swiped Right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardsDepleted() {
                // this method is called when no card is present
                Toast.makeText(getActivity(), "No more courses present", Toast.LENGTH_SHORT).show();
                Log.v("arrayCheck","fields: " + fields);
                updateData();
            }

            @Override
            public void cardActionDown() {
                // this method is called when card is swipped down.
                Log.i("TAG", "CARDS MOVED DOWN");
            }

            @Override
            public void cardActionUp() {
                // this method is called when card is moved up.
                Log.i("TAG", "CARDS MOVED UP");
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    private void updateData()
    {
        Map<String,Object> userFields = new HashMap<>();
        String insert_fields ="";
        for(int i =0;i<fields.size();i++)
        {
            insert_fields = insert_fields + fields.get(i)+", ";
        }
        userFields.put("Fields",insert_fields);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();

            db.collection("user").whereEqualTo("E-Mail",email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    if(task.isSuccessful()&& !task.getResult().isEmpty())
                    {
                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                        String documentID = documentSnapshot.getId();
                        db.collection("user").document(documentID).update(userFields).
                                addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        Toast.makeText(getActivity(),"Successfully Added User Fields", Toast.LENGTH_SHORT).show();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
                    }

                }
            });
        }
    }
}