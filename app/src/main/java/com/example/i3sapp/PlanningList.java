package com.example.i3sapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PlanningList extends ArrayAdapter<Planning>
{
    private Activity context;
    private List<Planning> planningList;

    public  PlanningList(Activity context, List<Planning> planningList)
    {
        super(context, R.layout.list_layout, planningList);
        this.context = context;
        this.planningList = planningList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null,true);

        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);
        TextView textViewPost_Date = (TextView) listViewItem.findViewById(R.id.textViewPostDate);
        TextView textViewChannel = (TextView) listViewItem.findViewById(R.id.textViewChannel);
        TextView textViewType = (TextView) listViewItem.findViewById(R.id.textViewType);
        TextView textViewDescription = (TextView) listViewItem.findViewById(R.id.textViewDescription);
        TextView textViewSponsoring = (TextView) listViewItem.findViewById(R.id.textViewSponsoring);
        TextView textViewHachtags = (TextView) listViewItem.findViewById(R.id.textViewHachtags);

        Planning planning = planningList.get(position);

        textViewGenre.setText(planning.getPlanningGenre());
        textViewPost_Date.setText(planning.getPlanningPostDate());
        textViewChannel.setText(planning.getPlanningChannel());
        textViewType.setText(planning.getPlanningType());
        textViewDescription.setText(planning.getPlanningDescription());
        textViewSponsoring.setText(planning.getPalnningSponsoring());
        textViewHachtags.setText(planning.getPlanningHachtags());

        return listViewItem;

    }
}
