package com.next.bluesignin;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {


    private ImageView imageView;
    private static OnItemClickListener mListener;
    private ArrayList<Data> listData = new ArrayList<>();
    @NonNull
    @Override
    public RecyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        //return new ItemViewHolder(view);
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item, parent, false) ;
        RecyclerAdapter.ItemViewHolder vh = new RecyclerAdapter.ItemViewHolder(view) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ItemViewHolder holder, int position) {

        holder.onBind(listData.get(position));
    }

    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
    void addItem(Data data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

   /// 새로 추가



    /////  새로 추가



    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView textView1;
        private TextView textView2;
        Uri uri;


       public ItemViewHolder(final View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int pos=getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION){
                        System.out.println(pos);
                        System.out.println(mListener);
                        if(mListener!=null)
                        {
                            mListener.onItemClick(view,pos);
                        }
                    }
                }
            });

           textView1 = itemView.findViewById(R.id.textView1);
           textView2 = itemView.findViewById(R.id.textView2);
           imageView = itemView.findViewById(R.id.imageView12);
        }


        void onBind(Data data) {
            textView1.setText(data.getTitle());
            textView2.setText(data.getContent());
            uri= Uri.parse(data.getResId());
            imageView.setImageResource(R.drawable.unnamed);
        }
    }


    public static class Data {

        private String title;
        private String content;
        private String resId;

        public String getTitle() {

            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getResId() {
            return resId;
        }

        public void setResId(String resId) {
            this.resId = resId;
        }
    }


}
