package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField nameTf , phoneNumberTf , addressTf;
    private RadioButton smallRb , largeRb , mediumRb,thinRb , thickRb;
    private CheckBox  pepperoniCb , sausageCb, linguicaCb , olivesCb , mushroomsCb, tomatoesCb , anchoviesCb;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Label titleLb = new Label("Order Your Pizza Now!");
        titleLb.setFont(Font.font(20));
        HBox titleHbox = new HBox(20,titleLb);
        titleHbox.setPadding(new Insets(20));


        Label nameLb = new Label("Name : ");
        nameTf = new TextField();
        nameTf.setPrefColumnCount(20);
        nameTf.setPromptText("Enter the customer's name");
        nameTf.setMaxWidth(Double.MAX_VALUE);


        Label phoneNumberLb = new Label("Phone Number : " + " ");
        phoneNumberLb.setPrefWidth(100);
        phoneNumberTf = new TextField();
        phoneNumberTf.setPrefColumnCount(20);
        phoneNumberTf.setPromptText("Enter the customer's phone Number");
        phoneNumberTf.setMaxWidth(Double.MAX_VALUE);


        Label addressLb = new Label("Address : ");
        addressLb.setPrefWidth(100);
        addressTf = new TextField();
        addressTf.setPrefColumnCount(20);
        addressTf.setPromptText("Enter the customer's address");

        VBox labelVbox=new VBox(13,nameLb,phoneNumberLb,addressLb);
        VBox textVbox=new VBox(5,nameTf,phoneNumberTf,addressTf);
        HBox customerDetailHbox=new HBox(labelVbox,textVbox);

        Label sizeLb = new Label("Size ");
        smallRb = new RadioButton("Small ");
        mediumRb = new RadioButton("Medium");
        largeRb = new RadioButton("Large");
        mediumRb.setSelected(true);
        ToggleGroup sizeTogglegroup = new ToggleGroup();
        smallRb.setToggleGroup(sizeTogglegroup);
        mediumRb.setToggleGroup(sizeTogglegroup);
        largeRb.setToggleGroup(sizeTogglegroup);


        VBox sizeVbox = new VBox(sizeLb , smallRb , mediumRb , largeRb);
        sizeVbox.setSpacing(10);


        Label crustLb = new Label("Crust");
        thinRb = new RadioButton("Thin");
        thickRb= new RadioButton("Thick");
        thinRb.setSelected(true);
        ToggleGroup crustTogglegroup = new ToggleGroup();
        thinRb.setToggleGroup(crustTogglegroup);
        thickRb.setToggleGroup(crustTogglegroup);

        VBox crustVbox = new VBox(crustLb, thinRb , thickRb);
        crustVbox.setSpacing(10);


        Label toppingLb  = new Label("Toppings");
        pepperoniCb = new CheckBox("Pepperoni");
        sausageCb = new CheckBox("Sausage");
        linguicaCb = new CheckBox("Linguica");
        olivesCb = new CheckBox("Olives");
        mushroomsCb = new CheckBox("Mushrooms");
        tomatoesCb = new CheckBox("Tomatoes");
        anchoviesCb = new CheckBox("Anchovies");


        FlowPane toppingsFlowPane = new FlowPane(Orientation.VERTICAL , pepperoniCb ,sausageCb , linguicaCb , olivesCb , mushroomsCb , tomatoesCb , anchoviesCb);
        toppingsFlowPane.setPadding(new Insets(10,0,10,0));
        toppingsFlowPane.setHgap(20);
        toppingsFlowPane.setVgap(10);
        toppingsFlowPane.setPrefWrapLength(100);


        VBox toppingVbox = new VBox(toppingLb , toppingsFlowPane);
        HBox pizzaDetailHbox = new HBox(50 , sizeVbox , crustVbox , toppingVbox);
        VBox centerVbox = new VBox(20, customerDetailHbox, pizzaDetailHbox);
        centerVbox.setPadding(new Insets(0,10, 0, 10));


        Button okBtn = new Button("OK");
        okBtn.setPrefWidth(80);
        okBtn.setOnAction(e -> btnOK_Click() );
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setPrefWidth(80);
        cancelBtn.setOnAction(e ->
                primaryStage.close()
        );
        Region spacer = new Region();
        HBox bottomHbox = new HBox(10, spacer, okBtn, cancelBtn);
        bottomHbox.setHgrow(spacer, Priority.ALWAYS);
        bottomHbox.setPadding(new Insets(20, 10, 20, 10));

        BorderPane root = new BorderPane();
        root.setTop(titleHbox);
        root.setCenter(centerVbox);
        root.setBottom(bottomHbox);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pizza Order");
        primaryStage.show();


    }

    private void btnOK_Click() {
        String str = "Customer's Details:\n\n";
        str += "\t" + "Customer's Name : " + nameTf.getText() + "\n";
        str += "\t" + "Customer's Phone Number : " + phoneNumberTf.getText() + "\n";
        str += "\t" + "Customer's Address : " + addressTf.getText() +"\n";


        if (smallRb.isSelected())
            str += "\t" + "Pizza Size : small \n";
        if (mediumRb.isSelected())
            str += "\t" + "Pizza Size : medium \n";
        if (largeRb.isSelected())
            str += "\t" + "Pizza Size : large \n";


        if (thinRb.isSelected())
            str += "\t" + "Pizza Crust : thin\n";
        if (thickRb.isSelected())
            str += "\t" + "Pizza Crust : thick\n";


        String toppings = "";
        toppings = buildToppings(pepperoniCb,toppings);
        toppings = buildToppings(sausageCb,toppings);
        toppings = buildToppings(linguicaCb,toppings);
        toppings = buildToppings(olivesCb,toppings);
        toppings = buildToppings(tomatoesCb,toppings);
        toppings = buildToppings(mushroomsCb,toppings);
        toppings = buildToppings(anchoviesCb,toppings);


        if (toppings.equals(""))
            str += "\t" + "no toppings.";
        else
            str += "\t" + "Topping\n"
                    + "\t" + toppings;


        Alert alert = new Alert(Alert.AlertType.INFORMATION ,"Order Details ");
        alert.setContentText(str);
        alert.show();
    }

    public String buildToppings(CheckBox checkBox, String str)
    {

        if (checkBox.isSelected())
        {
            if (!str.equals(""))
            {
                str += ", ";
            }
            str += checkBox.getText();
        }
        return str;
    }

    public void btnCancel_Click()
    {

    }


    public static void main(String[] args) {
        launch(args);
    }
}
