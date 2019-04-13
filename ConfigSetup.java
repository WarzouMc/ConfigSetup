package fr.WarzouMc.MonaiServGroup.fileConfiguration.config;

import fr.WarzouMc.MonaiServGroup.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.List;

public class ConfigSetup {

    /**************
     **BaseConfig**
     **************/

    public static class BaseConfig {

        private Main main;

        /************
         **AllMoney**
         ************/

        public void addAllMoney(int i){
            main.getConfig().set("allmonai", main.getConfig().getInt("allmonai") + i);
        }

        /****************
         **Player Money**
         ****************/

        public void createMoneySection(String playerName){
            main.getConfig().createSection("monai." + playerName);
        }

        public void setMoney(int i, String playerName){
            main.getConfig().set("monai." + playerName, i);
        }

        public void addMoney(int i, String playerName){
            main.getConfig().set("monai." + playerName, main.getConfig().getInt("monai." + playerName) + i);
        }

        /***************
         **Player List**
         ***************/

        public List<?> getList(){
            return main.getConfig().getList("allp");
        }

        public void addInList(String playerName){
            List<String> l = ( List<String> ) getList();
            main.getConfig().set("allp", l.add(playerName));
        }

        public void removeInList(String playerName){
            List<String> l = ( List<String> ) getList();
            main.getConfig().set("allp", l.remove(playerName));
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

        /************
         **containe**
         ************/

        public boolean contain(String playerName){
            if(main.getChatConfig().contains(playerName)){
                return true;
            }else{
                return false;
            }
        }

        public void createSection(String playerName){
            main.getChatConfig().createSection(playerName);
        }

        /**********
         **setter**
         **********/

        public void set(int i, String playerName){
            main.getChatConfig().set(playerName, i);
        }

        /**********
         **getter**
         **********/

        public int get(String playerName){
            return main.getChatConfig().getInt(playerName);
        }

        /********
         **File**
         ********/

        public void save() {
            try {
                main.getChatConfig().save(main.getChat());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void update(){
            YamlConfiguration.loadConfiguration(main.getChat());
        }

    }

}
