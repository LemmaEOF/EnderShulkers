package space.bbkr.endershulkers;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.LevelComponentCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import space.bbkr.endershulkers.block.EnderShulkerBlock;
import space.bbkr.endershulkers.block.entity.EnderShulkerBlockEntity;
import space.bbkr.endershulkers.component.EnderShulkerComponent;

import java.util.function.Supplier;

public class EnderShulkers implements ModInitializer {
	public static final String MODID = "endershulkers";

	public static final Logger logger = LogManager.getLogger();

	public static final ComponentType<EnderShulkerComponent> ENDER_SHULKER_COMPONENT = ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier(MODID, "ender_shulkers"), EnderShulkerComponent.class);

	public static final Block ENDER_SHULKER_BLOCK = register("ender_shulker_box", new EnderShulkerBlock(FabricBlockSettings.copy(Blocks.SHULKER_BOX).build()));
	public static final BlockEntityType<EnderShulkerBlockEntity> ENDER_SHULKER_BLOCK_ENTITY = register("ender_shulker_box", EnderShulkerBlockEntity::new, ENDER_SHULKER_BLOCK);

	@Override
	public void onInitialize() {
		LevelComponentCallback.EVENT.register(((levelProperties, container) -> container.put(ENDER_SHULKER_COMPONENT, EnderShulkerComponent.INSTANCE)));
	}

	private static Block register(String name, Block block) {
		return Registry.register(Registry.BLOCK, new Identifier(MODID, name), block);
	}

	private static <T extends BlockEntity> BlockEntityType<T> register(String name, Supplier<T> be, Block... blocks) {
		return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MODID, name), BlockEntityType.Builder.create(be, blocks).build(null));
	}
}
