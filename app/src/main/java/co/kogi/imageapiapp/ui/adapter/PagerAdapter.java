package co.kogi.imageapiapp.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.DynamicPagerAdapter;

import java.util.List;

import co.kogi.imageapiapp.model.Picture;
import co.kogi.imageapiapp.util.UIHelper;

/**
 * Created by user on 6/07/2016.
 */
public class PagerAdapter extends DynamicPagerAdapter{

    ItemClickListener listener;
    List<Picture> objects;
    @Override
    public View getView(ViewGroup container, final int position) {
        ImageView view = new ImageView(container.getContext());
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        UIHelper.displayImage(objects.get(position).getLink(),view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClickListener(position);
            }
        });

        return view;
    }

    @Override
    public int getCount() {
       if (objects == null){
           return 0;
       } else {
           return objects.size();
       }
    }

    public void setItems(List<Picture> objects){
        this.objects = objects;
        notifyDataSetChanged();
    }

    public List<Picture> getItems(){
        return objects;
    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    public interface ItemClickListener{
        void onItemClickListener(int pos);
    }
}
