package com.nethercoords; 

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.Text;

public class PortalCoordsMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {

            dispatcher.register(
                ClientCommandManager.literal("nethercoords")

                    .then(ClientCommandManager.argument("x", IntegerArgumentType.integer())
                    .then(ClientCommandManager.argument("z", IntegerArgumentType.integer())

                    .executes(context -> {

                        int x = IntegerArgumentType.getInteger(context, "x");
                        int z = IntegerArgumentType.getInteger(context, "z");

                        double netherX = x / 8.0;
                        double netherZ = z / 8.0;

                        String message =
                                "§6Overworld: §fX: " + x + " Z: " + z +
                                "\n§cNether: §fX: " + String.format("%.1f", netherX) +
                                " Z: " + String.format("%.1f", netherZ);

                        context.getSource().sendFeedback(Text.literal(message));

                        return 1;
                    })))
            );

        });
    }
}
{
  "schemaVersion": 1,
  "id": "portalcoords",
  "version": "1.0.0",
  "name": "Portal Coords",
  "description": "Converte coordenadas do overworld para o nether",
  "authors": [
    "Thales"
  ],
  "environment": "client",
  "entrypoints": {
    "client": [
      "com.seumod.PortalCoordsMod"
    ]
  },
  "depends": {
    "fabricloader": ">=0.15.0",
    "minecraft": "~1.21.10",
    "fabric-api": "*"
  }
}
