package com.example.user.sqlitefirstex;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity  {
    SQLiteDatabase db;
    HelperDB hlp;
    EditText t1et1, t1et2, t2et1, t2et2, t2et3;
    String strID, strNAME, strCLASS, strTYPE, strSHIHVA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1et1=(EditText)findViewById(R.id.tav1et1);
        t1et2=(EditText)findViewById(R.id.tav1et2);
        t2et1=(EditText)findViewById(R.id.tav2et1);
        t2et2=(EditText)findViewById(R.id.tav2et2);
        t2et3=(EditText)findViewById(R.id.tav2et3);
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
        if (id==R.id.menudsh){
            Intent t=new Intent(this, secondActivity.class);
            startActivity(t);
        }
        return super.onOptionsItemSelected(item);
    }



    public void nameid(View view) {
        hlp = new HelperDB(this);
        db=hlp.getWritableDatabase();
        //strID=t1et2.getText().toString();
        //strNAME=t1et1.getText().toString();
        ContentValues  cv=new ContentValues();
        cv.put(Name.NAME,t1et1.getText().toString());
        cv.put(Name.ST_ID,t1et2.getText().toString());
        db.insert(Name.TABLE_NAME,null,cv);
        db.close();

    }

    public void classInfo(View view) {
        hlp = new HelperDB(this);
        db=hlp.getWritableDatabase();
        //strCLASS=t2et2.getText().toString();
        //strSHIHVA=t2et1.getText().toString();
       // strTYPE=t2et3.getText().toString();
        ContentValues  cv=new ContentValues();
        cv.put(Age.CLASS,t2et2.getText().toString());
        cv.put(Age.SHIHVA,t2et1.getText().toString());
        cv.put(Age.TYPE,t2et3.getText().toString());
        db.insert(Age.TABLE_AGE,null,cv);
        db.close();
    }
}
