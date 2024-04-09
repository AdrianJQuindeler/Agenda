package com.example.agenda.ui.activity;

import static com.example.agenda.ui.activity.ConstantesActivity.CHAVE_PESSOA;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.PessoaDAO;
import com.example.agenda.models.Pessoa;

public class FormularioNovaPessoaActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo pessoa";
    private static final String TITULO_APPBAR_EDITAR_ALUNO = "Editar pessoa";
    private Pessoa pessoa;
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final PessoaDAO dao = new PessoaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_nova_pessoa_layout);
        inicializacaoDosCampos();
        carregaPessoa();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_pessoa_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void carregaPessoa() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_PESSOA)){
            setTitle(TITULO_APPBAR_EDITAR_ALUNO);
            pessoa = (Pessoa) dados.getSerializableExtra("pessoa");
            PreencheCampos();
        }
        else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            pessoa = new Pessoa();
        }
    }

    private void PreencheCampos() {
        campoNome.setText(pessoa.getNome());
        campoTelefone.setText(pessoa.getTelefone());
        campoEmail.setText(pessoa.getEmail());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_pessoa_menu_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void finalizaFormulario() {
        atualizapessoa();
        if(pessoa.temIdValido()){
            dao.editar(pessoa);
        }
        else {
            dao.salvar(pessoa);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.formulario_nova_pessoa_campo_nome);
        campoTelefone = findViewById(R.id.formulario_nova_pessoa_campo_telefone);
        campoEmail = findViewById(R.id.formulario_nova_pessoa_campo_email);
    }

    private void atualizapessoa() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        pessoa.setNome(nome);
        pessoa.setTelefone(telefone);
        pessoa.setEmail(email);
    }
}
