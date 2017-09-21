package com.example.project1;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<Post> users;
    private int rowLayout;
    private Context context;
    private String name, desc;
    private Bitmap decodedImage, finalImage;
    static private ClickListner clickListner;


    public static class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RelativeLayout userLayout;
        TextView firstn, lastn, dateb, dpt, g;
        ImageView pt;

        public UserViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            userLayout = (RelativeLayout) v.findViewById(R.id.user_Layout);
            firstn = (TextView) v.findViewById(R.id.fn);
            lastn = (TextView) v.findViewById(R.id.ln);
            dateb = (TextView) v.findViewById(R.id.date);
            dpt = (TextView) v.findViewById(R.id.d);
            pt = (ImageView) v.findViewById(R.id.p);
            g = (TextView) v.findViewById(R.id.gen);
        }

        @Override
        public void onClick(View v) {
            if(clickListner!=null){
                clickListner.showDialog(v, getPosition());
            }
        }
    }

    public interface ClickListner{
        public void showDialog(View v, int position);
    }

    public UsersAdapter(List<Post> users, int rowLayout, Context context) {
        this.users = users;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public UsersAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        holder.firstn.setText(users.get(position).getFirstName());
        holder.lastn.setText(users.get(position).getLastName());
        holder.dateb.setText(users.get(position).getDob());
        holder.dpt.setText(users.get(position).getDept().toString());
        holder.g.setText(users.get(position).getGender().toString());
        finalImage = decodeImage(users.get(position).getPhoto().toString());
        holder.pt.setImageBitmap(finalImage);
        age_calculation(holder, users.get(position).getDob());
    }

    public void setClickListner(ClickListner click){
        this.clickListner=click;
    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    public Bitmap decodeImage(String imageString){
        byte[] imageBytes = android.util.Base64.decode(imageString, android.util.Base64.DEFAULT);
        decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        return decodedImage;
    }

    void age_calculation(UserViewHolder holder, String dob) {

        if (dob.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})") || dob.matches("([0-9])/([0-9])/([0-9]{4})")) {
            String [] dateParts = dob.split("/");
            String day = dateParts[1];
            String month = dateParts[0];
            String year = dateParts[2];
            int monthint = Integer.parseInt(month);
            int yearint = Integer.parseInt(year);
            Calendar c = Calendar.getInstance();
            int current = c.get(Calendar.YEAR);
            int monthcur = c.get(Calendar.MONTH);
            if (monthint < monthcur) {
                holder.dateb.setText("" + (current - yearint ) + "years");
            } else if(current != yearint)
                holder.dateb.setText("" + ((current - yearint) - 1) + "years" );
            else
                holder.dateb.setText("" + (current - yearint) + "years");
        } else
            holder.dateb.setText(dob);

    }
}

