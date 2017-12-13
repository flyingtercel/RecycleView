package us.mifeng.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 黑夜之火 on 2017/12/13.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {
    private ArrayList<String>list;
    private Context context;
    private OnRecyclerItemListener listener;
    public RecyclerAdapter(Context context,ArrayList<String> list) {
        this.list = list;
        this.context = context;
    }
    public void setOnItemClickListener(OnRecyclerItemListener listener){
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        String data = list.get(position);
        final ViewHolder vh = (ViewHolder) holder;
        vh.title.setText(data);
        vh.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(vh.title,position,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titel);
        }
    }
    public interface OnRecyclerItemListener{
        public void onItemClick(View view,int postion,int id);
    }
    public void addItem(String data){
        if (list!=null){
            list.add(data);
            notifyDataSetChanged();
        }
    }
    public void deleteItem(String data){
        if (list!=null){
            list.remove(data);
            notifyDataSetChanged();
        }
    }
}
