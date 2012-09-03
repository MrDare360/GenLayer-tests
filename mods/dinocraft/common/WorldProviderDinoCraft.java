package mods.dinocraft.common;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import mods.dinocraft.DinoCraft;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.ChunkCoordinates;
import net.minecraft.src.ChunkProviderEnd;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;
import net.minecraft.src.WorldChunkManagerHell;
import net.minecraft.src.WorldProvider;
import net.minecraft.src.WorldProviderBase;
import net.minecraft.src.WorldProviderEnd;
import net.minecraft.src.WorldProviderHell;
import net.minecraftforge.client.SkyProvider;

public class WorldProviderDinoCraft extends WorldProviderBase {

	private float[] colorsSunriseSunset = new float[4];

	@Override
	public String func_80007_l() {
		return "DinoCraft dimension";
	}

	@Override
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerDinoCraft();
		this.worldType = DinoCraft.worldType;
		this.hasNoSky = false;
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getCloudHeight() {
		return 240.0F;
	}

	@Override
	public boolean canCoordinateBeSpawn(int par1, int par2) {
		int var3 = this.worldObj.getFirstUncoveredBlock(par1, par2);
		return var3 == Block.grass.blockID;
	}

	@Override
	public int getAverageGroundLevel() {
		return 70;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int par1, int par2) {
		return true;
	}

	@Override
	public IChunkProvider getChunkProvider() {
		return new ChunkProviderDinoCraft(this.worldObj, this.worldObj.getSeed());
	}

	@Override
	public float calculateCelestialAngle(long par1, float par3) {
		int var4 = (int) (par1 % 24000L);
		float var5 = ((float) var4 + par3) / 24000.0F - 0.25F;

		if (var5 < 0.0F) {
			++var5;
		}

		if (var5 > 1.0F) {
			--var5;
		}

		float var6 = var5;
		var5 = 1.0F - (float) ((Math.cos((double) var5 * Math.PI) + 1.0D) / 2.0D);
		var5 = var6 + (var5 - var6) / 3.0F;
		return var5;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getMoonPhase(long par1, float par3) {
		return (int) (par1 / 24000L) % 8;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float[] calcSunriseSunsetColors(float par1, float par2) {
		float var3 = 0.4F;
		float var4 = MathHelper.cos(par1 * (float) Math.PI * 2.0F) - 0.0F;
		float var5 = -0.0F;

		if (var4 >= var5 - var3 && var4 <= var5 + var3) {
			float var6 = (var4 - var5) / var3 * 0.5F + 0.5F;
			float var7 = 1.0F - (1.0F - MathHelper.sin(var6 * (float) Math.PI)) * 0.99F;
			var7 *= var7;
			this.colorsSunriseSunset[0] = var6 * 0.3F + 0.7F;
			this.colorsSunriseSunset[1] = var6 * var6 * 0.7F + 0.2F;
			this.colorsSunriseSunset[2] = var6 * var6 * 0.0F + 0.2F;
			this.colorsSunriseSunset[3] = var7;
			return this.colorsSunriseSunset;
		} else {
			return null;
		}
	}

	@Override
	protected void generateLightBrightnessTable() {
		float var1 = 0.0F;

		for (int var2 = 0; var2 <= 15; ++var2) {
			float var3 = 1.0F - (float) var2 / 15.0F;
			this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3 getFogColor(float par1, float par2) {
		float var3 = MathHelper.cos(par1 * (float) Math.PI * 2.0F) * 2.0F + 0.5F;

		if (var3 < 0.0F) {
			var3 = 0.0F;
		}

		if (var3 > 1.0F) {
			var3 = 1.0F;
		}

		float var4 = 0.7529412F;
		float var5 = 0.84705883F;
		float var6 = 1.0F;
		var4 *= var3 * 0.94F + 0.06F;
		var5 *= var3 * 0.94F + 0.06F;
		var6 *= var3 * 0.91F + 0.09F;
		return Vec3.getVec3Pool().getVecFromPool((double) var4, (double) var5, (double) var6);
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isSkyColored() {
		return true;
	}

	@Override
	public ChunkCoordinates getEntrancePortalLocation() {
		return null;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean getWorldHasVoidParticles() {
		return false;
	}

	@Override
	public String getSaveFolder() {
		return "DIM-DINO";
	}

	@Override
	public String getWelcomeMessage() {
		return "We're going back in time!";
	}

	@Override
	public String getDepartMessage() {
		return "Back to the future!";
	}

	@Override
	public double getMovementFactor() {
		return 16.0;
	}

	@Override
	@SideOnly(Side.CLIENT)
    public SkyProvider getSkyProvider()
    {
        return null;
    }

	@Override
	public int getDimensionID() {
		return DinoCraft.dimensionID;
	}
}
