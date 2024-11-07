package ro.pub.cs.systems.eim.Colocviu1_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Colocviu1_2SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Preluarea intenției și a datelor primite
        Intent intent = getIntent();
        String allTerms = intent.getStringExtra("ALL_TERMS");

        // Calculul sumei termenilor
        int sum = calculateSum(allTerms);

        // Returnarea rezultatului
        Intent resultIntent = new Intent();
        resultIntent.putExtra("SUM_RESULT", sum);
        setResult(Activity.RESULT_OK, resultIntent);
        finish(); // Închide activitatea după returnarea rezultatului
    }

    // Funcție pentru calcularea sumei termenilor
    private int calculateSum(String allTerms) {
        int sum = 0;

        // Împarte textul "All Terms" în termeni individuali pe baza separatorului "+"

            String[] terms = allTerms.split("\\+");


            for (String term : terms) {
                term = term.trim(); // Elimină spațiile suplimentare
                if (!term.isEmpty()) {
                    try {
                        sum += Integer.parseInt(term); // Adaugă termenul la sumă
                    } catch (NumberFormatException e) {
                        // Ignoră termenii care nu sunt numere
                    }
                }
            }

        return sum;
    }
}
