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

    ArrayList<String> nm=new ArrayList<>();
    ArrayList<String> ag=new ArrayList<>();
    SQLiteDatabase db;
    HelperDB hlp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        hlp=new HelperDB(this);
        db=hlp.getWritableDatabase();
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Cursor c= db.query(Name.TABLE_NAME,null,null,null,null,null,null);
        int col1id=c.getColumnIndex("_id");
        int colname=c.getColumnIndex("Name");
        int colstid=c.getColumnIndex("st_id");
        c.moveToFirst();
        while (!c.isAfterLast()){
            String name=c.getString(colname);
            String student=c.getString(colstid);
            String temp=name+","+student;
            nm.add(temp);
            c.moveToNext();
        }
        c.close();
        adp=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,nm);
        lv.setAdapter(adp);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Cursor c= db.query(Age.TABLE_AGE,null,null,null,null,null,null);
        int col2id=c.getColumnIndex("_id");
        int colclass=c.getColumnIndex("Class");
        int colshih=c.getColumnIndex("Shihva");
        int coltype=c.getColumnIndex("Type");
        c.moveToFirst();
        while (!c.isAfterLast()){
            String  clas=c.getString(colclass);
            int shih=c.getInt(colshih);
            String type=c.getString(coltype);
            String temp=type+","+shih+","+clas;
            ag.add(temp);
            c.moveToNext();
        }
        c.close();
        adp=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,ag);
        lv.setAdapter(adp);

    }
}
