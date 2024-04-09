package com.example.agenda;

import android.app.Application;

import com.example.agenda.dao.PessoaDAO;
import com.example.agenda.models.Pessoa;

public class agendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        criaPessoasDeTeste();
    }
    
    private void criaPessoasDeTeste() {
        PessoaDAO dao = new PessoaDAO();
        dao.salvar(new Pessoa(  "Fulano", "27 677778888", "FulanoDeTal@Gmail.com"));
        dao.salvar(new Pessoa("Beltrano", "27 788889999", "Beltrano@Gmail.com"));
        dao.salvar(new Pessoa( "Sicrano", "27 899991010", "Sicrano@Gmail.com"));
    }
}
