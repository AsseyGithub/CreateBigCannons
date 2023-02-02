package rbasamoyai.createbigcannons.munitions.fuzes;

import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.utility.Lang;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import rbasamoyai.createbigcannons.CreateBigCannons;
import rbasamoyai.createbigcannons.base.CBCTooltip;
import rbasamoyai.createbigcannons.config.CBCConfigs;
import rbasamoyai.createbigcannons.munitions.AbstractCannonProjectile;

import java.util.List;

public class ImpactFuzeItem extends FuzeItem {

	public ImpactFuzeItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean onProjectileImpact(ItemStack stack, AbstractCannonProjectile projectile, HitResult result) {
		float f = this.getDetonateChance();
		return projectile.getProjectileMass() <= 0 && f > 0 && projectile.level.getRandom().nextFloat() < f;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, level, tooltip, flag);
		CBCTooltip.appendImpactFuzeText(stack, level, tooltip, flag, this.getDetonateChance());
	}
	
	protected float getDetonateChance() {
		return CBCConfigs.SERVER.munitions.impactFuzeDetonationChance.getF();
	}
	
	@Override
	public void addExtraInfo(List<Component> tooltip, boolean isSneaking, ItemStack stack) {
		super.addExtraInfo(tooltip, isSneaking, stack);
		MutableComponent info = Lang.builder("item")
				.translate(CreateBigCannons.MOD_ID + ".impact_fuze.tooltip.shell_info", (int)(this.getDetonateChance() * 100.0f))
				.component();
		tooltip.addAll(TooltipHelper.cutTextComponent(info, ChatFormatting.GRAY, ChatFormatting.GREEN, 6));
	}
	
}
