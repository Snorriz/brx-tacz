package me.wazup.battleroyalex.listeners;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.PlayerData;
import me.wazup.battleroyalex.Arena.PAData;
import me.wazup.battleroyalex.managers.AchievementsManager;
import me.wazup.battleroyalex.managers.Config;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.managers.RanksManager;
import me.wazup.battleroyalex.managers.RecipeManager;
import me.wazup.battleroyalex.managers.SoundsManager;
import me.wazup.battleroyalex.managers.TitleManager;
import me.wazup.battleroyalex.managers.RecipeManager.RecipeData;
import me.wazup.battleroyalex.shop.MysteryBox;
import me.wazup.battleroyalex.utils.ChestType;
import me.wazup.battleroyalex.utils.ItemStackBuilder;
import me.wazup.battleroyalex.utils.RealisticItems;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.XMaterial;
import me.wazup.battleroyalex.utils.Enums.AchievementType;
import me.wazup.battleroyalex.utils.Enums.ArenaMode;
import me.wazup.battleroyalex.utils.Enums.ArenaState;
import me.wazup.battleroyalex.utils.Enums.SPECIAL_CHARACTER;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.golde.bukkit.corpsereborn.CorpseAPI.CorpseAPI;
import org.spigotmc.event.entity.EntityDismountEvent;

public class PlayerListener implements Listener {
   @EventHandler
   public void onPlayerPreLogin(PlayerLoginEvent var1) {
      if (BattleRoyaleX.getInstance().isOneGamePerServer()) {
         if (!((Arena)BattleRoyaleX.getInstance().arenas.values().toArray()[0]).state.AVAILABLE()) {
            var1.disallow(Result.KICK_OTHER, (String)Customization.getInstance().messages.get("Arena-Not-Available"));
         }
      }
   }

   @EventHandler
   public void onPlayerJoin(PlayerJoinEvent var1) {
      Player var2 = var1.getPlayer();
      BattleRoyaleX.getInstance().playerData.put(var2.getName(), new PlayerData(var2));
      if (Config.getInstance().bungee_mode_enabled) {
         if (BattleRoyaleX.getInstance().lobbyLocation != null) {
            BattleRoyaleX.getInstance().join(var2);
         } else {
            var2.sendMessage((String)Customization.getInstance().messages.get("Lobby-Not-Set"));
         }
      }

      if (var2.hasPermission("battleroyalex.admin")) {
         if (BattleRoyaleX.getInstance().availableUpdate) {
            var2.sendMessage(
               Customization.getInstance().prefix
                  + "Found a new available version! "
                  + ChatColor.LIGHT_PURPLE
                  + "download at "
                  + BattleRoyaleX.getInstance().downloadURL
            );
         } else {
            BattleRoyaleX.getInstance().checkUpdates(var2);
         }
      }
   }

   @EventHandler
   public void onPlayerQuit(PlayerQuitEvent var1) {
      Player var2 = var1.getPlayer();
      PlayerData var3 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName());
      if (var3.party != null) {
         var3.party.leave(var2);
      }

      if (BattleRoyaleX.getInstance().players.contains(var2.getName())) {
         if (var3.arena != null
            && !var3.arena.state.AVAILABLE()
            && !var3.arena.spectators.contains(var2.getName())
            && !var3.lastHit.isEmpty()
            && (System.currentTimeMillis() - var3.lastHitTime) / 1000L <= 15L) {
            Player var4 = Bukkit.getPlayer(var3.lastHit);
            if (var4 != null && !var4.getName().equals(var2.getName()) && var3.arena.players.containsKey(var4.getName())) {
               var3.deaths++;
               this.awardKiller(var4, var2, var3);
            }
         }

         BattleRoyaleX.getInstance().leave(var2, true);
      }

      var3.saveAsync(var2);
      BattleRoyaleX.getInstance().playerData.remove(var2.getName());
   }

   @EventHandler
   public void onPlayerInteract(PlayerInteractEvent var1) {
      final Player var2 = var1.getPlayer();
      if (var1.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
         Block var3 = var1.getClickedBlock();
         if (var3.getState() instanceof Sign) {
            Sign var4 = (Sign)var3.getState();
            if (var4.getLine(0).equals(Customization.getInstance().signs_title)) {
               var1.setCancelled(true);
               if (var2.getItemInHand().getType() != Material.AIR) {
                  var2.sendMessage((String)Customization.getInstance().messages.get("Must-Have-Empty-Hand"));
                  return;
               }

               if (((PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName())).hasCooldown(var2, "SIGN_INTERACT", 3)) {
                  return;
               }

               if (var4.getLine(1).equals(Customization.getInstance().signs_join)) {
                  String var16 = ChatColor.stripColor(var4.getLine(2)).toLowerCase();
                  if (BattleRoyaleX.getInstance().arenas.containsKey(var16)) {
                     if (!BattleRoyaleX.getInstance().players.contains(var2.getName())) {
                        BattleRoyaleX.getInstance().join(var2);
                     }

                     ((Arena)BattleRoyaleX.getInstance().arenas.get(var16)).join(var2);
                  } else {
                     if (BattleRoyaleX.getInstance().players.contains(var2.getName())) {
                        var2.sendMessage((String)Customization.getInstance().messages.get("Already-In-Game"));
                        return;
                     }

                     BattleRoyaleX.getInstance().join(var2);
                  }
               } else if (var4.getLine(1).equals(Customization.getInstance().signs_leave)) {
                  if (!BattleRoyaleX.getInstance().players.contains(var2.getName())) {
                     var2.sendMessage((String)Customization.getInstance().messages.get("Not-In-Game"));
                     return;
                  }

                  BattleRoyaleX.getInstance().leave(var2, false);
               } else if (var4.getLine(1).equals(Customization.getInstance().signs_autojoin)) {
                  if (!BattleRoyaleX.getInstance().players.contains(var2.getName())) {
                     BattleRoyaleX.getInstance().join(var2);
                  }

                  BattleRoyaleX.getInstance().autojoin(var2, var4.getLine(2));
               }

               return;
            }
         }
      }

      if (BattleRoyaleX.getInstance().players.contains(var2.getName())) {
         final PlayerData var11 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName());
         final Arena var12 = var11.arena;
         if (BattleRoyaleX.getInstance().protectedPlayers.contains(var2.getName())) {
            var1.setCancelled(true);
            if (var2.getGameMode().equals(GameMode.SPECTATOR) && var12 != null) {
               var2.openInventory(BattleRoyaleX.getInstance().spectatorMenu);
               return;
            }

            if (var1.getAction().equals(Action.RIGHT_CLICK_AIR) || var1.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
               if (var1.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                  Block var15 = var1.getClickedBlock();
                  if (var15.getType().equals(MysteryBox.getInstance().type)) {
                     if (var12 != null && !var12.state.AVAILABLE()) {
                        return;
                     }

                     if (!MysteryBox.getInstance().ready) {
                        var2.sendMessage((String)Customization.getInstance().messages.get("Invalid-Mystery-Box"));
                        return;
                     }

                     var2.openInventory(MysteryBox.getInstance().inventory);
                     return;
                  }
               }

               if (Utils.compareItem(var2.getItemInHand(), (ItemStack)Customization.getInstance().items.get("Quit"))) {
                  var2.openInventory(BattleRoyaleX.getInstance().quitInventory);
                  return;
               }

               if (Utils.compareItem(var2.getItemInHand(), (ItemStack)Customization.getInstance().items.get("Profile"))) {
                  var2.openInventory(BattleRoyaleX.getInstance().profileInventory);
                  return;
               }

               if (Utils.compareItem(var2.getItemInHand(), (ItemStack)Customization.getInstance().items.get("Shop"))) {
                  BattleRoyaleX.getInstance().kitsShop.open(var2);
                  return;
               }

               if (Utils.compareItem(var2.getItemInHand(), (ItemStack)Customization.getInstance().items.get("Arena-Selector"))) {
                  BattleRoyaleX.getInstance().arenaSelector.open(var2);
                  return;
               }

               if (var12 != null) {
                  if (var11.arena.state.AVAILABLE()) {
                     if (Utils.compareItem(var2.getItemInHand(), (ItemStack)Customization.getInstance().items.get("Vote"))) {
                        ((PAData)var11.arena.players.get(var2.getName())).votingCategory = -1;
                        var2.openInventory(BattleRoyaleX.getInstance().votingInventory);
                        return;
                     }

                     if (Utils.compareItem(var2.getItemInHand(), (ItemStack)Customization.getInstance().items.get("Team-Selector"))) {
                        var12.teamSelector.open(var2);
                        return;
                     }

                     return;
                  }

                  if (var12.spectators.contains(var2.getName())
                     && Utils.compareItem(var2.getItemInHand(), (ItemStack)Customization.getInstance().items.get("Teleporter"))) {
                     if (var11.hasCooldown(var2, "TELEPORT_GUI", 3)) {
                        return;
                     }

                     var2.openInventory(var11.arena.getPlayersInventory((String)Customization.getInstance().inventories.get("Teleporter")));
                     return;
                  }

                  return;
               }

               if (BattleRoyaleX.getInstance().lobbyPlayers.contains(var2.getName())) {
                  if (Utils.compareItem(var2.getItemInHand(), (ItemStack)Customization.getInstance().items.get("Party"))) {
                     if (var11.party == null) {
                        var2.openInventory(BattleRoyaleX.getInstance().partyMenu);
                     } else {
                        var2.openInventory(var11.party.gui);
                     }

                     return;
                  }

                  if (Utils.compareItem(var2.getItemInHand(), (ItemStack)Customization.getInstance().items.get("Recipes"))) {
                     RecipeManager.recipesInventory.open(var2);
                     return;
                  }

                  return;
               }
            }

            return;
         }

         if (!var12.players.containsKey(var2.getName())) {
            return;
         }

         if (((PAData)var12.players.get(var2.getName())).downed_chicken != null) {
            var1.setCancelled(true);
            return;
         }

         if (var2.getItemInHand().getType().equals(Material.COMPASS)) {
            if (var11.hasCooldown(var2, "COMPASS_TRACK", Config.getInstance().trackingCompassCooldown)) {
               return;
            }

            int var14 = Integer.MAX_VALUE;
            Player var20 = null;

            for (Player var27 : var12.getEnemies(var2)) {
               if (var2.getWorld().getName().equals(var27.getWorld().getName())) {
                  double var30 = var27.getLocation().distance(var2.getLocation());
                  if (var30 < var14) {
                     var14 = (int)var30;
                     var20 = var27;
                  }
               }
            }

            if (var20 != null) {
               new ItemStackBuilder(var2.getItemInHand())
                  .setName(
                     ChatColor.BOLD
                        + "Tracking: "
                        + ChatColor.RED
                        + var20.getName()
                        + ChatColor.WHITE
                        + ChatColor.BOLD
                        + " - DISTANCE: "
                        + ChatColor.RED
                        + var14
                        + ".0"
                  )
                  .build();
               var2.setCompassTarget(var20.getLocation());
               var2.sendMessage(((String)Customization.getInstance().messages.get("Tracking-Player")).replace("%target%", var20.getName()));
            } else {
               new ItemStackBuilder(var2.getItemInHand()).setName(ChatColor.RED + "No nearby players!").build();
            }

            return;
         }

         if (var2.getItemInHand().getType().equals(Material.GOLD_INGOT)) {
            ItemStack var5 = var2.getItemInHand();
            if (BattleRoyaleX.getInstance().claimCash(var2, (PAData)var12.players.get(var2.getName()), var5)) {
               var2.getInventory().removeItem(new ItemStack[]{var5});
               return;
            }
         }

         if (var1.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Block var13 = var1.getClickedBlock();
            if (var13.getType().equals(Material.CHEST)) {
               if (!var12.modifiedBlocks.containsKey(var13.getLocation())) {
                  if (Config.getInstance().realisticChests) {
                     var1.setCancelled(true);
                  }

                  if (!var12.openedChests.contains(var13.getLocation())) {
                     var12.openedChests.add(var13.getLocation());
                     Utils.fillChest(var13, (ChestType)BattleRoyaleX.getInstance().chests.get("Normal"), var2.getLocation());
                  }

                  return;
               }

               if (var12.deathChests.containsKey(var13.getLocation())) {
                  var1.setCancelled(true);
                  var2.openInventory((Inventory)var12.deathChests.get(var13.getLocation()));
               } else if (Config.getInstance().realisticChests && var12.openedChests.contains(var13.getLocation())) {
                  var1.setCancelled(true);
               } else if (var12.extractionChests.contains(var13.getLocation())) {
                  var1.setCancelled(true);
                  final PAData var19 = (PAData)var11.arena.players.get(var2.getName());
                  if (var19.cash == 0) {
                     return;
                  }

                  if (var19.channelingTask == null) {
                     var19.lastChannelingTime = System.currentTimeMillis();
                     var19.channelingTask = (new BukkitRunnable() {
                           int progress = 0;
                           int needed = 20;

                           public void run() {
                              long var1x = System.currentTimeMillis() - var19.lastChannelingTime;
                              if (var1x > 500L) {
                                 this.cancel();
                                 var19.channelingTask = null;
                              } else {
                                 this.progress++;
                                 if (this.progress == this.needed) {
                                    this.cancel();
                                    var19.channelingTask = null;
                                    String var3 = ChatColor.GREEN + "Complete!";
                                    if (TitleManager.getInstance().isActionBarEnabled()) {
                                       TitleManager.getInstance().sendForcedActionBar(var2, var3, var11);
                                    }

                                    var2.getWorld().playSound(var2.getLocation(), SoundsManager.ENDERDRAGON_GROWL, 1.0F, 1.0F);
                                    var19.team.cash = var19.team.cash + var19.cash;
                                    var19.cash = 0;
                                    var19.updateDataString();
                                    var3 = ((String)Customization.getInstance().messages.get("Cash-Extraction"))
                                       .replace("%cash%", String.valueOf(var19.team.cash));

                                    for (Player var5 : Utils.getPlayers(var19.team.players)) {
                                       var5.sendMessage(var3);
                                    }
                                 } else {
                                    String var7 = ChatColor.GRAY + "Extracting: " + Utils.getProgressBar(this.progress, this.needed);
                                    if (TitleManager.getInstance().isActionBarEnabled()) {
                                       TitleManager.getInstance().sendForcedActionBar(var2, var7, var11);
                                    } else {
                                       var7 = Customization.getInstance().prefix + var7;
                                       var2.sendMessage(var7);
                                    }

                                    var2.getWorld().playSound(var2.getLocation(), SoundsManager.CLICK, 1.0F, 1.0F);
                                 }
                              }
                           }
                        })
                        .runTaskTimer(BattleRoyaleX.getInstance(), 5L, 15L);
                  } else {
                     var19.lastChannelingTime = System.currentTimeMillis();
                  }
               }

               return;
            }

            if (var13.getType().equals(BattleRoyaleX.getInstance().shoppingStationMaterial)) {
               var1.setCancelled(true);
               var2.openInventory(BattleRoyaleX.getInstance().shoppingStation);
               var2.playSound(var2.getLocation(), SoundsManager.LAVA_POP, 1.0F, 1.0F);
               return;
            }

            if (var2.getItemInHand().getType().equals(Material.ENDER_CHEST)) {
               var1.setCancelled(true);
               final int var18 = Config.getInstance().carepackageHeight;
               boolean var22 = false;
               final Block var26 = var1.getClickedBlock().getRelative(var1.getBlockFace());

               for (int var28 = 1; var28 < var18; var28++) {
                  if (!var26.getWorld().getBlockAt(var26.getLocation().add(0.0, var28, 0.0)).getType().equals(Material.AIR)) {
                     var22 = true;
                     break;
                  }
               }

               if (!var22 && !var1.getClickedBlock().getRelative(BlockFace.DOWN).getType().name().contains("STEP")) {
                  ItemStack var29 = var2.getItemInHand();
                  if (var29.getAmount() > 1) {
                     var29.setAmount(var29.getAmount() - 1);
                  } else {
                     var2.getInventory().removeItem(new ItemStack[]{var29});
                  }

                  final World var10 = var2.getWorld();
                  (new BukkitRunnable() {
                     int Seconds = Config.getInstance().carepackageTime;

                     public void run() {
                        if (var12.state.equals(ArenaState.INGAME)) {
                           var2.playSound(var2.getLocation(), SoundsManager.CLICK, 1.0F, 1.0F);

                           for (byte var1 = 1; var1 < var18; var1 += 2) {
                              var10.playEffect(new Location(var10, var26.getX() + 0.5, var26.getY() + var1, var26.getZ() + 0.5), Effect.SMOKE, 35);
                           }

                           this.Seconds--;
                           if (this.Seconds == 0) {
                              FallingBlock var2x = var10.spawnFallingBlock(var26.getLocation().add(0.5, var18, 0.5), Material.SAND, (byte)0);
                              var2x.setDropItem(false);
                              var2x.setMetadata("BRX_PACKAGE", new FixedMetadataValue(BattleRoyaleX.getInstance(), var12.name));
                              this.cancel();
                           }
                        } else {
                           this.cancel();
                        }
                     }
                  }).runTaskTimer(BattleRoyaleX.getInstance(), 0L, 20L);
                  return;
               }

               var2.sendMessage((String)Customization.getInstance().messages.get("Carepackage-Denied"));
               return;
            }

            if (var2.getItemInHand().getType().name().contains("_EGG")) {
               short var17 = var2.getItemInHand().getDurability();
               EntityType var21 = EntityType.fromId(var17);
               if (var21 == null) {
                  var21 = EntityType.fromName(var2.getItemInHand().getType().name().replace("_SPAWN_EGG", ""));
               }

               if (var21 == null && var2.getItemInHand().getItemMeta().getDisplayName() != null) {
                  String var24 = var2.getItemInHand().getItemMeta().getDisplayName();
                  var21 = EntityType.fromName(ChatColor.stripColor(var24));
               }

               if (var21 != null) {
                  final LivingEntity var25 = (LivingEntity)var2.getWorld()
                     .spawnEntity(var13.getRelative(var1.getBlockFace()).getLocation().add(0.5, 0.0, 0.5), var21);
                  var25.setCustomName(ChatColor.AQUA + var2.getName() + "'s " + var25.getType().getName());
                  var25.setCustomNameVisible(true);
                  var25.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 3));
                  if (var25 instanceof Ageable) {
                     ((Ageable)var25).setAdult();
                  }

                  var25.setMetadata("BRX_MOB", new FixedMetadataValue(BattleRoyaleX.getInstance(), null));
                  if (var25 instanceof Creature) {
                     final String var9 = var2.getName();
                     (new BukkitRunnable() {
                           public void run() {
                              if (!var25.isDead() && var25.isValid()) {
                                 PlayerData var1;
                                 if (BattleRoyaleX.getInstance().players.contains(var9)
                                    && (var1 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var9)).arena != null
                                    && !var1.arena.state.AVAILABLE()
                                    && !var1.arena.spectators.contains(var9)) {
                                    if (Config.getInstance().teleportOwnedMobs
                                       && var25.getWorld().getName().equals(var2.getWorld().getName())
                                       && var2.getLocation().distanceSquared(var25.getLocation()) > 400.0) {
                                       var25.teleport(var2.getLocation());
                                    }

                                    List<Player> var2x = var1.arena.getEnemies(var2);
                                    if (!var2x.isEmpty()) {
                                       int var3 = Integer.MAX_VALUE;
                                       Player var4 = null;

                                       for (Player var6 : var2x) {
                                          if (var25.getWorld().getName().equals(var6.getWorld().getName())) {
                                             double var7 = var6.getLocation().distanceSquared(var25.getLocation());
                                             if (var7 < var3) {
                                                var3 = (int)var7;
                                                var4 = var6;
                                             }
                                          }
                                       }

                                       if (var4 != null) {
                                          ((Creature)var25).setTarget(var4);
                                       }
                                    }
                                 } else {
                                    var25.remove();
                                    this.cancel();
                                 }
                              } else {
                                 this.cancel();
                              }
                           }
                        })
                        .runTaskTimer(BattleRoyaleX.getInstance(), 0L, 40L);
                  }
               } else {
                  var2.sendMessage((String)Customization.getInstance().messages.get("Invalid-Entity"));
               }

               if (var2.getItemInHand().getAmount() > 1) {
                  var2.getItemInHand().setAmount(var2.getItemInHand().getAmount() - 1);
               } else {
                  var2.setItemInHand(new ItemStack(Material.AIR));
               }

               var1.setCancelled(true);
               return;
            }

            for (Entity var7 : var1.getPlayer().getWorld().getNearbyEntities(var1.getClickedBlock().getLocation(), 0.4, 0.4, 0.4)) {
               if (var7.getType().equals(EntityType.ARMOR_STAND)) {
                  ArmorStand var8 = (ArmorStand)var7;
                  if (var8.getItemInHand() != null && !var8.getItemInHand().getType().equals(Material.AIR)) {
                     this.realisticPickup(var2, var12, var8.getItemInHand());
                     var8.setItemInHand(null);
                  }

                  var8.remove();
                  var1.setCancelled(true);
                  break;
               }
            }
         }
      }
   }

   @EventHandler
   public void onPlayerDeath(PlayerDeathEvent var1) {
      final Player var2 = var1.getEntity();
      if (BattleRoyaleX.getInstance().players.contains(var2.getName())) {
         PlayerData var3 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName());
         Arena var4 = var3.arena;
         if (var4 != null && !var4.state.AVAILABLE() && !BattleRoyaleX.getInstance().protectedPlayers.contains(var2.getName())) {
            var1.setDeathMessage(null);
            (new BukkitRunnable() {
               public void run() {
                  var2.spigot().respawn();
               }
            }).runTaskLater(BattleRoyaleX.getInstance(), 1L);
            PAData var5 = (PAData)var4.players.get(var2.getName());
            if (!Config.getInstance().downingSystemEnabled) {
               this.spawnDownChicken(var2, var5);
               var5.downedItems = var2.getInventory().getContents();
               var5.downedArmor = var2.getInventory().getArmorContents();
               var1.getDrops().clear();
            }

            if (var5.downed_chicken == null) {
               var2.sendMessage((String)Customization.getInstance().messages.get("Bleeding-Faster-Tip"));
               var5.downedItems = var2.getInventory().getContents();
               var5.downedArmor = var2.getInventory().getArmorContents();
               var1.getDrops().clear();
               return;
            }

            ItemStack var6 = null;
            if (var5.cash > 0) {
               int var7 = var4.mode.equals(ArenaMode.Plunder) ? var5.cash / 2 : var5.cash;
               var6 = Utils.getItemStack("BattleRoyaleX:Cash:" + var7, false, false);
               var5.cash -= var7;
               var5.updateDataString();
            }

            if (BattleRoyaleX.getInstance().CorpseRebornEnabled) {
               if (!var4.mode.equals(ArenaMode.Normal)) {
                  ItemStack[] var14 = new ItemStack[36];
                  if (var6 != null) {
                     var14[0] = var6;
                  }

                  CorpseAPI.spawnCorpse(var2, var2.getLocation(), var14);
               } else {
                  if (var6 != null) {
                     for (int var13 = 0; var13 < var5.downedItems.length; var13++) {
                        if (var5.downedItems[var13] == null) {
                           var5.downedItems[var13] = var6;
                           break;
                        }
                     }
                  }

                  CorpseAPI.spawnCorpse(
                     var2, var2.getLocation(), var5.downedItems, var5.downedArmor[3], var5.downedArmor[2], var5.downedArmor[1], var5.downedArmor[0]
                  );
                  var5.downedItems = null;
                  var5.downedArmor = null;
               }
            } else {
               ArrayList var12 = new ArrayList();
               if (var6 != null) {
                  var12.add(var6);
               }

               if (var4.mode.equals(ArenaMode.Normal)) {
                  for (ItemStack var11 : var5.downedArmor) {
                     if (var11 != null) {
                        var12.add(var11);
                     }
                  }

                  for (ItemStack var20 : var5.downedItems) {
                     if (var20 != null) {
                        var12.add(var20);
                     }
                  }

                  var5.downedItems = null;
                  var5.downedArmor = null;
               }

               this.spawnDeathChest(var4, var2, var12);
            }

            var3.deaths++;
            RanksManager.getInstance().addExp(var2, Config.getInstance().expPerDeath);
            var2.getWorld().strikeLightningEffect(var2.getLocation());
            Player var15 = var2.getKiller();
            if (var15 == null && !var3.lastHit.isEmpty() && (System.currentTimeMillis() - var3.lastHitTime) / 1000L <= 15L) {
               var15 = Bukkit.getPlayer(var3.lastHit);
            }

            if (var15 != null && !var15.getName().equals(var2.getName()) && var3.arena.players.containsKey(var15.getName())) {
               this.awardKiller(var15, var2, var3);
            }

            String var17 = this.getDeathMessage(var2, var15);
            var4.kill(var2, var5, var17);
         } else {
            var1.getDrops().clear();
         }
      }
   }

   private void spawnDownChicken(Player var1, PAData var2) {
      LivingEntity var3 = (LivingEntity)var1.getWorld().spawnEntity(var1.getLocation().getBlock().getLocation().add(0.0, -0.5, 0.0), EntityType.CHICKEN);
      var3.setMetadata("BRX_DOWNED", new FixedMetadataValue(BattleRoyaleX.getInstance(), null));
      var3.setPassenger(var1);
      var3.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 99999));

      try {
         Utils.removeAI(var3);
      } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var5) {
         var5.printStackTrace();
      }

      var2.downed_chicken = var3;
   }

   @EventHandler
   public void onPlayerRespawnEvent(PlayerRespawnEvent var1) {
      final Player var2 = var1.getPlayer();
      if (BattleRoyaleX.getInstance().players.contains(var2.getName())) {
         if (BattleRoyaleX.getInstance().lobbyPlayers.contains(var2.getName())) {
            var1.setRespawnLocation(BattleRoyaleX.getInstance().lobbyLocation);
            return;
         }

         final PlayerData var3 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName());
         final Arena var4 = var3.arena;
         if (var4 != null && !var4.state.AVAILABLE()) {
            if (var4.players.containsKey(var2.getName())) {
               final PAData var5 = (PAData)var4.players.get(var2.getName());
               if (var5.downed_chicken == null) {
                  var1.setRespawnLocation(var2.getLocation());
                  this.spawnDownChicken(var2, var5);
                  (new BukkitRunnable() {
                     public void run() {
                        if (var4.state.equals(ArenaState.INGAME) && var4.players.containsKey(var2.getName()) && var5.downed_chicken != null) {
                           if (var5.downed_chicken.getPassenger() == null) {
                              var5.downed_chicken.setPassenger(var2);
                           }

                           var2.damage(Config.getInstance().bleedingDamage);
                           var2.setNoDamageTicks(0);
                           TitleManager.getInstance().sendForcedActionBar(var2, ChatColor.RED + "Bleeding...!", var3);
                        } else {
                           this.cancel();
                        }
                     }
                  }).runTaskTimer(BattleRoyaleX.getInstance(), 20L, 20L);
                  (new BukkitRunnable() {
                     public void run() {
                        if (var4.players.containsKey(var2.getName())) {
                           var2.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999, 0));
                           var2.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 0));
                        }
                     }
                  }).runTaskLater(BattleRoyaleX.getInstance(), 1L);
                  return;
               }

               if (var4.mode.equals(ArenaMode.Plunder)) {
                  var1.setRespawnLocation(var4.getRandomHighLocation());
                  var5.downed_chicken.remove();
                  var5.downed_chicken = null;
                  var2.getInventory().setContents(var5.downedItems);
                  var2.getInventory().setArmorContents(var5.downedArmor);
                  var5.downedItems = null;
                  var5.downedArmor = null;
                  (new BukkitRunnable() {
                     public void run() {
                        if (var4.players.containsKey(var2.getName())) {
                           BattleRoyaleX.getInstance().openParachute(var2);
                        }
                     }
                  }).runTaskLater(BattleRoyaleX.getInstance(), 2L);
               }
            } else if (var4.spectators.contains(var2.getName())) {
               if (var4.spectatorsLocation != null) {
                  var1.setRespawnLocation(var4.spectatorsLocation);
               } else {
                  var1.setRespawnLocation(var2.getLocation());
               }

               if (Config.getInstance().useGameMode3ForSpectators) {
                  var2.setGameMode(GameMode.SPECTATOR);
                  var2.sendMessage((String)Customization.getInstance().messages.get("Spectator-Tip"));
               } else {
                  Bukkit.getScheduler().scheduleSyncDelayedTask(BattleRoyaleX.getInstance(), new Runnable() {
                     public void run() {
                        if (var4.spectators.contains(var2.getName())) {
                           Utils.makeSpectator(var2);
                        }
                     }
                  }, 1L);
               }
            }
         }
      }
   }

   @EventHandler
   public void onPlayerTeleport(PlayerTeleportEvent var1) {
      Player var2 = var1.getPlayer();
      if (BattleRoyaleX.getInstance().protectedPlayers.contains(var2.getName())
         && var2.getGameMode().equals(GameMode.SPECTATOR)
         && !var1.getCause().equals(TeleportCause.PLUGIN)
         && !var1.getCause().equals(TeleportCause.COMMAND)) {
         var1.setCancelled(true);
      }
   }

   @EventHandler
   public void onEntityTarget(EntityTargetEvent var1) {
      if (var1.getTarget() != null) {
         if (var1.getEntity().hasMetadata("BRX_MOB")
            || var1.getTarget().getType().equals(EntityType.PLAYER) && BattleRoyaleX.getInstance().protectedPlayers.contains(var1.getTarget().getName())) {
            var1.setCancelled(true);
         }
      }
   }

   @EventHandler
   public void onEntityDamageByEntity(EntityDamageByEntityEvent var1) {
      if (!var1.isCancelled()) {
         Player var2 = null;
         if (var1.getDamager().getType().equals(EntityType.FIREWORK)) {
            if (var1.getEntity().getType().equals(EntityType.PLAYER) && BattleRoyaleX.getInstance().players.contains(var1.getEntity().getName())) {
               var1.setCancelled(true);
            }
         } else {
            if (var1.getDamager().getType().equals(EntityType.PLAYER)) {
               var2 = (Player)var1.getDamager();
               if (BattleRoyaleX.getInstance().protectedPlayers.contains(var2.getName())) {
                  var1.setCancelled(true);
                  return;
               }
            }

            if (var1.getEntityType().equals(EntityType.PLAYER) || var1.getEntity().hasMetadata("BRX_MOB")) {
               if (var2 == null) {
                  if (var1.getDamager() instanceof Projectile && ((Projectile)var1.getDamager()).getShooter() instanceof Player) {
                     var2 = (Player)((Projectile)var1.getDamager()).getShooter();
                  } else if (var1.getDamager().hasMetadata("BRX_MOB")) {
                     LivingEntity var3 = (LivingEntity)var1.getDamager();
                     String var4 = ChatColor.stripColor(var3.getCustomName().split("'s")[0]);
                     var2 = Bukkit.getPlayer(var4);
                  }
               }

               if (var2 == null) {
                  return;
               }

               if (var1.getEntityType().equals(EntityType.PLAYER)) {
                  final Player var8 = (Player)var1.getEntity();
                  PlayerData var10 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var8.getName());
                  if (var10 == null) {
                     return;
                  }

                  final Arena var5 = var10.arena;
                  if (var5 != null && !var5.spectators.contains(var8.getName()) && var5.players.containsKey(var2.getName())) {
                     if (((PAData)var5.players.get(var2.getName())).team.players.contains(var8.getName())) {
                        var1.setCancelled(true);
                        return;
                     }

                     if (var1.getDamager() instanceof Projectile) {
                        PlayerData var6 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName());
                        if (var6 == null) {
                           return;
                        }

                        var6.projectiles_hit++;
                        AchievementsManager.getInstance().checkPlayer(var2, AchievementType.PROJECTILES_HIT, var6.projectiles_hit);
                        if (var1.getDamager().getType().equals(EntityType.ARROW) && Config.getInstance().showHealthOnBowHit) {
                           final Player var7 = var2;
                           Bukkit.getScheduler()
                              .scheduleSyncDelayedTask(
                                 BattleRoyaleX.getInstance(),
                                 new Runnable() {
                                    public void run() {
                                       if (var5.players.containsKey(var8.getName())) {
                                          var7.sendMessage(
                                             ((String)Customization.getInstance().messages.get("Arrow-Hit"))
                                                .replace("%player%", var8.getName())
                                                .replace("%health%", new BigDecimal(var8.getHealth()).setScale(1, RoundingMode.HALF_UP).toString())
                                                .replace("%heart%", SPECIAL_CHARACTER.HEART.toString())
                                          );
                                       }
                                    }
                                 },
                                 1L
                              );
                        }
                     }

                     ((PlayerData)BattleRoyaleX.getInstance().playerData.get(var8.getName())).lastHit = var2.getName();
                     ((PlayerData)BattleRoyaleX.getInstance().playerData.get(var8.getName())).lastHitTime = System.currentTimeMillis();
                  }
               } else {
                  LivingEntity var9 = (LivingEntity)var1.getEntity();
                  if (ChatColor.stripColor(var9.getCustomName().split("'s")[0]).equals(var2.getName())) {
                     var1.setCancelled(true);
                     return;
                  }
               }
            }
         }
      }
   }

   @EventHandler
   public void onEntityDamage(EntityDamageEvent var1) {
      if (!var1.isCancelled()) {
         if (var1.getEntityType().equals(EntityType.PLAYER)) {
            Player var2 = (Player)var1.getEntity();
            if (BattleRoyaleX.getInstance().players.contains(var2.getName())) {
               PlayerData var3 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName());
               if (BattleRoyaleX.getInstance().protectedPlayers.contains(var2.getName())) {
                  var1.setCancelled(true);
                  if (var3.arena != null && var3.arena.state.AVAILABLE() && var1.getCause().equals(DamageCause.VOID)) {
                     var2.teleport(var3.arena.lobby);
                  }

                  return;
               }

               if (var3.arena != null && var3.arena.players.containsKey(var2.getName())) {
                  PAData var4 = (PAData)var3.arena.players.get(var2.getName());
                  if (var4.getArmor() > 0.0) {
                     if (var1.getFinalDamage() > var4.getArmor()) {
                        var1.setDamage(var1.getFinalDamage() - var4.getArmor());
                        var4.setArmor(0.0);
                     } else {
                        var4.setArmor(var4.getArmor() - var1.getFinalDamage());
                        var1.setDamage(0.1);
                     }

                     var3.arena.sendDataBar(var2, true);
                  }
               }
            }
         } else if (var1.getEntity().hasMetadata("BRX_DOWNED") || var1.getEntity().hasMetadata("BRX_PARACHUTE") || var1.getEntity().hasMetadata("BRX_BUS")) {
            var1.setCancelled(true);
            if (var1.getEntity().getPassenger() != null) {
               ((LivingEntity)var1.getEntity().getPassenger()).damage(var1.getDamage());
            }
         }
      }
   }

   @EventHandler
   public void onBlockPlace(BlockPlaceEvent var1) {
      Player var2 = var1.getPlayer();
      if (BattleRoyaleX.getInstance().players.contains(var2.getName())) {
         if (BattleRoyaleX.getInstance().protectedPlayers.contains(var2.getName())) {
            var1.setCancelled(true);
            return;
         }

         Arena var3 = ((PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName())).arena;
         if (var3 != null && !var3.modifiedBlocks.containsKey(var1.getBlock().getLocation())) {
            var3.modifiedBlocks.put(var1.getBlock().getLocation(), var1.getBlockReplacedState());
         }
      }
   }

   @EventHandler(priority = EventPriority.MONITOR)
   public void onBlockBreak(BlockBreakEvent var1) {
      Player var2 = var1.getPlayer();
      if (BattleRoyaleX.getInstance().players.contains(var2.getName())) {
         if (BattleRoyaleX.getInstance().protectedPlayers.contains(var2.getName())) {
            var1.setCancelled(true);
            return;
         }

         Arena var3 = ((PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName())).arena;
         if (var3 != null) {
            var1.setCancelled(true);
            Block var4 = var1.getBlock();
            if (var3.modifiedBlocks.containsKey(var4.getLocation())) {
               if (var3.extractionChests.contains(var4.getLocation())) {
                  return;
               }

               var4.setType(Material.AIR, true);
               if (var3.deathChests.containsKey(var4.getLocation())) {
                  ListIterator var5 = ((Inventory)var3.deathChests.get(var4.getLocation())).iterator();

                  while (var5.hasNext()) {
                     ItemStack var6 = (ItemStack)var5.next();
                     if (var6 != null) {
                        this.realisticPickup(var2, var3, var6);
                     }
                  }

                  var3.deathChests.remove(var4.getLocation());
                  var2.playSound(var2.getLocation(), SoundsManager.LAVA_POP, 1.0F, 1.0F);
               }
            }
         }
      }
   }

   @EventHandler
   public void onPlayerChat(AsyncPlayerChatEvent var1) {
      Player var2 = var1.getPlayer();
      if (BattleRoyaleX.getInstance().players.contains(var2.getName())) {
         PlayerData var3 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName());
         if (Config.getInstance().displayRankInChat) {
            var1.setFormat(ChatColor.GRAY + "[" + ChatColor.AQUA + var3.player_rank + ChatColor.GRAY + "] " + var1.getFormat());
         }

         if (var1.getMessage().startsWith("!") && var3.party != null) {
            var1.setMessage(var1.getMessage().replaceFirst("!", ""));
            var1.setFormat(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "PARTY" + ChatColor.GRAY + "] " + var1.getFormat());
            var1.getRecipients().clear();

            for (Player var11 : Utils.getPlayers(var3.party.players)) {
               var1.getRecipients().add(var11);
            }

            return;
         }

         Arena var4 = var3.arena;
         if (var4 != null) {
            if (var4.spectators.contains(var2.getName())) {
               if (!Config.getInstance().spectatorsChat) {
                  return;
               }

               var1.setFormat(ChatColor.GRAY + "[SPEC] " + var1.getFormat());
               var1.getRecipients().clear();

               for (Player var14 : var4.state.equals(ArenaState.ENDING) ? var4.getAllPlayers() : var4.getSpectators()) {
                  var1.getRecipients().add(var14);
               }

               return;
            }

            if (var4.state.AVAILABLE()) {
               if (!Config.getInstance().lobbyChat) {
                  return;
               }

               var1.setFormat(ChatColor.GRAY + "[Lobby] " + var1.getFormat());
               var1.getRecipients().clear();

               for (Player var13 : var4.getPlayers()) {
                  var1.getRecipients().add(var13);
               }

               return;
            }

            if (!Config.getInstance().arenaChat) {
               return;
            }

            var1.setFormat(ChatColor.GRAY + "[Playing] " + var1.getFormat());
            var1.getRecipients().clear();

            for (Player var12 : var4.getPlayers()) {
               var1.getRecipients().add(var12);
            }

            return;
         }

         if (BattleRoyaleX.getInstance().lobbyPlayers.contains(var2.getName())) {
            if (!Config.getInstance().lobbyChat) {
               return;
            }

            var1.setFormat(ChatColor.GRAY + "[Lobby] " + var1.getFormat());
            var1.getRecipients().clear();

            for (Player var6 : Utils.getPlayers(BattleRoyaleX.getInstance().lobbyPlayers)) {
               var1.getRecipients().add(var6);
            }

            return;
         }
      }
   }

   @EventHandler
   public void onPlayerInteractWithEntity(PlayerInteractEntityEvent var1) {
      final Player var2 = var1.getPlayer();
      if (BattleRoyaleX.getInstance().players.contains(var2.getName())) {
         Entity var3 = var1.getRightClicked();
         if (var3.getType().equals(EntityType.PLAYER)) {
            final PlayerData var4 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName());
            final Player var5 = (Player)var3;
            if (var2.isSneaking()
               && BattleRoyaleX.getInstance().lobbyPlayers.contains(var2.getName())
               && BattleRoyaleX.getInstance().lobbyPlayers.contains(var5.getName())) {
               if (var4.party != null) {
                  if (var4.hasCooldown(var2, "PARTY_INVITE", 3)) {
                     return;
                  }

                  if (!var4.party.leaderName.equals(var2.getName())) {
                     var2.sendMessage((String)Customization.getInstance().messages.get("Party-Must-Be-Leader"));
                     return;
                  }

                  if (var4.party.invited.contains(var5.getName())) {
                     var2.sendMessage((String)Customization.getInstance().messages.get("Player-Already-Invited"));
                     return;
                  }

                  var4.party.invite(var5);
               } else {
                  PlayerData var7 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var5.getName());
                  if (var7.party != null && !var4.hasCooldown(var2, "PARTY_JOIN", 3)) {
                     var7.party.join(var2);
                  }
               }

               return;
            }

            if (var4.arena != null
               && var4.arena.players.containsKey(var2.getName())
               && ((PAData)var4.arena.players.get(var2.getName())).team.players.contains(var5.getName())) {
               if (((PAData)var4.arena.players.get(var5.getName())).downed_chicken != null) {
                  final PAData var6 = (PAData)var4.arena.players.get(var2.getName());
                  if (var6.channelingTask == null) {
                     var6.lastChannelingTime = System.currentTimeMillis();
                     var6.channelingTask = (new BukkitRunnable() {
                           int progress = 0;
                           int needed = 20;

                           public void run() {
                              long var1x = System.currentTimeMillis() - var6.lastChannelingTime;
                              if (var1x > 500L) {
                                 this.cancel();
                                 var6.channelingTask = null;
                              } else {
                                 this.progress++;
                                 if (this.progress == this.needed) {
                                    this.cancel();
                                    var6.channelingTask = null;
                                    String var3x = ChatColor.GREEN + "Revived!";
                                    if (TitleManager.getInstance().isActionBarEnabled()) {
                                       TitleManager.getInstance().sendForcedActionBar(var2, var3x, var4);
                                       TitleManager.getInstance()
                                          .sendForcedActionBar(var5, var3x, (PlayerData)BattleRoyaleX.getInstance().playerData.get(var5.getName()));
                                    } else {
                                       var3x = Customization.getInstance().prefix + var3x;
                                       var2.sendMessage(var3x);
                                       var5.sendMessage(var3x);
                                    }

                                    var2.getWorld().playSound(var2.getLocation(), SoundsManager.ENDERDRAGON_GROWL, 1.0F, 1.0F);
                                    PlayerListener.this.revive(var5);
                                 } else {
                                    String var5x = ChatColor.GRAY + "Reviving: " + Utils.getProgressBar(this.progress, this.needed);
                                    if (TitleManager.getInstance().isActionBarEnabled()) {
                                       TitleManager.getInstance().sendForcedActionBar(var2, var5x, var4);
                                       TitleManager.getInstance()
                                          .sendForcedActionBar(var5, var5x, (PlayerData)BattleRoyaleX.getInstance().playerData.get(var5.getName()));
                                    } else {
                                       var5x = Customization.getInstance().prefix + var5x;
                                       var2.sendMessage(var5x);
                                       var5.sendMessage(var5x);
                                    }

                                    var2.getWorld().playSound(var2.getLocation(), SoundsManager.CLICK, 1.0F, 1.0F);
                                 }
                              }
                           }
                        })
                        .runTaskTimer(BattleRoyaleX.getInstance(), 0L, 10L);
                  } else {
                     var6.lastChannelingTime = System.currentTimeMillis();
                  }
               }

               return;
            }
         }
      }
   }

   @EventHandler
   public void PlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent var1) {
      Player var2 = var1.getPlayer();
      if (BattleRoyaleX.getInstance().players.contains(var2.getName())) {
         var1.setCancelled(true);
         Entity var3 = var1.getRightClicked();
         if (var3.getType().equals(EntityType.ARMOR_STAND) && var3.hasMetadata("BRX_ITEM-DROP")) {
            PlayerData var4 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName());
            Arena var5 = var4.arena;
            if (!var5.players.containsKey(var2.getName()) || ((PAData)var5.players.get(var2.getName())).downed_chicken != null) {
               return;
            }

            ArmorStand var6 = (ArmorStand)var3;
            if (var6.getItemInHand() != null && !var6.getItemInHand().getType().equals(Material.AIR)) {
               this.realisticPickup(var2, var5, var6.getItemInHand());
            }

            var3.remove();
         }
      }
   }

   @EventHandler
   public void onPlayerDropItem(PlayerDropItemEvent var1) {
      Player var2 = var1.getPlayer();
      if (BattleRoyaleX.getInstance().players.contains(var2.getName())) {
         if (BattleRoyaleX.getInstance().protectedPlayers.contains(var1.getPlayer().getName())) {
            var1.setCancelled(true);
         } else if (Config.getInstance().realisticDrops) {
            final Item var3 = var1.getItemDrop();
            (new BukkitRunnable() {
               public void run() {
                  boolean var1x = RealisticItems.getInstance().makeDropPhyiscal(var3);
                  if (var1x) {
                     this.cancel();
                  }
               }
            }).runTaskTimer(BattleRoyaleX.getInstance(), 7L, 7L);
         }
      }
   }

   @EventHandler
   public void onPlayerPickupItem(PlayerPickupItemEvent var1) {
      Player var2 = var1.getPlayer();
      if (BattleRoyaleX.getInstance().players.contains(var1.getPlayer().getName())) {
         if (BattleRoyaleX.getInstance().protectedPlayers.contains(var1.getPlayer().getName())) {
            var1.setCancelled(true);
            return;
         }

         PlayerData var3 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName());
         if (var3.arena != null && var3.arena.players.containsKey(var2.getName()) && ((PAData)var3.arena.players.get(var2.getName())).downed_chicken != null) {
            var1.setCancelled(true);
         }
      }
   }

   @EventHandler
   public void onPlayerLoseHunger(FoodLevelChangeEvent var1) {
      if (var1.getEntityType().equals(EntityType.PLAYER)) {
         Player var2 = (Player)var1.getEntity();
         if (BattleRoyaleX.getInstance().players.contains(var2.getName())
            && (BattleRoyaleX.getInstance().protectedPlayers.contains(var2.getName()) || Config.getInstance().disableHunger)) {
            var1.setCancelled(true);
         }
      }
   }

   @EventHandler
   public void onEntityDismountEvent(EntityDismountEvent var1) {
      if (var1.getEntity().getType().equals(EntityType.PLAYER)) {
         final Player var2 = (Player)var1.getEntity();
         if (BattleRoyaleX.getInstance().players.contains(var2.getName())) {
            Arena var3 = ((PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName())).arena;
            if (var3 == null) {
               return;
            }

            final Entity var4 = var1.getDismounted();
            if (var4.hasMetadata("BRX_BUS")) {
               var4.remove();

               for (Player var6 : var3.getPlayers()) {
                  var6.showPlayer(var2);
                  var2.showPlayer(var6);
               }

               if (BattleRoyaleX.getInstance().offHandAvailable) {
                  BattleRoyaleX.getInstance().openParachute(var2);
               } else {
                  (new BukkitRunnable() {
                     public void run() {
                        BattleRoyaleX.getInstance().openParachute(var2);
                     }
                  }).runTaskLater(BattleRoyaleX.getInstance(), 1L);
               }
            } else if (var4.isValid() && (var4.hasMetadata("BRX_PARACHUTE") || var4.hasMetadata("BRX_DOWNED"))) {
               (new BukkitRunnable() {
                  public void run() {
                     if (var4.isValid() && var4.getWorld().getUID().equals(var2.getWorld().getUID())) {
                        if (XMaterial.supports(16)) {
                           var4.addPassenger(var2);
                        } else {
                           var4.setPassenger(var2);
                        }
                     }
                  }
               }).runTaskLater(BattleRoyaleX.getInstance(), 5L);
               if (var4.hasMetadata("BRX_DOWNED")) {
                  var2.damage(2.0);
               }
            }
         }
      }
   }

   @EventHandler
   public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent var1) {
      Player var2 = var1.getPlayer();
      String var3 = var1.getMessage().replace("/", "").split(" ")[0].toLowerCase();
      if (Config.getInstance().commandAliases.contains(var3)) {
         var3 = "battleroyale";
         var1.setMessage(var1.getMessage().replace(var1.getMessage().split(" ")[0], "/" + var3));
      }

      if (BattleRoyaleX.getInstance().players.contains(var2.getName())) {
         if (var2.hasPermission("battleroyalex.unblockcmds")) {
            return;
         }

         if (!Config.getInstance().allowedCommands.contains(var3)) {
            var1.setCancelled(true);
            var2.sendMessage((String)Customization.getInstance().messages.get("Command-Block"));
         }
      }
   }

   @EventHandler
   public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent var1) {
      Player var2 = var1.getPlayer();
      if (BattleRoyaleX.getInstance().players.contains(var2.getName()) && var1.getItem().getType().equals(XMaterial.GOLDEN_APPLE.parseMaterial())) {
         PlayerData var3 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName());
         if (var3.arena != null && var3.arena.players.containsKey(var2.getName())) {
            PAData var4 = (PAData)var3.arena.players.get(var2.getName());
            var4.setArmor(Math.min(var4.getArmor() + Config.getInstance().armorRefillAmount, Config.getInstance().maxArmor));
            var3.arena.sendDataBar(var2, true);
            var1.setCancelled(true);
            if (var2.getItemInHand().getAmount() > 1) {
               var2.getItemInHand().setAmount(var2.getItemInHand().getAmount() - 1);
            } else {
               var2.setItemInHand(new ItemStack(Material.AIR));
            }
         }
      }
   }

   @EventHandler
   public void onServerPing(ServerListPingEvent var1) {
      if (BattleRoyaleX.getInstance().isOneGamePerServer()) {
         var1.setMotd(((Arena)BattleRoyaleX.getInstance().arenas.values().toArray()[0]).state.value);
      }
   }

   @EventHandler
   public void onPrepareItemCraftEvent(PrepareItemCraftEvent var1) {
      ItemStack var2 = var1.getInventory().getResult();
      if (var2 != null) {
         if (RecipeManager.customRecipes.containsKey(var2)) {
            String var3 = ((HumanEntity)var1.getViewers().get(0)).getName();
            PlayerData var4 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var3);
            if (var4.arena == null) {
               var1.getInventory().setResult(new ItemStack(Material.AIR));
               return;
            }

            if (var4.player_rank < ((RecipeData)RecipeManager.customRecipes.get(var2)).requiredRank) {
               var1.getInventory().setResult(new ItemStack(Material.AIR));
            }
         }
      }
   }

   @EventHandler
   public void onCraftItem(CraftItemEvent var1) {
      Player var2 = (Player)var1.getWhoClicked();
      if (BattleRoyaleX.getInstance().players.contains(var2.getName())) {
         PlayerData var3 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName());
         if (var3.arena != null) {
            ItemStack var4 = var1.getCurrentItem();
            if (RecipeManager.customRecipes.containsKey(var4)) {
               PAData var5 = (PAData)var3.arena.players.get(var2.getName());
               int var6 = 0;

               for (ItemStack var8 : var5.customCrafted) {
                  if (var8.equals(var4)) {
                     var6++;
                  }
               }

               int var14 = ((RecipeData)RecipeManager.customRecipes.get(var4)).maxCrafts;
               if (var6 >= var14) {
                  var1.setCancelled(true);
                  var2.sendMessage((String)Customization.getInstance().messages.get("Craft-Deny"));
                  return;
               }

               if (var1.getClick().equals(ClickType.SHIFT_LEFT)) {
                  int var15 = 0;
                  int var9 = 1;

                  for (ItemStack var13 : var1.getInventory().getMatrix()) {
                     if (var13 != null && !var13.getType().equals(Material.AIR)) {
                        if (var15 == 0) {
                           var9 = var13.getAmount();
                        } else {
                           var9 = Math.min(var9, var13.getAmount());
                        }

                        var15++;
                     }
                  }

                  if (var6 + var9 > var14) {
                     var1.setCancelled(true);
                     var2.sendMessage((String)Customization.getInstance().messages.get("Shift-Click-Craft-Deny"));
                     return;
                  }

                  for (int var16 = var6; var16 < var6 + var9; var16++) {
                     var5.customCrafted.add(var4.clone());
                  }

                  return;
               }

               var5.customCrafted.add(var4.clone());
            }
         }
      }
   }

   @EventHandler
   public void onPlayerDrinkPotion(final PlayerItemConsumeEvent var1) {
      if (Config.getInstance().autoRemovePotionBottles) {
         if (BattleRoyaleX.getInstance().players.contains(var1.getPlayer().getName())) {
            if (var1.getItem().getType().equals(Material.POTION)) {
               (new BukkitRunnable() {
                  public void run() {
                     var1.getPlayer().getInventory().remove(Material.GLASS_BOTTLE);
                  }
               }).runTaskLater(BattleRoyaleX.getInstance(), 1L);
            }
         }
      }
   }

   private String getDeathMessage(Player var1, Player var2) {
      if (var2 != null) {
         return var2.getName().equals(var1.getName())
            ? Customization.getInstance().player_suicide.replace("%player%", var1.getName())
            : ((String)Customization.getInstance().killMessages.get(Utils.random.nextInt(Customization.getInstance().killMessages.size())))
               .replace("%player%", var1.getName())
               .replace("%killer%", var2.getName());
      } else {
         return var1.getLastDamageCause() != null && Customization.getInstance().deathMessages.containsKey(var1.getLastDamageCause().getCause().name())
            ? ((String)Customization.getInstance().deathMessages.get(var1.getLastDamageCause().getCause().name())).replace("%player%", var1.getName())
            : ((String)Customization.getInstance().deathMessages.get("UNKNOWN")).replace("%player%", var1.getName());
      }
   }

   private void realisticPickup(Player var1, Arena var2, ItemStack var3) {
      if (!var3.getType().equals(Material.GOLD_INGOT) || !BattleRoyaleX.getInstance().claimCash(var1, (PAData)var2.players.get(var1.getName()), var3)) {
         String var4 = var3.getType().name();
         if (var4.contains("HELMET") && var1.getInventory().getHelmet() == null) {
            var1.getInventory().setHelmet(var3);
         } else if (var4.contains("CHESTPLATE") && var1.getInventory().getChestplate() == null) {
            var1.getInventory().setChestplate(var3);
         } else if (var4.contains("LEG") && var1.getInventory().getLeggings() == null) {
            var1.getInventory().setLeggings(var3);
         } else if (var4.contains("BOOTS") && var1.getInventory().getBoots() == null) {
            var1.getInventory().setBoots(var3);
         } else {
            var1.getInventory().addItem(new ItemStack[]{var3});
         }
      }
   }

   private void awardKiller(Player var1, Player var2, PlayerData var3) {
      PlayerData var4 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var1.getName());
      Arena var5 = var4.arena;
      var4.kills++;
      AchievementsManager.getInstance().checkPlayer(var1, AchievementType.KILLS, var4.kills);
      int var6 = Config.getInstance().coinsPerKill * var4.modifier;
      var4.addCoins(var1, var6);
      int var7;
      if (Config.getInstance().maxExpPerKill - Config.getInstance().minExpPerKill > 0) {
         var7 = (Utils.random.nextInt(Config.getInstance().maxExpPerKill - Config.getInstance().minExpPerKill + 1) + Config.getInstance().minExpPerKill)
            * var4.modifier;
      } else {
         var7 = Config.getInstance().minExpPerKill * var4.modifier;
      }

      var1.sendMessage(
         ((String)Customization.getInstance().messages.get("Player-Kill"))
               .replace("%target%", var2.getName())
               .replace("%coins%", String.valueOf(var6))
               .replace("%exp%", String.valueOf(var7))
            + ChatColor.YELLOW
            + (var4.modifier > 1 ? " (x" + var4.modifier + ")" : "")
      );
      var1.playSound(var1.getLocation(), SoundsManager.NOTE_PLING, 1.0F, 1.0F);
      RanksManager.getInstance().addExp(var1, var7);
      var5.killers.put(var1.getName(), var5.killers.containsKey(var1.getName()) ? (Integer)var5.killers.get(var1.getName()) + 1 : 1);
      TitleManager.getInstance()
         .sendForcedActionBar(var1, ChatColor.RED + "You now have: " + ChatColor.YELLOW + var5.killers.get(var1.getName()) + ChatColor.RED + " kills!", var4);
      PAData var8 = (PAData)var5.players.get(var1.getName());
      var8.team.kills++;
      var8.killstreak++;
      if (Config.getInstance().killstreaks.containsKey(var8.killstreak)) {
         for (String var10 : (List<String>)Config.getInstance().killstreaks.get(var8.killstreak)) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), var10.replace("%player%", var1.getName()));
         }

         var1.sendMessage(((String)Customization.getInstance().messages.get("Killstreak-Award")).replace("%killstreak%", String.valueOf(var8.killstreak)));
         TitleManager.getInstance()
            .sendTitle(var1, ((String)Customization.getInstance().titles.get("Killstreak")).replace("%killstreak%", String.valueOf(var8.killstreak)));
      }

      int var13 = var3.getTotalBounty();
      if (var13 > 0) {
         var4.addCoins(var1, var13);
         var3.bounties.clear();
         String var14 = ((String)Customization.getInstance().messages.get("Bounty-Claim"))
            .replace("%killer%", var1.getName())
            .replace("%player%", var2.getName())
            .replace("%bounty%", String.valueOf(var13));

         for (Player var12 : var5.getPlayers()) {
            var12.sendMessage(var14);
         }
      }
   }

   private void spawnDeathChest(Arena var1, Player var2, List<ItemStack> var3) {
      Block var4 = var2.getLocation().add(0.0, 1.0, 0.0).getBlock();
      if (!var1.modifiedBlocks.containsKey(var4.getLocation())) {
         var1.modifiedBlocks.put(var4.getLocation(), var4.getState());
      }

      var4.setType(Material.CHEST);
      Inventory var5 = Bukkit.createInventory(null, 54, var2.getName() + "'s items");

      for (ItemStack var7 : var3) {
         var5.addItem(new ItemStack[]{var7});
      }

      var1.deathChests.put(var4.getLocation(), var5);
   }

   private void revive(Player var1) {
      var1.removePotionEffect(PotionEffectType.BLINDNESS);
      var1.removePotionEffect(PotionEffectType.SLOW);
      Arena var2 = ((PlayerData)BattleRoyaleX.getInstance().playerData.get(var1.getName())).arena;
      PAData var3 = (PAData)var2.players.get(var1.getName());
      if (var3.downed_chicken != null) {
         var3.downed_chicken.remove();
         var3.downed_chicken = null;
      }

      var1.getInventory().setContents(var3.downedItems);
      var1.getInventory().setArmorContents(var3.downedArmor);
      var3.downedItems = null;
      var3.downedArmor = null;
   }
}
