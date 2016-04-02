package com.example.beknur.project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class tab2 extends Fragment {
    public Elements content;
    public Elements content2;
    public ArrayList<String> title = new ArrayList<String>();
    public ArrayList<String> title2 = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private ListView lv;

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.content_tab2,container,false);
        lv =(ListView) view.findViewById(R.id.listView1);

        new NewThread().execute();
        adapter=new ArrayAdapter(getActivity().getApplicationContext(),R.layout.list_item, title){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = getActivity().getLayoutInflater().inflate(R.layout.list_item,null);
                TextView tv = (TextView) v.findViewById(R.id.pro_item);
                ImageView imageView = (ImageView) v.findViewById(R.id.im);
                new DownloadImageTask(imageView).execute(title2.get(position));
                tv.setText(title.get(position));
                TextView tv2 = (TextView)v.findViewById(R.id.sr);
                String urldisplay = title2.get(position);

                tv2.setText(title2.get(position));
                return v;
            }
        };
        return view;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }



        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public class  NewThread extends AsyncTask<String, Void , String>{
        @Override
        protected String doInBackground(String... arg){
            Document Doc;
            try{
                Doc = Jsoup.connect("https://tengrinews.kz/economic/").get();
                content = Doc.select(".name");
                content2 = Doc.getElementsByTag("img");
                for (Element el : content2) {
                    String src = el.absUrl("src");
                    title2.add(src);

                }
                title.clear();
                for(Element contents: content){
                    title.add(contents.text());
                }
            }catch (IOException e){
                e.printStackTrace();

            }
            return null;
        }


    @Override
    protected void onPostExecute(String result){
        lv.setAdapter(adapter);
    }
    }
}
