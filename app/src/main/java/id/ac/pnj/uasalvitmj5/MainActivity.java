package id.ac.pnj.uasalvitmj5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //referensi ke layout (button dll)
    Button btn_add, btn_viewAll,btn_aboutMe;
    EditText et_name, et_age;
    Switch sw_activeCustomer;
    ListView lv_customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        btn_aboutMe = findViewById(R.id.btn_aboutMe);
        sw_activeCustomer = findViewById(R.id.sw_active); //Id button ini di layout adalah sw_active, jadi seharusnya tetap bekerja
        lv_customerList = findViewById(R.id.lv_customerList);
        et_age = findViewById(R.id.et_age);
        et_name = findViewById(R.id.et_name);

        //listener
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CustomerModel customerModel;

                //mengetes (run),
                try {
                    customerModel = new CustomerModel(-1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), sw_activeCustomer.isChecked()); //id -1 itu sementara untuk default
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error memasukkan data baru", Toast.LENGTH_SHORT).show(); //customerModel.toString mengambil data customer
                    customerModel = new CustomerModel(-1, "Terjadi kesalahan", 0, false);
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this); //constructor DBH butuh param, kasih konteks sesuai di class DataBaseHelper

                boolean sukses = dataBaseHelper.addOne(customerModel);
                Toast.makeText(MainActivity.this, "Berhasil = " +sukses, Toast.LENGTH_SHORT).show();
            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                List<CustomerModel> everyone = dataBaseHelper.getEveryone();

                ArrayAdapter customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, everyone);
                lv_customerList.setAdapter(customerArrayAdapter);

               // Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();

            }


        });

        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                CustomerModel clickedCustomer = (CustomerModel) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedCustomer);
                Toast.makeText(MainActivity.this, clickedCustomer.toString()+" dihapus.", Toast.LENGTH_SHORT).show();
            }
        });

        btn_aboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AboutMe.class);
                startActivity(i);
            }
        });

    }
}