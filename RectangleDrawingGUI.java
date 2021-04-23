import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import java.util.*;

public class RectangleDrawingGUI extends Application {

	private Pane pane;
	private Rectangle rectangle, anotherRectangle;
	private RadioButton red, yellow, blue, thinBorder, thickBorder;
	private CheckBox fill;
	private Button clearButton;
	private boolean rectangleBeingDrawn;//, movingLeft, movingUp;
	private double x, y;
	private int count = 0;
	
	
	
	ArrayList<Rectangle> drawnRectangles = new ArrayList<Rectangle>();//each rectangle drawn on the board
	
	

	public void start(Stage primaryStage) {
		VBox mainVBox = new VBox();
		mainVBox.setAlignment(Pos.CENTER);
		mainVBox.setSpacing(10);

		pane = new Pane();// See rubber lines for help
		pane.setPrefHeight(500);
		pane.setPrefWidth(500);
		mainVBox.getChildren().add(pane);


		rectangleBeingDrawn = false;
		anotherRectangle = new Rectangle(-10, -10);
		pane.getChildren().add(anotherRectangle);
		pane.setOnMouseClicked(this::handleMouseClicks);
		pane.setOnMouseMoved(this::handleMouseMotion);
		
		
		
		Scene scene = new Scene(mainVBox, 500, 650, Color.TRANSPARENT);

		primaryStage.setTitle("Rectangle Drawing GUI");
		primaryStage.setScene(scene);
		primaryStage.show();

		red = new RadioButton("Red");
		yellow = new RadioButton("Yellow");
		blue = new RadioButton("Blue");
		red.setSelected(true); 
		thinBorder = new RadioButton("Thin Border");
		thickBorder = new RadioButton("Thick Border");
		thinBorder.setSelected(true);

		ToggleGroup colorGroup = new ToggleGroup();
		red.setToggleGroup(colorGroup);
		yellow.setToggleGroup(colorGroup);
		blue.setToggleGroup(colorGroup);
		HBox colorChoiceBox = new HBox(red, yellow, blue);
		colorChoiceBox.setAlignment(Pos.CENTER);
		colorChoiceBox.setSpacing(10);
		mainVBox.getChildren().add(colorChoiceBox);

		ToggleGroup borderGroup = new ToggleGroup();
		thinBorder.setToggleGroup(borderGroup);
		thickBorder.setToggleGroup(borderGroup);

		HBox borderChoiceBox = new HBox(thinBorder, thickBorder);
		borderChoiceBox.setAlignment(Pos.CENTER);
		borderChoiceBox.setSpacing(10);
		mainVBox.getChildren().add(borderChoiceBox);

		fill = new CheckBox("Fill Rectangle");
		VBox fillBox = new VBox(fill);
		fillBox.setAlignment(Pos.CENTER);
		fillBox.setSpacing(10);
		mainVBox.getChildren().add(fillBox);
		
		clearButton = new Button("Clear");
		clearButton.setOnAction(this::handleButton);
		HBox buttonBox = new HBox(clearButton);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(10);
		mainVBox.getChildren().add(buttonBox);
	}

	private void handleMouseClicks(MouseEvent event) {
		
		
		if (!rectangleBeingDrawn) { // rectangleBeingDrawn==false
			rectangleBeingDrawn = true;			
			rectangle = new Rectangle();
			x = event.getX();
			y = event.getY();
			
			rectangle.setX(x);
			rectangle.setY(y);
			anotherRectangle.setX(x);
			anotherRectangle.setY(y);
			
		} else { // rectangleBeingDrawn==true
			rectangleBeingDrawn = false;
			drawnRectangles.add(rectangle);
			pane.getChildren().add(drawnRectangles.get(count));
			count++;
		}
		
		//Color Selection
		ArrayList<RadioButton> radioButtonColorList = new ArrayList<RadioButton>();
		radioButtonColorList.add(red);
		radioButtonColorList.add(yellow);
		radioButtonColorList.add(blue);

		for (RadioButton colorButton : radioButtonColorList) {
			if (red.isSelected()) {
				rectangle.setStroke(Color.RED);
				anotherRectangle.setStroke(Color.RED);
			} else if (yellow.isSelected()) {
				rectangle.setStroke(Color.YELLOW);
				anotherRectangle.setStroke(Color.YELLOW);
			} else {// blue.isSelected()
				rectangle.setStroke(Color.BLUE);
				anotherRectangle.setStroke(Color.BLUE);
			}
		}

		//BorderWidth Selection
		ArrayList<RadioButton> radioButtonBorderWidth = new ArrayList<RadioButton>();
		radioButtonBorderWidth.add(thinBorder);
		radioButtonBorderWidth.add(thickBorder);

		for (RadioButton borderThicknessButton : radioButtonBorderWidth) {
			if (thinBorder.isSelected()) {
				rectangle.setStrokeWidth(2);
				anotherRectangle.setStrokeWidth(2);
			} else { // if(thickBorder.isSelected())
				rectangle.setStrokeWidth(7);
				anotherRectangle.setStrokeWidth(7);
			}
		}
		
		//Fill Selection
		if(fill.isSelected()) {
			if (red.isSelected()) {
				rectangle.setFill(Color.RED);
				anotherRectangle.setFill(Color.RED);
			} else if (yellow.isSelected()) {
				rectangle.setFill(Color.YELLOW);
				anotherRectangle.setFill(Color.YELLOW);
			} else {// blue.isSelected()
				rectangle.setFill(Color.BLUE);
				anotherRectangle.setFill(Color.BLUE);
			}
		} else {//!fill.isSelected()
			rectangle.setFill(Color.TRANSPARENT);
			anotherRectangle.setFill(Color.TRANSPARENT);
		}
		
		
		

	}

	private void handleMouseMotion(MouseEvent event) {
		if (rectangleBeingDrawn) {
			double x2 = event.getX();
			double y2 = event.getY();
			
			if(x2<x) {
				//movingLeft = true;
				rectangle.setX(x2);
				rectangle.setWidth(x-x2);
				anotherRectangle.setX(x2);
				anotherRectangle.setWidth(x-x2);
			} else {//x>=x
				//movingLeft = false;
				rectangle.setWidth(x2-x);
				anotherRectangle.setWidth(x2-x);
			}
			
			if(y2<y) {
				//movingUp = true;
				rectangle.setY(y2);
				rectangle.setHeight(y-y2);
				anotherRectangle.setY(y2);
				anotherRectangle.setHeight(y-y2);
			} else {//y2>=y
				//movingUp = false;
				rectangle.setHeight(y2-y);
				anotherRectangle.setHeight(y2-y);
			}
			
			//System.out.println("x" + x + "    y:" + y);
			//Whatever I click will always be the coordinate. 
			//System.out.println("x2: " + x2 + "    y" + y2);
			//as you move up, y2 becomes lower. as you move left, x2 becomes lower. 
			//System.out.println((x2-x) + "   " + (y2-y));
			
		} 

	}

	private void handleButton(ActionEvent event) {

		for(int i = 0; i<drawnRectangles.size(); i++) {
			pane.getChildren().remove(drawnRectangles.get(i));
			
		}
		
		pane.getChildren().remove(anotherRectangle);
		
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
