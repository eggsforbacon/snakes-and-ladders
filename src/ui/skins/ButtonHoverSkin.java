package ui.skins;

import com.jfoenix.transitions.JFXFillTransition;
import com.sun.javafx.scene.control.skin.ButtonSkin;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ButtonHoverSkin extends ButtonSkin {
    public ButtonHoverSkin(Button button) {
        super(button);
        Color buttonColor = new Color(0.909803,0.52157,0.15294,1);
        Color onHoverButton = new Color(0.909803,0.86274,0.81961,1);

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
