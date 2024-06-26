package com.example.agenda.ui.activity;

import static com.example.agenda.ui.activity.ConstantesActivity.CHAVE_PESSOA;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.models.Pessoa;
import com.example.agenda.ui.ListaPessoasView;


public class ListaDePessoasActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Pessoas";
    private final ListaPessoasView listaPessoasView = new ListaPessoasView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_de_pessoas_layout);
        setTitle(TITULO_APPBAR);
        configuraBotaoNovaPessoa();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_pessoas_menu, menu);
    }

    private void configuraBotaoNovaPessoa() {
        Button botaoNovoPessoa = findViewById(R.id.lista_de_pessoas_botao_adicionar);
        botaoNovoPessoa.setOnClickListener(view -> abreFormularioModoInserirAluno());
    }

    private void abreFormularioModoInserirAluno() {
        startActivity(new Intent(this, FormularioNovaPessoaActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaPessoasView.atualizaPessoas();
    }

    private void configuraLista() {
        ListView listaDePessoas = findViewById(R.id.lista_de_pessoas_listview);
        listaPessoasView.configuraAdapter(listaDePessoas);
        configuraListenerDeCliquePorItem(listaDePessoas);
        registerForContextMenu(listaDePessoas);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_pessoas_menu_remover) {
            listaPessoasView.confirmarRemocao(item);
        }

        return super.onContextItemSelected(item);
    }

    private void configuraListenerDeCliquePorItem(ListView listaDePessoas) {
        listaDePessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pessoa pessoaClicada = (Pessoa) parent.getItemAtPosition(position);
                Toast.makeText(ListaDePessoasActivity.this, ""+ pessoaClicada, Toast.LENGTH_SHORT).show();
                AbreFormularioModoEditaPessoa(pessoaClicada);
            }

            private void AbreFormularioModoEditaPessoa(Pessoa pessoaEscolhida) {
                Intent vaiParaOFormulario = new Intent(ListaDePessoasActivity.this, FormularioNovaPessoaActivity.class);
                vaiParaOFormulario.putExtra(CHAVE_PESSOA, pessoaEscolhida);
                startActivity(vaiParaOFormulario);
            }
        });
    }
}
