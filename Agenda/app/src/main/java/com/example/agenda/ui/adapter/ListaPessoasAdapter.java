package com.example.agenda.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.models.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class ListaPessoasAdapter extends BaseAdapter {
    private final List<Pessoa> pessoas = new ArrayList<>();
    private final Context context;

    public ListaPessoasAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return pessoas.size();
    }

    @Override
    public Pessoa getItem(int position) {
        return pessoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pessoas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_pessoa, parent, false);
        Pessoa pessoaDevolvida = pessoas.get(position);
        mostraNomeENumero(viewCriada, pessoaDevolvida);
        return viewCriada;
    }

    private void mostraNomeENumero(View viewCriada, Pessoa pessoaDevolvida) {
        TextView nomePessoa = viewCriada.findViewById(R.id.item_pessoa_nome);
        nomePessoa.setText(pessoaDevolvida.getNome());

        TextView telefonePessoa = viewCriada.findViewById(R.id.item_pessoa_telefone);
        telefonePessoa.setText(pessoaDevolvida.getTelefone());
    }

    public void atualiza(List<Pessoa> pessoas){
        this.pessoas.clear();
        this.pessoas.addAll(pessoas);
        notifyDataSetChanged();
    }
    public void remove(Pessoa pessoa) {
        pessoas.remove(pessoa);
        notifyDataSetChanged();
    }
}
