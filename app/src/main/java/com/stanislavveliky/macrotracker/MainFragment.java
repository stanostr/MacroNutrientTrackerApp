package com.stanislavveliky.macrotracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by stan_ on 1/10/2018.
 */

public class MainFragment extends Fragment {
    private DailyTotal mDailyTotal;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mDailyTotal = DailyTotal.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        return v;
    }

}
