package com.jdenner.control.util;

import com.jdenner.model.Pessoa;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

/**
 *
 * @author Juliano
 */
public class ColunaTipo implements Callback<TableColumn<Pessoa, String>, TableCell<Pessoa, String>> {

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
                    Pessoa p = getTableView().getItems().get(getIndex());

                    HBox tipos = new HBox(5);
                    tipos.setAlignment(Pos.CENTER);
                    if (p.isCliente()) {
                        ImageView img = new ImageView("/com/jdenner/view/img/cliente.png");
                        tipos.getChildren().add(img);
                    }
                    if (p.isFornecedor()) {
                        ImageView img = new ImageView("/com/jdenner/view/img/fornecedor.png");
                        tipos.getChildren().add(img);
                    }
                    if (p.isFuncionario()) {
                        ImageView img = new ImageView("/com/jdenner/view/img/funcionario.png");
                        tipos.getChildren().add(img);
                    }

                    setGraphic(tipos);
                    setText(null);
                }
            }
        };
        return cell;
    }

}
