package GameFactory;

import java.util.Hashtable;

public class SimpleWeapon extends Weapon{
    private WeaponType weaponType;
    private Hashtable<ElementType, Integer> listDamagePerElement ;
    private boolean state;

    SimpleWeapon(){
        this.listDamagePerElement= new Hashtable<ElementType, Integer>();
        this.state = true;
        setDamagePerElement();
    }
    public void setDamagePerElement(){
        for(int i = 0; i< ElementType.values().length; i++){
            this.listDamagePerElement.put(ElementType.values()[i],getRandomDamage());
        }
    };

    public int getRandomDamage(){
        return (int)(Math.random() * (100 - 20 + 1) + 20);
    }

    public Hashtable<ElementType, Integer> getListDamagePerElement() {
        return listDamagePerElement;
    }

    public void setListDamagePerElement(Hashtable<ElementType, Integer> listDamagePerElement) {
        this.listDamagePerElement = listDamagePerElement;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public boolean getState(){
        return state;
    }

    public void setState(boolean state){
        this.state=state;
    }

    public void changeState(){
        if(this.state == true){
            this.state = false;
        }else{
            this.state = true;
        }
    }
}
