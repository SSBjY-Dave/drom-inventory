package hu.davidorcsik.dorm.inventory.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import hu.davidorcsik.dorm.inventory.R;
import hu.davidorcsik.dorm.inventory.logic.ItemCondition;

public class ItemConditionSpinnerAdapter extends ArrayAdapter<ItemCondition> {
    static class ViewHolder {
        ImageView icon;
        TextView name;
        EditText description;
    }
    private final Context context;
    private final ItemCondition[] conditions;

    public ItemConditionSpinnerAdapter(@NonNull Context context, ItemCondition[] conditions) {
        super(context, R.layout.item_condition_spinner_row);
        this.context = context;
        this.conditions = conditions;
    }

    @Override
    public int getCount() {
        return conditions.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_condition_spinner_row, parent, false);
            viewHolder.icon = convertView.findViewById(R.id.ivConditionIcon);
            viewHolder.name = convertView.findViewById(R.id.txvConditionName);
            viewHolder.description = convertView.findViewById(R.id.edtConditionDescription);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.icon.setImageResource(conditions[position].getIconResourceId());
        viewHolder.name.setText(conditions[position].getName());
        viewHolder.description.setText(conditions[position].getDescription());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Nullable
    @Override
    public ItemCondition getItem(int position) {
        return conditions[position];
    }
}
