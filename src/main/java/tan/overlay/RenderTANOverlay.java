package tan.overlay;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public abstract class RenderTANOverlay
{
    public Minecraft minecraft = Minecraft.getMinecraft();
    public Random rand = new Random();
    
    public FontRenderer fontRenderer = minecraft.fontRenderer;
    public ScaledResolution scaledRes;
    
    public ResourceLocation overlayLocation = new ResourceLocation("toughasnails:textures/overlay/overlay.png");
    
    public NBTTagCompound tanData;
    
    public int updateCounter;

    @ForgeSubscribe
    public void render(RenderGameOverlayEvent.Pre event)
    {
        scaledRes = event.resolution;
        tanData = minecraft.thePlayer.getEntityData().getCompoundTag("ToughAsNails");
        
        this.updateCounter++;
        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        this.preRender(event);
    }
    
    abstract void preRender(RenderGameOverlayEvent.Pre event);
    
    public static void bindTexture(ResourceLocation resourceLocation)
    {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(resourceLocation);
    }
    
    public void drawStringWithBorder(FontRenderer fontrenderer, String string, int x, int y, int borderColour, int colour)
    {
        fontrenderer.drawString(string, x + 1, y, borderColour);
        fontrenderer.drawString(string, x - 1, y, borderColour);
        fontrenderer.drawString(string, x, y + 1, borderColour);
        fontrenderer.drawString(string, x, y - 1, borderColour);
        fontrenderer.drawString(string, x, y, colour);
    }

    public void drawTexturedModalRect(int x, int y, int u, int v, int width, int height)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + height), 0, (double)((float)(u + 0) * f), (double)((float)(v + height) * f1));
        tessellator.addVertexWithUV((double)(x + width), (double)(y + height), 0, (double)((float)(u + width) * f), (double)((float)(v + height) * f1));
        tessellator.addVertexWithUV((double)(x + width), (double)(y + 0), 0, (double)((float)(u + width) * f), (double)((float)(v + 0) * f1));
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + 0), 0, (double)((float)(u + 0) * f), (double)((float)(v + 0) * f1));
        tessellator.draw();
    }
}
