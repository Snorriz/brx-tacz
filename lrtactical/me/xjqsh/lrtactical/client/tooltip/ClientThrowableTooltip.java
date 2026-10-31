package me.xjqsh.lrtactical.client.tooltip;

import me.xjqsh.lrtactical.api.LrTacticalAPI;
import me.xjqsh.lrtactical.inventory.tooltip.ThrowableTooltip;

public class ClientThrowableTooltip extends AbstractClientItemTooltip {
   public ClientThrowableTooltip(ThrowableTooltip tooltip) {
      LrTacticalAPI.getThrowableIndex(tooltip.getStack()).ifPresent(index -> this.build(index.getTooltip(), index.getData().getTooltipLines()));
   }
}
