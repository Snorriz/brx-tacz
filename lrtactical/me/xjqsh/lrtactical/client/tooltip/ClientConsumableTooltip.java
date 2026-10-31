package me.xjqsh.lrtactical.client.tooltip;

import me.xjqsh.lrtactical.api.LrTacticalAPI;
import me.xjqsh.lrtactical.inventory.tooltip.ConsumableTooltip;

public class ClientConsumableTooltip extends AbstractClientItemTooltip {
   public ClientConsumableTooltip(ConsumableTooltip tooltip) {
      LrTacticalAPI.getConsumableIndex(tooltip.getStack()).ifPresent(index -> this.build(index.getTooltip(), index.getData().getTooltipLines()));
   }
}
