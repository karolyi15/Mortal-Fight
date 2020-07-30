package GameFactory;

import java.util.ArrayList;


public class Warrior  extends Character{
    CharacterType character;
    ElementType element;
    ArrayList<Weapon> listWeapons;
    int life;
    AbstractFactory weaponFactory;
    boolean state;

    Warrior(){
        this.life = 100;
        this.weaponFactory = new WeaponFactory();
        this.listWeapons = new ArrayList<Weapon>();
        this.state = true;
        createWeapons();
    }

    public void createWeapons(){
        for(int i = 0; i< WeaponType.values().length; i++){
            Weapon weapon  = this.weaponFactory.getWeapon(WeaponStyle.SIMPLEWEAPON);
            weapon.setWeaponType(WeaponType.values()[i]);
            this.listWeapons.add(weapon);
        }
    }

    public void reloadWeapons(){
        for(int i = 0; i< this.listWeapons.size(); i++){
            this.listWeapons.get(i).setState(true);
        }
    }

    public void takeDamage(int damage){
        System.out.println("Character :"+this.character.name());
        System.out.println("Element :"+this.element);
        System.out.println("Take Damage: "+ damage);
        System.out.println("Life before the attack :"+this.life);
        if(checkLife()==true){
            this.life = this.life - damage;
            System.out.println("Life after the attack :"+this.life);
            if(checkLife()==false){
                killMe();
            }
        }else{
            System.out.println("The character died");
        }
    }

    public boolean checkLife(){
        if(this.life > 0){
            return true;
        }
        return false;
    }

    public void killMe(){
        this.setState(false);
        System.out.println("The character died");
    }

    public int attack(WeaponType weapon, Character enemy){
        Weapon weaponSelected = getWeapon(weapon);
        if(weaponSelected.getState() == false){
            return 0;
        }else{
            weaponSelected.setState(false);
            return weaponSelected.getListDamagePerElement().get(enemy.getElement());
        }
    }

    public Weapon getWeapon(WeaponType weaponType){
        for(int i = 0 ; i<this.listWeapons.size(); i++){
            if(this.listWeapons.get(i).getWeaponType() == weaponType){
                   return this.listWeapons.get(i);
            }
        }
        return null;
    }

    public CharacterType getCharacterType() {
        return character;
    }

    public void setCharacter(CharacterType _character){
        this.element = _character.getElement();
        this.character = _character;
    }

    public boolean getState(){
        return state;
    }

    public void setState(boolean state){
        this.state=state;
    }

    public ElementType getElement(){ return this.element;}

    public void setElement(ElementType element){ this.element = element;}

    public void getData() {
        System.out.println("*Character: "+this.character.name());
        System.out.println("*Element: "+this.element.name());
        System.out.println("*Life: "+this.life);
        System.out.println("*State: "+this.state);
        for(int i = 0 ; i<this.listWeapons.size(); i++){
            System.out.println("    -Weapon "+i+" : "+ this.listWeapons.get(i).getWeaponType().name());
            System.out.println("    -State: "+ this.listWeapons.get(i).getState());
            for(int j = 0 ; j<this.listWeapons.get(i).getListDamagePerElement().size(); j++){
                System.out.println("        Element: " + ElementType.values()[j].name()+" / # of Damage: " + this.listWeapons.get(i).getListDamagePerElement().get(ElementType.values()[j]));
            }
        }
    }
}
