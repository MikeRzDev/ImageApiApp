package co.kogi.imageapiapp.ui.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.jude.rollviewpager.RollPagerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import co.kogi.imageapiapp.R;
import co.kogi.imageapiapp.model.Picture;
import co.kogi.imageapiapp.ui.adapter.PagerAdapter;
import co.kogi.imageapiapp.util.UIHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowcaseFragment extends Fragment {


    TextView textView_author;
    TextView textView_date;
    TextView textView_tags;
    TextView textView_authorTitle;
    TextView textView_dateTitle;
    TextView textView_tagsTitle;


    String pictureUrl;

    public ShowcaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_showcase, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final PagerAdapter pagerAdapter = new PagerAdapter();
        textView_author = (TextView) getActivity().findViewById(R.id.textView_author);
        textView_date =  (TextView) getActivity().findViewById(R.id.textView_date);
        textView_tags =  (TextView) getActivity().findViewById(R.id.textView_tags);

        textView_authorTitle = (TextView) getActivity().findViewById(R.id.textView_authorTitle);
        textView_dateTitle =  (TextView) getActivity().findViewById(R.id.textView_dateTitle);
        textView_tagsTitle =  (TextView) getActivity().findViewById(R.id.textView_tagTitle);

        textView_authorTitle.setText(UIHelper.getStringResource(R.string.author));
        textView_dateTitle.setText(UIHelper.getStringResource(R.string.publish_date));
        textView_tagsTitle.setText(UIHelper.getStringResource(R.string.tag));

        RollPagerView rollPagerView_main = (RollPagerView) getActivity().findViewById(R.id.rollPagerView_main);


        Bundle b = getArguments();
        if (b != null){

            Picture[] pictures = (Picture[]) b.getParcelableArray("pictureList");
            int selectedPic = b.getInt("selectedPicture");
            pagerAdapter.setItems(new ArrayList<>(Arrays.asList(pictures)));
            rollPagerView_main.getViewPager().setCurrentItem(selectedPic);
            setPictureInfo(pictures[selectedPic]);
            rollPagerView_main.setAdapter(pagerAdapter);

            rollPagerView_main.getViewPager().setCurrentItem(selectedPic);

            pagerAdapter.setItemClickListener(new PagerAdapter.ItemClickListener() {
                @Override
                public void onItemClickListener(int pos) {
                    Bundle b = new Bundle();
                    String profile = pagerAdapter.getItems().get(pos).getAccount_url();

                    b.putString("profile",profile);
                    UIHelper.changeFragment(R.id.container,getFragmentManager(),new WebviewFragment(),b);
                }
            });






            rollPagerView_main.getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    setPictureInfo(pagerAdapter.getItems().get(position));


                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_showcase, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_share) {
            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse(pictureUrl))
                    .build();
            ShareDialog.show(this,content);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setPictureInfo(Picture picture){
        UIHelper.changeAppBarTitle(getActivity(),picture.getTitle());
        pictureUrl = picture.getLink();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE dd MMM yyyy 'at' HH:mm");
        long dateMillis = picture.getDatetime() * 1000;
        textView_author.setText(picture.getAccount_url());
        textView_date.setText(sdf.format(new Date(dateMillis)));
        textView_tags.setText(picture.getTopic());
    }
}
