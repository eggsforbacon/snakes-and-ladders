package ui.skins;

import com.jfoenix.transitions.JFXFillTransition;
import com.sun.javafx.scene.control.skin.ButtonSkin;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ButtonHoverSkinGelb extends ButtonSkin {
    public ButtonHoverSkinGelb(Button button) {
        super(button);

        Color buttonColor = new Color(0.85098,0.741176,0.043137,1);
        Color onHoverButton = new Color(0.960784,0.917647,0.662745,1);

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
