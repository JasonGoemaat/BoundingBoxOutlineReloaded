package com.irtimaled.bbor.client.interop;

import com.irtimaled.bbor.client.config.ConfigManager;
import com.irtimaled.bbor.client.config.HexColor;
import com.irtimaled.bbor.client.config.Setting;
import com.irtimaled.bbor.common.models.Coords;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FlowerForestHelper {
    private static final Random random = new Random();

    private static final Map<BlockState, Setting<HexColor>> flowerColorMap = new HashMap<>();
    private static final FlowersFeature flowersFeature;

    static {
        flowerColorMap.put(Blocks.DANDELION.getDefaultState(), ConfigManager.colorFlowerForestDandelion);
        flowerColorMap.put(Blocks.POPPY.getDefaultState(), ConfigManager.colorFlowerForestPoppy);
        flowerColorMap.put(Blocks.ALLIUM.getDefaultState(), ConfigManager.colorFlowerForestAllium);
        flowerColorMap.put(Blocks.AZURE_BLUET.getDefaultState(), ConfigManager.colorFlowerForestAzureBluet);
        flowerColorMap.put(Blocks.RED_TULIP.getDefaultState(), ConfigManager.colorFlowerForestRedTulip);
        flowerColorMap.put(Blocks.ORANGE_TULIP.getDefaultState(), ConfigManager.colorFlowerForestOrangeTulip);
        flowerColorMap.put(Blocks.WHITE_TULIP.getDefaultState(), ConfigManager.colorFlowerForestWhiteTulip);
        flowerColorMap.put(Blocks.PINK_TULIP.getDefaultState(), ConfigManager.colorFlowerForestPinkTulip);
        flowerColorMap.put(Blocks.OXEYE_DAISY.getDefaultState(), ConfigManager.colorFlowerForestOxeyeDaisy);
        flowerColorMap.put(Blocks.CORNFLOWER.getDefaultState(), ConfigManager.colorFlowerForestCornflower);
        flowerColorMap.put(Blocks.LILY_OF_THE_VALLEY.getDefaultState(), ConfigManager.colorFlowerForestLilyOfTheValley);

        DecoratedFeatureConfig config = (DecoratedFeatureConfig) Biomes.FLOWER_FOREST.getFlowers().get(0).config;
        flowersFeature = (FlowersFeature) config.feature.feature;
    }

    public static Setting<HexColor> getFlowerColorAtPos(Coords coords) {
        int x = coords.getX();
        int z = coords.getZ();
        BlockState blockState = flowersFeature.getRandomFlower(random, new BlockPos(x, coords.getY(), z));
        return flowerColorMap.get(blockState);
    }

    public static void setSeed(long seed) {
        random.setSeed(seed);
    }

    public static boolean canGrowFlower(int x, int y, int z) {
        return Minecraft.getInstance().world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.GRASS_BLOCK;
    }
}
