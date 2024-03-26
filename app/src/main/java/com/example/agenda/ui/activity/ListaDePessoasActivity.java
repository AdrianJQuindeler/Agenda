package com.example.agenda.ui.activity;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_PESSOA;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.PessoaDAO;
import com.example.agenda.models.Pessoa;

public class ListaDePessoasActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Pessoas";
    private final PessoaDAO dao = new PessoaDAO();
    private ArrayAdapter<Pessoa> adapter;

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
        botaoNovoPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioModoInserirAluno();
            }
        });
    }

    private void abreFormularioModoInserirAluno() {
        startActivity(new Intent(this, FormularioNovaPessoaActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaPessoas();
    }

    private void atualizaPessoas() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configuraLista() {
        ListView listaDePessoas = findViewById(R.id.lista_de_pessoas_listview);
        configuraAdapter(listaDePessoas);
        configuraListenerDeCliquePorItem(listaDePessoas);
        registerForContextMenu(listaDePessoas);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        CharSequence title = item.getTitle();
        if(title.equals(R.id.activity_lista_pessoas_menu_remover)){
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Pessoa pessoaClicada = adapter.getItem(menuInfo.position);
            adapter.remove(pessoaClicada);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraListenerDeCliquePorItem(ListView listaDePessoas) {
        listaDePessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pessoa pessoaClicada = (Pessoa) parent.getItemAtPosition(position);
                Toast.makeText(ListaDePessoasActivity.this, ""+ pessoaClicada, Toast.LENGTH_SHORT).show();
                AbreFormilarioModoEditaAluno(pessoaClicada);
            }

            private void AbreFormilarioModoEditaAluno(Pessoa pessoaEscolhida) {
                Intent vaiParaOFormulario = new Intent(ListaDePessoasActivity.this, FormularioNovaPessoaActivity.class);
                vaiParaOFormulario.putExtra(CHAVE_PESSOA, pessoaEscolhida);
                startActivity(vaiParaOFormulario);
            }
        });
    }

    private void configuraAdapter(ListView listaDePessoas) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1);
        listaDePessoas.setAdapter(adapter);
    }
}
