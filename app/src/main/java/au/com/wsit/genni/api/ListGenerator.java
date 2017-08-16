package au.com.wsit.genni.api;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import au.com.wsit.genni.R;
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
    public ArrayList<Integer> numbersList;

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
        // Wipe the numbers array list
        // Used to detect duplicates
        numbersList = new ArrayList<>();

        Log.i(TAG, "Creating number list with size of " + listCount);
        int gameCount = 1;

        // Create an array of number rows to store each row
        ArrayList<NumberRow> numberList = new ArrayList<>();

        // Loop through the list count
        for (int i = 0; i < listCount; i++)
        {
            // Create a number row object
            NumberRow numberRow = new NumberRow();

            // Set the colour of the number
            numberRow.setColour(getRandomColour());

            // Set the number row ArrayList into the numberRow
            numberRow.setNumbers(generateRow());
            // Add the numberRow to the list

            numberRow.setGameName("Game " + gameCount);
            gameCount++;

            numberList.add(numberRow);
        }

        callback.onResult(numberList);
    }

    // Gets a row of numbers
    private Set<Integer> generateRow()
    {
        Random random = new Random();
        Set<Integer> generated = new LinkedHashSet<>();

        while(generated.size() < 6)
        {
            Integer next = random.nextInt(numberMax - 1) + 1;
            generated.add(next);
        }

        return generated;
    }

    private ArrayList<Integer> getRandomColour()
    {
        ArrayList<Integer> coloursList = new ArrayList<>();

        Random random = new Random();
        int colours[] = {
                R.drawable.yellow_circle,
                R.drawable.blue_circle,
                R.drawable.cyan_circle,
                R.drawable.green_circle,
                R.drawable.grey_circle,
                R.drawable.purple_circle,
                R.drawable.red_circle,
                R.drawable.teal_circle,
                R.drawable.orage_circle,
                R.drawable.pink_circle};

        for(int i = 0; i < rowCount; i++)
        {
            int index = random.nextInt(colours.length);
            coloursList.add(colours[index]);
        }

        return coloursList;

    }
}
