package com.example.desafiomarvel.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desafiomarvel.R;
import com.example.desafiomarvel.model.Date;
import com.example.desafiomarvel.model.Price;
import com.example.desafiomarvel.model.Result;
import com.squareup.picasso.Picasso;

public class QuadrinhoDetalheActivity extends AppCompatActivity {
    private ImageView imagemDestaque;
    private ImageView imagemHQ;
    private TextView titleHQ;
    private TextView descHQ;
    private TextView publicHQ;
    private TextView priceHQ;
    private TextView pagesHQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadrinho_detalhe);
        initViews();
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_action_back);

        toolbar.setNavigationOnClickListener(view ->{
            finish();
        });

        Intent intent = getIntent();
        if(intent != null){
            Bundle bundle = intent.getExtras();
            Result result = bundle.getParcelable("chave");

            Picasso.get()
                    .load(result.getThumbnail().getPath() + ".jpg").into(imagemDestaque);
            Picasso.get()
                    .load(result.getThumbnail().getPath()+ ".jpg").into(imagemHQ);
            titleHQ.setText(result.getTitle());
            descHQ.setText(result.getDescription());
            publicHQ.setText("Publicado em: " + result.getDates().get(0).getDate());
            priceHQ.setText("Preço: R$"+ result.getPrices().get(0).getPrice());
            pagesHQ.setText("Páginas: " + result.getPageCount());

        }
    }

    public void initViews(){
        imagemDestaque = findViewById(R.id.imagemDestaqueHQ);
        imagemHQ = findViewById(R.id.imagemHQ);
        titleHQ = findViewById(R.id.tituloHQ);
        descHQ = findViewById(R.id.descHQ);
        publicHQ = findViewById(R.id.publiHQ);
        priceHQ = findViewById(R.id.priceHQ);
        pagesHQ = findViewById(R.id.pagesHQ);
    }

}
