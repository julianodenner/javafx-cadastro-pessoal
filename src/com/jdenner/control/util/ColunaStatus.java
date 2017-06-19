package com.jdenner.control.util;

import com.jdenner.control.PessoaController;
import com.jdenner.model.Pessoa;
import com.jdenner.model.Status;
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
public class ColunaStatus implements Callback<TableColumn<Pessoa, Status>, TableCell<Pessoa, Status>> {

    @Override
    public TableCell<Pessoa, Status> call(TableColumn<Pessoa, Status> param) {

        TableCell<Pessoa, Status> cell = new TableCell<Pessoa, Status>() {

            @Override
            public void updateItem(Status status, boolean empty) {
                super.updateItem(status, empty);

                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    ImageView img = null;

                    if (status == Status.A) {
                        img = new ImageView("/com/jdenner/view/img/ativo.png");
                    } else {
                        img = new ImageView("/com/jdenner/view/img/inativo.png");
                    }

                    setGraphic(img);
                    setText(null);
                    setAlignment(Pos.CENTER);
                    setCursor(Cursor.HAND);
                }
            }
        };
        return cell;
    }

}
