package eu.poliks.pspigot;

public abstract class KnockBack {

    public abstract String[] getComboValues();

    public abstract String getName();

    public abstract String[] getBasicValues();

    public abstract double getHor();

    public abstract void setHor(double hor);

    public abstract double getVer();

    public abstract void setVer(double ver);

    public abstract double getHorGround();

    public abstract void setHorGround(double horGround);

    public abstract double getVerGround();

    public abstract void setVerGround(double verGround);

    public abstract double getHorSprint();

    public abstract void setHorSprint(double horSprint);

    public abstract double getVerSprint();

    public abstract void  setVerSprint(double verSprint);

    public abstract double getHorWtap();

    public abstract void setHorWtap(double horWtap);

    public abstract double getVerWtap();

    public abstract void setVerWtap(double verWtap);

    public abstract boolean isComboMode();

    public abstract void setComboMode(boolean comboMode);

    public abstract double getComboHor();

    public abstract void  setComboHor(double comboHor);

    public abstract double getComboLimit();

    public abstract void setComboLimit(double comboLimit);

    public abstract double getComboIncrease();

    public abstract void setComboIncrease(double comboIncrease);

    public abstract double getComboReducer();

    public abstract void setComboReducer(double comboReducer);

    public abstract void save();

}
