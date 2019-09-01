package com.irtimaled.bbor.client.renderers;

import com.irtimaled.bbor.client.PlayerCoords;
import com.irtimaled.bbor.common.models.BoundingBoxMobSpawner;
import com.irtimaled.bbor.common.models.Colors;
import com.irtimaled.bbor.common.models.Coords;
import com.irtimaled.bbor.config.ConfigManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class MobSpawnerRenderer extends AbstractRenderer<BoundingBoxMobSpawner> {
    @Override
    public void render(BoundingBoxMobSpawner boundingBox) {
        Coords coords = boundingBox.getCoords();
        Color color = boundingBox.getColor();
        OffsetBox bb = ConfigManager.renderMobSpawnerSpawnArea.get() ?
                new OffsetBox(boundingBox.getMinCoords(), boundingBox.getMaxCoords()) :
                new OffsetBox(coords, coords);
        renderCuboid(bb, color);

        if (!ConfigManager.renderMobSpawnerActivationLines.get()) return;

        renderActivationLine(new OffsetPoint(coords).offset(0.5, 0.5, 0.5));
    }

    private void renderActivationLine(OffsetPoint centerPoint) {
        OffsetPoint playerPos = new OffsetPoint(PlayerCoords.getX(), PlayerCoords.getY(), PlayerCoords.getZ());
        double distance = centerPoint.getDistance(playerPos);
        if (distance <= 20) {
            Color color = distance > 18 ? Color.RED : distance > 16 ? Colors.DARK_ORANGE : Color.GREEN;

            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
            Renderer.startLines().setColor(color).addPoint(centerPoint).addPoint(playerPos.offset(0, 0.1, 0)).render();
        }
    }
}
