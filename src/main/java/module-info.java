module com.ptasinska {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;

    opens com.ptasinska to javafx.fxml;
    exports com.ptasinska;
    exports com.ptasinska.controller;
    opens com.ptasinska.controller to javafx.fxml;

}