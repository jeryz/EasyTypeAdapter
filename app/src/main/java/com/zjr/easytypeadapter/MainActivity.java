package com.zjr.easytypeadapter;

import android.graphics.Rect;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zjr.easytypeadapter.adapter.BaseItemHolder;
import com.zjr.easytypeadapter.adapter.EasyTypeAdapter;
import com.zjr.easytypeadapter.adapter.RvItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DemoAdapter());
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 10;
            }
        });
    }

    public class HeaderViewHolder extends BaseItemHolder{

        public HeaderViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
        }
    }


    public class DemoAdapter extends EasyTypeAdapter {

        private ArrayList<String> list1 = new ArrayList();
        private ArrayList<String> list5 = new ArrayList();

        public DemoAdapter() {
            addItem(new RvItem(R.layout.list_item_header));
            addItem(new RvItem(list1,R.layout.list_item1));
            addItem(new RvItem(R.layout.list_item2));
            addItem(new RvItem(R.layout.list_item3));
            addItem(new RvItem(R.layout.list_item4));
            addItem(new RvItem(list5,R.layout.list_item5));
            addItem(new RvItem(R.layout.list_item_footer));
            initData();
        }

        private void initData() {
            for (int i = 0; i < 5; i++) {
                list1.add("type1 position"+i);
            }

            for (int i = 0; i < 25; i++) {
                list5.add("type5 position"+i);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewTypeId) {
            if(viewTypeId==R.layout.list_item_header){
                return new HeaderViewHolder(parent,viewTypeId);
            }
            return super.onCreateViewHolder(parent, viewTypeId);
        }

        @Override
        public void onBindView(BaseItemHolder baseHolder, RvItem rvItem, int position, int layoutPosition) {
            switch (baseHolder.getItemViewType()){
                case R.layout.list_item1:
                    baseHolder.setText(R.id.textView,list1.get(position));
                    break;
                case R.layout.list_item5:
                    baseHolder.setText(R.id.textView,list5.get(position));
                    break;
            }
        }
    }
}
