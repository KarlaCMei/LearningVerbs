package com.example.learningverbs.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningverbs.R;
import com.example.learningverbs.model.Verb;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class VerbAdapter extends FirestoreRecyclerAdapter<Verb, VerbAdapter.ViewHolder> {

    public VerbAdapter(@NonNull FirestoreRecyclerOptions<Verb> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull VerbAdapter.ViewHolder holder, int position, @NonNull Verb model) {

        holder.txtNameVerb.setText(model.getVerboEspañol());

        if(model.getEsRegular()){
            holder.txtIsRegular.setText("Regular");
        }else{
            holder.txtIsRegular.setText("Irregular");
        }
    }

    @NonNull
    @Override
    public VerbAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.verb_list_item,parent,false);

        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNameVerb;
        private TextView txtIsRegular;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNameVerb = itemView.findViewById(R.id.txtVerbName);
            txtIsRegular = itemView.findViewById(R.id.txtIsRegular);

        }
    }

}
