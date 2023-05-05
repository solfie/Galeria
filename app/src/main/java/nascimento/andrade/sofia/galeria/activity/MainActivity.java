package nascimento.andrade.sofia.galeria.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.NinePatch;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import nascimento.andrade.sofia.galeria.R;
import nascimento.andrade.sofia.galeria.adapter.MyAdapter;
import nascimento.andrade.sofia.galeria.model.MainActivityViewModel;
import nascimento.andrade.sofia.galeria.model.MyItem;
import nascimento.andrade.sofia.galeria.util.Util;


//Nesse código o app abrirá a galria do celular e pedirá que o usuário escolha uma foto, que será enviada para a NewItemActivity. A NewItemActivity exibe a imagem na ImageView.
public class MainActivity extends AppCompatActivity {
    static int NEW_ITEM_REQUEST = 1;


    MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvItens = findViewById(R.id.rvItens);

        MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        List<MyItem> itens = vm.getItens();

        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);
        myAdapter = new MyAdapter(this, itens);
        rvItens.setAdapter(myAdapter);
        rvItens.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);
        rvItens.addItemDecoration(dividerItemDecoration);

        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);
                startActivityForResult(i, NEW_ITEM_REQUEST);

            }
        });
    }
    // Recebe dados de retorno de uma activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_ITEM_REQUEST) {
            if(resultCode == Activity.RESULT_OK) {
                MyItem myItem = new MyItem();
                myItem.title = data.getStringExtra("title");
                MyItem.description = data.getStringExtra("description");
                Uri selectedPhotoURI = data.getData();
                try {
                    Bitmap photo = Util.getBitmap( MainActivity.this, selectedPhotoURI, 100, 100 );
                    myItem.photo = photo;


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                MainActivityViewModel vm = new ViewModelProvider( this ).get(MainActivityViewModel.class );
                List<MyItem> itens = vm.getItens();

                itens.add(myItem);
                myAdapter.notifyItemInserted(itens.size()-1);
                }
            }
        }
}