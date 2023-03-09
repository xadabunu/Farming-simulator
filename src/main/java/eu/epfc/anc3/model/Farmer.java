package eu.epfc.anc3.model;

class Farmer extends Character {

    Farmer(Field field) {
        super(field);
    }

//    boolean plantGrass(Land land) {
//        if (land.contentProperty().isEqualTo(LandContent.DIRT).get()) {
//            land.setContent(LandContent.GRASS);
//            return true;
//        }
//        return false;
//    }
//
//    boolean unplant(Land land) {
//        if (!land.contentProperty().isEqualTo(LandContent.DIRT).get()) {
//            land.setContent(LandContent.DIRT);
//            return true;
//        }
//        return false;
//    }

    boolean plantGrass() {
        if (position.get().contentProperty().isEqualTo(LandContent.DIRT).get()) {
            position.get().setContent(LandContent.GRASS);
            return true;
        }
        return false;
    }

    boolean plantCarrot() {
        if (position.get().contentProperty().isEqualTo(LandContent.DIRT).get() || position.get().contentProperty().isEqualTo(LandContent.GRASS).get()) {
            position.get().setContent(LandContent.CARROT);
            return true;
        }
        return false;
    }

    boolean plantCabbage() {
        if (position.get().contentProperty().isEqualTo(LandContent.DIRT).get() || position.get().contentProperty().isEqualTo(LandContent.GRASS).get()) {
            position.get().setContent(LandContent.CABBAGE);
            return true;
        }
        return false;
    }


    boolean unplant() {
        if (!position.get().contentProperty().isEqualTo(LandContent.DIRT).get()) {
            position.get().setContent(LandContent.DIRT);
            return true;
        }
        return false;
    }
}
