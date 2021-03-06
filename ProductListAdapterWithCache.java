package com.mivi.mivilogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ProductListAdapterWithCache extends ArrayAdapter<Product> {
    private Context mContext;
    List<Product> mylist;

    public ProductListAdapterWithCache(Context _context, List<Product> _mylist) {
        super(_context, R.layout.lits_item, _mylist);

        mContext = _context;
        this.mylist = _mylist;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Product product = getItem(position);

        ProductViewHolder holder;

        if (convertView == null) {
            convertView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
            convertView = vi.inflate(R.layout.grid_product_item, parent, false);

            //
            holder = new ProductViewHolder();
            holder.img = (ImageView)convertView.findViewById(R.id.image);
            holder.title = (TextView)convertView.findViewById(R.id.title);

            //
            convertView.setTag(holder);
        }
        else{
            holder = (ProductViewHolder) convertView.getTag();
        }


        //
        holder.populate(product, ((HomePageActivity)mContext).isLvBusy());

        //
        return convertView;
    }


    static class ProductViewHolder {
        public ImageView img;
        public TextView title;

        void populate(Product p) {
            title.setText(p.title);

            //
            ImageDownloader imageDownloader = new ImageDownloader();
            imageDownloader.download(p.img_url, img);
        }

        void populate(Product p, boolean isBusy) {
            title.setText(p.title);

            if (!isBusy){
                // download from internet
                ImageDownloader imageDownloader = new ImageDownloader();
                imageDownloader.download(p.img_url, img);
            }
            else{
                // set default image
                img.setImageResource(R.drawable.spinner);
            }
        }
    }

}

