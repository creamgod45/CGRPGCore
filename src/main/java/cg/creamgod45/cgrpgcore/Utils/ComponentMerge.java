package cg.creamgod45.cgrpgcore.Utils;

import cg.creamgod45.cgrpgcore.CGRPGCore;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

import static cg.creamgod45.cgrpgcore.Utils.Utils.ComponentParser;


public class ComponentMerge {
    private Component component;

    public ComponentMerge(Component c) {
        this.component = c;
    }

    public ComponentMerge(String s) {
        this.component = ComponentParser(s);
    }

    public Component build() {
        return component;
    }

    public ComponentMerge add(Component c) {
        this.component = this.component.append(c);
        return this;
    }

    public ComponentMerge add(String s) {
        add(ComponentParser(s));
        return this;
    }

    public ComponentMerge add(List<String> list){
        for (String s : list) {
            add(s);
        }
        return this;
    }

    public ComponentMerge add(Component[] clist){
        for (Component component1 : clist) {
            add(component1);
        }
        return this;
    }

    public ComponentMerge newline(){
        this.component = this.component.append(ComponentParser("<newline>"));
        return this;
    }

    public Output output (){
        return new Output(this.component);
    }

    public static class Output{
        private Component component;
        public Output(Component c){
            this.component=c;
        }

        public void print(){
            CGRPGCore.adventure.console().sendMessage(component);
        }
        public void print(Player player) {
            CGRPGCore.adventure.player(player).sendMessage(component);
        }

        public void print(UUID uuid) {
            CGRPGCore.adventure.player(uuid).sendMessage(component);
        }

        public void print(World world) {
            CGRPGCore.adventure.world(Key.key(world.getKey().toString())).sendMessage(component);
        }

        public void print(boolean players) {
            CGRPGCore.adventure.players().sendMessage(component);
        }
    }
}
