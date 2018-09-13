package br.edu.ifsp.sdm.denis.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import br.edu.ifsp.sdm.denis.bingo.Bingo;
import br.edu.ifsp.sdm.denis.bingo.BingoFactory;
import br.edu.ifsp.sdm.denis.bingo.R;
import br.edu.ifsp.sdm.denis.bingo.TipoBingoEnum;

public class MainActivity extends AppCompatActivity {

    private Bingo bingo;

    //Referências para os componentes visuais no arquivo de leiaute
    private TextView pedraLabel;
    private TextView totalLabel;
    private ListView pedrasListView;
    private Button sortearButton;

    //Precisamos de um adapter para a ListView
    private ArrayAdapter<Integer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pedraLabel = findViewById(R.id.pedraLabel);
        totalLabel = findViewById(R.id.totalLabel);
        pedrasListView = findViewById(R.id.pedrasListView);
        sortearButton = findViewById(R.id.sortearButton);
    }

    public void sortear(View view) {
        pedraLabel.setText(String.valueOf(bingo.sortearPedra()));
        totalLabel.setText(String.format(Locale.getDefault(),"Total de pedras: %d",bingo.getQuantidadePedrasSorteadas()));

        if(bingo.sorteouTodas()) {
            sortearButton.setEnabled(false);
            Toast.makeText(this, "O jogo já foi finalizado!", Toast.LENGTH_LONG).show();
        }
        adapter.notifyDataSetChanged();
    }

    public void criarJogo(View view) {
        bingo = new BingoFactory().criarBingo(TipoBingoEnum.BINGO_75); //Poderíamos obter do usuário o tipo de bingo
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bingo.getPedrasSorteadas());
        pedrasListView.setAdapter(adapter);
        totalLabel.setText(String.format(Locale.getDefault(),"Pedras sorteadas: %d",bingo.getQuantidadePedrasSorteadas()));
        pedraLabel.setText(null);
        sortearButton.setEnabled(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("bingo", bingo);
        outState.putBoolean("sortearButton", sortearButton.isEnabled());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        bingo = (Bingo)savedInstanceState.getSerializable("bingo");
        sortearButton.setEnabled(savedInstanceState.getBoolean("sortearButton"));
        if(bingo != null) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bingo.getPedrasSorteadas());
            pedrasListView.setAdapter(adapter);
            pedraLabel.setText(bingo.getUltimaPedra()==null? null: String.valueOf(bingo.getUltimaPedra()));
            totalLabel.setText(String.format(Locale.getDefault(),"Total de pedras: %d",bingo.getQuantidadePedrasSorteadas()));
        }
    }
}
