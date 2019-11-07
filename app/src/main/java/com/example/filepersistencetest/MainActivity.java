package com.example.filepersistencetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText edit1,edit2,edit3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        edit1=(EditText)findViewById( R.id.edit1 );
        /*edit2=(EditText)findViewById( R.id.edit2 );
        edit3=(EditText)findViewById( R.id.edit3 );*/
        String inputText1 = load();
       /* String inputText2 = load();
        String inputText3 = load();*/
    if(!TextUtils.isEmpty( inputText1)){
        edit1.setText( inputText1 );
        edit1.setSelection( inputText1.length() );
        Toast.makeText(this,"Restoring succeed",Toast.LENGTH_SHORT).show();
    }
       /*if(!TextUtils.isEmpty( inputText2)){
            edit2.setText( inputText2 );
            edit2.setSelection( inputText2.length() );
            Toast.makeText(this,"Restoring succeed",Toast.LENGTH_SHORT).show();
        }
        if(!TextUtils.isEmpty( inputText3)){
            edit3.setText( inputText3 );
            edit3.setSelection( inputText3.length() );
            Toast.makeText(this,"Restoring succeed",Toast.LENGTH_SHORT).show();
        }*/
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        String inputText1 = edit1.getText().toString();
        String inputText2 = edit2.getText().toString();
        String inputText3 = edit3.getText().toString();
        save(inputText1,inputText2,inputText3);

    }
    public void save(String inputText1,String inputText2,String inputText3){
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput( "data", Context.MODE_PRIVATE );
            writer = new BufferedWriter( new OutputStreamWriter( out ) );
            writer.write( inputText1);
            /*writer.write( inputText2);
            writer.write( inputText3);*/
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(writer!=null){
                    writer.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    public String load(){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder(  );
        try{
            in = openFileInput( "data" );
            reader = new BufferedReader( new InputStreamReader( in ) );
            String line = "";
            while((line = reader.readLine())!=null){
                content.append( line );
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader!=null){
                try{
                    reader.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }


}
