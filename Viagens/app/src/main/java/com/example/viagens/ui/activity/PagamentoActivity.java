package com.example.viagens.ui.activity;

import static com.example.viagens.ui.activity.PacoteActivityConstantes.CHAVE_PACOTE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.viagens.R;
import com.example.viagens.ui.model.Pacote;
import com.example.viagens.util.MoedaUtil;


public class PagamentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        setTitle("Pagamento");

        carregaPacote();
    }

    private void carregaPacote() {
        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_PACOTE)) {
            final Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);

            mostraPreco(pacote);
            configuraBotao(pacote);
        }
    }

    private void configuraBotao(Pacote pacote) {
        Button finalizaCompra = findViewById(R.id.pagamento_finaliza_compra);
        finalizaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaiParaResumo(pacote);
            }
        });
    }

    private void vaiParaResumo(Pacote pacote) {
        Intent intent = new Intent(PagamentoActivity.this, FimDaCompraActivity.class);
        intent.putExtra(CHAVE_PACOTE, pacote);
        startActivity(intent);
    }

    private void mostraPreco(Pacote pacoteSaoPaulo) {
        TextView preco = findViewById(R.id.pagamento_preco_pacote);
        String moedaBrasileira = MoedaUtil.formataMoedaBrasileira(pacoteSaoPaulo.getPreco());
        preco.setText(moedaBrasileira);
    }
}