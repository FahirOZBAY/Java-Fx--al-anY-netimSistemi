/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calisanlarfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 *
 * @author Win
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private VBox txtAra;
    @FXML
    private TextField txtAd;
    @FXML
    private TextField txtSoyad;
    @FXML
    private TextField txtDept;
    @FXML
    private TextField txtMaas;
    @FXML
    private Button btnEkle;
    @FXML
    private Button btnGuncelle;
    @FXML
    private Button btnSil;
    @FXML
    private TableView<Pojo.Calisanlar> tblCalisan;
    @FXML
    private TableColumn<Pojo.Calisanlar, Integer> col_id;
    @FXML
    private TableColumn<Pojo.Calisanlar, String> col_ad, col_soyad, col_dep, col_maas;
    public ObservableList<Pojo.Calisanlar> data = FXCollections.observableArrayList();
    String key = null;
    Database.calisanlar c = new Database.calisanlar();
    @FXML
    private TextField aramac;

    @Override

    public void initialize(URL url, ResourceBundle rb) {

        calisanOku();
        aramac.textProperty().addListener((observable, oldValue, newValue) -> {
            arama();
        });
    }

    public void calisanOku() {
        tblCalisan.getItems().clear();
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
        col_soyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
        col_dep.setCellValueFactory(new PropertyValueFactory<>("departman"));
        col_maas.setCellValueFactory(new PropertyValueFactory<>("maas"));

        ObservableList<Pojo.Calisanlar> list = Database.calisanlar.getCalisanlars();
        tblCalisan.setItems(list);
    }

    @FXML
    private void arama() {

        tblCalisan.getItems().clear();
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
        col_soyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
        col_dep.setCellValueFactory(new PropertyValueFactory<>("departman"));
        col_maas.setCellValueFactory(new PropertyValueFactory<>("maas"));
        if (aramac.getText().toString().equals("")) {
            System.out.println(aramac.getText());
            ObservableList<Pojo.Calisanlar> list = Database.calisanlar.getCalisanlars();
            tblCalisan.setItems(list);

        } else {
            ObservableList<Pojo.Calisanlar> list = c.calisanBul(aramac.getText());
            tblCalisan.setItems(list);

        }

    }

    @FXML
    public void satirSec() {

        int i = tblCalisan.getSelectionModel().getSelectedIndex();
        key = col_id.getCellData(i).toString();
        txtAd.setText(col_ad.getCellData(i));
        txtSoyad.setText(col_soyad.getCellData(i));
        txtDept.setText(col_dep.getCellData(i));
        txtMaas.setText(col_maas.getCellData(i));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
        col_soyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
        col_dep.setCellValueFactory(new PropertyValueFactory<>("departman"));
        col_maas.setCellValueFactory(new PropertyValueFactory<>("maas"));

    }

    public void temizle() {
        key = null;
        txtAd.clear();
        txtSoyad.clear();
        txtMaas.clear();
        txtDept.clear();

    }

    @FXML
    private void ekle(ActionEvent event) {
        if (!txtAd.getText().equals("") && !txtDept.getText().equals("") && !txtMaas.getText().equals("") && !txtSoyad.getText().equals("")) {

            String ad = txtAd.getText();
            String soyad = txtSoyad.getText();
            String maas = txtMaas.getText();
            String departman = txtDept.getText();
            Database.calisanlar.calisanEkle(ad, soyad, departman, maas);
            calisanOku();
            temizle();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Gerekli alanlar boş geçilemez");
            alert.setHeaderText("İşlem sırasında hata meydana geldi");
            alert.setContentText("Lütfen ad soyad maaş ve departman alanlarını eksiksiz doldurun");
            alert.showAndWait();
        }

    }

    @FXML
    private void guncelle(ActionEvent event) {
        if (!txtAd.getText().equals("") && !txtDept.getText().equals("") && !txtMaas.getText().equals("") && !txtSoyad.getText().equals("")) {
            int i = tblCalisan.getSelectionModel().getSelectedIndex();
            int id = col_id.getCellData(i);
            String ad = txtAd.getText();
            String soyad = txtSoyad.getText();
            String maas = txtMaas.getText();
            String departman = txtDept.getText();
            Database.calisanlar.calisanGuncelle(ad, soyad, departman, maas, id);
            calisanOku();
            temizle();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Gerekli alanlar boş geçilemez");
            alert.setHeaderText("İşlem sırasında hata meydana geldi");
            alert.setContentText("Lütfen ad soyad maaş ve departman alanlarını eksiksiz doldurun");
            alert.showAndWait();
        }

    }

    @FXML
    private void sil(ActionEvent event) {
        if (!txtAd.getText().equals("") && !txtDept.getText().equals("") && !txtMaas.getText().equals("") && !txtSoyad.getText().equals("")) {
            int i = tblCalisan.getSelectionModel().getSelectedIndex();
            int id = col_id.getCellData(i);
            String ad = txtAd.getText();
            String soyad = txtSoyad.getText();
            String maas = txtMaas.getText();
            String departman = txtDept.getText();
            Database.calisanlar.calisanSil(ad, soyad, departman, maas, id);
            calisanOku();
            temizle();
        } else {
            Alert alert = new Alert(AlertType.ERROR, "Lütfen silmek için kayıt seçin", ButtonType.OK);
            alert.setTitle("Hata");
            alert.setHeaderText("Silme işlemi sırasında hata oluştu");

            alert.showAndWait();

        }

    }

}
