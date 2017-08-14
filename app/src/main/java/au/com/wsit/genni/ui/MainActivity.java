package au.com.wsit.genni.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.lottoRecyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        lottoAdapter = new LottoAdapter();
        recyclerView.setAdapter(lottoAdapter);

        ListGenerator listGenerator = new ListGenerator(45, 6, 18);

        listGenerator.generateNumbers(new ListGenerator.Callback()
        {
            @Override
            public void onResult(ArrayList<NumberRow> numberList)
            {
                Log.i(TAG, "Generated a game of " + numberList.size());

                lottoAdapter.swap(numberList);
            }

            @Override
            public void onFail(String errorMessage)
            {
                Log.i(TAG, errorMessage);
            }
        });
    }
}
