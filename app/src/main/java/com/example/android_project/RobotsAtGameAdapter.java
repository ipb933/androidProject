package com.example.android_project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RobotsAtGameAdapter extends ArrayAdapter<RobotAtGame> {
    Context context;
    ArrayList<RobotAtGame> objects;
    public RobotsAtGameAdapter(Context context, int resource, int textViewResourceId, ArrayList<RobotAtGame> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context=context;
        this.objects=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.single_robot_at_game,parent,false);
        TextView tvGameNumber = (TextView)view.findViewById(R.id.tvGameNumber);
        TextView tvRobotNumber = (TextView)view.findViewById(R.id.tvRobotNumber);
        TextView tvRobotScore = (TextView)view.findViewById(R.id.tvRobotScore);
        RobotAtGame temp = objects.get(position);
        tvGameNumber.setText(temp.getGameNumber());
        tvRobotNumber.setText(temp.getRobotNumber());
        tvRobotScore.setText(temp.getRobotScore());
        return view;
    }
}