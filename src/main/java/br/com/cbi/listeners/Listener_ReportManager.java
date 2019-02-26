/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.listeners;

import br.com.cbi.entities.Cidade;
import br.com.fs.api.dal.EntityManagerHelper;
import br.com.fs.api.frames.Form_ReportManager;
import br.com.fs.api.frames.filtros.SeletorAno;
import br.com.fs.api.frames.filtros.SeletorDataCorte;
import br.com.fs.api.frames.filtros.SeletorDatas;
import br.com.fs.api.frames.filtros.SeletorDias;
import br.com.fs.api.frames.filtros.SeletorHorario;
import br.com.fs.api.frames.filtros.SeletorLista;
import br.com.fs.api.frames.filtros.SeletorListaVarios;
import br.com.fs.api.frames.filtros.SeletorMeses;
import br.com.fs.api.interfaces.ListenerPatternAdapter;
import br.com.fs.api.interfaces.TableModelDefaultAdapter;
import br.com.fs.api.msg.MessageFactory;
import br.com.fs.api.util.ControleInstancias;
import br.com.fs.api.util.ControleUsuario;
import br.com.fs.api.util.JasperUtil;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;

/**
 *
 * @author tiago.teixeira
 */
public final class Listener_ReportManager extends ListenerPatternAdapter<Form_ReportManager> {

    /*private List<SresReport> reports;
    private List<Report_Group> groups;
    private EntityManagerHelper<SresReport> emh;
    private TableModel_ReportGroup groupModel;
    private TableModel_Report reportModel;
    private TableModel_Filtros filtrosModel;*/
    private final Map<String, Object> mapParam = new HashMap();

    public Listener_ReportManager(Form_ReportManager form) {
        super(form);
        initComponents();
    }

    @Override
    protected void initComponents() {
        //emh = new EntityManagerHelper();
        //reports = new ArrayList();
        attachListener();
        carregarListas();
        addModel();
    }

    @Override
    public void attachListener() {
        form.getTbGrupos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    try {
                        //reportModel.setLista(reports.stream().filter(rl -> rl.getGrupo().getGrupo().equals(((Report_Group) groupModel.getObject(form.getTbGrupos().getSelectedRow())).getGrupo())).collect(Collectors.toList()));
                        //filtrosModel.setLista(null);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(form, ex.getMessage(), "Erro ao tentar gerar lista", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        form.getTbReports().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    //filtrosModel.setLista(((SresReport) reportModel.getObject(form.getTbReports().getSelectedRow())).getFiltro());
                }
            }
        });
        form.getTbFiltros().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    form.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    editar();
                    form.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });
        form.getBtEditarFiltro().addActionListener(this);
        form.getBtImprimir().addActionListener(this);
        form.getItemFechar().addActionListener(this);
        fecharESC(form.getItemFechar());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        form.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            switch (e.getActionCommand()) {
                case "fechar":
                    form.fechar();
                case "editar":
                    editar();
                    break;
                case "imprimir":
                    imprimir();
                    break;
                default:
                    MessageFactory.getAppMessage(MessageFactory.EM_DESENVOLVIMENTO, form);
            }
        } catch (Exception ex) {
            form.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            if (!ex.getMessage().equals("")) {
                MessageFactory.getExceptionMessage(ex.getMessage(), form);
            }
        }
        form.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void carregarListas() {
        try {
            /*            reports = ControleUsuario.getUsuarioAtual().getPermissoes().getRelatorios().stream()
            .filter(rel -> rel.getAtivo())
            .sorted((r1, r2) -> r1.getNome().compareTo(r2.getNome()))
            .collect(Collectors.toList());
            groups = reports.stream()
            .map(rel -> rel.getGrupo())
            .distinct().sorted((g1, g2) -> g1.getGrupo().compareTo(g2.getGrupo()))
            .collect(Collectors.toList());*/
            //reports = (List<SresReport>) emh.getObjectListNamedQuery(SresReport.class, "report.findAll", null, null, EntityManagerHelper.DERBYDB_PU).get();
            //groups = (List<Report_Group>) emh.getObjectListNamedQuery(Report_Group.class, "grupo.findAll", null, null, EntityManagerHelper.DERBYDB_PU).get();
        } catch (Exception e) {
        }
    }

    @Override
    public void addModel() {
        /*groupModel = new TableModel_ReportGroup(groups);
        reportModel = new TableModel_Report();
        filtrosModel = new TableModel_Filtros();
        form.getTbGrupos().setModel(groupModel);
        form.getTbReports().setModel(reportModel);
        form.getTbFiltros().setModel(filtrosModel);*/
    }

    @Override
    public void editar() {
        try {
            /*Filtro filtro = (Filtro) filtrosModel.getObject(form.getTbFiltros().getSelectedRow());
            switch (filtro.getTipo()) {
            case "data":
            SeletorDatas datas = (SeletorDatas) ControleInstancias.getInstance(SeletorDatas.class.getName());
            datas.getBtConfirmar().addActionListener((ActionEvent e) -> {
            try {
            if (datas.validarData()) {
            filtro.setValor(datas.getTxtDataInicial().getText() + " à " + datas.getTxtDataFinal().getText());
            if (reportModel.getObject(form.getTbReports().getSelectedRow()).getSigla().equals("REG0005")
            || reportModel.getObject(form.getTbReports().getSelectedRow()).getSigla().equals("RRP0001")
            || reportModel.getObject(form.getTbReports().getSelectedRow()).getSigla().equals("RRP0002")
            || reportModel.getObject(form.getTbReports().getSelectedRow()).getSigla().equals("RAC0001")) {
            mapParam.put("DataInicial", datas.getTxtDataInicial().getText());
            mapParam.put("DataFinal", datas.getTxtDataFinal().getText());
            } else {
            mapParam.put("DataInicial", new Timestamp(datas.getInitDateTimeMillis()));
            mapParam.put("DataFinal", new Timestamp(datas.getEndDateTimeMillis()));
            }
            filtrosModel.fireTableDataChanged();
            datas.dispose();
            } else {
            JOptionPane.showMessageDialog(datas, "Datas inválidas", "Erro ao informar datas", JOptionPane.INFORMATION_MESSAGE);
            }
            } catch (Exception ex) {
            ex.printStackTrace();
            }
            });
            datas.setVisible(true);
            break;
            case "dias":
            SeletorDias dias = (SeletorDias) ControleInstancias.getInstance(SeletorDias.class.getName());
            dias.getBtConfirmar().addActionListener(event -> {
            try {
            filtro.setValor(dias.getDiaInicial() + " a " + dias.getDiaFinal());
            mapParam.put("DIA_INICIAL", dias.getDiaInicial());
            mapParam.put("DIA_FINAL", dias.getDiaFinal());
            filtrosModel.fireTableDataChanged();
            dias.dispose();
            } catch (Exception ex) {
            MessageFactory.getExceptionMessage(ex.getMessage(), dias);
            }
            });
            dias.setVisible(true);
            break;
            case "mes":
            SeletorMeses mes = (SeletorMeses) ControleInstancias.getInstance(SeletorMeses.class.getName());
            mes.getBtConfirmar().addActionListener(event -> {
            try {
            filtro.setValor(mes.getMesInicial() + " a " + mes.getMesFinal());
            mapParam.put("MES_INICIAL", mes.getMesInicial());
            mapParam.put("MES_FINAL", mes.getMesFinal());
            filtrosModel.fireTableDataChanged();
            mes.dispose();
            } catch (Exception ex) {
            MessageFactory.getExceptionMessage(ex.getMessage(), mes);
            }
            });
            mes.setVisible(true);
            break;
            case "ano":
            SeletorAno ano = (SeletorAno) ControleInstancias.getInstance(SeletorAno.class.getName());
            ano.getBtConfirmar().addActionListener(event -> {
            try {
            filtro.setValor(ano.getAno());
            mapParam.put("ANO", ano.getAno());
            filtrosModel.fireTableDataChanged();
            ano.dispose();
            } catch (Exception ex) {
            MessageFactory.getExceptionMessage(ex.getMessage(), ano);
            }
            });
            ano.setVisible(true);
            break;
            case "consulta prestador":
            List<Prestador_Servico> listaPS = (List<Prestador_Servico>) emh.getObjectListNamedQuery(Prestador_Servico.class, "PrestadorServico.findAll", null, null, EntityManagerHelper.ORACLE11G_PU).get();
            mountPesquisa(listaPS, new TableModel_SeletorPrestador(), filtro);
            break;
            case "consulta empresa":
            List<Empresa> listaEp = (List<Empresa>) emh.getObjectListNamedQuery(Empresa.class, "Empresa.findAll", null, null, EntityManagerHelper.ORACLE11G_PU).get();
            mountPesquisa(listaEp, new TableModel_SeletorEmpresa(), filtro);
            break;
            case "cidade":
            List<Cidade> listaCid = (List<Cidade>) emh.getObjectListNamedQuery(Cidade.class, "Cidade.findAll", null, null, EntityManagerHelper.ORACLE11G_PU).get();
            mountPesquisa(listaCid, new TableModel_SeletorCidade(), filtro);
            break;
            case "collection":
            mountSeletorCollection(filtro);
            break;
            case "departamento":
            List<Departamento> listaDpt = (List<Departamento>) emh.getObjectListNamedQuery(Departamento.class, "departamento.findAll", null, null, EntityManagerHelper.ORACLE11G_PU).get();
            List<Departamento> listaFiltrada = listaDpt.stream().filter(dpt -> {
            return dpt.getId().intValue() == 1 || dpt.getId().intValue() == 2 || dpt.getId().intValue() == 5 || dpt.getId().intValue() == 21 || dpt.getId().intValue() == 23
            || dpt.getId().intValue() == 41 || dpt.getId().intValue() == 42 || dpt.getId().intValue() == 47 || dpt.getId().intValue() == 48 || dpt.getId().intValue() == 54;
            }).collect(Collectors.toList());
            mountPesquisa(listaFiltrada, new TableModel_SeletorDepartamento(), filtro);
            break;
            case "dataCorte":
            SeletorDataCorte dataCorte = (SeletorDataCorte) ControleInstancias.getInstance(SeletorDataCorte.class.getName());
            dataCorte.getBtConfirmar().addActionListener(event -> {
            filtro.setValor(dataCorte.getDataCorte());
            mapParam.put("DATA_CORTE", dataCorte.getDataCorte());
            filtrosModel.fireTableDataChanged();
            dataCorte.dispose();
            });
            dataCorte.setVisible(true);
            break;
            case "horas":
            SeletorHorario horario = (SeletorHorario) ControleInstancias.getInstance(SeletorHorario.class.getName());
            horario.getBtConfirmar().addActionListener(event -> {
            filtro.setValor(horario.getHoraInicial() + " à " + horario.getHoraFinal());
            mapParam.put("HORA_INICIO", horario.getHoraInicial());
            mapParam.put("HORA_FIM", horario.getHoraFinal());
            filtrosModel.fireTableDataChanged();
            horario.dispose();
            });
            horario.setVisible(true);
            break;
            case "boolean":
            filtro.setValor(filtro.getValor().equals("SIM") ? "NÃO" : "SIM");
            if (filtro.getNome().equals("Imprimir Gráfico")) {
            mapParam.put("GRAFICO", filtro.getValor().equals("SIM"));
            } else if (filtro.getNome().equals("Detalhar Resultados")) {
            mapParam.put("DETALHAR", filtro.getValor().equals("SIM"));
            }
            filtrosModel.fireTableDataChanged();
            }*/
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(form, e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void imprimir() {
        /*SresReport report = (SresReport) reportModel.getObject(form.getTbReports().getSelectedRow());
        if (report.getTipo().equals("SQL")) {
        imprimirReporSql(report);
        } else if (report.getTipo().equals("BEAN")) {
        imprimirReportBean(report);
        }*/
    }

    //private void imprimirReporSql(SresReport report) {
    private void imprimirReporSql() {
        try {
            /*if (filtrosModel.getRowCount() > 0 && !mapParam.isEmpty()) {
            //System.out.println(report.getPath());
            this.getReportPath(report);
            //System.out.println(report.getPath());
            InputStream input = getClass().getResourceAsStream(report.getPath());
            mapParam.put("SUBREPORT_DIR", getClass().getResource("/br/com/sres/reports/"));
            JasperUtil.imprimirRelatorio(emh.getConnection(EntityManagerHelper.ORACLE11G_PU), report.getNome(), form.getIconImage(), mapParam, input);
            emh.closeAll();
            } else if (filtrosModel.getRowCount() == 0) {
            this.getReportPath(report);
            InputStream input = getClass().getResourceAsStream(report.getPath());
            mapParam.put("SUBREPORT_DIR", getClass().getResource("/br/com/sres/reports/"));
            JasperUtil.imprimirRelatorio(emh.getConnection(EntityManagerHelper.ORACLE11G_PU), report.getNome(), form.getIconImage(), mapParam, input);
            emh.closeAll();
            } else {
            JOptionPane.showMessageDialog(form, "Não há parâmetros para geração do relatório!", "Parâmetros não informados", JOptionPane.INFORMATION_MESSAGE);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(form, "Não foi possível imprimir este ralatório!", "Erro ao tentar imprimir", JOptionPane.ERROR_MESSAGE);
        }
    }

    //private void imprimirReportBean(SresReport report) {
    private void imprimirReportBean() {
        try {
            //InputStream input = getClass().getResourceAsStream(report.getPath());
            //mapParam.put("SUBREPORT_DIR", getClass().getResource("/br/com/sres/reports/"));
            //JasperUtil.imprimirRelatorio(input, report.getNome(), form.getIconImage(), mapParam, getListReportBean(report.getSigla()));
            //emh.closeAll();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(form, "Não foi possível imprimir este ralatório!", "Erro ao tentar imprimir", JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<?> getListReportBean(String sigla) {
        try {
            switch (sigla) {
                case "RAC0001":
                //return Factory_RAC0001.generateCollection((List<String>) mapParam.get("FUNCIONARIOS"), LocalDateTime.of(LocalDate.parse(String.valueOf(mapParam.get("DataInicial")), DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalTime.of(0, 0)), LocalDateTime.of(LocalDate.parse(String.valueOf(mapParam.get("DataFinal")), DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalTime.of(0, 0)));
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    //private void mountPesquisa(List<?> lista, TableModelDefaultAdapter model, Filtro filtro) {
    private void mountPesquisa(List<?> lista, TableModelDefaultAdapter model) {
        SeletorLista prestadores = (SeletorLista) ControleInstancias.getInstance(SeletorLista.class.getName());
        prestadores.setParameters(lista, model, 50, 200);
        prestadores.getTbPrestador().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    /*RowSorter sorter = prestadores.getTbPrestador().getRowSorter();
                    Object object = model.getObject(sorter.convertRowIndexToModel(prestadores.getTbPrestador().getSelectedRow()));
                    if (object instanceof Prestador_Servico) {
                    Prestador_Servico p = (Prestador_Servico) object;
                    filtro.setValor(p.getNome());
                    mapParam.put("CodPrestador", Integer.parseInt(p.getCodigo().toString()));
                    } else if (object instanceof Empresa) {
                    Empresa emp = (Empresa) object;
                    filtro.setValor(emp.getNome());
                    mapParam.put("CodPrestador", new BigDecimal(emp.getId().toString()));
                    } else if (object instanceof Cidade) {
                    Cidade cid = (Cidade) object;
                    filtro.setValor(cid.getNome());
                    mapParam.put("CIDADE", Integer.parseInt(cid.getId().toString()));
                    } else if (object instanceof Departamento) {
                    Departamento dpt = (Departamento) object;
                    filtro.setValor(dpt.getNome_departamento());
                    mapParam.put("DEPARTAMENTO", Integer.parseInt(dpt.getId().toString()));
                    }
                    filtrosModel.fireTableDataChanged();
                    prestadores.dispose();*/
                }
            }
        });
        prestadores.setVisible(true);
    }

    //private synchronized void mountSeletorCollection(Filtro filtro) {
    private synchronized void mountSeletorCollection() {
        List<?> resultado = new ArrayList();
        SeletorListaVarios seletor = new SeletorListaVarios();
        TableModelDefaultAdapter model = null;
        /*switch (filtro.getNome()) {
        case "Grupo Exames":
        List<Grupo_Exames> grupo_exames = (List<Grupo_Exames>) emh.getObjectListNamedQuery(Grupo_Exames.class, "grupoexame.findAll", null, null, EntityManagerHelper.ORACLE11G_PU).orElse(new ArrayList());
        model = new TableModel_Pesquisa_Grupo_Exames(grupo_exames);
        seletor.getBtConfirmar().addActionListener(acEvent -> comportamentoSeletor(seletor, resultado, filtro, "id", "GRUPO_EXAMES"));
        break;
        case "Procedimentos":
        List<Proced_Sinonimia> sinonimia = (List<Proced_Sinonimia>) emh.getObjectListNamedQuery(Proced_Sinonimia.class, "sinonimia.findByType", null, null, EntityManagerHelper.ORACLE11G_PU).orElse(new ArrayList());
        model = new TableModel_Pesquisa_Proced_Sinonimia(sinonimia);
        seletor.getBtConfirmar().addActionListener(acEvent -> comportamentoSeletor(seletor, resultado, filtro, "id", "PROCEDIMENTOS"));
        break;
        case "Profissionais":
        List<Medico> profissionais;
        if (reportModel.getObject(form.getTbReports().getSelectedRow()).getSigla().equals("APM0001") && filtrosModel.getObject(0).getValor() != null) {
        LocalDateTime dataInicial = LocalDateTime.of(LocalDate.parse("01/".concat(filtrosModel.getObject(0).getValor()), DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalTime.of(0, 0));
        profissionais = (List<Medico>) emh.getObjectListNamedQuery(Medico.class, "profissionalcs.findPlantaoByMes", new String[]{"paramDataInicial", "paramDataFinal"}, new Object[]{dataInicial, dataInicial.plusDays(dataInicial.toLocalDate().lengthOfMonth())}, EntityManagerHelper.ORACLE11G_PU).orElse(new ArrayList());
        } else {
        profissionais = (List<Medico>) emh.getObjectListNamedQuery(Medico.class, "profissionalcs.findAll", null, null, EntityManagerHelper.ORACLE11G_PU).orElse(new ArrayList());
        }
        model = new TableModel_Pesquisa_Profissional_CS(profissionais);
        seletor.getBtConfirmar().addActionListener(acEvent -> comportamentoSeletor(seletor, resultado, filtro, "id", "PROFISSIONAIS"));
        break;
        case "Departamentos":
        List<Departamento> departamentos = (List<Departamento>) emh.getObjectListNamedQuery(Departamento.class, "departamento.findAll", null, null, EntityManagerHelper.ORACLE11G_PU).orElse(new ArrayList());
        model = new TableModel_Pesquisa_Departamento(departamentos);
        seletor.getBtConfirmar().addActionListener(acEvent -> comportamentoSeletor(seletor, resultado, filtro, "id", "DEPARTAMENTOS"));
        break;
        case "Prestadores":
        List<Prestador_Servico> prestadores = (List<Prestador_Servico>) emh.getObjectListNamedQuery(Prestador_Servico.class, "PrestadorServico.findAll", null, null, EntityManagerHelper.ORACLE11G_PU).orElse(new ArrayList());
        model = new TableModel_Pesquisa_Prestadores(prestadores);
        seletor.getBtConfirmar().addActionListener(acEvent -> comportamentoSeletor(seletor, resultado, filtro, "codigo", "PRESTADORES"));
        break;
        case "Colaboradores":
        List<Funcionario> funcionarios = (List<Funcionario>) emh.getObjectListNamedQuery(Funcionario.class, "funcionario.findAll", null, null, EntityManagerHelper.ORACLE11G_PU).orElse(new ArrayList());
        model = new TableModel_Pesquisa_Funcionario(funcionarios);
        seletor.getBtConfirmar().addActionListener(acEvent -> comportamentoSeletor(seletor, resultado, filtro, "cpf", "FUNCIONARIOS"));
        break;
        }*/
        seletor.setParametrosComportamento(model, resultado);
        seletor.setVisible(true);
    }

    //private synchronized void comportamentoSeletor(SeletorListaVarios seletor, List<?> resultado, Filtro filtro, String campo, String parametro) {
    private synchronized void comportamentoSeletor(SeletorListaVarios seletor, List<?> resultado, String campo, String parametro) {
        seletor.getResultado();
        String valor = "";
        List<?> valores = null;
        /*switch (filtro.getNome()) {
        case "Procedimentos":
        List<Procedimento> lista = resultado.stream().map((sin) -> ((Proced_Sinonimia) sin).getProcedimento()).collect(Collectors.toList());
        valor = getResultadoListaValores(lista, campo);
        valores = getResultadoListaValoresDB(lista, campo);
        break;
        case "Departamentos":
        valor = getResultadoListaValores(resultado, campo);
        valores = getResultadoListaValoresDB(resultado, campo);
        if (valor.contains("21") || valor.contains("1") || valor.contains("2") || valor.contains("521") || valor.contains("47") || valor.contains("23")
        || valor.contains("48") || valor.contains("5") || valor.contains("42") || valor.contains("41") || valor.contains("52") || valor.contains("54") || valor.contains("121")) {
        if (JOptionPane.showConfirmDialog(seletor, "Deseja usar os valores padrões?", "Usar Padrões", JOptionPane.YES_NO_OPTION) == 0) {
        setValoresPadroes(valor);
        }
        }
        break;
        default:
        valor = getResultadoListaValores(resultado, campo);
        valores = getResultadoListaValoresDB(resultado, campo);
        break;
        }
        switch (valor) {
        case "1":
        case "2":
        case "521":
        filtro.setValor("1,2,521");
        mapParam.put(parametro, Arrays.asList(1, 2, 521));
        break;
        case "21":
        case "52":
        case "81":
        filtro.setValor("21,52,81");
        mapParam.put(parametro, Arrays.asList(21, 52, 81));
        break;
        default:
        filtro.setValor(valor);
        mapParam.put(parametro, valores);
        break;
        }*/
        seletor.dispose();
    }

    private synchronized void setValoresPadroes(String departamento) {
        /*switch (departamento) {
        case "21":
        //Cardiologia
        filtrosModel.getLista().get(5).setValor("29,35,64,141");
        filtrosModel.getLista().get(6).setValor("63,71,83,381,3404,9245,16375,20474");
        filtrosModel.getLista().get(7).setValor("63,65");
        filtrosModel.fireTableDataChanged();
        mapParam.put("GRUPO_EXAMES", Arrays.asList(29, 35, 64, 141));
        mapParam.put("PROFISSIONAIS", Arrays.asList(63, 71, 83, 381, 3404, 16375, 9245, 20474));
        mapParam.put("PROCEDIMENTOS", Arrays.asList(63, 65));
        break;
        case "1":
        case "2":
        case "521":
        //Cirurgia Geral
        filtrosModel.getLista().get(5).setValor("24,29,34,35,37,64,141");
        filtrosModel.getLista().get(6).setValor("9,24,69,95,9666,25876,27120");
        filtrosModel.getLista().get(7).setValor("0");
        filtrosModel.fireTableDataChanged();
        mapParam.put("GRUPO_EXAMES", Arrays.asList(24, 29, 34, 35, 37, 64, 141));
        mapParam.put("PROFISSIONAIS", Arrays.asList(9, 24, 69, 95, 9666, 25876, 27120));
        mapParam.put("PROCEDIMENTOS", Arrays.asList(0));
        break;
        case "47":
        //Endocrinologia
        filtrosModel.getLista().get(5).setValor("29,34,64,141");
        filtrosModel.getLista().get(6).setValor("188,12454,24635,26895");
        filtrosModel.getLista().get(7).setValor("0");
        filtrosModel.fireTableDataChanged();
        mapParam.put("GRUPO_EXAMES", Arrays.asList(29, 34, 64, 141));
        mapParam.put("PROFISSIONAIS", Arrays.asList(188, 12454, 24635, 26895));
        mapParam.put("PROCEDIMENTOS", Arrays.asList(0));
        break;
        case "23":
        //Ginecologia
        filtrosModel.getLista().get(5).setValor("29,34,64,141");
        filtrosModel.getLista().get(6).setValor("6,10,50,70,74,77,85,167,4084,12854");
        filtrosModel.getLista().get(7).setValor("0");
        filtrosModel.fireTableDataChanged();
        mapParam.put("GRUPO_EXAMES", Arrays.asList(29, 34, 64, 141));
        mapParam.put("PROFISSIONAIS", Arrays.asList(6, 10, 50, 70, 74, 77, 85, 167, 4084, 12854));
        mapParam.put("PROCEDIMENTOS", Arrays.asList(0));
        break;
        case "48":
        //Neurologia
        filtrosModel.getLista().get(5).setValor("29,35,37,64,141");
        filtrosModel.getLista().get(6).setValor("25,15855,18994");
        filtrosModel.getLista().get(7).setValor("0");
        filtrosModel.fireTableDataChanged();
        mapParam.put("GRUPO_EXAMES", Arrays.asList(29, 35, 37, 64, 141));
        mapParam.put("PROFISSIONAIS", Arrays.asList(25, 15855, 18994));
        mapParam.put("PROCEDIMENTOS", Arrays.asList(0));
        break;
        case "5":
        //Ortopedia
        filtrosModel.getLista().get(5).setValor("29,33,34,35,37,64,141");
        filtrosModel.getLista().get(6).setValor("19,25,66,68,126,15855,18994,21294,25877");
        filtrosModel.getLista().get(7).setValor("109,8986,9605,9606");
        filtrosModel.fireTableDataChanged();
        mapParam.put("GRUPO_EXAMES", Arrays.asList(29, 33, 34, 35, 37, 64, 141));
        mapParam.put("PROFISSIONAIS", Arrays.asList(19, 25, 66, 68, 126, 15855, 18994, 21294, 25877));
        mapParam.put("PROCEDIMENTOS", Arrays.asList(109, 8986, 9605, 9606));
        break;
        case "42":
        //Urologia
        filtrosModel.getLista().get(5).setValor("29,34,35,37,64,141");
        filtrosModel.getLista().get(6).setValor("8,5224,23314");
        filtrosModel.getLista().get(7).setValor("0");
        filtrosModel.fireTableDataChanged();
        mapParam.put("GRUPO_EXAMES", Arrays.asList(29, 34, 35, 37, 64, 141));
        mapParam.put("PROFISSIONAIS", Arrays.asList(8, 5224, 23314));
        mapParam.put("PROCEDIMENTOS", Arrays.asList(0));
        break;
        case "41":
        //Vascular
        filtrosModel.getLista().get(5).setValor("29,34,35");
        filtrosModel.getLista().get(6).setValor("81,87,1464,18114");
        filtrosModel.getLista().get(7).setValor("27");
        filtrosModel.fireTableDataChanged();
        mapParam.put("GRUPO_EXAMES", Arrays.asList(29, 34, 35));
        mapParam.put("PROFISSIONAIS", Arrays.asList(81, 87, 1464, 18114));
        mapParam.put("PROCEDIMENTOS", Arrays.asList(27));
        break;
        case "54":
        //Gastro
        filtrosModel.getLista().get(5).setValor("29,34,64,141");
        filtrosModel.getLista().get(6).setValor("23154");
        filtrosModel.getLista().get(7).setValor("0");
        filtrosModel.fireTableDataChanged();
        mapParam.put("GRUPO_EXAMES", Arrays.asList(29, 34, 64, 141));
        mapParam.put("PROFISSIONAIS", Arrays.asList(23154));
        mapParam.put("PROCEDIMENTOS", Arrays.asList(0));
        break;
        case "121":
        //Neurocirurgião
        filtrosModel.getLista().get(5).setValor("35,37");
        filtrosModel.getLista().get(6).setValor("16281,20574,23354,2964");
        filtrosModel.getLista().get(7).setValor("0");
        filtrosModel.fireTableDataChanged();
        mapParam.put("GRUPO_EXAMES", Arrays.asList(35, 37));
        mapParam.put("PROFISSIONAIS", Arrays.asList(16281, 20574, 23354, 2964));
        mapParam.put("PROCEDIMENTOS", Arrays.asList(0));
        break;
        default:
        break;
        }*/
    }

    private synchronized String getResultadoListaValores(List<?> resultado, String campo) {
        String valor = "";
        for (Object item : resultado) {
            try {
                Field field = item.getClass().getDeclaredField(campo);
                field.setAccessible(true);
                if ((resultado.size() - 1) != resultado.indexOf(item)) {
                    valor = valor.concat(field.get(item).toString() + ",");
                } else {
                    valor = valor.concat(field.get(item).toString());
                }
            } catch (Exception ex) {
                return valor;
            }
        }
        return valor;
    }

    private synchronized List<?> getResultadoListaValoresDB(List<?> resultado, String campo) {
        List<Object> valores = new ArrayList();
        for (Object item : resultado) {
            try {
                Field field = item.getClass().getDeclaredField(campo);
                field.setAccessible(true);
                valores.add(field.get(item));
            } catch (Exception ex) {
                return valores;
            }
        }
        return valores;
    }

    //private void getReportPath(SresReport report) {
    private void getReportPath() {
        //if (report.getPath().contains("rel_RESUMO_PROCEDIMENTOS_PROFISSIONAIS")) {
        //report.setPath("/br/com/sres/reports/rel_RESUMO_PROCEDIMENTOS_PROFISSIONAIS");
        //report.getFiltro().stream().filter((filtro) -> (filtro.getNome().equals("Departamento"))).forEachOrdered((Filtro filtro) -> {
        //report.getFiltro().stream().filter((filtro) -> (filtro.getNome().equals("Um Grupo por Folha"))).forEachOrdered((Filtro filtro) -> {
        //report.setPath(report.getPath().replace(".jasper", ""));
        //if (filtro.getValor().equals("SIM")) {
        //    report.setPath(report.getPath() + "_SEPARADO.jasper");
        //} else {
        //    report.setPath(report.getPath() + ".jasper");
        //}
        /*if (filtro.getValor().contains("Ginecologia")) {
                report.setPath(report.getPath() + "_GINECOLOGIA.jasper");
                } else {
                report.setPath(report.getPath() + "_" + filtro.getValor().trim().toUpperCase().replaceAll("\\s", "_") + ".jasper");
                }*/
        //});
    }
}
