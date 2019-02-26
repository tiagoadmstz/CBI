/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.odbc;

import br.com.fs.api.util.CastFactory;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tiago
 */
public class AccessUtil {

    private final ConnectionOdbc connection;

    public AccessUtil(ConnectionOdbc connection) {
        this.connection = connection;
    }

    public ResultSet getResultSet(String sql) {
        try {
            return connection.getSimpleResultSet(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<?> getObjectList(String sql, Class<?> clazz, Map<String, String> map) {
        try {
            ResultSet rs = connection.getSimpleResultSet(sql);
            List<Object> lista = new ArrayList();
            while (rs.next()) {
                Object ob = clazz.getConstructor().newInstance();
                Field[] fields = ob.getClass().getDeclaredFields();
                for (Field f : fields) {
                    if (!f.getName().equals("serialVersionUID") && !f.getName().equals("version")) {
                        f.setAccessible(true);
                        String campo = map.get(f.getName());
                        if (campo != null) {
                            f.set(ob, CastFactory.cast(rs.getObject(map.get(f.getName())), f.getType()));
                        }
                    }
                }
                lista.add(ob);
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*public synchronized void registrarMarcas() {
    try {
    ResultSet rs = access.getSimpleResultSet("SELECT DISTINCT Marca FROM Marcas");
    while (rs.next()) {
    Marca marca = new Marca();
    marca.setMarca(rs.getString(1));
    emh.getOperation(EntityManagerHelper.SALVAR, marca, EntityManagerHelper.DERBYDB_PU);
    }
    } catch (Exception ex) {
    ex.printStackTrace();
    }
    }
    
    public synchronized void registrarModelos() {
    try {
    ResultSet rs = access.getSimpleResultSet("SELECT DISTINCT Modelo FROM DADOS DE BOBINAGEM");
    while (rs.next()) {
    Modelo modelo = new Modelo();
    modelo.setModelo(rs.getString(1));
    emh.getOperation(EntityManagerHelper.SALVAR, modelo, EntityManagerHelper.DERBYDB_PU);
    }
    } catch (Exception ex) {
    ex.printStackTrace();
    }
    }
    
    public synchronized void registrarCategorias() {
    try {
    ResultSet rs = access.getSimpleResultSet("SELECT DISTINCT Categoria FROM DADOS DE BOBINAGEM");
    while (rs.next()) {
    Categoria categoria = new Categoria();
    categoria.setCategoria(rs.getString(1));
    emh.getOperation(EntityManagerHelper.SALVAR, categoria, EntityManagerHelper.DERBYDB_PU);
    }
    } catch (Exception ex) {
    ex.printStackTrace();
    }
    }
    
    public synchronized void registrarCamadas() {
    try {
    ResultSet rs = access.getSimpleResultSet("SELECT DISTINCT Camada FROM DADOS DE BOBINAGEM");
    while (rs.next()) {
    Camada camada = new Camada();
    camada.setCamada(rs.getString(1));
    emh.getOperation(EntityManagerHelper.SALVAR, camada, EntityManagerHelper.DERBYDB_PU);
    }
    } catch (Exception ex) {
    ex.printStackTrace();
    }
    }
    
    public synchronized void registrarClientes() {
    try {
    ResultSet rs = access.getSimpleResultSet("SELECT DISTINCT Clientes FROM DADOS DE BOBINAGEM");
    while (rs.next()) {
    Entidade cliente = new Entidade();
    cliente.setRazao_social(rs.getString(1));
    cliente.setNome_fantasia(rs.getString(1));
    cliente.setCliente(true);
    cliente.setFornecedor(false);
    cliente.setEmpresa(false);
    cliente.setAtivo(true);
    emh.getOperation(EntityManagerHelper.SALVAR, cliente, EntityManagerHelper.DERBYDB_PU);
    }
    } catch (Exception ex) {
    ex.printStackTrace();
    }
    }*/
    public synchronized void registrarDadosBobinagem() {
        try {
            /*ResultSet rs = access.getSimpleResultSet("SELECT Comprimento,'Diâmetro Ø',Ranhura,Camada,Passo,Espiras,Fio,seccao"
            + ",Passo Aux,Espira Aux,Fio Aux,seccaoAux,'Peso (Kg)',Ligação,infAdicionais,Causa da Queima"
            + ",'Forma Nº','Calço Nº','Enrolamento de Fábrica'"
            + " FROM DADOS DE BOBINAGEM");*/
            //ResultSet rs = access.getSimpleResultSet("SELECT Comprimento, Diâmetro Ø FROM DADOS DE BOBINAGEM");
            //PreparedStatement ps = access.getConnection().prepareStatement("SELECT ?, ? FROM DADOS DE BOBINAGEM");
            //ps.setObject(1, "Comprimento");
            //ps.setObject(2, "Diâmetro Ø");
            //ResultSet rs = ps.executeQuery();

            //while (rs.next()) {
            //Dados_Bobinagem dados_bobinagem = new Dados_Bobinagem();
            //dados_bobinagem.setComprimento(rs.getString(1));
            //dados_bobinagem.setDiametro(rs.getString(2));
            //dados_bobinagem.setRanhura(rs.getString(3));
            //dados_bobinagem.setCamada((Camada) emh.getObjectNamedQuery(Camada.class, "camada.findByCamada", "paramCamda", rs.getString(4), EntityManagerHelper.DERBYDB_PU).orElse(null));
            //dados_bobinagem.setPasso(rs.getString(5));
            //dados_bobinagem.setEspiras(rs.getString(6));
            //dados_bobinagem.setFio(rs.getString(7));
            //dados_bobinagem.setSeccao(rs.getBigDecimal(8));
            //dados_bobinagem.setPasso_aux(rs.getString(9));
            //dados_bobinagem.setEspira_aux(rs.getString(10));
            //dados_bobinagem.setFio_aux(rs.getString(11));
            //dados_bobinagem.setSeccao_aux(rs.getBigDecimal(12));
            //dados_bobinagem.setPeso(rs.getBigDecimal(13));
            //dados_bobinagem.setLigacao(rs.getString(14));
            //dados_bobinagem.setInformacoes_adicionais(rs.getString(15));
            //dados_bobinagem.setCausa_queima(rs.getString(16));
            //dados_bobinagem.setForma(rs.getString(17));
            //dados_bobinagem.setCalco(rs.getString(18));
            //dados_bobinagem.setEnrolamento_fabrica(rs.getBoolean(19));
            //emh.getOperation(EntityManagerHelper.SALVAR, dados_bobinagem, EntityManagerHelper.DERBYDB_PU);
            //System.out.println(dados_bobinagem);
            //}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
