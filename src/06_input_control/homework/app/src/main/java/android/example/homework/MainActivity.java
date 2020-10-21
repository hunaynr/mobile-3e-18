package android.example.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox chocolate,sprinkles,nuts,cherries,orio;
    Button showToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButtonClick();
    }

    public void addListenerOnButtonClick(){
        //Getting instance of CheckBoxes and Button from the activty_main.xml file
        chocolate=(CheckBox)findViewById(R.id.checkBox);
        sprinkles=(CheckBox)findViewById(R.id.checkBox2);
        nuts=(CheckBox)findViewById(R.id.checkBox3);
        cherries=(CheckBox)findViewById(R.id.checkBox4);
        orio=(CheckBox)findViewById(R.id.checkBox5);
        showToast=(Button)findViewById(R.id.button);

        //Applying the Listener on the Button click
        showToast.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                StringBuilder result=new StringBuilder();
                result.append("Toppings: ");
                if(chocolate.isChecked()){
                    result.append("Chocolate syrup ");
                }
                if(sprinkles.isChecked()){
                    result.append("Sprinkles ");
                }
                if(nuts.isChecked()){
                    result.append("Crushed Nuts ");
                }
                if(cherries.isChecked()){
                    result.append("Cherries ");
                }
                if(orio.isChecked()){
                    result.append("Orio cookie crumble");
                }
                //Displaying the message on the toast
                Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
            }

        });
    }
}