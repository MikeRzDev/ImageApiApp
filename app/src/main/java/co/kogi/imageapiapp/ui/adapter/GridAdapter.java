package co.kogi.imageapiapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.kogi.imageapiapp.R;
import co.kogi.imageapiapp.model.Picture;
import co.kogi.imageapiapp.util.UIHelper;

/**
 * Created by user on 6/07/2016.
 */
public class GridAdapter extends BaseAdapter {

    private List<Picture> objects;

    private Context context;
    private LayoutInflater layoutInflater;

    public GridAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (objects == null){
            return 0;
        } else {
            return objects.size();
        }

    }

    @Override
    public Picture getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_grid, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }


    private void initializeViews(Picture object, ViewHolder holder) {

        UIHelper.displayImage(object.getLink(),holder.imageViewPicture);
        holder.textViewTitle.setText(object.getTitle());
    }

    protected class ViewHolder {
        private ImageView imageViewPicture;
        private TextView textViewTitle;

        public ViewHolder(View view) {
            imageViewPicture = (ImageView) view.findViewById(R.id.imageView_picture);
            textViewTitle = (TextView) view.findViewById(R.id.textView_title);
        }
    }

    public void setItems(List<Picture> items){
        this.objects = items;
        notifyDataSetChanged();
    }

    public List<Picture> getItems(){
        return objects;
    }


}
