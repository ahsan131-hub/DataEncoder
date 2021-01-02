package HuffManEncoder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class HuffmanController {

    @FXML
    private Label filePath;

    @FXML
    private JFXButton fileChooser;

    @FXML
    private JFXListView<File> list;

    @FXML
    private JFXButton encodeBtn;

    @FXML
    private JFXButton decodeBtn;

    @FXML
    void selectFile(ActionEvent event) {
        FileChooser fileChooser=new FileChooser();
        File selectedFile=fileChooser.showOpenDialog(null);
        if(selectedFile!=null){
            filePath.setText(selectedFile.getName());
            list.getItems().add(new File(selectedFile.getAbsolutePath()));
        }else {
           filePath.setText("Select correct Files");
        }


    }


    @FXML
    void EncodeFiles(ActionEvent event) {
        System.out.println(list.toString());
        for (var file :
                list.getItems()) {
            System.out.println(file.toString());
            try {
                if(mainMinHeap.encodeFile(file)){
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("File is Encoded succesfully");
                a.show();

            }else {
                Alert a=new Alert(Alert.AlertType.ERROR);
                a.setContentText("File is Could not be Encoded ");
                a.show();

            }

        } catch (IOException e) {
                e.printStackTrace();
            }
        }
        list.getItems().clear();


    }

    @FXML
    void decodeFiles(ActionEvent event) {
        System.out.println(list.toString());
        for (var file :
                list.getItems()) {
            System.out.println(file.toString());
            try {
                if(mainMinHeap.DecodeFile(file)){
                    Alert a=new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("File is Decoded succesfully");
                    a.show();

                }else {
                    Alert a=new Alert(Alert.AlertType.ERROR);
                    a.setContentText("File is Could not be Decoded");
                    a.show();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        list.getItems().clear();
    }

}
