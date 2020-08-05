package GameFactory;

public enum CharacterType {

    //https://www.deviantart.com/herocollector16/art/Top-20-Greatest-Mortal-Kombat-Characters-of-All-Ti-811835919

    CYRAX(ElementType.IRON,"file:Resources/Imgs/CYRAX.jpg"),
    NOOBSAIBOT(ElementType.IRON,"file:Resources/Imgs/NOOBSAIBOT.jpg"),
    REPTILE(ElementType.ACID,"file:Resources/Imgs/REPTILE.jpg"),
    GORO(ElementType.BLACKMAGIC,"file:Resources/Imgs/GORO.jpg"),
    KANO(ElementType.ELECTRIC,"file:Resources/Imgs/KANO.jpg"),
    BARAKA(ElementType.ACID,"file:Resources/Imgs/BARAKA.jpg"),
    SHANGTSUNG(ElementType.ELECTRIC,"file:Resources/Imgs/SHANGTSUNG.jpg"),
    JAX(ElementType.FIRE,"file:Resources/Imgs/JAX.jpg"),
    MILEENA(ElementType.AIR,"file:Resources/Imgs/MILEENA.jpg"),
    SHAOKAHN(ElementType.MAGIC,"file:Resources/Imgs/SHAOKAHN.jpg"),
    SONYABLADE(ElementType.ICE,"file:Resources/Imgs/SONYABLADE.jpg"),
    KENSHI(ElementType.WATER,"file:Resources/Imgs/KENSHI.jpeg"),
    KUNGLAO(ElementType.BLACKMAGIC,"file:Resources/Imgs/KUNGLAO.jpg"),
    KITANA(ElementType.ICE,"file:Resources/Imgs/KITANA.jpg"),
    RAIDEN(ElementType.IRON,"file:Resources/Imgs/RAIDEN.jpg"),
    CASSIECAGE(ElementType.SPIRITUAL,"file:Resources/Imgs/CASSIECAGE.jpg"),
    JOHNNYCAGE(ElementType.FIRE,"file:Resources/Imgs/JOHNNYCAGE.jpg"),
    LIUKANG(ElementType.FIRE,"file:Resources/Imgs/LIUKANG.jpg"),
    SUBZERO(ElementType.ICE,"file:Resources/Imgs/SUBZERO.jpg"),
    SCORPION(ElementType.FIRE,"file:Resources/Imgs/SCORPION.jpg");

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
