package GameFactory;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public abstract class Character {
    CharacterType character;
    ElementType element;
    ArrayList<Weapon> listWeapons;
    int life;
    AbstractFactory weaponFactory;
    boolean state;

    public abstract void createWeapons();

    public abstract void reloadWeapons();

    public abstract void takeDamage(int damage);

    public abstract int attack(WeaponType weapon, Character enemry);

    public abstract CharacterType getCharacterType();

    public abstract void setCharacter(CharacterType _character);

    public abstract boolean getState();

    public abstract void setState(boolean state);

    public abstract ElementType getElement();
    public abstract void setElement(ElementType element);

    public abstract void getData();
    public abstract JSONObject toJson();
}
