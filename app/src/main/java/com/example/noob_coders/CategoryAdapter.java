package com.example.noob_coders;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CategoryAdapter extends FragmentStateAdapter {

    public CategoryAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public Fragment createFragment(int position) {

        if (position==0){
            return new Counselling();
        }
        else if (position==1){
            return new Community();
        }
        else {
            return new Projects();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
