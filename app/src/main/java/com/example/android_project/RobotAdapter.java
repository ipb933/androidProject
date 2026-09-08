package com.example.android_project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RobotAdapter extends ArrayAdapter<Robot> {
    Context context;
    ArrayList<Robot> objects;
    public RobotAdapter(Context context, int resource, int textViewResourceId, ArrayList<Robot> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context=context;
        this.objects=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.single_robot,parent,false);
        TextView tvGameNumber = (TextView)view.findViewById(R.id.tvTeamName);
        TextView tvRobotNumber = (TextView)view.findViewById(R.id.tvRobotNumber);
        TextView tvRobotScore = (TextView)view.findViewById(R.id.tvRobotAverageScore);
        return view;
    }
}