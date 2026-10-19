package com.svhuerta.laliebreylatortuga;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class PrimaryController {

    @FXML
    private Button btnIniciar;

    @FXML
    private ProgressBar barraLiebre;

    @FXML
    private ProgressBar barraTortuga;

    @FXML
    private Label lblLiebreStatus;

    @FXML
    private Label lblTortugaStatus;

    @FXML
    private void handleIniciarCarrera(ActionEvent event) {
        btnIniciar.setDisable(true);
        barraLiebre.setProgress(0.0);
        barraTortuga.setProgress(0.0);

        // Hilo de la Liebre (rápida al inicio, se duerme a la mitad, y luego despierta)
        Thread hiloLiebre = new Thread(() -> {
            try {
                // Corre rápido hasta el 50%
                for (int i = 1; i <= 50; i++) {
                    final double prog = i / 100.0;
                    Platform.runLater(() -> {
                        barraLiebre.setProgress(prog);
                        lblLiebreStatus.setText("La Liebre: ¡Va corriendo rapidísimo!");
                    });
                    Thread.sleep(30);
                }

                // Se duerme
                Platform.runLater(() -> {
                    lblLiebreStatus.setText("La Liebre: ¡Se durmió!");
                });
                Thread.sleep(4500); // Se duerme 4.5 segundos

                // Despierta y corre a la meta
                for (int i = 51; i <= 100; i++) {
                    final double prog = i / 100.0;
                    Platform.runLater(() -> {
                        barraLiebre.setProgress(prog);
                        lblLiebreStatus.setText("La Liebre: ¡Despertó y corre!");
                    });
                    Thread.sleep(30);
                }

                Platform.runLater(() -> {
                    lblLiebreStatus.setText("La Liebre: ¡Llegó a la meta!");
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Hilo de la Tortuga (constante y sin detenerse)
        Thread hiloTortuga = new Thread(() -> {
            try {
                for (int i = 1; i <= 100; i++) {
                    final double prog = i / 100.0;
                    Platform.runLater(() -> {
                        barraTortuga.setProgress(prog);
                        lblTortugaStatus.setText("La Tortuga: ¡Está corriendo!");
                    });
                    Thread.sleep(80); // Avanza constante
                }

                Platform.runLater(() -> {
                    lblTortugaStatus.setText("La Tortuga: ¡Cruzó la meta!");
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Iniciar los hilos de la carrera
        hiloLiebre.start();
        hiloTortuga.start();

        // Hilo monitor para habilitar de nuevo el botón al terminar
        new Thread(() -> {
            try {
                hiloLiebre.join();
                hiloTortuga.join();
                Platform.runLater(() -> {
                    btnIniciar.setDisable(false);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}