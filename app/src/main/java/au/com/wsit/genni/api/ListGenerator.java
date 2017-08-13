package au.com.wsit.genni.api;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import au.com.wsit.genni.model.NumberRow;


/**
 * Created by guyb on 13/08/17.
 */

public class ListGenerator
{
    private static final String TAG = ListGenerator.class.getSimpleName();
    private Integer rowCount;
    private Integer listCount;
    private Integer numberMax;

    public ListGenerator(Integer numberMax, Integer rowCount, Integer listCount)
    {
        this.numberMax = numberMax;
        this.rowCount = rowCount;
        this.listCount = listCount;
    }

    public interface Callback
    {
        void onResult(ArrayList<NumberRow> numberList);
        void onFail(String errorMessage);
    }

    public void generateNumbers(Callback callback)
    {
        Log.i(TAG, "Creating number list with size of " + listCount);

        ArrayList<NumberRow> numberList = new ArrayList<>();

        // Loop through the list
        for (int i = 0; i < listCount; i++)
        {
            NumberRow numberRow = new NumberRow();
            numberRow.setNumbers(generateRow());
            numberList.add(numberRow);
        }

        callback.onResult(numberList);
    }

    private ArrayList<Integer> generateRow()
    {
        Log.i(TAG, "Generating number row");
        ArrayList<Integer> numberRow = new ArrayList<>();

        for (int i = 0; i < rowCount; i++)
        {
            // Add the random number to the row
            numberRow.add(getRandomNumber());
        }

        Log.i(TAG, "Created number row: " + numberRow.toString());

        return numberRow;
    }

    private int getRandomNumber()
    {
        Random random = new Random();
        return random.nextInt(numberMax);
    }
}
