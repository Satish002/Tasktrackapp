package com.example.tasks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

      ArrayList<String> Tasks=new ArrayList<>();
    ArrayList<Integer> Current=new ArrayList<>(),Lower=new ArrayList<>(),Upper=new ArrayList<>();

    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Tasks.add("C");
        Tasks.add("C++");
        Tasks.add("Java");
        Tasks.add("Python");
        Tasks.add("Kotlin");
        Tasks.add("C#");
        Tasks.add("JavaScript");
        Tasks.add("HTML");
        Tasks.add("C");
        Tasks.add("C++");
        Tasks.add("Java");
        Tasks.add("Python");
        Tasks.add("Kotlin");
        Tasks.add("C#");
        Tasks.add("JavaScript");
        Tasks.add("HTML");
        for(int i=0;i<=15;i++){
            Current.add(2);
            Lower.add(1);
            Upper.add(16);
        }*/
        add=findViewById(R.id.addtask);
        recyclerView=findViewById(R.id.Tasks_RecyclerView);
        Bundle bundle=this.getIntent().getExtras();

        try {


            Tasks.addAll(bundle.getStringArrayList("Tasks"));
            Current.addAll(bundle.getIntegerArrayList("Current"));
            Lower.addAll(bundle.getIntegerArrayList("Lower"));
            Upper.addAll(bundle.getIntegerArrayList("Upper"));
        }
        catch (Exception e){

        }

        TaskAdapter taskAdapter=new TaskAdapter(this,Tasks,Current,Lower,Upper);
        recyclerView.setAdapter(taskAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*if(intent.getStringExtra("TaskName")!=null&&intent.getStringExtra("TaskName")!=""){

            String S=""+taskAdapter.Upper.size()+" ";
            taskAdapter.Tasks.add(intent.getStringExtra("TaskName"));
            taskAdapter.Current.add(intent.getIntExtra("InitVal",1));
            taskAdapter.Lower.add(intent.getIntExtra("InitVal",1));
            taskAdapter.Upper.add(intent.getIntExtra("FinVal",1));

            Toast.makeText(this , S+taskAdapter.Upper.size(),Toast.LENGTH_SHORT).show();
            taskAdapter.notifyDataSetChanged();
        }*/


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,NewTask.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("Tasks",Tasks);
                bundle.putIntegerArrayList("Current",Current);
                bundle.putIntegerArrayList("Lower",Lower);
                bundle.putIntegerArrayList("Upper",Upper);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

    }
}