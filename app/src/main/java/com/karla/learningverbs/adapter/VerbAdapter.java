package com.karla.learningverbs.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.karla.learningverbs.R;
import com.karla.learningverbs.model.Verb;

import java.util.List;

public class VerbAdapter extends RecyclerView.Adapter<VerbAdapter.ViewHolder> {

    private OnClicVerbListener listener;
    private List<Verb> resultsListVerbs;

    public VerbAdapter(List<Verb> resultsVerbs, OnClicVerbListener listener) {
        this.resultsListVerbs = resultsVerbs;
        this.listener = listener;

    }

    @NonNull
    @Override
    public VerbAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.verb_list_item, parent, false);

        ViewHolder vh = new ViewHolder(view);
        vh.setListener(this.listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VerbAdapter.ViewHolder holder, int position) {
        holder.txtNameVerb.setText(resultsListVerbs.get(position).getVerbSpanishPresent());

        Glide.with(holder.img_verb.getContext()).load(resultsListVerbs.get(position).getImage()).into(holder.img_verb);

        holder.setVerb(resultsListVerbs.get(position));

        if (resultsListVerbs.get(position).getRegular()) {
            holder.txtIsRegular.setText(R.string.msg_regular);
        } else {
            holder.txtIsRegular.setText(R.string.msg_irregular);
        }
    }

    @Override
    public int getItemCount() {
        return resultsListVerbs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private ImageView img_verb;
        private TextView txtNameVerb;
        private TextView txtIsRegular;
        private OnClicVerbListener listener;
        private Verb verb;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_verb = itemView.findViewById(R.id.imgVerb);
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
