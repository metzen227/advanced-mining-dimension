package de.maxhenkel.miningdimension;

import de.maxhenkel.corelib.config.ConfigBase;
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
    public final ForgeConfigSpec.BooleanValue generateSpawners;
    public final ForgeConfigSpec.BooleanValue generateStoneVariants;
    public final ForgeConfigSpec.BooleanValue generateOres;
    public final ForgeConfigSpec.BooleanValue generateLava;
    public final ForgeConfigSpec.BooleanValue bedrockFloor;
    public final ForgeConfigSpec.BooleanValue bedrockCeiling;

    public final ForgeConfigSpec.IntValue coalMaxHeight;
    public final ForgeConfigSpec.IntValue ironMaxHeight;
    public final ForgeConfigSpec.IntValue goldMaxHeight;
    public final ForgeConfigSpec.IntValue redstoneMaxHeight;
    public final ForgeConfigSpec.IntValue diamondMaxHeight;
    public final ForgeConfigSpec.IntValue lapisBaseline;

    public final ForgeConfigSpec.IntValue coalCount;
    public final ForgeConfigSpec.IntValue ironCount;
    public final ForgeConfigSpec.IntValue goldCount;
    public final ForgeConfigSpec.IntValue redstoneCount;
    public final ForgeConfigSpec.IntValue diamondCount;
    public final ForgeConfigSpec.IntValue lapisCount;

    public RegistryKey<World> overworldDimension;

    public ServerConfig(ForgeConfigSpec.Builder builder) {
        super(builder);
        overworldDimensionSpec = builder
                .comment("The dimension from where you can teleport to the mining dimension and back")
                .define("overworld_dimension", "minecraft:overworld");
        cavePercentage = builder.defineInRange("world_generation.cave_percentage", 0.3D, 0D, 1D);
        canyonPercentage = builder.defineInRange("world_generation.canyon_percentage", 0.02D, 0D, 1D);
        generateLavaLakes = builder.define("world_generation.lava_lakes", true);
        generateSpawners = builder.define("world_generation.spawners", true);
        generateStoneVariants = builder.define("world_generation.stone_variants", true);
        generateOres = builder.define("world_generation.ores", true);
        generateLava = builder
                .comment("If lava should be generated in caves below level 11")
                .define("world_generation.lava", true);
        bedrockFloor = builder
                .comment("If a bedrock layer should be generated at Y=0")
                .comment("Note that setting this to false can cause players to fall into the void")
                .define("world_generation.bedrock_floor", true);
        bedrockCeiling = builder
                .comment("If a bedrock layer should be generated at Y=255")
                .comment("Note that setting this to false causes the game to spawn mobs on top of the world")
                .define("world_generation.bedrock_ceiling", true);

        coalMaxHeight = builder.defineInRange("world_generation.coal_ore.max_height", 128, 1, 256);
        ironMaxHeight = builder.defineInRange("world_generation.iron_ore.max_height", 64, 1, 256);
        goldMaxHeight = builder.defineInRange("world_generation.gold_ore.max_height", 32, 1, 256);
        redstoneMaxHeight = builder.defineInRange("world_generation.redstone_ore.max_height", 16, 1, 256);
        diamondMaxHeight = builder.defineInRange("world_generation.diamond_ore.max_height", 16, 1, 256);
        lapisBaseline = builder.defineInRange("world_generation.lapis_lazuli_ore.baseline", 16, 1, 256);

        coalCount = builder.defineInRange("world_generation.coal_ore.count", 20, 1, 512);
        ironCount = builder.defineInRange("world_generation.iron_ore.count", 20, 1, 512);
        goldCount = builder.defineInRange("world_generation.gold_ore.count", 2, 1, 512);
        redstoneCount = builder.defineInRange("world_generation.redstone_ore.count", 8, 1, 512);
        diamondCount = builder.defineInRange("world_generation.diamond_ore.count", 1, 1, 512);
        lapisCount = builder.defineInRange("world_generation.lapis_lazuli_ore.count", 1, 1, 512);
    }

    @Override
    public void onReload(ModConfig.ModConfigEvent event) {
        super.onReload(event);
        overworldDimension = RegistryKey.func_240903_a_(Registry.field_239699_ae_, new ResourceLocation(overworldDimensionSpec.get()));
        Main.MINING_BIOME.initializeFeatures();
    }

}