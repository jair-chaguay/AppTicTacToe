module ec.edu.espol.tictactoev1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.tictactoev1 to javafx.fxml;
    exports ec.edu.espol.tictactoev1;
}
