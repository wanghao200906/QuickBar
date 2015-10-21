package wang.index.view;


import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * @author wang
 * @version 创建时间：2015年4月20日 下午2:04:21 
 * 类说明 简化viewholder的方法
 * 
 */
//   简单的实用方法。直接复用 
//
//@Override
//public View getView(int position, View convertView, ViewGroup parent) {
//	ViewHolder holder =
//			ViewHolder.get(context, parent, R.layout.activity_main, position, convertView);
//
//	Bean b = mData.get(position);
//	((TextView) holder.getView(R.id.tv)).setText(b.getText());
//	return holder.getConvertView();
//}
public class ViewHolder {
	private Context context;
	private SparseArray<View> mView;
	private ViewGroup parent;
	private int position;
	private View convertView;

	public ViewHolder(Context context, ViewGroup parent, int layoutId,
			int position) {
		this.context = context;
		this.mView = new SparseArray<View>();

		this.convertView = LayoutInflater.from(context).inflate(layoutId,
				parent, false);
		convertView.setTag(this);
	}

	public static ViewHolder get(Context context, ViewGroup parent,
			int layoutId, int position, View convertView) {
		if (convertView == null) {
			return new ViewHolder(context, parent, layoutId, position);
		} else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.position = position;
			return holder;
		}
	}

	public <T extends View> T getView(int viewId) {
		View v = mView.get(viewId);
		if (v == null) {
			v = convertView.findViewById(viewId);
			mView.put(viewId, v);
		}
		return (T) v;
	}

	private View findViewById(int viewId1) {
		// TODO Auto-generated method stub
		return null;
	}

	public View getConvertView() {
		return convertView;
	}
}
