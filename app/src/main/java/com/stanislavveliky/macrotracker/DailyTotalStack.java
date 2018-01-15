package com.stanislavveliky.macrotracker;

import android.content.Context;
import java.util.Stack;

/**
 * Singleton to represent two stacks of daily total information
 * undostack represents the stacks that can be returned to
 * redostack represents undone stacks that can be redone after an undo operation
 * @author stan_
 */
public class DailyTotalStack {
    private static final String TAG = "DailyTotalStack";
    private static DailyTotalStack sDailyTotalStack;
    private Stack <DailyTotal> undoStack;
    private Stack <DailyTotal> redoStack;

    public static DailyTotalStack get(Context context)
    {
        if(sDailyTotalStack ==null)
           sDailyTotalStack = new DailyTotalStack();
        return sDailyTotalStack;
    }

    private DailyTotalStack() {
        undoStack = new <DailyTotal>Stack();
        redoStack = new <DailyTotal>Stack();
    }

    /**
     * pushes a value to the top of the undo stack, clears redo stack because
     * there is no option to redo after a new change was made
     * @param value to be added to undo stack
     */
    public void push(DailyTotal value) {

        undoStack.push(value);
        redoStack.clear();
    }

    // returns whether or not an undo can be done
    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    /**
     * gets the top item off the undo stack, as well as adds it to the redo stack
     * @return the top item of the undo stack
     */
    public DailyTotal undo() {
        if (!canUndo()) {
            throw new IllegalStateException();
        }
        else {
            redoStack.push(new DailyTotal(undoStack.peek()));
            return undoStack.pop();
        }
    }

    // returns whether or not a redo can be done
    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    /**
     * returns top item from redo stack and also adds it to undo stack
     * @return top item from redo stack
     */
    public DailyTotal redo() {
        if (!canRedo()) {
            throw new IllegalStateException();
        }
        else {
            undoStack.push(new DailyTotal(redoStack.peek()));
            return redoStack.pop();
        }
    }
}