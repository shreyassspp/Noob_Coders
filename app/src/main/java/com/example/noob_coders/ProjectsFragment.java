package com.example.noob_coders;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ProjectsFragment extends Fragment {

    private RecyclerView courseRV;

    // Arraylist for storing data
    private ArrayList<ProjectModal> projectModalArrayList;


    public ProjectsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_projects, container, false);

        courseRV = rootView.findViewById(R.id.idRVCourse);

        // here we have created new array list and added data to it.
        projectModalArrayList = new ArrayList<>();
        projectModalArrayList.add(new ProjectModal("WebDev Final Project", "Developing a web application with help of Hibernate. The journey is about how to create a backend web application.Customer Relationship Manager will keep track of all the customers.", R.mipmap.ic_launcher));
        projectModalArrayList.add(new ProjectModal("Android Dev Final Project", "The world is facing one of the worst epidemics, the outbreak of COVID-19. Let’s create a COVID-19 Tracker Android App using REST API which will track the Global Stats only.", R.mipmap.ic_launcher));
        projectModalArrayList.add(new ProjectModal("Machine Learning Final Project", "Using a Spotify song dataset and Spotipy, a Python client for Spotify, to build a content-based music recommendation system.", R.mipmap.ic_launcher));
        projectModalArrayList.add(new ProjectModal("Finance Final Project", "In this final project, you will use the moneybhai site to get hands on experience of the Stock Market. All the Best!!", R.mipmap.ic_launcher));
        projectModalArrayList.add(new ProjectModal("InfoSec Final Project", "This project uses the combined concept ofsignature byte code detection and pattern matching to detect the presence of any malware in the android device and helps getting rid of it.", R.mipmap.ic_launcher));

        // we are initializing our adapter class and passing our arraylist to it.
        ProjectAdapter courseAdapter = new ProjectAdapter(getActivity(), projectModalArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(courseAdapter);

        return rootView;
    }
}