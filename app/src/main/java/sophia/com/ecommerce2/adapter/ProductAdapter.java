package sophia.com.ecommerce2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import sophia.com.ecommerce2.R;
import sophia.com.ecommerce2.model.Product;

/**
 * Created by archimede on 14/06/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private List<Product> productList;
    private Context context;
    private OnAdapterItemClickListener listener;

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
        this.listener = (OnAdapterItemClickListener)context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_row_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);
        vh.imageview = (ImageView) v.findViewById(R.id.imageview);
        vh.title = (TextView) v.findViewById(R.id.title);
        vh.subtitle = (TextView) v.findViewById(R.id.subtitle);
        vh.button = (Button) v.findViewById(R.id.button);
        vh.button2 = (Button) v.findViewById(R.id.button2);
        vh.price = (TextView) v.findViewById(R.id.price);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OnClickListner", v.getTag().toString());

                if(listener != null) {
                    listener.OnItemClick((int) v.getTag()) ;
                }

            }
        });


        vh.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                    listener.OnItemAddToCart((int)v.getTag());
            }

        });

        vh.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                    listener.OnItemAddToFavorites((int)v.getTag());
            }
        });

        return vh;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(productList.get(position).getImagePath()).into(holder.imageview);
        holder.title.setText(productList.get(position).getName());
        holder.subtitle.setText(productList.get(position).getDescription());
        holder.price.setText(productList.get(position).getPrice());
        holder.itemView.setTag(position);
        holder.button.setTag(position);
        holder.button2.setTag(position);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageview;
        public TextView title;//(a) procedimento con passaggio al costruttore, passo 1
        public TextView subtitle;
        public Button button;
        public Button button2;
        public TextView price;

        public ViewHolder(final View container) {
            super(container);

        }
    }
}
