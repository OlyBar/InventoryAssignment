package com.appsbyolympia.olympiabardis.inventoryassignment;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.support.design.widget.FloatingActionButton;

import com.appsbyolympia.olympiabardis.inventoryassignment.data.InventoryDbHelper;
import com.appsbyolympia.olympiabardis.inventoryassignment.data.StockItem;

import static com.appsbyolympia.olympiabardis.inventoryassignment.R.id.fab;

public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = MainActivity.class.getCanonicalName();
    InventoryDbHelper dbHelper;
    StockCursorAdapter adapter;
    int lastVisibleItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new InventoryDbHelper(this);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });

        final ListView listView = (ListView) findViewById(R.id.list_view);
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);

        Cursor cursor = dbHelper.readStock();

        adapter = new StockCursorAdapter(this, cursor);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState){
                if(scrollState == 0) return;
                final int currentFirstVisibleItem = view.getFirstVisiblePosition();
                if (currentFirstVisibleItem > lastVisibleItem){
                    fab.show();
                } else if (currentFirstVisibleItem < lastVisibleItem){
                    fab.hide();
                }
                lastVisibleItem = currentFirstVisibleItem;
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount){
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        adapter.swapCursor(dbHelper.readStock());
    }

    public void clickOnViewItem(long id){
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("itemId", id);
        startActivity(intent);
    }

    public void onClickOnSale(long id, int quantity){
        dbHelper.sellOneItem(id, quantity);
        adapter.swapCursor(dbHelper.readStock());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_add_dummy_data:
                addDummyData();
                adapter.swapCursor(dbHelper.readStock());
        }
        return super.onOptionsItemSelected(item);
    }
        private void addDummyData() {
            StockItem buttercup = new StockItem(
                    "buttercup",
                    "$ 6",
                    6,
                    "Ditmars Flowers",
                    "718-777-8888",
                    "ditmars@flowers.com",
                    "android.resource://com.appsbyolympia.olympiabardis.inventoryassignment/drawable/buttercup");
                dbHelper.insertItem(buttercup);

            StockItem carnation = new StockItem(
                    "carnation",
                    "$ 7",
                    12,
                    "Ditmars Flowers",
                    "718-777-8888",
                    "ditmars@flowers.com",
                    "android.resource://com.appsbyolympia.olympiabardis.inventoryassignment/drawable/carnation");
                dbHelper.insertItem(carnation);

            StockItem daisy = new StockItem(
                    "daisy",
                    "$ 8",
                    18,
                    "Ditmars Flowers",
                    "718-777-8888",
                    "ditmars@flowers.com",
                    "android.resource://com.appsbyolympia.olympiabardis.inventoryassignment/drawable/daisy");
                dbHelper.insertItem(daisy);

            StockItem gardenia = new StockItem(
                    "gardenia",
                    "$ 9",
                    24,
                    "Ditmars Flowers",
                    "718-777-8888",
                    "ditmars@flowers.com",
                    "android.resource://com.appsbyolympia.olympiabardis.inventoryassignment/drawable/gardenia");
            dbHelper.insertItem(gardenia);

            StockItem lilly = new StockItem(
                    "lilly",
                    "$ 10",
                    30,
                    "Ditmars Flowers",
                    "718-777-8888",
                    "ditmars@flowers.com",
                    "android.resource://com.appsbyolympia.olympiabardis.inventoryassignment/drawable/lilly");
            dbHelper.insertItem(lilly);

            StockItem orchid = new StockItem(
                    "orchid",
                    "$ 11",
                    36,
                    "Ditmars Flowers",
                    "718-777-8888",
                    "ditmars@flowers.com",
                    "android.resource://com.appsbyolympia.olympiabardis.inventoryassignment/drawable/orchid");
            dbHelper.insertItem(orchid);

            StockItem peony = new StockItem(
                    "peony",
                    "$ 12",
                    42,
                    "Ditmars Flowers",
                    "718-777-8888",
                    "ditmars@flowers.com",
                    "android.resource://com.appsbyolympia.olympiabardis.inventoryassignment/drawable/peony");
            dbHelper.insertItem(peony);

            StockItem rose = new StockItem(
                    "rose",
                    "$ 14",
                    48,
                    "Ditmars Flowers",
                    "718-777-8888",
                    "ditmars@flowers.com",
                    "android.resource://com.appsbyolympia.olympiabardis.inventoryassignment/drawable/rose");
            dbHelper.insertItem(rose);

            StockItem sunflower = new StockItem(
                    "sunflower",
                    "$ 15",
                    54,
                    "Ditmars Flowers",
                    "718-777-8888",
                    "ditmars@flowers.com",
                    "android.resource://com.appsbyolympia.olympiabardis.inventoryassignment/drawable/sunflower");
            dbHelper.insertItem(sunflower);

            StockItem tulip = new StockItem(
                    "tulip",
                    "$ 16",
                    60,
                    "Ditmars Flowers",
                    "718-777-8888",
                    "ditmars@flowers.com",
                    "android.resource://com.appsbyolympia.olympiabardis.inventoryassignment/drawable/tulip");
            dbHelper.insertItem(tulip);

        }

}
