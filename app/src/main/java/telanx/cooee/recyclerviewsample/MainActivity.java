package telanx.cooee.recyclerviewsample;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import telanx.cooee.recyclerviewsample.adapter.StringAdapter;
import telanx.cooee.recyclerviewsample.itemdecoration.Line;


public class MainActivity extends ActionBarActivity
{

    private RecyclerView recyclerView ;
    private ArrayList<String> datas;
    private RecyclerView.LayoutManager layoutManager ;
    private RecyclerView.Adapter adapter ;
    private ArrayList<RecyclerView.ItemDecoration> itemDecorations = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init() ;

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        int itemDecorationNum = itemDecorations.size() ;
        for (int i = 0 ; i < itemDecorationNum ; i++){
            recyclerView.addItemDecoration(itemDecorations.get(i));
        }
    }

    private void init()
    {
        initData();

        layoutManager = new LinearLayoutManager(this) ;
        adapter = new StringAdapter(this , datas) ;
        itemDecorations.add(new Line(4 , Color.BLUE).setMarginLeft(10).setMarginRight(10)) ;
    }


    protected void initData()
    {
        datas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            datas.add("" + (char) i);
        }
    }
}
