import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import java.util.concurrent.FutureTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;


// Max int for the tree is 999

public class JuggleFish extends Application {
  public Stage window;
  public Scene scene1;
  public int btn_count = 0;

  public void start(Stage primaryStage) throws Exception {

    // --- Create UI Window --- //
    window = primaryStage;
    Pane root = new Pane();
    BorderPane bPane = new BorderPane();
    HBox buttonPanel = new HBox();
    FieldButton startGame = new FieldButton("Start Game");
    
    Button clearGame = new Button("Clear Game");
    StackPane stP = new StackPane();
    stP.getChildren().addAll(clearGame);
    StackPane.setAlignment(clearGame, Pos.BOTTOM_CENTER);
    
    buttonPanel.setSpacing(60);
    buttonPanel.getChildren().addAll(startGame.root, stP);
    Canvas canvas = new Canvas(600, 450);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    drawBorder(gc);


    // --- Create Button Functionality --- //
    startGame.nameFld.setPrefWidth(190);
    startGame.doBtn.setOnAction(e -> {
      String numList = startGame.nameFld.getText();
      startGame.msg.setText("Action applied");
    });

    
    buttonPanel.setAlignment(Pos.CENTER);
    bPane.setCenter(canvas);
    bPane.setBottom(buttonPanel);

    HBox jugglerSpread = new HBox(10);
    Juggler juggler1 = new Juggler("1");
    Juggler juggler2 = new Juggler("2");
    Juggler juggler3 = new Juggler("3");
    Group jug1 = new Group(juggler1.imageView);
    Group jug2 = new Group(juggler2.imageView);
    Group jug3 = new Group(juggler3.imageView);
    Pane j1 = new Pane(jug1);
    Pane j2 = new Pane(jug2);
    Pane j3 = new Pane(jug3);


    jugglerSpread.getChildren().addAll(j1, j2, j3);
    jugglerSpread.setLayoutY(130);
    jugglerSpread.setLayoutX(230);
    // jugglerSpread.setAlignment(Pos.CENTER_LEFT);
    // jugglerSpread.setSpacing(135);

    // System.out.println("HBOX has: " + jugglerSpread.getChildren());

    // --- Create Scene --- //
    // root.getChildren().add(jugglerSpread);
    bPane.getChildren().add(jugglerSpread); // root
    scene1 = new Scene(bPane); // bPane
    window.setScene(scene1);
    window.setTitle("Fish Juggling Game");
    window.setResizable(false);
    window.setWidth(750);
    window.setHeight(700);
    window.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  private void clearCanvas(GraphicsContext gc, double width, double height) {
    gc.clearRect(0, 0, width, height);
    drawBorder(gc);
  }

  private void drawBorder(GraphicsContext gc) {

    Color myYellow = Color.web("#ffffe6"); // Colour light yellow
    gc.setFill(myYellow);
    gc.fillRect(0, 0, 600, 450);

    // Draw border
    gc.setStroke(Color.BROWN);
    gc.setLineWidth(2);
    gc.strokeLine(0, 0, 600, 0); // x,y  x,y  from-to
    gc.strokeLine(600, 0, 600, 450); // x,y  x,y
    gc.strokeLine(600, 450, 0, 450); // x,y  x,y
    gc.strokeLine(0, 450, 0, 0); // x,y  x,y
  }

  public int countDigits(int number) {
    int digitCount = 0;
    while(number > 0) {
      number = number / 10;
      digitCount += 1;
    }
    digitCount = (digitCount==0) ? 1: digitCount;
    return digitCount;
  }

  public boolean isInteger(String str) {
    if (str == null) {
      return false;
    }
    int length = str.length();
    if (length == 0) {
      return false;
    }
    int i = 0;
    if (str.charAt(0) == '-') {
      if (length == 1) {
        return false;
      }
      i = 1;
    }
    for (; i < length; i++) {
      char c = str.charAt(i);
      if (c < '0' || c > '9') {
        return false;
      }
    }
    return true;
  }


  class FieldButton {
    public Label nameLbl = new Label("Enter value:");
    public TextField nameFld = new TextField();   
    public Label msg = new Label();
    public Button doBtn = new Button("Apply action");
    public VBox root = new VBox();
    
    FieldButton(String purpose) {
      this.nameLbl = new Label(purpose);
      this.nameFld = new TextField();
      this.nameFld.setPrefWidth(90);
      this.msg = new Label();
      this.doBtn = new Button("Apply action");
      this.root = new VBox();
      dressUpBtn();
    }
    public void dressUpBtn() {
      msg.setStyle("-fx-text-fill: blue;");
      root.setSpacing(5);
      root.getChildren().addAll(nameLbl, nameFld, msg, doBtn);
    }
  }
}




