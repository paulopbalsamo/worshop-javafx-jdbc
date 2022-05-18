package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.listerners.DataChangeListerner;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {

	private Department entity;
	private DepartmentService service;
	private List<DataChangeListerner> dataChangeListerners = new ArrayList<DataChangeListerner>();

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtName;

	@FXML
	private Label labelErrorName;

	@FXML
	private Button btSave;

	@FXML
	private Button btCancel;

	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("entity é null");
		}
		if (service == null) {
			throw new IllegalStateException("service é null");
		}
		try {
			entity = getFormData(); // reponsavel pegar os dados na cx do formulario e instanciar
			service.saveOrUpdate(entity);
			notifyDataChangeListerner();
			Utils.currentStage(event).close();

		} catch (DbException e) {
			Alerts.showAlert("ERRO AO SALVAR", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private void notifyDataChangeListerner() {
		for (DataChangeListerner dataChangeListerner : dataChangeListerners) {
			dataChangeListerner.onDataChanged();
		}
		
	}

	private Department getFormData() {
		Department objDepartment = new Department();
		objDepartment.setId(Utils.tryParseInteger(txtId.getText()));
		objDepartment.setName(txtName.getText());
		return objDepartment;

	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}

	public void setDepartment(Department entity) {
		this.entity = entity;
	}

	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity é null");
		}

		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
	}
	
	public void subscribleDataChangeListener(DataChangeListerner listener) {
		dataChangeListerners.add(listener);
	}

}