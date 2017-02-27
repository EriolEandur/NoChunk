/*
 * This file is part of NoChunks.
 * 
 * NoChunks is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * NoChunks is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with NoChunks.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * 
 */
package com.mcmiddleearth.nochunks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.ChunkGenerator.BiomeGrid;

/**
 *
 * @author Eriol_Eandur
 */
public class VoidGenerator extends ChunkGenerator {
 
    /**
    *
    * @param x
    * X co-ordinate of the block to be set in the array
    * @param y
    * Y co-ordinate of the block to be set in the array
    * @param z
    * Z co-ordinate of the block to be set in the array
    * @param chunk
    * An array containing the Block id's of all the blocks in the chunk. The first offset
    * is the block section number. There are 16 block sections, stacked vertically, each of which
    * 16 by 16 by 16 blocks.
    * @param material
    * The material to set the block to.
    */
    byte getBlock(int x, int y, int z, byte[][] chunk) {
        return 0;
    }

    void setBlock(int x, int y, int z, byte[][] chunk, Material material) {
            // do nothing
    }
 
   
    /**
    * @param world
    * The world the chunk belongs to
    * @param rand
    * Don't use this, make a new random object using the world seed (world.getSeed())
    * @param biome
    * Use this to set/get the current biome
     * @param ChunkZ
    * @param ChunkX and ChunkZ
    * The x and z co-ordinates of the current chunk.
     * @return 
    */
    @Override
    public byte[][] generateBlockSections(World world, Random rand, int ChunkX,
            int ChunkZ, BiomeGrid biome) {
       
       byte[][] chunk = new byte[world.getMaxHeight() / 16][];
       
        return chunk;
    }
    /**
    * Returns a list of all of the block populators (that do "little" features)
    * to be called after the chunk generator
     * @param world
     * @return 
    */
    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        ArrayList<BlockPopulator> pops = new ArrayList<>();
        //Add Block populators here
        return pops;
    }
   
   
    @Override
    public boolean canSpawn(World world, int x, int z) {
        return true;
    }
}
