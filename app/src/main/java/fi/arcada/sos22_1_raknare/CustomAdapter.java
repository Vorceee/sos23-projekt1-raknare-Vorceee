package fi.arcada.sos22_1_raknare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    Context context;
    ArrayList<DataItem> dataItems;


    public CustomAdapter(Context context, ArrayList<DataItem> dataItems) {
        this.context = context;
        this.dataItems = dataItems;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {


        DataItem item = dataItems.get(position);
        holder.name.setText(item.getName());
        holder.value.setText(String.format("%.2f", item.getValue()));
    }

    @Override
    public int getItemCount() {

        return dataItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView value;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.dataItemName);
            value = itemView.findViewById(R.id.dataItemValue);
        }
    }
}
