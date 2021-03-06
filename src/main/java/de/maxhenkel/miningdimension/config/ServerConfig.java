package de.maxhenkel.miningdimension.config;

import de.maxhenkel.corelib.config.ConfigBase;
import de.maxhenkel.miningdimension.Main;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class ServerConfig extends ConfigBase {

    private final ForgeConfigSpec.ConfigValue<String> overworldDimensionSpec;
    public final ForgeConfigSpec.DoubleValue cavePercentage;
    public final ForgeConfigSpec.DoubleValue canyonPercentage;
    public final ForgeConfigSpec.BooleanValue generateLavaLakes;
    public final ForgeConfigSpec.IntValue lavaLakeChance;
    public final ForgeConfigSpec.BooleanValue generateSpawners;
    public final ForgeConfigSpec.IntValue spawnerChance;
    public final ForgeConfigSpec.BooleanValue generateLava;
    public final ForgeConfigSpec.BooleanValue bedrockFloor;
    public final ForgeConfigSpec.BooleanValue bedrockCeiling;

    public RegistryKey<World> overworldDimension;

    public ServerConfig(ForgeConfigSpec.Builder builder) {
        super(builder);
        overworldDimensionSpec = builder
                .comment("The dimension from where you can teleport to the mining dimension and back")
                .define("overworld_dimension", "minecraft:overworld");
        cavePercentage = builder.worldRestart().defineInRange("world_generation.cave_percentage", 0.3D, 0D, 1D);
        canyonPercentage = builder.worldRestart().defineInRange("world_generation.canyon_percentage", 0.02D, 0D, 1D);
        generateLavaLakes = builder.worldRestart().define("world_generation.lava_lakes.enabled", true);
        lavaLakeChance = builder.worldRestart().defineInRange("world_generation.lava_lakes.chance", 80, 0, 1024);
        generateSpawners = builder.worldRestart().define("world_generation.spawners.enabled", true);
        spawnerChance = builder.worldRestart().defineInRange("world_generation.spawners.chance", 8, 0, 1024);
        generateLava = builder
                .comment("If lava should be generated in caves below level 11")
                .worldRestart()
                .define("world_generation.lava", true);
        bedrockFloor = builder
                .comment("If a bedrock layer should be generated at Y=0")
                .comment("Note that setting this to false can cause players to fall into the void")
                .worldRestart()
                .define("world_generation.bedrock_floor", true);
        bedrockCeiling = builder
                .comment("If a bedrock layer should be generated at Y=255")
                .comment("Note that setting this to false causes the game to spawn mobs on top of the world")
                .worldRestart()
                .define("world_generation.bedrock_ceiling", true);
    }

    @Override
    public void onReload(ModConfig.ModConfigEvent event) {
        super.onReload(event);
        overworldDimension = RegistryKey.func_240903_a_(Registry.field_239699_ae_, new ResourceLocation(overworldDimensionSpec.get()));
        Main.MINING_BIOME.initializeFeatures();
    }

}