package com.nareshgediya.selectrecycler;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {



    public interface itemClick{
        void onitemListner(int position, View view);
        void onitemLongListner (int position, View view);
    }
    ArrayList<String> list;
    MainActivity mainActivity;

    public MyAdapter(ArrayList<String> list,MainActivity mainActivity) {
        this.list = list;
        this.mainActivity = mainActivity;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
      MyViewHolder myViewHolder = new MyViewHolder(view,mainActivity);

      return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textView.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "You just Clicked " +list.get(position), Toast.LENGTH_SHORT).show();
//                list.remove(position);
//                notifyDataSetChanged();
            }
        });

        if (!mainActivity.iscontexualMode){
            holder.checkBox.setVisibility(View.GONE);
        }
        else {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(false);
        }

//        holder.checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (holder.checkBox.isChecked()){
//                    holder.itemView.setBackgroundColor(mainActivity.getColor(R.color.lightBlue));
//                }else {
//                    holder.itemView.setBackgroundColor(mainActivity.getColor(R.color.white));
//                }
//
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        CheckBox checkBox;
        View view2;
        public MyViewHolder(@NonNull View itemView, MainActivity mainActivity) {
            super(itemView);
            textView = itemView.findViewById(R.id.text1);
            checkBox = itemView.findViewById(R.id.checkBox1);
            view2 = itemView;
            view2.setOnLongClickListener(mainActivity);
            checkBox.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mainActivity.makeSelectionList(view,getAdapterPosition());

            if (checkBox.isChecked()){
                view2.setBackgroundColor(mainActivity.getColor(R.color.lightBlue));
            }
            else {
                view2.setBackgroundColor(mainActivity.getColor(R.color.white));
            }


        }
    }




    public void RemoveItems(ArrayList<String> selectedList) {

        for (int i = 0;i<selectedList.size();i++){

            list.remove(selectedList.get(i));
            notifyDataSetChanged();

        }
    }

}
