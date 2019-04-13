package fr.WarzouMc.MonaiServGroup.fileConfiguration.config;

import fr.WarzouMc.MonaiServGroup.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigSetup {

    /**************
     **BaseConfig**
     **************/

    public static class BaseConfig extends Main {

        private FileConfiguration config;

        public BaseConfig(FileConfiguration config) {this.config = config;}

        /************
         **AllMoney**
         ************/

        public void addAllMoney(int i){
            config.set("allmonai", config.getInt("allmonai") + i);
        }

        /****************
         **Player Money**
         ****************/

        public void createMoneySection(String playerName){
            config.createSection("monai." + playerName);
        }

        public void setMoney(int i, String playerName){
            config.set("monai." + playerName, i);
        }

        public void addMoney(int i, String playerName){
            config.set("monai." + playerName, config.getInt("monai." + playerName) + i);
        }

        /***************
         **Player List**
         ***************/

        public List<?> getList(){
            return config.getList("allp");
        }

        public void addInList(String playerName){
            List<String> l = ( List<String> ) getList();
            config.set("allp", l.add(playerName));
        }

        public void removeInList(String playerName){
            List<String> l = ( List<String> ) getList();
            config.set("allp", l.remove(playerName));
        }

        /********
         **File**
         ********/

        public void save(){saveConfig();}

    }

    /**************
     **ChatConfig**
     **************/

    public static class ChatConfig extends Main {

        private FileConfiguration config;

        public ChatConfig(FileConfiguration chatConfig) {this.config = chatConfig;}

        /***********
         **contain**
         ***********/

        public boolean contain(String playerName){
            if(config.contains(playerName)){
                return true;
            }else{
                return false;
            }
        }

        public void createSection(String playerName){
            config.createSection(playerName);
        }

        /**********
         **setter**
         **********/

        public void set(int i, String playerName){
            config.set(playerName, i);
        }

        /**********
         **getter**
         **********/

        public int get(String playerName){
            return config.getInt(playerName);
        }

        /********
         **File**
         ********/

        public void save() {
            try {
                config.save(getChat());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void update(){
            YamlConfiguration.loadConfiguration(getChat());
        }

    }

    /**************
     **PermConfig**
     **************/

    public static class PermConfig extends Main {

        private FileConfiguration config;

        public PermConfig(FileConfiguration permConfig) {this.config = permConfig;}

        /**********
         **Create**
         **********/

        public void created(){
            config.createSection("Groups");
            config.createSection("Groups.op");
            config.createSection("Groups.member");
            config.createSection("Groups.op.builder");
            config.createSection("Groups.op.dev");
            config.createSection("Groups.member.player");

            List op = new ArrayList();
            List player = new ArrayList();

            op.add("SkyExpender.op");
            player.add("SkyExpander.p");

            config.set("Groups.op.builder", op);
            config.set("Groups.op.dev", op);
            config.set("Groups.member.player", player);
        }

        public void createSection(String playerName, String befor, String perms, String display, String colormsg){
            config.createSection(playerName);
            config.createSection(playerName+".Perm");
            config.createSection(playerName+".type");
            config.createSection(playerName+".DisplayName");
            config.createSection(playerName+".ColorMessage");
            config.createSection(playerName+".Prefix");

            setPerms(playerName, perms);
            setBefor(playerName, befor);
            setColorMsg(playerName, colormsg);
            setDisplay(playerName, display);
        }

        /***********
         **contain**
         ***********/

        public boolean contain(String playerName){
            if(config.contains(playerName)){
                return true;
            }else{
                return false;
            }
        }

        /**********
         **Setter**
         **********/

        public void setBefor(String playerName, String befor){
            config.set(playerName+".Perm", befor);
        }

        public void setPerms(String playerName, String perms){
            config.set(playerName+".type", perms);
        }

        public void setDisplay(String playerName, String display){
            config.set(playerName+".DisplayName", display);
        }

        public void setColorMsg(String playerName, String colormsg){
            config.set(playerName+".ColorMessage", colormsg);
        }

        public void setPrefix(String playerName, String display){
            config.set(playerName+".Prefix", display.substring(0, display.length()-3));
        }

        /**********
         **Getter**
         **********/

        public String getBefor(String playerName){
            return config.getString(playerName+".Perm");
        }

        public String getPerms(String playerName){
            return config.getString(playerName+".type");
        }

        public String getDisplay(String playerName){
            return config.getString(playerName+".DisplayName");
        }

        public String getColorMsg(String playerName){
            return config.getString(playerName+".ColorMessage");
        }

        public String getPrefix(String playerName){
            return config.getString(playerName+".Prefix");
        }

        /********
         **File**
         ********/

        public void save() {
            try {
                config.save(getPerm());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void update(){
            YamlConfiguration.loadConfiguration(getPerm());
        }

    }

}
