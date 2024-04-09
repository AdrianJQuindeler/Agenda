package com.example.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.agenda.dao.PessoaDAO;
import com.example.agenda.models.Pessoa;
import com.example.agenda.ui.activity.ListaDePessoasActivity;
import com.example.agenda.ui.adapter.ListaPessoasAdapter;

public class ListaPessoasView {
    private final Context context;
    private final PessoaDAO dao;
    private final ListaPessoasAdapter adapter;

    public ListaPessoasView(Context context) {
        this.context = context;
        this.adapter = new ListaPessoasAdapter(context);
        this.dao = new PessoaDAO();
    }

    public void confirmarRemocao(MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Excluir Contato")
                .setMessage("Tem certeza que deseja apagar este contato?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    assert menuInfo != null;
                    Pessoa pessoaEscolhida = adapter.getItem(menuInfo.position);
                    remove(pessoaEscolhida);
                })
                .setNegativeButton("Não", null)
                .show();
    }
    public void atualizaPessoas() {
        adapter.atualiza(dao.todos());
    }

    private void remove(Pessoa pessoa) {
        dao.remove(pessoa);
        adapter.remove(pessoa);
    }

    public void configuraAdapter(ListView listaDePessoas) {
        listaDePessoas.setAdapter(adapter);
        atualizaPessoas();
    }
}
