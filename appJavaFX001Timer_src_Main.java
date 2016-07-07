import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import javax.swing.Timer;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

/**
 * Created by Руслан on 07.07.2016.
 */
public class Main extends Application {
    Stage windows;
    Date data;
    Timer timer;
    Timer timer2;
    String str;

    List<StoragData> listData=new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception {
        windows=primaryStage;
        primaryStage.setTitle("Test Timer");
        TextArea lable=new TextArea();

        timer=new Timer(1000,e ->{
            //
            data=new Date();
            str=data.toString();
            str=str.replace("EEST","");
            str=str.substring(8,str.length());
            Random rnd=new Random();
            double var=rnd.nextDouble()*10;
            var=(Math.round(var*1000))/1000.0;
lable.setText(str+"      "+String.valueOf(var)+'\n'+lable.getText());
listData.add(new StoragData(var,str));



        });

        Button btn=new Button("ON");
        btn.setOnAction(event -> {

if(timer.isRunning()==true){
    timer.stop();timer2.stop();
}

            else{ timer.start();
    timer2.start();}


        });
Button btn2=new Button("Over");
        btn2.setOnAction(event -> {




        });
        //
        timer2=new Timer(60000,e -> { String dataText;
            try (FileOutputStream fileOutputStream=new FileOutputStream("D://dataFile.txt")){
                for(StoragData storagData:listData){
                    System.out.println(storagData.date+"  "+storagData.value);
                    dataText=storagData.date+" "+storagData.value+"  ";
                    byte[] buffer=dataText.getBytes();
                    fileOutputStream.write(buffer,0,buffer.length);
                }
            }
            catch (Exception ex){}
        });
        //
        VBox vBox=new VBox(20);
        vBox.setPadding(new Insets(20,20,20,20));
        vBox.getChildren().add(btn);
        vBox.getChildren().add(lable);
        vBox.getChildren().add(btn2);
        Scene scene=new Scene(vBox,300,250);

        windows.setScene(scene);
        windows.show();
    }

    public static void main(String... args) {
        launch(args);
    }
}
