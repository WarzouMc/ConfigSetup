package fr.WarzouMc.MonaiServGroup.fileConfiguration.config;

import fr.WarzouMc.MonaiServGroup.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigSetup {

    /**************
     **BaseConfig**
     **************/

    public static class BaseConfig {

        private Main main;

        private FileConfiguration config;

        public BaseConfig(FileConfiguration config, Main main) {
            this.config = config;
            this.main = main;
        }

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

        public void createPlayerSection(String playerName){
            config.createSection("player." + playerName);
        }

        public void createPlayerKitSection(String playerName){
            config.createSection("kitstart." + playerName);
        }

        /***********
         **contains**
         ***********/

        public boolean containMoney(String playerName){
            if(config.contains("monai." + playerName)){
                return true;
            }else{
                return false;
            }
        }

        public boolean containPlayer(String playerName){
            if(config.contains("player." + playerName)){
                return true;
            }else{
                return false;
            }
        }

        public boolean containPlayerKit(String playerName){
            if(config.contains("kitstart." + playerName)){
                return true;
            }else{
                return false;
            }
        }

        public void setMoney(int i, String playerName){
            config.set("monai." + playerName, i);
            config.set("allmonai", getAllMoney() - getMoney(playerName) + i);
        }

        public void setPlayer(String s, String playerName){
            config.set("player." + playerName, s);
        }

        public void addMoney(int i, String playerName){
            config.set("monai." + playerName, getMoney(playerName) + i);
            config.set("allmonai", getAllMoney() + i);
        }

        public int getMoney(String playerName){
            return config.getInt("monai." + playerName);
        }

        public int getAllMoney(){
            return config.getInt("allmonai");
        }

        /***************
         **Player List**
         ***************/

        public ArrayList<String> getList(){
            return ( ArrayList<String> ) config.getStringList("allp");
        }

        public void addInList(String playerName){
            ArrayList<String> l = ( ArrayList<String> ) config.get("allp");
            l.add(playerName);
            config.set("allp", l);
        }

        public void removeInList(String playerName){
            ArrayList<String> l = ( ArrayList<String> ) config.getStringList("allp");
            l.remove(playerName);
            config.set("allp", l);
        }

        /********
         **File**
         ********/

        public void save(){main.saveConfig();}

    }

    /**************
     **ChatConfig**
     **************/

    public static class ChatConfig {

        private Main main;

        private FileConfiguration config;

        public ChatConfig(FileConfiguration chatConfig, Main main) {
            this.config = chatConfig;
            this.main = main;
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

        public void createSection(String playerName){
            config.createSection(playerName);
        }

        /**********
         **setter**
         **********/

        public void set(int i, String playerName){
            config.set(playerName + "", i);
        }

        /**********
         **getter**
         **********/

        public int get(String playerName){
            return config.getInt(playerName + "");
        }

        /********
         **File**
         ********/

        public void save() {
            try {
                config.save(main.getChat());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void update(){
            YamlConfiguration.loadConfiguration(main.getChat());
        }

    }

    /**************
     **PermConfig**
     **************/

    public static class PermConfig {

        private Main main;
        private FileConfiguration config;

        public PermConfig(File permConfig, Main main) {
            this.config = YamlConfiguration.loadConfiguration(permConfig);
            this.main = main;
        }

        /**********
         **Create**
         **********/

        public void created(){
            config.createSection("Groups");
            config.createSection("Groups.op");
            config.createSection("Groups.member");

            this.save();
            this.update();

            List op = new ArrayList();
            List op1 = new ArrayList();
            List player = new ArrayList();

            op.add("SkyExpender.op");
            op1.add("SkyExpender.op");
            player.add("SkyExpander.p");

            config.set("Groups.op.builder", op);
            config.set("Groups.op.dev", op1);
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

        public boolean containG(){
            return config.contains("Groups");
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
                config.save(main.getPerm());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void update(){
            this.config = YamlConfiguration.loadConfiguration(main.getPerm());
        }

    }

    /*************
     **ModConfig**
     *************/

    public static class ModConfig {

        private Main main;
        private FileConfiguration config;

        public ModConfig(FileConfiguration modConfig, Main main) {
            this.config = modConfig;
            this.main = main;
        }

        /**********
         **Create**
         **********/

        public void created(String playerName){
            config.createSection(playerName);
        }

        /***********
         **Contain**
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

        public void set(String playerName, String mod){
            config.set(playerName, mod);
        }

        /**********
         **Getter**
         **********/

        public String get(String playerName){
            return config.getString(playerName);
        }

        /********
         **File**
         ********/

        public void save() {
            try {
                config.save(main.getMod());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void update(){
            YamlConfiguration.loadConfiguration(main.getMod());
        }

    }

    /**************
     **ShopConfig**
     **************/

    public static class ShopConfig {

        private Main main;
        private FileConfiguration config;

        public ShopConfig(FileConfiguration shopConfig, Main main) {
            this.config = shopConfig;
            this.main = main;
        }

        public void deletSection(String path){
            config.set(path, null);
        }

        /**********
         **Getter**
         **********/

        public int getInt(String path){
            return config.getInt(path);
        }

        public String getString(String path){
            return config.getString(path);
        }

        public boolean contain(String path){
            if(config.contains(path)){
                return true;
            }else{
                return false;
            }
        }

        /**********
         **Setter**
         **********/

        public void setInt(String path, int i){
            config.set(path, i);
        }

        /********
         **File**
         ********/

        public void save() {
            try {
                config.save(main.getShop());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void update(){
            YamlConfiguration.loadConfiguration(main.getShop());
        }

    }

    public static class StringMessage{

        public String WareZone(int MsgType){
            List<String> type = new ArrayList<>();
            type.add("§1[§9WareZone§1] §2>> §1");
            type.add("§4[§cWareZone§4] §2>> §4");
            return type.get(MsgType);
        }

    }

}
