/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.odbc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class Testes {

    public static void main(String[] args) {
        try {
            Testes t = new Testes();
            List<Aluno> alunos = new ArrayList();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            Aluno aluno = t.new Aluno();
            aluno.setNome("Wiz Costa");
            aluno.setDataNascimento(sdf.parse("01/01/2018"));
            alunos.add(aluno);

            for (Aluno a : alunos) {
                System.out.println("Nome do aluno: " + a.getNome());
                System.out.println("Data de nascimento do aluno: " + sdf.format(a.getDataNascimento()));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public class Aluno {

        private String nome;
        private Date dataNascimento;

        public Aluno() {
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }

        public Date getDataNascimento() {
            return dataNascimento;
        }

        public void setDataNascimento(Date dataNascimento) {
            this.dataNascimento = dataNascimento;
        }

    }

}
