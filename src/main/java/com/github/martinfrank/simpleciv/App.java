package com.github.martinfrank.simpleciv;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
//        Parent root = new Label("tesasdfasdfkjhaslkjdhfalkjshdflkjhas");
        TextArea textArea = new TextArea();
        textArea.setFont(Font.font("Courier new",12));
        textArea.setText(listToString(createSampleText()));
        Parent root = textArea;
        Scene scene = new Scene(root);
        primaryStage.setTitle("Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private List<String> createSampleText(){
        //this is 4x2
        List<String> lines = new ArrayList<>();
        lines.add ( "  +---+       +---+       +");
        lines.add ( " /     \\     /     \\     /");
        lines.add ( "+       +---+       +---+");
        lines.add ( " \\     /     \\     /     \\");
        lines.add ( "  +---+       +---+       +");
        lines.add ( " /     \\     /     \\     /");
        lines.add ( "+       +---+       +---+");
        lines.add ( " \\     /     \\     /     \\");
        lines.add ( "  +---+       +---+       +");
        lines.add ( " /     \\     /     \\     /");
        lines.add ( "+       +---+       +---+");
        return lines;
    }

    private String listToString(List<String> lines){
        StringBuffer sb = new StringBuffer();
        lines.forEach(l->sb.append(l+"\n"));
        return sb.toString();
    }
}
