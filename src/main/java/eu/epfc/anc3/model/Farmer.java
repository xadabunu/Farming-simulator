package eu.epfc.anc3.model;

class Farmer extends Character {

    Farmer() {
    }

    boolean plantGrass(Land land) {
        if (land.contentProperty().isEqualTo(LandContent.DIRT).get()) {
            land.setContent(LandContent.GRASS);
            return true;
        }
        return false;
    }

    boolean unplant(Land land) {
        if (!land.contentProperty().isEqualTo(LandContent.DIRT).get()) {
            land.setContent(LandContent.DIRT);
            return true;
        }
        return false;
    }

}
