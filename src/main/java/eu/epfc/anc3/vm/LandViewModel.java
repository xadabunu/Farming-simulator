package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.LandContent;
import javafx.beans.property.ReadOnlyObjectProperty;

public class LandViewModel {

    private final int line, column;

    public LandViewModel(int line, int column) {
        this.line = line;
        this.column = column;
    }

    //public ReadOnlyObjectProperty<LandContent>
}
