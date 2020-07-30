package GameFactory;

import java.util.Hashtable;

public abstract class  Weapon{
    private WeaponType weaponType;
    private Hashtable<ElementType, Integer> listDamagePerElement ;
    private boolean state;

    public abstract void setDamagePerElement();
    public abstract int getRandomDamage();
    public abstract Hashtable<ElementType, Integer> getListDamagePerElement();
    public abstract void setListDamagePerElement(Hashtable<ElementType, Integer> listDamagePerElement);
    public abstract WeaponType getWeaponType();
    public abstract void setWeaponType(WeaponType weaponType);
    public abstract boolean getState();
    public abstract void setState(boolean state);
    public abstract void changeState();

}
