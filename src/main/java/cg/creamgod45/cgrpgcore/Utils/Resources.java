package cg.creamgod45.cgrpgcore.Utils;


import cg.creamgod45.cgrpgcore.CGRPGCore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Resources {
    private final List<Resource> resources = new ArrayList();

    public Resources(CGRPGCore plugin) {
        this.resources.add(new Resource(plugin, "classs.yml"));
        //this.resources.add(new Resource(plugin, "guilds.yml"));
    }

    public void load() {
        Iterator var1 = this.resources.iterator();

        while (var1.hasNext()) {
            Resource ability = (Resource) var1.next();
            ability.load();
        }

    }

    public void reload() {
        this.load();
    }

    public void save() {
        Iterator var1 = this.resources.iterator();

        while (var1.hasNext()) {
            Resource ability = (Resource) var1.next();
            ability.save();
        }

    }

    public List<Resource> getResource() {
        return this.resources;
    }
}
