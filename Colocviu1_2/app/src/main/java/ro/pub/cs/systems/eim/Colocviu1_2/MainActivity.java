package ro.pub.cs.systems.eim.Colocviu1_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNextTerm;
    private TextView textViewAllTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNextTerm = findViewById(R.id.editTextNextTerm);
        textViewAllTerms = findViewById(R.id.textViewAllTerms);
        textViewAllTerms.setText("");
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonCompute = findViewById(R.id.buttonCompute);




        // Setăm un listener pentru butonul "Add"
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Preluăm textul din EditText și verificăm dacă este un număr
                String nextTerm = editTextNextTerm.getText().toString();

                // Dacă textul introdus este un număr, îl adăugăm la All Terms
                if (isNumeric(nextTerm)) {
                    String allTerms = textViewAllTerms.getText().toString();

                    // Dacă nu este prima adăugare, adăugăm "+" între termeni
                    if (!allTerms.isEmpty()) {
                        textViewAllTerms.setText(allTerms + " + " + nextTerm);
                    } else {
                        textViewAllTerms.setText(nextTerm);
                    }
                }

                // Golește câmpul EditText după adăugare
                editTextNextTerm.setText("");
            }
        });

        buttonCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve All Terms text
                String allTerms = textViewAllTerms.getText().toString();

                // Pass All Terms to Colocviu1_2SecondActivity
                Intent intent = new Intent(MainActivity.this, Colocviu1_2SecondActivity.class);
                intent.putExtra("ALL_TERMS", allTerms); // Pass the All Terms
                startActivityForResult(intent, 1); // Start the activity for a result
            }
        });
    }

    // Metodă de verificare dacă textul este numeric
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str); // Încercăm să convertim textul la număr
            return true; // Este număr
        } catch (NumberFormatException e) {
            return false; // Nu este număr
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            // Obține rezultatul sumei
            int sumResult = data.getIntExtra("SUM_RESULT", 0);

            // Afișează rezultatul sau actualizează UI în funcție de sumResult
            Toast.makeText(this, "Suma este: " + sumResult, Toast.LENGTH_LONG).show();
        }
    }
}
