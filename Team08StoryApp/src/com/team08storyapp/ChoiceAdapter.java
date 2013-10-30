package com.team08storyapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ChoiceAdapter extends ArrayAdapter<Choice>{

	private ArrayList<Choice> infos;
	private Activity activity;
	
	public static class ViewHolder{
		public TextView choiceText;
	}
	
	public ChoiceAdapter(Activity a, int textViewResourceId, ArrayList<Choice> infos){
		super(a, textViewResourceId, infos);
		this.infos = infos;
		this.activity = a;
		
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		View v = convertView;
		ViewHolder holder = new ViewHolder();
		
		if(v == null){
			LayoutInflater vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);		
			v = vi.inflate(R.layout.choices_row, null);
			holder.choiceText = (TextView) v.findViewById(R.id.choiceText);
			v.setTag(holder);
		
		}else{
			holder = (ViewHolder)v.getTag();
		}
		final Choice choice= infos.get(position);
		
		if(choice != null){
			holder.choiceText.setText(choice.getText());
		}
		return v;
		
	}

}
