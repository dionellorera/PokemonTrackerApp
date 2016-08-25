package com.example.dione.pokemontrackerapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dione.pokemontrackerapp.api.models.Results;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dione on 25/08/2016.
 */
public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.MyViewHolder> {
    private List<Results> pokemonArrayList;
    private Context context;
    public PokemonAdapter(Context context, List<Results> pokemonArrayList){
        this.pokemonArrayList = pokemonArrayList;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemon_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.pokemonName.setText(pokemonArrayList.get(position).getmName());
        switch (pokemonArrayList.get(position).getmRare()){
            case "uncommon":
                holder.pokemonRarity.setText(R.string.uncommon_string);
                break;
            case "very_rare":
                holder.pokemonRarity.setText(R.string.very_rare_string);
                break;
            case "special":
                holder.pokemonRarity.setText(R.string.special_string);
                break;
            case "rare":
                holder.pokemonRarity.setText(R.string.rare_string);
                break;
            default:
                return;
        }
        holder.pokemonLocation.setText(pokemonArrayList.get(position).getmCoords());
        Picasso.with(context)
                .load(pokemonArrayList.get(position).getmIcon())
                .resize(100, 100).centerCrop()
                .into(holder.pokemonImage);
    }

    @Override
    public int getItemCount() {
        return pokemonArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView pokemonName;
        private TextView pokemonRarity;
        private TextView pokemonLocation;
        private ImageView pokemonImage;

        public MyViewHolder(View view) {
            super(view);
            pokemonName = (TextView) view.findViewById(R.id.pokemonName);
            pokemonRarity = (TextView) view.findViewById(R.id.pokemonRarity);
            pokemonLocation = (TextView) view.findViewById(R.id.pokemonLocation);
            pokemonImage = (ImageView) view.findViewById(R.id.pokemonImage);
        }
    }

//    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
//                                                         int reqWidth, int reqHeight) {
//
//        // First decode with inJustDecodeBounds=true to check dimensions
//        final BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(res, resId, options);
//
//        // Calculate inSampleSize
//        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
//
//        // Decode bitmap with inSampleSize set
//        options.inJustDecodeBounds = false;
//        return BitmapFactory.decodeResource(res, resId, options);
//    }
//
//    public static int calculateInSampleSize(
//            BitmapFactory.Options options, int reqWidth, int reqHeight) {
//        // Raw height and width of image
//        final int height = options.outHeight;
//        final int width = options.outWidth;
//        int inSampleSize = 1;
//
//        if (height > reqHeight || width > reqWidth) {
//
//            final int halfHeight = height / 2;
//            final int halfWidth = width / 2;
//
//            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
//            // height and width larger than the requested height and width.
//            while ((halfHeight / inSampleSize) >= reqHeight
//                    && (halfWidth / inSampleSize) >= reqWidth) {
//                inSampleSize *= 2;
//            }
//        }
//
//        return inSampleSize;
//    }
}
