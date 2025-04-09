package org.nano.asyncTest.domain.box;

import org.bukkit.Material;

import java.util.EnumSet;
import java.util.Set;

public class ItemTypeClassifier {

    private static final Set<Material> MINERALS = EnumSet.of(
            Material.COAL, Material.IRON_INGOT, Material.GOLD_INGOT,
            Material.DIAMOND, Material.EMERALD, Material.COPPER_INGOT
    );

    private static final Set<Material> WOODS = EnumSet.of(
            Material.OAK_LOG, Material.BIRCH_LOG, Material.SPRUCE_LOG,
            Material.ACACIA_LOG, Material.JUNGLE_LOG, Material.DARK_OAK_LOG,
            Material.STICK
    );

    private static final Set<Material> FOODS = EnumSet.of(
            Material.BREAD, Material.COOKED_BEEF, Material.APPLE,
            Material.CARROT, Material.COOKED_CHICKEN
    );

    private static final Set<Material> LOOTS = EnumSet.of(
            Material.ROTTEN_FLESH, Material.BONE, Material.STRING,
            Material.GUNPOWDER, Material.SPIDER_EYE
    );

    public static Category classify(Material material) {
        if (MINERALS.contains(material)) return Category.MINERAL;
        if (WOODS.contains(material)) return Category.WOOD;
        if (FOODS.contains(material)) return Category.FOOD;
        if (LOOTS.contains(material)) return Category.LOOT;
        return Category.UNKNOWN;
    }
}
