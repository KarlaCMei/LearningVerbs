package com.karla.learningverbs.kotlin.view.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karla.learningverbs.R
import com.karla.learningverbs.kotlin.listeners.OnClicVerbListener
import com.karla.learningverbs.model.Verb

class VerbAdapter(private val resultsListVerbs: List<Verb?>,private val listener: OnClicVerbListener): RecyclerView.Adapter<VerbAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerbAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.verb_list_item, parent, false)
        val vh: VerbAdapter.ViewHolder = VerbAdapter.ViewHolder(view)
        vh.listener = listener
        return vh
    }

    override fun onBindViewHolder(holder: VerbAdapter.ViewHolder, position: Int) {

        val verb = resultsListVerbs[position]
        verb?.let {verbSelected ->
            holder.txtNameVerb.text = verbSelected.verbSpanishPresent
            Glide.with(holder.imgVerb.context).load(verbSelected.image)
                .into(holder.imgVerb)
            if (verbSelected.regular) {
                holder.txtIsRegular.setText(R.string.msg_regular)
            } else {
                holder.txtIsRegular.setText(R.string.msg_irregular)
            }
        }


    }

    override fun getItemCount(): Int {
        return resultsListVerbs!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         private val cardView: CardView
         val imgVerb: ImageView
         val txtNameVerb: TextView
         val txtIsRegular: TextView
        var listener: OnClicVerbListener? = null
        var verb: Verb? = null

        init {
            imgVerb = itemView.findViewById(R.id.imgVerb)
            cardView = itemView.findViewById(R.id.cardview)
            txtNameVerb = itemView.findViewById(R.id.txtVerbName)
            txtIsRegular = itemView.findViewById(R.id.txtIsRegular)
            cardView.setOnClickListener { listener?.onVerbClicListener(verb) }
        }
    }

}