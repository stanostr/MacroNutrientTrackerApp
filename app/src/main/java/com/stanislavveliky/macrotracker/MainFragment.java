package com.stanislavveliky.macrotracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by stan_ on 1/10/2018.
 */

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    private DailyTotal mDailyTotal;

    private EditText mCalorieField;
    private EditText mFatField;
    private EditText mCarbField;
    private EditText mProteinField;

    private Button mAddButton;
    private Button mResetButton;
    private Button mUndoButton;
    private Button mRedoButton;

    private TextView mCalTotalTextView;
    private TextView mFatTotalTextView;
    private TextView mCarbTotalTextView;
    private TextView mProteinTotalTextView;

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
        mCalorieField = (EditText) v.findViewById(R.id.calories_edit_text);
        mFatField = (EditText) v.findViewById(R.id.fat_edit_text);
        mCarbField = (EditText) v.findViewById(R.id.carbs_edit_text);
        mProteinField = (EditText) v.findViewById(R.id.protein_edit_text);

        mCalTotalTextView = (TextView) v.findViewById(R.id.calorie_total_label);
        mFatTotalTextView = (TextView) v.findViewById(R.id.fat_total_label);
        mCarbTotalTextView = (TextView) v.findViewById(R.id.carb_total_label);
        mProteinTotalTextView = (TextView) v.findViewById(R.id.protein_total_label);

        mAddButton = (Button) v.findViewById(R.id.add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fixBlankInput();
                mDailyTotal.addCalories(Integer.valueOf(mCalorieField.getText().toString()));
                mDailyTotal.addFat(Integer.valueOf(mFatField.getText().toString()));
                mDailyTotal.addCarbs(Integer.valueOf(mCarbField.getText().toString()));
                mDailyTotal.addProtein(Integer.valueOf(mProteinField.getText().toString()));
                mCalTotalTextView.setText(getString(R.string.calories_label) + " " + mDailyTotal.getCalories());
                mFatTotalTextView.setText(getString(R.string.fat_label) + " " + mDailyTotal.getFat());
                mCarbTotalTextView.setText(getString(R.string.carbs_label) + " " + mDailyTotal.getCarbs());
                mProteinTotalTextView.setText(getString(R.string.protein_label) + " " + mDailyTotal.getProtein());
                clearFields();
            }
        });
        mResetButton = (Button) v.findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        mUndoButton = (Button) v.findViewById(R.id.undo_button);
        mUndoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mRedoButton = (Button) v.findViewById(R.id.redo_button);
        mRedoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        reset();
        return v;
    }

    private void reset()
    {
        mDailyTotal.clear();
        clearFields();
        mCalTotalTextView.setText(getString(R.string.calories_label) + " 0");
        mFatTotalTextView.setText(getString(R.string.fat_label) + " 0");
        mCarbTotalTextView.setText(getString(R.string.carbs_label) + " 0");
        mProteinTotalTextView.setText(getString(R.string.protein_label) + " 0");
    }

    private void clearFields()
    {
        mCalorieField.setText("", TextView.BufferType.EDITABLE);
        mFatField.setText("", TextView.BufferType.EDITABLE);
        mCarbField.setText("", TextView.BufferType.EDITABLE);
        mProteinField.setText("", TextView.BufferType.EDITABLE);
    }

    private void fixBlankInput()
    {
        if(mCalorieField.getText().toString().equals("")) mCalorieField.setText("0");
        if(mFatField.getText().toString().equals("")) mFatField.setText("0");
        if(mCarbField.getText().toString().equals("")) mCarbField.setText("0");
        if(mProteinField.getText().toString().equals("")) mProteinField.setText("0");
    }


}
