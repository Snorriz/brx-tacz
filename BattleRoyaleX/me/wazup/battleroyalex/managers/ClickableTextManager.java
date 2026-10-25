package me.wazup.battleroyalex.managers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.bukkit.entity.Player;

public class ClickableTextManager {
   private Method fromLegacyText;
   private Constructor<?> textComponentConstructor;
   private Constructor<?> clickEvent;
   private Class<Enum> actionEnum;
   private Enum<?> RUN_COMMAND;
   private Method setClickEvent;
   private Method sendMessage;

   public ClickableTextManager() throws Exception {
      Class var1 = Class.forName("net.md_5.bungee.api.chat.TextComponent");
      this.fromLegacyText = var1.getMethod("fromLegacyText", new Class[]{String.class});
      Object[] var2 = this.getBaseComponent("Test");

      for (Constructor var6 : var1.getConstructors()) {
         try {
            var6.newInstance(new Object[]{var2});
            this.textComponentConstructor = var6;
            break;
         } catch (Exception var8) {
         }
      }

      if (this.textComponentConstructor == null) {
         throw new NoSuchMethodException();
      }

      Class var9 = Class.forName("net.md_5.bungee.api.chat.ClickEvent");
      this.actionEnum = (Class<Enum>)this.getNestedClass(var9, "Action");
      this.RUN_COMMAND = Enum.valueOf(this.actionEnum, "RUN_COMMAND");
      this.clickEvent = var9.getConstructor(new Class[]{this.actionEnum, String.class});
      this.setClickEvent = var1.getMethod("setClickEvent", new Class[]{Class.forName("net.md_5.bungee.api.chat.ClickEvent")});
      this.sendMessage = this.getNestedClass(Class.forName("org.bukkit.entity.Player"), "Spigot")
         .getMethod("sendMessage", new Class[]{Class.forName("net.md_5.bungee.api.chat.BaseComponent")});
   }

   private Class<?> getNestedClass(Class<?> var1, String var2) throws Exception {
      for (Class var6 : var1.getClasses()) {
         if (var6.getSimpleName().equals(var2)) {
            return var6;
         }
      }

      return null;
   }

   private Object[] getBaseComponent(String var1) throws Exception {
      return (Object[])this.fromLegacyText.invoke(null, new Object[]{var1});
   }

   private Object getTextComponent(String var1, String var2) throws Exception {
      Object[] var3 = this.getBaseComponent(var1);
      Object var4 = this.textComponentConstructor.newInstance(new Object[]{var3});
      this.setClickEvent.invoke(var4, new Object[]{this.clickEvent.newInstance(new Object[]{this.RUN_COMMAND, "/" + var2})});
      return var4;
   }

   private Object getSpigot(Player var1) throws Exception {
      return Player.class.getMethod("spigot", new Class[0]).invoke(var1, new Object[0]);
   }

   public void sendMessage(Player var1, String var2, String var3) {
      try {
         this.sendMessageInternal(var1, var2, var3);
      } catch (Exception var4) {
      }
   }

   private void sendMessageInternal(Player var1, String var2, String var3) throws Exception {
      try {
         this.sendMessage.invoke(this.getSpigot(var1), new Object[]{this.getTextComponent(var2, var3)});
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException var5) {
         var5.printStackTrace();
      }
   }
}
