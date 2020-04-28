package com.example.ejerciciouno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public int candidato1 = 0, candidato2 = 0, candidato3 = 0;
    private EditText etEdad;
    private RadioButton rdSelected;
    private TextView tvMensaje, tvMensaje2;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Votar(View view) {
        etEdad = findViewById(R.id.etEdad);
        tvMensaje = findViewById(R.id.tvMensaj);
        radioGroup = findViewById(R.id.radioGroup);
        //Try para evitar que la aplicación reviente por errores no controlados
        try {
            //Validacion de que el la edad tenga algo
            if (!etEdad.getText().toString().isEmpty()) {
                //Validación de que sea mayor de edad
                if (Integer.parseInt(etEdad.getText().toString()) >= 18) {
                    //Para no tener que hacer mil condicionales encontré esta propiedad del radioGroup
                    rdSelected = findViewById(radioGroup.getCheckedRadioButtonId());
                    //Muestra por el candidato que ha votado
                    tvMensaje.setText("Has votado por " + rdSelected.getText().toString());
                    MostrarGanador(rdSelected);
                } else {
                    tvMensaje.setText("No tiene la edad suficiente!");
                }
            } else {
                tvMensaje.setText("Por favor ingrese la edad ");
            }

        } catch (Exception e) {
            tvMensaje.setText("Ha ocurrido un error " + e.toString());
        }
    }

    public void MostrarGanador(RadioButton rdSelected) {
        try {
            //Instancia el mensaje 2
            tvMensaje2 = findViewById(R.id.tvMensaj2);
            //Instancia los contadores de votos para candidatos
            int cero = 0;
            //Segun el texto del radiobutton que entra por parametro suma para cada candidato
            switch (rdSelected.getText().toString()) {
                case "Candidato 1":
                    candidato1++;
                    break;
                case "Candidato 2":
                    candidato2++;
                    break;
                case "Candidato 3":
                    candidato3++;
                    break;
                default:
                    tvMensaje2.setText("No hay suficientes votos");
                    break;
            }
            //Se genera un arreglo con los votos de los candidatos para saber cual es el que mas votos tiene a to_do momento

            int[] listaNumeros = {cero, candidato1, candidato2, candidato3};

            int iNumeroMayor, iPosicion;
            iNumeroMayor = listaNumeros[0];
            iPosicion = 0;
            for (int i = 0; i < listaNumeros.length; i++) {
                if (listaNumeros[i] > iNumeroMayor) {
                    iNumeroMayor = listaNumeros[i];
                    iPosicion = i;
                }
            }
            // una vez se conoce cual es el candidato con mas votos se procede a mostrar al usuario
            switch (iPosicion) {
                case 1:
                    tvMensaje2.setText("Va ganando: Candidato 1 con esta cantidad de votos:" + listaNumeros[iPosicion]);
                    break;
                case 2:
                    tvMensaje2.setText("Va ganando: Candidato 2 con esta cantidad de votos:" + listaNumeros[iPosicion]);
                    break;
                case 3:
                    tvMensaje2.setText("Va ganando: Candidato 3 con esta cantidad de votos:" + listaNumeros[iPosicion]);
                    break;
                default:
                    tvMensaje2.setText("No hay suficientes votos");
                    break;
            }
        } catch (Exception e) {
            tvMensaje.setText("Ha ocurrido un error " + e.toString());
        }
    }
}