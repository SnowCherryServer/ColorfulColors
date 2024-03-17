package mc233.fun.colorfulcolors;

import java.awt.Color;
import java.util.*;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

public class AllTemplates {
    private static final Map<String, AbstractTemplate> allTemplates = new LinkedHashMap<>();
    private static final List<AbstractTemplate> templatesList = new ArrayList<>();

    public static List<AbstractTemplate> getTemplatesList() {
        return templatesList;
    }

    public static Map<String, AbstractTemplate> getAllTemplates() {
        return allTemplates;
    }

    public static boolean exists(String templateName) {
        return allTemplates.containsKey(templateName);
    }

    public static AbstractTemplate getTemplate(String templateName) {
        return allTemplates.get(templateName);
    }

    private static void addSolidColor(String name, Material material, String displayName, Color color, boolean isPremium, boolean isLimited) {
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "纯色 " + ChatColor.of(color) + name);
        new SolidColors(name, material, ChatColor.RESET + displayName, (ArrayList<String>) lore, new Color[]{color}, isPremium, isLimited);
    }

    private static void addPlus1Animation(String name, Material material, String displayName, List<Color> colors, boolean isPremium) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.of(colors.get(0)) + "是时候" + ChatColor.of(colors.get(2)) + "庆祝" + ChatColor.of(colors.get(0)) + name + "了！");
        new Plus1animation(name, material, ChatColor.RESET + displayName, lore, colors.toArray(new Color[0]), isPremium);
    }

    private static void addRandomAnimation(int rarity, String name, Material material, String displayName, List<Color> colors, boolean isPremium) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "你今天看起来非常" + ChatColor.of(colors.get(0)) + name + ChatColor.GRAY + "！");
        new RandomAnimation(rarity, name, material, ChatColor.RESET + displayName, lore, new Color(87, 145, 247), colors.toArray(new Color[0]), isPremium);
    }


    private static void addBurstAnimation(String name, Material material, String displayName, List<Color> colors, boolean isPremium, int rarity) {
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.of(colors.get(1)) + "传播光明！");
        BurstAnimation burstAnimation = new BurstAnimation(name, material, ChatColor.RESET + displayName, (ArrayList<String>) lore, colors.toArray(new Color[0]), isPremium);
        burstAnimation.setRarity(rarity);
    }

    public static void initiate() {
        addSolidColor("红色", Material.RED_WOOL, "红色", new Color(176, 0, 0), false, false);
        addSolidColor("蓝色", Material.BLUE_WOOL, "蓝色", new Color(36, 87, 227), false, false);
        addSolidColor("绿色", Material.GREEN_WOOL, "绿色", new Color(31, 222, 24), false, false);
        addSolidColor("粉色", Material.PINK_WOOL, "粉色", new Color(222, 33, 190), false, false);
        addSolidColor("橙色", Material.ORANGE_WOOL, "橙色", new Color(235, 152, 52), false, false);
        addSolidColor("紫色", Material.PURPLE_WOOL, "紫色", new Color(183, 52, 235), false, false);
        addSolidColor("浅蓝色", Material.LIGHT_BLUE_WOOL, "浅蓝色", new Color(52, 217, 235), false, false);
        addSolidColor("青色", Material.CYAN_WOOL, "青色", new Color(40, 224, 157), false, false);
        addSolidColor("黄色", Material.YELLOW_WOOL, "黄色", new Color(241, 247, 57), false, false);
        addSolidColor("樱桃色", Material.RED_WOOL, "樱桃色", new Color(250, 23, 84), false, false);
        addSolidColor("深绿色", Material.GREEN_WOOL, "深绿色", new Color(11, 107, 2), false, false);
        addSolidColor("青色", Material.CYAN_WOOL, "青色", new Color(0, 139, 139), false, false);
        addSolidColor("天空蓝色", Material.LIGHT_BLUE_WOOL, "天空蓝色", new Color(131, 163, 222), false, false);
        addSolidColor("金黄色", Material.GOLD_BLOCK, "金黄色", new Color(255, 215, 0), true, true);

        addPlus1Animation("圣诞", Material.SWEET_BERRIES, "圣诞", Arrays.asList(new Color(206, 13, 13), new Color(12, 169, 12), new Color(231, 237, 228)), true);
        addPlus1Animation("圣诞雪", Material.ICE, "冰雪", Arrays.asList(new Color(91, 177, 212), new Color(126, 205, 237), new Color(161, 217, 240), new Color(197, 235, 250), new Color(225, 243, 250)), true);
        addPlus1Animation("骄傲旗帜", Material.RED_GLAZED_TERRACOTTA, "骄傲旗帜", Arrays.asList(new Color(228, 3, 3), new Color(255, 140, 0), new Color(0, 128, 38), new Color(0, 77, 255), new Color(117, 7, 135)), true);
        addPlus1Animation("骄傲旗帜2.0", Material.PINK_GLAZED_TERRACOTTA, "骄傲旗帜2.0", Arrays.asList(new Color(244, 149, 149), new Color(249, 235, 151), new Color(198, 249, 172), new Color(168, 217, 246), new Color(226, 187, 253)), true);

        addRandomAnimation(4, "圣诞装饰", Material.SUNFLOWER, "装饰品", Arrays.asList(new Color(245, 204, 71), new Color(245, 83, 71), new Color(218, 165, 32), new Color(212, 25, 32)), true);
        addRandomAnimation(3, "黄色系", Material.YELLOW_DYE, "黄色系", Arrays.asList(new Color(237, 222, 90), new Color(237, 226, 128), new Color(245, 223, 32), new Color(237, 214, 17)), true);
        addRandomAnimation(3, "淡紫色系", Material.MAGENTA_DYE, "淡紫色系", Arrays.asList(new Color(241, 125, 245), new Color(249, 82, 255), new Color(244, 151, 247), new Color(239, 71, 245)), true);
        addRandomAnimation(3, "蓝色系", Material.BLUE_DYE, "蓝色系", Arrays.asList(new Color(69, 134, 247), new Color(46, 114, 232), new Color(34, 94, 199), new Color(17, 90, 217)), true);

        addBurstAnimation("光明节", Material.LANTERN, "光明节", Arrays.asList(new Color(31, 24, 8), new Color(242, 187, 19)), true, 6);

        Collections.sort(templatesList, Collections.reverseOrder());
        for (AbstractTemplate template : templatesList) {
            allTemplates.put(template.getTemplateName(), template);
        }
    }
}
