package sophia.com.ecommerce2;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sophia.com.ecommerce2.adapter.CategoryAdapter;
import sophia.com.ecommerce2.adapter.OnAdapterItemClickListener;
import sophia.com.ecommerce2.adapter.ProductAdapter;
import sophia.com.ecommerce2.model.Product;

public class ProductListActivity extends AppCompatActivity implements OnAdapterItemClickListener {

    private RecyclerView productRecycleView;
    private List<Product> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        productRecycleView = (RecyclerView)findViewById(R.id.product_recycle_view);


        GridLayoutManager layout = new GridLayoutManager(this, 1);
        productRecycleView.setLayoutManager(layout);

        for (int i = 0; i < 20; i++){
            productList.add(new Product("https://www.money.it/local/cache-vignettes/L600xH377/immagini_buon_compleanno_amore_frasi_auguri_di_buon_compleanno_2_whatsapp-eb1fb.jpg?1495219739", "title", "subtitle", "10 euro"));


        }

        ProductAdapter productAdapter = new ProductAdapter(productList, this);
        productRecycleView.setAdapter(productAdapter);
    }

    @Override
    public void OnItemClick(int position) {
        Log.d("ProductListActivity","è stato cliccato un elemento");
    }

    @Override
    public void OnItemAddToCart(int position){
        Log.d("ProductListActivity","è stato cliccato il bottone AddToCart");

    }

    @Override
    public void OnItemAddToFavorites(int position) {
        Log.d("ProductListActivity", "è stato cliccato il bottone AddToFavorites");


        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.product_recycle_view),
                "Aggiunto", Snackbar.LENGTH_LONG);

        mySnackbar.setAction("Anulla", new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("ProductListActivity", "e stato anullato");
            }
        });

        mySnackbar.show();
    }
}

