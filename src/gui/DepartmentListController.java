package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {

	private DepartmentService service;

	@FXML
	private TableView<Department> tableViewDepartmen;

	@FXML
	private TableColumn<Department, Integer> tableColumnDepartmenId;

	@FXML
	private TableColumn<Department, String> tableColumnDepartmenName;

	@FXML
	private Button btNew;

	@FXML
	private ObservableList<Department> observableList;

	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}

	@FXML
	public void onBtNewAction() {
		System.out.println("Imprimi ação btNew");
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

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Serviço null");
		}
		
		List<Department> list = service.findAll();
		observableList = FXCollections.observableList(list);
		tableViewDepartmen.setItems(observableList);
		
	}

}
