package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        try {
            BorderPane root = new BorderPane();
            Text titulo = new Text("¿Cuánto es el resultado de la siguiente multiplicación?");
            root.setTop(titulo);
            root.getTop().setStyle("-fx-background-color: #9d5595; -fx-font-family: 'Century Gothic'; -fx-font-size: 18;-fx-text-alignment: center");
            root.setStyle("-fx-background-color: #539d29;");
            //
            GridPane multiplicacion = new GridPane();
            multiplicacion.setStyle("-fx-background-color: #fcffe4;-fx-font-family: 'Century Gothic';-fx-font-size: 12");
            multiplicacion.setHgap(10);
            multiplicacion.setVgap(10);
            multiplicacion.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            multiplicacion.setPadding(new Insets(0, 11, 0, 11));

            //Numero 1
            Text numero1T = new Text("Cantidad 1:");
            multiplicacion.add(numero1T, 1, 3);
            TextField numero1 = new TextField();
            numero1.setText(Integer.toString((int)(Math.random()*100)));
            numero1.setEditable(false);
            multiplicacion.add(numero1, 1, 4);

            //X
            Text x = new Text("X");
            multiplicacion.add(x, 5, 4);

            //Numero 2
            Text numero2T = new Text("Cantidad 2:");
            multiplicacion.add(numero2T, 9, 3);
            TextField numero2 = new TextField();
            numero2.setText(Integer.toString((int)(Math.random()*100)));
            numero2.setEditable(false);
            multiplicacion.add(numero2, 9, 4);

            //resultado
            Text resultadoT = new Text("Resultado");
            multiplicacion.add(resultadoT, 0, 6);
            TextField resultado = new TextField();
            multiplicacion.add(resultado, 1,6);

            //Botón aceptar
            Button enviar = new Button("Enviar");
            multiplicacion.add(enviar, 9, 10);

            VBox tiempo = new VBox();
            tiempo.setStyle("-fx-background-color: #fcffe4;-fx-font-family: 'Century Gothic';-fx-font-size: 13;");
            tiempo.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            tiempo.setPadding(new Insets(5, 0, 5, 0));

            Text minutosT = new Text("Minutos");
            TextField minutos = new TextField();
            minutos.setEditable(false);

            Text segundosT = new Text("Segundos");
            TextField segundos = new TextField();
            segundos.setEditable(false);

            Text aviso = new Text("Mal");
            aviso.setVisible(false);
            tiempo.getChildren().addAll(minutosT, minutos, segundosT, segundos, aviso);

            root.setRight(tiempo);
            ControlaTiempo n1 = new ControlaTiempo(minutos, segundos);
            Thread n2 = new Thread(n1);
            n2.setDaemon(true);
            n2.start();
            n1.isDone();
            class evento implements EventHandler<MouseEvent> {
                Thread n1;

                public evento(Thread n1) {
                    this.n1 = n1;
                }

                @Override
                public void handle(MouseEvent actionEvent) {
                    try {
                        if (resultado.getText() == null || resultado.getText() == "") {
                            aviso.setVisible(true);
                        }
                        if (Integer.parseInt(resultado.getText()) == (Integer.parseInt(numero1.getText()) * Integer.parseInt(numero2.getText()))) {
                            aviso.setVisible(false);
                            n1.stop();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Información del tiempo");
                            alert.setHeaderText("Datos");
                            alert.setContentText("Usted duró:" + "\n" + "Minutos: " + minutos.getText() + "\n" + "Segundos: " + segundos.getText());

                            alert.showAndWait();

                        } else {
                            aviso.setVisible(true);
                            //multiplicacion.add(aviso, 3, 6);

                        }
                    } catch (Exception e) {
                        System.out.println("e = " + e);
                    }
                }
            }
            root.setCenter(multiplicacion);
            enviar.setOnMouseClicked(new evento(n2));
            primaryStage.setTitle("Examen unidad dos multiplicaciones");
            primaryStage.setScene(new Scene(root, 500, 250));
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
