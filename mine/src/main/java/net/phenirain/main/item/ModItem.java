package net.phenirain.main.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.phenirain.main.Main;
import net.phenirain.main.blocks.ModBlock;

import java.awt.event.InputEvent;

public class ModItem {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
    public static final RegistryObject<Item> StrawberryJam = ITEMS.register("strawberry_jam",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> Blueberry = ITEMS.register("blueberry_jam",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> HoneyJar = ITEMS.register("honey_jar",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ApricotJam = ITEMS.register("apricot_jam",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> CAGE =
            ITEMS.register("cage", () -> new BlockItem(ModBlock
                    .CAGE.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Item> CORN_BLOCK =
            ITEMS.register("corn_block", () -> new BlockItem(ModBlock
                    .CORN_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Item> MANURE_BLOCK =
            ITEMS.register("manure_block", () -> new BlockItem(ModBlock
                    .MANURE_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Item> BRICK_OVEN =
            ITEMS.register("brick_oven", () -> new BlockItem(ModBlock
                    .BRICK_OVEN.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));


}
