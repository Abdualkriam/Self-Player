/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naser;

import java.io.File;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.text.html.CSS;

/**
 *
 * @author USER
 */
public class Naser extends Application {

    private MediaView atuoPlayer = new MediaView();
    
    private MenuItem l = new MenuItem();
    private MediaPlayer mediaPlayer;
    private ContextMenu menuForVdioeSlowSbied;
    private MenuItem slowOne = new MenuItem("0.5");
    private MenuItem slowTow = new MenuItem("0.10");
    private MenuItem slowThree = new MenuItem("0.15");

    private ContextMenu menuForVdioeFastSbied;
    private MenuItem fastOne = new MenuItem("1");
    private MenuItem fastTow = new MenuItem("1.5");
    private MenuItem fastThree = new MenuItem("1.15");

    private Image img5 = new Image("/img/chese.png");
    private Image img1 = new Image("/img/play.png");
    private Image img2 = new Image("/img/pase.png");
    private Image img3 = new Image("/img/next.png");
    private Image img4 = new Image("/img/back.png");
    private Image img6 = new Image("/img/audio.png");
    private Image img7 = new Image("/img/mute.png");
    private Image img8 = new Image("/img/maxsize.png");
    private Image img9 = new Image("/img/minsize.png");
    
    private ImageView mute = new ImageView(img7);
    private ImageView minmamSiaze = new ImageView(img9);
    private ImageView maxSiaze = new ImageView(img8);
    private ImageView pasee = new ImageView(img2);
    private ImageView play = new ImageView(img1);

    private Button btnChese = new Button("ches", new ImageView(img5));
    private Button btnPlay = new Button("", new ImageView(img1));
    
    private Button btnNext = new Button("", new ImageView(img3));
    private Button btnBack = new Button("", new ImageView(img4));
    private Button btnMinse10 = new Button("-10x");
    private Button btnPlase10 = new Button("+10x");
    private Button btnPAdiou = new Button("",new ImageView(img6));
    private Button btnMaxsize = new Button("",maxSiaze);
    private Button btnListVdio = new Button("list");
    Label vdioVuleching = new Label(" 0 : 0 ");
    Label vdioVule = new Label(" 0 : 0 : 0 ");
    private ImageView sonde = new ImageView(img6);
    private Slider SliderAdiou = new Slider();
    private Slider SliderVdiou = new Slider();
    ContextMenu listVdiou = new ContextMenu();
    File[]files;
    MenuItem m;
    private String path;

    @Override
    public void start(Stage primaryStage) {
        
        resizeButton(btnBack , btnChese , btnMinse10 , btnNext  , btnPAdiou , btnPlase10 , btnPlay , btnMaxsize);
        
        StackPane continerApp = new StackPane();
        continerApp.setStyle("-fx-background-color: white");
        menuForVdioeSlowSbied = new ContextMenu();
        menuForVdioeSlowSbied.getItems().addAll(slowOne, slowTow, slowThree);

        menuForVdioeFastSbied = new ContextMenu();
        menuForVdioeFastSbied.getItems().addAll(fastOne, fastTow, fastThree);

        vdioVule.setId("vdioVule");
        vdioVuleching.setId("vdioVuleching");
        
        HBox buttonControl = new HBox(5, vdioVule,btnChese, btnPlay, btnNext, btnBack, btnMinse10, btnPlase10 , btnPAdiou, SliderAdiou ,btnMaxsize ,btnListVdio, vdioVuleching);
               
        VBox con_slider = new VBox(2 ,SliderVdiou, buttonControl);  
        buttonControl.setAlignment(Pos.CENTER);
        setfontSize(10, btnChese, btnPlay, btnPlase10, btnMinse10);

        continerApp.getChildren().addAll(atuoPlayer,con_slider);
        con_slider.setAlignment(Pos.BOTTOM_CENTER);
        continerApp.setCursor(Cursor.DEFAULT);
        Scene scene = new Scene(continerApp, 800, 500);
        scene.getStylesheets().add(getClass().getResource("styel.css").toExternalForm());
        
        primaryStage.setTitle("Self-Plyer"); 
        primaryStage.setScene(scene);
        primaryStage.show();
                
        
        //حدث زر اختياار الفيديو
        btnChese.setOnAction(e -> {
            fileCheserAction();
        });

        //حدث زر تشغيل الفيديو
        btnPlay.setOnAction(e -> { 
            if(mediaPlayer.getStatus()== MediaPlayer.Status.PAUSED) 
                playeAction();
            else
                passAction();   
        });               

        //حدث زر تشغيل الفيديو ب بطئ عند الضغط على الزر يظهر قائمة ل اختيار مقدار التشغي ببطئ
        btnBack.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                menuForVdioeSlowSbied.show(btnBack, event.getScreenX(), event.getScreenY());
            }
        });

        //احداث ازرار قائمة تشغيل الفيديو ببطئ
        slowOne.setOnAction(e -> {   
            sbideOneAction();
        });

        slowTow.setOnAction(e -> {
            sbideTowAction();
        });

        slowThree.setOnAction(e -> {
            sbideThreeAction();
        });

//       حدث زر تشغيل الفيديو ب بسرعة عند الضغط على الزر يظهر قائمة ل اختيار مقدار التشغيل السريع
        btnNext.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                menuForVdioeFastSbied.show(btnNext, event.getScreenX(), event.getScreenY());
            }
        });
        
        //احداث ازرار قائمة تشغيل الفيديو بسرعة
        fastOne.setOnAction(e->{
            fastOneAction();
        });
        
        fastTow.setOnAction(e->{
            fastTowAction();
        });
        
        fastThree.setOnAction(e->{
            fastThreeAction();
        });
        
        //حدث عرض قائمة الفديوهات الموجودة في نفس مسار الفيديو الشغال//
        btnListVdio.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if (event.getButton() == MouseButton.PRIMARY) 
                listVdiou.show(btnListVdio, event.getSceneX(), event.getScreenY());
        });
        
        btnListVdio.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e)->{
//            files.length
        });
        

        //حدث زر اضافة عشر ثواني من الفيديو
        btnPlase10.setOnAction(e -> {
            skip10();
        });

        //حدث زر التراجع عشر ثواني من الفيديو
        btnMinse10.setOnAction(e -> {
            back10();
        });
        
        //حدث زر كتم او تشغيل الصوت
        btnPAdiou.setOnAction(e->{
            mute();
        });
        
        //حدث زر تكبير شاشة الفيديو
        btnMaxsize.setOnAction(e->{
            if(scene.getWidth() == 800 && scene.getHeight() == 500){
                primaryStage.setFullScreen(true);
                btnMaxsize.setGraphic(minmamSiaze);
                Vizbel(con_slider.getChildren());
            }
            else{
                primaryStage.setFullScreen(false);
                btnMaxsize.setGraphic(maxSiaze);
                UNVizbel(con_slider.getChildren());
            }
        });
        
        //اجداث شريط توقيت الفيديو
        SliderVdiou.setOnMousePressed((javafx.scene.input.MouseEvent event) -> {
            mediaPlayer.seek(Duration.seconds(SliderVdiou.getValue()));
        });
        
        SliderVdiou.setOnMouseEntered(e->{
            Duration d = Duration.seconds(SliderVdiou.getValue());
            SliderVdiou.setTooltip(new Tooltip(String.valueOf(TimeVdio(d))));
            
        });

////        حداث اخفاء الازرار من الشاشةعند ابعاد موشر الفارة منها
//        continerApp.setOnMouseExited(e->{
//                Vizbel(con_slider.getChildren());
//        });

////        حداث اظهار الازرار قي الشاشةعند ظهور موشر الفارة عليها
//        continerApp.setOnMouseEntered(e->{
//            Vizbel(con_slider.getChildren());
//        });
        
        /////////////////////////////////(احداث الاختصارات)//////////////////////////////////////////////////
              
            //اجداث اختصار زرايقاف الفيديو او تشغيلة
        scene.setOnKeyPressed(e->{
            boolean statusPlyer = isVudioPlayer();
            if(e.getCode() == KeyCode.ENTER && statusPlyer == true){
                passAction();
            }
            else if(e.getCode() == KeyCode.ENTER && statusPlyer == false)
                playeAction();
        });
        
        scene.setOnMousePressed(e->{
            boolean statusPlayer = isVudioPlayer();
            if(e.getClickCount() == 1 && statusPlayer == true)
                passAction();
            else
                playeAction();
        });
//////////////////////////////////////////////////////////////////////////////////////////
        
        //حدث اختصار زر كتم الصوت و تشغلية
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent e)->{
            if(e.getCode() == KeyCode.F1)
                mute();
        });
        
        //حدث اختصار اضافة عشر ثواني من الفيديو
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            if(e.getCode() == KeyCode.PAGE_DOWN){
                skip10();
                
            }
            else if(e.getCode() == KeyCode.PAGE_UP)
                back10();
        });
        
        //حدث اختصار تكبير الفيديو على كامل الشاشة
        scene.addEventHandler(KeyEvent.KEY_PRESSED , (KeyEvent e) -> {
            if(e.isControlDown() && e.getCode() == KeyCode.SHIFT && scene.getWidth() == 800 && scene.getHeight() == 500){
                primaryStage.setFullScreen(true);
                btnMaxsize.setGraphic(minmamSiaze);
                Vizbel(con_slider.getChildren());  
            }
            else if(e.isControlDown() && e.getCode() == KeyCode.SHIFT){
                primaryStage.setFullScreen(false);
                btnMaxsize.setGraphic(maxSiaze);
                UNVizbel(con_slider.getChildren());
                continerApp.setCursor(Cursor.DEFAULT);
            }
        });
        
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            if(e.getCode() == KeyCode.ESCAPE){
                primaryStage.setFullScreen(false);
                btnMaxsize.setGraphic(maxSiaze);
                UNVizbel(con_slider.getChildren());
            }
        });
        
      }
    
    //////////////////////////////////////////////(دوال الاحداث)///////////////////////////////////////////////////
   
    public void setfontSize(double fontsize, Control... controls) {
        for (Control c : controls) {
            if (c instanceof TextInputControl) {
                ((TextInputControl) c).setFont(Font.font("", FontWeight.BOLD, fontsize));
            } else if (c instanceof Labeled) {
                ((Labeled) c).setFont(Font.font("", FontWeight.BOLD, fontsize));
            }
        }
    }

    private void fileCheserAction() {
        FileChooser filechooser = new FileChooser();
        File file = filechooser.showOpenDialog(null);
        path = file.toURI().toString();
        
        try {
            files = file.getParentFile().listFiles();
                for(File f : files){
                    m = new MenuItem(String.valueOf(f));
                    listVdiou.getItems().addAll(m);
                }
        } catch (Exception e) {
            
            System.out.println(e);
        }

        
        if (path != null) {
            if (isVudioPlayer()){
                mediaPlayer.dispose();
            }
            Media media = new Media(path);
            mediaPlayer = new MediaPlayer(media);

            atuoPlayer.setMediaPlayer(mediaPlayer);
            DoubleProperty widthProprty = atuoPlayer.fitWidthProperty();
            DoubleProperty higthproprty = atuoPlayer.fitHeightProperty();
            widthProprty.bind(Bindings.selectDouble(atuoPlayer.sceneProperty(), "width"));
            higthproprty.bind(Bindings.selectDouble(atuoPlayer.sceneProperty(), "hight"));
            mediaPlayer.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) -> {
                SliderVdiou.setValue(newValue.toSeconds());
                Duration d = mediaPlayer.getCurrentTime();
                vdioVuleching.setText(TimeVdio(d));
            });
                    
            
            mediaPlayer.setOnReady(() -> {
                Duration totel = media.getDuration();
                SliderVdiou.setMax(totel.toSeconds());
                vdioVule.setText(TimeVdio(totel));
            });

            SliderAdiou.setValue(mediaPlayer.getVolume() * 100);
            SliderAdiou.valueProperty().addListener((javafx.beans.Observable observable) -> {
                mediaPlayer.setVolume(SliderAdiou.getValue() / 100);
            });
              
            mediaPlayer.play();
            }
        }


    private void playeAction() {
        mediaPlayer.play();
        btnPlay.setGraphic(play);
    }

    private void passAction() {
        mediaPlayer.pause();
        btnPlay.setGraphic(pasee);
    }

    private void skip10() {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(10)));
    }

    private void back10() {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(-10)));
    }

    private void sbideOneAction() {
        Double d = Double.parseDouble(slowOne.getText());
        mediaPlayer.setRate(d);
    }

    private void sbideTowAction() {
        Double d = Double.parseDouble(slowTow.getText());
        mediaPlayer.setRate(d);
    }

    private void sbideThreeAction() {
        Double d = Double.parseDouble(slowThree.getText());
        mediaPlayer.setRate(d);
    }

    private void fastOneAction() {
        Double d = Double.parseDouble(fastOne.getText());
        mediaPlayer.setRate(d);
    }

    private void fastTowAction() {
        Double d = Double.parseDouble(fastTow.getText());
        mediaPlayer.setRate(d);
    }

    private void fastThreeAction() {
        Double d = Double.parseDouble(fastThree.getText());
        mediaPlayer.setRate(d);
    }
    
    private void mute(){
      if(SliderAdiou.getValue() != 0){
                SliderAdiou.setValue(0);
                btnPAdiou.setGraphic(mute);
            }
            else{
                SliderAdiou.setValue(100);
                btnPAdiou.setGraphic(sonde);
            }
    }
    
    private boolean isVudioPlayer(){
        if(mediaPlayer != null){
            MediaPlayer.Status status = mediaPlayer.getStatus();
            return status == MediaPlayer.Status.PLAYING;
        }
        return false;
    }
    
    private void resizeButton(Button...buttons){
        for(Button b : buttons){
            b.setPrefHeight(27);
        }
    }
    
    private String TimeVdio(Duration time){
        int hours = (int)time.toHours();
        int mintes = (int)time.toMinutes();
        int seconds = (int)time.toSeconds();
        
        if(seconds > 59)
            seconds = seconds % 60;
        if(mintes > 59)
            mintes = mintes % 60;
        if(hours > 59)
            hours = hours % 60;
        
        if(hours > 0)
            return String.format(" %d : %02d : % 02d ", hours , mintes , seconds);
        else
            return String.format(" %02d : %02d ", mintes , seconds);
    }
       
    public void Vizbel(ObservableList<Node> nodes) { 
        for(Node n : nodes){
          n.setStyle("-fx-opacity:0");
        }
    }
    
    public void UNVizbel(ObservableList<Node> nodes){
        for(Node n : nodes){
          n.setStyle("-fx-opacity:1");
        }
    }
    
 

   
 //////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
}

