package au.com.wsit.genni.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

import au.com.wsit.genni.R;
import au.com.wsit.genni.model.NumberRow;

/**
 * Created by guyb on 14/08/17.
 */

public class LottoAdapter extends RecyclerView.Adapter<LottoAdapter.ViewHolder>
{
    private static final String TAG = LottoAdapter.class.getSimpleName();
    private ArrayList<NumberRow> numberRows = new ArrayList<>();
    private Context context;

    public LottoAdapter(Context context)
    {
        this.context = context;
    }

    @Override
    public LottoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lotto_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void swap(ArrayList<NumberRow> numberRows)
    {
        if(numberRows != null)
        {
            this.numberRows = numberRows;
            notifyDataSetChanged();
        }
    }

    @Override
    public void onBindViewHolder(LottoAdapter.ViewHolder holder, int position)
    {
        holder.bindViewHolder(numberRows.get(position));
    }

    @Override
    public int getItemCount()
    {
        return numberRows.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView number1;
        private TextView number2;
        private TextView number3;
        private TextView number4;
        private TextView number5;
        private TextView number6;
        private TextView gameName;

        public ViewHolder(View itemView)
        {
            super(itemView);

            number1 = (TextView) itemView.findViewById(R.id.lottoNumber1);
            number2 = (TextView) itemView.findViewById(R.id.lottoNumber2);
            number3 = (TextView) itemView.findViewById(R.id.lottoNumber3);
            number4 = (TextView) itemView.findViewById(R.id.lottoNumber4);
            number5 = (TextView) itemView.findViewById(R.id.lottoNumber5);
            number6 = (TextView) itemView.findViewById(R.id.lottoNumber6);
            gameName = (TextView) itemView.findViewById(R.id.gameName);

        }

        private void bindViewHolder(NumberRow numberRow)
        {
            number1.setText(String.valueOf(numberRow.getNumbers().toArray()[0]));
            number2.setText(String.valueOf(numberRow.getNumbers().toArray()[1]));
            number3.setText(String.valueOf(numberRow.getNumbers().toArray()[2]));
            number4.setText(String.valueOf(numberRow.getNumbers().toArray()[3]));
            number5.setText(String.valueOf(numberRow.getNumbers().toArray()[4]));
            number6.setText(String.valueOf(numberRow.getNumbers().toArray()[5]));

            number1.setBackgroundResource(numberRow.getColour().get(0));
            number2.setBackgroundResource(numberRow.getColour().get(1));
            number3.setBackgroundResource(numberRow.getColour().get(2));
            number4.setBackgroundResource(numberRow.getColour().get(3));
            number5.setBackgroundResource(numberRow.getColour().get(4));
            number6.setBackgroundResource(numberRow.getColour().get(5));

            gameName.setText(numberRow.getGameName());

        }
    }
}
