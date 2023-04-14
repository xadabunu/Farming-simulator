package eu.epfc.anc3.model;

public class Copy implements Memento {

    private final Game game;
    private final Field fieldCopy;
    private final Farmer farmerCopy;
    private final int daysCopy;

    Copy(Game game, Field field, Farmer farmer, int day) {
        this.game = game;
        fieldCopy = new Field(field);
        farmerCopy = new Farmer(farmer, fieldCopy);
        daysCopy = day;
    }

    @Override
    public void restore() {
        game.restoreField(fieldCopy);
        game.restoreFarmer(farmerCopy);
        game.restoreDay(daysCopy);
    }
}
