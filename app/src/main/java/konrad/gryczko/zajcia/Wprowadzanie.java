package konrad.gryczko.zajcia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Wprowadzanie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final List<RadioGroup> radioGroupList = new ArrayList<RadioGroup>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wprowadzanie);
        Intent intent=getIntent();
        String ilosc=intent.getStringExtra("ilosc");
        final int iIlosc=Integer.parseInt(ilosc);
        TableLayout tableLayout=(TableLayout) findViewById(R.id.tableLayout);
        for(int i=0;i<iIlosc;i++){
            final TableRow tableRow=(TableRow) LayoutInflater.from(Wprowadzanie.this).inflate(R.layout.row_at,null);
            ((TextView) tableRow.findViewById(R.id.OCENA)).setText("ocena "+(i+1));
            RadioGroup radioGroup=(RadioGroup)tableRow.findViewById(R.id.GROUP);
            radioGroupList.add(radioGroup);

            tableLayout.addView(tableRow);
        }

        Button button=(Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double avg=0;
                for(int i=0;i<radioGroupList.size();i++){
                    RadioGroup radioGroup=radioGroupList.get(i);
                    RadioButton rb=findViewById(radioGroup.getCheckedRadioButtonId());
                    avg+=Integer.parseInt(rb.getText().toString());

                }
                avg/=iIlosc;
                Intent intent1=new Intent(Wprowadzanie.this,MainActivity.class);
                intent1.putExtra("avg",Double.toString(avg));
                Wprowadzanie.this.startActivity(intent1);
            }
        });

    }
}
