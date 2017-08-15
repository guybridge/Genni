package au.com.wsit.genni.model;

import java.util.ArrayList;

/**
 * Created by guyb on 13/08/17.
 */

public class NumberRow
{
    private ArrayList<Integer> numbers;
    private ArrayList<Integer> colour;
    private String gameName;

    public String getGameName()
    {
        return gameName;
    }

    public void setGameName(String gameName)
    {
        this.gameName = gameName;
    }

    public ArrayList<Integer> getColour()
    {
        return colour;
    }

    public void setColour(ArrayList<Integer> colour)
    {
        this.colour = colour;
    }

    public ArrayList<Integer> getNumbers()
    {
        return numbers;
    }

    public void setNumbers(ArrayList<Integer> numbers)
    {
        this.numbers = numbers;
    }
}
