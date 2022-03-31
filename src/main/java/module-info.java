module com.ptasinska {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires jakarta.xml.bind;

    opens com.ptasinska to javafx.fxml;
    exports com.ptasinska;
    exports com.ptasinska.controller;
    exports com.ptasinska.data;
    opens com.ptasinska.controller to javafx.fxml;
    opens com.ptasinska.data to jakarta.xml.bind;

}