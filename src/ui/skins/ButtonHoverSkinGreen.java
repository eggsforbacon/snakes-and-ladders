package ui.skins;

import com.jfoenix.transitions.JFXFillTransition;
import com.sun.javafx.scene.control.skin.ButtonSkin;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ButtonHoverSkinGreen extends ButtonSkin {
    public ButtonHoverSkinGreen(Button button) {
        super(button);
        Color buttonColor = new Color(0.113725,0.65098,0.086274,1);
        Color onHoverButton = new Color(0.85098,1,0.88235,1);

        button.setOnMouseEntered(e -> {
            JFXFillTransition colorEaseIn = new JFXFillTransition();
            colorEaseIn.setDuration(Duration.millis(200));
            colorEaseIn.setRegion(button);
            colorEaseIn.setFromValue(buttonColor);
            colorEaseIn.setToValue(onHoverButton);
            colorEaseIn.play();
        });

        button.setOnMouseExited(e -> {
            JFXFillTransition colorEaseOut = new JFXFillTransition();
            colorEaseOut.setDuration(Duration.millis(200));
            colorEaseOut.setRegion(button);
            colorEaseOut.setFromValue(onHoverButton);
            colorEaseOut.setToValue(buttonColor);
            colorEaseOut.play();
        });
    }
}
