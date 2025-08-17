module gripsystem.grip {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;

    // Export the main package
    exports gripsystem.grip;
    
    // Export controller packages
    exports gripsystem.grip.controller;

    // Open packages for reflection (needed for FXML)
    opens gripsystem.grip to javafx.fxml;
    opens gripsystem.grip.controller to javafx.fxml;
    
    // Open for reflection
    opens gripsystem.grip.model;
    opens gripsystem.grip.service;
}