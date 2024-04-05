package com.example.viagens.ui.activity;

import static com.example.viagens.ui.activity.PacoteActivityConstantes.CHAVE_PACOTE;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.viagens.R;
import com.example.viagens.ui.model.Pacote;
import com.example.viagens.util.DataUtil;
import com.example.viagens.util.MoedaUtil;
import com.example.viagens.util.ResourcesUtil;

public class FimDaCompraActivity extends AppCompatActivity {

    public static final String TITLE = "Resumo da compra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim_da_compra);
        setTitle(TITLE);

        carregaPacote();
    }

    private void carregaPacote() {
        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_PACOTE)) {
            final Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);

            iniciaCampos(pacote);
        }
    }

    private void iniciaCampos(Pacote pacote) {
        mostraLocal(pacote);
        mostraImagem(pacote);
        mostraData(pacote);
        mostraPreco(pacote);
    }

    private void mostraPreco(Pacote pacote) {
        TextView preco = findViewById(R.id.fim_da_compra_preco);
        String moedaBrasileira = MoedaUtil.formataMoedaBrasileira(pacote.getPreco());
        preco.setText(moedaBrasileira);
    }

    private void mostraData(Pacote pacote) {
        TextView data = findViewById(R.id.fim_da_compra_data);
        String periodo = DataUtil.periodoEmTexto(pacote);
        data.setText(periodo);
    }

    private void mostraImagem(Pacote pacote) {
        ImageView imagem = findViewById(R.id.fim_da_compra_imagem_pacote);
        Drawable drawableDoPacote = ResourcesUtil.imagemDrawable(this, pacote.getImagem());
        imagem.setImageDrawable(drawableDoPacote);
    }

    private void mostraLocal(Pacote pacote) {
        TextView local = findViewById(R.id.fim_da_compra_local);
        local.setText(pacote.getLocal());
    }
}