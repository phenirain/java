package net.phenirain.main.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.phenirain.main.Main;

public class ModBlock {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);

    public static final RegistryObject<Block> CORN_BLOCK =
            BLOCKS.register("corn_block", () ->
                    new Block(BlockBehaviour.Properties.of(Material.VEGETABLE)));

    public static final RegistryObject<Block> MANURE_BLOCK =
            BLOCKS.register("manure_block", () ->
                    new Block(BlockBehaviour.Properties.of(Material.GRASS)));

    public static final RegistryObject<Block> CAGE =
            BLOCKS.register("cage", () ->
                    new LeavesBlock(BlockBehaviour.Properties.of(Material.METAL)));

    public static final RegistryObject<Block> BRICK_OVEN =
            BLOCKS.register("brick_oven", () ->
                    new Block(BlockBehaviour.Properties.of(Material.STONE)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
