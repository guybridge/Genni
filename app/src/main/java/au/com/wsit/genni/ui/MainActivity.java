package au.com.wsit.genni.ui;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import au.com.wsit.genni.R;
import au.com.wsit.genni.adapter.LottoAdapter;
import au.com.wsit.genni.api.ListGenerator;
import au.com.wsit.genni.model.NumberRow;

public class MainActivity extends AppCompatActivity
{

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private LottoAdapter lottoAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListGenerator listGenerator;
    private int loopCount;
    private SharedPreferences sharedPreferences;
    private int gameCount;
    private int numberMax;
    private int shuffleCount;
    private int LOOP_SPEED = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        updateSettings();

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        recyclerView = (RecyclerView) findViewById(R.id.lottoRecyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        lottoAdapter = new LottoAdapter(this);
        recyclerView.setAdapter(lottoAdapter);

        listGenerator = new ListGenerator(numberMax, 6, gameCount);

        // Generate a list of numbers
        generate();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                loop();
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i(TAG, "onResume called");

       updateSettings();
        listGenerator = new ListGenerator(numberMax, 6, gameCount);

    }

    private void updateSettings()
    {
        gameCount = Integer.parseInt(sharedPreferences.getString("KEY_NUM_GAMES", "18"));
        numberMax = Integer.parseInt(sharedPreferences.getString("KEY_MAX_NUMBER", "45"));
        shuffleCount = Integer.parseInt(sharedPreferences.getString("KEY_SHUFFLE_COUNT", "10"));
    }

    private void generate()
    {
        listGenerator.generateNumbers(new ListGenerator.Callback()
        {
            @Override
            public void onResult(ArrayList<NumberRow> numberList)
            {
                Log.i(TAG, "Generated a game of " + numberList.size());

                lottoAdapter.swap(numberList);

                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFail(String errorMessage)
            {
                Log.i(TAG, errorMessage);
            }
        });
    }


    // Loop through the numbers to animate them
    private void loop()
    {
            // Do it a few times for looks
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    loopCount++;
                    if(loopCount <= shuffleCount)
                    {
                        loop();
                        generate();
                    }
                    else
                    {
                        handler.removeCallbacksAndMessages(this);
                        loopCount = 0;
                    }

                }
            }, LOOP_SPEED);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
