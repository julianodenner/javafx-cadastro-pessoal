package com.jdenner.control.util;

import com.jdenner.control.PessoaController;
import com.jdenner.model.Pessoa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 *
 * @author Juliano
 */
public class ColunaEditar implements Callback<TableColumn<Pessoa, String>, TableCell<Pessoa, String>> {

    private PessoaController controller;

    public ColunaEditar(PessoaController controller) {
        this.controller = controller;
    }

    @Override
    public TableCell<Pessoa, String> call(TableColumn<Pessoa, String> param) {

        TableCell<Pessoa, String> cell = new TableCell<Pessoa, String>() {

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    Button btn = new Button();
                    Image img = new Image(getClass().getResourceAsStream("/com/jdenner/view/img/editar.png"));
                    btn.setGraphic(new ImageView(img));
                    btn.setStyle("-fx-background-color: transparent;");
                    btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Pessoa p = getTableView().getItems().get(getIndex());
                            controller.onActionBtnEditar(p);
                        }
                    });
                    
                    setGraphic(btn);
                    setText(null);
                    setAlignment(Pos.CENTER);
                    setCursor(Cursor.HAND);
                }
            }
        };
        return cell;
    }

}
