package fr.WarzouMc.MonaiServGroup.fileConfiguration.config;

import com.sun.xml.internal.fastinfoset.algorithm.HexadecimalEncodingAlgorithm;
import fr.WarzouMc.MonaiServGroup.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

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
            String s = "";
            if(contain(playerName)){
                s = config.getString(playerName+".type");
            }else{
                s = "nop";
            }
            return s;
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

    public static class LvlConfig {

        private Main main;
        private FileConfiguration config;

        public LvlConfig(FileConfiguration lvlConfig, Main main) {
            this.config = lvlConfig;
            this.main = main;
        }

        /**********
         **Create**
         **********/

        public void created(String playerName){
            config.createSection("Players." + playerName);
            config.createSection("Players." + playerName + ".Lvl");
            config.createSection("Players." + playerName + ".LvlGrade");
            config.createSection("Players." + playerName + ".Xp");
        }

        public void createList(){
            List<Integer> l = new ArrayList<>();
            if(!config.contains("LevelsXp")){
                config.createSection("LevelsXp");
                save();
                update();
            }
            for(int i = 0; i < 30; i++){
                for(int i1 = 1; i1 < 150; i1++){
                    l.add(getXpPerLevelPerGrade(i1, i));
                }
            }
            config.set("LevelsXp", l);
            save();
            update();
        }

        /***********
         **Contain**
         ***********/

        public boolean contain(String playerName){
            if(config.contains("Players." + playerName)){
                return true;
            }else{
                return false;
            }
        }

        /**********
         **Setter**
         **********/

        public void setLvl(String playerName, int level){
            config.set("Players." + playerName + ".Lvl", level);
        }

        public void setLvlGrade(String playerName, int grade){
            config.set("Players." + playerName + ".LvlGrade", grade);
            if(grade > getDiscoverGrade()){
                config.set("discover", grade);
                Bukkit.broadcastMessage(new StringMessage().Servers(0) + "§6Un nouveau prestige a été découvert par : " + Bukkit.getPlayer(playerName).getDisplayName() + ".\n§2Braveau à toi tu est maintenant : §r" + buildGradeFormInt(grade) + " §4!");
            }else{
                Bukkit.broadcastMessage(new StringMessage().Servers(0) + Bukkit.getPlayer(playerName).getDisplayName() + " §2est passé : " + buildGradeFormInt(grade));
            }

            Player player = Bukkit.getPlayer(playerName);

            player.closeInventory();
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 1, 1);
            player.sendTitle("§6§lTu est :", buildGradeFormInt(grade));

            new BaseConfig(main.getConfig(), main).setMoney(30, playerName);
            new BaseConfig(main.getConfig(), main).save();
        }

        public void resetXp(String playerName){
            config.set("Players." + playerName + ".Xp", getXpPerLevelPerGrade(getLvl(playerName), getLvlGradeInt(playerName)));
        }

        public void setXp(String playerName, int xp){
            config.set("Players." + playerName + ".Xp", xp);
        }

        public void addXpNeed(String playerName, int xp){
            config.set("Players." + playerName + ".Xp", getXpNeed(playerName) + xp);
        }

        public void rvmXpNeed(String playerName, int xp){
            config.set("Players." + playerName + ".Xp", getXpNeed(playerName) - xp);
        }

        public void passGrade(String playerName, int i){
            setLvlGrade(playerName, i);
            setLvl(playerName, 1);
            resetXp(playerName);
            save();
        }

        /**********
         **Getter**
         **********/

        public int getLvl(String playerName){
            return config.getInt("Players." + playerName + ".Lvl");
        }

        public String getLvlGrade(String playerName){
            return getSpecGrade(getLvlGradeInt(playerName)).replace("&", "§");
        }

        public int getXpNeed(String playerName){
            return config.getInt("Players." + playerName + ".Xp");
        }

        public int getXpPerLevelPerGrade(int lvl, int grade){
            float i;
            i = lvl * (5f + (((lvl + 1f) * ((grade + 1f) * ((grade + 1f) / 100f * 10f)) * 10f / 100f)));
            int r = Math.round(i);
            return r;
        }

        public int getTotalXp(String playerName){
            return 1;
        }

        public int getLvlGradeInt(String playerName){
            return config.getInt("Players." + playerName + ".LvlGrade");
        }

        public List<String> getGradeList(){
            return config.getStringList("LvlGrade");
        }

        public String getSpecGrade(int i){
            return getGradeList().get(i);
        }

        public List<String> getColorList(){
            return config.getStringList("Color");
        }

        public String getSpecColor(int i){
            return getColorList().get(i);
        }

        public int getTotalGameXp(){
            int total = 0;
            for(int i = 0; i < 30; i++){
                for(int i1 = 1; i1 < 150; i1++){
                    total = total + getXpPerLevelPerGrade(i1, i);
                }
            }
            return total + 5020;
        }

        public int getGradeXp(int i){
            int xp = 0;
            for (int j = 0; j < 150; j++) {
                xp += getXpPerLevelPerGrade(j, i);
            }
            return xp;
        }

        public int getPlayerXpInGrade(String playerName){
            int level = getLvl(playerName);
            int prestige = getLvlGradeInt(playerName);

            int xp = 0;

            for (int i = 1; i < level; i++) {
                xp += getXpPerLevelPerGrade(i, prestige);
            }

            xp += (getXpPerLevelPerGrade(level, prestige) - getXpNeed(playerName));

            return xp;
        }

        public int getTotalXpOfPlayer(String playerName){
            int total = 0;
            List<Integer> l = config.getIntegerList("LevelsXp");

            if(getLvl(playerName) < 150){
                for(int i = 0; i < getLvl(playerName) + (getLvlGradeInt(playerName) * 149); i++){
                    total = total + l.get(i);
                }
                total = total + ((getLvl(playerName) + (getLvlGradeInt(playerName) * 149) - 1) - getXpNeed(playerName));
            }else {
                if(getLvlGradeInt(playerName) < 29){
                    for(int i = 0; i < 1 + ((getLvlGradeInt(playerName) + 1) * 149); i++){
                        total = total + l.get(i);
                    }
                }else{
                    total = getTotalGameXp();
                }
            }

            return total;
        }

        public int getDiscoverGrade(){
            return config.getInt("discover");
        }

        public int[] getColors(String playerName){
            int level = (getLvl(playerName) - 1) / 10;
            int grade = getLvlGradeInt(playerName) * 10 / 20;
            return new int[] {level, grade};
        }

        public String[] getNotif(String playerName){
            int level = getColors(playerName)[0];
            int grade = getColors(playerName)[1];
            String levelColor = getSpecColor(level).replace("&", "§");
            String gradeColor = getSpecColor(grade).replace("&", "§");
            return new String[] {levelColor, gradeColor};
        }

        /*********
         **Build**
         *********/

        public void buildPassLevel(String playerName){
            String first = buildLevel(playerName);
            setLvl(playerName, getLvl(playerName) + 1);
            resetXp(playerName);
            save();
            update();
            String second = buildLevel(playerName);
            Bukkit.getPlayer(playerName).sendTitle(first + " §b-> " + second, "§b§oLevel up");
            Bukkit.getPlayer(playerName).sendMessage("§b§oLevel Up §r: " + first + "§b-> " + second);
        }

        public String buildLevel(String playerName){
            String lvlColor = getNotif(playerName)[0];
            String gradeColor = getNotif(playerName)[1];

            String level = gradeColor + "[§f" + lvlColor + getLvl(playerName) + gradeColor + "]§f ";

            if(getLvl(playerName) == 150){
                level = gradeColor + "[§f§k!§r§11§f5§40§f§k!§r" + gradeColor + "]§f ";
            }

            return level;
        }

        public String buildGradeFormInt(int i){
            List<String> l = config.getStringList("LvlGrade");
            String grade = l.get(i).replace("&", "§");
            String color = getSpecColor(i * 10 / 20).replace("&", "§");
            grade = color + "[§f" + grade + color + "]";
            return grade;
        }

        public String buildGrade(String playerName){
            String gradeColor = getNotif(playerName)[1];

            String grade = gradeColor + "[§f" + getLvlGrade(playerName) + gradeColor + "]§f ";
            return grade;
        }

        public String build(String playerName){
            String pr = buildGrade(playerName) + buildLevel(playerName);
            return pr;
        }

        /********
         **File**
         ********/

        public void save() {
            try {
                config.save(main.getLevels());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void update(){
            YamlConfiguration.loadConfiguration(main.getLevels());
        }

    }

    public static class StringMessage{

        public String WareZone(int MsgType){
            List<String> type = new ArrayList<>();
            type.add("§1[§9WarZone§1] §2>> §1");
            type.add("§4[§cWarZone§4] §2>> §4");
            type.add("§7[§fWarZone§7] §2>> §7");
            type.add("§6[§eWarZone§6] §2>> §6");
            type.add("§b[§3WarZone§b] §2>> §b");
            type.add("§2[§aWarZone§2] §2>> §2");
            return type.get(MsgType);
        }

        public String Servers(int MsgType){
            List<String> type = new ArrayList<>();
            type.add("§1[§9Servreur§1] §2>> §1");
            type.add("§4[§cServreur§4] §2>> §4");
            return type.get(MsgType);
        }

        public String Se(int MsgType){
            List<String> type = new ArrayList<>();
            type.add("§1[§9SE§1] §2>> §1");
            type.add("§4[§cSE§4] §2>> §4");
            return type.get(MsgType);
        }

        public String Protection(int MsgType){
            List<String> type = new ArrayList<>();
            type.add("§1[§9Protection§1] §2>> §1");
            type.add("§4[§cProtection§4] §2>> §4");
            return type.get(MsgType);
        }

        public String AntiLag(int MsgType){
            List<String> type = new ArrayList<>();
            type.add("§1[§9AntiLag§1] §2>> §1");
            type.add("§4[§cAntiLag§4] §2>> §4");
            return type.get(MsgType);
        }

        public String Xp(int MsgType){
            List<String> type = new ArrayList<>();
            type.add("§1[§9XP§1] §2>> §1");
            type.add("§4[§cXP§4] §2>> §4");
            return type.get(MsgType);
        }

        public String Money(int MsgType){
            List<String> type = new ArrayList<>();
            type.add("§1[§9Money§1] §2>> §1");
            type.add("§4[§cMoney§4] §2>> §4");
            return type.get(MsgType);
        }

    }

}
