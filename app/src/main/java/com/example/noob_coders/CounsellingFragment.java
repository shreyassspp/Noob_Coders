package com.example.noob_coders;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class CounsellingFragment extends Fragment {

    // on below line we are creating variable
    // for our array list and swipe deck.
    private SwipeDeck cardStack;
    private ArrayList<CourseModal> courseModalArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_counselling, container, false);

        // on below line we are initializing our array list and swipe deck.
        courseModalArrayList = new ArrayList<>();
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
                // on card swipe right we are displaying a toast message.
                Toast.makeText(getActivity(), "Card Swiped Right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardsDepleted() {
                // this method is called when no card is present
                Toast.makeText(getActivity(), "No more courses present", Toast.LENGTH_SHORT).show();
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
}