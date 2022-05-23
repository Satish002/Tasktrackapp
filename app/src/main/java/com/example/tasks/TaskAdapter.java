package com.example.tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    ArrayList<String> Tasks;
    ArrayList<Integer> Current,Lower,Upper;
    Context context;
    public TaskAdapter(){

    }
    public TaskAdapter(Context ct , ArrayList<String>A,ArrayList<Integer>C,ArrayList<Integer>L,ArrayList<Integer>U){
        context=ct;
        Tasks=A;
        Current=C;
        Lower=L;
        Upper=U;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int i=position;
        final boolean[] change = {true};
        holder.Name.setText(Tasks.get(position));
        holder.Points.setText(Integer.toString(Current.get(position))+"/"+Integer.toString(Upper.get(position)));
        holder.progressBar.setProgress((Current.get(position)*100)/Upper.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(change[0]){

                holder.decrease.setVisibility(View.VISIBLE);
                holder.delete.setVisibility(View.VISIBLE);
                holder.increase.setVisibility(View.VISIBLE);
                change[0] =false;
                }
                else{

                    holder.decrease.setVisibility(View.GONE);
                    holder.delete.setVisibility(View.GONE);
                    holder.increase.setVisibility(View.GONE);
                    change[0] =true;

                }

            }
        });
        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Current.get(i)<Upper.get(i)){
                    Current.set(i,Current.get(i)+1);
                    notifyItemChanged(i);
                }
                //holder.Points.setText(Integer.toString(Current.get(position))+"/"+Integer.toString(Upper.get(position)));
            }
        });
        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Current.get(i)>Lower.get(i)) {
                    Current.set(i, Current.get(i) - 1);
                    notifyItemChanged(i);
                }
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tasks.remove(i);
                Current.remove(i);
                Lower.remove(i);
                Upper.remove(i);
                notifyDataSetChanged();
               // notifyItemRemoved();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView Name,Points;
        Button decrease,increase,delete;
        ProgressBar progressBar;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardview);
            progressBar=itemView.findViewById(R.id.progress);
            Name=itemView.findViewById(R.id.CourseName);
            Points=itemView.findViewById(R.id.coursePoints);
            decrease=itemView.findViewById(R.id.pointsDecrease);
            increase=itemView.findViewById(R.id.pointsIncrease);
            delete=itemView.findViewById(R.id.deleteCourse);
        }
    }
}
