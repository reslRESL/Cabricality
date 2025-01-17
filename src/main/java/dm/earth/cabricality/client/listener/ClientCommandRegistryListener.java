package dm.earth.cabricality.client.listener;

import dm.earth.cabricality.client.CabricalityClient;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandBuildContext;
import net.minecraft.server.command.CommandManager;
import dm.earth.cabricality.client.command.HeldItemInfoCommand;
import dm.earth.cabricality.client.command.GenTradingQuestsCommand;
import com.mojang.brigadier.CommandDispatcher;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class ClientCommandRegistryListener implements ClientCommandRegistrationCallback {
	@Override
	public void register(
			CommandDispatcher<FabricClientCommandSource> dispatcher,
			CommandBuildContext commandBuildContext
	) {
		dispatcher.register(
				literal(CabricalityClient.ID)
						.then(literal("genTradingQuests").executes(new GenTradingQuestsCommand()))
						.then(literal("heldItemInfo").executes(new HeldItemInfoCommand()))
		);

		dispatcher.register(literal("i").executes(new HeldItemInfoCommand())); // Alias
	}
}
