package eu.poliks.pspigot.knockback;

import lombok.Getter;
import lombok.Setter;
import eu.poliks.pspigot.KnockBack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

@Getter
@Setter
public class KnockbackProfile extends KnockBack {

     private String name;
     private double hor = 1.0D, ver = 1.0D, horGround = 1.0D, verGround = 1.0D, horSprint = 1.0D, verSprint = 1.0D, horWtap = 1.0D, verWtap= 1.0D;
     private boolean comboMode = false;
     private double comboHor = 1.0D, comboLimit = 1.0D, comboIncrease = 1.0D, comboReducer = 0.1;

    public KnockbackProfile(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String[] getBasicValues() {
        return new String[] {
                "Horizontal: " + ChatColor.WHITE + hor,
                "Vertical: " + ChatColor.WHITE + ver,
                "Ground Horizontal: " + ChatColor.WHITE + horGround,
                "Ground Vertical: " + ChatColor.WHITE + verGround,
                "Sprint Horizontal: " + ChatColor.WHITE + horSprint,
                "Sprint Vertical: " + ChatColor.WHITE + verSprint,
                "W-Tap Horizontal: " + ChatColor.WHITE + horWtap,
                "W-Tap Vertical: " + ChatColor.WHITE + verWtap,
                "Combo Mode: " + ChatColor.WHITE + comboMode,
        };
    }

    @Override
    public double getHor() {
        return hor;
    }

    @Override
    public void setHor(double hor) {
        this.hor = hor;
    }

    @Override
    public double getVer() {
        return ver;
    }

    @Override
    public void setVer(double ver) {
        this.ver = ver;
    }

    @Override
    public String[] getComboValues() {
        return new String[] {
                "Combo Mode: " + ChatColor.WHITE + comboMode,
                "Combo Horizontal: " + ChatColor.WHITE + comboHor,
                "Combo Limit: " + ChatColor.WHITE + comboLimit,
                "Combo Increase: " + ChatColor.WHITE + comboIncrease,
                "Combo Reducer: " + ChatColor.WHITE + comboReducer,
        };
    }

    @Override
    public double getHorGround() {
        return horGround;
    }

    @Override
    public void setHorGround(double horGround) {
        this.horGround = horGround;
    }

    @Override
    public double getVerGround() {
        return verGround;
    }

    @Override
    public void setVerGround(double verGround) {
        this.verGround = verGround;
    }

    @Override
    public double getHorSprint() {
        return horSprint;
    }

    @Override
    public void setHorSprint(double horSprint) {
        this.horSprint = horSprint;
    }

    @Override
    public double getVerSprint() {
        return verSprint;
    }

    @Override
    public void setVerSprint(double verSprint) {
        this.verSprint = verSprint;
    }

    @Override
    public double getHorWtap() {
        return horWtap;
    }

    @Override
    public void setHorWtap(double horWtap) {
        this.horWtap = horWtap;
    }

    @Override
    public double getVerWtap() {
        return verWtap;
    }

    @Override
    public void setVerWtap(double verWtap) {
        this.verWtap = verWtap;
    }

    @Override
    public boolean isComboMode() {
        return comboMode;
    }

    @Override
    public void setComboMode(boolean comboMode) {
        this.comboMode = comboMode;
    }

    @Override
    public double getComboHor() {
        return comboHor;
    }

    @Override
    public void setComboHor(double comboHor) {
        this.comboHor = comboHor;
    }

    @Override
    public double getComboLimit() {
        return comboLimit;
    }

    @Override
    public void setComboLimit(double comboLimit) {
        this.comboLimit = comboLimit;
    }

    @Override
    public double getComboIncrease() {
        return comboIncrease;
    }

    @Override
    public void setComboIncrease(double comboIncrease) {
        this.comboIncrease = comboIncrease;
    }

    @Override
    public double getComboReducer() {
        return comboReducer;
    }

    @Override
    public void setComboReducer(double comboReducer) {
        this.comboReducer = comboReducer;
    }

    @Override
    public void save() {
        final String path = "KnockBack.settings." + this.name;

        Bukkit.getServer().setInConfig(path + ".horizontal", this.hor);
        Bukkit.getServer().setInConfig(path + ".vertical", this.ver);
        Bukkit.getServer().setInConfig(path + ".ground-horizontal", this.horGround);
        Bukkit.getServer().setInConfig(path + ".ground-vertical", this.verGround);
        Bukkit.getServer().setInConfig(path + ".sprint-horizontal", this.horSprint);
        Bukkit.getServer().setInConfig(path + ".sprint-vertical", this.verSprint);
        Bukkit.getServer().setInConfig(path + ".wtap-horizontal", this.horWtap);
        Bukkit.getServer().setInConfig(path + ".wtap-vertical", this.verWtap);
        Bukkit.getServer().setInConfig(path + ".combo-mode", this.comboMode);
        Bukkit.getServer().setInConfig(path + ".combo-horizontal", this.comboHor);
        Bukkit.getServer().setInConfig(path + ".combo-limit", this.comboLimit);
        Bukkit.getServer().setInConfig(path + ".combo-increase", this.comboIncrease);
        Bukkit.getServer().setInConfig(path + ".combo-reducer", this.comboReducer);
        Bukkit.getServer().savePSpigot();
    }

}
