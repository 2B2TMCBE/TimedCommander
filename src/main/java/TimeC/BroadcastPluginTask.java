package TimeC;

import cn.nukkit.scheduler.PluginTask;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.server.ServerCommandEvent;
import TimeC.Main;

public class BroadcastPluginTask extends PluginTask<Main> {
    
    Main plugin;

    public BroadcastPluginTask(Main owner) {
        super(owner);
        this.plugin = plugin;
    }

    @Override
    public void onRun(int currentTick) {
        this.getOwner().getServer().dispatchCommand(null, Main.cmdValue);
    }
}