package hu.davidorcsik.dorm.inventory.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import hu.davidorcsik.dorm.inventory.R;
import hu.davidorcsik.dorm.inventory.logic.ItemType;

public class ItemTypeSpinnerAdapter extends ArrayAdapter<ItemType> {
    static class ViewHolder {
        ImageView icon;
        TextView name;
    }

    private final Context context;
    private final ItemType[] items;

    public ItemTypeSpinnerAdapter(@NonNull Context context, ItemType[] items) {
        super(context, R.layout.item_type_spinner_row);
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_type_spinner_row, parent, false);
            viewHolder.icon = convertView.findViewById(R.id.ivItemIcon);
            viewHolder.name = convertView.findViewById(R.id.txvItemName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.icon.setImageResource(items[position].getIconResourceId());
        viewHolder.name.setText(items[position].getName());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Nullable
    @Override
    public ItemType getItem(int position) {
        return items[position];
    }
}
