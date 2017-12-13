package us.mifeng.recycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//http://www.jianshu.com/p/0f5d0991efdc
    private RecyclerView recyclerView;
    ArrayList<String>list= new ArrayList<>();
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadData();
        onRecyclerListener();
    }

    private void loadData() {
        for(int i=0;i<21;i++){
            list.add("嘿嘿嘿"+i);
        }
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //设置RecyclerView的排列方式
        //LinearLayoutManager manager = new LinearLayoutManager(this);
        //manager.setOrientation(LinearLayoutManager.VERTICAL);

        GridLayoutManager manager = new GridLayoutManager(this,3);
        manager.setOrientation(GridLayoutManager.VERTICAL);

        //StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter(this,list);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        //自定义ListView分割线的样式
        //recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));
        //recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        //自定义GridView分割线的样式
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));

    }
    public void onRecyclerListener(){
        adapter.setOnItemClickListener(new RecyclerAdapter.OnRecyclerItemListener() {
            @Override
            public void onItemClick(View view, int postion, int id) {
               Toast.makeText(MainActivity.this,list.get(postion), Toast.LENGTH_SHORT).show();
                //点击添加数据
                adapter.deleteItem(list.get(postion));
            }
        });
    }
    //点击添加数据
    public void addData(View view) {
        adapter.addItem("你好");
    }
    //点击删除数据
    public void deleteData(View view) {
        adapter.deleteItem("你好");
    }
}
