package cat.copernic.rodriguez.albert;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity { //Codi per l'activitat concreta
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView mountainsTitle = findViewById(R.id.titleDetail);
        ImageView mountainsImage = findViewById(R.id.mountainsImageDetail);
        mountainsTitle.setText(getIntent().getStringExtra("title"));
        //Codi per les imatges
        Glide.with(this).load(getIntent().getIntExtra("image_resource",0))
                .into(mountainsImage);
    }
}