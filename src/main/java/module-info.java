module no.ntnu.olekel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens no.ntnu.olekel to javafx.fxml;
    opens no.ntnu.olekel.controllers to javafx.fxml;
    opens no.ntnu.olekel.ui to javafx.fxml;
    opens no.ntnu.olekel.core to javafx.base;
    exports no.ntnu.olekel.controllers;
    exports no.ntnu.olekel;
}