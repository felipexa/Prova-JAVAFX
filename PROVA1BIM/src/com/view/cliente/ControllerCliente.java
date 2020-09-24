package com.view.cliente;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.dao.ClienteDAO;
import com.entity.Cliente;
import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class ControllerCliente extends Application implements Initializable{

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldCpf;

    @FXML
    private TextField textFieldSalario;

    @FXML
    private TextArea textAreaListClientes;

    @FXML
    private Button buttonInserir;

    @FXML
    private Button buttonAlterar;

    @FXML
    private Button buttonExcluir;

    @FXML
    private Button buttonBuscar;

    @FXML
    private TextField textFieldId;

    @FXML
    private DatePicker datePickerData;
    
    @FXML
    private Label labelQtd;
    
    public static void main(String[] args) {
		launch();
	}
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		listarClientes();			
	}

    @Override
    public void start(Stage stage) {
        try {
            AnchorPane painel = (AnchorPane) FXMLLoader.load(getClass().getResource("Cliente.fxml"));
            stage.setTitle("Cadastro de Cliente");
            Scene sc = new Scene(painel);
            stage.setScene(sc);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  boolean validacao() {
    	if(textFieldNome.getText().isEmpty() | textFieldCpf.getText().isEmpty() | datePickerData.getValue() == null |  textFieldSalario.getText().isEmpty()){
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Alerta");
    		alert.setHeaderText("Campos não preenchidos!");
    		alert.setContentText("Preencha os campos!");
    		alert.showAndWait();
    		return false;
    	} 
    	return true;
    	
    }
    @FXML
    void inserirCliente(ActionEvent event) {
    	
    	if(validacao()) {
	    	Cliente cliente = pegaDados();
			limpaCampos();
			int qtde = new ClienteDAO().inserir(cliente);
			listarClientes();
    	} 

    }

    @FXML
    void alterarCliente(ActionEvent event) {
    	if(validacao()) {
	    	Cliente cliente= pegaDadosID();
	    	if(String.valueOf(cliente.getId()) == null || String.valueOf(cliente.getId()) =="") {
	    		Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("Alerta");
	    		alert.setHeaderText("Cliente não selecionado");
	    		alert.setContentText("Selecione um Cliente para alterar");
	    	}
	    	else {
	    		Alert alert = new Alert(AlertType.CONFIRMATION);
	        	alert.setTitle("Alterar Cliente");
	        	alert.setHeaderText("Você está prestes a alterar um Cliente");
	        	alert.setContentText("Tem certeza que deseja alterar o Cliente?");
	        	Optional<ButtonType> result = alert.showAndWait();
	        	if (result.get() == ButtonType.OK){
	        	
	    		limpaCampos();
	    		int qtde = new ClienteDAO().alterar(cliente);
	    		listarClientes();
	    		
	        	}
	    	}  
    	}  
    }
    
    @FXML
    void excluirCliente(ActionEvent event) {
    	if(validacao()) {
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Deletar cliente");
	    	alert.setHeaderText("Você está prestes a deletar um cliente");
	    	alert.setContentText("Tem certeza que deseja deletar o cliente?");
	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
	    		Cliente cliente= pegaDadosID();
	        	int qtde = new ClienteDAO().deletar(cliente.getId());
	        	limpaCampos();
	        	listarClientes();
	    	}
    	}
    }

    @FXML
    void buscarCliente(ActionEvent event) {
    	
    	if(textFieldId.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Alerta");
    		alert.setHeaderText("Campos não preenchidos!");
    		alert.setContentText("Preencha os campos!");
    		alert.showAndWait();    		
    		
    	} else {
    		String idString = textFieldId.getText();
    		Cliente cliente = null;
    		if (!idString.equals("")) {
    			try {
    				int id = Integer.valueOf(idString);
    				cliente = new ClienteDAO().buscar(id);
    			} catch (Exception e) {
    			}
    			if (cliente != null) {				
    				textFieldNome.setText(cliente.getNome());
    				textFieldCpf.setText(cliente.getCpf());
    				textFieldSalario.setText(cliente.getSalario() + "");
    				datePickerData.setValue(cliente.getNascimento().toLocalDate());
    			}
    		}	    		
    	}   	
    	
    
    }

	private void limpaCampos() {
		textFieldCpf.clear();
		datePickerData.setValue(null);
		textFieldSalario.clear();		
		textFieldNome.clear();
		textFieldNome.requestFocus();	
	}
    
	private Cliente pegaDados() {
		return new Cliente(textFieldNome.getText(), textFieldCpf.getText(), java.sql.Date.valueOf(datePickerData.getValue()) , java.lang.Float.parseFloat(textFieldSalario.getText()));
	}
	
	private Cliente pegaDadosID() {		
		return new Cliente(Integer.valueOf(textFieldId.getText()), textFieldNome.getText(), textFieldCpf.getText(),  java.sql.Date.valueOf(datePickerData.getValue())  , java.lang.Float.parseFloat(textFieldSalario.getText()));
	}

	private void listarClientes() {
		
		textAreaListClientes.clear();
		List<Cliente> listaCliente = new ClienteDAO().listar();
		listaCliente.forEach(Cliente -> {
			textAreaListClientes.appendText(Cliente.toString() + "\n");
			labelQtd.setText(String.valueOf(listaCliente.size()));
		});
		
	}

	public void execute() {
		launch();		
	}
	
}
