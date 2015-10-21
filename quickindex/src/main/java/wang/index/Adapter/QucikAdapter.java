package wang.index.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import wang.index.Bean.Person;
import wang.index.R;
import wang.index.view.ViewHolder;

/**
 * 创建日期: 15/10/8 上午10:04
 * 作者:wanghao
 * 描述:
 */
public class QucikAdapter extends BaseAdapter {
    Context context;
    ArrayList<Person> person;

    public QucikAdapter(Context context, ArrayList<Person> pserson) {
        this.context = context;
        this.person = pserson;
    }

    @Override
    public int getCount() {
        return person.size();
    }

    @Override
    public Object getItem(int position) {
        return person.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder =
                ViewHolder.get(context, parent, R.layout.item_list, position, convertView);


        Person p = person.get(position);
        String currentletter = p.getPinyin().charAt(0) + "";
        String str = null;
        TextView index = ((TextView) holder.getView(R.id.tv_index));
        TextView name = ((TextView) holder.getView(R.id.tv_name));


        if (position == 0) {
            str = currentletter;
        } else {
            String preletter = person.get(position-1).getPinyin().charAt(0) + "";
            if (!TextUtils.equals(preletter, currentletter)) {
                str = currentletter;
            }
        }

        index.setVisibility(str == null ? View.GONE : View.VISIBLE);
        index.setText(currentletter);
        name.setText(p.getName());

        return holder.getConvertView();
    }
}
