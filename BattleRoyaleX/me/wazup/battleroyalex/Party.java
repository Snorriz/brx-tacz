package me.wazup.battleroyalex;

import java.util.ArrayList;
import me.wazup.battleroyalex.managers.Config;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.utils.ItemStackBuilder;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.XMaterial;
import me.wazup.battleroyalex.utils.Enums.PartyPrivacy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Party {
   public String leaderName;
   public PartyPrivacy privacy;
   int capacity;
   public ArrayList<String> players = new ArrayList();
   public ArrayList<String> invited = new ArrayList();
   public Inventory gui;
   public Inventory playersInventory;
   int id;
   int slot;

   public Party(Player var1, int var2) {
      this.capacity = var2;
      this.players.add(var1.getName());
      this.gui = Bukkit.createInventory(null, 9, (String)Customization.getInstance().inventories.get("Party-Settings"));
      Utils.cageInventory(this.gui, true);
      this.playersInventory = Bukkit.createInventory(
         null, Utils.getInventorySize(var2 + 1), (String)Customization.getInstance().inventories.get("Party-Players")
      );

      for (int var3 = var2; var3 < this.playersInventory.getSize(); var3++) {
         this.playersInventory.setItem(var3, (ItemStack)Customization.getInstance().items.get("Pane"));
      }

      this.playersInventory.setItem(this.playersInventory.getSize() - 1, (ItemStack)Customization.getInstance().items.get("Back"));
      this.setLeader(var1);
      this.setPrivacy(PartyPrivacy.INVITE);
      this.updatePlayers();
      this.gui
         .setItem(
            6,
            new ItemStackBuilder(Material.ENDER_CHEST)
               .setName(ChatColor.GREEN + "Invite")
               .addLore(new String[]{ChatColor.GRAY + "Click to invite players"})
               .build()
         );
      this.gui
         .setItem(
            8, new ItemStackBuilder(Material.TNT).setName(ChatColor.RED + "Leave").addLore(new String[]{ChatColor.GRAY + "Click to leave the party"}).build()
         );
   }

   public void setLeader(Player var1) {
      this.leaderName = var1.getName();
      this.gui
         .setItem(0, new ItemStackBuilder(Material.PAPER).setName(ChatColor.YELLOW + "Leader:").addLore(new String[]{ChatColor.GOLD + var1.getName()}).build());
   }

   public void setPrivacy(PartyPrivacy var1) {
      this.privacy = var1;
      this.gui
         .setItem(
            2, new ItemStackBuilder(XMaterial.ENDER_EYE.parseMaterial()).setName(ChatColor.AQUA + "Privacy:").addLore(new String[]{var1.toString()}).build()
         );
   }

   public void updatePlayers() {
      ItemStackBuilder var1 = new ItemStackBuilder(Material.CHEST)
         .setName(ChatColor.LIGHT_PURPLE + "Players:")
         .addLore(new String[]{"" + ChatColor.LIGHT_PURPLE + this.players.size() + ChatColor.YELLOW + "/" + ChatColor.LIGHT_PURPLE + this.capacity, " "});
      int var2 = 0;

      for (int var3 = 0; var3 < this.capacity; var3++) {
         this.playersInventory.setItem(var3, new ItemStack(Material.AIR));
      }

      for (Object var4 : this.players) {
         ChatColor var5 = var4.equals(this.leaderName) ? ChatColor.GOLD : ChatColor.GRAY;
         var1.addLore(new String[]{ChatColor.GRAY + "- " + var5 + var4});
         ItemStack var6 = Utils.getSkull((String)var4, var5 + var4);
         if (!var4.equals(this.leaderName)) {
            new ItemStackBuilder(var6)
               .addLore(new String[]{ChatColor.YELLOW + "Left click to promote to leader!", " ", ChatColor.YELLOW + "Right click to kick!"})
               .build();
         }

         this.playersInventory.setItem(var2, var6);
         var2++;
      }

      this.gui.setItem(4, var1.build());
   }

   public void updateItem() {
      BattleRoyaleX.getInstance()
         .partySelector
         .setItem(
            this.id,
            this.slot,
            new ItemStackBuilder(Material.BOOK)
               .setName(ChatColor.GOLD + this.leaderName + "'s party")
               .addLore(
                  new String[]{
                     ChatColor.AQUA + "Privacy: " + this.privacy,
                     " ",
                     ChatColor.YELLOW
                        + "Players: "
                        + ChatColor.LIGHT_PURPLE
                        + this.players.size()
                        + ChatColor.YELLOW
                        + "/"
                        + ChatColor.LIGHT_PURPLE
                        + this.capacity
                  }
               )
               .build()
         );
   }

   public void sendMessage(String var1) {
      for (Player var3 : Utils.getPlayers(this.players)) {
         var3.sendMessage(var1);
      }
   }

   public void leave(Player var1) {
      this.players.remove(var1.getName());
      ((PlayerData)BattleRoyaleX.getInstance().playerData.get(var1.getName())).party = null;
      var1.closeInventory();
      var1.sendMessage((String)Customization.getInstance().messages.get("Party-Player-Leave"));
      if (this.players.isEmpty()) {
         BattleRoyaleX.getInstance().parties.remove(this);
         BattleRoyaleX.getInstance().updatePartiesInventory();
      } else {
         this.sendMessage(((String)Customization.getInstance().messages.get("Party-Player-Leave-Alert")).replace("%player%", var1.getName()));
         if (var1.getName().equals(this.leaderName)) {
            Player var2 = Bukkit.getPlayer((String)this.players.get(0));
            this.setLeader(var2);
            this.sendMessage(((String)Customization.getInstance().messages.get("Party-New-Leader")).replace("%player%", var2.getName()));
         }

         this.updateItem();
         this.updatePlayers();
      }
   }

   public void join(Player var1) {
      if (this.players.size() >= this.capacity) {
         var1.sendMessage((String)Customization.getInstance().messages.get("Party-Full"));
      } else {
         if (!this.privacy.equals(PartyPrivacy.PUBLIC) && !this.invited.contains(var1.getName())) {
            var1.sendMessage((String)Customization.getInstance().messages.get("Party-Not-Invited"));
         } else {
            this.players.add(var1.getName());
            ((PlayerData)BattleRoyaleX.getInstance().playerData.get(var1.getName())).party = this;
            this.updatePlayers();
            this.updateItem();
            this.sendMessage(((String)Customization.getInstance().messages.get("Party-Join")).replace("%player%", var1.getName()));
            var1.closeInventory();
         }
      }
   }

   public void invite(final Player var1) {
      this.sendMessage(
         ((String)Customization.getInstance().messages.get("Party-Invite-Send")).replace("%player%", this.leaderName).replace("%target%", var1.getName())
      );
      this.invited.add(var1.getName());
      var1.sendMessage(
         ((String)Customization.getInstance().messages.get("Party-Invite-Receive"))
            .replace("%leader%", this.leaderName)
            .replace("%seconds%", String.valueOf(Config.getInstance().partyInvitationLength))
      );
      if (BattleRoyaleX.getInstance().clickableTextManager != null) {
         BattleRoyaleX.getInstance()
            .clickableTextManager
            .sendMessage(
               var1, (String)Customization.getInstance().messages.get("Party-Invite-Receive-Clickable-Message"), "battleroyale party join " + this.leaderName
            );
      }

      Bukkit.getScheduler().scheduleSyncDelayedTask(BattleRoyaleX.getInstance(), new Runnable() {
         public void run() {
            Party.this.invited.remove(var1.getName());
            if (!Party.this.players.contains(var1.getName())) {
               var1.sendMessage((String)Customization.getInstance().messages.get("Party-Invitation-Expire"));
            }
         }
      }, Config.getInstance().partyInvitationLength * 20);
   }

   public void kick(Player var1) {
      var1.sendMessage((String)Customization.getInstance().messages.get("Party-Player-Kick"));
      this.leave(var1);
      this.sendMessage(
         ((String)Customization.getInstance().messages.get("Party-Player-Kick-Alert")).replace("%leader%", this.leaderName).replace("%player%", var1.getName())
      );
   }
}
