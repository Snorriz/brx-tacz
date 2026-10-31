package me.xjqsh.lrtactical.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tacz.guns.client.gui.GunSmithTableScreen;
import com.tacz.guns.client.resource.ClientAssetsManager;
import com.tacz.guns.client.resource.pojo.PackInfo;
import com.tacz.guns.crafting.GunSmithTableRecipe;
import com.tacz.guns.inventory.GunSmithTableMenu;
import java.util.List;
import me.xjqsh.lrtactical.api.item.ICustomItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GunSmithTableScreen.class)
public abstract class GunSmithTableScreenMixin extends AbstractContainerScreen<GunSmithTableMenu> {
   public GunSmithTableScreenMixin(GunSmithTableMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
      super(pMenu, pPlayerInventory, pTitle);
   }

   @Inject(method = "renderPackInfo", at = @At("HEAD"), remap = false, cancellable = true)
   private void renderPackInfo(GuiGraphics gui, GunSmithTableRecipe recipe, CallbackInfo ci) {
      ItemStack output = recipe.getOutput();
      if (output.m_41720_() instanceof ICustomItem customItem) {
         ResourceLocation id = customItem.getId(output);
         PackInfo packInfo = ClientAssetsManager.INSTANCE.getPackInfo(id);
         PoseStack poseStack = gui.m_280168_();
         if (packInfo != null) {
            poseStack.m_85836_();
            poseStack.m_85841_(0.75F, 0.75F, 1.0F);
            Component nameText = Component.m_237115_(packInfo.getName());
            gui.m_280614_(
               this.f_96547_, nameText, (int)((this.f_97735_ + 6) / 0.75F), (int)((this.f_97736_ + 122) / 0.75F), ChatFormatting.DARK_GRAY.m_126665_(), false
            );
            poseStack.m_85849_();
            poseStack.m_85836_();
            poseStack.m_85841_(0.5F, 0.5F, 1.0F);
            int offsetX = (this.f_97735_ + 6) * 2;
            int offsetY = (this.f_97736_ + 123) * 2;
            int nameWidth = this.f_96547_.m_92852_(nameText);
            Component ver = Component.m_237113_("v" + packInfo.getVersion()).m_130940_(ChatFormatting.UNDERLINE);
            gui.m_280614_(this.f_96547_, ver, (int)(offsetX + nameWidth * 0.75F / 0.5F + 5.0F), offsetY, ChatFormatting.DARK_GRAY.m_126665_(), false);
            offsetY += 14;
            String descKey = packInfo.getDescription();
            if (StringUtils.isNoneBlank(new CharSequence[]{descKey})) {
               Component desc = Component.m_237115_(descKey);

               for (FormattedCharSequence charSequence : this.f_96547_.m_92923_(desc, 245)) {
                  gui.m_280649_(this.f_96547_, charSequence, offsetX, offsetY, ChatFormatting.DARK_GRAY.m_126665_(), false);
                  offsetY += 9;
               }

               offsetY += 3;
            }

            gui.m_280614_(
               this.f_96547_,
               Component.m_237115_("gui.tacz.gun_smith_table.license").m_7220_(Component.m_237113_(packInfo.getLicense()).m_130940_(ChatFormatting.DARK_GRAY)),
               offsetX,
               offsetY,
               ChatFormatting.DARK_GRAY.m_126665_(),
               false
            );
            offsetY += 12;
            List<String> authors = packInfo.getAuthors();
            if (!authors.isEmpty()) {
               gui.m_280614_(
                  this.f_96547_,
                  Component.m_237115_("gui.tacz.gun_smith_table.authors")
                     .m_7220_(Component.m_237113_(StringUtils.join(authors, ", ")).m_130940_(ChatFormatting.DARK_GRAY)),
                  offsetX,
                  offsetY,
                  ChatFormatting.DARK_GRAY.m_126665_(),
                  false
               );
               offsetY += 12;
            }

            gui.m_280614_(
               this.f_96547_,
               Component.m_237115_("gui.tacz.gun_smith_table.date").m_7220_(Component.m_237113_(packInfo.getDate()).m_130940_(ChatFormatting.DARK_GRAY)),
               offsetX,
               offsetY,
               ChatFormatting.DARK_GRAY.m_126665_(),
               false
            );
            poseStack.m_85849_();
         } else {
            ResourceLocation recipeId = recipe.m_6423_();
            gui.m_280614_(
               this.f_96547_,
               Component.m_237115_("gui.tacz.gun_smith_table.error").m_130940_(ChatFormatting.DARK_RED),
               this.f_97735_ + 6,
               this.f_97736_ + 122,
               11468800,
               false
            );
            gui.m_280614_(
               this.f_96547_,
               Component.m_237110_("gui.tacz.gun_smith_table.error.id", new Object[]{recipeId.toString()}).m_130940_(ChatFormatting.DARK_RED),
               this.f_97735_ + 6,
               this.f_97736_ + 134,
               16777215,
               false
            );
            PackInfo errorPackInfo = ClientAssetsManager.INSTANCE.getPackInfo(id);
            if (errorPackInfo != null) {
               gui.m_280614_(
                  this.f_96547_,
                  Component.m_237115_(errorPackInfo.getName()).m_130940_(ChatFormatting.DARK_RED),
                  this.f_97735_ + 6,
                  this.f_97736_ + 146,
                  11468800,
                  false
               );
            }
         }

         ci.cancel();
      }
   }
}
