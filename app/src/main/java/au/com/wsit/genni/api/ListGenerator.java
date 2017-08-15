package au.com.wsit.genni.api;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

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

    private ArrayList<Integer> generateRow()
    {
        //Log.i(TAG, "Generating number row");
        ArrayList<Integer> numberRow = new ArrayList<>();

        for (int i = 0; i < rowCount; i++)
        {
            // Add the random number to the row
            numberRow.add(getRandomNumber());
        }

        //Log.i(TAG, "Created number row: " + numberRow.toString());

        return numberRow;
    }

    private int getRandomNumber()
    {
        int number;
        Random random = new Random();
        number = random.nextInt(numberMax + 1);
        numbersList.add(number);

        // Check if the number is a duplicate
        for(int i = 0; i <= numbersList.size(); i++)
        {
            Log.i(TAG, "Checking if " + numbersList.get(i) + " matches " + number);
            if(number == numbersList.get(i))
            {
                Log.i(TAG, "The number already exists in this row, regenerating");
                getRandomNumber();
            }

            if(number == 0)
            {
                Log.i(TAG, "The number was 0");
                return number + 1;
            }
            else
            {
                return number;
            }
        }
        return number;
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
