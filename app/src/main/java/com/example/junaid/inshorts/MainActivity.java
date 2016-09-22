package com.example.junaid.inshorts;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.junaid.inshorts.adapters.ScreenSlidePagerAdapter;
import com.example.junaid.inshorts.apiclients.Service;
import com.example.junaid.inshorts.helpers.DbHelper;
import com.example.junaid.inshorts.helpers.ImageHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

//import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class MainActivity extends AppCompatActivity implements Service.Result, SlidingFragment.AppData {

    private JSONArray mJsonArray ;
    private List<Card> mCards;
    DbHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Service service = new Service(this,this);
        service.callApi();
    }


    @Override
    public void result(String response) {
        JSONObject jObject = null;
        String body = "";
        String imageUrl = "";
        response    =   getApplicationContext().getResources().getString(R.string.response);
        try {
            jObject = new JSONObject(response);

            mJsonArray = jObject.getJSONArray("cards");

            mDbHelper = new DbHelper(this);
            DbHelper dbHelper = new DbHelper(this);
            mCards = dbHelper.getCards(mJsonArray);

            ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
//            ((ViewPager) findViewById(R.id.pager)).setAdapter(screenSlidePagerAdapter);

            VerticalViewPager verticalViewPager = (VerticalViewPager) findViewById(R.id.pager);
            verticalViewPager.setAdapter(screenSlidePagerAdapter);

            ImageHelper.getInstance().init(this);
//            ImageHelper.getInstance().displayImage(imageUrl, (ImageView) findViewById(R.id.image));
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        ((TextView) findViewById(R.id.text)).setText(body);
    }

    @Override
    public JSONObject getData(int position) {
        String body = "";
        String imageUrl = "";
        String hdline;
        String reportedBy;
        String moreAt;
        JSONObject jsonObject = new JSONObject();
        try {
            body = mCards.get(position).body;
            imageUrl = mCards.get(position).imageUrl;
            hdline = mCards.get(position).shortLine;
            reportedBy = mCards.get(position).reportedBy;
            moreAt = mCards.get(position).moreAt;

            jsonObject.put("image_url", imageUrl);
            jsonObject.put("body", body);
            jsonObject.put("shortLine", hdline);
            jsonObject.put("reportby", reportedBy);
            jsonObject.put("more", moreAt);

            /*
            hdline = jsonObject.getString("shortLine");
            reportedBy = jsonObject.getString("reportby");
            moreAt = jsonObject.getString("more");

             */

        } catch (Exception e){

        }
        return jsonObject;
    }
}
