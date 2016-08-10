package com.mayo.animations.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mayo.animations.R;

import java.util.ArrayList;

/**
 * Created by Mahayogi Lakshmipathi on 10/8/16.
 *
 * @author <a href="mailto:mygreymatter@gmail.com">Mahayogi Lakshmipathi</a>
 * @version 1.0
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private ArrayList<String> mNames;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.row_name,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(mNames.get(position));
    }

    @Override
    public int getItemCount() {
        return mNames != null ? mNames.size() : 0;
    }



    public void setNames(ArrayList<String> names){
        mNames = names;
        notifyDataSetChanged();
    }

    public void addName(int pos){
        notifyItemInserted(pos);
        notifyItemRangeChanged(pos,mNames.size());
    }

    public void remove(int i) {
        notifyItemRemoved(i);
        notifyItemRangeChanged(i,mNames.size());
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;

        public ViewHolder(View v) {
            super(v);

            name = (TextView) v.findViewById(R.id.name);
        }
    }
}
