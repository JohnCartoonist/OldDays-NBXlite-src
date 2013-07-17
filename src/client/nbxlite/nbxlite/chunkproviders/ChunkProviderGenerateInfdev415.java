package net.minecraft.src.nbxlite.chunkproviders;

import java.util.Random;
import net.minecraft.src.*;
import net.minecraft.src.nbxlite.noise.InfdevNoiseGeneratorOctaves;
import net.minecraft.src.nbxlite.mapgens.MapGenStronghold2;
import net.minecraft.src.nbxlite.mapgens.OldWorldGenBigTree;
import net.minecraft.src.nbxlite.mapgens.OldWorldGenTrees;
import net.minecraft.src.nbxlite.mapgens.SuperOldWorldGenMinable;

public class ChunkProviderGenerateInfdev415 extends ChunkProviderBaseInfinite{
    private InfdevNoiseGeneratorOctaves b;
    private InfdevNoiseGeneratorOctaves c;
    private InfdevNoiseGeneratorOctaves d;
    private InfdevNoiseGeneratorOctaves e;
    private InfdevNoiseGeneratorOctaves f;
    private InfdevNoiseGeneratorOctaves g;
    private World h;
    public MapGenStronghold2 strongholdGenerator;
    public MapGenVillage villageGenerator;
    public MapGenMineshaft mineshaftGenerator;
    private MapGenBase ravineGenerator;

    public ChunkProviderGenerateInfdev415(World world, long l, boolean flag){
        super(world, l, flag);
        fixLight = true;
        h = world;
        b = new InfdevNoiseGeneratorOctaves(rand, 16);
        c = new InfdevNoiseGeneratorOctaves(rand, 16);
        d = new InfdevNoiseGeneratorOctaves(rand, 8);
        e = new InfdevNoiseGeneratorOctaves(rand, 4);
        f = new InfdevNoiseGeneratorOctaves(rand, 4);
        new InfdevNoiseGeneratorOctaves(rand, 5);
        g = new InfdevNoiseGeneratorOctaves(rand, 5);
        if(flag)
        {
            strongholdGenerator = new MapGenStronghold2();
            villageGenerator = new MapGenVillage();
            mineshaftGenerator = new MapGenMineshaft();
            ravineGenerator = new MapGenRavine();
        }
    }

    @Override
    protected void generateTerrain(int i, int j, byte abyte0[]){
        for(int k = 0; k < 4; k++)
        {
            for(int i1 = 0; i1 < 4; i1++)
            {
                double ad[][] = new double[33][4];
                int k1 = (i << 2) + k;
                int i2 = (j << 2) + i1;
                for(int j2 = 0; j2 < ad.length; j2++)
                {
                    ad[j2][0] = generateNoise(k1, j2, i2);
                    ad[j2][1] = generateNoise(k1, j2, i2 + 1);
                    ad[j2][2] = generateNoise(k1 + 1, j2, i2);
                    ad[j2][3] = generateNoise(k1 + 1, j2, i2 + 1);
                }

                for(int l1 = 0; l1 < 32; l1++)
                {
                    double d3 = ad[l1][0];
                    double d5 = ad[l1][1];
                    double d6 = ad[l1][2];
                    double d7 = ad[l1][3];
                    double d8 = ad[l1 + 1][0];
                    double d9 = ad[l1 + 1][1];
                    double d10 = ad[l1 + 1][2];
                    double d11 = ad[l1 + 1][3];
                    for(int i4 = 0; i4 < 4; i4++)
                    {
                        double d12 = (double)i4 / 4D;
                        double d13 = d3 + (d8 - d3) * d12;
                        double d14 = d5 + (d9 - d5) * d12;
                        double d15 = d6 + (d10 - d6) * d12;
                        double d16 = d7 + (d11 - d7) * d12;
                        for(int j4 = 0; j4 < 4; j4++)
                        {
                            double d17 = (double)j4 / 4D;
                            double d18 = d13 + (d15 - d13) * d17;
                            double d19 = d14 + (d16 - d14) * d17;
                            int k4 = j4 + (k << 2) << 11 | 0 + (i1 << 2) << 7 | (l1 << 2) + i4;
                            for(int l4 = 0; l4 < 4; l4++)
                            {
                                double d20 = (double)l4 / 4D;
                                double d21 = d18 + (d19 - d18) * d20;
                                int i5 = 0;
                                if((l1 << 2) + i4 < 64)
                                    i5 = Block.waterStill.blockID;
                                if(d21 > 0.0D)
                                    i5 = Block.stone.blockID;
                                abyte0[k4] = (byte)i5;
                                k4 += 128;
                            }

                        }

                    }

                }

            }

        }
    }

    @Override
    protected void replaceBlocks(int i, int j, byte abyte0[]){
        for(int l = 0; l < 16; l++)
        {
            for(int j1 = 0; j1 < 16; j1++)
            {
                double d2 = (i << 4) + l;
                double d4 = (j << 4) + j1;
                boolean flag = ODNBXlite.MapFeatures==ODNBXlite.FEATURES_INFDEV0415 && e.generateNoise(d2 * 0.03125D, d4 * 0.03125D, 0.0D) + rand.nextDouble() * 0.20000000000000001D > 0.0D;
                boolean flag1 = ODNBXlite.MapFeatures==ODNBXlite.FEATURES_INFDEV0415 && e.generateNoise(d4 * 0.03125D, 109.0134D, d2 * 0.03125D) + rand.nextDouble() * 0.20000000000000001D > 3D;
                int k2 = (int)(f.func_806_a(d2 * 0.03125D * 2D, d4 * 0.03125D * 2D) / 3D + 3D + rand.nextDouble() * 0.25D);
                int l2 = l << 11 | j1 << 7 | 0x7f;
                int i3 = -1;
                int j3 = Block.grass.blockID;
                int k3 = Block.dirt.blockID;
                for(int l3 = 127; l3 >= 0; l3--)
                {
                    if(abyte0[l2] == 0)
                        i3 = -1;
                    else
                    if(abyte0[l2] == Block.stone.blockID)
                        if(i3 == -1)
                        {
                            if(k2 <= 0)
                            {
                                j3 = 0;
                                k3 = (byte)Block.stone.blockID;
                            } else
                            if(l3 >= 60 && l3 <= 65)
                            {
                                j3 = Block.grass.blockID;
                                k3 = Block.dirt.blockID;
                                if(flag1)
                                    j3 = 0;
                                if(flag1)
                                    k3 = Block.gravel.blockID;
                                if(flag)
                                    j3 = Block.sand.blockID;
                                if(flag)
                                    k3 = Block.sand.blockID;
                            }
                            if(l3 < 64 && j3 == 0)
                                j3 = Block.waterStill.blockID;
                            i3 = k2;
                            if(l3 >= 63)
                                abyte0[l2] = (byte)j3;
                            else
                                abyte0[l2] = (byte)k3;
                        } else
                        if(i3 > 0)
                        {
                            i3--;
                            abyte0[l2] = (byte)k3;
                        }
                    l2--;
                }

            }

        }
    }

    private double generateNoise(double d1, double d2, double d3)
    {
        double d4;
        if((d4 = d2 * 4D - 64D) < 0.0D)
            d4 *= 3D;
        double d5;
        double d9;
        if((d5 = d.generateNoise((d1 * 684.41200000000003D) / 80D, (d2 * 684.41200000000003D) / 400D, (d3 * 684.41200000000003D) / 80D) / 2D) < -1D)
        {
            double d6;
            if((d9 = (d6 = b.generateNoise(d1 * 684.41200000000003D, d2 * 984.41200000000003D, d3 * 684.41200000000003D) / 512D) - d4) < -10D)
                d9 = -10D;
            if(d9 > 10D)
                d9 = 10D;
        } else
        if(d5 > 1.0D)
        {
            double d7;
            if((d9 = (d7 = c.generateNoise(d1 * 684.41200000000003D, d2 * 984.41200000000003D, d3 * 684.41200000000003D) / 512D) - d4) < -10D)
                d9 = -10D;
            if(d9 > 10D)
                d9 = 10D;
        } else
        {
            double d10 = b.generateNoise(d1 * 684.41200000000003D, d2 * 984.41200000000003D, d3 * 684.41200000000003D) / 512D - d4;
            double d11 = c.generateNoise(d1 * 684.41200000000003D, d2 * 984.41200000000003D, d3 * 684.41200000000003D) / 512D - d4;
            if(d10 < -10D)
                d10 = -10D;
            if(d10 > 10D)
                d10 = 10D;
            if(d11 < -10D)
                d11 = -10D;
            if(d11 > 10D)
                d11 = 10D;
            double d12 = (d5 + 1.0D) / 2D;
            double d8;
            d9 = d8 = d10 + (d11 - d10) * d12;
        }
        return d9;
    }

    @Override
    protected void generateStructures(int i, int j, byte abyte0[]){
        if(mapFeaturesEnabled){
            if (ODNBXlite.Structures[0]){
                ravineGenerator.generate(this, worldObj, i, j, abyte0);
            }
            if (ODNBXlite.Structures[3]){
                mineshaftGenerator.generate(this, worldObj, i, j, abyte0);
            }
            if (ODNBXlite.Structures[1]){
                villageGenerator.generate(this, worldObj, i, j, abyte0);
            }
            if (ODNBXlite.Structures[2]){
                strongholdGenerator.generate(this, worldObj, i, j, abyte0);
            }
        }
    }

    @Override
    public void populate(IChunkProvider ichunkprovider, int i, int j){
        rand.setSeed((long)i * 0x12f88dd3L + (long)j * 0x36d41eecL);
        int ii = i;
        int jj = j;
        int a1 = i << 4;
        i = j << 4;
        if(mapFeaturesEnabled)
        {
            if (ODNBXlite.Structures[2]){
                strongholdGenerator.generateStructuresInChunk(worldObj, rand, ii, jj);
            }
            if (ODNBXlite.Structures[1]){
                villageGenerator.generateStructuresInChunk(worldObj, rand, ii, jj);
            }
            if (ODNBXlite.Structures[3]){
                mineshaftGenerator.generateStructuresInChunk(worldObj, rand, ii, jj);
            }
        }
        for(j = 0; j < 20; j++)
        {
            int k = a1 + rand.nextInt(16);
            int k1 = rand.nextInt(128);
            int l2 = i + rand.nextInt(16);
            (new SuperOldWorldGenMinable(Block.oreCoal.blockID)).generate_infdev(h, rand, k, k1, l2);
        }

        for(j = 0; j < 10; j++)
        {
            int l = a1 + rand.nextInt(16);
            int l1 = rand.nextInt(64);
            int i3 = i + rand.nextInt(16);
            (new SuperOldWorldGenMinable(Block.oreIron.blockID)).generate_infdev(h, rand, l, l1, i3);
        }

        if(rand.nextInt(2) == 0)
        {
            j = a1 + rand.nextInt(16);
            int i1 = rand.nextInt(32);
            int i2 = i + rand.nextInt(16);
            (new SuperOldWorldGenMinable(Block.oreGold.blockID)).generate_infdev(h, rand, j, i1, i2);
        }
        if(rand.nextInt(8) == 0)
        {
            j = a1 + rand.nextInt(16);
            int j1 = rand.nextInt(16);
            int j2 = i + rand.nextInt(16);
            (new SuperOldWorldGenMinable(Block.oreDiamond.blockID)).generate_infdev(h, rand, j, j1, j2);
        }
        j = (int)g.func_806_a((double)a1 * 0.25D, (double)i * 0.25D) << 3;
        WorldGenerator treegen = ODNBXlite.MapFeatures==ODNBXlite.FEATURES_INFDEV0327 ? new OldWorldGenTrees(false) : new OldWorldGenBigTree();
        for(int k2 = 0; k2 < j; k2++)
        {
            int j3 = a1 + rand.nextInt(16) + 8;
            int k3 = i + rand.nextInt(16) + 8;
            treegen.setScale(1.0D, 1.0D, 1.0D);
            treegen.generate(h, rand, j3, h.getHeightValue(j3, k3), k3);
        }
        spawnAnimals(ii * 16, jj * 16);
    }

    @Override
    public ChunkPosition findClosestStructure(World world, String s, int i, int j, int k){
        if("Stronghold".equals(s) && strongholdGenerator != null){
            return strongholdGenerator.getNearestInstance(world, i, j, k);
        }else{
            return null;
        }
    }
}
