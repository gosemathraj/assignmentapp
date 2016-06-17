package com.gosemathraj.paritycubeapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gosemathraj.paritycubeapp.R;
import com.gosemathraj.paritycubeapp.model.Deals;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iamsparsh on 14/6/16.
 */
public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.CustomViewHolder> {

    private List<Deals> deals = new ArrayList<>();
    private Context context;

    public RecyclerviewAdapter(Context context,List<Deals> deals) {

        this.deals = deals;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        holder.title.setText(deals.get(position).getTitle());
        holder.price.setText("Rs " + deals.get(position).getCurrent_price());
        holder.original_price.setText("Rs " + deals.get(position).getOriginal_price());
        if(deals.get(position).getOff_percent().equals(""))
            holder.percent_off.setText("0% OFF");
        else
            holder.percent_off.setText(deals.get(position).getOff_percent()+"% OFF");

        holder.comments_count.setText(deals.get(position).getComments_count());
        holder.like_count.setText(deals.get(position).getLike_count());

        //image url is not returning anything so used fixed category url
        Picasso.with(context).load("http://cdn0.desidime.com/categories/12/thumb/19-Other.png?1456740336").into(holder.image);
    }

    @Override
    public int getItemCount() {
        return deals.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView title;
        private TextView price;
        private TextView original_price;
        private TextView percent_off;
        private TextView like_count;
        private TextView comments_count;

        public CustomViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.item_image);
            title = (TextView) itemView.findViewById(R.id.item_title);
            price = (TextView) itemView.findViewById(R.id.item_price);
            original_price = (TextView) itemView.findViewById(R.id.item_original_price);
            percent_off = (TextView) itemView.findViewById(R.id.item_off_percentage);
            like_count = (TextView) itemView.findViewById(R.id.like_count);
            comments_count = (TextView) itemView.findViewById(R.id.comment_count);

        }
    }
}
