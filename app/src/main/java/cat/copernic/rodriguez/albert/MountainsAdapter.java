package cat.copernic.rodriguez.albert;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class MountainsAdapter extends RecyclerView.Adapter<MountainsAdapter.ViewHolder> {

    private ArrayList<Mountain> mMountainsData;
    private Context mContext;

    MountainsAdapter(Context context, ArrayList<Mountain> mountainsData) {
        this.mMountainsData = mountainsData;
        this.mContext = context;
    }

    @Override
    public MountainsAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MountainsAdapter.ViewHolder holder,
                                 int position) {
        Mountain currentMountain = mMountainsData.get(position);

        holder.bindTo(currentMountain);
    }

    @Override
    public int getItemCount() {
        return mMountainsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mMountainsImage;

        ViewHolder(View itemView) {
            super(itemView);

            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mMountainsImage = itemView.findViewById(R.id.mountainsImage);

            itemView.setOnClickListener(this);
        }

        void bindTo(Mountain currentMountain) {
            mTitleText.setText(currentMountain.getTitle());
            mInfoText.setText(currentMountain.getInfo());

            Glide.with(mContext).load(
                    currentMountain.getImageResource()).into(mMountainsImage);
        }

        @Override
        public void onClick(View view) {
            Mountain currentMountain = mMountainsData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("title", currentMountain.getTitle());
            detailIntent.putExtra("image_resource",
                    currentMountain.getImageResource());
            mContext.startActivity(detailIntent);
        }
    }
}
