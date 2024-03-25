package com.example.barkia_firas_i1_mesure_glycemie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etValeur;
    private TextView tvAge, tvReponse;
    private SeekBar sbAge;
    private RadioButton rbIsFasting, rbIsNotFasting;
    private Button btnConsulter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Information", "onProgressChanged "+progress);
                tvAge.setText("Votre âge : "+ progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

        });

        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Information", "button cliqué");
                boolean verifAge = false, verifValeur = false;

                if(sbAge.getProgress()!=0)
                    verifAge = true;
                else
                    Toast.makeText(MainActivity.this, "Veuillez saisir votre age !", Toast.LENGTH_SHORT).show();

                if(!etValeur.getText().toString().isEmpty())
                    verifValeur = true;
                else
                    Toast.makeText(MainActivity.this, "Veuillez saisir votre valeur mesurée !", Toast.LENGTH_LONG).show();

                if(verifAge && verifValeur)
                {
                    calculer();
                }
            }
        });
    }

    public void calculer ()
    {
        int age = Integer.valueOf(sbAge.getProgress());
        float valeurMesuree = Float.valueOf(etValeur.getText().toString());
        boolean isFasting = rbIsFasting.isChecked();

        if(isFasting) {
            if (age >= 13) {
                if (valeurMesuree < 5.0)
                    tvReponse.setText("Niveau de glycémie est trop bas");
                else if (valeurMesuree >= 5.0 && valeurMesuree <= 7.2)
                    tvReponse.setText("Niveau de glycémie est normale");
                else
                    tvReponse.setText("Niveau de glycémie est trop élevé");
            } else if (age >= 6 && age <= 12) {
                if (valeurMesuree < 5.0)
                    tvReponse.setText("Niveau de glycémie est trop bas");
                else if (valeurMesuree >= 5.0 && valeurMesuree <= 10.0)
                    tvReponse.setText("Niveau de glycémie est normale");
                else
                    tvReponse.setText("Niveau de glycémie est trop élevé");
            } else if (age < 6) {
                if (valeurMesuree < 5.5)
                    tvReponse.setText("Niveau de glycémie est trop bas");
                else if (valeurMesuree >= 5.5 && valeurMesuree <= 10.0)
                    tvReponse.setText("Niveau de glycémie est normale");
                else
                    tvReponse.setText("Niveau de glycémie est trop élevé");
            }
        } else {
            if (valeurMesuree > 10.5)
                tvReponse.setText("Niveau de glycémie est trop élevé");
            else
                tvReponse.setText("Niveau de glycémie est normale");
        }
    }
    private void init()
    {
        sbAge = findViewById(R.id.sbAge);
        tvAge = findViewById(R.id.tvAge);
        etValeur = findViewById(R.id.etValeur);
        rbIsFasting = findViewById(R.id.rbtOui);
        rbIsNotFasting = findViewById(R.id.rbtNon);
        btnConsulter = findViewById(R.id.btnConsulter);
        tvReponse = findViewById(R.id.tvReponse);
    }
}
