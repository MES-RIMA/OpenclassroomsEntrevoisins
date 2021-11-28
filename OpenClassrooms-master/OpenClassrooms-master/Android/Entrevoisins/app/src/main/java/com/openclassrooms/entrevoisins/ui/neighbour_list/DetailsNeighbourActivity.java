package com.openclassrooms.entrevoisins.ui.neighbour_list;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class DetailsNeighbourActivity extends AppCompatActivity {
    private ImageView img;
    private TextView nom, addlocal, tel, mail, title, description;

    private FloatingActionButton addfav;
    private NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_neighbour);

        mApiService = DI.getNeighbourApiService();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbarlayout);


        img = (ImageView) findViewById(R.id.avatar);
        nom = (TextView) findViewById(R.id.name);
        addlocal = (TextView) findViewById(R.id.address);
        tel = (TextView) findViewById(R.id.number_phone);
        mail = (TextView) findViewById(R.id.reseau_sociaux);
        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.aboutMe);
        addfav = findViewById(R.id.add_fav);
        Neighbour neighbour;
        if (getIntent().getExtras() != null) {
            neighbour = getIntent().getExtras().getParcelable("myNeighbour");

            Glide.with(this)
                    .load(neighbour.getAvatarUrl())
                    .into(img);

            toolBarLayout.setTitle(neighbour.getName());
            nom.setText(neighbour.getName());
            addlocal.setText(neighbour.getAddress());
            tel.setText(neighbour.getPhoneNumber());
            description.setText(neighbour.getAboutMe());
            mail.setText("www.facebook/" + neighbour.getName());
            title.setText("A propos de moi");

            if (neighbour.isFavorite()) {
                addfav.setImageResource(R.drawable.ic_star_white_24dp);
            } else {
                addfav.setImageResource(R.drawable.ic_star_border_white_24dp);
            }

           addfav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (neighbour.isFavorite()) {
                        addfav.setImageResource(R.drawable.ic_star_border_white_24dp);
                    } else {
                       addfav.setImageResource(R.drawable.ic_star_white_24dp);

                    }
                    neighbour.setFavorite(!neighbour.isFavorite());
                    mApiService.modifyNeighbour(neighbour);
                }
            });
        }
    }

}