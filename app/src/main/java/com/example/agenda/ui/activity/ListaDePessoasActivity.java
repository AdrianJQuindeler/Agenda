package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.PessoaDAO;
import com.example.agenda.models.Pessoa;

import java.util.List;

public class ListaDePessoasActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Pessoas";
    private final PessoaDAO dao = new PessoaDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_de_pessoas_layout);
        setTitle(TITULO_APPBAR);
        configuraFabNovoPessoa();
        dao.salvar(new Pessoa("Adrian Jansen Quindeler", "27 988887777", "AdrianJQuindeler@Gmail.com"));
        dao.salvar(new Pessoa("Mariana Bernardino Ribeiro", "27 911112222", "MarianaBRibeiro@Gmail.com"));

    }

    private void configuraFabNovoPessoa() {
        Button botaoNovoPessoa = findViewById(R.id.lista_de_pessoas_botao_adicionar);
        botaoNovoPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioPessoaActivity();
            }
        });
    }

    private void abreFormularioPessoaActivity() {
        startActivity(new Intent(this, FormularioNovaPessoaActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        ListView listaDePessoas = findViewById(R.id.lista_de_pessoas_listview);
        List<Pessoa> pessoas = dao.todos();
        configuraAdapter(listaDePessoas, pessoas);
        configuraListenerDeCliquePorItem(listaDePessoas);
    }

    private void configuraListenerDeCliquePorItem(ListView listaDePessoas) {
        listaDePessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pessoa pessoaEscolhida = (Pessoa) parent.getItemAtPosition(position);
                Toast.makeText(ListaDePessoasActivity.this, ""+ pessoaEscolhida, Toast.LENGTH_SHORT).show();
                Intent vaiParaOFormulario = new Intent(ListaDePessoasActivity.this, FormularioNovaPessoaActivity.class);
                vaiParaOFormulario.putExtra("pessoa", pessoaEscolhida);
                startActivity(vaiParaOFormulario);
            }
        });
    }

    private void configuraAdapter(ListView listaDePessoas, List<Pessoa> pessoas) {
        listaDePessoas.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                pessoas));
    }
}
