package tech.minesoft.minesite.core.api;

public abstract class VersionConfig {

    public abstract void init();

    public abstract void upgrade(int oldVersion,int newVersion);

}
