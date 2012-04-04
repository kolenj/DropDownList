package com.dropdownlistdemo;

import java.util.ArrayList;

import com.popupwindowtrial.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class DropDownListAdapter extends BaseAdapter {

	private ArrayList<String> mListItems;
	private LayoutInflater mInflater;
	private TextView mSelectedItems;
	private static int selectedCount = 0;
	private static String firstSelected = "";
	private ViewHolder holder;
	private static String selected = "";
	
	public static String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		DropDownListAdapter.selected = selected;
	}

	public DropDownListAdapter(Context context, ArrayList<String> items,
			TextView tv) {
		mListItems = new ArrayList<String>();
		mListItems.addAll(items);
		mInflater = LayoutInflater.from(context);
		mSelectedItems = tv;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListItems.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.drop_down_list_row, null);
			holder = new ViewHolder();
			holder.tv = (TextView) convertView.findViewById(R.DropDownList.SelectOption);
			holder.chkbox = (CheckBox) convertView.findViewById(R.DropDownList.checkbox);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv.setText(mListItems.get(position));

		final int position1 = position;
		holder.chkbox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setText(position1);
			}
		});

		if(DropDownListDemo.checkSelected[position])
			holder.chkbox.setChecked(true);
		else
			holder.chkbox.setChecked(false);	 
		return convertView;
	}

	private void setText(int position1){
		if (!DropDownListDemo.checkSelected[position1]) {
			DropDownListDemo.checkSelected[position1] = true;
			selectedCount++;
		} else {
			DropDownListDemo.checkSelected[position1] = false;
			selectedCount--;
		}

		if (selectedCount == 0) {
			mSelectedItems.setText(R.string.select_string);
		} else if (selectedCount == 1) {
			for (int i = 0; i < DropDownListDemo.checkSelected.length; i++) {
				if (DropDownListDemo.checkSelected[i] == true) {
					firstSelected = mListItems.get(i);
					break;
				}
			}
			mSelectedItems.setText(firstSelected);
			setSelected(firstSelected);
		} else if (selectedCount > 1) {
			for (int i = 0; i < DropDownListDemo.checkSelected.length; i++) {
				if (DropDownListDemo.checkSelected[i] == true) {
					firstSelected = mListItems.get(i);
					break;
				}
			}
			mSelectedItems.setText(firstSelected + " & "+ (selectedCount - 1) + " more");
			setSelected(firstSelected + " & "+ (selectedCount - 1) + " more");
		}
	}

	private class ViewHolder {
		TextView tv;
		CheckBox chkbox;
	}
}
