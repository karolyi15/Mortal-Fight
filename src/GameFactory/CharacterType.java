package GameFactory;

public enum CharacterType {

    //https://www.deviantart.com/herocollector16/art/Top-20-Greatest-Mortal-Kombat-Characters-of-All-Ti-811835919

    CYRAX(ElementType.IRON,"file:Resources/Imgs/CYRAX.jpeg"),
    NOOBSAIBOT(ElementType.IRON,"file:Resources/Imgs/NOOBSAIBOT.jpeg"),
    REPTILE(ElementType.ACID,"file:Resources/Imgs/REPTILE.jpeg"),
    GORO(ElementType.BLACKMAGIC,"file:Resources/Imgs/GORO.jpeg"),
    KANO(ElementType.ELECTRIC,"file:Resources/Imgs/KANO.jpeg"),
    BARAKA(ElementType.ACID,"file:Resources/Imgs/BARAKA.jpeg"),
    SHANGTSUNG(ElementType.ELECTRIC,"file:Resources/Imgs/SHANGTSUNG.jpeg"),
    JAX(ElementType.FIRE,"file:Resources/Imgs/JAX.jpeg"),
    MILEENA(ElementType.AIR,"file:Resources/Imgs/MILEENA.jpeg"),
    SHAOKAHN(ElementType.MAGIC,"file:Resources/Imgs/SHAOKAHN.jpeg"),
    SONYABLADE(ElementType.ICE,"file:Resources/Imgs/SONYABLADE.jpeg"),
    KENSHI(ElementType.WATER,"file:Resources/Imgs/KENSHI.jpeg"),
    KUNGLAO(ElementType.BLACKMAGIC,"file:Resources/Imgs/KUNGLAO.jpeg"),
    KITANA(ElementType.ICE,"file:Resources/Imgs/KITANA.jpeg"),
    RAIDEN(ElementType.IRON,"file:Resources/Imgs/RAIDEN.jpeg"),
    CASSIECAGE(ElementType.SPIRITUAL,"file:Resources/Imgs/CASSIECAGE.jpeg"),
    JOHNNYCAGE(ElementType.FIRE,"file:Resources/Imgs/JOHNNYCAGE.jpeg"),
    LIUKANG(ElementType.FIRE,"file:Resources/Imgs/LIUKANG.jpeg"),
    SUBZERO(ElementType.ICE,"file:Resources/Imgs/SUBZERO.jpeg"),
    SCORPION(ElementType.FIRE,"file:Resources/Imgs/SCORPION.jpeg");

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
