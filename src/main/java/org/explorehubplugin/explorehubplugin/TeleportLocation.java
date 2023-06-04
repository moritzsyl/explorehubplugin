package org.explorehubplugin.explorehubplugin;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.bukkit.Material;
import java.io.*;

public class TeleportLocation implements Serializable {
    private String name;
    private String desc;
    private String block;

    private Vector direction;
    private int x;
    private int y;
    private int z;

    public TeleportLocation(String name, Location location){
        this(name, "description", "GRASS", location.getDirection(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
    public TeleportLocation(String name, String desc, String block, Location location){
        this(name, desc, block, location.getDirection(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public TeleportLocation(String name, String desc, String block, Vector direction, int x, int y, int z){
        this.setName(name);
        this.setDesc(desc);
        this.setBlock(block);
        this.setDirection(direction);
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    public void setName(String name){
        if(name != null){
            this.name = name;
        }
    }

    public void setDesc(String desc){
        if(desc != null){
            this.desc = desc;
        }
    }

    public void setBlock(String block){
        try{
            Material.valueOf(block.toUpperCase());
            this.block = block.toUpperCase();
        } catch (IllegalArgumentException exc){
            this.block = "GRASS";
        }
    }

    public void setDirection(Vector direction){
        if(direction != null){
            this.direction = direction;
        }
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setZ(int z){
        this.z = z;
    }

    public String getName(){
        return this.name;
    }

    public String getDesc(){
        return this.desc;
    }

    public String getBlock(){
        return this.block;
    }

    public Vector getDirection(){
        return this.direction;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getZ(){
        return this.z;
    }
}
