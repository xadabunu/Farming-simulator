package eu.epfc.anc3.model;

public class Farmer extends Character {

    public boolean plant(Plantable plantable, Land land) {
        return true;
    }
    public boolean unplant(Land land) {
        return true;
    }

}
