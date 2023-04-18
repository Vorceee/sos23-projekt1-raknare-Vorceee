package fi.arcada.sos22_1_raknare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // deklarera variabler
    TextView outputText;
    EditText inputText, inputValue;
    RecyclerView recyclerView;
    fi.arcada.sos22_1_raknare.CustomAdapter adapter;

    ArrayList<DataItem> dataItems = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dataItems = Statistics.getSampleData();

        // Vi skapar en koppling mellan vår Java-kod och vår XML-layout
        outputText = findViewById(R.id.outputText);
        inputText = findViewById(R.id.dataName);
        inputValue = findViewById(R.id.dataValue);
        recyclerView = findViewById(R.id.dataItemsRecyclerView);

        adapter = new CustomAdapter(this, dataItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void calculate(View view) {

        // Avbryt metoden om man försöker mata in ett tomt värde
        if (TextUtils.isEmpty(inputValue.getText())) return;

        // Lägg till ett nytt dataobjekt enligt användarens inmatning
        dataItems.add(new DataItem(
                inputText.getText().toString(),
                Double.parseDouble(inputValue.getText().toString())
        ));

        // Uppdatera recyclerView:en
        adapter.notifyDataSetChanged();

        // Skapa values-arraylist med endast värden
        ArrayList<Double> values = new ArrayList<>();
        for (DataItem item: dataItems) {
            values.add(item.getValue());
        }

        // Kolla att det finns tillräckligt med värden att räkna ut
        if (values.size() < 2) {
            outputText.setText("Mera data behövs...");
            return;
        }

        outputText.setText(String.format("Min: %.2f\nMax: %.2f\nMedelvärde: %.2f\nMedian: %.2f\nStandardavvikelse: %.2f\nTypvärde: %.2f\nLQ: %.2f\nUQ: %.2f\nQR: %.2f",
                Statistics.min(values),
                Statistics.max(values),
                Statistics.calcMean(values),
                Statistics.calcMedian(values),
                Statistics.calcSD(values),
                Statistics.calcMode(values),
                Statistics.calcLQ(values),
                Statistics.calcUQ(values),
                Statistics.calcQR(values)
        ));
    }
}