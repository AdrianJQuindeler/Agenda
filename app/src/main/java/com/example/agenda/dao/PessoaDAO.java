package com.example.agenda.dao;

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
        Pessoa pessoaEncontrada = null;
        for(Pessoa a : pessoas){
            if(a.getId() == pessoa.getId()){
                pessoaEncontrada = a;
            }
            if(pessoaEncontrada != null){
                int position = pessoas.indexOf(pessoaEncontrada);
                pessoas.set(position, pessoa);
            }
        }
    }
    public List<Pessoa> todos() { return pessoas; }
}
