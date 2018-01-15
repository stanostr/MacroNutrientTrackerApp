package com.stanislavveliky.macrotracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by stan_ on 1/10/2018.
 */

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";
    private static final String KEY_DAILY_TOTAL = "daily_total";
    private DailyTotal mDailyTotal;
    private DailyTotalStack mDailyTotalStack;

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
        mDailyTotalStack = DailyTotalStack.get(getActivity());
        if(savedInstanceState!=null)
        {
            double [] values = savedInstanceState.getDoubleArray(KEY_DAILY_TOTAL);
            mDailyTotal = new DailyTotal(values[0], values[1], values[2], values[3]);
        }
        else mDailyTotal = new DailyTotal();
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
                mDailyTotalStack.push(new DailyTotal(mDailyTotal));
                mDailyTotal.addCalories(Double.valueOf(mCalorieField.getText().toString()));
                mDailyTotal.addFat(Double.valueOf(mFatField.getText().toString()));
                mDailyTotal.addCarbs(Double.valueOf(mCarbField.getText().toString()));
                mDailyTotal.addProtein(Double.valueOf(mProteinField.getText().toString()));
                updateTextViews();
                clearFields();
            }
        });
        mResetButton = (Button) v.findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDailyTotalStack.push(new DailyTotal(mDailyTotal));
                mDailyTotal.clear();
                clearFields();
                updateTextViews();
            }
        });
        mUndoButton = (Button) v.findViewById(R.id.undo_button);
        mUndoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mDailyTotalStack.canUndo())
                {
                    Toast.makeText(getActivity(), R.string.cant_undo, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mDailyTotal = mDailyTotalStack.undo();
                    updateTextViews();
                }
            }
        });
        mRedoButton = (Button) v.findViewById(R.id.redo_button);
        mRedoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mDailyTotalStack.canRedo())
                {
                    Toast.makeText(getActivity(), R.string.cant_redo, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mDailyTotal = mDailyTotalStack.redo();
                    updateTextViews();
                }
            }
        });
        updateTextViews();
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle onSavedInstanceState)
    {
        super.onSaveInstanceState(onSavedInstanceState);
        if(mDailyTotal!=null) {
            double[] values = {mDailyTotal.getCalories(), mDailyTotal.getFat(),
                    mDailyTotal.getCarbs(), mDailyTotal.getProtein()};
            onSavedInstanceState.putDoubleArray(KEY_DAILY_TOTAL, values);
        }
    }

    private void updateTextViews()
    {
        mCalTotalTextView.setText(getString(R.string.calories_label) + " " + mDailyTotal.getCalories());
        mFatTotalTextView.setText(getString(R.string.fat_label) + " " + mDailyTotal.getFat());
        mCarbTotalTextView.setText(getString(R.string.carbs_label) + " " + mDailyTotal.getCarbs());
        mProteinTotalTextView.setText(getString(R.string.protein_label) + " " + mDailyTotal.getProtein());
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