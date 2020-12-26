package view.controllers;

import home.DAO.BillTableDAO;
import home.DAO.DataDAO;
import home.DAO.EmployeeDAO;
import home.LinearRegression.Implement;
import home.DTO.BillTable;
import home.DTO.Employee;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfitController {

    @FXML
    private Button btnHome;

    @FXML
    private Button btnAppointment;

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnProfit;

    @FXML
    private Button btnAbout;

    @FXML
    private BarChart<?, ?> predictChart;

    @FXML
    private BarChart<?, ?> lastThreeMoths;

    @FXML
    private TableView<BillTable> tableID;

    @FXML
    private TextField searchField;

    @FXML
    private Tab billBtn2;

    @FXML
    private Tab billBtn1;

    @FXML
    private Tab salaryBtn;



    @FXML
    private TableView<Employee> salaryTable;

    @FXML
    private TableColumn<Employee, String> cotID;

    @FXML
    private TableColumn<Employee, String> cotTen;

    @FXML
    private TableColumn<Employee, Integer> cotKN;

    @FXML
    private TableColumn<Employee, Integer> cotThoiGian;

    @FXML
    private TableColumn<Employee, Integer> cotLuong;

    private static final EmployeeDAO employeeDAO = new EmployeeDAO();

    private void setTableSalary() throws SQLException {
        cotID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cotTen.setCellValueFactory(new PropertyValueFactory<>("name"));
        cotKN.setCellValueFactory(new PropertyValueFactory<>("exp"));
        cotThoiGian.setCellValueFactory(new PropertyValueFactory<>("times"));
        cotLuong.setCellValueFactory(new PropertyValueFactory<>("salary"));
        salaryTable.getItems().addAll(employeeDAO.getSalary());
    }

    @FXML
    private TableColumn<BillTable, String> bookingCol;

    @FXML
    private TableColumn<BillTable, String> cusCol;

    @FXML
    private TableColumn<BillTable, String> nameCol;

    @FXML
    private TableColumn<BillTable, String> timeCol;

    @FXML
    void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btnAppointment) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Appointment.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
        if (event.getSource() == btnCustomer) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Customer.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
        if (event.getSource() == btnEmployee) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Employee.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage)btnEmployee.getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();

        }
        if (event.getSource() == btnProfit) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Profit.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
        if (event.getSource() == btnAbout) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/About.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
        if (event.getSource() == btnHome) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Main.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage) btnHome.getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
    }

    private static final BillTableDAO bills = new BillTableDAO();

    void setTab () throws SQLException, FileNotFoundException {
        if(!CheckAdmin.isAdmin){
            billBtn1.setDisable(true);
            billBtn2.setDisable(true);
            salaryBtn.setDisable(true);
        }
        else {
            setTableSalary();
            setBarChart();
            setThreeMonthChart();
        }
    }

    @FXML
    void initialize() throws SQLException, FileNotFoundException {
        setTab();
        tableID.getStylesheets().add("src/fxml/css/profit.css");
        bookingCol.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
        cusCol.setCellValueFactory(new PropertyValueFactory<>("cusID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        addButtonToTable();
        tableID.setItems(bills.getBillTable());
        FilteredList<BillTable> filteredList = new FilteredList<>(BillTableDAO.getBillTable(), b->true);
        searchField.textProperty().addListener((observable, oldValue, newValue)-> filteredList.setPredicate(employee -> {
            if (newValue == null || newValue.isEmpty()) return true;
            String lowerCaseFilter = newValue.toLowerCase();
            if (employee.getBillID().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (employee.getBillID().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else return employee.getBookingID().toLowerCase().contains(lowerCaseFilter);
        }));
        SortedList<BillTable> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableID.comparatorProperty());
        tableID.setItems(sortedList);

    }

    private static final DataDAO dataDAO = new DataDAO();




    private void setBarChart() throws FileNotFoundException, SQLException {
        Implement implement = new Implement();
        ObservableList<Double> results = dataDAO.getAvgCus();
        ObservableList<Pair<Double, Double>> data = dataDAO.getPricePerCustomer();
        ArrayList<Double> csv1 = new ArrayList<>(), csv2 = new ArrayList();
        for (Pair<Double, Double> datum : data) {
            csv1.add(datum.getKey());
            csv2.add(datum.getValue());
        }

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Day");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Income");
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("100.000 vnd");
        dataSeries.getData().add(new XYChart.Data("Monday", implement.predictProfit(results.get(0),csv1, csv2)));
        dataSeries.getData().add(new XYChart.Data("Tuesday"  , implement.predictProfit(results.get(1),csv1, csv2)));
        dataSeries.getData().add(new XYChart.Data("Wednesday"  , implement.predictProfit(results.get(2),csv1, csv2)));
        dataSeries.getData().add(new XYChart.Data("Thursday"  , implement.predictProfit(results.get(3),csv1, csv2)));
        dataSeries.getData().add(new XYChart.Data("Friday"  , implement.predictProfit(results.get(4),csv1, csv2)));
        dataSeries.getData().add(new XYChart.Data("Saturday"  , implement.predictProfit(results.get(5),csv1, csv2)));
        dataSeries.getData().add(new XYChart.Data("Sunday"  , implement.predictProfit(results.get(6),csv1, csv2)));
        predictChart.getData().add(dataSeries);
    }

    private void setThreeMonthChart() throws SQLException {
        ObservableList<Double> results = dataDAO.getLast2months();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Month");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Income");
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("vnd");
        dataSeries.getData().add(new XYChart.Data("October", results.get(0)));
        dataSeries.getData().add(new XYChart.Data("November", results.get(1)));
        dataSeries.getData().add(new XYChart.Data("December", results.get(2)));
        lastThreeMoths.getData().add(dataSeries);
    }

    private void addButtonToTable() {
        TableColumn<BillTable, Void> colBtn = new TableColumn("More Information");

        Callback<TableColumn<BillTable, Void>, TableCell<BillTable, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<BillTable, Void> call(final TableColumn<BillTable, Void> param) {
                return new TableCell<>() {

                    private final Button btn = new Button("Details");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Image img;
                            img = new Image("/view/fxml/img/icon.png");
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/Invoice.fxml"));
                            Parent root1 = null;
                            try {
                                root1 = fxmlLoader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            InvoiceController invoiceController = fxmlLoader.getController();
                            BillTable bt = getTableView().getItems().get(getIndex());
                            invoiceController.setAllText(bt.getBillID(), bt.getDate(), bt.getCusName(), bt.getCusID());
                            try {
                                invoiceController.setTable(bt.getBookingID());
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            Stage stage = new Stage();
                            stage.getIcons().add(img);
                            stage.setTitle("Details View:");
                            stage.setScene(new Scene(root1));
                            stage.show();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        colBtn.setCellFactory(cellFactory);

        tableID.getColumns().add(colBtn);

    }


}