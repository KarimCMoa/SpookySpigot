package protocolsupport.injector;

import com.google.common.collect.ImmutableList;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import net.minecraft.server.Block;
import net.minecraft.server.BlockStateList;
import net.minecraft.server.Blocks;
import net.minecraft.server.IBlockData;
import net.minecraft.server.Item;
import net.minecraft.server.ItemAnvil;
import net.minecraft.server.ItemBlock;
import net.minecraft.server.ItemCloth;
import net.minecraft.server.ItemSpade;
import net.minecraft.server.MinecraftKey;
import net.minecraft.server.RegistryBlocks;
import net.minecraft.server.RegistryID;
import net.minecraft.server.RegistryMaterials;
import net.minecraft.server.TileEntity;
import org.bukkit.Bukkit;
import protocolsupport.server.block.BlockAnvil;
import protocolsupport.server.block.BlockCarpet;
import protocolsupport.server.block.BlockEnchantTable;
import protocolsupport.server.block.BlockSnow;
import protocolsupport.server.item.ItemSnow;
import protocolsupport.server.tileentity.TileEntityEnchantTable;
import protocolsupport.utils.Utils;

public class ServerInjector {
    public static void inject() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        ServerInjector.registerTileEntity(TileEntityEnchantTable.class, "EnchantTable");
        ServerInjector.registerBlock(116, "enchanting_table", (Block)new BlockEnchantTable());
        ServerInjector.registerBlock(145, "anvil", new ItemAnvil((Block)new BlockAnvil()).b("anvil"));
        ServerInjector.registerBlock(171, "carpet", new ItemCloth((Block)new BlockCarpet()).b("woolCarpet"));
        ServerInjector.registerBlock(78, "snow_layer", (ItemBlock)new ItemSnow(new BlockSnow()));
        ServerInjector.fixBlocksRefs();
        ServerInjector.fixShovel();
        Bukkit.resetRecipes();
    }

    private static void registerTileEntity(Class<? extends TileEntity> entityClass, String name) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        ((Map)Utils.setAccessible(TileEntity.class.getDeclaredField("f")).get(null)).put(name, entityClass);
        ((Map)Utils.setAccessible(TileEntity.class.getDeclaredField("g")).get(null)).put(entityClass, name);
    }

    private static void registerBlock(int id, String name, Block block) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        MinecraftKey stringkey = new MinecraftKey(name);
        ItemBlock itemblock = new ItemBlock(block);
        Block.REGISTRY.a(id, stringkey, block);
        for (IBlockData blockdata : block.P().a()) {
            int stateId = id << 4 | block.toLegacyData(blockdata);
            Block.d.a(blockdata, stateId);
        }
        Item.REGISTRY.a(id, stringkey, itemblock);
        ((Map)Utils.setAccessible(Item.class.getDeclaredField("a")).get(null)).put(block, itemblock);
    }

    private static void registerBlock(int id, String name, ItemBlock itemblock) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        MinecraftKey stringkey = new MinecraftKey(name);
        Block.REGISTRY.a(id, stringkey, itemblock.d());
        for (IBlockData blockdata : itemblock.d().P().a()) {
            int stateId = id << 4 | itemblock.d().toLegacyData(blockdata);
            Block.d.a(blockdata, stateId);
        }
        Item.REGISTRY.a(id, stringkey, itemblock);
        ((Map)Utils.setAccessible(Item.class.getDeclaredField("a")).get(null)).put(itemblock.d(), itemblock);
    }

    private static void fixBlocksRefs() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        for (Field field : Blocks.class.getDeclaredFields()) {
            Block newblock;
            Block block;
            field.setAccessible(true);
            if (!Block.class.isAssignableFrom(field.getType()) || (block = (Block)field.get(null)) == (newblock = Block.getById((int)Block.getId((Block)block)))) continue;
            Utils.setStaticFinalField(field, (Object)newblock);
        }
    }

    public static void fixShovel() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        Set blocks = (Set)Utils.setAccessible(ItemSpade.class.getDeclaredField("c")).get(null);
        blocks.add(Blocks.SNOW_LAYER);
    }
}

