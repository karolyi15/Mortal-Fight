package GameFactory;

public enum CharacterType {

    //https://www.deviantart.com/herocollector16/art/Top-20-Greatest-Mortal-Kombat-Characters-of-All-Ti-811835919

    CYRAX(ElementType.IRON,"CYRAX.jpeg"),
    NOOBSAIBOT(ElementType.IRON,"NOOBSAIBOT.jpeg"),
    REPTILE(ElementType.ACID,"REPTILE.jpeg"),
    GORO(ElementType.BLACKMAGIC,"GORO.jpeg"),
    KANO(ElementType.ELECTRIC,"KANO.jpeg"),
    BARAKA(ElementType.ACID,"BARAKA.jpeg"),
    SHANGTSUNG(ElementType.ELECTRIC,"SHANGTSUNG.jpeg"),
    JAX(ElementType.FIRE,"JAX.jpeg"),
    MILEENA(ElementType.AIR,"MILEENA.jpeg"),
    SHAOKAHN(ElementType.MAGIC,"SHAOKAHN.jpeg"),
    SONYABLADE(ElementType.ICE,"SONYABLADE.jpeg"),
    KENSHI(ElementType.WATER,"KENSHI.jpeg"),
    KUNGLAO(ElementType.BLACKMAGIC,"KUNGLAO.jpeg"),
    KITANA(ElementType.ICE,"KITANA.jpeg"),
    RAIDEN(ElementType.IRON,"RAIDEN.jpeg"),
    CASSIECAGE(ElementType.SPIRITUAL,"CASSIECAGE.jpeg"),
    JOHNNYCAGE(ElementType.FIRE,"JOHNNYCAGE.jpeg"),
    LIUKANG(ElementType.FIRE,"LIUKANG.jpeg"),
    SUBZERO(ElementType.ICE,"SUBZERO.jpeg"),
    SCORPION(ElementType.FIRE,"SCORPION.jpeg");

    private ElementType element;
    private final String image;

    CharacterType(ElementType element, String image) {
        this.image = image;
        this.element = element;
    }

    //Setters and Getters
    public String getImage() { return image; }

    public ElementType getElement() {
        return element;
    }


}
