package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;

public class DepartmentListController implements Initializable {

	@FXML
	private TableView<Department> tableViewDepartmen;

	@FXML
	private TableColumn<Department, Integer> tableColumnDepartmenId;

	@FXML
	private TableColumn<Department, String> tableColumnDepartmenName;

	@FXML
	private Button btNew;

	@FXML
	public void onBtNewAction() {
		System.out.println("Imprimi a��o btNew");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();

	}

	private void initializeNodes() {
		tableColumnDepartmenId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnDepartmenName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartmen.prefHeightProperty().bind(stage.heightProperty());
	}

}
