package konrad.gryczko.zajcia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button=(Button) findViewById(R.id.button);
        final EditText imie=(EditText) findViewById(R.id.Imie);
        final EditText nazwisko=(EditText) findViewById(R.id.Nazwisko);
        final EditText ilosc=(EditText) findViewById(R.id.Ilosc);
        final TextView avgEdit=(TextView) findViewById(R.id.AvgTextViev);
        final TextView avgText=(TextView) findViewById(R.id.avgTextViev);
        Intent intent=getIntent();


        avgEdit.setVisibility(View.INVISIBLE);
        avgText.setVisibility(View.INVISIBLE);
        String avg=intent.getStringExtra("avg");
        if(avg!=null){
            avgEdit.setVisibility(View.VISIBLE);
            avgText.setVisibility(View.VISIBLE);
            avgEdit.setText(avg);
        }
        button.setVisibility(View.INVISIBLE);

        final Intent target=new Intent(MainActivity.this,Wprowadzanie.class);

        TextWatcher textWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String sImie=imie.getText().toString();
                String sNazwisko=nazwisko.getText().toString();
                String sIlosc=ilosc.getText().toString();
                if(sImie.isEmpty()||sIlosc.isEmpty()||sNazwisko.isEmpty()){
                    button.setVisibility(View.INVISIBLE);
                }
                else
                    if(Integer.parseInt(sIlosc)>=5&&Integer.parseInt(sIlosc)<=15) button.setVisibility(View.VISIBLE);
                    else button.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        ilosc.addTextChangedListener(textWatcher);
        imie.addTextChangedListener(textWatcher);
        nazwisko.addTextChangedListener(textWatcher);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                target.putExtra("ilosc",ilosc.getText().toString());
                MainActivity.this.startActivity(target);
            }
        });
    }
}
