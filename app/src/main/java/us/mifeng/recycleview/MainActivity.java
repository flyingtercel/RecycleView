package us.mifeng.recycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//http://www.jianshu.com/p/0f5d0991efdc
    private RecyclerView recyclerView;
    ArrayList<String>list= new ArrayList<>();
    private RecyclerAdapter adapter;
    private TextView addItem;
    private TextView deleteItem;
    private TextView changeList;
    private TextView changeGrid;
    private TextView changeStaggered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        findViewById();
        loadData();
    }

    private void findViewById() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //设置item的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置管理这对象
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        //添加适配器
        adapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(adapter);

        //设置自定义的点击事件
        adapter.setOnItemClickListener(new RecyclerAdapter.ItemListener() {
            @Override
            public void onItemClick(View view, int postion, long id) {
                Toast.makeText(MainActivity.this, "点击了Item" + postion, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int postion, long id) {
                Toast.makeText(MainActivity.this, "点击了Item" + postion, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadData() {
        for(int i='A';i<='Z';i++){
            list.add(" "+((char)i));
        }
        adapter.initData(list);
    }

    private void initView() {
        //设置RecyclerView的排列方式
        //LinearLayoutManager manager = new LinearLayoutManager(this);
        //manager.setOrientation(LinearLayoutManager.VERTICAL);

        //GridLayoutManager manager = new GridLayoutManager(this,3);
        //manager.setOrientation(GridLayoutManager.VERTICAL);

        //StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
       // recyclerView.setLayoutManager(manager);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        //自定义ListView分割线的样式
        //recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));
        //recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        //自定义GridView分割线的样式
        //recyclerView.addItemDecoration(new DividerGridItemDecoration(this));

        addItem = (TextView) findViewById(R.id.addItem);
        deleteItem = (TextView) findViewById(R.id.deleteItem);
        changeList = (TextView) findViewById(R.id.changeListView);
        changeGrid = (TextView) findViewById(R.id.changeGridView);
        changeStaggered = (TextView) findViewById(R.id.changeStaggered);
        addItem.setOnClickListener(this);
        deleteItem.setOnClickListener(this);
        changeList.setOnClickListener(this);
        changeGrid.setOnClickListener(this);
        changeStaggered.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addItem:
                adapter.addItem("ZZ");
                break;
            case R.id.deleteItem:
                adapter.deleteItem(0);
                break;
            case R.id.changeListView:
                //设置Listview显示方式
                recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                break;
            case R.id.changeGridView:
                //设置GridView显示方式
                recyclerView.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false));
                break;
            case R.id.changeStaggered:
                //设置流式显示方式
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                break;
        }
    }
}
