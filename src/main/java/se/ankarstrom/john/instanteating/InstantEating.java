package se.ankarstrom.john.instanteating;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("instanteating")
public class InstantEating
{
    public InstantEating() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

	@SubscribeEvent
	public void onPlayerRightClick(RightClickItem event)
	{
		ItemStack itemStack = event.getItemStack();
		Item item = itemStack.getItem();
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();

		if (item.isFood())
		{
			event.setCanceled(true);
			if (!player.getFoodStats().needFood() && !item.getFood().canEatWhenFull())
				return;
			item.onItemUseFinish(itemStack, player.world, player);
		}
	}

}
