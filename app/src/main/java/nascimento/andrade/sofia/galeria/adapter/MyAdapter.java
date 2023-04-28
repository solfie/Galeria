package nascimento.andrade.sofia.galeria.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nascimento.andrade.sofia.galeria.R;
import nascimento.andrade.sofia.galeria.activity.MainActivity;
import nascimento.andrade.sofia.galeria.model.MyItem;

//Criamos a classe Adapter, que constrói e preenche item da lista do RecycleView. Essa classe implementa 3 métodos, o onCreateViewHolder, que cria os elementos de interface para um item.
//Onde os elementos são guardados em uma classe container do tipo ViewHolder.
//O onBindViewHolder, que irá receber o ViewHolder que é criado por onCreateViewHolder e preenche os elementos de UI com os dados do item. E o getItemCount, que informa quantos elementos a lista possui.
public class MyAdapter extends RecyclerView.Adapter {
        MainActivity mainActivity;
        List<MyItem> itens;

        public MyAdapter(MainActivity mainActivity, List<MyItem> itens) {
                this.mainActivity = mainActivity;
                this.itens = itens;
        }

        @NonNull
        @Override
        //O RecycleView mostra os itens na lista
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(mainActivity);
                View v = inflater.inflate(R.layout.item_list, parent, false);
                return new MyViewHolder(v);
        }
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                MyItem myItem = itens.get(position);
                View v = holder.itemView;
                ImageView imvfoto = v.findViewById(R.id.imvPhoto);
                imvfoto.setImageBitmap(myItem.photo);
                TextView tvTitle = v.findViewById(R.id.tvTitle);
                tvTitle.setText(myItem.title);
                TextView tvdesc = v.findViewById(R.id.tvDesc);
                tvdesc.setText(myItem.description);
        }
        @Override
        public int getItemCount() {
                return itens.size();
        }

}
