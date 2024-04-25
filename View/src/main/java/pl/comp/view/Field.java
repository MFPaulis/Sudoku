
package pl.comp.view;

import javafx.scene.control.TextField;


public class Field extends TextField {
    private int valueX;
    private int valueY;
    private boolean isChangeable;
    
    public int getValueX() {
        return valueX;
    }

    public int getValueY() {
        return valueY;
    }

    public boolean isIsChangeable() {
        return isChangeable;
    }
    
    public Field(final int x, final int y, final String text) {
        super();
        this.valueX = x;
        this.valueY = y;
        this.setPrefSize(50,50);
        this.isChangeable = true;
        if (!text.equals("0")) {
            this.setPromptText(text);
            isChangeable = false;
        } else {
            isChangeable = true;
        }
        this.setDisable(!isChangeable);
    }
}
