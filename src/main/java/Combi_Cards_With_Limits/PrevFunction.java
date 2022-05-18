package Combi_Cards_With_Limits;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PrevFunction extends AbstractAction {
    PrevFunction() {
        super("Move Focus Backwards");
    }

    public void actionPerformed(ActionEvent evt) {
        ((Component) evt.getSource()).transferFocusBackward();
    }
}

