package com.example.learningverbs.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningverbs.R;
import com.example.learningverbs.model.Verb;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class VerbAdapter extends FirestoreRecyclerAdapter<Verb, VerbAdapter.ViewHolder> {
    private OnClicVerbListener listener;


    public VerbAdapter(@NonNull FirestoreRecyclerOptions<Verb> options) {
        super(options);
    }

    public OnClicVerbListener getListener() {
        return listener;
    }

    public void setListener(OnClicVerbListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull VerbAdapter.ViewHolder holder, int position, @NonNull Verb model) {
        Log.e("miverbo","mi verbo"+model.toString());
        holder.txtNameVerb.setText(model.getVerboEspañol());

        if (model.getEsRegular()) {
            holder.txtIsRegular.setText("Regular");
        } else {
            holder.txtIsRegular.setText("Irregular");
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getListener() != null) {
                    getListener().onVerbClicListener(model);
                }
            }
        });
    }

    @NonNull
    @Override
    public VerbAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.verb_list_item, parent, false);


        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private TextView txtNameVerb;
        private TextView txtIsRegular;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            txtNameVerb = itemView.findViewById(R.id.txtVerbName);
            txtIsRegular = itemView.findViewById(R.id.txtIsRegular);


        }

    }
}
