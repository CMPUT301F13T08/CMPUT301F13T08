package com.team08storyapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StoryInfoAdapter extends ArrayAdapter<StoryInfo> {
	
	private ArrayList<StoryInfo> infos;
	private Activity activity;
	
	public StoryInfoAdapter(Activity a, int textViewResourceId, ArrayList<StoryInfo> infos){
		super(a, textViewResourceId, infos);
		this.infos = infos;
		this.activity = a;
		
	}
	
    public static class ViewHolder{
        public TextView item1;
        public TextView item2;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        
        if (v == null) {
            LayoutInflater vi =
                (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.stories_row, null);
            holder = new ViewHolder();
            holder.item1 = (TextView) v.findViewById(R.id.big);
            holder.item2 = (TextView) v.findViewById(R.id.small);
            v.setTag(holder);
        }
        else
            holder=(ViewHolder)v.getTag();
 
        final StoryInfo story = infos.get(position);
        
        if (story != null) {
            holder.item1.setText(story.getTitle());
            holder.item2.setText(story.getAuthor());
        }
        return v;
    }
}
