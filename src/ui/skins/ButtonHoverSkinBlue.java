package ui.skins;

import com.jfoenix.transitions.JFXFillTransition;
import com.sun.javafx.scene.control.skin.ButtonSkin;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ButtonHoverSkinBlue extends ButtonSkin {
    public ButtonHoverSkinBlue(Button button) {
        super(button);

        Color buttonColor = new Color(0.06274,0.44314,0.87843,1);
        Color onHoverButton = new Color(0.85098,0.94509,1,1);

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
