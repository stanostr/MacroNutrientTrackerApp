package com.stanislavveliky.macrotracker;

import android.content.Context;

/**
 * Created by stan_ on 1/8/2018.
 * singleton to represent daily running totals
 */

public class DailyTotal {
    private static DailyTotal sDailyTotal;
    private double mCalories;
    private double mFat;
    private double mCarbs;
    private double mProtein;

    public static DailyTotal get(Context context)
    {
        if(sDailyTotal==null)
        {
            sDailyTotal = new DailyTotal(context);
        }
        return sDailyTotal;
    }

    private DailyTotal(Context context)
    {
        mCalories = 0;
        mFat = 0;
        mCarbs = 0;
        mProtein = 0;
    }

    public void addCalories(double amount)
    {
        mCalories += amount;
    }

    public void addFat(double amount)
    {
        mFat += amount;
    }

    public void addCarbs(double amount)
    {
        mCarbs += amount;
    }

    public void addProtein(double amount)
    {
        mProtein += amount;
    }

    public double getCalories() {
        return mCalories;
    }

    public void setCalories(double calories) {
        mCalories = calories;
    }

    public double getFat() {
        return mFat;
    }

    public void setFat(double fat) {
        mFat = fat;
    }

    public double getCarbs() {
        return mCarbs;
    }

    public void setCarbs(double carbs) {
        mCarbs = carbs;
    }

    public double getProtein() {
        return mProtein;
    }

    public void setProtein(double protein) {
        mProtein = protein;
    }

    public void clear()
    {
        mCalories =0;
        mFat = 0;
        mCarbs = 0;
        mProtein = 0;
    }

    private void saveState()
    {

    }

}
