package com.example.tasks;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class NewTask extends AppCompatActivity {

    EditText name,ivalue,fvalue;
    Button save;
    ArrayList<String> Tasks=new ArrayList<>();
    ArrayList<Integer> Current=new ArrayList<>(),Lower=new ArrayList<>(),Upper=new ArrayList<>();
    String Name="";
    int initialv=-1,finalv=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle=this.getIntent().getExtras();
        Tasks=bundle.getStringArrayList("Tasks");
        Current=bundle.getIntegerArrayList("Current");
        Lower=bundle.getIntegerArrayList("Lower");
        Upper=bundle.getIntegerArrayList("Upper");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        name=findViewById(R.id.taskname);
        ivalue=findViewById(R.id.ivalue);
        fvalue=findViewById(R.id.fvlaue);
        save=findViewById(R.id.savebtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name=name.getText().toString().trim();
                try {


                    initialv = Integer.parseInt(ivalue.getText().toString().trim());
                    finalv = Integer.parseInt(fvalue.getText().toString().trim());
                }
                catch (Exception e){

                }
                if(Name==null||Name.isEmpty()||ivalue.getText().toString().trim().isEmpty()||fvalue.getText().toString().trim().isEmpty()){
                    Toast.makeText(NewTask.this,"Please fill all the credentials ",Toast.LENGTH_SHORT).show();
                }
                else if(initialv<0||initialv>100){
                    Toast.makeText(NewTask.this,"Initial value must be in between 0 & 100 ",Toast.LENGTH_SHORT).show();
                }
                else if(Name==null||finalv<0||finalv>100){
                    Toast.makeText(NewTask.this,"Final value must be in between 0 & 100 ",Toast.LENGTH_SHORT).show();
                }
                else if(initialv>finalv){
                    Toast.makeText(NewTask.this,"Initial Value must be less than final value",Toast.LENGTH_SHORT).show();
                }
                else{
                    Tasks.add(Name);
                    Current.add(initialv);
                    Lower.add(initialv);
                    Upper.add(finalv);
                    Intent intent=new Intent(NewTask.this,MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("Tasks",Tasks);
                    bundle.putIntegerArrayList("Current",Current);
                    bundle.putIntegerArrayList("Lower",Lower);
                    bundle.putIntegerArrayList("Upper",Upper);
                    intent.putExtras(bundle);

                    startActivity(intent);

                }
            }
        });





    }
}