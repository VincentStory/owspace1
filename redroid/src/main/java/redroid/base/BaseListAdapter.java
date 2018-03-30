package redroid.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * RecyclerView适配器基类
 *
 * @author RobinVanYang created at 2018-01-26 13:37.
 */

public abstract class BaseListAdapter<T> extends RecyclerView.Adapter<BaseListAdapter.BaseViewHolder> {
    private ArrayList<T> mDataSource = new ArrayList<>();
    public OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (null != mOnItemClickListener) {
            holder.itemView.setOnClickListener(view -> mOnItemClickListener.onItemClick(position));
        }
    }

    public void addItem(T item) {
        mDataSource.add(item);
    }

    public void addItems(ArrayList<T> items) {
        mDataSource.addAll(items);
        notifyDataSetChanged();
    }

    public void clearAll() {
        mDataSource.clear();
    }

    public T getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public void bindView(int position) {/*need to be override in sub class.*/}
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
