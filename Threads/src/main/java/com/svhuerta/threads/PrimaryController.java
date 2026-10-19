package com.svhuerta.threads;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextField txtContador;

    @FXML
    private Button btnContar;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button btnEsperar;

    private int contadorClics = 0;

    @FXML
    private void handleBtnContar(ActionEvent event) {
        contadorClics++;
        txtContador.setText(String.valueOf(contadorClics));
    }

    @FXML
    private void handleBtnEsperar(ActionEvent event) {
        btnEsperar.setDisable(true);
        progressBar.setProgress(0.0);

        Thread hilo = new Thread(() -> {
            int totalPasos = 100;
            int tiempoTotalMs = 10000; // 10 segundos
            int tiempoPasoMs = tiempoTotalMs / totalPasos;

            for (int i = 1; i <= totalPasos; i++) {
                final double progreso = (double) i / totalPasos;

                try {
                    Thread.sleep(tiempoPasoMs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Platform.runLater(() -> {
                    progressBar.setProgress(progreso);
                });
            }

            Platform.runLater(() -> {
                btnEsperar.setDisable(false);
            });
        });

        hilo.start();
    }
}