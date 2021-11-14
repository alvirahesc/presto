package id.ac.pnj.uasalvitmj5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AboutMe extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mencoba membuat loop crash >:D
                int i = 5;
                do {
                    Toast.makeText(AboutMe.this, "Stucked.", Toast.LENGTH_SHORT).show(); //DO NOT PRESSSSSSS
                } while (i>1);
            }
        });
    }
}