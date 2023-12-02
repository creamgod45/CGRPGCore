package cg.creamgod45.cgrpgcore.Commands.CommandExecutor;

import cg.creamgod45.cgrpgcore.CGRPGCore;
import cg.creamgod45.cgrpgcore.Commands.ICommandManual;
import cg.creamgod45.cgrpgcore.Utils.ComponentMerge;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class cgrpgcoreCommandExecutor implements CommandExecutor , ICommandManual {
    int totalpage = 1;
    private CGRPGCore plugin;

    public cgrpgcoreCommandExecutor(CGRPGCore cgrpgCore) {
        plugin=cgrpgCore;
        new ComponentMerge(cgrpgCore.getConfigClass().prefix())
                .add("<color:#4287f5>指令註冊.... -> [cgrpgcore] => Executor")
                .output().print();
    }

    @Override
    public void PageControl(Player player, Integer page, Integer emptyValue) {
        ICommandManual.super.PageControl(player, page, totalpage);
    }

    @Override
    public void Manual(Player player, int page) {
        switch (page) {
            case 0 -> {
                new ComponentMerge("")
                        .add("<color:#f54296>=====================================================").newline()
                        .add("<yellow><bold>[CGRPGCore] <red>指令幫助 頁面: (" + page + "/"+totalpage+")").newline()
                        .add("<color:#8d42f5>指令簡稱 [cgrpg, cgrpgc]").newline()
                        .add("<color:#5a8af2>說明: 這是一個RPG系統").newline()
                        .add("<color:#43e8b9>參數說明:").newline()
                        .add("<color:#43e8b9> <> -> <gray>必填資料").newline()
                        .add("<color:#43e8b9> [] -> <gray>非必填資料").newline()
                        .add("<color:#6ad929>操作說明: 所有幫助頁面有提示字卡，均可以點擊文字快速輸入，或是執行指令。").newline()
                        .newline()
                        .newline()
                        .newline()
                        .newline()
                        .newline()
                        .newline()
                        .newline()
                        .newline()
                        .newline()
                        .output().print(player);
                PageControl(player, 0, 0);
            }
            case 1 -> {
                new ComponentMerge("")
                        .add("<color:#f54296>=====================================================").newline()
                        .add("<yellow><bold>[CGRPGCore] <red>指令幫助 頁面: (" + page + "/"+totalpage+")").newline()
                        .add("<color:#8d42f5>指令簡稱 [cgrpg, cgrpgc]").newline()
                        .add("<color:#5a8af2>說明: 這是一個RPG系統").newline()
                        .add("<color:#43e8b9>參數說明:").newline()
                        .add("<color:#43e8b9> <> -> <gray>必填資料").newline()
                        .add("<color:#43e8b9> [] -> <gray>非必填資料").newline()
                        .add("<color:#6ad929>操作說明: 所有幫助頁面有提示字卡，均可以點擊文字快速輸入，或是執行指令。").newline()
                        .add("<click:SUGGEST_COMMAND:/cgrpg help ><hover:show_text:'<yellow>點我輸入'><green>/cgrpg help <gray>[page]").add(" <light_purple>查看指定的幫助頁面 ").newline()
                        //.add("<click:run_command:/cgrpg main><hover:show_text:'<red>點我執行'><green>/cgrpg main").add(" <light_purple>開啟首頁介面 ").newline()
                        //.add("<click:SUGGEST_COMMAND:/cgrpg console ><hover:show_text:'<yellow>點我輸入'><green>/cgrpg console <Messages>").add(" <light_purple>顯示在後台訊息 ").newline()
                        .newline()
                        .newline()
                        .newline()
                        .newline()
                        .newline()
                        .newline()
                        .newline()
                        .newline()
                        .output().print(player);
                PageControl(player, 1, 0);
            }

        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(commandSender instanceof Player player){
            if(args.length>0) {
                switch (args[0]) {
                    case "help" -> {
                        if (args.length >= 2) {
                            int page = Integer.parseInt(args[1]);
                            Manual(player, page);
                        }else{
                            Manual(player, 0);
                        }
                    }
                }
            }
        }else{

        }
        return false;
    }
}
