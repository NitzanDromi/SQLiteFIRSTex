package com.example.user.sqlitefirstex;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class secondActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ListView lv;
    ArrayAdapter adp;
    Spinner spinner;
    String [] whichTable = {"name", "age"};
    ArrayList<String> data=new ArrayList<>();
    SQLiteDatabase db;
    HelperDB hlp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        hlp=new HelperDB(this);
        db=hlp.getWritableDatabase();
        spinner = findViewById(R.id.spin);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, whichTable);
        spinner.setAdapter(adp);
        spinner.setOnItemSelectedListener(this);
        lv= (ListView)findViewById(R.id.listvew);
        hlp= new HelperDB(this);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if (id==R.id.menucreds){
            Intent t=new Intent(this, CredsActivity.class);
            startActivity(t);
        }
        if (id==R.id.menuden){
            Intent t=new Intent(this, MainActivity.class);
            startActivity(t);
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void showData(int position) {
        db = hlp.getWritableDatabase();
            data.clear();
            Cursor c;
            if (position == 0) {
                c= db.query(Name.TABLE_NAME,null,null,null,null,null,null);
                int[] col = new int[3];
                col[0] = c.getColumnIndex("_id");
                col[1] = c.getColumnIndex("Name");
                col[2] = c.getColumnIndex("st_id");
                c.moveToFirst();

            while (!c.isAfterLast()) {
                String name = c.getString(col[1]);
                String st_id = c.getString(col[2]);
                String tmp = name + ", " + st_id;
                data.add(tmp);
                c.moveToNext();
                adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
                lv.setAdapter(adp);
            }
        }
        else
        {
            c = db.query(Age.TABLE_AGE, null, null, null, null, null, null);
            int[] col2 = new int[4];
            col2[0] = c.getColumnIndex("_idAge");
            col2[1] = c.getColumnIndex("Class");
            col2[2] = c.getColumnIndex("Shihva");
            col2[3] = c.getColumnIndex("Type");
            c.moveToFirst();

            while (!c.isAfterLast()) {
                String clas = c.getString(col2[1]);
                String shih = c.getString(col2[2]);
                String sug = c.getString(col2[3]);

                String tmp = shih + ", " + clas + ", " + sug;
                data.add(tmp);
                c.moveToNext();
                adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
                lv.setAdapter(adp);
            }
        }
             c.close();

        }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        showData(position);
    }

}
