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
	private Rectangle rectangle, initialRectangle, paneBorder;
	private RadioButton red, yellow, blue, thinBorder, thickBorder;
	private CheckBox fill;
	private Button clearButton;
	private boolean rectangleBeingDrawn;
	private double x, y;
	private int count = 0;
	private boolean movingLeft, movingUp;
	
	
	ArrayList<Rectangle> drawnRectangles = new ArrayList<Rectangle>();//each rectangle drawn on the board
	
	

	public void start(Stage primaryStage) {
		VBox mainVBox = new VBox();
		mainVBox.setAlignment(Pos.CENTER);
		mainVBox.setSpacing(10);

		pane = new Pane();// See rubber lines for help
		pane.setPrefHeight(500);
		pane.setPrefWidth(500);
		mainVBox.getChildren().add(pane);
		/*//Can't draw over this rectangle if I do this
		paneBorder = new Rectangle(500, 500, Color.TRANSPARENT);
		paneBorder.setStroke(Color.BLACK);
		paneBorder.setStrokeWidth(3);
		mainVBox.getChildren().add(paneBorder);
		*/

		rectangleBeingDrawn = false;
		//initialRectangle = new Rectangle(-10, -10);// Color.TRANSPARENT
		//drawnRectangles.add(rectangle);
		//pane.getChildren().add(initialRectangle);
		//rectangle = new Rectangle();
		rectangle = new Rectangle(0, 0);
		pane.getChildren().add(rectangle);
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
			//rectangle = new Rectangle();
			
			x = event.getX();
			y = event.getY();
			
			rectangle.setX(x);
			rectangle.setY(y);
			
		} else { // rectangleBeingDrawn==true
			
			rectangleBeingDrawn = false;
			//drawnRectangles.add(rectangle);
			
			
			//pane.getChildren().add(drawnRectangles.get(count));
			//pane.getChildren().add(rectangle);
			//count++;
			
			
		}
		
		//Color Selection
		ArrayList<RadioButton> radioButtonColorList = new ArrayList<RadioButton>();
		radioButtonColorList.add(red);
		radioButtonColorList.add(yellow);
		radioButtonColorList.add(blue);

		for (RadioButton colorButton : radioButtonColorList) {
			if (red.isSelected()) {
				rectangle.setStroke(Color.RED);
			} else if (yellow.isSelected()) {
				rectangle.setStroke(Color.YELLOW);
			} else {// blue.isSelected()
				rectangle.setStroke(Color.BLUE);
			}
		}

		//BorderWidth Selection
		ArrayList<RadioButton> radioButtonBorderWidth = new ArrayList<RadioButton>();
		radioButtonBorderWidth.add(thinBorder);
		radioButtonBorderWidth.add(thickBorder);

		for (RadioButton borderThicknessButton : radioButtonBorderWidth) {
			if (thinBorder.isSelected()) {
				rectangle.setStrokeWidth(2);
			} else { // if(thickBorder.isSelected())
				rectangle.setStrokeWidth(7);
			}
		}
		
		//Fill Selection
		if(fill.isSelected()) {
			if (red.isSelected()) {
				rectangle.setFill(Color.RED);
			} else if (yellow.isSelected()) {
				rectangle.setFill(Color.YELLOW);
			} else {// blue.isSelected()
				rectangle.setFill(Color.BLUE);
			}
		} else {//!fill.isSelected()
			rectangle.setFill(Color.TRANSPARENT);
		}
		
		
		

	}

	private void handleMouseMotion(MouseEvent event) {
		if (rectangleBeingDrawn) {
			double x2 = event.getX();
			double y2 = event.getY();
			
			
			//rectangle.setWidth(x - x2);
			//rectangle.setHeight(y - y2);
			
			if(x2<0) {
				x2 = x;
				x = event.getX();
			} else {//x>=0
				rectangle.setWidth(x2);
			}
			
			if(y2<0) {
				y2 = y;
				y = event.getY();
			} else {//y2>=0
				rectangle.setHeight(y2);
			}
			
			//rectangle.setWidth(xEnd);
			//rectangle.setHeight(yEnd);
			
			//System.out.println((x2-x) + "   " + (y2-y));
			
		} 

	}

	private void handleButton(ActionEvent event) {
		//Clear the board. I'm thinking a for each loop, null on the ArrayList. 
		
		//drawnRectangles.clear();
		//drawnRectangles = null;
		//no such thing as pane.clear();
		/*
		for(int i = 0; i<drawnRectangles.size(); i++) {
			drawnRectangles.remove(i);
		}
		*/
		
		
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
