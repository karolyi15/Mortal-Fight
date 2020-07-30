package GameFactory;

public interface AbstractFactory {
    public Character getCharacter(CharacterStyle characterStyle);
    public Weapon getWeapon(WeaponStyle weaponStyle);
}
