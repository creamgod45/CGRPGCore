package cg.creamgod45.cgrpgcore.Commands;

import cg.creamgod45.cgrpgcore.Utils.ComponentMerge;
import org.bukkit.entity.Player;

public interface ICommandManual {

    default void PageControl(Player player, Integer page, Integer totalpage){
        ComponentMerge componentMerge = new ComponentMerge("").add("頁面: ");
        if (page != 0) {
            componentMerge.add("<click:run_command:/cgrpgcore help " + (page - 1) + "><hover:show_text:'<red>點我執行'><green><bold>[上一頁]</hover></click>");
        } else {
            componentMerge.add("<gray>上一頁");
        }
        componentMerge.add(" | ");
        for (int i = 0; i <= totalpage; i++) {
            if (i == page) {
                componentMerge.add(" <gray>[" + i + "]<reset> ");
            } else {
                componentMerge.add(" <click:run_command:/cgrpgcore help " + i + "><hover:show_text:'<red>點我前往'><green><bold>" + i + "</hover></click> ");
            }
        }
        componentMerge.add(" | ");
        if (!page.equals(totalpage)) {
            componentMerge.add("<click:run_command:/cgrpgcore help " + (page + 1) + "><hover:show_text:'<red>點我執行'><green><bold>[下一頁]</hover></click>");
        } else {
            componentMerge.add("<gray>下一頁");
        }
        componentMerge.output().print(player);
    }

    void Manual(Player player, int page);
}
