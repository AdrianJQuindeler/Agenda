package com.example.agenda.dao;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import com.example.agenda.models.Pessoa;

public class PessoaDAO {
    private final static List<Pessoa> pessoas = new ArrayList<>();
    private static int contaorDeIds = 1;
    public void salvar(Pessoa pessoa) {
        pessoa.setId(contaorDeIds);
        pessoas.add(pessoa);
        contaorDeIds++;
    }

    public void editar(Pessoa pessoa){
        Pessoa pessoaEncontrada = buscaPessoaPorID(pessoa);
        if(pessoaEncontrada != null){
                int position = pessoas.indexOf(pessoaEncontrada);
                pessoas.set(position, pessoa);
            }
        }

    @Nullable
    private static Pessoa buscaPessoaPorID(Pessoa pessoa) {
        for(Pessoa a : pessoas) {
            if (a.getId() == pessoa.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Pessoa> todos() { return pessoas; }
}
