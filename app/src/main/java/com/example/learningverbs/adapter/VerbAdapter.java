package com.example.learningverbs.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningverbs.R;
import com.example.learningverbs.model.Verb;

import java.util.List;

public class VerbAdapter extends RecyclerView.Adapter<VerbAdapter.ViewHolder>  {

    private OnClicVerbListener listener;
    private List<Verb> resultsListVerbs;

    public VerbAdapter(List<Verb> resultsSuperHeros, OnClicVerbListener listener) {
        this.resultsListVerbs = resultsSuperHeros;
        this.listener = listener;

    }

    @NonNull
    @Override
    public VerbAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.verb_list_item, parent, false);

        ViewHolder vh = new ViewHolder(view);
        vh.setListener(this.listener);
        return vh;    }

    @Override
    public void onBindViewHolder(@NonNull VerbAdapter.ViewHolder holder, int position) {
        holder.txtNameVerb.setText(resultsListVerbs.get(position).getVerbSpanishPresent());
        holder.setVerb(resultsListVerbs.get(position));

        if (resultsListVerbs.get(position).getRegular()) {
            holder.txtIsRegular.setText("Regular");
        } else {
            holder.txtIsRegular.setText("Irregular");
        }
    }

    @Override
    public int getItemCount() {
        return resultsListVerbs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private TextView txtNameVerb;
        private TextView txtIsRegular;
        private OnClicVerbListener listener;
        private Verb verb;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            txtNameVerb = itemView.findViewById(R.id.txtVerbName);
            txtIsRegular = itemView.findViewById(R.id.txtIsRegular);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getListener().onVerbClicListener(getVerb());
                }
            });

        }

        public Verb getVerb() {
            return verb;
        }

        public void setVerb(Verb verb) {
            this.verb = verb;
        }

        public OnClicVerbListener getListener() {
            return listener;
        }

        public void setListener(OnClicVerbListener listener) {
            this.listener = listener;
        }
    }
}
