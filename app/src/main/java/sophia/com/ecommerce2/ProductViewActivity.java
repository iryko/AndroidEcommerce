package sophia.com.ecommerce2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ProductViewActivity extends AppCompatActivity {
    private ImageView img_product;
    private TextView title;
    private RatingBar rating;
    private TextView price;
    private TextView description;
    private Button button;
    private Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        img_product = (ImageView)findViewById(R.id.img_product);
        title = (TextView)findViewById(R.id.title);
        rating = (RatingBar)findViewById(R.id.rating);
        price = (TextView)findViewById(R.id.price);
        description = (TextView)findViewById(R.id.description);
        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);

    }
}
