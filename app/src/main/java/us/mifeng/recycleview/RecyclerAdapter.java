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
    private ArrayList<String>list = new ArrayList<>();
    private ArrayList<Integer>heights = new ArrayList<>();
    private Context context;
    private ItemListener listener;
    public RecyclerAdapter(Context context) {
        this.context = context;
    }
    public void initData(ArrayList<String> data){
        list.addAll(data);
        notifyDataSetChanged();
    }
    public void initHeight(){
        for(int i=0;i<list.size();i++){
            heights.add((int)(100+Math.random()*400));
        }
    }

    public void setOnItemClickListener(ItemListener listener){
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
        ViewGroup.LayoutParams layoutParams = vh.title.getLayoutParams();
        layoutParams.height = heights.get(position);
        vh.title.setLayoutParams(layoutParams);
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
        initHeight();
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;

        public ViewHolder(final View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titel);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(itemView,getAdapterPosition(),getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(itemView,getAdapterPosition(),getAdapterPosition());
                    return true;
                }
            });
        }
    }
    //设置接口，用来监听点击事件
    public interface ItemListener{
        void onItemClick(View view,int postion,long id);
        void onItemLongClick(View view,int postion,long id);
    }
    //点击添加
    public void addItem(String data){
        if (list!=null){
            list.add(0,data);
            notifyDataSetChanged();
        }
    }
    //点击删除
    public void deleteItem(int postion){
        if (postion<list.size()){
            list.remove(postion);
            notifyDataSetChanged();
        }
    }
}
