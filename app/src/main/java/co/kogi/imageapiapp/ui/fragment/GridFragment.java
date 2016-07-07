package co.kogi.imageapiapp.ui.fragment;


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
import android.widget.AdapterView;
import android.widget.GridView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.jude.rollviewpager.RollPagerView;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.kogi.imageapiapp.R;
import co.kogi.imageapiapp.device.HttpClient;
import co.kogi.imageapiapp.device.NetworkService;
import co.kogi.imageapiapp.model.Picture;
import co.kogi.imageapiapp.ui.adapter.GridAdapter;
import co.kogi.imageapiapp.ui.adapter.PagerAdapter;
import co.kogi.imageapiapp.util.AppContext;
import co.kogi.imageapiapp.util.OAuthHelper;
import co.kogi.imageapiapp.util.PermissionHelper;
import co.kogi.imageapiapp.util.UIHelper;


public class GridFragment extends Fragment  {

    private GridAdapter adapter;
    private MaterialRefreshLayout materialRefreshLayout;
    private PagerAdapter pagerAdapter;
    private boolean feedLoaded = false;


    public GridFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        feedLoaded = false;
        return inflater.inflate(R.layout.fragment_grid, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UIHelper.changeAppBarTitle(getActivity(),UIHelper.getStringResource(R.string.app_name_ptr));
        final GridView gridView_gallery = (GridView) getActivity().findViewById(R.id.gridView_gallery);
        adapter = new GridAdapter(getActivity());
        gridView_gallery.setAdapter(adapter);
        materialRefreshLayout = (MaterialRefreshLayout) getActivity().findViewById(R.id.refresh);
        final RollPagerView rollPagerView_main = (RollPagerView) getActivity().findViewById(R.id.rollPagerView_main);
        pagerAdapter = new PagerAdapter();
        rollPagerView_main.setAdapter(pagerAdapter);


        materialRefreshLayout.setIsOverLay(false);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                PermissionHelper.requestStoragePermission(getActivity(), new PermissionHelper.PermissionGrantedListener() {
                    @Override
                    public void onPermissionGranted() {
                        HttpClient.startCache();
                        refreshGallery();
                    }
                });
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
            }
        });

        pagerAdapter.setItemClickListener(new PagerAdapter.ItemClickListener() {
            @Override
            public void onItemClickListener(int pos) {
                if (feedLoaded) {
                    Bundle b = new Bundle();
                    Picture[] array = new Picture[adapter.getItems().size()];
                    adapter.getItems().toArray(array);
                    b.putParcelableArray("pictureList", array);
                    b.putInt("selectedPicture", pos);
                    UIHelper.changeFragment(R.id.container, getFragmentManager(), new ShowcaseFragment(), b);
                }
            }
        });


        rollPagerView_main.getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                gridView_gallery.setItemChecked(position,true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        gridView_gallery.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
        gridView_gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rollPagerView_main.getViewPager().setCurrentItem(position);
            }
        });

        fillwithThumbnails();



    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            PermissionHelper.requestStoragePermission(getActivity(), new PermissionHelper.PermissionGrantedListener() {
                @Override
                public void onPermissionGranted() {
                    HttpClient.startCache();
                    UIHelper.showProgressDialog(getActivity(),UIHelper.getStringResource(R.string.refresh_feed));
                    refreshGallery();
                }
            });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    private void fillwithThumbnails(){
        List<Picture> pictureList = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            Picture picture = new Picture();
            picture.setLink(Integer.toString(R.drawable.placeholder));
            picture.setTitle("Title");
            pictureList.add(picture);
        }
        adapter.setItems(pictureList);
        pagerAdapter.setItems(pictureList);
    }

    private void showNoConnDialog(){
        UIHelper.showInformationDialog(getActivity(),
                UIHelper.getStringResource(R.string.information),
                UIHelper.getStringResource(R.string.network_err_msg)
                , null);
        UIHelper.hideProgressDialog();
        materialRefreshLayout.finishRefresh();
    }


    private void refreshGallery(){

        if (!NetworkService.hasInternetAccess()){
           showNoConnDialog();
            return;

        }
        OAuthHelper.getAuthToken(getActivity(), new OAuthHelper.TokenListener() {
            @Override
            public void onTokenObtained(String token) {
                AppContext.addPreference("token",token);
                HttpClient.getAuth("https://api.imgur.com/3/gallery/hot/top/0.json", new AsyncHttpClient.StringCallback() {
                    @Override
                    public void onCompleted(Exception e, AsyncHttpResponse source, String result) {
                        if (e != null){
                            if (!NetworkService.hasInternetAccess()){
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                       showNoConnDialog();
                                    }
                                });

                            }
                            return;
                        }

                        try {
                            JSONObject object = new JSONObject(result);
                            JSONArray array = object.getJSONArray("data");
                            String str = array.toString();
                            Picture[] pictures = AppContext.getGson().fromJson(str,Picture[].class);
                            final List<Picture> filteredPictures = new ArrayList<>();
                            for (Picture picture : pictures){
                                if (
                                        picture.getAccount_id() > 0 &&
                                                picture.getType() != null &&
                                                !picture.isNsfw() &&
                                                ( picture.getType().equals("image/png")
                                                        || picture.getType().equals("image/jpeg") ) ){
                                    filteredPictures.add(picture);
                                }
                            }

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pagerAdapter.setItems(filteredPictures);
                                    adapter.setItems(filteredPictures);
                                    materialRefreshLayout.finishRefresh();
                                    UIHelper.hideProgressDialog();
                                    feedLoaded =true;
                                    UIHelper.changeAppBarTitle(getActivity(),UIHelper.getStringResource(R.string.app_name));
                                }
                            });


                        } catch (JSONException ex) {

                        }


                    }
                });
            }

            @Override
            public void onTokenRequestFailed() {
                UIHelper.hideProgressDialog();
                materialRefreshLayout.finishRefresh();
            }
        });
    }
}
