/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.Block
 *  net.minecraft.server.BlockPosition
 *  net.minecraft.server.Blocks
 *  net.minecraft.server.ContainerEnchantTable
 *  net.minecraft.server.Enchantment
 *  net.minecraft.server.EnchantmentManager
 *  net.minecraft.server.EntityHuman
 *  net.minecraft.server.IBlockData
 *  net.minecraft.server.IInventory
 *  net.minecraft.server.InventorySubcontainer
 *  net.minecraft.server.Item
 *  net.minecraft.server.ItemEnchantedBook
 *  net.minecraft.server.ItemStack
 *  net.minecraft.server.Items
 *  net.minecraft.server.PlayerAbilities
 *  net.minecraft.server.PlayerInventory
 *  net.minecraft.server.Statistic
 *  net.minecraft.server.StatisticList
 *  net.minecraft.server.WeightedRandomEnchant
 *  net.minecraft.server.World
 *  org.bukkit.block.Block
 *  org.bukkit.craftbukkit.CraftServer
 *  org.bukkit.craftbukkit.CraftWorld
 *  org.bukkit.craftbukkit.entity.CraftHumanEntity
 *  org.bukkit.craftbukkit.inventory.CraftInventoryView
 *  org.bukkit.craftbukkit.inventory.CraftItemStack
 *  org.bukkit.enchantments.Enchantment
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Event
 *  org.bukkit.event.enchantment.EnchantItemEvent
 *  org.bukkit.event.enchantment.PrepareItemEnchantEvent
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.PluginManager
 */
package protocolsupport.server.container;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.server.Block;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.Blocks;
import net.minecraft.server.EnchantmentManager;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.IBlockData;
import net.minecraft.server.IInventory;
import net.minecraft.server.InventorySubcontainer;
import net.minecraft.server.Item;
import net.minecraft.server.ItemEnchantedBook;
import net.minecraft.server.Items;
import net.minecraft.server.PlayerAbilities;
import net.minecraft.server.PlayerInventory;
import net.minecraft.server.Statistic;
import net.minecraft.server.StatisticList;
import net.minecraft.server.WeightedRandomEnchant;
import net.minecraft.server.World;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.inventory.CraftInventoryView;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import protocolsupport.api.ProtocolSupportAPI;
import protocolsupport.api.ProtocolVersion;

public class ContainerEnchantTable
extends net.minecraft.server.ContainerEnchantTable {
    private final Random random = new Random();
    private World world;
    private BlockPosition position;
    private Player player;

    public ContainerEnchantTable(PlayerInventory inv, World world, BlockPosition pos) {
        super(inv, world, pos);
        this.world = world;
        this.position = pos;
        this.player = (Player)inv.player.getBukkitEntity();
    }

    public void a(IInventory iinventory) {
        if (iinventory == this.enchantSlots) {
            net.minecraft.server.ItemStack itemstack = iinventory.getItem(0);
            if (itemstack != null) {
                int bookShelfs = 0;
                for (int z = -1; z <= 1; ++z) {
                    for (int x = -1; x <= 1; ++x) {
                        if (z == 0 && x == 0 || !this.world.isEmpty(this.position.a(x, 0, z)) || !this.world.isEmpty(this.position.a(x, 1, z))) continue;
                        if (this.world.getType(this.position.a(x * 2, 0, z * 2)).getBlock() == Blocks.BOOKSHELF) {
                            ++bookShelfs;
                        }
                        if (this.world.getType(this.position.a(x * 2, 1, z * 2)).getBlock() == Blocks.BOOKSHELF) {
                            ++bookShelfs;
                        }
                        if (x == 0 || z == 0) continue;
                        if (this.world.getType(this.position.a(x * 2, 0, z)).getBlock() == Blocks.BOOKSHELF) {
                            ++bookShelfs;
                        }
                        if (this.world.getType(this.position.a(x * 2, 1, z)).getBlock() == Blocks.BOOKSHELF) {
                            ++bookShelfs;
                        }
                        if (this.world.getType(this.position.a(x, 0, z * 2)).getBlock() == Blocks.BOOKSHELF) {
                            ++bookShelfs;
                        }
                        if (this.world.getType(this.position.a(x, 1, z * 2)).getBlock() != Blocks.BOOKSHELF) continue;
                        ++bookShelfs;
                    }
                }
                this.random.setSeed(this.f);
                for (int i = 0; i < 3; ++i) {
                    this.costs[i] = EnchantmentManager.a((Random)this.random, (int)i, (int)bookShelfs, (net.minecraft.server.ItemStack)itemstack);
                    this.h[i] = -1;
                    if (this.costs[i] >= i + 1) continue;
                    this.costs[i] = 0;
                }
                CraftItemStack item = CraftItemStack.asCraftMirror((net.minecraft.server.ItemStack)itemstack);
                PrepareItemEnchantEvent event = new PrepareItemEnchantEvent(this.player, (InventoryView)this.getBukkitView(), this.world.getWorld().getBlockAt(this.position.getX(), this.position.getY(), this.position.getZ()), (ItemStack)item, this.costs, bookShelfs);
                event.setCancelled(!itemstack.v());
                this.world.getServer().getPluginManager().callEvent((Event)event);
                if (event.isCancelled()) {
                    for (bookShelfs = 0; bookShelfs < 3; ++bookShelfs) {
                        this.costs[bookShelfs] = 0;
                    }
                    return;
                }
                for (int i = 0; i < 3; ++i) {
                    List<WeightedRandomEnchant> list;
                    if (this.costs[i] <= 0 || (list = this.getEnchantments(itemstack, i, this.costs[i])) == null || list.isEmpty()) continue;
                    WeightedRandomEnchant weightedrandomenchant = list.get(this.random.nextInt(list.size()));
                    this.h[i] = weightedrandomenchant.enchantment.id | weightedrandomenchant.level << 8;
                }
                this.b();
            } else {
                for (int i = 0; i < 3; ++i) {
                    this.costs[i] = 0;
                    this.h[i] = -1;
                }
            }
        }
    }

    public boolean a(EntityHuman entityhuman, int slot) {
        boolean supportsLapisSlot = ProtocolSupportAPI.getProtocolVersion((Player)entityhuman.getBukkitEntity()) == ProtocolVersion.MINECRAFT_1_8;
        net.minecraft.server.ItemStack itemstack = this.enchantSlots.getItem(0);
        net.minecraft.server.ItemStack lapis = this.enchantSlots.getItem(1);
        int cost = slot + 1;
        if (supportsLapisSlot && (lapis == null || lapis.count < cost) && !entityhuman.abilities.canInstantlyBuild) {
            return false;
        }
        if (this.costs[slot] > 0 && itemstack != null && (entityhuman.expLevel >= cost && entityhuman.expLevel >= this.costs[slot] || entityhuman.abilities.canInstantlyBuild)) {
            List<WeightedRandomEnchant> enchantments = this.getEnchantments(itemstack, slot, this.costs[slot]);
            if (enchantments == null) {
                enchantments = new ArrayList<WeightedRandomEnchant>();
            }
            boolean isBook = itemstack.getItem() == Items.BOOK;
            HashMap<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();
            for (WeightedRandomEnchant enchantment : enchantments) {
                enchants.put(Enchantment.getById((int)enchantment.enchantment.id), enchantment.level);
            }
            CraftItemStack item = CraftItemStack.asCraftMirror((net.minecraft.server.ItemStack)itemstack);
            EnchantItemEvent event = new EnchantItemEvent((Player)entityhuman.getBukkitEntity(), (InventoryView)this.getBukkitView(), this.world.getWorld().getBlockAt(this.position.getX(), this.position.getY(), this.position.getZ()), (ItemStack)item, this.costs[slot], enchants, slot);
            this.world.getServer().getPluginManager().callEvent((Event)event);
            int level = event.getExpLevelCost();
            if (event.isCancelled() || level > entityhuman.expLevel && !entityhuman.abilities.canInstantlyBuild || event.getEnchantsToAdd().isEmpty()) {
                return false;
            }
            if (isBook) {
                itemstack.setItem((Item)Items.ENCHANTED_BOOK);
            }
            for (Map.Entry entry : event.getEnchantsToAdd().entrySet()) {
                try {
                    if (isBook) {
                        int enchantId = ((Enchantment)entry.getKey()).getId();
                        if (net.minecraft.server.Enchantment.getById((int)enchantId) == null) continue;
                        WeightedRandomEnchant enchantment = new WeightedRandomEnchant(net.minecraft.server.Enchantment.getById((int)enchantId), ((Integer)entry.getValue()).intValue());
                        Items.ENCHANTED_BOOK.a(itemstack, enchantment);
                        continue;
                    }
                    item.addUnsafeEnchantment((Enchantment)entry.getKey(), ((Integer)entry.getValue()).intValue());
                }
                catch (IllegalArgumentException enchantId) {}
            }
            entityhuman.enchantDone(supportsLapisSlot ? cost : this.costs[slot]);
            if (!entityhuman.abilities.canInstantlyBuild && supportsLapisSlot) {
                net.minecraft.server.ItemStack itemStack = lapis;
                itemStack.count -= cost;
                if (lapis.count <= 0) {
                    this.enchantSlots.setItem(1, null);
                }
            }
            entityhuman.b(StatisticList.W);
            this.enchantSlots.update();
            this.f = entityhuman.cj();
            this.a((IInventory)this.enchantSlots);
            return true;
        }
        return false;
    }

    private List<WeightedRandomEnchant> getEnchantments(net.minecraft.server.ItemStack itemstack, int slot, int cost) {
        this.random.setSeed(this.f + slot);
        List list = EnchantmentManager.b((Random)this.random, (net.minecraft.server.ItemStack)itemstack, (int)cost);
        if (itemstack.getItem() == Items.BOOK && list != null && list.size() > 1) {
            list.remove(this.random.nextInt(list.size()));
        }
        return list;
    }
}

