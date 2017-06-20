package com.jdenner.control;

import com.jdenner.control.util.Alerta;
import com.jdenner.control.util.ColunaEditar;
import com.jdenner.control.util.ColunaStatus;
import com.jdenner.control.util.ColunaTipo;
import com.jdenner.control.util.Util;
import com.jdenner.model.Cidade;
import com.jdenner.model.Cliente;
import com.jdenner.model.Estado;
import com.jdenner.model.Fornecedor;
import com.jdenner.model.Funcionario;
import com.jdenner.model.Pessoa;
import com.jdenner.model.PessoaFisica;
import com.jdenner.model.PessoaJuridica;
import com.jdenner.model.Status;
import com.jdenner.model.dao.CidadeDAO;
import com.jdenner.model.dao.ClienteDAO;
import com.jdenner.model.dao.Conexao;
import com.jdenner.model.dao.EstadoDAO;
import com.jdenner.model.dao.FornecedorDAO;
import com.jdenner.model.dao.FuncionarioDAO;
import com.jdenner.model.dao.PessoaDAO;
import com.jdenner.model.dao.PessoaFisicaDAO;
import com.jdenner.model.dao.PessoaJuridicaDAO;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Date;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class PessoaController {

    private int codPessoa;

    private int codPessoaFisica;

    private int codPessoaJuridica;

    @FXML
    private Button btnNovo;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnCancelar;

    @FXML
    private SplitPane spPainel;

    @FXML
    private TabPane tpFormulario;

    @FXML
    private Tab tbPessoaFisica;

    @FXML
    private Tab tbPessoaJuridica;

    @FXML
    private Tab tbFuncionario;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfEndereco;

    @FXML
    private ComboBox<Estado> cbEstado;

    @FXML
    private ComboBox<Cidade> cbCidade;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextField tfEmail;

    @FXML
    private RadioButton rbAtivo;

    @FXML
    private RadioButton rbInativo;

    @FXML
    private CheckBox cbCliente;

    @FXML
    private CheckBox cbFornecedor;

    @FXML
    private CheckBox cbFuncionario;

    @FXML
    private RadioButton rbPessoaFisica;

    @FXML
    private RadioButton rbPessoaJuridica;

    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfRg;

    @FXML
    private DatePicker dpDataNascimento;

    @FXML
    private TextField tfCnpj;

    @FXML
    private TextField tfIe;

    @FXML
    private TextField tfRazaoSocial;

    @FXML
    private DatePicker dpDataAdmissao;

    @FXML
    private DatePicker dpDataDemissao;

    @FXML
    private VBox pnGrade;

    @FXML
    private CheckBox cbClientes;

    @FXML
    private CheckBox cbFornecedores;

    @FXML
    private CheckBox cbFuncionarios;

    @FXML
    private TextField tfFiltro;

    @FXML
    private TableView<Pessoa> tbGrade;

    @FXML
    private TableColumn<Pessoa, String> tcNome;

    @FXML
    private TableColumn<Pessoa, String> tcDocumento;

    @FXML
    private TableColumn<Pessoa, String> tcTipo;

    @FXML
    private Button btnAjudaTipo;

    @FXML
    private TableColumn<Pessoa, Status> tcStatus;

    @FXML
    private TableColumn<Pessoa, String> tcEditar;

    @FXML
    protected void onActionBtnCancelar(ActionEvent event) {
        habilitarEdicao(false);
        limparFormulario();
    }

    @FXML
    protected void onActionBtnNovo(ActionEvent event) {
        codPessoa = 0;
        codPessoaFisica = 0;
        codPessoaJuridica = 0;
        limparFormulario();
        habilitarEdicao(true);
    }

    @FXML
    protected void onActionBtnSalvar(ActionEvent event) {

        Conexao con = null;

        try {
            con = new Conexao();

            Pessoa p = new Pessoa();
            p.setCodPessoa(codPessoa);
            p.setNome(tfNome.getText());
            p.setEndereco(tfEndereco.getText());
            p.setCidade(cbCidade.getSelectionModel().getSelectedItem());
            p.setEmail(tfEmail.getText());
            p.setDataCadastro(new Date());
            p.setCliente(cbCliente.isSelected());
            p.setFornecedor(cbFornecedor.isSelected());
            p.setFuncionario(cbFuncionario.isSelected());
            p.setTelefone(tfTelefone.getText());
            p.setStatus((rbAtivo.isSelected()) ? Status.A : Status.I);

            if (rbPessoaFisica.isSelected()) {
                PessoaFisica pf = new PessoaFisica();
                pf.setCodPessoaFisica(codPessoaFisica);
                pf.setCpf(tfCpf.getText());
                pf.setRg(tfRg.getText());
                pf.setDataNascimento(Util.toDate(dpDataNascimento.getValue()));
                pf.setCodPessoaFisica(codPessoaFisica = PessoaFisicaDAO.salvar(pf, con));
                p.setPessoaFisica(pf);
            } else {
                PessoaJuridica pj = new PessoaJuridica();
                pj.setCodPessoaJuridica(codPessoaJuridica);
                pj.setCnpj(tfCnpj.getText());
                pj.setIe(tfIe.getText());
                pj.setRazaoSocial(tfRazaoSocial.getText());
                pj.setCodPessoaJuridica(codPessoaJuridica = PessoaJuridicaDAO.salvar(pj, con));
                p.setPessoaJuridica(pj);
            }

            p.setCodPessoa(codPessoa = PessoaDAO.salvar(p, con));

            if (rbPessoaFisica.isSelected()) {
                PessoaJuridicaDAO.excluir(codPessoaJuridica, con);
            } else {
                PessoaFisicaDAO.excluir(codPessoaFisica, con);
            }

            if (p.isCliente()) {
                Cliente c = new Cliente();
                c.setCodPessoa(codPessoa);
                ClienteDAO.salvar(c, con);
            } else {
                ClienteDAO.excluir(p.getCodPessoa(), con);
            }

            if (p.isFornecedor()) {
                Fornecedor f = new Fornecedor();
                f.setCodPessoa(codPessoa);
                FornecedorDAO.salvar(f, con);
            } else {
                FornecedorDAO.excluir(p.getCodPessoa(), con);
            }

            if (p.isFuncionario()) {
                Funcionario fu = new Funcionario();
                fu.setCodPessoa(codPessoa);
                fu.setDataAdmissao(Util.toDate(dpDataAdmissao.getValue()));
                fu.setDataDemissao((dpDataDemissao.getValue() != null) ? Util.toDate(dpDataDemissao.getValue()) : null);
                FuncionarioDAO.salvar(fu, con);
            } else {
                FuncionarioDAO.excluir(p.getCodPessoa(), con);
            }

            con.confirmar();

        } catch (InvalidParameterException e) {
            Alerta.alerta("Erro ao salvar.", e);
            return;
        } catch (Exception e) {
            if (con != null) {
                con.fechar();
            }
            Alerta.erro("Erro ao salvar.", e);
            return;
        }

        atualizarGrade();
        Alerta.sucesso("Dados salvos com sucesso");
        habilitarEdicao(false);
    }

    @FXML
    protected void onActionCbEstado(ActionEvent event) {
        try {
            if (cbEstado != null && !cbEstado.getSelectionModel().isEmpty()) {
                cbCidade.setItems(CidadeDAO.listar(true, cbEstado.getSelectionModel().getSelectedItem()));
            }
        } catch (Exception e) {
            Alerta.erro("Erro ao consultar dados.", e);
        }
    }

    @FXML
    protected void onActionRbTipo(ActionEvent event) {
        tpFormulario.getTabs().removeAll(tbPessoaFisica, tbPessoaJuridica);
        tpFormulario.getTabs().add((rbPessoaFisica.isSelected()) ? tbPessoaFisica : tbPessoaJuridica);
    }

    @FXML
    protected void onActionVisualizar(ActionEvent event) {
        atualizarGrade();
    }

    @FXML
    protected void onActionFuncionario(ActionEvent event) {
        if (cbFuncionario.isSelected()) {
            tpFormulario.getTabs().add(tbFuncionario);
        } else {
            tpFormulario.getTabs().remove(tbFuncionario);
        }
    }

    @FXML
    protected void initialize() {
        tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        tcTipo.setCellFactory(new ColunaTipo());
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tcStatus.setCellFactory(new ColunaStatus());
        tcEditar.setCellFactory(new ColunaEditar(this));
        carregarCbEstado();
        atualizarGrade();
        habilitarEdicao(false);
        exibeAjudaTipo();
    }

    public void onActionBtnEditar(Pessoa p) {
        try {
            codPessoa = p.getCodPessoa();
            tfNome.setText(p.getNome());
            tfEndereco.setText(p.getEndereco());
            cbEstado.getSelectionModel().select(p.getCidade().getEstado());
            cbCidade.getSelectionModel().select(p.getCidade());
            tfTelefone.setText(p.getTelefone());
            tfEmail.setText(p.getEmail());
            rbAtivo.setSelected(p.getStatus() == Status.A);
            rbInativo.setSelected(p.getStatus() == Status.I);
            cbCliente.setSelected(p.isCliente());
            cbFornecedor.setSelected(p.isFornecedor());
            cbFuncionario.setSelected(p.isFuncionario());

            if (p.getPessoaFisica() != null) {
                codPessoaFisica = p.getPessoaFisica().getCodPessoaFisica();
                codPessoaJuridica = 0;
                rbPessoaFisica.setSelected(true);
                rbPessoaJuridica.setSelected(false);
                codPessoaFisica = p.getPessoaFisica().getCodPessoaFisica();
                codPessoaJuridica = 0;
                tfCpf.setText(p.getPessoaFisica().getCpf());
                tfRg.setText(p.getPessoaFisica().getRg());
                dpDataNascimento.setValue(Util.toLocalDate(p.getPessoaFisica().getDataNascimento()));
            } else {
                codPessoaFisica = 0;
                codPessoaJuridica = p.getPessoaJuridica().getCodPessoaJuridica();
                rbPessoaFisica.setSelected(false);
                rbPessoaJuridica.setSelected(true);
                codPessoaFisica = 0;
                codPessoaJuridica = p.getPessoaJuridica().getCodPessoaJuridica();
                tfCnpj.setText(p.getPessoaJuridica().getCnpj());
                tfIe.setText(p.getPessoaJuridica().getIe());
                tfRazaoSocial.setText(p.getPessoaJuridica().getRazaoSocial());
            }
            if (p.isFuncionario()) {
                tbFuncionario.setDisable(false);
                Funcionario f = FuncionarioDAO.get(p.getCodPessoa());
                dpDataAdmissao.setValue(Util.toLocalDate(f.getDataAdmissao()));
                if (f.getDataDemissao() != null) {
                    dpDataDemissao.setValue(Util.toLocalDate(f.getDataDemissao()));
                }
            }
            habilitarEdicao(true);
        } catch (Exception e) {
            Alerta.erro("Erro ao carregar dados.", e);
        }
    }

    private void carregarCbEstado() {
        try {
            cbEstado.setItems(EstadoDAO.listar(true));
        } catch (Exception e) {
            Alerta.erro("Erro ao consultar dados.", e);
        }
    }

    private void atualizarGrade() {
        try {
            tbGrade.setItems(PessoaDAO.listar(false,
                    cbClientes.isSelected(),
                    cbFornecedores.isSelected(),
                    cbFuncionarios.isSelected(),
                    tfFiltro.getText()));
            tbGrade.refresh();
        } catch (Exception e) {
            Alerta.erro("Erro ao consultar dados.", e);
        }
    }

    private void limparFormulario() {
        tfNome.setText("");
        tfEndereco.setText("");
        cbEstado.getSelectionModel().clearSelection();
        cbCidade.getSelectionModel().clearSelection();
        tfTelefone.setText("");
        tfEmail.setText("");
        rbAtivo.setSelected(true);
        cbCliente.setSelected(false);
        cbFornecedor.setSelected(false);
        cbFuncionario.setSelected(false);
        rbPessoaFisica.setSelected(true);
        tfCpf.setText("");
        tfRg.setText("");
        dpDataNascimento.setValue(null);
        tfCnpj.setText("");
        tfIe.setText("");
        tfRazaoSocial.setText("");
        dpDataAdmissao.setValue(null);
        dpDataDemissao.setValue(null);
        tpFormulario.getTabs().removeAll(tbPessoaFisica, tbPessoaJuridica, tbFuncionario);
    }

    private void exibeAjudaTipo() {
        try {
            Tooltip ajuda = new Tooltip();
            Parent root = FXMLLoader.load(getClass().getResource("/com/jdenner/view/TipoPessoa.fxml"));
            ajuda.setGraphic(root);
            btnAjudaTipo.setTooltip(ajuda);
        } catch (IOException e) {
            Alerta.erro("Erro ao exibir ajuda.", e);
        }
    }

    private void habilitarEdicao(boolean habilitar) {
        btnNovo.setManaged(!habilitar);
        btnNovo.setVisible(!habilitar);
        btnSalvar.setVisible(habilitar);
        btnSalvar.setManaged(habilitar);
        btnCancelar.setVisible(habilitar);
        btnCancelar.setManaged(habilitar);
        tpFormulario.setDisable(!habilitar);
        pnGrade.setDisable(habilitar);
        onActionRbTipo(null);
        onActionFuncionario(null);
        alterarPainel((habilitar) ? 1 : 0);
    }

    private void alterarPainel(double posicao) {
        KeyValue keyValue = new KeyValue(spPainel.getDividers().get(0).positionProperty(), posicao);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(250), keyValue);
        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
    }
}
