package org.explorehubplugin.explorehubplugin;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.bukkit.Material;
import java.io.*;

/**
 * Die Klasse stellt ein TeleportLocation Objekt dar. Eine Location wird durch name, beschreibung, mcblock, direction, x, y, z.
 * Das Programm arbeitet anhand einer Liste von TeleportLocations.
 * @author Moritz Syllaba
 * @version 2023-06-05
 */
public class TeleportLocation implements Serializable {
    private String name;
    private String desc;
    private String block;

    private Vector direction;
    private int x;
    private int y;
    private int z;

    public TeleportLocation(String name, Location location) throws IOException{
        this(name, "description", "GRASS", location.getDirection(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
    public TeleportLocation(String name, String desc, String block, Location location) throws IOException{
        this(name, desc, block, location.getDirection(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public TeleportLocation(String name, String desc, String block, Vector direction, int x, int y, int z) throws IOException{
        this.setName(name);
        this.setDesc(desc);
        this.setBlock(block);
        this.setDirection(direction);
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    public void setName(String name) throws IOException{
        if(name != null && name != " "){
            this.name = name;
        }
        else{
            this.name = "defaultname";
            throw  new IOException("Invalid name");
        }
    }

    public void setDesc(String desc) throws IOException{
        if(desc != null){
            this.desc = desc;
        }
        else {
            this.desc = "default";
            throw new IOException("Invalid description");
        }
    }

    public void setBlock(String block) throws IOException{
        try{
            Material.valueOf(block.toUpperCase());
            this.block = block.toUpperCase();
        } catch (IllegalArgumentException exc){
            this.block = "GRASS";
            throw new IOException("Invalid block");
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
